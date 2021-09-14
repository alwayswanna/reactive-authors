
import 'package:flutter/material.dart';
import 'package:web_client/models/post.dart';

class PostBoxList extends StatelessWidget{
  final List<Post> posts;
  PostBoxList({Key? key, required this.posts});

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: posts.length,
      itemBuilder: (context, index){
        return GestureDetector(
          child: PostBox(item: posts[index]),
          onTap: (){

          },
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
    return Container(
      padding: EdgeInsets.all(2), height: 140,
      child: Card(
        child: Row(
          mainAxisAlignment: MainAxisAlignment.spaceEvenly,
          children: [
            Expanded(
                child: Container(
                  padding: EdgeInsets.all(5),
                  child: Column(mainAxisAlignment: MainAxisAlignment.spaceEvenly,
                  children: [
                    Text(this.item.title, style: TextStyle(fontWeight: FontWeight.bold),),
                    Text(this.item.description),
                    Text(this.item.likes.toString())
                  ],),
                )
            )
          ],
        ),
      ),
    );
  }
}