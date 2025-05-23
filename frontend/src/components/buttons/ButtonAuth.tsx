import React from "react";
import {
  Text,
  TouchableOpacity,
  StyleSheet,
  View,
  GestureResponderEvent,
} from "react-native";
import { LinearGradient } from "expo-linear-gradient";

type ButtonAuthProps = {
  text: string;
  onPress: (event: GestureResponderEvent) => void;
  color?: string;
  fontSize?: number;
  isGradient?: boolean;
};

const ButtonAuth: React.FC<ButtonAuthProps> = ({
  text,
  onPress,
  color = "#0673F9",
  fontSize = 20,
  isGradient = true,
}) => {
  const content = (
    <TouchableOpacity
      onPress={onPress}
      activeOpacity={0.8}
      style={styles.buttonContent}
    >
      <Text style={[styles.text, { fontSize }]}>{text}</Text>
    </TouchableOpacity>
  );

  return (
    <View style={styles.container}>
      {isGradient ? (
        <LinearGradient
          colors={["#0673F9", "#044493"]}
          start={{ x: 0, y: 0.5 }}
          end={{ x: 1, y: 0.5 }}
          style={styles.gradientBackground}
        >
          {content}
        </LinearGradient>
      ) : (
        <View style={[styles.plainBackground, { backgroundColor: color }]}>
          {content}
        </View>
      )}
    </View>
  );
};

export default ButtonAuth;

const styles = StyleSheet.create({
  container: {
    height: 45,
    width: "100%",
    borderRadius: 8,
    overflow: "hidden",
  },
  gradientBackground: {
    flex: 1,
    borderRadius: 8,
    justifyContent: "center",
  },
  plainBackground: {
    flex: 1,
    borderRadius: 8,
    justifyContent: "center",
  },
  buttonContent: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    color: "#FFF",
    fontWeight: "bold",
  },
});
