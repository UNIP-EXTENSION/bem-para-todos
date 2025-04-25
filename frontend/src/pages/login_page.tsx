import React from "react";
import {
  View,
  StyleSheet,
  ImageBackground,
  ScrollView,
  Image,
} from "react-native";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";

import LoginForm from "../components/forms/LoginForm";

type RootStackParamList = {
  Login: undefined;
  Register: undefined;
  Main: undefined;
};

type NavigationProp = NativeStackNavigationProp<RootStackParamList>;

const LoginPage: React.FC = () => {
  const navigation = useNavigation<NavigationProp>();

  const navigateToAuthPage = () => {
    navigation.navigate("Register");
  };

  const navigateToMainPage = () => {
    navigation.navigate("Main");
  };

  return (
    <ImageBackground
      source={require("../assets/images/background-login.png")}
      style={styles.backgroundImage}
      resizeMode="cover"
    >
      <View style={styles.overlay} />

      <ScrollView contentContainerStyle={styles.scrollViewContent}>
        <View style={styles.contentContainer}>
          <Image
            source={require("../assets/images/logo-login.png")}
            style={styles.logo}
          />

          <View style={styles.spacing} />

          <LoginForm onNavigateToMainPage={navigateToMainPage} onNavigateToAuthPage={navigateToAuthPage} />
        </View>
      </ScrollView>
    </ImageBackground>
  );
};

const styles = StyleSheet.create({
  backgroundImage: {
    flex: 1,
    width: "100%",
    height: "100%",
  },
  overlay: {
    ...StyleSheet.absoluteFillObject,
    backgroundColor: "rgba(0, 0, 0, 0.5)",
  },
  scrollViewContent: {
    flexGrow: 1,
    justifyContent: "center",
    paddingHorizontal: 45,
  },
  contentContainer: {
    alignItems: "stretch",
  },
  logo: {
    height: 140,
    resizeMode: "contain",
    alignSelf: "center",
  },
  spacing: {
    height: 70,
  },
});

export default LoginPage;
