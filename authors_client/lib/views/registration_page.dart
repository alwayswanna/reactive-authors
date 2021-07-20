
import 'package:authors_client/configuration/connection.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';

class RegistrationPage extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Reactive authors'),
      ),
      body: Center(
        child: FormRegistration(),
      ),
    );
  }

}

class FormRegistration extends StatefulWidget{
  @override
  State<StatefulWidget> createState() {
    return FormRegistrationState();
  }
}

void sendRegistrationData(String username, String password, String name, String surname,
    String email){
  Connection().registerNewUser(username, password, name, surname, email);
}

class FormRegistrationState extends State<FormRegistration>{
  TextEditingController username = TextEditingController();
  TextEditingController password = TextEditingController();
  TextEditingController name = TextEditingController();
  TextEditingController surname = TextEditingController();
  TextEditingController email = TextEditingController();

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
                 return 'Please enter username';
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
               hintText: 'Password'
             ),
             validator: (value){
               if(value == null || value.isEmpty || value.length < 8){
                 return 'Password must contains 8 or more symbols';
               }
               return null;
             },
           )
         ),
         Container(
           width: 300,
           child: TextFormField(
             controller: name,
             decoration: InputDecoration(
                 hintText: 'Name:'
             ),
             validator: (value){
               if(value == null || value.isEmpty){
                 return 'Please enter name';
               }
               return null;
             },
           ),
         ),
         Container(
           width: 300,
           child: TextFormField(
             controller: surname,
             decoration: InputDecoration(
                 hintText: 'Surname:'
             ),
             validator: (value){
               if(value == null || value.isEmpty){
                 return 'Please enter surname';
               }
               return null;
             },
           ),
         ),
         Container(
           width: 300,
           child: TextFormField(
             controller: email,
             decoration: InputDecoration(
                 hintText: 'E-mail:'
             ),
             validator: (value){
               if(value == null || value.isEmpty){
                 return 'Please enter e-mail';
               }
               return null;
             },
           ),
         ),
         Container(
           padding: const EdgeInsets.symmetric(vertical: 15),
           child: ElevatedButton(
             onPressed: (){
               Connection().registerNewUser(username.text, password.text, name.text, surname.text, email.text);
               //sendRegistrationData(username.text, password.text, name.text, surname.text, email.text);
               Navigator.pushNamed(context, "/");
             },
             child: Text('Create user'),
           ),
         )
       ],
     ),
    );
  }
}