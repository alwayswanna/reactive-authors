import 'dart:convert';


class Post{
  final int id;
  final String title;
  final String description;
  final String postText;
  final int likes;

  factory Post.fromJson(Map<String, dynamic> data) {
    return Post(
      data['id'],
      data['title'],
      data['description'],
      data['postText'],
      data['likes']
    );
  }

Post(this.id, this.title, this.description, this.postText, this.likes);
  factory Post.fromMap(Map<String, dynamic> json){
    return Post(
      json['id'],
      json['title'],
      json['description'],
      json['postText'],
      json['likes'],
    );
  }

}

