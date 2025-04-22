import 'package:frontend/components/storages/auth_storage.dart';
import 'package:http/http.dart' as http;
import 'package:frontend/services/interceptors/http_interceptors.dart';
import 'package:http_interceptor/http_interceptor.dart';
import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'dart:convert';

class Crudservice {
  final String url = '${dotenv.env['BASE_URL']}/';
  final AuthStorage _authStorage = AuthStorage();
  final String resource;
  Crudservice({required this.resource});

  http.Client client =
      InterceptedClient.build(interceptors: [LoggingInterceptor()]);

  String getUrl() {
    return '$url$resource';
  }

  //Realizar método POST
  Future<bool> post({required Map<String, dynamic> map}) async {
    String jsonConverted = json.encode(map);
    http.Response response = await client.post(Uri.parse(getUrl()),
        headers: {'Content-type': 'application/json'}, body: jsonConverted);
    if (response.statusCode == 201) {
      return true;
    }
    return false;
  }

  //Realizar método GET
  Future<List<Map<String, dynamic>>> getAll() async {
    try {
      String? token = await _authStorage.getToken();
      if (token == null) {
        throw Exception('Token não encontrado');
      }

      final response = await client.get(
        Uri.parse(getUrl()),
        headers: {
          'Authorization': 'Bearer $token',
          'Content-type': 'application/json'
        },
      );

      if (response.statusCode == 200) {
        Map<String, dynamic> data = json.decode(response.body);

        List<dynamic> eventsData = data['content'];

        List<Map<String, dynamic>> events = eventsData.map((event) {
          return {
            'name': event['name'],
            'image': event['files'][0]['urlFile'],
          };
        }).toList();

        return events;
      } else {
        return [];
      }
    } catch (e) {
      return [];
    }
  }
}
