import React from "react";
import { View, Text, StyleSheet } from "react-native";
import TopBar from "../components/bars/top_bar";

const MyEventsScreen = () => {
  const [index, setIndex] = React.useState(0);

  const routes = [{ key: "first", title: "Meus Ingressos" }];

  const renderScene: Record<string, () => JSX.Element> = {
    first: () => (
      <View style={styles.screen}>
        <Text>Eventos Screen</Text>
      </View>
    ),
  };

  return (
    <View style={{ flex: 1 }}>
      <TopBar tabController={{ index, routes }} onIndexChange={setIndex} />

      <View style={styles.screen}>{renderScene[routes[index].key]()}</View>
    </View>
  );
};

const styles = StyleSheet.create({
  screen: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
});

export default MyEventsScreen;
