import 'package:authors_client/views/add_post_page.dart' as NewPost;
import 'package:authors_client/views/login_page.dart' as LoginState;
import 'package:authors_client/views/registration_page.dart' as Registration;
import 'package:authors_client/views/posts_page.dart';
import 'package:authors_client/views/single_post.dart';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() => runApp(ClientApp());

class ClientApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: "ReactiveAuthors",
      initialRoute: '/',
      routes: {
        '/': (context) => PostList(),
        SinglePostState.routeName:(context) => SingleView(),
        '/login': (context) => LoginState.LoginPage(),
        '/new_post':(context) => NewPost.AddPostPage(),
        '/register':(context) => Registration.RegistrationPage(),
      },
    );
  }
}
