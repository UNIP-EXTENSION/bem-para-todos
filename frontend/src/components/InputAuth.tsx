import React from "react";
import { View, TextInput, Text, Image, StyleSheet } from "react-native";

type InputAuthProps = {
  labelText?: string;
  hintText?: string;
  obscureText?: boolean;
  value: string;
  onChangeText: (text: string) => void;
  marginBottom?: number;
  imagePath?: any;
  error?: string;
};

const InputAuth = React.forwardRef<TextInput, InputAuthProps>(
  (
    {
      hintText,
      obscureText = false,
      value,
      onChangeText,
      marginBottom = 0,
      imagePath,
      error,
    },
    ref
  ) => {
    return (
      <View style={[styles.container, { marginBottom }]}>
        <View style={styles.inputWrapper}>
          {imagePath && <Image source={imagePath} style={styles.prefixIcon} />}
          <TextInput
            ref={ref}
            style={styles.input}
            placeholder={hintText}
            secureTextEntry={obscureText}
            value={value}
            onChangeText={onChangeText}
            placeholderTextColor="#999"
          />
        </View>
        {error && <Text style={styles.errorText}>{error}</Text>}
      </View>
    );
  }
);

export default InputAuth;

const styles = StyleSheet.create({
  container: {},
  inputWrapper: {
    flexDirection: "row",
    alignItems: "center",
    backgroundColor: "#FFF",
    borderRadius: 8,
    borderColor: "#FFE500",
    borderWidth: 2,
    paddingHorizontal: 12,
    shadowColor: "#6B6B6B",
    shadowOffset: { width: 0, height: 3 },
    shadowOpacity: 0.3,
    shadowRadius: 2,
    elevation: 3,
  },
  prefixIcon: {
    width: 20,
    height: 20,
    marginRight: 10,
    resizeMode: "contain",
  },
  input: {
    flex: 1,
    fontSize: 16,
    paddingVertical: 10,
    color: "#000",
  },
  errorText: {
    color: "red",
    marginTop: 4,
    fontSize: 12,
  },
});
