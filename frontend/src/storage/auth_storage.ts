import AsyncStorage from "@react-native-async-storage/async-storage";

export class AuthStorage {
  private static tokenKey = "auth_token";

  // Salvar o token JWT
  static async saveToken(token: string): Promise<void> {
    try {
      await AsyncStorage.setItem(AuthStorage.tokenKey, token);
    } catch (error) {
      console.error("Erro ao salvar token:", error);
    }
  }

  // Recuperar o token JWT
  static async getToken(): Promise<string | null> {
    try {
      return await AsyncStorage.getItem(AuthStorage.tokenKey);
    } catch (error) {
      console.error("Erro ao obter token:", error);
      return null;
    }
  }

  // Remover o token JWT (logout)
  static async removeToken(): Promise<void> {
    try {
      await AsyncStorage.removeItem(AuthStorage.tokenKey);
    } catch (error) {
      console.error("Erro ao remover token:", error);
    }
  }
}
