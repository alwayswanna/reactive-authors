import 'dart:convert';

import 'package:authors_client/models/post.dart';
import 'package:http/http.dart' as http;


const ROOT_PATH = "localhost:8080";

class Connection{

  Future<List<Post>> fetchPost() async{

    final uri = Uri.http(ROOT_PATH, "/posts");

    final response = await http.get(uri);

    Iterable list = jsonDecode(response.body);

    return List<Post>.from(list.map((model) => Post.fromJson(model)));
  }

}