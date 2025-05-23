import React from "react";
import {
  View,
  Text,
  FlatList,
  StyleSheet,
  TouchableOpacity,
} from "react-native";
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
      <View style={styles.noEvents}>
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
    <View style={styles.wrapper}>
      <TouchableOpacity
        style={styles.headerButton}
        onPress={() => console.log("Cadastrar evento pressionado")}
      >
        <Text style={styles.headerButtonText}>+ Cadastrar novo evento</Text>
      </TouchableOpacity>

      <FlatList
        data={events}
        keyExtractor={(item, index) => index.toString()}
        renderItem={renderItem}
        contentContainerStyle={styles.container}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  wrapper: {
    flex: 1,
  },
  headerButton: {
    paddingTop: 30,
    paddingBottom: 30,
    alignItems: "center",
    justifyContent: "center",
  },
  headerButtonText: {
    fontSize: 20,
    color: "#00000040",
    fontWeight: "bold",
  },
  container: {
    paddingRight: 20,
    paddingLeft: 20,
  },
  noEvents: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
  },
  noEventsText: {
    fontSize: 18,
    color: "grey",
  },
});

export default EventList;
