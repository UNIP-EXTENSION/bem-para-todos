import { AuthStorage } from "../storage/auth_storage";
import httpClient from "./http_client";

export class AuthService {
  private endpoint = "/auth";

  // Método para autenticar o usuário
  async login(email: string, password: string): Promise<boolean> {
    try {
      const response = await httpClient.post(this.endpoint, {
        email,
        password,
      });

      const token = response.data.access_token;
      await AuthStorage.saveToken(token);
      return true;
    } catch (error: any) {
      const status = error.response?.status || "N/A";
      console.warn(`Falha no login: ${status}`);
      return false;
    }
  }

  // Verifica se está autenticado
  async isLoggedIn(): Promise<boolean> {
    const token = await AuthStorage.getToken();
    return token !== null;
  }

  // Logout
  async logout(): Promise<void> {
    await AuthStorage.removeToken();
  }
}
