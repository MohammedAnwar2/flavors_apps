import 'package:flavors/configration/configration.dart';
import 'package:flavors/cubit/orders_cubit.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

class FlavorsApp extends StatelessWidget {
  const FlavorsApp({super.key, required this.configration});

  final Configration configration;
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: configration.color),
      ),
      home: const ColorView(),
    );
  }
}

class ColorView extends StatelessWidget {
  const ColorView({super.key});

  @override
  Widget build(BuildContext context) {
    return BlocProvider(
      create: (context) => ColorCubit(),
      child: const Scaffold(appBar: CustomAppBar(), body: ColorViewBody()),
    );
  }
}


class CustomAppBar extends StatelessWidget implements PreferredSizeWidget {
  const CustomAppBar({super.key});

  @override
  Size get preferredSize => const Size.fromHeight(kToolbarHeight);
  @override
  Widget build(BuildContext context) {
    return BlocBuilder<ColorCubit, ColorState>(
      buildWhen: (previous, current) => current is TitleChangedState,
      builder: (context, state) {
        final title = context.read<ColorCubit>().currentTitle;
        print('AppBar rebuild');
        return AppBar(title: Text(title));
      },
    );
  }
}

class ColorViewBody extends StatelessWidget {
  const ColorViewBody({super.key});
  @override
  Widget build(BuildContext context) {
    return const Column(
      children: [
        Expanded(child: ListViewBlocBuilder()),
        ColorAndTitleControls(),
        SizedBox(height: 16),
      ],
    );
  }
}

class ListViewBlocBuilder extends StatelessWidget {
  const ListViewBlocBuilder({super.key});

  @override
  Widget build(BuildContext context) {
    final items = context.read<ColorCubit>().fetchDate();
    print('ListViewBlocBuilder rebuild');
    return BlocBuilder<ColorCubit, ColorState>(
      buildWhen: (previous, current) => current is ColorChangedState,
      builder: (context, state) {
        print('ColorListScreenBody rebuild');
        final color = context.read<ColorCubit>().currentColor;
        return ListView.builder(
          itemCount: items.length,
          itemBuilder: (context, index) {
            return Container(
              color: color,
              margin: const EdgeInsets.symmetric(vertical: 4, horizontal: 8),
              padding: const EdgeInsets.all(16),
              child: Text(
                items[index],
                style: const TextStyle(color: Colors.white),
              ),
            );
          },
        );
      },
    );
  }
}

class ColorAndTitleControls extends StatelessWidget {
  const ColorAndTitleControls({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.spaceEvenly,
      children: [
        ElevatedButton(
          onPressed: () => context.read<ColorCubit>().toggleColor(),
          child: const Text('تغيير اللون'),
        ),
        ElevatedButton(
          onPressed: () => context.read<ColorCubit>().updateTitle(),
          child: const Text('تغيير العنوان'),
        ),
      ],
    );
  }
}
