package com.ei.math.fraction.operation;

import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.text.FractionFormatter;
import com.ei.math.fraction.util.FractionPartStepMethods;
import com.ei.math.number.util.MMC;
import com.ei.math.general.MathResult;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.FractionRegex;
import com.ei.math.fraction.util.FractionUtil;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Getter;
import lombok.Setter;
/**
 *{@code FractionSub} is the concrete class that represents the subtraction operation between fraction
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public class FractionSub extends FractionOper{
    public final static String METHOD_MMC = "mmc";
    public final static String METHOD_CROSS_SYSTEM = "crossSystem";
    public final static String METHOD_RANDOM = "random";
    @Getter @Setter
    private String method = METHOD_RANDOM;    
    @Setter
    private boolean listGeneratorExpression = false;    
    /**
     * Returns the subtraction of two fractions using the least common multiple method
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @see com.ei.math.number.util.MMC
     * @see com.ei.math.fraction.MathResult
     * @return a MathResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */       
    public MathResult minMultiploCommon(Fraction first, Fraction second){
        init();
        long start = System.currentTimeMillis();
        if(listGeneratorExpression){
            list.add(ArithmeticFormatter.start(List.of(first, second)));
        }        
        if(first.getDenominator().equals(second.getDenominator()))
            return FractionPartStepMethods.baseCase(getClass(), list, first, second, "-");
        long mmc = MMC.solve(first.getDenominator(), second.getDenominator());
        second = second.isPositive()? second.negative() : second;
        list.add(FractionFormatter.startSumOrSub(first, second,"step.method.mmc"));
        list.add(FractionFormatter.stepOneMMC(mmc, first,second,"-"));
        list.add(FractionFormatter.stepTwoMMC(mmc, first, second,"-"));
        list.add(FractionFormatter.stepThreeMMC(mmc, first, second));
        list.add(FractionFormatter.stepFourMMC(mmc, first, second));
        long num = first.getNumerator()*(mmc/first.getDenominator())+
                   second.getNumerator()*(mmc/second.getDenominator());
        Fraction fraction = Fraction.of(num, mmc);
        FractionPartStepMethods.simplify(list, fraction, 5, 6);
        long end = System.currentTimeMillis();
        return FractionUtil.finishOperation(list, fraction, this,"step.method.mmc",start, end);
    }
    /**
     * Returns the subtraction of two fractions using the cross system method
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @see com.ei.math.fraction.MathResult
     * @return a MathResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */     
    public MathResult crossSystem(Fraction first, Fraction second){
        init();
        long start = System.currentTimeMillis();
        if(listGeneratorExpression){
            list.add(ArithmeticFormatter.start(List.of(first, second)));
        }        
        if(first.getDenominator().equals(second.getDenominator()))
            return FractionPartStepMethods.baseCase(getClass(), list, first, second, "-");    
        second = second.isPositive()? second.negative() : second;
        list.add(FractionFormatter.startSumOrSub(first, second,"step.method.cross"));
        list.add(FractionFormatter.stepOneCrossSystem(first, second,"-"));
        list.add(FractionFormatter.stepTwoCrossSystem(first, second,"-"));
        list.add(FractionFormatter.stepThreeCrossSystem(first, second));
        Fraction fraction = FractionUtil.crossSystem(first, second);
        FractionPartStepMethods.simplify(list, fraction, 4, 5);
        long end = System.currentTimeMillis();
        return FractionUtil.finishOperation(list, fraction, this ,"step.method.cross", start, end);
    }
    /**
     * Returns the subtraction of two fractions using the standard method (cross system)
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @return crossSystem(first, second);
     */  
    @Override
    public MathResult solve(Fraction first, Fraction second) {
        return crossSystem(first, second);
    }
    /**
     * Return the subtraction of expression using method
     * @param expression {@code String} represents the regular expression. Example: 5/7-3/5
     * @param method {@code String} represents the sum operation methods. Values:(mmc, crossSystem)
     * @return a MathResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */  
    @Override
    public MathResult solve(String expression, String method) {
        String[] numbs;
        Fraction fractionOne, fractionTwo;
        expression = FractionUtil.gameSignal(expression);
        if(expression.matches("\\-"+FractionRegex.SUM)){
            numbs = expression.split("\\+");
            fractionOne = FractionConverter.parse(numbs[1]);
            fractionTwo = FractionConverter.parse(numbs[0].substring(1)).negative();
        }else if(expression.matches("\\-"+FractionRegex.SUB)){
            expression = expression.substring(1);
            numbs = expression.split("\\-");
            fractionOne = FractionConverter.parse(numbs[0]).negative();
            fractionTwo = FractionConverter.parse(numbs[1]).negative();
        }else{
            if(!expression.matches("\\+?"+FractionRegex.SUB)) 
                return new MathResult().toBuilder().build();
            numbs = expression.split("\\-");
            fractionOne = FractionConverter.parse(numbs[0]);
            fractionTwo = FractionConverter.parse(numbs[1]).negative();
        }
        if(method.equals(METHOD_MMC)) return minMultiploCommon(fractionOne, fractionTwo);
        if(method.equals(METHOD_CROSS_SYSTEM)) return crossSystem(fractionOne, fractionTwo);
        boolean par = ThreadLocalRandom.current().nextInt(1, 10) % 2 == 0;
        return  par ? minMultiploCommon(fractionOne, fractionTwo) : crossSystem(fractionOne, fractionTwo);
    }    
     /**
     * Return the subtraction of expression using the standard method (cross system)
     * @param expression {@code String} represents the regular expression. Example: 5/7-3/5
     * @return solve(expression, FractionSub.METHOD_CROSS_SYSTEM);
     */     
    @Override
    public MathResult solve(String expression) {
        return solve(expression,method);
    }
    /**
     * Return the subtraction of expression using the standard method (cross system)
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @param method {@code String} represents the subtraction operation methods. Values:(mmc, crossSystem)
     * @return a MathResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */   
    @Override
    public MathResult solve(Fraction first, Fraction second, String method) {
        switch(method){
            case METHOD_MMC:
                return minMultiploCommon(first, second);
            case METHOD_CROSS_SYSTEM:
                return crossSystem(first, second);
            default:
                boolean par = ThreadLocalRandom.current().nextInt(1,10) % 2 == 0;
                return par ? minMultiploCommon(first, second) : crossSystem(first, second);
        }
    }  
    
}
 