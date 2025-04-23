import 'package:flutter/material.dart';
import 'package:frontend/components/alerts/alert_dialog.dart';

Future<void> waitingAlert(BuildContext context) async {
  await alertDialog(
    context,
    primarybutton: false,
    textBody: "Carregando...",
    dialogIcon: Padding(
      padding: const EdgeInsets.all(10),
      child: SizedBox(
        width: 70,
        height: 70,
        child: CircularProgressIndicator(
          color: Colors.grey[500],
          strokeWidth: 10,
        ),
      ),
    ),
    secondButton: false,
  );
}

Future<void> alertLoadingMessage(BuildContext context, String message) async {
  await alertDialog(
    context,
    primarybutton: false,
    textBody: message,
    dialogIcon: Padding(
      padding: const EdgeInsets.all(10),
      child: SizedBox(
        width: 70,
        height: 70,
        child: CircularProgressIndicator(
          color: Colors.grey[500],
          strokeWidth: 10,
        ),
      ),
    ),
    secondButton: false,
  );
}

Future<void> alertSucessMessage(BuildContext context, String message) async {
  await alertDialog(
    context,
    primarybutton: false,
    textBody: message,
    dialogIcon: Icon(
      Icons.check_circle,
      color: Colors.green[400],
      size: 120,
    ),
    secondButton: false,
  );
}
