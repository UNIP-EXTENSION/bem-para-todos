import 'package:flutter/material.dart';
import 'package:frontend/components/bars/app_bar.dart';
import 'package:frontend/components/buttons/button_auth.dart';
import 'package:frontend/helpers/situation-alerts.dart';
import 'package:frontend/pages/login_page.dart';
import 'package:frontend/screens/profile/views/edit_profile_page.dart';
import 'package:frontend/services/auth_service.dart';

class ProfileScreen extends StatefulWidget {
  const ProfileScreen({super.key});

  @override
  _ProfileScreen createState() => _ProfileScreen();
}

class _ProfileScreen extends State<ProfileScreen>
    with SingleTickerProviderStateMixin {
  final AuthService _authService = AuthService();

  late TabController _tabController;

  @override
  void initState() {
    super.initState();
    _tabController = TabController(length: 1, vsync: this);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const CustomAppBar(),
      body: Container(
        color: const Color(0xFFFAB603), // Cor de fundo
        child: Column(
          children: [
            Container(
              padding: const EdgeInsets.symmetric(vertical: 20, horizontal: 28),
              color: const Color(0xFFFAB603),
              child: const Row(
                children: [
                  CircleAvatar(
                    radius: 40,
                    backgroundImage:
                        AssetImage('lib/assets/images/bars/profile.png'),
                  ),
                  SizedBox(width: 16),
                  Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      Text(
                        'Nome do Usuário',
                        style: TextStyle(
                          fontSize: 20,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                      Text(
                        'email@dominio.com',
                        style: TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.bold,
                          color: Colors.white,
                        ),
                      ),
                    ],
                  ),
                ],
              ),
            ),
            const SizedBox(height: 25), // Espaçamento

            // Botões
            Padding(
              padding: const EdgeInsets.symmetric(horizontal: 16),
              child: Column(
                children: [
                  ButtonAuth(
                    text: 'Meus Ingressos',
                    fontSize: 16.0,
                    onPressed: () {
                      print('Meus Ingressos');
                    },
                    isGradient: false,
                    color: const Color(0xFF0675F9),
                  ),
                  const SizedBox(height: 18),
                  ButtonAuth(
                    text: 'Editar Dados',
                    fontSize: 16.0,
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => const EditProfilePage()),
                      );
                    },
                    isGradient: false,
                    color: const Color(0xFF0675F9),
                  ),
                  const SizedBox(height: 18),
                  ButtonAuth(
                    text: 'Sair',
                    fontSize: 16.0,
                    onPressed: () async {
                      _authService.logout();

                      await alertLoadingMessage(context, "Deslogando...");

                      if (context.mounted) {
                        Navigator.pushReplacement(
                          context,
                          MaterialPageRoute(
                              builder: (context) => const LoginPage()),
                        );
                      }
                    },
                    isGradient: false,
                    color: const Color(0xFFD35555),
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
