package com.ei.math.radical.util;

import com.ei.math.fraction.Fraction;
import com.ei.math.general.Step;
import com.ei.math.radical.Radical;
import com.ei.math.radical.text.RadicalFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

public class RadicalUtil {
    
    private static boolean isPrime(long numero){
        if (numero <= 1)  return false;
        for (int i = 2; i <= Math.sqrt(numero); i++) 
            if (numero % i == 0) return false;
        return true;
    }

    private static Radical caseOne(Map<Long,Long> reaptDivisors, Radical radical, long raiz, List<Step> steps){
        return caseOne(reaptDivisors, radical, raiz, steps, true);
    }
    
    private static Radical caseOne(Map<Long,Long> reaptDivisors, Radical radical, long raiz, List<Step> steps, boolean insertStep){
        int index = 0;
        for (Map.Entry<Long, Long> entry : reaptDivisors.entrySet()) {
            if (index == 0) {
                if(entry.getValue() == 1 || entry.getValue() < raiz) return radical;
                if(entry.getValue() == raiz) {
                    Fraction mult = Fraction.of(entry.getKey()).mult(radical.getCoefficient());
                    Radical of = Radical.of(mult, Fraction.of(1), Fraction.of(0));
                    if(insertStep) steps.add(RadicalFormatter.reduceStep(radical, of));
                    return of;
                }
                if(entry.getValue() > raiz) {
                    if(entry.getValue() % raiz == 0){
                        Fraction pow = Fraction.of(entry.getKey()).pow(entry.getValue() / raiz);
                        Fraction mult = pow.mult(radical.getCoefficient());
                        Radical of = Radical.of(mult, Fraction.of(1), Fraction.of(0));
                        if(insertStep) steps.add(RadicalFormatter.reduceStep(radical, of));
                        return of;
                    }else{
                       long expoent = entry.getValue() / raiz;
                       long baseExpoent = entry.getValue() - expoent * raiz;
                       Fraction base = Fraction.of(entry.getKey()).pow(baseExpoent);
                       Fraction pow = Fraction.of(entry.getKey()).pow(expoent);
                       Fraction mult = pow.mult(radical.getCoefficient());
                       Radical of = Radical.of(mult, base, radical.getExpoent());                        
                       if(insertStep) steps.add(RadicalFormatter.reduceStep(radical, of));
                       return of;
                    }
                }
                break;
            }
            index++;
        }
        return radical;
    }
    
    private static void refletOne(Map<Boolean, Map<Long, Long>> collect, Radical initRadical, long expoent, boolean status, List<Step> steps){
        collect.get(status).forEach((key,value)->{
            
           Radical caseOne = caseOne(new HashMap<>(){{
               put(key, value);
           }}, Radical.of(1, (long) Math.pow(key, value)), expoent, steps, false);
           
           initRadical.setCoefficient(initRadical.getCoefficient().mult(caseOne.getCoefficient()));
           initRadical.setBase(initRadical.getBase().mult(caseOne.getBase()));
           
        });    
    }
    /*
    public static Radical reduce(Radical radical){
        return reduce(radical, new ArrayList<>());
    }
    */
    public static Radical reduce(Radical radical, List<Step> steps) {
        long base = radical.getBase().getNumerator();
        List<Long> divisors = List.of(2l,3l,4l,5l,6l,7l,8l,9l);
        List<Long> results = new ArrayList<>();
        if(isPrime(base)) return radical;
        
        while(true){
            for (Long divisor: divisors) {
                if(base % divisor == 0){
                    long result = base / divisor;
                    results.add(divisor);
                    base = result;
                   break;
                }
            }
            if(base <= 1) break;
        }
        
        long expoent = radical.getExpoent().getDenominator();
        
        Map<Long,Long> reaptDivisors = results.stream().distinct().sorted()
                .collect(Collectors.toMap(f -> f, f -> results.stream().filter(p -> p.equals(f)).count() ));
        
        if(reaptDivisors.size() == 1)
            return caseOne(reaptDivisors,radical, expoent, steps);
        
        Map<Long, Long> divisorsThebExpoent = reaptDivisors.entrySet().stream().filter(entry -> entry.getValue() >= expoent)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        
        Map<Boolean, Map<Long, Long>> collect = reaptDivisors.entrySet().stream().collect(Collectors.partitioningBy(
                e -> divisorsThebExpoent.containsKey(e.getKey()),
                Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
        ));

        Radical initRadical = new Radical(Fraction.of(1),Fraction.of(1),Fraction.of(expoent));
        
        refletOne(collect, initRadical, expoent, true, steps);
        refletOne(collect, initRadical, expoent, false, steps);
        
        initRadical.setCoefficient(initRadical.getCoefficient().mult(radical.getCoefficient()));
        
        return initRadical;
    }
       
}
