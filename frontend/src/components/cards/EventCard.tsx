import React from "react";
import { View, Text, Image, StyleSheet, TouchableOpacity } from "react-native";

interface EventCardProps {
  name: string;
  imageUrl: string;
  onPress: () => void;
}

const EventCard: React.FC<EventCardProps> = ({ name, imageUrl, onPress }) => {
  return (
    <TouchableOpacity onPress={onPress} style={styles.card}>
      <View style={styles.cardContainer}>
        <View style={styles.header}>
          <Text style={styles.headerText}>{name}</Text>
        </View>

        <View style={styles.imageContainer}>
          <Image source={{ uri: imageUrl }} style={styles.image} />
        </View>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  card: {
    margin: 10,
  },
  cardContainer: {
    width: "100%",
    height: 280,
    borderRadius: 8,
    overflow: "hidden",
    backgroundColor: "#fff",
    elevation: 5, // sombra
  },
  header: {
    height: 50,
    backgroundColor: "black",
    justifyContent: "center",
    alignItems: "center",
  },
  headerText: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "bold",
  },
  imageContainer: {
    flex: 1,
    borderTopLeftRadius: 8,
    borderTopRightRadius: 8,
    overflow: "hidden",
  },
  image: {
    width: "100%",
    height: "100%",
    resizeMode: "cover",
  },
});

export default EventCard;
