import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:web_client/configuration/connection.dart';
import 'package:web_client/views/add_post_page.dart';
import 'package:web_client/views/index.dart';
import 'package:web_client/views/login_page.dart';
import 'package:web_client/views/registration_page.dart';

void main() => runApp(ClientApp());

class ClientApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "ReactiveAuthors",
      initialRoute: '/',
      routes: {
        '/': (context) => IndexPage(postList: Connection().fetchPosts(),),
        '/login': (context) => LoginPage(),
        '/new_post':(context) => AddPostPage(),
        '/register':(context) => RegistrationPage(),
      },
    );
  }
}