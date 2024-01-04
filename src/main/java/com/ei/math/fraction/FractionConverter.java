package com.ei.math.fraction;

import com.ei.math.fraction.exception.FractionRegexException;
/**
 * {@code FractionConverter} is a final class to convert string and double 
 * into a fraction, uses static methods, to do the conversions
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public final class FractionConverter {

   private static Fraction convertDoubleToFraction(String num){
        String[] split = num.split("\\.");
        long numerator = Long.parseLong(split[0]+split[1]);
        long denominator = (long) Math.pow(10, split[1].length());
        return Fraction.of(numerator, denominator).simplify();
   }
    /**
     convert a double to a fraction
     * @param number.
     * <pre>{
     * String num = String.valueOf(number);
     *  if(num.matches("\\d+\\.0+"))
     *     return Fraction.of(Double.valueOf(num).longValue());
     * }</pre>
     * @return parseStringToFraction(num);
     */        
   public static Fraction parse(double number){
        String num = String.valueOf(number);
        if(num.matches("[\\-|\\+]?\\d+\\.0+"))
               return Fraction.of(Double.valueOf(num).longValue());
        return convertDoubleToFraction(num);
    }
    /**
     convert a string to a fraction
     * @param number
     * @see com.ei.math.fraction.exception.FractionRegexException
     * <pre>{
     * String num = String.valueOf(number);
     *  if(num.matches("\\d+\\.0+"))
     *  if(number.matches("\\d+\\.\\d+")) return parseStringToFraction(number);
     *  if(!number.matches(FractionRegex.FRACTION))
     *      throw new FractionNumberFormatException();
     *  String[] numbs = number.split("/");
     *  double numerator = Double.parseDouble(numbs[0].trim());
     *  double denominator = Double.parseDouble(numbs[1].trim());
     * }</pre>
     * @return Fraction.of(numerator,denominator);
     */    
    public static Fraction parse(String number){   
        if(number.matches("[\\-|\\+]?\\d+"))
            return Fraction.of(Long.parseLong(number),1L);
        if(number.matches("[\\-|\\+]?\\d+\\.\\d+")) return convertDoubleToFraction(number);
        if(!number.matches(FractionRegex.FRACTION)){
            throw new FractionRegexException();
        }
        String[] numbs = number.split("/");
        double numerator = Double.parseDouble(numbs[0].trim());
        double denominator = Double.parseDouble(numbs[1].trim());
        return Fraction.of(numerator,denominator);
    }
          
}
