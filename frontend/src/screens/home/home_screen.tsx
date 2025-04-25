import React from "react";
import { View, Text, StyleSheet } from "react-native";
import TopBar from "../../components/bars/top_bar";
import EventList from "./views/EventList";
import GalleryView from "./views/GalleryView";

const HomeScreen = () => {
  const [index, setIndex] = React.useState(0);

  const events = [
    { name: "Evento 1", image: "https://via.placeholder.com/150" },
    { name: "Evento 2", image: "https://via.placeholder.com/150" },
    { name: "Evento 3", image: "https://via.placeholder.com/150" },
    { name: "Evento 4", image: "https://via.placeholder.com/150" },
  ];

  const gallery = [
    { name: "Álbum 1", image: "https://via.placeholder.com/150" },
    { name: "Álbum 2", image: "https://via.placeholder.com/150" },
    { name: "Álbum 3", image: "https://via.placeholder.com/150" },
    { name: "Álbum 4", image: "https://via.placeholder.com/150" },
  ];

  const routes = [
    { key: "first", title: "Eventos" },
    { key: "second", title: "Galeria" },
  ];

  const renderScene: Record<string, () => JSX.Element> = {
    first: () => <EventList events={events} />,
    second: () => <GalleryView events={gallery} />,
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
  },
});

export default HomeScreen;
