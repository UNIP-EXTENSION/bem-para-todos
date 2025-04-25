// BottomNavBar.tsx
import React from "react";
import { View, Image, TouchableOpacity, StyleSheet } from "react-native";
import { LinearGradient } from "expo-linear-gradient";

const iconMap: { [key: string]: any } = {
  "home.png": require("../../assets/images/bars/home.png"),
  "ticket-fill.png": require("../../assets/images/bars/ticket-fill.png"),
  "profile.png": require("../../assets/images/bars/profile.png"),
};

type BottomNavBarProps = {
  currentIndex: number;
  onTabPress: (index: number) => void;
};

const BottomNavBar = ({ currentIndex, onTabPress }: BottomNavBarProps) => {
  const icons = ["home.png", "ticket-fill.png", "profile.png"];

  const colors = [
    currentIndex === 0 ? "#FAB603" : "white",
    currentIndex === 1 ? "#FAB603" : "white",
    currentIndex === 2 ? "#FAB603" : "white",
  ];

  return (
    <View style={styles.navBarContainer}>
      <LinearGradient
        colors={["#07274D", "#315988", "#07274D"]}
        start={{ x: 0, y: 0.5 }}
        end={{ x: 1, y: 0.5 }}
        style={styles.gradientBackground}
      >
        {icons.map((iconFileName, index) => {
          return (
            <TouchableOpacity
              key={index}
              onPress={() => onTabPress(index)}
              style={styles.navBarItem}
            >
              <Image
                source={iconMap[iconFileName]}
                style={[
                  styles.icon,
                  index === 1 && styles.middleIcon,
                  { tintColor: colors[index] },
                ]}
                resizeMode="contain"
              />
            </TouchableOpacity>
          );
        })}
      </LinearGradient>
    </View>
  );
};

const styles = StyleSheet.create({
  navBarContainer: {
    height: 60,
  },
  gradientBackground: {
    flex: 1,
    flexDirection: "row",
    justifyContent: "space-around",
    backgroundColor: "transparent",
    borderTopWidth: 1,
    borderTopColor: "#07274D",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: -2 },
    shadowOpacity: 0.1,
    shadowRadius: 2,
    elevation: 5,
  },
  navBarItem: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  icon: {
    height: 24,
    width: 24,
  },
  middleIcon: {
    height: 55,
    width: 55,
  },
});

export default BottomNavBar;
