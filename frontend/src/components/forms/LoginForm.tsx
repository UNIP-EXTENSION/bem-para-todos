import React, { useState } from "react";
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ActivityIndicator,
  KeyboardAvoidingView,
  Platform,
} from "react-native";
import { useForm, Controller, SubmitHandler } from "react-hook-form";
import ButtonAuth from "../buttons/ButtonAuth";
import ButtonGoogle from "../buttons/ButtonGoogle";
import InputAuth from "../InputAuth";
import { AuthService } from "../../services/auth_service";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";
import { RootStackParamList } from "../../navigation/types";
import { all } from "axios";
import { useAlert } from "../../hooks/useAlert";
import AlertDialog from "../AlertDialog";

type FormData = {
  email: string;
  password: string;
};

type LoginFormProps = {
  onNavigateToAuthPage?: () => void;
  onNavigateToMainPage?: () => void;
};

const authService = new AuthService();

const LoginForm: React.FC<LoginFormProps> = ({ onNavigateToAuthPage }) => {
  const {
    control,
    handleSubmit,
    formState: { errors },
  } = useForm<FormData>({
    defaultValues: {
      email: "",
      password: "",
    },
    mode: "all",
  });

  const navigation =
    useNavigation<NativeStackNavigationProp<RootStackParamList>>();
  const { alertState, updateAlertState } = useAlert();

  // const [isLoading, setIsLoading] = useState(false);

  const onSubmit: SubmitHandler<FormData> = async (data) => {
    updateAlertState("loading", "Realizando login...");

    try {
      const success = await authService.login(data.email, data.password);

      if (success) {
        updateAlertState("success", "Login realizado com sucesso!");
        setTimeout(() => {
          updateAlertState("idle");
          navigation.replace("Main");
        }, 2000);
      } else {
        updateAlertState(
          "error",
          "Falha no login. Verifique suas credenciais."
        );
      }
    } catch (error: any) {
      console.error("Erro no login:", error);
      updateAlertState("error", error.message || "Ocorreu um erro inesperado.");
    }
  };

  return (
    <KeyboardAvoidingView
      style={styles.keyboardAvoidingView}
      behavior={Platform.OS === "ios" ? "padding" : undefined}
    >
      <View style={styles.container}>
        <Controller
          control={control}
          name="email"
          rules={{
            required: "E-mail é obrigatório",
            pattern: {
              value: /^[\w-.]+@([\w-]+\.)+[\w-]{2,4}$/,
              message: "Formato de e-mail inválido",
            },
          }}
          render={({ field: { onChange, onBlur, value, ...rest } }) => (
            <InputAuth
              labelText="E-mail"
              hintText="Digite seu e-mail"
              value={value}
              onChangeText={onChange}
              error={errors["email"]?.message}
              imagePath={require("../../assets/images/inputs/email.png")}
              {...rest}
            />
          )}
        />
        <View style={styles.spacing} />

        <Controller
          control={control}
          name="password"
          rules={{ required: "Senha é obrigatório" }}
          render={({ field: { onChange, onBlur, value, ...rest } }) => (
            <InputAuth
              labelText="Senha"
              hintText="Digite sua senha"
              value={value}
              onChangeText={onChange}
              error={errors["password"]?.message}
              imagePath={require("../../assets/images/inputs/key.png")}
              {...rest}
            />
          )}
        />

        <View style={styles.forgotPasswordContainer}>
          <TouchableOpacity onPress={() => {}}>
            <Text style={styles.forgotPasswordText}>Esqueceu a senha?</Text>
          </TouchableOpacity>
        </View>

        <View style={styles.smallSpacing} />

        <ButtonAuth text="Entrar" onPress={handleSubmit(onSubmit)} />

        <View style={styles.smallSpacing} />

        <ButtonGoogle text="Entrar com o Google" />

        <View style={styles.largeSpacing} />

        <TouchableOpacity
          onPress={onNavigateToAuthPage}
          style={styles.firstAccessButton}
        >
          <Text style={styles.firstAccessButtonText}>Primeiro acesso?</Text>
        </TouchableOpacity>
      </View>

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

const styles = StyleSheet.create({
  keyboardAvoidingView: {
    flex: 1,
    width: "100%",
  },
  container: {
    justifyContent: "center",
    alignItems: "stretch",
    width: "100%",
  },
  inputMargin: {
    marginBottom: 16,
  },
  forgotPasswordContainer: {
    width: "100%",
    alignItems: "flex-start",
    marginTop: 10,
  },
  forgotPasswordText: {
    color: "white",
  },
  spacing: {
    height: 22,
  },
  largeSpacing: {
    height: 80,
  },
  smallSpacing: {
    height: 10,
  },
  firstAccessButton: {
    backgroundColor: "#FAB603",
    paddingVertical: 12,
    borderRadius: 8,
    width: "100%",
    alignItems: "center",
  },
  firstAccessButtonText: {
    color: "white",
    fontWeight: "bold",
    fontSize: 16,
    textAlign: "center",
  },
  errorMessageText: {
    color: "red",
    marginTop: 10,
    textAlign: "center",
  },
});

export default LoginForm;
