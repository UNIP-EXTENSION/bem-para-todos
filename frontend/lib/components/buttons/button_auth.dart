import 'package:flutter/material.dart';

class ButtonAuth extends StatelessWidget {
  final String text;
  final VoidCallback onPressed;
  final Color? color;
  final double fontSize;
  final bool isGradient;

  const ButtonAuth({
    super.key,
    required this.text,
    required this.onPressed,
    this.color,
    this.fontSize = 20.0,
    this.isGradient = true,
  });

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      height: 45,
      width: double.infinity,
      child: Container(
        decoration: BoxDecoration(
          gradient: isGradient
              ? const LinearGradient(
                  colors: [Color(0xFF0673F9), Color(0xFF044493)],
                )
              : null,
          color: !isGradient ? color : null,
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
            textStyle: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: fontSize,
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
