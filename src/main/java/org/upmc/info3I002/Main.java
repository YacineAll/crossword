package org.upmc.info3I002;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        int n = 3;
        int m = 2;

        String cords = IntStream.range(0,n)
                .boxed()
                .flatMap(x->IntStream.range(0,m).mapToObj(y->String.format("(%d,%d)",x,y)))
                .collect(Collectors.joining(",", "{", "}"));
        System.out.println(cords);
    }
}