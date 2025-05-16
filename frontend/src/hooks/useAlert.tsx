import { useState } from "react";
import { ActivityIndicator } from "react-native";
import { Ionicons } from "@expo/vector-icons";
import React from "react";

type AlertStatus = "idle" | "loading" | "success" | "error";

export const useAlert = () => {
  const [alertState, setAlertState] = useState<{
    visible: boolean;
    text: string;
    loading: boolean;
    icon: React.ReactNode;
  }>({
    visible: false,
    text: "",
    loading: false,
    icon: null,
  });

  const updateAlertState = (status: AlertStatus, text = "") => {
    switch (status) {
      case "loading":
        setAlertState({
          visible: true,
          text: text || "Salvando alterações...",
          loading: true,
          icon: <ActivityIndicator size="large" color="#6200ee" />,
        });
        break;
      case "success":
        setAlertState({
          visible: true,
          text: text || "Perfil atualizado com sucesso!",
          loading: false,
          icon: <Ionicons name="checkmark-circle" size={48} color="green" />,
        });
        break;
      case "error":
        setAlertState({
          visible: true,
          text: text || "Erro ao atualizar dados.",
          loading: false,
          icon: <Ionicons name="alert-circle" size={48} color="red" />,
        });
        break;
      case "idle":
      default:
        setAlertState({
          visible: false,
          text: "",
          loading: false,
          icon: null,
        });
    }
  };

  return { alertState, updateAlertState };
};
