package com.ei.math.equation.text;

import com.ei.math.general.Step;
import com.ei.math.equation.Equation;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.text.FractionText;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EquationText {

    public static String joinFunction(Step step, Equation function) {
     return "("+step.getText()+")"+function.simplyText();
    }

    public static String joinSumOrSub(Map<Equation, List<Equation>> maps, boolean equationFomm) {
        String join = "", expre, resul;
        for (Map.Entry<Equation, List<Equation>> entry : maps.entrySet()) {
             expre = entry.getValue().stream().map(e -> e.getCoefficient().text(true)).collect(Collectors.joining());
             if(expre.startsWith("+")) expre = expre.substring(1);
             String incog = entry.getKey().getIncognita();
             long exp = entry.getKey().getExpoent().getNumerator();
             if(equationFomm){
                resul = "("+expre+")"+("".equals(incog) ? "" : incog)+(exp > 1 ? "^"+exp : "");
                join += "".equals(join) ? resul : "+"+resul;
             }else{
                resul = "("+expre+")";
                join += "".equals(join) ? resul : ", "+resul;
             }
        }
        return join;
    }
    
    public static String joinSumOrSub(Map<Equation, List<Equation>> maps){
        return joinSumOrSub(maps, true);
    }

    public static String joinSumOrSub(List<Equation> list) {
        String join = "";
        for(int i = 0; i < list.size(); i++)
            join += list.get(i).text(i != 0);
        return join;
    }

    static String expressionIncoginita(List<Equation> list, String n) {
        String join = "";
        for(int i = 0; i < list.size(); i++)
            join += list.get(i).text(n, i != 0);
        return join;
    }

    static String incoginitaResult(List<Equation> list, String n) {
        String join = "";
        for(int i = 0; i < list.size(); i++){
            join += list.get(i).calculateExpoent(n, i != 0);
        }
        return join;
    }

    public static String deltaZeroForm(String b, String a) {
        return "-"+b+"/2"+a;
    }

    public static String deltaPositiveForm(String b, String delta, String a, boolean radic) {
        String radical = radic ? "^1/2" : "";
        return "(-("+b+")±("+delta+")"+radical+")/2"+a;
    }
    
     public static String deltaPositiveForm(String b, String delta, String a){
        return deltaPositiveForm(b, delta, a, true);
     }

    public static String deltaPositiveFraction(String b, String a,long num) {
        return "x"+num+"="+b+"/"+a;
    }
    
    public static String deltaPositiveFraction(long result,long num) {
        return "x"+num+"="+result;
    }   
    
    
    
}
