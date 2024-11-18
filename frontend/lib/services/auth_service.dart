import 'dart:convert';
import 'package:http/http.dart' as http;

class AuthService {
  static const String _baseUrl = 'http://192.168.229.97:8080/auth';

  // Método para autenticar o usuário
  Future<String?> login(
      {required String email, required String password}) async {
    try {
      final response = await http.post(
        Uri.parse(_baseUrl),
        headers: {'Content-Type': 'application/json'},
        body: jsonEncode({
          'email': email,
          'password': password,
        }),
      );

      if (response.statusCode == 200) {
        final data = jsonDecode(response.body);
        return data['access_token'];
      } else {
        return null;
      }
    } catch (e) {
      print('Erro ao realizar login: $e');
      return null;
    }
  }
}
