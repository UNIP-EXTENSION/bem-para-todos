import 'package:flutter/material.dart';
import 'package:frontend/components/bars/bottom_nav_bar.dart';
import 'package:frontend/screens/home/home_screen.dart';
import 'package:frontend/screens/myevents_screen.dart';
import 'package:frontend/screens/profile_screen.dart';

class MainScreen extends StatefulWidget {
  const MainScreen({super.key});

  @override
  _MainScreenState createState() => _MainScreenState();
}

class _MainScreenState extends State<MainScreen> {
  int _selectedIndex = 0;
  final PageController _pageController = PageController();

  final List<Widget> _pages = [
    const HomeScreen(),
    const MyEventsScreen(),
    const ProfileScreen()
  ];

  void _onBottomNavBarTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
    _pageController.jumpToPage(index);
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
