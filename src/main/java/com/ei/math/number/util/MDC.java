package com.ei.math.number.util;

import java.util.List;
/**
 *{@code MDC} is a class that represents the greatest common divisor
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public final class MDC {
    /**
      calculates the greatest common divisor
     * @param x {@code int}.
     * @return x;
     */ 
    public static int solve(int x){ return x;}
    /**
      calculates the greatest common divisor
     * @param x {@code long}.
     * @return x;
     */     
    public static long solve(long x){ return x;}
    /**
      calculates the greatest common divisor
     * @param x {@code long}.
     * @param y {@code long}
     * @return x;
     */    
    public static long solve(long x, long y) { //return y == 0 ? x : base(y, x % y);
        long resto, mdc = 0;
        if(x == y) return x;
        do {
            resto = x % y;
            if (resto != 0) {
                x = y;
                y = resto;
            } else  mdc = y;
        } while (resto != 0);
        return mdc;
    }
    /**
      calculates the greatest common divisor
     * @param x {@code int}.
     * @param y {@code int}
     * @return x;
     */        
    public static int solve(int x,int y){ 
       return (int) solve(Integer.valueOf(x).longValue(), Integer.valueOf(y).longValue());
    }
     /**
      calculates the greatest common divisor
     * @param x {@code long}.
     * @param y {@code long}
     * @param numbs 
     * <pre>{
     *  int mdc = solve(x, y);
     *  for (int i = 0; i &lt; numbs.length; i++) 
     *      mdc = solve(mdc, numbs[i]);
     * }</pre>
     * @return x;
     */    
    public static long solve(long x, long y, long... numbs) { 
        long mdc = solve(x, y);
        for (int i = 0; i < numbs.length; i++) 
            mdc = solve(mdc, numbs[i]);
        return mdc;
    }    
    /**
      calculates the greatest common divisor 
     * @param x
     * @param y
     * @param numbs
     * <pre>{
     *  int mdc = solve(x, y);
     *  for (int i = 0; i &lt; numbs.length; i++) 
     *      mdc = solve(mdc, numbs[i]);
     * }</pre>
     * @return mdc;
     */     
    public static int solve(int x, int y, int... numbs) { 
       int mdc = solve(x, y);
        for (int i = 0; i < numbs.length; i++) 
            mdc = solve(mdc, numbs[i]);
        return mdc;
    }     
    /**
      calculates the greatest common divisor
     * @param numbs 
     * <pre>{
        int tam = numbs.size();
        if(tam == 0) return 0;
        if(tam == 1) return solve(numbs.get(0));
        if(tam == 2) return  solve(numbs.get(0), numbs.get(1));
        long mdc = solve(numbs.get(0), numbs.get(1));
        for (int i = 2; i &lt; tam; i++) 
            mdc = solve(mdc, numbs.get(i));
     * }</pre>
     * @return mdc;
     */     
    public static long solve(List<Long> numbs) { 
        int tam = numbs.size();
        if(tam == 0) return 0;
        if(tam == 1) return solve(numbs.get(0));
        if(tam == 2) return  solve(numbs.get(0), numbs.get(1));
        long mdc = solve(numbs.get(0), numbs.get(1));
        for (int i = 2; i < tam; i++) 
            mdc = solve(mdc, numbs.get(i));
        return mdc;
    }   
    
    
    
}
