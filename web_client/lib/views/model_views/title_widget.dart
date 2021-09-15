import 'package:flutter/material.dart';

class TitleWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: Stack(
        children: [
          Text(
            "Reactive authors",
            style: TextStyle(
                fontSize: 30,
                foreground: Paint()
                  ..style = PaintingStyle.stroke
                  ..strokeWidth = 9
                  ..color = Colors.black),
          ),
          Text(
            "Reactive authors",
            style: TextStyle(fontSize: 30),
          )
        ],
      ),
    );
  }
}
