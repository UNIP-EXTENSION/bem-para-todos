import React from "react";
import { View, Text, Image, StyleSheet, TouchableOpacity } from "react-native";
import { LinearGradient } from "expo-linear-gradient";


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
        <LinearGradient
          colors={["#000000", "#616161"]}
          start={{ x: 0, y: 0.5 }}
          end={{ x: 1, y: 0.5 }}
          style={styles.gradientBackground}
        >
          <Text style={styles.headerText}>{name}</Text>
        </LinearGradient>
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
    marginBottom: 10,
  },
  cardContainer: {
    width: "100%",
    height: 280,
    overflow: "hidden",
    backgroundColor: "#fff",
    elevation: 5,
  },
  header: {
    height: 50,
    justifyContent: "center",
    alignItems: "center",
  },
  headerText: {
    color: "#fff",
    fontSize: 16,
    fontWeight: "bold",
  },
  gradientBackground: {
    flex: 1,
    width: "100%",
    justifyContent: "center",
    alignItems: "center",
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
