import { AuthStorage } from "../storage/auth_storage";
import decodeBase64 from "../utils/decode";
import httpClient from "./http_client";

export class AuthService {
  private endpoint = "/auth";

  // Método para autenticar o usuário
  async login(email: string, password: string): Promise<boolean> {
    try {
      const { data } = await httpClient.post(this.endpoint, {
        email,
        password,
      });

      const token = data.access_token;
      const emailDecoded = decodeBase64(data.userInfo.email);
      const nameDecoded = decodeBase64(data.userInfo.name);
      const uuid = data.userInfo.uuid;

      await AuthStorage.saveSession({
        token,
        email: emailDecoded,
        firstName: nameDecoded,
        uuid,
      });

      return true;
    } catch (error: any) {
      const status = error.response?.status || "N/A";
      console.warn(`Falha no login: ${status}`);
      return false;
    }
  }

  // Verifica se está autenticado
  async isLoggedIn(): Promise<boolean> {
    const session = await AuthStorage.getSession();
    return !!session?.token;
  }

  // Logout
  async logout(): Promise<void> {
    await AuthStorage.removeSession();
  }
}
