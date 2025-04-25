export class User {
  firstName: string;
  lastName: string;
  email: string;
  password: string;

  constructor({
    firstName,
    lastName,
    email,
    password,
  }: {
    firstName: string;
    lastName: string;
    email: string;
    password: string;
  }) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }

  static login({ email, password }: { email: string; password: string }): User {
    return new User({
      firstName: "",
      lastName: "",
      email,
      password,
    });
  }

  static fromMap(map: Record<string, string>): User {
    return new User({
      firstName: map.firstName,
      lastName: map.lastName,
      email: map.email,
      password: map.password,
    });
  }

  toMap(): Record<string, string> {
    return {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      password: this.password,
    };
  }
}
