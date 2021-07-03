import 'package:authors_client/views/posts_page.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

void main() => runApp(ClientApp());

class ClientApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: "ReactiveAuthors", home: PostList());
  }
}
