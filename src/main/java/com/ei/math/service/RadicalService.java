package com.ei.math.service;

import com.ei.math.general.MathResult;
import com.ei.math.radical.operation.RadicalSumOrSub;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class RadicalService {
    RadicalSumOrSub radicalSumOrSub = new RadicalSumOrSub();

    public MathResult calculate(String expression) {
        return radicalSumOrSub.resolve(expression);
    }
    
}
