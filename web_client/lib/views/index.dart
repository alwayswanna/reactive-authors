import 'package:flutter/material.dart';
import 'package:web_client/models/post.dart';
import 'package:web_client/views/model_views/posts_list_view.dart';

class IndexPage extends StatelessWidget {
  final Future<List<Post>> postList;

  IndexPage({Key? key, required this.postList}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Reactive authors',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: _IndexPage(title: 'All posts:', posts: postList),
    );
  }
}

class _IndexPage extends StatelessWidget {
  final String title;
  final Future<List<Post>> posts;

  _IndexPage({Key? key, required this.title, required this.posts})
      : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Product Navigation")),
        body: Center(
          child: FutureBuilder<List<Post>>(
              future: posts,
              builder: (context, AsyncSnapshot<List<Post>> snapshot) {
                List<Widget> children;
                if (snapshot.hasData) {
                  children = <Widget>[
                    PostBoxList(posts: snapshot.requireData)
                  ];
                } else if (snapshot.hasError) {
                  children = <Widget>[
                    const Icon(
                      Icons.error_outline,
                      color: Colors.red,
                      size: 60,
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 16),
                      child: Text('Error: ${snapshot.error}'),
                    )
                  ];
                } else {
                  children = const <Widget>[
                    SizedBox(
                      child: CircularProgressIndicator(),
                      width: 60,
                      height: 60,
                    ),
                    Padding(
                      padding: EdgeInsets.only(top: 16),
                      child: Text('Awaiting result...'),
                    )
                  ];
                }
                return Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.center,
                    children: children,
                  ),
                );
              },
               /* if (snapshot.hasData) {
                  return PostBoxList(posts: snapshot.requireData);
                } else {
                  Center(child: CircularProgressIndicator(),);
                }*/
              ),
        ));
  }
}
