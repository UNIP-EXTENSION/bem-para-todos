import React from "react";
import { ScrollView, View, Text, Image, StyleSheet } from "react-native";
import AuthForm from "../components/forms/AuthForm";

const AuthPage: React.FC = () => {
  return (
    <View style={styles.container}>
      <Image
        source={require("../assets/images/logo-app.png")}
        style={styles.logo}
      />
      <ScrollView contentContainerStyle={styles.scrollContainer}>
        <Text style={styles.title}>Cadastro</Text>
        <AuthForm />
      </ScrollView>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: "100%",
    backgroundColor: "#F1C12B",
    padding: 45,
  },
  scrollContainer: {
    width: "100%",
    justifyContent: "center",
    alignItems: "center",

  },
  logo: {
    width: 120,
    height: 120,
    resizeMode: "contain",
    alignSelf: 'center',
  },
  title: {
    color: "#FFF",
    fontSize: 30,
    fontWeight: "900",
    marginVertical: 18,
  },
});

export default AuthPage;
