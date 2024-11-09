import 'package:flutter/material.dart';
import 'package:frontend/components/buttons/button_auth.dart';
import 'package:frontend/components/buttons/button-google.dart';
import 'package:frontend/components/inputs/input-auth.dart';
import 'package:frontend/models/User/User.dart';
import 'package:frontend/pages/home_screen.dart';

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

  void _submitForm() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => HomeScreen()),
    );
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
        ),
        // Campo de senha
        InputAuth(
          labelText: 'Senha',
          hintText: 'Digite sua senha',
          obscureText: true,
          controller: _passwordController,
          value: _passwordController.text,
          marginBottom: 6,
        ),
        // Link de Esqueceu a senha
        Align(
          alignment: Alignment.centerLeft,
          child: TextButton(
            onPressed: () {
              print('Esqueceu a senha');
            },
            child: const Text(
              'Esqueceu a senha?',
              style: TextStyle(color: Colors.white),
            ),
          ),
        ),
        const SizedBox(height: 6),
        // Botão de Entrar
        ButtonAuth(
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
      ],
    );
  }
}
