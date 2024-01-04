package com.ei.math.equation;

import com.ei.math.equation.text.EquationHtml;
import com.ei.math.equation.util.EquationUtil;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.FractionConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Equation implements Comparable<Equation>{
    @Builder.Default
    private Fraction coefficient = Fraction.of();
    @Builder.Default
    private String incognita = "";
    @Builder.Default
    private Fraction expoent = Fraction.of(1);
    
    public Equation of(Fraction coefficient, String ingonita, Fraction expooent){
        return  Equation.builder().coefficient(coefficient).incognita(ingonita).expoent(expoent).build();
    }
    
    public Equation of(Fraction coefficient, String ingonita){
        return  of(coefficient, ingonita, Fraction.of(1));
    }
    
    public Equation of(String ingonita, Fraction expooent){
        return  of(Fraction.of(1), ingonita, expooent);
    }

    public Equation of(String ingonita){
        return  of(Fraction.of(1), ingonita, Fraction.of(1));
    }    
    
   
    public String simplyText(){ return ""; }
   
    public String html(){
        return EquationHtml.html(this);
    }
      
    public String htmlIncogita(String ingonita, boolean calculate, boolean first, boolean repre){
        if(calculate){
           //coefficient = coefficient.pow(expoent.getNumerator());
        }
        return EquationHtml.html(ingonita,this, calculate, first, repre);
    }

    public String htmlIncogita(String ingonita, boolean calculate, boolean first){
        return htmlIncogita(ingonita, calculate, first, false);
    }    
    
    public String htmlIncogita(String ingonita, boolean calculate){
        return htmlIncogita(ingonita, calculate, false);
    }
    
    public String htmlIncogita(String ingonita){
        return htmlIncogita(ingonita, false);
    }
    
    public String text(boolean signal){
        boolean expoentIsHidden = expoent.equals(Fraction.of(1)) || expoent.equals(Fraction.of(0));
        boolean coefficientIsHidden = coefficient.equals(Fraction.of(1));  
        String sig = "", coefficientStr;
        String expoentStr = expoentIsHidden ? "" : "^"+expoent.getNumerator();
        String ingonitaStr = incognita.matches("[a-z]") ? incognita : "("+incognita+")";
        if(signal) sig = coefficient.isNegative() ? "-" : "+";
        if(expoentIsHidden && coefficientIsHidden) return sig+ingonitaStr;
        coefficientStr = coefficientIsHidden ? "" : coefficient.positive().text();
        return sig+coefficientStr+ingonitaStr+expoentStr;
    }
    
    public String text(String incoginita, boolean signal){
        Equation equation = this;
        equation.setIncognita(incoginita);
        return equation.text(signal);
    }

    public String text(String incoginita){
        return text(incoginita, false);
    }
    
    public String text(){
        return text(false);
    }
   
    public String calculateExpoent(String ingonita, boolean signal){
        return EquationUtil.calculateExpoent(this, ingonita,signal);
    }
    
    public Equation root() {
        return new Equation(Fraction.of(),incognita, expoent);
    }
    
    public Equation calculate() {
        if(!incognita.matches("\\d+")) return this;
        Fraction pow = FractionConverter.parse(incognita).pow(expoent.getNumerator());
        Fraction mult = coefficient.mult(pow);
        return new Equation(mult,"", Fraction.of());
    }    
       
   public boolean equalsFunction(Equation function){
       return incognita.equals(function.getIncognita()) && expoent.equals(function.getExpoent());
   }
       
   public boolean isNegative(){
       return coefficient.isNegative();
   }
   
    @Override
    public int compareTo(Equation equation) {
        return expoent.getNumerator().compareTo(equation.getExpoent().getNumerator());
    }

  
    
    
}
