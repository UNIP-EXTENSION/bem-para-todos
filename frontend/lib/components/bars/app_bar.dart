import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  final TabController tabController;

  const CustomAppBar({super.key, required this.tabController});

  @override
  Widget build(BuildContext context) {
    return AppBar(
      backgroundColor: const Color(0xFFFAB603),
      bottom: PreferredSize(
        preferredSize: const Size.fromHeight(100),
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Image.asset(
                'lib/assets/images/logo-app.png',
                height: 50, // Altura da imagem
              ),
            ),
            TabBar(
              controller: tabController,
              tabs: const [
                Tab(text: 'Eventos'),
                Tab(text: 'Galeria'),
              ],
            ),
          ],
        ),
      ),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight + 100);
}
