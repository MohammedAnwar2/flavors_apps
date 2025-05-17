import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
part 'orders_state.dart';

class ColorCubit extends Cubit<ColorState> {
  Color _color = Colors.blue;
  String _title = 'Cubit ListView';

  ColorCubit()
      : super(InitialColorState(color: Colors.blue, title: 'Cubit ListView'));

  void toggleColor() {
    _color = _color == Colors.blue ? Colors.green : Colors.blue;
    emit(ColorChangedState());
  }

  void updateTitle() {
    _title = _title == 'Cubit ListView'
        ? 'تم تغيير العنوان'
        : 'Cubit ListView';
    emit(TitleChangedState());
  }

  List<String> fetchDate() {
    return List.generate(10, (index) => 'Item ${index + 1}');
  }

  Color get currentColor => _color;
  String get currentTitle => _title;
}
