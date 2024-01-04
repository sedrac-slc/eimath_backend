package com.ei.math.equation.util;

import com.ei.math.equation.Equation;
import com.ei.math.equation.EquationConverter;
import com.ei.math.equation.registory.EquationMessage;
import static com.ei.math.equation.text.EquationFormatter.message;
import com.ei.math.fraction.Fraction;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class EquationUtil {

    public static List<Equation> convertToList(String expression){
        List<Equation> list = new ArrayList<>();
        int pos, start = 0;
        String signal, express;
        String[] split = expression.split("[\\+|\\-]");
        for (String exp : split) {
            pos = expression.indexOf(exp,start);
            signal = pos-1 >= 0 ? expression.charAt(pos-1) == '-' ? "-" : "" : "";
            start += exp.length();
            express = signal+exp;
            if(!"".equals(express)){
                if("-".equals(express)) express = "-1";
                if("+".equals(express)) express = "1";
                list.add(EquationConverter.parse(express));
            }
        }
        return list;
    }
    
    public static Equation calculate(Equation equation){
        if(!equation.getIncognita().matches("\\d+")) return equation;
        Long number = Long.valueOf(equation.getIncognita());
        double pow = Math.pow(number, equation.getExpoent().getNumerator());
        Fraction powFraction = Fraction.of(pow);
        Fraction mult = equation.getCoefficient().mult(powFraction);
        return Equation.builder().coefficient(mult).build();
    }
    
    public static List<Equation> calculate(List<Equation> list){
        return list.stream().map( e -> calculate(e)).toList();
    }
    
    public static String messageSituaction(EquationMessage message, String text){
        Pattern pattern = Pattern.compile("\\(\\w+[\\+|\\-]+\\w+\\)");
        Matcher matcher = pattern.matcher(text);
        List<String> expressoes = new ArrayList<>();
        while(matcher.find()){
            expressoes.add(matcher.group());
        }
        
        if(expressoes.isEmpty()) return message.getString("step.eq.finish");
        
        String join = expressoes.stream().collect(Collectors.joining(","));
        return String.format(message.getString("step.eq.oper"),join);
    }

    public static String calculateExpoent(Equation equation, String ingonita, boolean signal) {
        int parseInt = Integer.parseInt(ingonita);
        long pow = (long) Math.pow(parseInt, equation.getExpoent().getNumerator());
        return new Equation(equation.getCoefficient(), pow+"", Fraction.of(0)).text(signal);
    }

}
