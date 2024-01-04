package com.ei.math.fraction;
/**
 * {@code FractionRegex} is a class that contains regular expressions to 
 * validate whether a fraction or operation between fraction.
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public final class FractionRegex {
    private static final String FRACTION_NOT_SIGNAL = "\\d+(\\.\\d+)?(/\\d+(\\.\\d+)?)?";
    
    public static final String SIGNAL = "[\\+|\\-|\\*|:]";
    public static final String FRACTION = "[\\+|\\-]?"+FRACTION_NOT_SIGNAL;
    public static final String FRACTION_MMC = FRACTION+"\\(\\d+\\)";
    
    public static final String SUM = FRACTION_NOT_SIGNAL+"\\+"+FRACTION_NOT_SIGNAL;
    public static final String SUB = FRACTION_NOT_SIGNAL+"\\-"+FRACTION_NOT_SIGNAL;
    public static final String MULT = "[\\+|\\-]?"+FRACTION_NOT_SIGNAL+"\\*"+"[\\+|\\-]?"+FRACTION_NOT_SIGNAL;
    public static final String DIV = "[\\+|\\-]?"+FRACTION_NOT_SIGNAL+":"+"[\\+|\\-]?"+FRACTION_NOT_SIGNAL;
    
    public static final String OPERATION = FRACTION_NOT_SIGNAL+SIGNAL+FRACTION_NOT_SIGNAL;
    
}
