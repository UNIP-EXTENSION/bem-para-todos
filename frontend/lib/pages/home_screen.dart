import 'package:flutter/material.dart';
import 'package:frontend/components/bars/app_bar.dart';
import 'package:frontend/components/bars/bottom_nav_bar.dart';
import 'package:frontend/components/cards/event_card.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;
  int _selectedIndex = 0;

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
    _tabController = TabController(length: 2, vsync: this);
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  void _onBottomNavBarTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: CustomAppBar(
        tabController: _tabController,
      ),
      body: Column(
        children: [
          Expanded(
            child: ListView.builder(
              itemCount: events.length,
              itemBuilder: (context, index) {
                return EventCard(
                  name: events[index]['name']!,
                  imageUrl: events[index]['image']!,
                  onPressed: () {
                    // Aqui você poderá navegar para outra tela
                    print('Clicou no ${events[index]['name']}');
                  },
                );
              },
            ),
          ),
        ],
      ),
      bottomNavigationBar: CustomBottomNavBar(
        currentIndex: _selectedIndex,
        onTap: _onBottomNavBarTapped,
      ),
    );
  }
}
