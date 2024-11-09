// ignore_for_file: file_names

import 'package:flutter/material.dart';
import 'package:frontend/components/forms/auth-form.dart';

class AuthPage extends StatelessWidget {
  const AuthPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Theme.of(context).primaryColor,
      body: Padding(
        padding: const EdgeInsets.all(45.0),
        child: Center(
          child: SingleChildScrollView(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Image.asset(
                  'lib/assets/images/logo-app.png',
                ),
                const SizedBox(height: 18),
                const Text(
                  'Cadastro',
                  style: TextStyle(
                    color: Colors.white,
                    fontSize: 30,
                    fontWeight: FontWeight.w900,
                  ),
                ),
                const SizedBox(height: 14),
                const AuthForm(),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
