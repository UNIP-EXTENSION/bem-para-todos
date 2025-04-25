import React from "react";
import { createBottomTabNavigator } from "@react-navigation/bottom-tabs";
import BottomNavBar from "../components/bars/bottom_nav_bar";
import HomeScreen from "../screens/home/home_screen";
import MyEventsScreen from "../screens/myevents_screen";
import ProfileScreen from "../screens/profile/profile_screen";

const Tab = createBottomTabNavigator();

const MainPage = () => {
  return (
    <Tab.Navigator
      screenOptions={{
        headerShown: false,
      }}
      tabBar={({ state, navigation }) => (
        <BottomNavBar
          currentIndex={state.index}
          onTabPress={(index: number) => {
            navigation.navigate(state.routes[index].name);
          }}
        />
      )}
    >
      <Tab.Screen name="Home" component={HomeScreen} />
      <Tab.Screen name="Events" component={MyEventsScreen} />
      <Tab.Screen name="Profile" component={ProfileScreen} />
    </Tab.Navigator>
  );
};

export default MainPage;
