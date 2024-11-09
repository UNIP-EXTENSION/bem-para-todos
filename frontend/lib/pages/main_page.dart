import 'package:flutter/material.dart';
import 'package:frontend/components/bars/bottom_nav_bar.dart';
import 'package:frontend/screens/home_screen.dart';
import 'package:frontend/screens/myevents_screen.dart'; // Importe seu bottom nav bar

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  _MainScreenState createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _selectedIndex = 0;
  final PageController _pageController = PageController();

  final List<Widget> _pages = [
    HomeScreen(), // Página Home
    MyEventsScreen(), // Página Meus Eventos
    // Outras páginas podem ser adicionadas aqui
  ];

  void _onBottomNavBarTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
    _pageController.jumpToPage(index); // Alterna entre as páginas principais
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: PageView(
        controller: _pageController,
        onPageChanged: (index) {
          setState(() {
            _selectedIndex = index;
          });
        },
        children: _pages, // Navegação entre as telas principais
      ),
      bottomNavigationBar: CustomBottomNavBar(
        currentIndex: _selectedIndex,
        onTap: _onBottomNavBarTapped, // Alterna entre as páginas principais
      ),
    );
  }
}
