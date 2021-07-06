import 'dart:convert';

import 'package:authors_client/models/post.dart';
import 'package:http/http.dart' as http;


const ROOT_PATH = "localhost:8080";

class Connection{

  Future<List<Post>> fetchPost() async {
    final response =
    await http.get(Uri.parse('http://localhost:8080/posts'));

    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      Iterable listPosts = jsonDecode(response.body);
      return List<Post>.from(listPosts.map((e) => Post.fromJson(e)));
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to load album');
    }
  }

  Future<http.Response> createNewPost(String title, String description, String postText, int likes){
    return http.post(
      Uri.parse('http://localhost:8080/post'),
      headers: <String, String>{
        'ContentType' : 'application/json; charset=UTF-8'
      },
      body: jsonEncode(<String, dynamic>{
          'title' : title,
          'description' : description,
          'postText' : postText,
          'likes' : likes,
      })
    );
  }


}