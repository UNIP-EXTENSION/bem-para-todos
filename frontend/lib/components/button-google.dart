import 'package:flutter/material.dart';

class ButtonGoogle extends StatelessWidget {
  final String text;
  // final VoidCallback onPressed;

  const ButtonGoogle({
    super.key,
    required this.text,
    // required this.onPressed,
  });

  void _loginWithGoogle() async {
    print('Logar com o google');
  }

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 45,
      width: double.infinity,
      child: Container(
        decoration: BoxDecoration(
          gradient: const LinearGradient(
            colors: [Color(0xFF0673F9), Color(0xFF044493)],
          ),
          borderRadius: BorderRadius.circular(8),
          boxShadow: [
            BoxShadow(
              color: const Color(0xFF6B6B6B).withOpacity(0.5), // Cor da sombra
              spreadRadius: 2, // Alargamento da sombra
              blurRadius: 2, // Desfoque da sombra
              offset:
                  const Offset(0, 3), // Offset da sombra (horizontal, vertical)
            ),
          ],
        ),
        child: ElevatedButton(
          onPressed: _loginWithGoogle,
          style: ElevatedButton.styleFrom(
            foregroundColor: Colors.black,
            backgroundColor: Colors.white,
            textStyle: const TextStyle(
              fontWeight: FontWeight.w400,
              fontSize: 15,
            ),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
          ),
          child: Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Image.asset(
                'lib/assets/images/logo-google.png',
              ),
              const SizedBox(width: 8), // Espaçamento entre a imagem e o texto
              Text(
                text,
                style: const TextStyle(
                  fontWeight: FontWeight.w400,
                  fontSize: 15,
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
