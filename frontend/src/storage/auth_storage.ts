import AsyncStorage from "@react-native-async-storage/async-storage";
import { AuthSession } from "./types";

export class AuthStorage {
  private static sessionKey = "auth_session";

  // Salvar a sessão completa
  static async saveSession(session: AuthSession): Promise<void> {
    try {
      const jsonValue = JSON.stringify(session);
      await AsyncStorage.setItem(AuthStorage.sessionKey, jsonValue);
    } catch (error) {
      console.error("Erro ao salvar sessão:", error);
    }
  }

  // Recuperar a sessão
  static async getSession(): Promise<AuthSession | null> {
    try {
      const jsonValue = await AsyncStorage.getItem(AuthStorage.sessionKey);
      return jsonValue ? JSON.parse(jsonValue) : null;
    } catch (error) {
      console.error("Erro ao obter sessão:", error);
      return null;
    }
  }

  // Remover a sessão (logout)
  static async removeSession(): Promise<void> {
    try {
      await AsyncStorage.removeItem(AuthStorage.sessionKey);
    } catch (error) {
      console.error("Erro ao remover sessão:", error);
    }
  }

  // Recuperar o token
  static async getToken(): Promise<string | null> {
    const session = await AuthStorage.getSession();
    return session?.token ?? null;
  }
}
