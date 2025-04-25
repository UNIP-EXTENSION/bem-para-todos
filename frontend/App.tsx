import { SafeAreaView } from "react-native-safe-area-context";

import AppNavigator from "./src/navigation/app_navigation";

export default function App() {
  return (
    <SafeAreaView style={{ flex: 1, backgroundColor: '#FAB603' }}>
      <AppNavigator />
    </SafeAreaView>
  );
}
