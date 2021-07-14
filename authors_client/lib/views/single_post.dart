import 'package:flutter/material.dart';

class SinglePostPageState extends State<SinglePostState> {
  final String id;

  SinglePostPageState({required this.id});

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }
}

class SinglePostState extends StatefulWidget {
  final String id;

  const SinglePostState({required Key key, required this.id}) : super(key: key);

  @override
  SinglePostPageState createState() => SinglePostPageState(id: this.id);
}
