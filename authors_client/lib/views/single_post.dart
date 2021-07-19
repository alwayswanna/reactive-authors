

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


  Future<Post> loadPostById(id) {
    Future<Post> ps = Connection().getSelectedPost(id);
    return ps;
  }

  @override
  Widget build(BuildContext context) {
    final args = ModalRoute.of(context)!.settings.arguments;
    return Scaffold(
      appBar: AppBar(
        title: Text('Reactive authors'),
      ),
      body: Form(
        child: FutureBuilder<Post>(
          future: loadPostById(args.toString()),
          builder: (context, snapshot){
            if(snapshot.hasData){
              return Column(
                mainAxisAlignment: MainAxisAlignment.center,
                crossAxisAlignment: CrossAxisAlignment.start,
                children: <Widget>[
                  Container(
                    width: 300,
                    child: Text(snapshot.data!.title),
                  ),
                  Container(
                    width: 300,
                    child: Text(snapshot.data!.description),
                  ),
                  Container(
                    width: 300,
                    child: Text(snapshot.data!.postText),
                  )
                ],
              );
            }else if(snapshot.hasError){
              return Text("${snapshot.error}");
            }
            return CircularProgressIndicator();
          },
        )

      ),
    );
  }
}

class SingleView extends StatefulWidget {
  createState() => SinglePostState();
}
