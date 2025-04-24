import 'package:flutter/material.dart';
import 'package:frontend/components/buttons/button_auth.dart';
import 'package:frontend/components/inputs/input-auth.dart';
import 'package:frontend/helpers/situation-alerts.dart';
import 'package:frontend/models/User/User.dart';
import 'package:frontend/pages/login_page.dart';
import 'package:frontend/services/CrudService.dart';

class EditProfileForm extends StatefulWidget {
  const EditProfileForm({Key? key});

  @override
  State<EditProfileForm> createState() => _EditProfileFormState();
}

class _EditProfileFormState extends State<EditProfileForm> {
  final TextEditingController _nameController = TextEditingController();
  final TextEditingController _lastNameController = TextEditingController();
  final TextEditingController _emailController = TextEditingController();
  final TextEditingController _confirmEmailController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();
  final TextEditingController _confirmPasswordController =
      TextEditingController();

  Map<String, dynamic> mapController() {
    Map<String, String> map = {
      "name": _nameController.text,
      "lastName": _lastNameController.text,
      "email": _emailController.text,
      "confirmEmail": _confirmEmailController.text,
      "password": _passwordController.text,
      "confirmPassword": _confirmPasswordController.text,
    };
    return map;
  }

  void _submitForm() {
    if (_formKey.currentState!.validate()) {
      waitingAlert(context);
      Crudservice(resource: "users").post(map: mapUser()).then((value) async {
        Navigator.pop(context);

        await alertSucessMessage(context, "Dados alterados!");

        if (context.mounted) {
          Navigator.pushReplacement(
            context,
            MaterialPageRoute(builder: (context) => const LoginPage()),
          );
        }
      });
    }
  }

  Map<String, dynamic> mapUser() {
    User user = User(
        fisrtName: mapController()["name"],
        lastName: mapController()["lastName"],
        email: mapController()["email"],
        password: mapController()["password"]);
    return user.map();
  }

  final _formKey = GlobalKey<FormState>();

  @override
  void dispose() {
    _nameController.dispose();
    _emailController.dispose();
    _confirmEmailController.dispose();
    _passwordController.dispose();
    _confirmPasswordController.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Card(
      color: Colors.transparent,
      elevation: 0,
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          const CircleAvatar(
            radius: 40,
            backgroundImage: AssetImage('lib/assets/images/bars/profile.png'),
          ),
          const SizedBox(width: 16),
          const Text(
            'Editar Perfil',
            style: TextStyle(
              color: Colors.white,
              fontSize: 30,
              fontWeight: FontWeight.w900,
            ),
          ),
          const SizedBox(height: 18),
          Form(
            key: _formKey,
            child: Column(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              crossAxisAlignment: CrossAxisAlignment.center,
              children: [
                InputAuth(
                  labelText: 'Digite seu nome',
                  hintText: 'Seu nome',
                  controller: _nameController,
                  value: _nameController.text,
                ),
                InputAuth(
                  labelText: 'E-mail',
                  hintText: 'Seu e-mail',
                  controller: _emailController,
                  value: _emailController.text,
                ),
                InputAuth(
                  labelText: 'Confirme seu E-mail',
                  hintText: 'Confirme seu e-mail',
                  controller: _confirmEmailController,
                  value: _confirmEmailController.text,
                ),
                InputAuth(
                  labelText: 'Digite sua senha',
                  hintText: 'Sua senha',
                  obscureText: true,
                  controller: _passwordController,
                  value: _passwordController.text,
                ),
                InputAuth(
                  labelText: 'Confirme sua senha',
                  hintText: 'Confirme sua senha',
                  obscureText: true,
                  controller: _confirmPasswordController,
                  value: _confirmPasswordController.text,
                ),
                const SizedBox(height: 16),
                ButtonAuth(
                  text: 'Editar',
                  onPressed: _submitForm,
                ),
              ],
            ),
          ),
        ],
      ),
    );
  }
}
