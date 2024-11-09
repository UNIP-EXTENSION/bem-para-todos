// ignore_for_file: file_names

class User {
  final String fisrtName;
  final String lastName;
  final String email;
  final String password;

  User(
      {required this.fisrtName,
      required this.lastName,
      required this.email,
      required this.password});

  User.login({required this.email, required this.password})
      : fisrtName = '',
        lastName = '';

  //Converte os dados em um json
  User.fromMap(Map<String, String> map)
      : fisrtName = map['firstName']!,
        lastName = map['lastName']!,
        email = map['email']!,
        password = map['password']!;

  //Converte os dados em um map
  Map<String, String> map() {
    return {
      "firstName": fisrtName,
      "lastName": lastName,
      "email": email,
      "password": password
    };
  }
}
