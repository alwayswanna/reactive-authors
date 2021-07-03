class Post {
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

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
        id: json['id'],
        title: json['title'],
        description: json['description'],
        postText: json['postText'],
        likes: json['likes']);
  }
}
