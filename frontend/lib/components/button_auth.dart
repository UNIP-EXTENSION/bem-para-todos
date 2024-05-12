import 'package:flutter/material.dart';

class ButtonAuth extends StatelessWidget {
  final String text;
  final VoidCallback onPressed;

  const ButtonAuth({
    super.key,
    required this.text,
    required this.onPressed,
  });

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
          onPressed: onPressed,
          style: ElevatedButton.styleFrom(
            foregroundColor: Colors.white,
            backgroundColor: Colors.transparent,
            textStyle: const TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 20,
            ),
            shape: RoundedRectangleBorder(
              borderRadius: BorderRadius.circular(8),
            ),
          ),
          child: Text(text),
        ),
      ),
    );
  }
}
