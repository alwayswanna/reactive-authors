
import 'package:flutter/material.dart';
import 'package:web_client/models/post.dart';

class PostBoxList extends StatelessWidget{
  final List<Post> posts;
  PostBoxList({Key? key, required this.posts});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      padding: const EdgeInsets.all(8),
      scrollDirection: Axis.vertical,
      shrinkWrap: true,
      itemCount: posts.length,
      itemBuilder: (context, index){
        return Container(
            child: PostBox(item: posts[index],),
        );
      }
    );
  }
}

class PostBox extends StatelessWidget{
  PostBox({Key? key, required this.item});
  final Post item;

  @override
  Widget build(BuildContext context) {
    return Center(
      child: Card(
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: <Widget>[
            ListTile(
              leading: Icon(Icons.album),
              title: Text(item.title),
              subtitle: Text(item.description),
            ),
            Row(
              mainAxisAlignment: MainAxisAlignment.end,
              children: <Widget>[
                TextButton(
                  child: const Text('BUY TICKETS'),
                  onPressed: () {/* ... */},
                ),
                const SizedBox(width: 8),
                TextButton(
                  child: const Text('BUY TICKETS'),
                  onPressed: () {/* ... */},
                ),
                const SizedBox(width: 8),
              ],
            ),
          ],
        ),
      ),
    );
  }
}