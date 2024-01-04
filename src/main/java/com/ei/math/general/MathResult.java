package com.ei.math.general;

import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionResult;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class MathResult{
    @Builder.Default
    private List<StepMap> stepGroups = new ArrayList<>();
    @Builder.Default
    private List<Step> steps = List.of();
    
    private String expression;
    private String  result;
    @Builder.Default
    private String  method="default";
    @Builder.Default
    private String className = "undefined";
    @Builder.Default
    private String pack = "undefined";    
    @Builder.Default
    private String timeMilliseconds = "undefined";
    @Builder.Default
    private boolean status = false;
    
    private Object object;
    
    public FractionResult fractionResult(){
        if(object instanceof Fraction) { 
            Fraction fraction = ((Fraction) object);
            FractionResult build = FractionResult.builder()
                    .steps(steps)
                    .status(status)
                    .expression(expression)
                    .fraction(fraction)
                    .build();
           
            return build;
        }
        return FractionResult.builder().build();
    }
    
    public MathResponse response(){
        return MathResponse.builder()
                .expression(expression)
                .className(className)
                .method(method)
                .pack(pack)
                .result(result)
                .status(status)
                .stepGroups(stepGroups)
                .steps(steps)
                .timeMilliseconds(timeMilliseconds)
                .build();
    }
    
}
