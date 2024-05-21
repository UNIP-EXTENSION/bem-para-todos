import 'package:http/http.dart' as http;
import 'package:frontend/services/interceptors/http_interceptors.dart';
import 'package:http_interceptor/http_interceptor.dart';
import 'dart:convert';

class Crudservice {
  static const String url = 'localhost:8080/';
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
}
