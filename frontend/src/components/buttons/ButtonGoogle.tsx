import React from "react";
import { Text, TouchableOpacity, StyleSheet, View, Image } from "react-native";
import { LinearGradient } from 'expo-linear-gradient';

type ButtonGoogleProps = {
  text: string;
};

const ButtonGoogle: React.FC<ButtonGoogleProps> = ({ text }) => {
  const _loginWithGoogle = async () => {
    console.log("Logar com o Google");
  };

  return (
    <View style={styles.container}>
      <LinearGradient
        colors={["#0673F9", "#044493"]}
        style={styles.gradientBackground}
      >
        <TouchableOpacity
          style={styles.button}
          activeOpacity={0.8}
          onPress={_loginWithGoogle}
        >
          <Image
            source={require("../../assets/images/logo-google.png")}
            style={styles.image}
            resizeMode="contain"
          />
          <Text style={styles.text}>{text}</Text>
        </TouchableOpacity>
      </LinearGradient>
    </View>
  );
};

export default ButtonGoogle;

const styles = StyleSheet.create({
  container: {
    height: 45,
    width: "100%",
    borderRadius: 8,
    shadowColor: "#6B6B6B",
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.3,
    shadowRadius: 2,
    elevation: 3,
    overflow: "hidden",
  },
  gradientBackground: {
    flex: 1,
    borderRadius: 8,
    justifyContent: "center",
  },
  button: {
    flexDirection: "row",
    backgroundColor: "#fff",
    height: "100%",
    alignItems: "center",
    justifyContent: "center",
    borderRadius: 8,
    paddingHorizontal: 16,
  },
  image: {
    width: 20,
    height: 20,
    marginRight: 8,
  },
  text: {
    fontSize: 15,
    fontWeight: "400",
    color: "#000",
  },
});
