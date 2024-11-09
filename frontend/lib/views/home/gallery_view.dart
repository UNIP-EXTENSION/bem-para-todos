import 'package:flutter/material.dart';

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
        return Card(
          shape: RoundedRectangleBorder(
            borderRadius: BorderRadius.circular(10),
          ),
          child: ClipRRect(
            borderRadius: BorderRadius.circular(10),
            child: Image.network(
              events[index]['image']!,
              fit: BoxFit.cover,
            ),
          ),
        );
      },
    );
  }
}
