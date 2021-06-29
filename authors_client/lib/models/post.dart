import 'package:json_annotation/json_annotation.dart';

@JsonSerializable()
class Post{
  final int id;
  final String title;
  final String description;
  final String postText;
  final int likes;

  Post({
    required this.id,
    required this.title,
    required this.description,
    required this.postText,
    required this.likes,
  });

}

