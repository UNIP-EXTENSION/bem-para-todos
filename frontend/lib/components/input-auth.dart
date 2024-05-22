import 'package:flutter/material.dart';

// ignore: must_be_immutable
class InputAuth extends StatefulWidget {
  final String labelText;
  String? hintText;
  final bool obscureText;
  final TextEditingController controller;
  final String value;
  InputAuth(
      {super.key,
      required this.labelText,
      this.hintText,
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
      child: Column(
        children: [
          TextFormField(
            obscureText: widget.obscureText,
            controller: widget.controller,
            validator: valueValidator,
            decoration: InputDecoration(
              hintText: widget.hintText,
              border: InputBorder.none,
              contentPadding:
                  const EdgeInsets.symmetric(horizontal: 10, vertical: 10),
            ),
          ),
        ],
      ),
    );
  }
}
