import { AuthStorage } from "../storage/auth_storage";
import { BASE_URL } from "react-native-dotenv";

export class AuthService {
  private baseUrl = `${BASE_URL}/auth`;

  // Método para autenticar o usuário
  async login(email: string, password: string): Promise<boolean> {
    try {
      const response = await fetch(this.baseUrl, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      if (response.status === 200) {
        const data = await response.json();
        const token = data.access_token;

        await AuthStorage.saveToken(token);
        return true;
      } else {
        console.warn(`Falha no login: ${response.status}`);
        return false;
      }
    } catch (error) {
      console.error("Erro ao realizar login:", error);
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
