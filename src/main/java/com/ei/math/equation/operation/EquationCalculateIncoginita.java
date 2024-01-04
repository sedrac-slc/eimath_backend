package com.ei.math.equation.operation;

import com.ei.math.arithmetic.operator.ArithmeticSumOrSub;
import com.ei.math.equation.Equation;
import com.ei.math.equation.EquationRegex;
import com.ei.math.equation.text.EquationFormatter;
import com.ei.math.equation.util.EquationUtil;
import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import java.util.ArrayList;
import java.util.List;

public class EquationCalculateIncoginita {
    
    private ArithmeticSumOrSub arithmeticSumOrSub;

    {
        arithmeticSumOrSub = new ArithmeticSumOrSub();
    }
    
    public MathResult resolve(String expression) {
        if (!expression.matches(EquationRegex.EQUATION_CALCULATE_INGONITA))
            return MathResult.builder().build();
        long start = System.currentTimeMillis();
        String[] split = expression.split("=");
        String n = split[0].replace("f(", "").replace(")", "");
        List<Equation> list = EquationUtil.convertToList(split[1]);
        List<Step> steps = new ArrayList<>();
        steps.add(EquationFormatter.startExpressionSumOrSub(list));
        steps.add(EquationFormatter.expressionIncoginita(list, n));
        steps.add(EquationFormatter.incoginitaResult(list, n));
        list = EquationUtil.calculate(list);
        steps.add(EquationFormatter.startExpressionSumOrSub(list));
        MathResult arithmeticSumOrSubSolve = arithmeticSumOrSub.solve(list.stream().map(Equation::getCoefficient).toList());
        steps.addAll(arithmeticSumOrSubSolve.getSteps());
        long finish = System.currentTimeMillis();
        return MathResult.builder()
                .steps(steps)
                .className(getClass().getName())
                .timeMilliseconds(String.format("%s", finish - start))
                .method("grouping")
                .status(true)
                .build();
    }
/*    
    public static void main(String[] args) {
        EquationCalculateIncoginita equationCalculateIngonita = new EquationCalculateIncoginita();
        equationCalculateIngonita.resolve("f(2)=3x^2+5x+x^2-2x");
    }
*/   
}
