package com.ei.math.service;

import com.ei.math.general.MathResult;
import com.ei.math.arithmetic.Arithmetic;
import com.ei.math.arithmetic.params.ArithmeticParams;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class ArithmeticService {
    Arithmetic arithmetic = new Arithmetic();

    public MathResult calculate(ArithmeticParams request) {
        return arithmetic.solve(request);
    }
    
}
