import 'account.dart';

class Post{
  final int id;
  final String title;
  final String description;
  final String full_story;
  final int likes;
  final Account authorDTO;


  Post(this.id, this.title, this.description, this.full_story, this.likes, this.authorDTO);
  factory Post.fromJson(Map<String, dynamic> data){
    return Post(
        data['id'],
        data['title'],
        data['description'],
        data['full_story'],
        data['likes'],
        data['accountDTO']);
  }

  factory Post.fromMap(Map<String, dynamic> json){
    return Post(
        json['id'],
        json['title'],
        json['description'],
        json['full_story'],
        json['likes'],
        json['authorDTO']);
  }
}