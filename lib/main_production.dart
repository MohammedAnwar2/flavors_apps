import 'package:flavors/app.dart';
import 'package:flavors/configration/production_configration.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(FlavorsApp(configration: ProductionConfigration()));
}
