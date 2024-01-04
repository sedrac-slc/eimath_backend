package com.ei.math.number.util;

import java.util.List;
/**
 *{@code MMC} is a class that represents the least common multiple
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public final class MMC {
    /**
     calculates least common multiple
     * @param x {@code int}.
     * @return x;
     */     
    public static int solve(int x){ return x;}
    /**
     calculates least common multiple
     * @param x {@code long}.
     * @return x;
     */      
    public static long solve(long x){ return x;}
    /**
     calculates least common multiple
     * @param x {@code long}.
     * @param y {@code long}
     * @return x*y)/MDC.solve(x,y);
     */     
    public static long solve(long x, long y) { //return y == 0 ? x : base(y, x % y);
        return (x*y)/MDC.solve(x,y);
    }
    /**
      calculates least common multiple
     * @param x {@code int}.
     * @param y {@code int}
     * @return x;
     */       
    public static int solve(int x,int y){ 
        return (int) solve(Integer.valueOf(x).longValue(), Integer.valueOf(y).longValue());
    }
     /**
      calculates least common multiple
     * @param x {@code long}.
     * @param y {@code long}
     * @param numbs 
     * <pre>{
     *  int mdc = solve(x, y);
     *  for (int i = 0; i &lt; numbs.length; i++) 
     *      mdc = solve(mdc, numbs[i]);
     * }</pre>
     * @return mmc;
     */    
    public static long solve(long x, long y, long... numbs) { 
        long mmc = solve(x, y);
        for (int i = 0; i < numbs.length; i++) 
            mmc = solve(mmc, numbs[i]);
        return mmc;
    }
     /**
      calculates least common multiple
     * @param x {@code long}.
     * @param y {@code long}
     * @param numbs 
     * <pre>{
     *  int mdc = solve(x, y);
     *  for (int i = 0; i &lt; numbs.length; i++) 
     *      mdc = solve(mdc, numbs[i]);
     * }</pre>
     * @return mmc;
     */      
    public static int solve(int x, int y, int... numbs) { 
       int mmc = solve(x, y);
        for (int i = 0; i < numbs.length; i++) 
            mmc = solve(mmc, numbs[i]);
        return mmc;
    }     
    /**
      calculates least common multiple
     * @param numbs 
     * <pre>{
        int tam = numbs.size();
        if(tam == 0) return 0;
        if(tam == 1) return solve(numbs.get(0));
        if(tam == 2) return  solve(numbs.get(0), numbs.get(1));
        long mmc = solve(numbs.get(0), numbs.get(1));
        for (int i = 2; i &lt; tam; i++) 
            mmc = solve(mmc, numbs.get(i));
     * }</pre>
     * @return mmc;
     */ 
    public static long solve(List<Long> numbs) { 
        int tam = numbs.size();
        if(tam == 0) return 0;
        if(tam == 1) return solve(numbs.get(0));
        if(tam == 2) return  solve(numbs.get(0), numbs.get(1));
        long mmc = solve(numbs.get(0), numbs.get(1));
        for (int i = 2; i < tam; i++) 
            mmc = solve(mmc, numbs.get(i));
        return mmc;
    }       
    
}
