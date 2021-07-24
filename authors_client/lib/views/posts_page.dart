
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
    Widget titleSection = Container(
      decoration: BoxDecoration(
        border: Border.all(
          color: Colors.black12
        )
      ),
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
                      fontSize: 24,
                      color: Colors.black
                    ),
                  ),
                ),
                Text(
                  _postList[index].description,
                  style: TextStyle(
                    color: Colors.grey[500],
                  ),
                ),
                Padding(
                  padding: const EdgeInsets.fromLTRB(0, 20, 0, 20),
                  child: ElevatedButton(
                      onPressed: (){
                        var id = _postList[index].id;
                        Navigator.pushNamed(context, '/single-story', arguments: id);
                      },
                      style: ElevatedButton.styleFrom(
                          primary: Colors.black12,
                          padding: EdgeInsets.symmetric(horizontal: 30, vertical: 10),
                          textStyle: TextStyle(
                              fontSize: 14,
                              fontWeight: FontWeight.bold)),
                      child: Text("Read more")),
                )
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
        Navigator.pushNamed(context, '/register');
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
          crossAxisCount: 2,
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
