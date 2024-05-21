import 'package:flutter/material.dart';
import 'package:frontend/components/alerts/alert_dialog.dart';

Future<void> sucessAlert(BuildContext context) async {
  await alertDialog(
    context,
    primarybutton: true,
    textBody: "Gravação realizada com êxito.",
    textConfirmationButton: "Voltar",
    dialogIcon: Icon(
      Icons.check_circle,
      color: Colors.green[400],
      size: 120,
    ),
    secondButton: false,
  );
}

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
