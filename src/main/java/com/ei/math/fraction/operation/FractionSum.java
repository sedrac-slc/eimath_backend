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
import java.util.concurrent.ThreadLocalRandom;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
/**
 *{@code FractionSum} is the concrete class that represents the addition operation between fraction
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
public class FractionSum extends FractionOper{
    public final static String METHOD_MMC = "mmc";
    public final static String METHOD_CROSS_SYSTEM = "crossSystem";
    public final static String METHOD_RANDOM = "random";
    @Getter @Setter
    private String method = METHOD_RANDOM;
    @Setter
    private boolean listGeneratorExpression = false;
    /**
     * Returns the sum or addition of two fractions using the least common multiple method
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @see com.ei.math.number.util.MMC
     * @see com.ei.math.fraction.MathResult
     * @return a FractionResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */      
    public MathResult minMultiploCommon(Fraction first, Fraction second){
        init();
        System.out.println("min: "+listGeneratorExpression);
        if(listGeneratorExpression){
            list.add(ArithmeticFormatter.start(List.of(first, second)));
        }
        if(first.getDenominator().equals(second.getDenominator())){
            String signal = second.isNegative() ? "-" : "+";
            return FractionPartStepMethods.baseCase(getClass(),list, first, second, signal);
        }
        long start = System.currentTimeMillis();
        long mmc = MMC.solve(first.getDenominator(), second.getDenominator());
        list.add(FractionFormatter.startSumOrSub(first, second,"step.method.mmc"));
        list.add(FractionFormatter.stepOneMMC(mmc, first, second,"+"));
        list.add(FractionFormatter.stepTwoMMC(mmc, first, second,"+"));
        list.add(FractionFormatter.stepThreeMMC(mmc, first, second));
        list.add(FractionFormatter.stepFourMMC(mmc, first, second));
        long num = first.getNumerator()*(mmc/first.getDenominator())+
                   second.getNumerator()*(mmc/second.getDenominator());
        Fraction fraction = Fraction.of(num, mmc);
        FractionPartStepMethods.simplify(list, fraction, 5, 6);
        long end = System.currentTimeMillis();
        return FractionUtil.finishOperation(list, fraction,this,"step.method.mmc", start, end);
    }
    /**
     * Returns the sum or addition of two fractions using the cross system method
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @see com.ei.math.fraction.MathResult
     * @return a FractionResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */      
    public MathResult crossSystem(Fraction first, Fraction second){
        init();
        long start = System.currentTimeMillis();
        System.out.println("cross: "+listGeneratorExpression);
        if(listGeneratorExpression){
            list.add(ArithmeticFormatter.start(List.of(first, second)));
        }        
        if(first.getDenominator().equals(second.getDenominator())){
            String signal = second.isNegative() ? "-" : "+";
            return FractionPartStepMethods.baseCase(getClass(), list, first, second, signal);  
        }
        list.add(FractionFormatter.startSumOrSub(first, second, "step.method.cross"));
        list.add(FractionFormatter.stepOneCrossSystem(first, second,"+"));
        list.add(FractionFormatter.stepTwoCrossSystem(first, second,"+"));
        list.add(FractionFormatter.stepThreeCrossSystem(first, second));
        Fraction fraction = FractionUtil.crossSystem(first, second);
        FractionPartStepMethods.simplify(list, fraction, 4, 5);
        long end = System.currentTimeMillis();
        return FractionUtil.finishOperation(list, fraction,this,"step.method.cross", start, end);
    }
    /**
     * Returns the sum or addition of two fractions using the standard method (cross system)
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @return crossSystem(first, second);
     */    
    @Override
    public MathResult solve(Fraction first, Fraction second) {
        return solve(first, second,method); 
    }
    /**
     * Return the sum or addition of expression using method
     * @param expression {@code String} represents the regular expression. Example: 5/7+3/5
     * @param method {@code String} represents the sum or addition operation methods. Values:(mmc, crossSystem)
     * @return a FractionResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */   
    @Override
    public MathResult solve(String expression, String method) {
        expression = FractionUtil.gameSignal(expression);
        if(!expression.matches("\\+?"+FractionRegex.SUM)) 
            return new MathResult().toBuilder().build();
        if(expression.matches("\\-"+FractionRegex.SUB))
            expression = expression.replace("-", "+");
        String[] numbs = expression.split("\\+");
        Fraction fractionOne = FractionConverter.parse(numbs[0]);
        Fraction fractionTwo = FractionConverter.parse(numbs[1]);
       
        if(method.equals(METHOD_MMC)) return minMultiploCommon(fractionOne, fractionTwo);
        if(method.equals(METHOD_CROSS_SYSTEM)) return crossSystem(fractionOne, fractionTwo);
        
        boolean par = ThreadLocalRandom.current().nextInt(1, 10) % 2 == 0;
        return  par ? minMultiploCommon(fractionOne, fractionTwo) : crossSystem(fractionOne, fractionTwo);
    }
    /**
     * Return the sum or addition of expression using the standard method (cross system)
     * @param expression {@code String} represents the regular expression. Example: 5/7+3/5
     * @return solve(expression, FractionSub.METHOD_CROSS_SYSTEM);
     */     
    @Override
    public MathResult solve(String expression) {
        return solve(expression, method);
    }
    /**
     * Return the sum or addition of expression using the standard method (cross system)
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * @param method {@code String} represents the sum or addition operation methods. Values:(mmc, crossSystem)
     * @return a FractionResult object, which brings the result of the operation 
     * and the steps to arrive at the result.
     */   
    @Override
    public MathResult solve(Fraction first, Fraction second, String method) {
        if(method == null) return randomMethod(first, second);
        return switch (method) {
            case METHOD_MMC -> minMultiploCommon(first, second);
            case METHOD_CROSS_SYSTEM -> crossSystem(first, second);
            default -> randomMethod(first, second);
        };
    }
    
    private MathResult randomMethod(Fraction first, Fraction second){
        boolean par = ThreadLocalRandom.current().nextInt(1,10) % 2 == 0;
       return par ? minMultiploCommon(first, second) : crossSystem(first, second);
    }
    
}
