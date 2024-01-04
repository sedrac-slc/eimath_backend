package com.ei.math.fraction.text;

import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.util.FractionPartStepMethods;

public class FractionHtml {
    
    public static String operationStepThreeCrossSystem(Fraction first, Fraction second) {
        String num = (first.getNumerator()*second.getDenominator()+
                     first.getDenominator()*second.getNumerator())+"";
        String den = (first.getDenominator()*second.getDenominator())+"";
        return template(num, den);
    }    
    
    public static String operationStepTwoCrossSystem(Fraction first, Fraction second) {
        long n = first.getDenominator()*second.getNumerator();
        String num = first.getNumerator()*second.getDenominator()+(n > 0 ? "+"+n : n+"");
        String den = (first.getDenominator()*second.getDenominator())+"";
        return template(num, den);
    }
    
   public static String operationStepOneCrossSystem(Fraction first, Fraction second) {
        String sig = second.isPositive() ? "+" : "-";
        second = second.positive();
        String num = first.getNumerator()+"*"+second.getDenominator()+sig+
                     first.getDenominator()+"*"+second.getNumerator();
        String den = first.getDenominator()+"*"+second.getDenominator();
        return template(num, den);
    }    
    
   public static String operationStepFourMMC(Fraction first,Fraction second, long mmc,String sig){
        long num = FractionPartStepMethods.mmcSig(first, second, mmc, sig);
        return template(num+"", mmc+"");
    }   
   
    public static String operationStepFourMMC(Fraction first,Fraction second, long mmc){
        return operationStepFourMMC(first, second, mmc, "+");
    }   
    
   public static String operationStepThreeMMC(Fraction first,Fraction second, long mmc){
        String signal = second.isNegative() ? "-" : "+";
        second = second.positive();
        String numerator = "<div class='numerator'>("
            +first.getNumerator()*(mmc/first.getDenominator())+signal
            +second.getNumerator()*(mmc/second.getDenominator())
        +")</div>";
        String denominator = "<div class='separator'></div>"
                + "<div class='denominator'>"+mmc+"</div>";
        return "<div class='fraction'>"
               +numerator + denominator
             + "</div>";
    }    
   
   public static String operationStepTwoMMC(Fraction first,Fraction second, long mmc){
        String signal = second.isNegative() ? "-" : "+";
        second = second.positive();
        String numerator = "("
            +first.getNumerator()+"*"+(mmc/first.getDenominator())+signal
            +second.getNumerator()+"*"+(mmc/second.getDenominator())
        +")";
        return template(numerator, mmc+"");
    }    
    
   public static String operationStepOneMMC(Fraction first,Fraction second, long mmc){
       return "<div class='fraction-arithmetic'>"
                +first.html(mmc)+second.html(mmc,true)+
               "</div>"; 
   }
       
   private static String type(Fraction fraction){
       if(fraction.isApparent()) return "apparent";
       if(fraction.isImproper()) return "improper";
       return "proper";
   }
   
   private static String template(Fraction fraction,boolean signal,long mmc, boolean veriy, String sig){
       String sinal=  fraction.isNegative() ? "<div class='signal minus'>-</div>" : "";
       String sigDefault = "plus";
       
       if(sig != null && sig.equals("*")) sigDefault = "mult";
       if(sig != null && sig.equals(":")) sigDefault = "div";
       
       if(signal && fraction.isPositive())
            sinal = "<div class='signal "+sigDefault+"'>"+(sigDefault.equals("plus") ? "+": sig)+"</div>" ;
   
       String denominator, parentes;
       fraction = fraction.isNegative() ? fraction.positive() : fraction;
       if(!veriy){
        denominator = !fraction.isInteger() 
          ?  "<div class='separator'></div>" 
          +  "<div class='denominator'>"+fraction.getDenominator()+"</div>"
          : "";
          parentes= "";
       }else{
           denominator = "<div class='separator'></div>" + ( fraction.isInteger() 
                ? "<div class='denominator'>1</div>"
                : "<div class='denominator'>"+fraction.getDenominator()+"</div>");
           parentes ="<div class='mmc'>("+mmc/fraction.getDenominator()+")</div>";
       }

       String numerator = "<div class='numerator'>"+fraction.getNumerator()+"</div>";
       
       return "<div class='fraction "+type(fraction)+"'>"
               + sinal + numerator + denominator + parentes
             + "</div>";
   }
   
   public static String template(Fraction fraction,long mmc,boolean signal){
       return template(fraction,signal,mmc,true,null);
   }
   
   public static String template(Fraction fraction,long mmc){
       return template(fraction,false,mmc,true,null);
   }
   
   public static String template(Fraction fraction,boolean signal){
       return template(fraction,signal,0,false,null);
   }
   
   public static String template(Fraction fraction,boolean signal,String sig){
       return template(fraction,signal,0,false,sig);
   }
   
   public static String template(Fraction fraction){
      return template(fraction, false);
  }
  
   public static String template(String numerator, String denominator){
       return "<div class='fraction'>"+
                "<div class='numerator'>"+numerator+"</div>"+
                "<div class='separator'></div>"+
                "<div class='denominator'>"+denominator+"</div>"+
              "</div>";
   }
   
   public static String template(String numerator, String denominator, String cls){
       return "<div class='fraction "+cls+"'>"+
                "<div class='numerator'>"+numerator+"</div>"+
                "<div class='separator'></div>"+
                "<div class='denominator'>"+denominator+"</div>"+
              "</div>";
   }   
   
      
   public static String simply(Fraction fraction, long mdc){
       String num = fraction.getNumerator() +":"+ mdc;
       String den = fraction.getDenominator() +":"+ mdc;
       return template(num, den);
   }
   
   public static String join(Fraction first, Fraction second){
       return "<div class='fraction-arithmetic'>"
               +first.html()+second.html(true)+
              "</div>";
   }
   
      public static String join(Fraction first, Fraction second, String signal){
       return "<div class='fraction-arithmetic'>"
               +first.html()+second.html(true,signal)+
              "</div>";
   }
   
   
   
}
