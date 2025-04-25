import axios from "axios";
import { BASE_URL } from "react-native-dotenv";

const httpClient = axios.create({
  baseURL: BASE_URL,
  headers: {
    "Content-Type": "application/json",
  },
});

// Interceptor de requisição
httpClient.interceptors.request.use(
  (config) => {
    console.log(`[REQUEST] ${config.method?.toUpperCase()} ${config.url}`);
    console.log("Headers:", config.headers);
    console.log("Body:", config.data);
    return config;
  },
  (error) => {
    console.error("[REQUEST ERROR]", error);
    return Promise.reject(error);
  }
);

// Interceptor de resposta
httpClient.interceptors.response.use(
  (response) => {
    console.log(`[RESPONSE] ${response.status} ${response.config.url}`);
    console.log("Data:", response.data);
    return response;
  },
  (error) => {
    console.error(
      `[RESPONSE ERROR]`,
      error.response?.status,
      error.response?.data
    );
    return Promise.reject(error);
  }
);

export default httpClient;
