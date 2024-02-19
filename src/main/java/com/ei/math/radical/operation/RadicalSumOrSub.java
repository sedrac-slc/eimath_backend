package com.ei.math.radical.operation;

import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import com.ei.math.arithmetic.operator.ArithmeticSumOrSub;
import com.ei.math.arithmetic.params.ArithmeticParams;
import com.ei.math.fraction.Fraction;
import com.ei.math.general.StepMap;
import com.ei.math.radical.Radical;
import com.ei.math.radical.RadicalConverter;
import com.ei.math.radical.RadicalRegex;
import com.ei.math.radical.RadicalRoot;
import com.ei.math.radical.text.RadicalFormatter;
import com.ei.math.radical.util.RadicalUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
public class RadicalSumOrSub {

    private ArithmeticSumOrSub arithmeticSumOrSub;
    private ArithmeticParams arithmeticParams;

    {
        arithmeticParams = new ArithmeticParams();
        arithmeticSumOrSub = new ArithmeticSumOrSub();
    }

    public List<Radical> reduce(List<Radical> list, List<Step> steps) {
        List<Radical> radicals = new ArrayList<>();
        list.forEach(radical -> radicals.add(RadicalUtil.reduce(radical, steps)) );
        return radicals;
    }    
    
    private void radicalRootEqual(List<Radical> radicals, RadicalRoot root, List<Step> steps) {
        List<Fraction> coefficients = radicals.stream().map(Radical::getCoefficient).toList();
        MathResult solve = arithmeticSumOrSub.solve(coefficients);
        solve.getSteps().forEach( step -> {
            steps.add(RadicalFormatter.joinRadicalRoot(step,root));
        });
    }    

    public MathResult resolve(List<Radical> radicals) {
        long start = System.currentTimeMillis();
        arithmeticSumOrSub.setArithmeticParams(arithmeticParams);
        arithmeticSumOrSub.setListGeneratorExpression(true);
        
        List<Step> steps = new ArrayList<>();
        List<StepMap> stepMaps = new ArrayList<>();
        
        radicals = reduce(radicals, steps);
        
        Map<RadicalRoot, List<Radical>> collect = radicals.stream().collect(Collectors.groupingBy(Radical::root));
  
        if(collect.size() == 1){
            for (Map.Entry<RadicalRoot, List<Radical>> entry : collect.entrySet()) 
                radicalRootEqual(entry.getValue(),entry.getKey(), steps);
        }else{
            arithmeticSumOrSub.setListGeneratorExpression(true);
            List<Radical> result = collect.entrySet().stream().map(entry -> {
                MathResult solve = arithmeticSumOrSub.solve(
                    entry.getValue().stream().map(Radical::getCoefficient).toList()
                );
                Fraction combinedCoefficient = solve.fractionResult().getFraction();
                stepMaps.add(new StepMap(solve.getExpression(), solve.getSteps()));
                return Radical.of(combinedCoefficient, entry.getKey().getBase(), entry.getKey().getExpoent());
            }).toList();
            
            if(!stepMaps.isEmpty()){
                steps.addAll(stepMaps.get(0).getSteps());
                stepMaps.stream().skip(1).forEach(stepMap -> {
                    steps.add(RadicalSumOrSub.separator());
                    steps.addAll(stepMap.getSteps());
                 });
            }
            
            steps.add(RadicalFormatter.finish(result));
        }
        
        long finish = System.currentTimeMillis();
        return MathResult.builder()
                .steps(steps)
                .stepGroups(stepMaps)
                .className(getClass().getName())
                .timeMilliseconds(String.format("%s", finish - start))
                .method("grouping")
                .pack(getClass().getPackageName())
                .status(true)
                .build();
    }

    public MathResult resolve(Radical... radicals) {
        return resolve(List.of(radicals));
    }

    public MathResult resolve(String expression) {
        if (!expression.matches(RadicalRegex.SUM_SUB)) {
            return MathResult.builder().build();
        }
        List<Radical> list = Arrays.stream(expression.split("[\\+|\\-]")).map(RadicalConverter::parse).toList();
        return resolve(list);
    }
   
    private static Step separator(){
        String message = "------------------------------------------------------";
        return Step.builder().text(message).html(message).build();
    }
   
    
}
