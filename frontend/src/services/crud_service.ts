import httpClient from "./http_client";
import { AuthStorage } from "../storage/auth_storage";

export const CrudService = {
  async post(resource: string, data: Record<string, any>): Promise<boolean> {
    try {
      const response = await httpClient.post(`/${resource}`, data);
      return response.status === 201;
    } catch (err) {
      return false;
    }
  },

  async getAll(
    resource: string
  ): Promise<Array<{ name: string; image: string }>> {
    try {
      const token = await AuthStorage.getToken();
      const response = await httpClient.get(`/${resource}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      const content = response.data.content || [];
      return content.map((item: any) => ({
        name: item.name,
        image: item.files?.[0]?.urlFile || "",
      }));
    } catch (err) {
      return [];
    }
  },
};
