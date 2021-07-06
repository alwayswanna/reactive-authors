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

  void _addNewPost() {
    Connection().createNewPost(
        'Post from flutter', 'Flutter description', 'Text off flutter post', 0);
  }

  ListTile _buildItemsForListView(BuildContext context, int index) {
    Widget titleSection = Container(
      padding: const EdgeInsets.all(32),
      child: Row(
        children: [
          Expanded(
            /*1*/
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Container(
                  padding: const EdgeInsets.only(bottom: 8),
                  child: Text(
                    _postList[index].title,
                    style: TextStyle(
                      fontWeight: FontWeight.bold,
                    ),
                  ),
                ),
                Text(
                  _postList[index].description,
                  style: TextStyle(
                    color: Colors.grey[500],
                  ),
                ),
              ],
            ),
          ),
          /*3*/
          Icon(
            Icons.star,
            color: Colors.red[500],
          ),
          Text(_postList[index].likes.toString()),
        ],
      ),
    );
    return ListTile(subtitle: titleSection);
  }

  void handleClick(String value) {
    switch (value) {
      case 'Create account':
        break;
      case 'Login':
        Navigator.pushNamed(context, '/login');
        break;
      case 'Add post':
        Navigator.pushNamed(context, '/new_post');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text('Reactive authors'),
          actions: <Widget>[
            PopupMenuButton<String>(
              onSelected: handleClick,
              itemBuilder: (BuildContext context) {
                return {'Create account', 'Login', 'Add post'}.map((String choice) {
                  return PopupMenuItem<String>(
                    value: choice,
                    child: Text(choice),
                  );
                }).toList();
              },
            )
          ],
        ),
        body: GridView.count(
          crossAxisCount: 3,
          crossAxisSpacing: 10,
          children: [
            ListView.builder(
              itemCount: _postList.length,
              itemBuilder: _buildItemsForListView,
            ),
          ],
        ));
  }
}

class PostList extends StatefulWidget {
  createState() => PostListState();
}
