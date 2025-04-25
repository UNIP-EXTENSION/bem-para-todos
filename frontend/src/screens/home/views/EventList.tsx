import React from "react";
import { View, Text, FlatList, StyleSheet } from "react-native";
import EventCard from "../../../components/cards/EventCard";

interface Event {
  name: string;
  image: string;
}

interface EventListProps {
  events: Event[];
}

const EventList: React.FC<EventListProps> = ({ events }) => {
  if (events.length === 0) {
    return (
      <View style={styles.center}>
        <Text style={styles.noEventsText}>Não há eventos disponíveis.</Text>
      </View>
    );
  }

  const renderItem = ({ item }: { item: Event }) => (
      <EventCard
        name={item.name}
        imageUrl={item.image}
        onPress={() => {
          console.log(`Clicou no ${item.name}`);
        }}
      />
  );

  return (
    <FlatList
      data={events}
      keyExtractor={(item, index) => index.toString()}
      renderItem={renderItem}
    />
  );
};

const styles = StyleSheet.create({
  itemContainer: {
    flex: 1,
    margin: 5,
    aspectRatio: 1,
  },
  center: {
    // flex: 1,
    // justifyContent: "center",
    // alignItems: "center",
  },
  noEventsText: {
    fontSize: 18,
    color: "grey",
  },
});

export default EventList;
