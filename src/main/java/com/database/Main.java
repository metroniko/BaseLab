package com.database;


import java.util.stream.IntStream;

/**
 *  starting point of the program.
 */
public class Main {
    public static void main(String[] args) {
        IntStream.of(50, 60, 70, 80, 90, 100, 110, 120).filter(x -> x < 90).map(x -> x + 10)
                .limit(3).forEach(System.out::print);
    }
}
