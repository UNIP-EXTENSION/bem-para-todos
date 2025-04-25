import React from "react";
import { View, Text, Image, StyleSheet, TouchableOpacity } from "react-native";

interface AlbumCardProps {
  name: string;
  imageUrl: string;
  onPressed: () => void;
}

const AlbumCard: React.FC<AlbumCardProps> = ({ name, imageUrl, onPressed }) => {
  return (
    <TouchableOpacity onPress={onPressed} style={styles.container}>
      <View style={styles.card}>
        <Image
          source={{ uri: imageUrl }}
          style={styles.image}
          resizeMode="cover"
        />
        <View style={styles.textContainer}>
          <Text style={styles.text} numberOfLines={1}>
            {name}
          </Text>
        </View>
      </View>
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
  },
  card: {
    borderRadius: 10,
    overflow: "hidden",
    backgroundColor: "#fff",
    elevation: 3,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    width: "100%",
    height: "100%",
    position: "relative",
  },
  image: {
    width: "100%",
    height: "100%",
  },
  textContainer: {
    position: "absolute",
    bottom: 5,
    left: 5,
    right: 5,
    backgroundColor: "#065BF9",
    borderRadius: 25,
    paddingVertical: 2,
    paddingHorizontal: 10,
    alignItems: "center",
    justifyContent: "center",
  },
  text: {
    color: "white",
    fontSize: 14,
    fontWeight: "bold",
    textAlign: "center",
  },
});

export default AlbumCard;
