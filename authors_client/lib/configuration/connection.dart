import 'package:http/http.dart' as http;

const BASE_URL = "localhost:8080";

class Connection{

  final uri = Uri.http(BASE_URL, "/posts");

  void getPosts(){
    var client = http.Client();
  }
}