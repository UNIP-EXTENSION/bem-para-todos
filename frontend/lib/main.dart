import 'package:flutter/material.dart';
import 'package:frontend/pages/auth-page.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "Bem para todos",
      theme: ThemeData(
        primaryColor: const Color(0xFFFAB603),
      ),
      home: const AuthPage(),
      debugShowCheckedModeBanner: false,
    );
  }
}