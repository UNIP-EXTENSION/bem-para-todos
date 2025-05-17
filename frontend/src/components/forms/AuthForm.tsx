import React from "react";
import {
  View,
  StyleSheet,
  ScrollView,
  KeyboardAvoidingView,
  Platform,
} from "react-native";
import { useForm, Controller } from "react-hook-form";
import ButtonAuth from "../buttons/ButtonAuth";
import ButtonGoogle from "../buttons/ButtonGoogle";
import InputAuth from "../InputAuth";
import { CrudService } from "../../services/crud_service";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackParamList } from "../../navigation/types";
import { useAlert } from "../../hooks/useAlert";
import AlertDialog from "../AlertDialog";

type FormData = {
  name: string;
  lastName: string;
  email: string;
  confirmEmail: string;
  password: string;
  confirmPassword: string;
};

const mapUser = (formData: any) => {
  return {
    firstName: formData.name,
    lastName: formData.lastName,
    email: formData.confirmEmail,
    password: formData.confirmPassword,
  };
};

const validationRules = {
  name: {
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
    required: "Campo obrigatório",
    minLength: {
      value: 8,
      message: "A senha deve ter pelo menos 8 caracteres",
    },
    validate: (value: string) =>
      (/[A-Za-z]/.test(value) && /\d/.test(value)) ||
      "A senha deve conter letras e números",
  },
  confirmPassword: {
    required: "Campo obrigatório",
    validate: (value: string, formValues: FormData) =>
      value === formValues.password || "As senhas não coincidem",
  },
};

const AuthForm: React.FC = () => {
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>({ mode: "all" });

  const navigation =
    useNavigation<NativeStackNavigationProp<RootStackParamList>>();

  const { alertState, updateAlertState } = useAlert();

  const onSubmit = async (data: FormData) => {
    updateAlertState("loading", "Cadastrando usuário...");

    const userData = mapUser(data);

    try {
      const success = await CrudService.post("users", userData);

      if (success) {
        updateAlertState("success", "Perfil atualizado com sucesso!");

        setTimeout(() => {
          updateAlertState("idle");
          navigation.replace("Login");
        }, 3000);
        navigation.replace("Login");
      }
    } catch (error) {
      console.error("Erro no cadastro:", error);
      updateAlertState("error", "Erro ao cadastrar usuário.");
      throw error;
    }
  };

  return (
    <KeyboardAvoidingView
      style={{ width: "100%" }}
      behavior={Platform.OS === "ios" ? "padding" : undefined}
    >
      <ScrollView contentContainerStyle={styles.container}>
        {[
          {
            name: "name",
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
          {
            name: "confirmEmail",
            label: "Confirme seu e-mail",
            placeholder: "Confirme seu e-mail",
            keyboardType: "email-address",
          },
          {
            name: "password",
            label: "Digite sua senha",
            placeholder: "Sua senha",
            obscureText: true,
          },
          {
            name: "confirmPassword",
            label: "Confirme sua senha",
            placeholder: "Confirme sua senha",
            obscureText: true,
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
                  error={
                    errors[name as keyof FormData]?.message as
                      | string
                      | undefined
                  }
                  obscureText={!!rest.obscureText}
                  {...rest}
                />
              )}
            />
          </View>
        ))}

        <ButtonAuth text="Cadastrar" onPress={handleSubmit(onSubmit)} />

        <View style={{ marginTop: 12 }} />
        <ButtonGoogle text="Cadastrar com o Google" />
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

export default AuthForm;

const styles = StyleSheet.create({
  container: {
    justifyContent: "center",
    alignItems: "stretch",
  },
  title: {
    fontSize: 24,
    fontWeight: "700",
    marginBottom: 20,
    textAlign: "center",
  },
  inputContainer: {
    marginBottom: 22,
  },
  label: {
    fontSize: 14,
    marginBottom: 4,
    fontWeight: "500",
  },
  input: {
    backgroundColor: "#fff",
    borderWidth: 2,
    borderColor: "#FFE500",
    borderRadius: 8,
    paddingHorizontal: 16,
    paddingVertical: 10,
    shadowColor: "#6B6B6B",
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.3,
    shadowRadius: 2,
    elevation: 2,
  },
  errorText: {
    fontSize: 12,
    color: "red",
    marginTop: 4,
  },
});
