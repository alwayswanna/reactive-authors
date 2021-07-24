

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class LoginPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Reactive authors'),
      ),
      body: Center(
        child: FormLogin(),
      ),
    );
  }

}

class FormLogin extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return FormLoginState();
  }
}

class FormLoginState extends State<FormLogin>{
  TextEditingController username = TextEditingController();
  TextEditingController password = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Form(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Container(
            width: 300,
            child: TextFormField(
              controller: username,
              decoration: InputDecoration(
                hintText: 'Username:'
              ),
              validator: (value){
                if(value == null || value.isEmpty){
                  return 'Please enter your username';
                }
                return null;
              },
            ),
          ),
          Container(
            width: 300,
            child: TextFormField(
              controller: password,
              obscureText: true,
              decoration: const InputDecoration(
                hintText: 'Password:'
              ),
              validator: (value){
                if(value == null || value.isEmpty){
                  return 'Please enter password';
                }
                return null;
              },
            ),
          ),
          Container(
            padding: const EdgeInsets.symmetric(vertical: 15),
            child: ElevatedButton(
              onPressed: (){
                print('Type Button');
              },
              child: Text('Login'),
            ),
          )
        ],
      ),
    );
  }
}