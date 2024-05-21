import 'package:flutter/material.dart';
import 'package:frontend/components/buttons/button_auth.dart';
import 'package:frontend/components/input-auth.dart';
import 'package:frontend/components/buttons/button-google.dart';
import 'package:frontend/helpers/situation-alerts.dart';

class AuthForm extends StatefulWidget {
  // ignore: use_key_in_widget_constructors
  const AuthForm({Key? key});

  @override
  State<AuthForm> createState() => _AuthFormState();
}

class _AuthFormState extends State<AuthForm> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _lastNameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _confirmEmailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _confirmPasswordController =
      TextEditingController();
      
  Map<String, dynamic> mapController() {
    Map<String, String> map = {
      "name": _nameController.text,
      "lastName": _lastNameController.text,
      "email": _emailController.text,
      "confirmEmail": _confirmEmailController.text,
      "password": _passwordController.text,
      "confirmPassword": _confirmPasswordController.text,
    };
    return map;
  }

  void _submit() {
    final String name = _nameController.text;
    final String lastName = _lastNameController.text;
    final String email = _emailController.text;
    final String confirmEmail = _confirmEmailController.text;
    final String password = _passwordController.text;
    final String confirmPassword = _confirmPasswordController.text;

    sucessAlert(context);
  }

  void _validFieldsRequireds() {}

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
              functionValidator: _validFieldsRequireds,
              labelText: 'Digite seu nome',
              hintText: 'Seu nome',
              controller: _nameController,
            ),
            InputAuth(
              functionValidator: _validFieldsRequireds,
              labelText: 'Digite seu sobrenome',
              hintText: 'Seu sobrenome',
              controller: _lastNameController,
            ),
            InputAuth(
              functionValidator: _validFieldsRequireds,
              labelText: 'E-mail',
              hintText: 'Seu e-mail',
              controller: _emailController,
            ),
            InputAuth(
              functionValidator: _validFieldsRequireds,
              labelText: 'Confirme seu E-mail',
              hintText: 'Confirme seu e-mail',
              controller: _confirmEmailController,
            ),
            InputAuth(
              functionValidator: _validFieldsRequireds,
              labelText: 'Digite sua senha',
              hintText: 'Sua senha',
              obscureText: true,
              controller: _passwordController,
            ),
            InputAuth(
              functionValidator: _validFieldsRequireds,
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
            const SizedBox(height: 11),
            const ButtonGoogle(
              text: 'Cadastrar com o Google',
            )
          ],
        ),
      ),
    );
  }
}
