import 'package:flutter/material.dart';
import 'package:frontend/components/buttons/button_auth.dart';
import 'package:frontend/components/buttons/button-google.dart';
import 'package:frontend/components/inputs/input-auth.dart';
import 'package:frontend/models/User/User.dart';
import 'package:frontend/pages/main_page.dart';
import 'package:frontend/services/auth_service.dart';

class LoginForm extends StatefulWidget {
  final Function onNavigateToAuthPage;

  const LoginForm({
    super.key,
    required this.onNavigateToAuthPage,
  });

  @override
  State<LoginForm> createState() => _LoginFormState();
}

class _LoginFormState extends State<LoginForm> {
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final AuthService _authService = AuthService();

  bool _isLoading = false;
  String? _errorMessage;

  Map<String, dynamic> mapController() {
    Map<String, String> map = {
      "email": _emailController.text,
      "password": _passwordController.text,
    };
    return map;
  }

  Map<String, dynamic> mapUser() {
    User user = User.login(
        email: mapController()["email"], password: mapController()["password"]);
    return user.map();
  }

  Future<void> _submitForm() async {
    setState(() {
      _isLoading = true;
      _errorMessage = null;
    });

    final email = _emailController.text;
    final password = _passwordController.text;

    final success = await _authService.login(email: email, password: password);

    setState(() {
      _isLoading = false;
    });

    if (success) {
      Navigator.pushReplacement(
        context,
        MaterialPageRoute(builder: (context) => const MainScreen()),
      );
    } else {
      setState(() {
        _errorMessage = 'Falha no login. Verifique suas credenciais.';
      });
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        // Campo de email
        InputAuth(
          labelText: 'E-mail',
          hintText: 'Digite seu e-mail',
          controller: _emailController,
          value: _emailController.text,
          imagePath: 'lib/assets/images/inputs/email.png',
        ),
        // Campo de senha
        InputAuth(
          labelText: 'Senha',
          hintText: 'Digite sua senha',
          obscureText: true,
          controller: _passwordController,
          value: _passwordController.text,
          imagePath: 'lib/assets/images/inputs/key.png',
          marginBottom: 6,
        ),
        // Link de Esqueceu a senha
        Align(
          alignment: Alignment.centerLeft,
          child: TextButton(
            onPressed: () {},
            child: const Text(
              'Esqueceu a senha?',
              style: TextStyle(color: Colors.white),
            ),
          ),
        ),
        const SizedBox(height: 6),
        // Botão de Entrar
        _isLoading
            ? const CircularProgressIndicator()
            : ButtonAuth(
                text: 'Entrar',
                onPressed: _submitForm,
              ),
        const SizedBox(height: 16),
        // Botão do Google
        const ButtonGoogle(
          text: 'Entrar com o Google',
        ),
        const SizedBox(height: 80),
        SizedBox(
          width: double.infinity,
          height: 45,
          child: TextButton(
            onPressed: () => widget.onNavigateToAuthPage(),
            style: TextButton.styleFrom(
              backgroundColor: const Color(0xFFFAB603),
              foregroundColor: Colors.white,
              padding: const EdgeInsets.symmetric(vertical: 12),
              shape: RoundedRectangleBorder(
                borderRadius: BorderRadius.circular(8),
              ),
            ),
            child: const Text(
              'Primeiro acesso?',
              textAlign: TextAlign.center,
              style: TextStyle(
                fontWeight: FontWeight.bold,
                fontSize: 16,
              ),
            ),
          ),
        ),
        if (_errorMessage != null) ...[
          const SizedBox(height: 10),
          Text(
            _errorMessage!,
            style: const TextStyle(color: Colors.red),
          ),
        ],
      ],
    );
  }
}
