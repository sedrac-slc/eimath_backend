package com.ei.math.fraction.text;

import com.ei.math.general.Step;
import com.ei.math.fraction.Fraction;
import com.ei.math.fraction.registory.FractionMessage;
import com.ei.math.fraction.util.FractionPartStepMethods;
import java.util.Locale;
/**
 *{@code FractionFormatter} is a class that contains methods that generate the 
 * steps for solving fraction operations
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
final public class FractionFormatter {
    public static final  FractionMessage message;
    
    static{
        message = new FractionMessage();
    }
    
    public static void setLocale(Locale locale){
        message.setLocale(locale);
    }
    
    /**
     * Returns the expression of a fraction addition or subtraction operation
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * <pre>{
     * String msg = String.format(message.getString("step.start"));
     * }</pre>
     * @param methodCodigResourceBlunde
     * @return  (new Step()).toBuilder().text(fraction.text()).html(fraction.html()).message(msg).codigo(pos).build();
     */
    public static Step startSumOrSub(Fraction first, Fraction second, String methodCodigResourceBlunde){
       String text = FractionText.join(first, second);
       String html = FractionHtml.join(first, second);
       String msgMethod = message.getString(methodCodigResourceBlunde);
       String msg = String.format(message.getString("step.frac.start"),text, msgMethod);
       return Step.builder().text(text).html(html).message(msg).codigo(0).build();
    }  
    

    
    /**
     * Returns the expression of a fraction multiplecation or division operation
     * @param first {@code Fraction} first fraction
     * @param second {@code Fraction} second fraction
     * <pre>{
     * String msg = String.format(message.getString("step.start"));
     * }</pre>
     * @param signal
     * @return  (new Step()).toBuilder().text(fraction.text()).html(fraction.html()).message(msg).codigo(pos).build();
     */
    public static Step starMultOrDiv(Fraction first, Fraction second, String signal){
       if(second.isNegative() && first.isPositive())
          first = first.negative(); second = second.positive();
       if(first.isNegative() && second.isNegative())
          first = first.positive(); second = second.positive();
       String text = FractionText.join(first, second,signal);
       String html = FractionHtml.join(first, second,signal);
       String method = signal.equals("*") 
                       ? message.getString("step.methd.mult") 
                       : message.getString("step.methd.div") ;
       String msg =  String.format(message.getString("step.frac.start"),text,method);
       return Step.builder().text(text).html(html).message(msg).codigo(0).build();
    }     
    /**
     * Returns the last solve step a fraction operation
     * @param fraction
     * @param pos
     * <pre>{
     * String msg = String.format(message.getString("step.frac.irreducible"));
     * }</pre>
     * @return  (new Step()).toBuilder().text(fraction.text()).html(fraction.html()).message(msg).codigo(pos).build();
     */
    public static Step finish(Fraction fraction, int pos){
       String msg = String.format(message.getString("step.frac.irreducible"));
       return (new Step()).toBuilder().text(fraction.text()).html(fraction.html()).message(msg).codigo(pos).build();
    }    
    /*
     * retorn expression a/b(c)+d/e(f) 
     */
    public static Step stepOneMMC(long mmc, Fraction first, Fraction second,String signal){
       String text = FractionText.operationStepOneMMC(first, second, mmc);
       String html = FractionHtml.operationStepOneMMC(first, second, mmc);
       String msg = String.format(message.getString("step.mmc.one"),signal,text,first.getDenominator(),second.getDenominator(),mmc);       
       return Step.builder().text(text).html(html).codigo(1).message(msg).build();
    }
    /*
     * retorn expression (a*b+d*c)/e
     */
    public static Step stepTwoMMC(long mmc, Fraction first, Fraction second,String signal){
       String text = FractionText.operationStepTwoMMC(first, second, mmc);
       String html = FractionHtml.operationStepTwoMMC(first, second, mmc);
       String msg = String.format(message.getString("step.mmc.two"),signal,text,mmc);       
       return Step.builder().text(text).html(html).codigo(2).message(msg).build();
    }
    /*
     * retorn expression (a+b)/c
     */
    public static Step stepThreeMMC(long mmc, Fraction first, Fraction second){
       String sig = second.isPositive()? "+"  : "-";
       String text = FractionText.operationStepThreeMMC(first, second, mmc);
       String html = FractionHtml.operationStepThreeMMC(first, second, mmc);
       String msg = String.format(message.getString("step.mmc.three"),sig,text,sig);   
       return (new Step()).toBuilder().text(text).html(html).codigo(3).message(msg).build();
    }         
    /*
     * retorn expression a/b
     */
    public static Step stepFourMMC(long mmc, Fraction first, Fraction second){
       String sig = second.isPositive() ? "+" : "-";
       String num = FractionPartStepMethods.mmcSig(first, second,mmc, "+")+"";
       String msg = String.format(message.getString("step.frac.end"),num,mmc);        
       String text = FractionText.operationStepFourMMC(first, second, mmc);
       String html = FractionHtml.operationStepFourMMC(first, second, mmc);
       return Step.builder().text(text).html(html).codigo(4).message(msg).build();
    }     
    /*
     * retorn expression (a*e)/b+(d*b)/(e*b) 
     */
    public static Step stepOneCrossSystem(Fraction first, Fraction second,String signal){
       String text = FractionText.operationStepOneCrossSystem(first, second);
       String html = FractionHtml.operationStepOneCrossSystem(first, second); 
       String msg = String.format(message.getString("step.cross.one"),signal,text);
       return Step.builder().text(text).html(html).codigo(1).message(msg).build();
    }    
    /*
     * retorn expression (a+b)/c
     */
    public static Step stepTwoCrossSystem(Fraction first, Fraction second,String signal){
       String text = FractionText.operationStepTwoCrossSystem(first, second);
       String html = FractionHtml.operationStepTwoCrossSystem(first, second);
       String msg = String.format(message.getString("step.cross.two"),signal,text,signal);
       return (new Step()).toBuilder().text(text).html(html).codigo(2).message(msg).build();
    } 
    /*
     * retorn expression (a+b)/c
     */
    public static Step stepThreeCrossSystem(Fraction first, Fraction second){
       //String sig = second.isPositive() ? "+" : "-";
       String num = FractionPartStepMethods.crossSig(first, second, "+")+"";
       String den = first.getDenominator()*second.getDenominator()+"";
       String msg = String.format(message.getString("step.frac.end"),num,den);
       String text = FractionText.operationStepThreeCrossSystem(first, second);
       String html = FractionHtml.operationStepThreeCrossSystem(first, second);
       return (new Step()).toBuilder().text(text).html(html).codigo(3).message(msg).build();
    }    

    public static Step stepOneDenominatorEquals(Fraction first, Fraction second,String signal) {
        second = second.positive();
        String num = first.getNumerator()+signal+second.getNumerator();
        String den = second.getDenominator().toString();
        String text = "("+num+")/"+den;
        String html = FractionHtml.template(num,den);
        String msg = String.format(message.getString("step.sum.sub.eq.den.one"),signal,signal,text,signal,den);
        return Step.builder().text(text).html(html).codigo(1).message(msg).build();
    }

    public static Step stepTwoDenominatorEquals(Fraction first, Fraction second,String signal) {
        String num = FractionPartStepMethods.numeratorsSumOrSub(first, second, signal)+"";
        String den = second.getDenominator().toString();
        String text = num+"/"+den;
        String html = FractionHtml.template(num,den);
        String msg = String.format(message.getString("step.frac.end"),num,den);        
        return Step.builder().text(text).html(html).codigo(2).message(msg).build();
    }

    public static Step stepOneMult(Fraction first, Fraction second,int pos) {
        if(first.isNegative() && second.isNegative()){
            first = first.positive(); second = second.positive();
        }
        String num = FractionPartStepMethods.numeratorMultDenominator(first, second, true);
        String den = FractionPartStepMethods.numeratorMultDenominator(first, second, false);
        String text = "("+num+")/("+den+")";
        String html = FractionHtml.template(num,den);
        String msg = String.format(message.getString("step.mult.one"),text);        
        return Step.builder().text(text).html(html).message(msg).codigo(pos).build();                
    }
    
   public static Step stepOneMult(Fraction first, Fraction second){
        return stepOneMult(first, second, 1);
   }    

    public static Step stepTwoMult(Fraction first, Fraction second,int pos) {
        String num = FractionPartStepMethods.numeratorMultDenominatorOper(first, second, true);
        String den = FractionPartStepMethods.numeratorMultDenominatorOper(first, second, false);
        String msg = String.format(message.getString("step.frac.end"),num,den);        
        return (new Step()).toBuilder()
                         .text(num+"/"+den).html(FractionHtml.template(num,den))
                         .message(msg).codigo(pos)
                         .build(); 
    }
    
    public static Step stepTwoMult(Fraction first, Fraction second){
         return stepTwoMult(first, second, 2);
     }

    public static Step stepOneDiv(Fraction first, Fraction second) {
       if(second.isNegative() && first.isPositive()){
          first = first.negative(); second = second.positive();
       }if(first.isNegative() && second.isNegative()){
          first = first.positive(); second = second.positive();
       }        
        second = second.reverse();
        String num = FractionPartStepMethods.numeratorMultDenominator(first, second, true);
        String den = FractionPartStepMethods.numeratorMultDenominator(first, second, false);
        String text = "("+num+")/("+den+")";
        String html = FractionHtml.template(num,den);
        String msg = String.format(message.getString("step.div.one"),text);        
        return Step.builder().text(text).html(html).message(msg).codigo(1).build();         
    }
    
    public static Step stepSimplify(Fraction fraction,int pos) {
        long mdc = fraction.mdc();
        String text = FractionText.simply(fraction, mdc);
        String html = FractionHtml.simply(fraction, mdc);
        String msg = String.format(message.getString("step.simplify"),text,mdc);        
        return Step.builder().text(text).html(html).message(msg).codigo(pos).build();         
    }
    
  
    
}
