import 'package:authors_client/views/posts_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:authors_client/views/login_page.dart' as LoginState;

void main() => runApp(ClientApp());

class ClientApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: "ReactiveAuthors",
      initialRoute: '/',
      routes: {
        '/':(context) => PostList(),
        '/login':(context) => LoginState.LoginStatePage(),
      },
    );
  }
}
