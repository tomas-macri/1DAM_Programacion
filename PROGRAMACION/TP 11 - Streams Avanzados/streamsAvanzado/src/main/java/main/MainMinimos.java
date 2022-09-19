package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainMinimos {

  public static void main(String[] args) {

    List<String> list = new ArrayList<>();
    list.add("Monkey");
    list.add("Lion");list.add("Giraffe");list.add("Lemur");




    List<String> nueva = new ArrayList<>();
    for (String s : list)
    {
      if (s.startsWith("L"))
      {
        nueva.add(s.toUpperCase());
      }
    }

    nueva = list.stream()
        .filter(nombre ->  nombre.startsWith("L"))
        .sorted()
        .limit(5)
        .map(String::toUpperCase)
        .map(s -> s.substring(0,2))
        .collect(Collectors.toList());

    nueva.forEach(System.out::println);



    List<Integer> numerosAleatorios = IntStream.of(1,2,3,1,5)
        .distinct()
        .mapToObj(value -> new Integer(value))
        .collect(Collectors.toList());

  }
}
