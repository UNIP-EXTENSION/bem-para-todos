import 'package:flutter/material.dart';

class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  final TabController tabController;
  final List<Tab> tabs;

  const CustomAppBar({
    super.key,
    required this.tabController,
    required this.tabs,
  });

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
                height: 50,
              ),
            ),
            TabBar(
              controller: tabController,
              tabs: tabs,
              labelColor: Colors.white,
              unselectedLabelColor: Colors.white,
              indicatorColor: const Color(0xFF6B6B6B),
              indicatorSize: TabBarIndicatorSize.label,
              indicatorWeight: 4.0,
              indicatorPadding: EdgeInsets.zero,
              labelStyle: const TextStyle(
                fontSize: 20.0,
                fontWeight: FontWeight.bold,
              ),
            ),
          ],
        ),
      ),
    );
  }

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight + 100);
}
