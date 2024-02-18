package com.ei.math.arithmetic.basic;

import com.ei.math.general.MathResult;
import com.ei.math.general.StepMap;
import com.ei.math.arithmetic.ArithmeticRegex;
import com.ei.math.arithmetic.abs.ArithOperaction;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.arithmetic.registory.ArithmeticMessage;
import com.ei.math.arithmetic.text.ArithmeticFormatter;
import com.ei.math.arithmetic.util.ArithmeticPartStepMethos;
import com.ei.math.arithmetic.util.ArithmeticUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import com.ei.math.fraction.operation.FractionSum;
import com.ei.math.fraction.util.FractionUtil;
import com.ei.math.util.MathResultUtil;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import com.ei.math.arithmetic.abs.ArithListOper;
import lombok.Setter;

public class ArithmeticSum extends ArithOperaction implements ArithListOper{
    public final static String METHOD_SEQUENCIAL = "sequencial";
    public final static String METHOD_MMC = "minMultipliCommon";
    public final static String METHOD_RANDOM = "random";
    
    private final FractionSum fractionSum;
    
    @Setter
    private boolean listGeneratorExpression = false;

    {
        fractionSum = new FractionSum();
    }
      
    public MathResult sequencial(List<Fraction> fractions) {
        init();
        long start = System.currentTimeMillis();
        System.out.println("arithmeticSum:sequecial");
        
        fractionSum.setMethod(arithmeticParams.getMethodFractionSum());
        fractionSum.setListGeneratorExpression(listGeneratorExpression);
        
        int tam = fractions.size(); 
        
        if(tam == 0) return MathResult.builder().build();
        if(tam == 1) return ArithmeticPartStepMethos.of(fractions.get(0));
     
        final long den = fractions.get(0).getDenominator();
        boolean denEquals = fractions.stream().allMatch(frac -> frac.getDenominator().equals(den));
        
        if(denEquals) return ArithmeticUtil.commun(fractions,listGeneratorExpression);
        
        if(tam == 2) return fractionSum.solve(fractions.get(0), fractions.get(1));
        steps.add(ArithmeticFormatter.start(fractions));
        
        mathResult =  fractionSum.solve(fractions.get(0), fractions.get(1));
        fractionResult = mathResult.fractionResult();
        fractions = fractions.subList(2, tam);
        tam = fractions.size();
        
        fractionsOper = ArithmeticUtil.join(fractionResult.getFraction(), fractions);
        stepGroups.add(StepMap.of(fractionResult.getExpression(), fractionResult.getSteps()));
        Fraction fraction = fractionResult.getFraction().simplify();
        String operacao;
        for (int i = 0; i < tam; i++) {
            fractionsOper = ArithmeticUtil.join(fraction, fractions.subList(i, tam));
            mathResult = fractionSum.solve(fraction, fractions.get(i));
            fractionResult = mathResult.fractionResult();
            operacao = String.format("%s+%s=", fraction,fractions.get(i));
            fraction = fractionResult.getFraction().simplify();
            operacao += fraction.text();
            if(i+1 < tam){
               steps.add(ArithmeticFormatter.stepSequencial(fractionsOper,operacao,i+2));
               stepGroups.add(StepMap.of(fractionResult.getExpression(), fractionResult.getSteps()));
            }
        }
        fractionResult.getSteps().get(0).setMessage(ArithmeticMessage.get("step.arith.seq.finish"));
        steps.addAll(fractionResult.getSteps());
        long end = System.currentTimeMillis();
        return MathResultUtil.finishOperation(stepGroups,steps, fraction, new ArithmeticSum(), start, end);
    }

    public MathResult sequencial(Fraction... fractions) {
        return sequencial(List.of(fractions));
    }

    public MathResult minMultiploCommon(List<Fraction> fractions) {
        init();
        long start = System.currentTimeMillis();
        int tam = fractions.size();
        System.out.println("arithmeticSum:minMultiploCommon");
        fractionSum.setMethod(arithmeticParams.getMethodFractionSum());
        if(tam == 0) return MathResult.builder().build();
        if(tam == 1) return ArithmeticPartStepMethos.of(fractions.get(0));
        
        final long den = fractions.get(0).getDenominator();
        boolean denEquals = fractions.stream().allMatch(frac -> frac.getDenominator().equals(den));
        if(denEquals) return ArithmeticUtil.commun(fractions);
        
        if(tam == 2) return fractionSum.solve(fractions.get(0), fractions.get(1));
        steps.add(ArithmeticFormatter.start(fractions,0));
        long mmc = ArithmeticUtil.mmc(fractions);
        steps.add(ArithmeticFormatter.stepOneCrossSystem(fractions, mmc));
        steps.add(ArithmeticFormatter.stepTwoCrossSystem(fractions, mmc));
        steps.add(ArithmeticFormatter.stepThreeCrossSystem(fractions, mmc,"+"));
        steps.add(ArithmeticFormatter.stepFourCrossSystem(fractions, mmc));
        Fraction fraction = ArithmeticUtil.getFractionMMC(fractions, mmc);
        ArithmeticPartStepMethos.simplify(steps, fraction, 5);
        long end = System.currentTimeMillis();
        return MathResultUtil.finishOperation(steps, fraction, new ArithmeticSum(), start, end);
    }
    
    public MathResult minMultiploCommon(Fraction... fractions) {
        return minMultiploCommon(List.of(fractions));
    }

    @Override
    public MathResult solve(String expression, String method) {
        init();
        expression = FractionUtil.gameSignal(expression);
        if(expression.matches(ArithmeticRegex.FRACTION))
            return ArithmeticPartStepMethos.of(FractionConverter.parse(expression));    
        if(!expression.matches("\\+?"+ArithmeticRegex.SUM)) 
           return MathResult.builder().build();
        if(expression.charAt(0) == '+') expression = expression.substring(1);
        fractionsOper = ArithmeticUtil.parseListFraction(expression, "\\+");
        return chooseMethod(fractionsOper, method);
    }
    
    @Override
    public MathResult solve(String expression) {
        return solve(expression,arithmeticParams.getMethodArithmeticSum());
    }    

  
    @Override
    public MathResult solve(List<Fraction> fractions) {
        return chooseMethod(fractions, arithmeticParams.getMethodArithmeticSum());
    }

  
    @Override
    public MathResult solve(Fraction... fractions) {
        return solve(List.of(fractions));
    }

    @Override
    public MathResult solve(ArithmeticParams params) {
        setArithmeticParams(params);
        return solve(params.getExpression());
    }
     
    private MathResult chooseMethod(List<Fraction> fractionsOper, String method) {
       if(method == null) return randomMethod(fractionsOper);
       return switch (method) {
            case METHOD_SEQUENCIAL -> sequencial(fractionsOper);
            case METHOD_MMC -> minMultiploCommon(fractionsOper);
            default -> randomMethod(fractionsOper);
        };
    }
    
    private MathResult randomMethod(List<Fraction> fractionsOper){
        boolean par = ThreadLocalRandom.current().nextInt(1, 10) % 2 == 0;
        return par ? sequencial(fractionsOper) : minMultiploCommon(fractionsOper);
    }
    
}
