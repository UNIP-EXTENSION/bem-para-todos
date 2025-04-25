import React from "react";
import { NavigationContainer } from "@react-navigation/native";
import { createNativeStackNavigator } from "@react-navigation/native-stack";

import LoginPage from "../pages/login_page";
import AuthPage from "../pages/auth_page";
import MainPage from "../pages/main_page";
import EditProfileView from "../screens/profile/views/edit_profile_view";
import SplashScreen from "../pages/splash_page";

const Stack = createNativeStackNavigator();

const AppNavigator = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="SplashScreen"
        screenOptions={{ headerShown: false }}
      >
        <Stack.Screen name="SplashScreen" component={SplashScreen} />
        <Stack.Screen name="Login" component={LoginPage} />
        <Stack.Screen name="Register" component={AuthPage} />
        <Stack.Screen name="Main" component={MainPage} />
        <Stack.Screen name="EditProfile" component={EditProfileView} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default AppNavigator;
