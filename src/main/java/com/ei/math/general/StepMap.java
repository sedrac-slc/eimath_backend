package com.ei.math.general;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StepMap implements Comparable<StepMap>{
    private String expression;
    private List<Step> steps; 
    
    public static StepMap of(String expression, List<Step> list) {
        return new StepMap(expression, list);
    }

    @Override
    public int compareTo(StepMap o) {
        return expression.compareTo(o.getExpression());
    }
    
    
    
}
