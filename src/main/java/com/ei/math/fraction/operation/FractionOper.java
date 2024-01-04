package com.ei.math.fraction.operation;

import com.ei.math.general.MathOper;
import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import com.ei.math.fraction.Fraction;
import java.util.ArrayList;
import java.util.List;
import lombok.Setter;
/**
 *{@code FractionOper} is the super class for the operation classes (FractionSum, FractionSub, FractionMult, FractionDiv)
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
@Setter
public abstract class FractionOper implements MathOper{
    protected List<Step> list;
    protected boolean verifyRegex = true;
    
    {list = new ArrayList<>();}
    
    protected  void init(){
        list = new ArrayList<>();
    }
    
    public abstract MathResult solve(Fraction first, Fraction second);
    public abstract MathResult solve(Fraction first, Fraction second,String method);
    @Override
    public abstract MathResult solve(String expression, String method);
    @Override
    public abstract MathResult solve(String expression);
    
}
