import 'package:flutter/material.dart';

class AuthForm extends StatelessWidget {
  const AuthForm({super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      child: Form(
        child: Column(
          children: [
            TextFormField(
              decoration: const InputDecoration(labelText: 'Digite seu nome'),
            ),
            TextFormField(
              decoration: const InputDecoration(labelText: 'E-mail'),
            ),
            TextFormField(
              decoration:
                  const InputDecoration(labelText: 'Confirme seu E-mail'),
            ),
            TextFormField(
              decoration: const InputDecoration(labelText: 'Digite sua senha'),
            ),
            TextFormField(
              decoration:
                  const InputDecoration(labelText: 'Confirme sua senha'),
            ),
            const SizedBox(
              height: 12,
            ),
            ElevatedButton(onPressed: () {}, child: const Text('Cadastrar'))
          ],
        ),
      ),
    );
  }
}
