import 'package:flutter/material.dart';

class CustomBottomNavBar extends StatelessWidget {
  final int currentIndex;
  final ValueChanged<int> onTap;

  const CustomBottomNavBar({
    super.key,
    required this.currentIndex,
    required this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 60,
      decoration: const BoxDecoration(
        gradient: LinearGradient(
          colors: [
            Color(0xFF07274D),
            Color(0xFF315988),
            Color(0xFF07274D),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
      ),
      child: BottomNavigationBar(
        currentIndex: currentIndex,
        onTap: onTap,
        type: BottomNavigationBarType.fixed,
        backgroundColor: Colors.transparent,
        selectedItemColor:
            const Color(0xFFFAB603), // Cor do item selecionado (amarelo)
        unselectedItemColor: Colors.white, // Cor dos itens não selecionados
        selectedFontSize: 0, // Esconde o texto de todos os itens
        unselectedFontSize: 0, // Esconde o texto de todos os itens
        items: [
          BottomNavigationBarItem(
            icon: ColorFiltered(
              colorFilter: ColorFilter.mode(
                currentIndex == 0 ? const Color(0xFFFAB603) : Colors.white,
                BlendMode.srcIn,
              ),
              child: Image.asset(
                'lib/assets/images/bars/home.png', // Substitua pelo caminho da sua imagem
                height: 24,
                width: 24,
              ),
            ),
            label: '', // Removido o label
          ),
          BottomNavigationBarItem(
            icon: ColorFiltered(
              colorFilter: ColorFilter.mode(
                currentIndex == 1 ? const Color(0xFFFAB603) : Colors.white,
                BlendMode.srcIn,
              ),
              child: Image.asset(
                'lib/assets/images/bars/search.png',
                height: 24,
                width: 24,
              ),
            ),
            label: '', // Removido o label
          ),
          BottomNavigationBarItem(
            icon: ColorFiltered(
              colorFilter: ColorFilter.mode(
                currentIndex == 2 ? const Color(0xFFFAB603) : Colors.white,
                BlendMode.srcIn,
              ),
              child: Image.asset(
                'lib/assets/images/bars/ticket-fill.png',
                height: 55,
                width: 55,
              ),
            ),
            label: '', // Removido o label
          ),
          BottomNavigationBarItem(
            icon: ColorFiltered(
              colorFilter: ColorFilter.mode(
                currentIndex == 3 ? const Color(0xFFFAB603) : Colors.white,
                BlendMode.srcIn,
              ),
              child: Image.asset(
                'lib/assets/images/bars/likes.png',
                height: 24,
                width: 24,
              ),
            ),
            label: '', // Removido o label
          ),
          BottomNavigationBarItem(
            icon: ColorFiltered(
              colorFilter: ColorFilter.mode(
                currentIndex == 4 ? const Color(0xFFFAB603) : Colors.white,
                BlendMode.srcIn,
              ),
              child: Image.asset(
                'lib/assets/images/bars/profile.png',
                height: 24,
                width: 24,
              ),
            ),
            label: '', // Removido o label
          ),
        ],
      ),
    );
  }
}
