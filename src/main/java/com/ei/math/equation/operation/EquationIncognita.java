package com.ei.math.equation.operation;

import com.ei.math.equation.Equation;
import com.ei.math.equation.EquationRegex;
import com.ei.math.equation.text.EquationFormatter;
import com.ei.math.equation.util.EquationUtil;
import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import java.util.ArrayList;
import java.util.List;

public class EquationIncognita {
   
   public static long partOne(long b){ return b * b; }
   public static long partTwo(long a,long c){ return 4 * a * c; }
   public static long delta(long a, long b, long c){ return partOne(b) - partTwo(a,c); }
   
    private void chooseCaseDelta(List<Step> steps, long a, long b, long c) {
        long delta = delta(a, b, c);
        if(delta == 0)
            deltaZero(steps, b, a);
        else if(delta < 0)
            deltaNegative(steps,delta);
        else{
            double sqrt = Math.sqrt(delta);
            String root = ""+sqrt;
            if(root.matches("\\d+\\.0")){
               deltaPositive(steps, delta, a, b,(long)sqrt);
            }else{
               deltaNegative(steps,delta);
            }
        }
    }    
    
    private void deltaZero(List<Step> steps, long b, long a){
        steps.add(EquationFormatter.deltaZeroForm());
        steps.add(EquationFormatter.deltaZeroOne(b,a));
        steps.add(EquationFormatter.deltaZeroTwo(b,a));
        steps.add(EquationFormatter.deltaZeroThree(b,a));
    }
    
    private void deltaNegative(List<Step> steps,long delta){
        steps.add(EquationFormatter.equationNotZeroFunction(delta));
    }
    
    private void deltaRaiz(List<Step> steps,long a, long b, long raiz,String signal,long num){
        steps.add(EquationFormatter.deltaPositiveRaizOne(b,raiz,a,signal,num));
        steps.add(EquationFormatter.deltaPositiveRaizTwo(b,raiz,a,signal,num));
        steps.add(EquationFormatter.deltaPositiveRaizThree(b,raiz,a,signal,num));
    }
     
    private void deltaPositive(List<Step> steps,long delta,long a, long b, long raiz){
        steps.add(EquationFormatter.deltaPositiveForm());
        steps.add(EquationFormatter.deltaPositiveOne(b,delta,a));
        steps.add(EquationFormatter.deltaPositiveTwo(b,raiz,a));
        deltaRaiz(steps, a, b, raiz, "+",1);
        deltaRaiz(steps, a, b, raiz, "-",2);
    }
    
    private void bhaskaraSteps(List<Step> steps, long a, long b, long c){
        steps.add(EquationFormatter.deltaForm());
        steps.add(EquationFormatter.deltaFormOne(a,b,c));
        steps.add(EquationFormatter.deltaFormTwo(a,b,c));
        steps.add(EquationFormatter.deltaFormThree(a,b,c));    
    }

    public MathResult bhaskara(long a, long b, long c){
        long start = System.currentTimeMillis();
        List<Step> steps = new ArrayList<>();
        bhaskaraSteps(steps, a, b, c);
        chooseCaseDelta(steps,a,b,c);
        long finish = System.currentTimeMillis();
        return MathResult.builder()
                .steps(steps)
                .className(getClass().getName())
                .timeMilliseconds(String.format("%s", finish - start))
                .method("bhaskara")
                .status(true)
                .pack(EquationIncognita.class.getSimpleName())
                .build();
    }    
    
    public MathResult resolve(String expression) {
        if (!expression.matches(EquationRegex.EQUATION_ZERO_INGONITA))
            return MathResult.builder().build();
        String[] split = expression.split("=");
        List<Equation> list = EquationUtil.convertToList(split[1]);
        System.out.println(list);
        long a = valueExpoent(list, 2);
        long b = valueExpoent(list, 1);
        long c = valueExpoent(list, 1,true);
        return bhaskara(a, b, c);
    }
    
    private long valueExpoent(List<Equation> list,int expoent, boolean partIdenpendent){
       return list.stream().filter(e -> e.getExpoent().getNumerator() == expoent 
               && (partIdenpendent ? e.getIncognita().equals("") : true) 
              ).findFirst() .map(e-> e.getCoefficient().getNumerator()).orElse(0l);
    }
    
    private long valueExpoent(List<Equation> list,int expoent){
        return valueExpoent(list, expoent, false);
    }
    
    /*
    public static void main(String[] args) {
        EquationIncognita equationIncognita = new EquationIncognita();
        equationIncognita.bhaskara(1, -5, 6);
    }
    */
    
}
