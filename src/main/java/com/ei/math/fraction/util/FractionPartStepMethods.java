package com.ei.math.fraction.util;

import com.ei.math.general.MathResult;
import com.ei.math.general.Step;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.text.FractionFormatter;
import java.util.List;

public class FractionPartStepMethods {
    
  public static long mmcSig(Fraction first,Fraction second, long mmc,String sig){
        long num = 0;
        if(sig.equals("+"))
            num = first.getNumerator()*(mmc/first.getDenominator())+second.getNumerator()*(mmc/second.getDenominator());
        if(sig.equals("-"))
            num = first.getNumerator()*(mmc/first.getDenominator())-second.getNumerator()*(mmc/second.getDenominator());
        return num;
    }  
  
  public static long crossSig(Fraction first,Fraction second, String sig){
        long num = 0;
        if(sig.equals("+"))
            num = first.getNumerator()*second.getDenominator()+first.getDenominator()*second.getNumerator();
        if(sig.equals("-"))
            num = first.getNumerator()*second.getDenominator()-first.getDenominator()*second.getNumerator();
        return num;          
    }
  
  public static long numeratorsSumOrSub(Fraction first,Fraction second,String sig){
        long num = 0;
        if(sig.equals("+"))
            num = first.getNumerator()+second.getNumerator();
        if(sig.equals("-"))
            num = first.getNumerator()+second.getNumerator();
        return num;
    }  
    
  public static MathResult baseCase(Object cls, List<Step> list,Fraction first, Fraction second,String signal){
        long start = System.currentTimeMillis();
        if(signal.equals("-")) second = second.isPositive() ? second.negative() : second;
        list.add(FractionFormatter.startSumOrSub(first,second,"step.methd.sum.sub"));
        list.add(FractionFormatter.stepOneDenominatorEquals(first, second,signal));
        list.add(FractionFormatter.stepTwoDenominatorEquals(first, second,signal));
        
        long num = numeratorsSumOrSub(first, second, signal);
        Fraction fraction = Fraction.of(num,second.getDenominator());
        FractionPartStepMethods.simplify(list, fraction, 3, 4);
        long end = System.currentTimeMillis();
        return FractionUtil.finishOperation(list, fraction, cls,"step.methd.sum.sub", start, end);
        
    }    
  
  public static String numeratorMultDenominator(Fraction first,Fraction second, boolean isNumeratorOrDenominator){
       String sig = FractionUtil.isPositive(first,second)? "" : "-";
       first = first.positive();
       second = second.positive();
      return isNumeratorOrDenominator
       ? sig+first.getNumerator()+"*"+second.getNumerator()
       : first.getDenominator()+"*"+second.getDenominator();
  }
  
  public static String numeratorMultDenominatorOper(Fraction first,Fraction second, boolean isNumeratorOrDenominator){
      return isNumeratorOrDenominator
       ? first.getNumerator()*second.getNumerator()+""
       : first.getDenominator()*second.getDenominator()+"";
  } 
  
    
   public static void simplify(List<Step> list, Fraction fraction, int posSimply, int posFinish) {
        if(!fraction.isIrreducible()){
            list.add(FractionFormatter.stepSimplify(fraction, posSimply));
            fraction = fraction.simplify();
            list.add(FractionFormatter.finish(fraction,posFinish));
        }
    }  
  
}
