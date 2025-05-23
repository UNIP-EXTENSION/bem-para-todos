import React, { useEffect, useState } from "react";
import {
  View,
  StyleSheet,
  ScrollView,
  Text,
  KeyboardAvoidingView,
  Platform,
  TouchableOpacity,
  Image,
} from "react-native";
import { useForm, Controller } from "react-hook-form";
import * as ImagePicker from "expo-image-picker";
import ButtonAuth from "../buttons/ButtonAuth";
import InputAuth from "../InputAuth";
import { AuthStorage } from "../../storage/auth_storage";
import { UserService } from "../../services/user_service";
import AlertDialog from "../AlertDialog";
import { useAlert } from "../../hooks/useAlert";
import { useNavigation } from "@react-navigation/native";
import { NavigationMainProp } from "../../navigation/types";

type FormData = {
  firstName: string;
  lastName: string;
  email: string;
  confirmEmail: string;
  password: string;
  confirmPassword: string;
};

const validationRules = {
  firstName: {
    required: "Campo obrigatório",
    pattern: {
      value: /^[A-Za-zÀ-ÿ\s]+$/,
      message: "Não use números no nome",
    },
  },
  lastName: {
    required: "Campo obrigatório",
    pattern: {
      value: /^[A-Za-zÀ-ÿ\s]+$/,
      message: "Não use números no sobrenome",
    },
  },
  email: {
    required: "Campo obrigatório",
    pattern: {
      value: /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
      message: "E-mail inválido",
    },
  },
  confirmEmail: {
    required: "Campo obrigatório",
    validate: (value: string, formValues: FormData) =>
      value === formValues.email || "Os e-mails não coincidem",
  },
  password: {
    validate: (value: string) => {
      if (!value || value.length === 0) return true;
      if (value.length < 8) return "A senha deve ter pelo menos 8 caracteres";
      if (!/[A-Za-z]/.test(value) || !/\d/.test(value)) {
        return "A senha deve conter letras e números";
      }
      return true;
    },
  },
  confirmPassword: {
    validate: (value: string, formValues: FormData) => {
      if (!formValues.password || formValues.password.length === 0) {
        return true; // não validar se a senha original não foi preenchida
      }
      return value === formValues.password || "As senhas não coincidem";
    },
  },
};

