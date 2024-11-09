import 'package:flutter/material.dart';
import 'package:frontend/components/login_form.dart';
import 'package:frontend/pages/auth-page.dart';

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  State<LoginPage> createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  void _navigateToAuthPage() {
    Navigator.push(
      context,
      MaterialPageRoute(builder: (context) => const AuthPage()),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Stack(
        children: [
          // Imagem de fundo
          Container(
            decoration: const BoxDecoration(
              image: DecorationImage(
                image: AssetImage('lib/assets/images/background-login.png'),
                fit: BoxFit.cover,
              ),
            ),
          ),
          // Conteúdo da tela de login
          Center(
            child: SingleChildScrollView(
              padding: const EdgeInsets.symmetric(horizontal: 45.0),
              child: Column(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Image.asset(
                    'lib/assets/images/logo-login.png',
                    height: 140,
                  ),
                  const SizedBox(height: 70),
                  // Formulário de Login
                  LoginForm(
                    onNavigateToAuthPage: _navigateToAuthPage,
                  ),
                ],
              ),
            ),
          ),
        ],
      ),
    );
  }
}
