import 'package:flutter/material.dart';

class InputAuth extends StatefulWidget {
  final String labelText;
  final String hintText;
  final bool obscureText;
  final TextEditingController controller;
  final String value;
  const InputAuth(
      {super.key,
      required this.labelText,
      required this.hintText,
      this.obscureText = false,
      required this.controller,
      required this.value});

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
    valueValidator;
  }
  @override
  Widget build(BuildContext context) {
    return Container(
      margin: const EdgeInsets.only(bottom: 22.0),
      decoration: BoxDecoration(
        color: const Color(0xFFFFFFFF),
        borderRadius: BorderRadius.circular(8.0),
        border: Border.all(
          color: const Color(0xFFFFE500),
          width: 2.0,
        ),
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
      height: 45,
      child: TextFormField(
        validator: valueValidator,
        obscureText: widget.obscureText,
        controller: widget.controller,
        decoration: InputDecoration(
            hintText: widget.hintText,
            border: InputBorder.none,
            contentPadding:
                const EdgeInsets.symmetric(horizontal: 18.0, vertical: 13.0),
            hintStyle: const TextStyle(
                fontSize: 14,
                fontWeight: FontWeight.bold,
                color: Colors.black45)),
      ),
    );
  }
}
