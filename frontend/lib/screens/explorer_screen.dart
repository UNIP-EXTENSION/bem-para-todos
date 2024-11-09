import 'package:flutter/material.dart';
import 'package:frontend/components/bars/app_bar.dart';
import 'package:frontend/components/cards/event_card.dart';

class ExplorerScreen extends StatefulWidget {
  const ExplorerScreen({super.key});

  @override
  _ExplorerScreen createState() => _ExplorerScreen();
}

class _ExplorerScreen extends State<ExplorerScreen>
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
      appBar: CustomAppBar(
        tabController: _tabController,
        tabs: const [
          Tab(text: 'Explorar'),
        ],
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
