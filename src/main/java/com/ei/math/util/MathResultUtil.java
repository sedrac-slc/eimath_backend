package com.ei.math.util;

import com.ei.math.general.StepMap;
import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import com.ei.math.fraction.Fraction;
import java.util.List;

public class MathResultUtil {
    
    public static MathResult finishOperation(List<Step> list, Fraction fraction, Object cls, long start, long end) {
        return MathResult.builder()
                .steps(list)
                .expression(list.get(0).getText())
                .className(cls.getClass().getSimpleName())
                .timeMilliseconds("" + (end - start))
                .result(fraction.text())
                .object(fraction)
                .pack(cls.getClass().getPackageName())
                .status(true)
                .build();
    }
    
    public static MathResult finishOperation(List<StepMap> stepGroups , List<Step> list, Fraction fraction, Object cls, long start, long end) {
        return MathResult.builder()
                .stepGroups(stepGroups)
                .steps(list)
                .expression(list.get(0).getText())
                .className(cls.getClass().getSimpleName())
                .timeMilliseconds("" + (end - start))
                .result(fraction.text())
                .object(fraction)
                .pack(cls.getClass().getPackageName())
                .status(true)
                .build();
    }      
    
}
