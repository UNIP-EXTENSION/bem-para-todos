import React from "react";
import { FlatList, Text, StyleSheet, View } from "react-native";
import AlbumCard from "../../../components/cards/AlbumCard";

interface Event {
  name: string;
  image: string;
}

interface GalleryViewProps {
  events: Event[];
}

const GalleryView: React.FC<GalleryViewProps> = ({ events }) => {
  if (events.length === 0) {
    return (
      <View style={styles.center}>
        <Text style={styles.noEventsText}>Não há albuns disponíveis.</Text>
      </View>
    );
  }

  const renderItem = ({ item }: { item: Event }) => (
    <View style={styles.itemContainer}>
      <AlbumCard
        name={item.name}
        imageUrl={item.image}
        onPressed={() => {
            console.log(`Clicou no ${item.name}`);
          }}
      />
    </View>
  );

  return (
    <FlatList
      data={events}
      renderItem={renderItem}
      keyExtractor={(item, index) => index.toString()}
      numColumns={2}
      contentContainerStyle={styles.container}
    />
  );
};

const styles = StyleSheet.create({
  container: {
    padding: 8,
  },
  itemContainer: {
    flex: 1,
    margin: 5,
    aspectRatio: 1,
  },
  center: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  noEventsText: {
    fontSize: 18,
    color: "grey",
  },
});

export default GalleryView;
