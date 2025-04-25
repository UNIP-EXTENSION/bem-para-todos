import React, { useState } from "react";
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

type FormData = {
  name: string;
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
    formState: { errors },
  } = useForm<FormData>();

  const [imageUri, setImageUri] = useState<string | null>(null);

  const pickImage = async () => {
    const { status } = await ImagePicker.requestMediaLibraryPermissionsAsync();
    if (status !== "granted") {
      alert("Permissão negada para acessar a galeria!");
      return;
    }

    const result = await ImagePicker.launchImageLibraryAsync({
      mediaTypes: ['images'],
      quality: 1,
    });

    if (!result.canceled) {
      setImageUri(result.assets[0].uri);
    }
  };

  const onSubmit = (data: FormData) => {
    console.log("Usuário cadastrado:", data);
    console.log("Imagem URI:", imageUri);
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
            name: "name",
            label: "Digite seu nome",
            placeholder: "Digite seu nome",
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
            secureTextEntry: true,
          },
          {
            name: "confirmPassword",
            label: "Confirme sua senha",
            placeholder: "Confirme sua senha",
            secureTextEntry: true,
          },
        ].map(({ name, label, placeholder, ...rest }) => (
          <View key={name} style={styles.inputContainer}>
            <Controller
              name={name as keyof FormData}
              control={control}
              rules={{ required: true }}
              render={({ field: { onChange, value } }) => (
                <InputAuth
                  labelText={label}
                  hintText={placeholder}
                  value={value ?? ""}
                  onChangeText={onChange}
                  error={
                    errors[name as keyof FormData]
                      ? "Campo obrigatório"
                      : undefined
                  }
                  {...rest}
                />
              )}
            />
          </View>
        ))}

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
