import 'package:flutter/material.dart';
import 'package:frontend/components/button_auth.dart';
import 'package:frontend/components/input-auth.dart';

class AuthForm extends StatefulWidget {
  // ignore: use_key_in_widget_constructors
  const AuthForm({Key? key});

  @override
  State<AuthForm> createState() => _AuthFormState();
}

class _AuthFormState extends State<AuthForm> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _confirmEmailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _confirmPasswordController =
      TextEditingController();

  void _submit() {
    final String name = _nameController.text;
    final String email = _emailController.text;
    final String confirmEmail = _confirmEmailController.text;
    final String password = _passwordController.text;
    final String confirmPassword = _confirmPasswordController.text;

    print('Nome: $name');
    print('E-mail: $email');
    print('Confirme o E-mail: $confirmEmail');
    print('Senha: $password');
    print('Confirme a Senha: $confirmPassword');
  }

  @override
  void dispose() {
    _nameController.dispose();
    _emailController.dispose();
    _confirmEmailController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.transparent,
      elevation: 0,
      child: Form(
        child: Column(
          children: [
            InputAuth(
              labelText: 'Digite seu nome',
              hintText: 'Seu nome',
              controller: _nameController,
            ),
            InputAuth(
              labelText: 'E-mail',
              hintText: 'Seu e-mail',
              controller: _emailController,
            ),
            InputAuth(
              labelText: 'Confirme seu E-mail',
              hintText: 'Confirme seu e-mail',
              controller: _confirmEmailController,
            ),
            InputAuth(
              labelText: 'Digite sua senha',
              hintText: 'Sua senha',
              obscureText: true,
              controller: _passwordController,
            ),
            InputAuth(
              labelText: 'Confirme sua senha',
              hintText: 'Confirme sua senha',
              obscureText: true,
              controller: _confirmPasswordController,
            ),
            const SizedBox(height: 16),
            ButtonAuth(
              text: 'Cadastrar',
              onPressed: _submit,
            ),
          ],
        ),
      ),
    );
  }
}
