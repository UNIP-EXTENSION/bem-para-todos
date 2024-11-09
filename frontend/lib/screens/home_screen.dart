import 'package:flutter/material.dart';
import 'package:frontend/components/bars/app_bar.dart';
import 'package:frontend/views/home/event_view.dart';
import 'package:frontend/views/home/gallery_view.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  _HomeScreenState createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen>
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
    _tabController = TabController(length: 2, vsync: this);
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
          Tab(text: 'Eventos'),
          Tab(text: 'Álbum'),
        ],
      ),
      body: TabBarView(
        controller: _tabController,
        children: [
          EventList(events: events),
          GalleryView(events: events),
        ],
      ),
    );
  }
}
