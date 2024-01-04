package com.ei.math.fraction.text;

import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.util.FractionPartStepMethods;

public class FractionText {
    
    public static String operationStepThreeCrossSystem(Fraction first, Fraction second) {
        String num = (first.getNumerator()*second.getDenominator()+
                     first.getDenominator()*second.getNumerator())+"";
        String den = (first.getDenominator()*second.getDenominator())+"";
        return num+"/"+den;
    }
    
    public static String operationStepTwoCrossSystem(Fraction first, Fraction second) {
        long n = first.getDenominator()*second.getNumerator();
        String num = first.getNumerator()*second.getDenominator()+(n > 0 ? "+"+n : n+"");
        String den = (first.getDenominator()*second.getDenominator())+"";
        return "("+num+")/"+den;
    }       
    
    public static String operationStepOneCrossSystem(Fraction first, Fraction second) {
        String sig = second.isPositive() ? "+" : "-";
        second = second.positive();
        String num = "("+first.getNumerator()+"*"+second.getDenominator()+sig+
                     first.getDenominator()+"*"+second.getNumerator()+")";
        String den = "("+first.getDenominator()+"*"+second.getDenominator()+")";
        return num+"/"+den;
    }    
    
    public static String operationStepFourMMC(Fraction first,Fraction second, long mmc,String sig){
        long num = FractionPartStepMethods.mmcSig(first, second, mmc, sig);
        return num+"/"+mmc;
    }
    
    public static String operationStepFourMMC(Fraction first,Fraction second, long mmc){
        return operationStepFourMMC(first, second, mmc, "+");
    }
    
    public static String operationStepThreeMMC(Fraction first,Fraction second, long mmc){
        String signal = second.isNegative() ? "-" : "+";
        second = second.positive();
        String num = "("+first.getNumerator()*(mmc/first.getDenominator())+signal
                        +second.getNumerator()*(mmc/second.getDenominator())+")";
        return num+"/"+mmc;
    }    
    
    public static String operationStepTwoMMC(Fraction first,Fraction second, long mmc){
        String signal = second.isNegative() ? "-" : "+";
        second = second.positive();
        String num = "("+first.getNumerator()+"*"+(mmc/first.getDenominator())+signal
                        +second.getNumerator()+"*"+(mmc/second.getDenominator())+")";
        return num+"/"+mmc;
    }
    
    public static String operationStepOneMMC(Fraction first,Fraction second, long mmc){
       return first.text(mmc)+second.text(mmc,true) ; 
   }
   
    private static String template(Fraction fraction,boolean signal,long mmc, boolean veriy,String sig){
       String sinal=  fraction.isNegative() ? "-" : "";
       if(signal && fraction.isPositive()) sinal = sig == null ? "+" : sig ;
       
       String denominator, parentes;
       fraction = fraction.isNegative() ? fraction.positive() : fraction;
       if(!veriy){
          denominator = !fraction.isInteger() ? "/"+fraction.getDenominator() : "";
          parentes= "";
       }else{
           denominator = "/" + ( fraction.isInteger() ? "1": fraction.getDenominator());
           parentes ="("+mmc/fraction.getDenominator()+")";
       }
       String numerator = fraction.getNumerator().toString();
       return sinal + numerator + denominator + parentes;
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

   public static String simply(Fraction fraction, long mdc){
       String num = fraction.getNumerator() +":"+ mdc;
       String den = fraction.getDenominator() +":"+ mdc;
       return num+"/"+den;
   }
   
   public static String join(Fraction first, Fraction second){
       return first.text()+second.text(true);
   }
   
   public static String join(Fraction first, Fraction second,String signal){
       return first.text()+second.text(true,signal);
   }
    
}
