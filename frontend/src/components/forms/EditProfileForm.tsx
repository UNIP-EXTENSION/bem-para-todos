import React, { useEffect, useState } from "react";
import {
  View,
  StyleSheet,
  TextInput,
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

type FormData = {
  firstName: string;
  lastName: string;
  email: string;
  confirmEmail: string;
  password: string;
  confirmPassword: string;
};

const EditProfileForm: React.FC = () => {
  const {
    control,
    handleSubmit,
    setValue,
    watch,
    formState: { errors },
  } = useForm<FormData>();

  const userService = new UserService();

  const [imageUri, setImageUri] = useState<string | null>(null);
  const [userId, setUserId] = useState<string>("");
  const [originalEmail, setOriginalEmail] = useState("");

  const emailValue = watch("email");

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
    console.log("Usuário cadastrado:", data);
    console.log("Imagem URI:", imageUri);

    if (data.email !== data.confirmEmail) {
      return;
    }

    try {
      const updated = await userService.updateUser(userId, {
        firstName: data.firstName,
        lastName: data.lastName,
        email: data.email,
      });

      console.log("Usuario foi atualizado:", updated);
    } catch (error) {
      console.error("Erro ao atualizar dados:", error);
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
            <Text style={styles.imagePlaceholderText}>Selecionar Imagem</Text>
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
              rules={{ required: "Campo obrigatório" }}
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
              rules={{
                required: true,
                validate: (value) =>
                  value === emailValue || "Os e-mails não coincidem.",
              }}
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

        <ButtonAuth text="Editar" onPress={handleSubmit(onSubmit)} />
      </ScrollView>
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
    marginBottom: 24,
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
