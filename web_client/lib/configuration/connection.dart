import 'dart:convert';

import 'package:http/http.dart' as http;
import 'package:web_client/models/account.dart';
import 'package:web_client/models/post.dart';

const ROOT_PATH = "http://localhost:8080";

class Connection {

  List<Post> parseProducts(String responseBody) {
    final parsed = json.decode(responseBody).cast<Map<String, dynamic>>();
    return parsed.map<Post>((json) => Post.fromJson(json)).toList();
  }
  Future<List<Post>> fetchPosts() async {
    final response = await http.get(Uri.parse(ROOT_PATH + '/unauthorized/posts'));
    if(response.statusCode == 200){
      return parseProducts(response.body);
    }else{
      print(response.body + ' ' +  response.statusCode.toString());
      throw Exception('RestApiException: can`t load data from server');
    }
  }

  Future<Post> getSelectedPost(String id) async {
    final response = await http.get(Uri.parse('http://localhost:8080/post/' + id));

    if (response.statusCode == 200) {
      return Post.fromJson(jsonDecode(response.body));
    }else{
      throw Exception('There are no post with [ID]: ' + id);
    }
  }

  Future<Post> createNewPost(
      String title, String description, String postText, int likes) async {
    final response = await http.post(
      Uri.parse('http://localhost:8080/post'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, dynamic>{
        'title': title,
        'description': description,
        'postText': postText,
        'likes': likes,
      }),
    );
    if (response.statusCode == 201) {
      return Post.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to create new story');
    }
  }

  Future<Account> registerNewUser(String username, String password, String name, String surname, String email) async{
    final response = await http.post(
      Uri.parse('http://localhost:8080/user'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(<String, dynamic>{
        'username': username,
        'password': password,
        'name': name,
        'surname': surname,
        'email': email,
      }),
    );
    print(response.toString());
    if(response.statusCode == 201){
      return Account.fromJson(jsonDecode(response.body));
    }else{
      throw Exception('Failed to create new account');
    }
  }

  Future<Account> login(String username, String password) async{
    final response = await http.post(
      Uri.parse('http://localhost:8080/login?username=' + username + "&password=" + password),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
    );

    if(response.statusCode == 201){
      return Account.fromJson(jsonDecode(response.body));
    }else{
      throw Exception('Incorrect date for login');
    }
  }

}
