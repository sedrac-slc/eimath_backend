package com.ei.math.service;

import com.ei.math.equation.EquationRegex;
import com.ei.math.equation.operation.EquationCalculateIncoginita;
import com.ei.math.equation.operation.EquationIncognita;
import com.ei.math.equation.operation.EquationSumOrSub;
import com.ei.math.general.MathResult;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class EquationService {
    EquationSumOrSub equationSumOrSub = new EquationSumOrSub();
    EquationIncognita equationIncognita = new EquationIncognita();
    EquationCalculateIncoginita equationCalculateIngonita = new EquationCalculateIncoginita();
    
    public MathResult calculate(String expression) {
        if(expression.matches(EquationRegex.EQUATION_ZERO_INGONITA))
            return equationIncognita.resolve(expression);              
        if(expression.matches(EquationRegex.EQUATION_CALCULATE_INGONITA))
            return equationCalculateIngonita.resolve(expression);  
        return equationSumOrSub.resolve(expression);
    }    
    
}
