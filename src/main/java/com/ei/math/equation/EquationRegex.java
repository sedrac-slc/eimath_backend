package com.ei.math.equation;

import com.ei.math.fraction.FractionRegex;

public final class EquationRegex {
    public static final String SEPARATOR = "[a-zA-z]";
    public static final String FRACTION = FractionRegex.FRACTION;
    public static final String EXPOENT = "[\\+|\\-]?\\d+";
    public static final String EXPOENT_SIGNAL  = "[\\^]";
    
    
    public static final String COEF_AND_INC = FRACTION+"[a-zA-z]";
    public static final String INC_AND_EXP = "\\-?[a-zA-z]\\^"+FRACTION;
    public static final String COF_AND_EXP = FRACTION+"\\^"+FRACTION;
    
    public static final String EQUATION = "("+FRACTION+")?(\\-?"+SEPARATOR+")?("+EXPOENT_SIGNAL+FRACTION+")?";
    
    public static final String SEPARATOR_SIG_ALL = SEPARATOR.substring(0, SEPARATOR.length()-1)
            +"|"+EXPOENT_SIGNAL.substring(1);
    
   
    public static final String SUM_SUB = "[\\+|\\-]?"+EQUATION+"([\\+|\\-]"+EQUATION+")+";
    
    public static final String EQUATION_CALCULATE_INGONITA = "f\\(\\-?[0-9]+\\)="+SUM_SUB;
    
    public static final String EQUATION_ZERO_INGONITA = "0="+"\\d?[a-zA-Z]\\^2([+-]\\d*[a-zA-Z])?([+-]\\d*)?";
    
}
