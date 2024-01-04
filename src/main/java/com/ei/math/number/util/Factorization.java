package com.ei.math.number.util;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Factorization {
    public static final String FORMAT_DIVISORS = "format_divisors";
    public static final String FORMAT_DIVISORS_NUMB = "format_divisors_nuumb";

    private Long number;
    @Builder.Default
    private Map<Long, Long> parOperator = new LinkedHashMap<>();

    private void init() {
        parOperator = new LinkedHashMap<>();
    }

    public Factorization(long number) {
        this.number = number;
    }

    private Factorization(Long number, Map<Long, Long> parOperator) {
        this(number);
        this.parOperator = parOperator;
    }

    public void resolve(long number) {
        init();
        long num = number;
        long i = 2;
        setNumber(number);
        while (i < 11) {
            if (num % i == 0) {
                long div = num / i;
                parOperator.put(num, i);
                num = div;
                i = 2;
            } else {
                i++;
            }
        }

        if (!NumberPrime.is(num)) {
            i = num - 1;
            while (i > 1) {
                if (num % i == 0) {
                    long div = num / i;
                    parOperator.put(num, i);
                    num = div;
                    i = div - 1;
                } else {
                    i--;
                }
            }
        }
        
        parOperator.put(num, num);
        if(num != 1) parOperator.put(1L, 1L);
    }

    public void resolve() {
        resolve(number);
    }

    public List<Long> divisors(){
        return parOperator.entrySet().stream().map(Map.Entry::getValue)
                .distinct()
                .collect(Collectors.toList());
    }
    
    private List<Long> divisorsRepeat(){
        return parOperator.entrySet().stream().map(Map.Entry::getValue)
                .collect(Collectors.toList());
    }
    
    public Map<Long, Long> parDivisors(){
        Map<Long, Long> groups = new LinkedHashMap<>();
        
        divisors().forEach(item->{
           groups.put(item, divisorsRepeat().stream().filter(obj-> obj.equals(item)).count());
        });
        
        return groups;
    }
    
    private String format(Function<Map.Entry<Long,Long> , String> function,String sig){
        return parDivisors().entrySet().stream()
                .filter(item -> !item.getKey().equals(1))
                .map(function).collect(Collectors.joining(sig));
    }
       
    public String format(String code, String sig){
        Function<Map.Entry<Long,Long>,String> function=o->o.getKey()+(o.getValue()==1? "":"^"+o.getValue());
        if(code.equals(FORMAT_DIVISORS))
            return format(function, sig);
        return number+"="+format(function,sig);
    }
    
    public String format(String code){
        return format(code, "x");
    }
    
    public String format(){ return format(FORMAT_DIVISORS);}
    
}
