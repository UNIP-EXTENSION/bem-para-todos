import React, { useEffect } from "react";
import { View, StyleSheet, Image, StatusBar } from "react-native";
import { useNavigation } from "@react-navigation/native";

import { AuthService } from "../services/auth_service";
import { NavigationProp } from "../navigation/types";

const SplashScreen: React.FC = () => {
  const navigation = useNavigation<NavigationProp>();
  const authService = new AuthService();

  useEffect(() => {
    const checkAuthentication = async () => {
      await new Promise((resolve) => setTimeout(resolve, 1500));

      try {
        const isLoggedIn = await authService.isLoggedIn();

        if (isLoggedIn) {
          navigation.replace("Main");
        } else {
          navigation.replace("Login");
        }
      } catch (error) {
        navigation.replace("Login");
      }
    };

    checkAuthentication();
  }, []);

  return (
    <View style={styles.container}>
      <StatusBar hidden={true} />

      <Image
        source={require("../assets/images/logo-login.png")}
        style={styles.logo}
        resizeMode="contain"
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#FAB603",
    justifyContent: "center",
    alignItems: "center",
  },
  logo: {
    width: 150,
    height: 150,
  },
});

export default SplashScreen;
