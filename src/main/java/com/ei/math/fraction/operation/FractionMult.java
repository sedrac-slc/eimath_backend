package com.ei.math.fraction.operation;

import com.ei.math.general.MathResult;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.FractionRegex;
import com.ei.math.fraction.text.FractionFormatter;
import com.ei.math.fraction.util.FractionPartStepMethods;
import com.ei.math.fraction.util.FractionUtil;
/**
 *{@code FractionMult} is the concrete class that represents the multiplecation operation between fraction
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public class FractionMult extends FractionOper{
    public static final String METHOD_DEFAULT = "default";
    /**
     * Returns the multiplication of two fractions using the standard method
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * <pre>{
     * list.add(FractionFormatter.stepOneMult(first, second));
     * list.add(FractionFormatter.stepTwoMult(first, second));
     * Fraction fraction = first.mult(second);
     * if(!fraction.isIrreducible()){
     *   fraction = fraction.simplify();
     *   list.add(FractionFormatter.finish(fraction,3));
     * }</pre>
     * @return (new FractionResult()).toBuilder().steps(list).status(true).fraction(fraction).build();
     */  
    @Override
    public MathResult solve(Fraction first, Fraction second) {
        init();
        long start = System.currentTimeMillis();
        if(first.isNegative() && second.isNegative()){
            first = first.positive(); second = second.positive();
        }if(first.isPositive() && second.isNegative()){
            first = first.negative(); second = second.positive();
        }
        list.add(FractionFormatter.starMultOrDiv(first, second, "*"));
        list.add(FractionFormatter.stepOneMult(first, second));
        list.add(FractionFormatter.stepTwoMult(first, second));
        Fraction fraction = FractionUtil.mult(first, second);
        FractionPartStepMethods.simplify(list, fraction, 3, 4);
        long end = System.currentTimeMillis();
        return FractionUtil.finishOperation(list, fraction,this,"step.methd.mult", start, end);
    }
    /**
     * Returns the multiplication of two fractions using the standard method
     * @param expression {@code String} represents the regular expression. Example: 5/7*3/5
     * @return solve(expression, "mult");
     */ 
    @Override
    public MathResult solve(String expression) {
        return solve(expression,"mult");
    }
    /**
     * Returns the multiplication of two fractions using the standard method
     * @param expression {@code String} represents the regular expression. Example: 5/7*3/5
     * @param method {@code String} represents the sum operation methods. Values:(mmc, crossSystem)
     * @return a FractionResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */  
    @Override
    public MathResult solve(String expression, String method) {
        expression = FractionUtil.gameSignal(expression);
        if(!expression.matches(FractionRegex.MULT)) 
           return MathResult.builder().build();
        String[] numbs = expression.split("\\*");
        Fraction fractionOne = FractionConverter.parse(numbs[0]);
        Fraction fractionTwo = FractionConverter.parse(numbs[1]);
        return solve(fractionOne, fractionTwo);
    }
    /**
     * Returns the multiplication of two fractions using the standard method
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @param method {@code String} this parameter has no use in this function it just exists because of the inheritance of the FractionOper class
     * @return solve(first, second);
     */   
    @Override
    public MathResult solve(Fraction first, Fraction second, String method) {
        return  solve(first, second);
    }
  
}
