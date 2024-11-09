import 'package:flutter/material.dart';
import 'package:frontend/components/app_bar.dart';
import 'package:frontend/components/bottom_nav_bar.dart';

class HomeScreen extends StatefulWidget {
  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;
  int _selectedIndex = 0; // Índice selecionado no BottomNavigationBar

  final List<Map<String, String>> events = [
    {
      'name': 'Evento 1',
      'image': 'https://via.placeholder.com/150',
      'link': 'https://example.com/event1'
    },
    {
      'name': 'Evento 2',
      'image': 'https://via.placeholder.com/150',
      'link': 'https://example.com/event2'
    },
    {
      'name': 'Evento 3',
      'image': 'https://via.placeholder.com/150',
      'link': 'https://example.com/event3'
    },
  ];

  @override
  void initState() {
    super.initState();
    _tabController =
        TabController(length: 2, vsync: this); // Criação do TabController
  }

  @override
  void dispose() {
    _tabController.dispose(); // Descartar o TabController
    super.dispose();
  }

  // Função para alterar a tela com base no índice selecionado
  void _onBottomNavBarTapped(int index) {
    setState(() {
      _selectedIndex = index; // Atualizar o índice selecionado
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        tabController: _tabController, // Passando o TabController
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              itemCount: events.length,
              itemBuilder: (context, index) {
                return Card(
                  margin: EdgeInsets.all(10),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: [
                      Text(events[index]['name']!,
                          style: TextStyle(fontSize: 18)),
                      Image.network(events[index]['image']!),
                      ElevatedButton(
                        onPressed: () {
                          // Ação do link
                        },
                        child: Text('Ver Evento'),
                      ),
                    ],
                  ),
                );
              },
            ),
          ),
        ],
      ),
      bottomNavigationBar: CustomBottomNavBar(
        currentIndex: _selectedIndex, // Passando o índice selecionado
        onTap:
            _onBottomNavBarTapped, // Passando o callback para atualizar o índice
      ),
    );
  }
}
