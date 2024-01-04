package com.ei.math.equation.text;

import com.ei.math.general.Step;
import com.ei.math.equation.Equation;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.text.FractionHtml;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EquationHtml {

    private static String functionFormat(String incog, Equation equation, boolean calculate, boolean first, boolean repre){
       Long coefficient = equation.getCoefficient().getNumerator();
       Long expoent = equation.getExpoent().getNumerator();
       String ingonita = equation.getIncognita();
       String sig;

       if(!first) { 
           sig = coefficient > 0 ? "<div class='signal'>+</div>" : "<div class='signal'>-</div>";
       }else{ 
           sig = coefficient > 0 ? "" : "<div class='signal'>-</div>";
       } 
       coefficient = Math.abs(coefficient);
       if(!repre){
            if(incog.matches("\\d+") && !calculate && !"".equals(ingonita)) 
             ingonita = (coefficient != 1 ? "<span class='sig-mult'>*</span>" : "")+incog;
       }else{
            ingonita = "("+(long)Math.pow(Long.parseLong(incog), expoent)+")";
            expoent = 1l;
       }
       return "<div class='equation'>"+sig+
                (coefficient != 1 ? "<div class='coefficient'>"+coefficient+"</div>" : "")+
                (!"".equals(ingonita) ? "<div class='ingonita'>"+ingonita+"</div>" : "")+
                (expoent != 1 ? "<div class='expoent'>"+expoent+"</div>" : "")+
               "</div>";
    }
    
    private static String functionFormat(String incog, Equation equation, boolean calculate, boolean first){
       return functionFormat(incog, equation, calculate, first, false);
    }
    
    private static String functionFormat(Equation equation, boolean signal, boolean first){
        return functionFormat("", equation, signal, first);
    }
    
    private static String functionFormat(Equation equation, boolean first){
        return functionFormat(equation, true, first);
    }
    
    private static String functionFormat(Equation equation){
        return functionFormat(equation, true);
    }
    
    private static String fractionGroupOne(Equation function , String html){
        return "<div class='fraction-arithmetic-group'>" +
                    "<div class='fraction-arithmetic-group-item'>"
                        +html+
                    "</div>"
                    + functionFormat(function)+
                "</div>";
    }
    
    private static String verifyType(Equation function, String html){
      return fractionGroupOne(function, html); 
    }
    
    public static String joinFunction(Equation function , Step step) {
        return verifyType(function, step.getHtml());
    }

    
    public static String joinFunction(List<Equation> list, String incoginita, boolean calculate, boolean first, boolean repre) {
       String html = list.stream().map(e -> {
            return e.htmlIncogita(incoginita, calculate, first,repre);
        }).collect(Collectors.joining());
       String join = "<div class='equation-arithmetic'>"+html+"</div>";
       String replaceOne = "<div class='equation-arithmetic'><div class='equation'><div class='signal'>+</div>";
       if(join.startsWith(replaceOne))
            join = join.replace(replaceOne, "<div class='equation-arithmetic'><div class='equation'>");
       return join;
    }
    
    public static String joinFunction(List<Equation> list, String incoginita, boolean calculate, boolean first){
        return joinFunction(list, incoginita, calculate, first, false);
    }
 
    public static String joinFunction(List<Equation> list, String incoginita, boolean calculate){
        return joinFunction(list, incoginita, calculate, false);
    }

    public static String html(String ingonita, Equation equation, boolean calculate,boolean first, boolean repre) {
        return functionFormat(ingonita,equation, calculate, first, repre);
    }
    
    public static String html(String ingonita, Equation equation, boolean calculate,boolean first){
        return html(ingonita, equation, calculate, first, false);
    }
    
    public static String html(Equation equation, boolean signal,boolean first) {
        return html("",equation, signal, first);
    }    
    
    public static String html(Equation equation, boolean signal){
        return html(equation, signal, false);
    }
    
    public static String html(Equation equation) {
        return html(equation, true,false);
    }
    
    private static String expreGroup(List<Equation> equations){
        int tam = equations.size();
        String join = "";
        for (int i = 0; i < tam; i++) {
            join += equations.get(i).getCoefficient().html( i != 0);
        }
        return join;
    }

    static String joinSumOrSub(Map<Equation, List<Equation>> maps) {
        String join = "", expre, resul;
        for (Map.Entry<Equation, List<Equation>> entry : maps.entrySet()) {
             expre = expreGroup(entry.getValue());
             String incog = entry.getKey().getIncognita();
             long exp = entry.getKey().getExpoent().getNumerator();
             resul = "<div class='equation-arithmetic'>"+expre+"</div>";
             if(!"".equals(incog) && exp > 1) 
                 resul = "<div class='equation-item'>"+resul+"<div class='incoginita'>"+incog+"</div><div class='expoent'>"+exp+"</div></div>";
             if(!"".equals(incog)&& exp < 2) 
                 resul = "<div class='equation-item'>"+resul+"<div class='incoginita'>"+incog+"</div></div>";             
             join += "".equals(join) ? resul : "<div class='signal'>+</div>"+resul;
        }
        return "<div class='equation-join'>"+join+"</div>";
    }

    public static String joinSumOrSub(List<Equation> list) {
        return joinFunction(list, "", false, false);
    }

    public static String expressionIncoginita(List<Equation> list, String n) {
        return joinFunction(list, n, false);
    }

    public static String incoginitaResult(List<Equation> list, String n) {
        return joinFunction(list, n, false,false, true);
    }

    public static String deltaZeroForm(String b, String a) {
        return "<div class=\"equation-delta-dflex\">"
                    +"<div>x = </div>"
                    +"<div class=\"equation-delta-dflex-fr\">"
                        +" <div class=\"equation-delta-b\">"+b+"</div>"
                    +"<div>2"+a+"</div>"
                    +"</div>"
               +"</div>";
    }

    public static String deltaPositiveForm(String b, String delta, String a, boolean radic, String sig, long num) {
        String radical =  radic ? " &radic;" : "";
        String signal = "".equals(sig) ? " &plusmn;" : sig;
        String n = num != 0 ? num+"" : "1,2";
        return "<div class=\"equation-delta-dflex\">" 
                +"<div>x<sub>"+n+"</sub> = </div>"
                    +"<div class=\"equation-delta-dflex-fr\">"
                        +"<div class=\"equation-delta-b\">-("+b+") "+signal+" "+radical+" "+delta+"</div>"
                        +"<div>"+a+"</div>"
                    +"</div>" 
               +"</div> ";
    }
    
    public static String deltaPositiveForm(String b, String delta, String a, boolean radic, String sig){
        return deltaPositiveForm(b, delta, a, radic, sig ,0);
    }
    
    public static String deltaPositiveForm(String b, String delta, String a, boolean radic){
        return  deltaPositiveForm(b, delta, a, radic, "");
    }
    
    public static String deltaPositiveForm(String b, String delta, String a){
        return deltaPositiveForm(b, delta, a, true);
    }

    public static String deltaPositiveFraction(String b, String a,long num) {
        String template = FractionHtml.template(b, a);
        return "<div class=\"equation-delta-dflex\"><div>x<sub>"+num+"</sub> =</div> %s <div/>".formatted(template);
    }
    
    public static String deltaPositiveFraction(long result,long num) {
        return "<div class=\"equation-delta-dflex\"><div>x<sub>"+num+"</sub> = %s</div><div/>".formatted(result);
    }       
    
}
