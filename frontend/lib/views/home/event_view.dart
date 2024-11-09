import 'package:flutter/material.dart';
import 'package:frontend/components/cards/event_card.dart';

class EventList extends StatelessWidget {
  final List<Map<String, String>> events;

  const EventList({super.key, required this.events});

  @override
  Widget build(BuildContext context) {
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
