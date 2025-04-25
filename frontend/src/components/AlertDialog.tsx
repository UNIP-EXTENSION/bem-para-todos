import React, { useEffect } from "react";
import {
  Modal,
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  Dimensions,
} from "react-native";

type AlertDialogProps = {
  visible: boolean;
  onClose: () => void;
  textBody: string;
  dialogIcon: React.ReactNode;
  textConfirmationButton?: string;
  primaryButton?: boolean;
  secondButton?: boolean;
  width?: number;
};

const AlertDialog: React.FC<AlertDialogProps> = ({
  visible,
  onClose,
  textBody,
  dialogIcon,
  textConfirmationButton = "Confirmar",
  primaryButton = true,
  secondButton = false,
  width = 120,
}) => {
  useEffect(() => {
    if (visible) {
      const timer = setTimeout(() => {
        onClose();
      }, 2000);
      return () => clearTimeout(timer);
    }
  }, [visible]);

  return (
    <Modal transparent visible={visible} animationType="fade">
      <View style={styles.backdrop}>
        <View style={styles.modal}>
          <View style={styles.icon}>{dialogIcon}</View>
          <Text style={styles.text}>{textBody}</Text>
          <View style={styles.actions}>
            {primaryButton && (
              <TouchableOpacity
                style={[styles.button, { width }]}
                onPress={onClose}
              >
                <Text style={styles.buttonText}>{textConfirmationButton}</Text>
              </TouchableOpacity>
            )}
            {secondButton && (
              <TouchableOpacity
                style={[styles.button, { width }]}
                onPress={onClose}
              >
                <Text style={styles.buttonText}>Voltar</Text>
              </TouchableOpacity>
            )}
          </View>
        </View>
      </View>
    </Modal>
  );
};

export default AlertDialog;

const styles = StyleSheet.create({
  backdrop: {
    flex: 1,
    backgroundColor: "rgba(0,0,0,0.3)",
    justifyContent: "center",
    alignItems: "center",
  },
  modal: {
    backgroundColor: "#fff",
    borderRadius: 10,
    padding: 24,
    width: Dimensions.get("window").width * 0.8,
    alignItems: "center",
  },
  icon: {
    marginBottom: 16,
  },
  text: {
    fontSize: 18,
    textAlign: "center",
    marginBottom: 20,
  },
  actions: {
    flexDirection: "row",
    justifyContent: "center",
    gap: 10,
  },
  button: {
    backgroundColor: "#6200ee",
    padding: 10,
    borderRadius: 8,
    marginHorizontal: 5,
  },
  buttonText: {
    color: "#fff",
    fontWeight: "bold",
    textAlign: "center",
  },
});
