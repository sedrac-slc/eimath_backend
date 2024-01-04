package com.ei.math.fraction.util;

import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.registory.FractionMessage;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FractionUtil {

    public static boolean isPositive(Fraction... fracsOther) {
        return List.of(fracsOther).stream().allMatch(Fraction::isPositive);
    }

    public static Fraction crossSystem(Fraction first, Fraction second) {
        long num = (first.getNumerator() * second.getDenominator()) + (first.getDenominator() * second.getNumerator());
        long den = first.getDenominator() * second.getDenominator();
        return Fraction.of(num, den);
    }

    public static Fraction mult(Fraction first, Fraction second) {
        long num = first.getNumerator() * second.getNumerator();
        long den = first.getDenominator() * second.getDenominator();
        return Fraction.of(num, den);
    }

    public static Fraction div(Fraction first, Fraction second) {
        long num = first.getNumerator() * second.getDenominator();
        long den = first.getDenominator() * second.getNumerator();
        return Fraction.of(num, den);
    }
    
    public static MathResult finishOperation(List<Step> list, Fraction fraction, Object cls, String methodKey ,long start, long end) {
        String method = new FractionMessage().getString(methodKey);
        return MathResult.builder()
                .steps(list)
                .expression(list.get(0).getText())
                .className(cls.getClass().getSimpleName())
                .pack(cls.getClass().getCanonicalName())
                .timeMilliseconds("" + (end - start))
                .result(fraction.text())
                .object(fraction)
                .method(method)
                .status(true)
                .build();
    }
    
    public static String gameSignal(String expression){
        Pattern pattern = Pattern.compile("[\\+|\\-][\\+|\\-]+");
        Matcher matcher = pattern.matcher(expression);
        while(matcher.find()){
            long count = matcher.group().chars().filter( charac -> charac == '-').count();
            expression = expression.replace(matcher.group(), count % 2 == 0 ? "+" : "-");
        }
        if(expression.charAt(0) == '+') expression = expression.substring(1);
        return expression;
    }
    


}