const EditProfileForm: React.FC = () => {
  const {
    control,
    handleSubmit,
    setValue,
    getValues,
    watch,
    formState: { errors },
  } = useForm<FormData>({
    defaultValues: {
      password: "",
      confirmPassword: "",
      firstName: "",
      lastName: "",
      email: "",
      confirmEmail: "",
    },
    mode: "all",
  });

  const userService = new UserService();
  const navigation = useNavigation<NavigationMainProp>();

  const [imageUri, setImageUri] = useState<string | null>(null);
  const [userId, setUserId] = useState<string>("");
  const [originalEmail, setOriginalEmail] = useState("");
  const { alertState, updateAlertState } = useAlert();

  const emailValue = watch("email");
  const passwordValue = watch("password");

  useEffect(() => {
    const fetchData = async () => {
      const session = await AuthStorage.getSession();
      if (session) {
        setUserId(session.uuid);
        try {
          const userData = await userService.getUserData(session.uuid);
          setOriginalEmail(userData.email);

          setValue("firstName", userData.firstName);
          setValue("lastName", userData.lastName);
          setValue("email", userData.email);
        } catch (error) {
          console.error("Erro ao buscar dados do usuário:", error);
        }
      }
    };

    fetchData();
  }, []);

  const pickImage = async () => {
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status !== "granted") {
      return;
    }

    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ["images"],
      quality: 1,
    });

    if (!result.canceled) {
      setImageUri(result.assets[0].uri);
    }
  };

  const onSubmit = async (data: FormData) => {
    console.log("Imagem URI:", imageUri);

    try {
      updateAlertState("loading", "Salvando alterações...");

      await userService.updateUser(userId, {
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
      });

      updateAlertState("success", "Perfil atualizado com sucesso!");

      setTimeout(() => {
        updateAlertState("idle");
        navigation.navigate("Main", { screen: "Profile" });
      }, 3000);
    } catch (error) {
      console.error("Erro ao atualizar dados:", error);
      updateAlertState("success", "Perfil atualizado com sucesso!");
    }
  };

  return (
    <KeyboardAvoidingView
      style={{ width: "100%" }}
      behavior={Platform.OS === "ios" ? "padding" : undefined}
    >
      <ScrollView contentContainerStyle={styles.container}>
        <TouchableOpacity onPress={pickImage} style={styles.imagePicker}>
          {imageUri ? (
            <Image source={{ uri: imageUri }} style={styles.image} />
          ) : (
            <Image source={require("../../assets/images/profile_view.png")} />
          )}
        </TouchableOpacity>
        <Text style={styles.title}>Editar Perfil</Text>
        {[
          {
            name: "firstName",
            label: "Digite seu nome",
            placeholder: "Digite seu nome",
          },
          {
            name: "lastName",
            label: "Digite seu sobrenome",
            placeholder: "Digite seu sobrenome",
          },
          {
            name: "email",
            label: "E-mail",
            placeholder: "E-mail",
            keyboardType: "email-address",
          },
        ].map(({ name, label, placeholder, ...rest }) => (
          <View key={name} style={styles.inputContainer}>
            <Controller
              name={name as keyof FormData}
              control={control}
              rules={validationRules[name as keyof FormData]}
              render={({ field: { onChange, value } }) => (
                <InputAuth
                  labelText={label}
                  hintText={placeholder}
                  value={value ?? ""}
                  onChangeText={onChange}
                  error={errors[name as keyof FormData]?.message}
                  {...rest}
                />
              )}
            />
          </View>
        ))}
        {emailValue !== originalEmail && (
          <View style={styles.inputContainer}>
            <Controller
              name="confirmEmail"
              control={control}
              rules={validationRules.confirmEmail}
              render={({ field: { onChange, value } }) => (
                <InputAuth
                  labelText="Confirme seu e-mail"
                  hintText="Confirme seu e-mail"
                  value={value ?? ""}
                  onChangeText={onChange}
                  error={errors?.confirmEmail?.message}
                />
              )}
            />
          </View>
        )}

        <View style={styles.inputContainer}>
          <Controller
            name="password"
            control={control}
            rules={validationRules.password}
            render={({ field: { onChange, value } }) => (
              <InputAuth
                labelText="Nova Senha"
                hintText="Digite sua nova senha"
                value={value ?? ""}
                onChangeText={onChange}
                obscureText={true}
                error={errors?.password?.message}
              />
            )}
          />
        </View>

        {passwordValue && passwordValue.length > 0 && (
          <View style={styles.inputContainer}>
            <Controller
              name="confirmPassword"
              control={control}
              rules={{
                validate: (value) =>
                  value === passwordValue || "As senhas não coincidem",
              }}
              render={({ field: { onChange, value } }) => (
                <InputAuth
                  labelText="Confirmar Nova Senha"
                  hintText="Confirme sua nova senha"
                  value={value ?? ""}
                  onChangeText={onChange}
                  obscureText={true}
                  error={errors.confirmPassword?.message}
                />
              )}
            />
          </View>
        )}

        <ButtonAuth text="Editar" onPress={handleSubmit(onSubmit)} />
      </ScrollView>

      <AlertDialog
        visible={alertState.visible}
        onClose={() => updateAlertState("idle")}
        textBody={alertState.text}
        dialogIcon={alertState.icon}
        primaryButton={false}
      />
    </KeyboardAvoidingView>
  );
};

export default EditProfileForm;

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "stretch",
  },
  title: {
    color: "#FFF",
    fontSize: 30,
    fontWeight: "900",
    marginVertical: 18,
    textAlign: "center",
  },
  inputContainer: {
    marginBottom: 22,
  },
  imagePicker: {
    alignSelf: "center",
    width: 120,
    height: 120,
    borderRadius: 60,
    backgroundColor: "#ddd",
    justifyContent: "center",
    alignItems: "center",
    marginBottom: 16,
    overflow: "hidden",
  },
  image: {
    width: "100%",
    height: "100%",
    resizeMode: "cover",
  },
  imagePlaceholderText: {
    color: "#555",
  },
});
