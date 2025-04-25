import React, { useState } from "react";
import { View, StyleSheet, ScrollView, Image, Text } from "react-native";
import EditProfileForm from "../../../components/forms/EditProfileForm";
import BottomNavBar from "../../../components/bars/bottom_nav_bar";
import { useNavigation } from "@react-navigation/native";
import { NativeStackNavigationProp } from "@react-navigation/native-stack";

type RootStackParamList = {
  Main: {
    screen: "Home" | "Events" | "Profile";
  };
};

type TabScreen = "Home" | "Events" | "Profile";

type NavigationProp = NativeStackNavigationProp<RootStackParamList>;

interface EditProfilePageProps {
  onGoBack?: () => void;
}

const EditProfileView: React.FC<EditProfilePageProps> = ({ onGoBack }) => {
  const navigation = useNavigation<NavigationProp>();
  const [currentIndex, setCurrentIndex] = useState(2);

  const tabs: TabScreen[] = ["Home", "Events", "Profile"];

  const handleTabPress = (index: number) => {
    setCurrentIndex(index);
    navigation.navigate("Main", { screen: tabs[index] as TabScreen });
  };

  return (
    <View style={{ flex: 1 }}>
      <View style={styles.container}>
        <ScrollView contentContainerStyle={styles.scrollContainer}>
          <EditProfileForm />
        </ScrollView>
      </View>
      <BottomNavBar currentIndex={currentIndex} onTabPress={handleTabPress} />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: "100%",
    backgroundColor: "#FAB603",
    padding: 28,
  },
  scrollContainer: {
    width: "100%",
    justifyContent: "center",
    alignItems: "center",
  },
});

export default EditProfileView;
