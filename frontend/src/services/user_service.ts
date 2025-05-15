import { AuthStorage } from "../storage/auth_storage";
import httpClient from "./http_client";

export class UserService {
  private endpoint = "/users";

  // Atualizar dados do usuário
  async updateUser(
    id: string,
    updateData: { firstName?: string; lastName?: string; email?: string }
  ): Promise<boolean> {
    try {
      const response = await httpClient.patch(
        `${this.endpoint}/update/${id}`,
        updateData
      );
      console.log("Usuário atualizado:", response.data);
      return true;
    } catch (error) {
      console.error("Erro ao atualizar usuário:", error);
      return false;
    }
  }

  // Buscar dados do usuário
  async getUserData(id: string): Promise<any> {
    try {
      const { data } = await httpClient.get(`${this.endpoint}/find/${id}`);

      return data;
    } catch (error) {
      console.error("Erro ao buscar os dados do usuário:", error);

      return null;
    }
  }

  // Recuperar dados do usuário logado
  async getUserInfo() {
    const session = await AuthStorage.getSession();
    return session
      ? { email: session.email, name: session.name, uuid: session.uuid }
      : null;
  }
}
