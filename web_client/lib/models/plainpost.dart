
class PlainPost {
  final int id;
  final String username;
  final String name;
  final String surname;
  final String email;

  PlainPost(this.id, this.username, this.name, this.surname, this.email);

  factory PlainPost.fromJson(Map<String, dynamic> data) {
    return PlainPost(
        data['id'],
        data['username'],
        data['name'],
        data['surname'],
        data['email']);
  }

  factory PlainPost.fromMap(Map<String, dynamic> json){
    return PlainPost(
        json['id'],
        json['username'],
        json['name'],
        json['surname'],
        json['email']);
  }

}
