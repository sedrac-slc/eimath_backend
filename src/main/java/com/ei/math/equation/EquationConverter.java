package com.ei.math.equation;

import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.equation.exception.EquationRegexException;

public class EquationConverter {
    
    public static Equation parse(String expression){
        if(expression.trim().isEmpty() || !expression.matches(EquationRegex.EQUATION)){
            throw new EquationRegexException();
        }
        
        if(expression.matches("\\d+"))
            return Equation.builder()
                           .coefficient(FractionConverter.parse(expression))
                           .expoent(Fraction.of(1))
                           .build();
        
         if(expression.matches("[a-zA-Z]"))
            return Equation.builder().incognita(expression).expoent(Fraction.of(1)).build();
        
        if(expression.matches(EquationRegex.FRACTION))
            return Equation.builder().coefficient(FractionConverter.parse(expression)).build();
        
        if(expression.matches(EquationRegex.COEF_AND_INC)){
            String[] split = expression.split("(?<=\\d)(?=\\D)");
            return Equation.builder()
                    .coefficient(FractionConverter.parse(split[0]))
                    .incognita(split[1])
                    .build();
        }
        
        if(expression.matches(EquationRegex.INC_AND_EXP)){
            boolean neg = false;
            if(expression.startsWith("-")){
                neg = true;
                expression = expression.substring(1);
            }
            String[] split = expression.split("(?<=[a-zA-Z])\\^");
            return Equation.builder()
                    .coefficient(neg ? Fraction.of(-1) : Fraction.of(1))
                    .incognita(split[0])
                    .expoent(FractionConverter.parse(split[1]))
                    .build();
        }        
        
        if(expression.matches(EquationRegex.COF_AND_EXP)){
            String[] split = expression.split("(?<=\\d)\\^");
            return Equation.builder()
                    .coefficient(FractionConverter.parse(split[0]))
                    .expoent(FractionConverter.parse(split[1]))
                    .build();
        }       
        String rgx = "(?<=[a-zA-Z^])|(?=[a-zA-Z^])";
        String[] split = expression.split(rgx);
        
        for(int i = 0; i < split.length ; i++){
            if("-".equals(split[i])) split[i] = "-1";
            if("+".equals(split[i])) split[i] = "1";
        }
       
        Equation build = Equation.builder().coefficient(FractionConverter.parse(split[0])).incognita(split[1]).build();
        if(split.length == 2) return build;              
        build.setExpoent(FractionConverter.parse(split[3]));
        return build;
                
    } 
    
}
