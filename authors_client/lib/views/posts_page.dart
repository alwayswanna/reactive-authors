
import 'package:authors_client/configuration/connection.dart';
import 'package:authors_client/models/post.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class PostListState extends State<PostList> {
  List<Post> _postList = <Post>[];

  @override
  void initState() {
    super.initState();
    loadPostFromServer();
  }

  void loadPostFromServer() {
    Connection()
        .fetchPost()
        .then((value) => {setState(() => _postList = value)});
  }

  ListTile _buildItemsForListView(BuildContext context, int index) {
    return ListTile(title: Text(_postList[index].title),
                    subtitle: Column(
                      children: [
                        Text(_postList[index].description),
                        ButtonBar(

                        )
                      ],
                    )
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Reactive authors'),
      ),
      body: ListView.builder(
        itemCount: _postList.length,
        itemBuilder: _buildItemsForListView,
      ),
    );
  }
}

class PostList extends StatefulWidget {
  createState() => PostListState();
}
