
import 'package:authors_client/configuration/connection.dart';
import 'package:authors_client/models/post.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class SinglePostState extends State<SingleView>{
  late Post post;

  @override
  void initState(){
    super.initState();
  }

  static const routeName = '/single-story';


  Post loadPostById(id) {
    Post ps = Connection().getSelectedPost(id).then((value) => post) as Post;
    return ps;
  }

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments;
    Post post = loadPostById(args.toString());
    return Scaffold(
      appBar: AppBar(
        title: Text('Reactive authors'),
      ),
      body: Form(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Container(
              width: 300,
              child: Text(post.title),
            ),
            Container(
              width: 300,
              child: Text(post.description),
            ),
            Container(
              width: 300,
              child: Text(post.postText),
            )
          ],
        ),

      ),
    );
  }

}


class SingleView extends StatefulWidget {
  createState() => SinglePostState();
}
