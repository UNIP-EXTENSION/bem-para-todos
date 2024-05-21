import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:frontend/components/buttons/button_alert.dart';

alertDialog(BuildContext context,
    {required String textBody,
    String? textConfirmationButton,
    required Widget dialogIcon,
    required bool secondButton,
    Widget? newScreenPrimary,
    double? width = 120,
    required primarybutton}) async {
  List<Widget> actions = [];

  if (primarybutton) {
    Widget confirmationButton = PopScope(
        canPop: false,
        child: SizedBox(
            width: width,
            child: ButtonAlert(
                text: textConfirmationButton!,
                onPressed: () {
                  Navigator.pop(context);
                })));
    actions.add(confirmationButton);
  }
  //A variável bool secondBbutton foi criada para poder reutilizar o alert para validacoes
  //e para alert de sucesso, onde o primeiro precisa de um botao de cancelar e o segundo nao
  if (secondButton) {
    Widget cancelButton = PopScope(
        canPop: false,
        child: SizedBox(
            width: width,
            child: ButtonAlert(
                text: "Voltar",
                onPressed: () {
                  Navigator.pop(context);
                })));
    actions.add(cancelButton);
  }
  //configura o AlertDialog
  AlertDialog alert = AlertDialog(
    backgroundColor: Colors.white,
    content: Column(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        mainAxisSize: MainAxisSize.min,
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: dialogIcon,
          ),
          Padding(
            padding: const EdgeInsets.only(top: 10),
            child: Container(
              alignment: AlignmentDirectional.center,
              child: SizedBox(
                width: kIsWeb ? 500 : double.maxFinite,
                child: Text(
                  textBody,
                  style: const TextStyle(fontSize: 18),
                  textAlign: TextAlign.center,
                ),
              ),
            ),
          )
        ]),
    actions: actions,
  );
  //exibe o diálogo
  return await showDialog(
    barrierDismissible: false,
    context: context,
    builder: (BuildContext context) => alert,
  );
}
