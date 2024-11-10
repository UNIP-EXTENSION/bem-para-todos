import 'package:flutter/material.dart';

// ignore: must_be_immutable
class InputAuth extends StatefulWidget {
  final String labelText;
  String? hintText;
  final bool obscureText;
  final TextEditingController controller;
  final String value;
  final double marginBottom;
  final String? imagePath;

  InputAuth({
    super.key,
    required this.labelText,
    this.hintText,
    this.obscureText = false,
    required this.controller,
    required this.value,
    this.marginBottom = 22.0,
    this.imagePath,
  });

  @override
  State<InputAuth> createState() => _InputAuthState();
}

class _InputAuthState extends State<InputAuth> {
  String? valueValidator(String? value) {
    if (value == null || value.isEmpty) {
      return 'Insira todos os campos obrigatórios';
    }
    return null;
  }

  @override
  void initState() {
    super.initState();
    widget.controller.text = widget.value;
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      margin: EdgeInsets.only(bottom: widget.marginBottom),
      decoration: BoxDecoration(
        color: const Color(0xFFFFFFFF),
        borderRadius: BorderRadius.circular(8.0),
        border: Border.all(
          color: const Color(0xFFFFE500),
          width: 2.0,
        ),
        boxShadow: [
          BoxShadow(
            color: const Color(0xFF6B6B6B).withOpacity(0.5),
            spreadRadius: 2,
            blurRadius: 2,
            offset: const Offset(0, 3),
          ),
        ],
      ),
      child: TextFormField(
        obscureText: widget.obscureText,
        controller: widget.controller,
        validator: valueValidator,
        decoration: InputDecoration(
          hintText: widget.hintText,
          border: InputBorder.none,
          contentPadding: const EdgeInsets.symmetric(
              horizontal: 20, vertical: 10), // Aumentando padding do conteúdo
          // Condicional para adicionar a imagem se existir
          prefixIcon: widget.imagePath != null
              ? Padding(
                  padding: const EdgeInsets.only(
                      left: 12.0,
                      right: 8.0), // Espaçamento entre a imagem e borda
                  child: Image.asset(
                    widget.imagePath!,
                    width: 10, // Tamanho da imagem
                    height: 10, // Tamanho da imagem
                    fit: BoxFit.contain,
                  ),
                )
              : null,
        ),
      ),
    );
  }
}
