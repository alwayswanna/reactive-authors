class Account {
  final int id;
  final String username;
  final String password;
  final String name;
  final String surname;
  final String email;
  /*final bool active;
  final Role role;*/

  Account({
    required this.id,
    required this.username,
    required this.password,
    required this.name,
    required this.surname,
    required this.email,
    /*required this.active,
    required this.role,*/
  });

  factory Account.fromJson(Map<String, dynamic> json){
    return Account(
        id: json['id'],
        username: json['username'],
        password: json['password'],
        name: json['name'],
        surname: json['surname'],
        email: json['email']
      /* active: json['active'],
        role: json['role']*/
    );
  }

}

enum Role{
  administrator,
  user
}