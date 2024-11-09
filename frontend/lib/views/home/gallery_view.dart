import 'package:flutter/material.dart';
import 'package:frontend/components/cards/album_card.dart';

class GalleryView extends StatelessWidget {
  final List<Map<String, String>> events;

  const GalleryView({super.key, required this.events});

  @override
  Widget build(BuildContext context) {
    return GridView.builder(
      padding: const EdgeInsets.all(8),
      gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
        crossAxisCount: 2,
        crossAxisSpacing: 10,
        mainAxisSpacing: 10,
      ),
      itemCount: events.length,
      itemBuilder: (context, index) {
        return AlbumCard(
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
