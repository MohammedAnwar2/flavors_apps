part of 'orders_cubit.dart';

@immutable
abstract class ColorState {}

class InitialColorState extends ColorState {
  final Color color;
  final String title;

  InitialColorState({required this.color, required this.title});
}

class ColorChangedState extends ColorState {


  ColorChangedState();
}

class TitleChangedState extends ColorState {

  TitleChangedState();
}
