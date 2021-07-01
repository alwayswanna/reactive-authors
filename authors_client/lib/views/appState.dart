
import 'package:authors_client/configuration/connection.dart';
import 'package:authors_client/main.dart';
import 'package:authors_client/models/post.dart';
import 'package:flutter/cupertino.dart';

class _AppState extends State <MyApp> {
  late Future<Post> futurePost;

  @override
  void initState(){
    super.initState();
    Connection con = new Connection();
    futurePost = con.fetchPost() as Future<Post>;
  }

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    throw UnimplementedError();
  }

}