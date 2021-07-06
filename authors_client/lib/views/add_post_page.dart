import 'package:authors_client/configuration/connection.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class AddPostPage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Reactive Authors'),
      ),
      body: CustomForm(),
    );
  }
}

class CustomForm extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return CustomFormState();
  }
}

String validatorField(value, String response) {
  if (value == null || value.isEmpty) {
    String resDefault = "Please enter ";
    return resDefault + response;
  }
  return '';
}

void createNewPostMethod(String title, String desc, String fullStory) {
  int likes = 0;
  Connection().createNewPost(title, desc, fullStory, likes);
  print('Complete');
}

class CustomFormState extends State<CustomForm> {
  final _formValues = GlobalKey<FormState>();

  @override
  Widget build(BuildContext context) {
    TextEditingController title = TextEditingController();
    TextEditingController desc = TextEditingController();
    TextEditingController story = TextEditingController();
    return Form(
        child: Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: <Widget>[
        TextFormField(
          controller: title,
          decoration: InputDecoration(
            hintText: 'Title',
          ),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter title';
            }
            return null;
          },
        ),
        TextFormField(
          controller: desc,
          decoration: InputDecoration(
            hintText: 'Description',
          ),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter description';
            }
            return null;
          },
        ),
        TextFormField(
          controller: story,
          decoration: InputDecoration(
            hintText: 'Full story',
          ),
          validator: (value) {
            if (value == null || value.isEmpty) {
              return 'Please enter story';
            }
            return null;
          },
        ),
        Padding(
          padding: const EdgeInsets.symmetric(vertical: 16.0),
          child: ElevatedButton(
            onPressed: (){
              Connection().createNewPost(title.text, desc.text, story.text, 0);
              print('Complete');
            },
            child: Text('Create'),
          ),
        ),
      ],
    ));
  }
}
