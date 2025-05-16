import { NativeStackNavigationProp } from "@react-navigation/native-stack";

export type RootStackParamList = {
  Login: undefined;
  Register: undefined;
  Main: undefined;
  EditProfile: undefined;
};

export type NavigationProp = NativeStackNavigationProp<RootStackParamList>;

export type MainRootStackParamList = {
  Main: {
    screen: "Home" | "Events" | "Profile";
  };
};

export type NavigationMainProp =
  NativeStackNavigationProp<MainRootStackParamList>;
