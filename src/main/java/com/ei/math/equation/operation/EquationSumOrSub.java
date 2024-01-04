package com.ei.math.equation.operation;

import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import com.ei.math.arithmetic.operator.ArithmeticSumOrSub;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.equation.Equation;
import com.ei.math.equation.EquationRegex;
import com.ei.math.equation.text.EquationFormatter;
import com.ei.math.equation.util.EquationUtil;
import com.ei.math.general.StepMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class ResultMap {

    private Step step;
    private Integer index;
    private boolean integers; 
    
    public ResultMap(Step step, Integer index){
        this.step = step;
        this.index= index;
        this.integers = false;
    }
    
}

@Data
public class EquationSumOrSub {

    private ArithmeticSumOrSub arithmeticSumOrSub;
    private ArithmeticParams arithmeticParams;

    {
        arithmeticParams = new ArithmeticParams();
        arithmeticSumOrSub = new ArithmeticSumOrSub();
    }

    public MathResult resolve(Equation functionOne, Equation functionTwo) {
        arithmeticSumOrSub.setListGeneratorExpression(true);
        if (functionOne.equalsFunction(functionTwo)) {
            return arithmeticSumOrSub.solve(functionOne.getCoefficient(), functionTwo.getCoefficient());
        }
        return null;
    }

    public MathResult resolve(List<Equation> functions) {
        arithmeticSumOrSub.setArithmeticParams(arithmeticParams);
        arithmeticSumOrSub.setListGeneratorExpression(true);
        long start = System.currentTimeMillis();
        Collections.sort(functions, (x ,y)-> y.compareTo(x) );
        Map<Equation, List<Equation>> maps = functions.stream().collect(Collectors.groupingBy(
                Equation::root, () -> new TreeMap<>((x ,y)-> y.compareTo(x)), Collectors.toList()
        ));

        Map<Equation, MathResult> results = new TreeMap<>((x ,y)-> y.compareTo(x));
        List<StepMap> stepMaps = new ArrayList<>();
        
        maps.entrySet().forEach(item -> {
            MathResult solve = arithmeticSumOrSub.solve(item.getValue().stream().map(Equation::getCoefficient).collect(Collectors.toList()));
            results.put(item.getKey(), solve);
            stepMaps.add( new StepMap(solve.getExpression(), solve.getSteps() ) );
        });        
        List<ResultMap> resultMaps = new ArrayList<>();
        final int tam = results.entrySet().stream().map(mapR -> mapR.getValue().getSteps().size()).reduce(0, (x, y) -> x > y ? x : y);

        results.entrySet().forEach((var item) -> {
            List<Step> steps = item.getValue().getSteps();
            int size = steps.size();
            boolean integers = steps.stream().allMatch(step -> !step.getHtml().contains("denominator"));
            for (int i = 0; i < tam; i++) {
                if (i < size) {
                    Step step = EquationFormatter.joinStepForFunction(item.getKey(), steps.get(i));
                    resultMaps.add(new ResultMap(step, i, integers));
                }else{
                    Step step = EquationFormatter.joinStepForFunction(item.getKey(), steps.get(size-1));
                    resultMaps.add(new ResultMap(step, i, integers));
                }
            }
        });

        Map<Integer, List<ResultMap>> resultsGroupy = resultMaps.stream().collect(Collectors.groupingBy(ResultMap::getIndex));

        List<Step> steps = new ArrayList<>();
        steps.add(EquationFormatter.startExpressionSumOrSub(functions));
        steps.add(EquationFormatter.joinSumOrSub(maps));
        resultsGroupy.entrySet().forEach((var item) -> {
            List<Step> collect = item.getValue().stream().map(ResultMap::getStep).collect(Collectors.toList());
            String text = collect.stream().map(Step::getText).collect(Collectors.joining("+"));
            String html = collect.stream().map(Step::getHtml).collect(Collectors.joining("<div>+</div>"));
            Step step = EquationFormatter.joinFunctionGroup(text, html);
            step.setCodigo(steps.size() + 1);
            steps.add(step);
        });
        
        long finish = System.currentTimeMillis();
        return MathResult.builder()
                .steps(steps)
                .className(getClass().getName())
                .timeMilliseconds(String.format("%s", finish - start))
                .method("grouping")
                .status(true)
                .stepGroups(stepMaps)
                .pack(EquationSumOrSub.class.getSimpleName())
                .build();
    }

    public MathResult resolve(Equation... functions) {
        return resolve(List.of(functions));
    }

    public MathResult resolve(String expression) {
        if (!expression.matches(EquationRegex.SUM_SUB)) {
            return MathResult.builder().build();
        }
        return resolve(EquationUtil.convertToList(expression));
    }
    /*
    public static void main(String[] args) {
        EquationSumOrSub equationSumOrSub = new EquationSumOrSub();
        equationSumOrSub.resolve("3x^2+5x+x^2-2x");
    }
    */
}
