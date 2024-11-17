import 'package:flutter/material.dart';
import 'package:frontend/components/cards/event_card.dart';

class EventList extends StatelessWidget {
  final List<Map<String, dynamic>> events;

  const EventList({super.key, required this.events});

  @override
  Widget build(BuildContext context) {
    if (events.isEmpty) {
      return const Center(
        child: Text(
          'Não há eventos disponíveis.',
          style: TextStyle(fontSize: 18, color: Colors.grey),
        ),
      );
    }

    return ListView.builder(
      itemCount: events.length,
      itemBuilder: (context, index) {
        return EventCard(
          name: events[index]['name'] ?? 'Sem Nome',
          imageUrl: events[index]['image'] ?? '',
          onPressed: () {
            print('Clicou no ${events[index]['name']}');
          },
        );
      },
    );
  }
}
