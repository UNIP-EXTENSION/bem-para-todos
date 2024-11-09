import 'package:flutter/material.dart';
import 'package:frontend/components/cards/event_card.dart';

class MyEventsScreen extends StatefulWidget {
  const MyEventsScreen({super.key});

  @override
  _MyEventsScreen createState() => _MyEventsScreen();
}

class _MyEventsScreen extends State<MyEventsScreen>
    with SingleTickerProviderStateMixin {
  late TabController _tabController;

  final List<Map<String, String>> events = [
    {'name': 'Evento 1', 'image': 'https://via.placeholder.com/150'},
    {'name': 'Evento 2', 'image': 'https://via.placeholder.com/150'},
    {'name': 'Evento 3', 'image': 'https://via.placeholder.com/150'},
  ];

  @override
  void initState() {
    super.initState();
    _tabController =
        TabController(length: 1, vsync: this); // Uma aba para "Meus Eventos"
  }

  @override
  void dispose() {
    _tabController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: const Color(0xFFFAB603),
        bottom: TabBar(
          controller: _tabController,
          tabs: const [
            Tab(text: 'Meus Eventos'),
          ],
        ),
      ),
      body: TabBarView(
        controller: _tabController,
        children: [
          _buildMyEventList(), // Exibe os eventos do usuário
        ],
      ),
    );
  }

  Widget _buildMyEventList() {
    return ListView.builder(
      itemCount: events.length,
      itemBuilder: (context, index) {
        return EventCard(
          name: events[index]['name']!,
          imageUrl: events[index]['image']!,
          onPressed: () {
            print('Clicou no ${events[index]['name']}');
          },
        );
      },
    );
  }
}
