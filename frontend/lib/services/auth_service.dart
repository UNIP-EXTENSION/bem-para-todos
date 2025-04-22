import 'dart:convert';
import 'package:frontend/components/storages/auth_storage.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:http/http.dart' as http;

class AuthService {
  final String _baseUrl = '${dotenv.env['BASE_URL']}/auth';
  final AuthStorage _authStorage = AuthStorage();

  // Método para autenticar o usuário
  Future<bool> login({required String email, required String password}) async {
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
        String token = data['access_token'];

        // Salvar o token para uso futuro
        await _authStorage.saveToken(token);
        return true;
      } else {
        print('Falha no login: ${response.statusCode}');
        return false;
      }
    } catch (e) {
      print('Erro ao realizar login: $e');
      return false;
    }
  }

  // Método para verificar se o usuário está autenticado
  Future<bool> isLoggedIn() async {
    final token = await _authStorage.getToken();
    return token != null;
  }

  // Método para logout
  Future<void> logout() async {
    await _authStorage.removeToken();
  }
}
