package com.ei.math.number.util;

public class NumberPrime {
    
    public static boolean is(long number){
        int contador = 2;
        for(long num = number - 1; num > 1 ; num--)
            if(number % num == 0) {
                contador++;
                break;
            };
        return contador == 2;
    }
    
}
