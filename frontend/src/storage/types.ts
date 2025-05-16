export interface AuthSession {
  token: string;
  email: string;
  firstName: string;
  lastName?: string;
  uuid: string;
  expire_in: number
}
