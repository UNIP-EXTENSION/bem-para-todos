import React, { useCallback, useEffect, useState } from "react";
import { View, Text, Image, StyleSheet } from "react-native";
import TopBar from "../../components/bars/top_bar";
import ButtonAuth from "../../components/buttons/ButtonAuth";
import { useNavigation } from "@react-navigation/native";
import { NavigationProp } from "../../navigation/types";
import { AuthService } from "../../services/auth_service";

const authService = new AuthService();

const ProfileScreen = () => {
  const navigation = useNavigation<NavigationProp>();

  const [userName, setUserName] = useState<string>("");
  const [userEmail, setUserEmail] = useState<string>("");

  useEffect(() => {
    const getUserInfo = async () => {
      await new Promise((resolve) => setTimeout(resolve, 1500));

      try {
        const userInfo = await authService.getUserInfo();

        if (userInfo) {
          setUserName(userInfo.name);
          setUserEmail(userInfo.email);
        } else {
          authService.logout();
          navigation.replace("Login");
        }
      } catch (error) {
        authService.logout();
        navigation.replace("Login");
      }
    };

    getUserInfo();
  }, []);

  const onTicketsPress = useCallback(async () => {
    console.log("Botão Meus Ingressos pressionado");
  }, []);

  const handleLogoutPress = useCallback(async () => {
    authService.logout();

    navigation.replace("Login");
  }, []);

  const onEditPress = useCallback(async () => {
    navigation.navigate("EditProfile");
  }, [navigation]);

  return (
    <View style={{ flex: 1 }}>
      <TopBar />

      <View style={styles.container}>
        <View style={styles.userInfoContainer}>
          <View style={styles.avatarContainer}>
            <Image
              source={require("../../assets/images/bars/profile.png")}
              style={styles.avatarImage}
            />
          </View>
          <View style={styles.userInfoText}>
            <Text style={styles.userNameText}>{userName || "..."}</Text>
            <Text style={styles.userEmailText}>{userEmail || "..."}</Text>
          </View>
        </View>

        <View style={styles.sectionSpacing} />

        <View style={styles.buttonsContainer}>
          <ButtonAuth
            text="Meus Ingressos"
            fontSize={16.0}
            onPress={onTicketsPress}
            isGradient={false}
            color="#0675F9"
          />
          <View style={styles.buttonSpacing} />
          <ButtonAuth
            text="Editar Dados"
            fontSize={16.0}
            onPress={onEditPress}
            isGradient={false}
            color="#0675F9"
          />
          <View style={styles.buttonSpacing} />
          <ButtonAuth
            text="Sair"
            fontSize={16.0}
            onPress={handleLogoutPress}
            isGradient={false}
            color="#D35555"
          />
        </View>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#FAB603",
    paddingHorizontal: 28,
  },
  userInfoContainer: {
    paddingVertical: 20,
    backgroundColor: "#FAB603",
    flexDirection: "row",
    alignItems: "center",
  },
  avatarContainer: {
    width: 80,
    height: 80,
    borderRadius: 40,
    overflow: "hidden",
  },
  avatarImage: {
    width: "100%",
    height: "100%",
    resizeMode: "cover",
  },
  userInfoText: {
    marginLeft: 16,
    flexDirection: "column",
    alignItems: "flex-start",
    justifyContent: "center",
  },
  userNameText: {
    fontSize: 20,
    fontWeight: "bold",
    color: "white",
  },
  userEmailText: {
    fontSize: 16,
    fontWeight: "bold",
    color: "white",
  },
  sectionSpacing: {
    height: 25,
  },
  buttonsContainer: {
    flexDirection: "column",
    alignItems: "stretch",
  },
  buttonSpacing: {
    height: 18,
  },
});

export default ProfileScreen;
