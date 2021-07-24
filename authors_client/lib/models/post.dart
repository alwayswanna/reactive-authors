class Post {
  final int id;
  final String title;
  final String description;
  final String postText;
  final String authorUserName;
  final int likes;

  Post({
    required this.id,
    required this.title,
    required this.description,
    required this.postText,
    required this.authorUserName,
    required this.likes,
  });

  factory Post.fromJson(Map<String, dynamic> json) {
    return Post(
        id: json['id'],
        title: json['title'],
        description: json['description'],
        postText: json['postText'],
        authorUserName: json['authorUserName'],
        likes: json['likes']);
  }
}
