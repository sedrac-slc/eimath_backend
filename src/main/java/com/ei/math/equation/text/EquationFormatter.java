package com.ei.math.equation.text;

import com.ei.math.general.Step;
import com.ei.math.equation.Equation;
import com.ei.math.equation.operation.EquationIncognita;
import com.ei.math.equation.registory.EquationMessage;
import com.ei.math.equation.util.EquationUtil;
import com.ei.math.fraction.Fraction;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EquationFormatter {

    public static final  EquationMessage message;
    
    static{
        message = new EquationMessage();
    }
    
    public static void setLocale(Locale locale){
        message.setLocale(locale);
    }
    
    public static Step startExpressionSumOrSub(List<Equation> list, String codeMessage){
        String text = EquationText.joinSumOrSub(list);
        String html = EquationHtml.joinSumOrSub(list);
        String msg = message.getString(codeMessage);
        return Step.builder().text(text).html(html).message(msg).build();
    }
    
     public static Step startExpressionSumOrSub(List<Equation> list){
         return startExpressionSumOrSub(list, "step.eq.start");
     }
    
    public static Step joinStepForFunction(Equation function,Step step){
        String text = EquationText.joinFunction(step,function);
        String html = EquationHtml.joinFunction(function,step);
        String msg = EquationUtil.messageSituaction(message, text);
        return Step.builder().text(text).html(html).message(msg).build();
    }
    
    public static Step joinFunctionGroup(String text, String html,int cod) {
        String exp = "<div class=\"fraction-group-function\">"+html+"</div>";
        if(!html.contains("denominator"))
            exp = "<div class=\"fraction-group-function signal-simple\">"+html+"</div>";
        String msg = EquationUtil.messageSituaction(message, text);
        return new Step(cod, text, exp,msg);
    }
    
    public static Step joinFunctionGroup(String text, String html) {
        return  joinFunctionGroup(text, html, 0);
    }

    public static Step joinSumOrSub(Map<Equation, List<Equation>> maps) {
        String text = EquationText.joinSumOrSub(maps);
        String html = EquationHtml.joinSumOrSub(maps);
        String txt = EquationText.joinSumOrSub(maps, false);
        String msg =  String.format(message.getString("step.eq.res.opers"), txt);
        return Step.builder().codigo(2).text(text).html(html).message(msg).build();
    }

    public static Step expressionIncoginita(List<Equation> list, String n) {
        String text = EquationText.expressionIncoginita(list,n);
        String html = EquationHtml.expressionIncoginita(list,n);
        String msg = String.format(message.getString("step.eq.sub"),"x",n);
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step calculateIncoginita(List<Equation> list, String n) {
        String text = EquationText.incoginitaResult(list,n);
        String html = EquationHtml.incoginitaResult(list,n);
        String msg = "";
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step incoginitaResult(List<Equation> list, String n) {
        String text = EquationText.incoginitaResult(list,n);
        String html = EquationHtml.incoginitaResult(list,n);
        String msg = "teste";
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaForm() {
        String text = "b^2-4ac";
        String html = "<divclass=\"equation-delta\">&Delta; = b<sup>2</sup> - 4ac</div>";
        String msg = message.getString("step.eq.delta.form");
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaFormOne(long a, long b, long c) {
        String text = "(%s)^2-4(%s)(%s)".formatted(b,a,c);
        String html = "<divclass=\"equation-delta\">&Delta; = %s<sup>2</sup> - 4(%s)(%s)</div>".formatted(b,a,c);
        String msg = message.getString("step.eq.delta.one");
        return Step.builder().text(text).html(html).message(msg).build();        
    }

    public static Step deltaFormTwo(long a, long b, long c) {
        long partOne = EquationIncognita.partOne(b);
        String text = "(%s)-4(%s)(%s)".formatted(partOne,a,c);
        String html = "<divclass=\"equation-delta\">&Delta; = %s - 4(%s)(%s)</div>".formatted(partOne,a,c);
        String msg = message.getString("step.eq.delta.two");
        return Step.builder().text(text).html(html).message(msg).build();    
    }

    public static Step deltaFormThree(long a, long b, long c) {
        long partOne = EquationIncognita.partOne(b);
        long partTwo = EquationIncognita.partTwo(a, c);
        String signal = partTwo > 0 ? "-" : "+";
        if(partTwo < 0) partTwo = Math.abs(partTwo);
        String text = "%s %s %s".formatted(partOne,signal, partTwo);
        String html = "<divclass=\"equation-delta\">&Delta; = %s %s %s</div>".formatted(partOne, signal, partTwo);
        String msg = message.getString("step.eq.delta.three");
        return Step.builder().text(text).html(html).message(msg).build();           
    }

    public static Step deltaFormFour(long a, long b, long c) {
        long delta = EquationIncognita.delta(a,b,c);
        String text = "%s".formatted(delta);
        String html = "<divclass=\"equation-delta\">&Delta; = %s</div>".formatted(delta);
        String msg = message.getString("step.eq.delta.form");
        return Step.builder().text(text).html(html).message(msg).build();    
    }

    public static Step equationNotZeroFunction(long delta) {
        String text = "%s".formatted(delta);
        String html = "<divclass=\"equation-delta\">&Delta; = %s</div>".formatted(delta);
        String msg = message.getString("step.eq.delta.no");
        return Step.builder().text(text).html(html).message(msg).build(); 
    }

    public static Step deltaZeroForm() {
        String text = EquationText.deltaZeroForm("-b","a");
        String html = EquationHtml.deltaZeroForm("-b","a");
        String msg = message.getString("step.eq.delta.zero.form");
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaZeroOne(long b, long a) {
        if(b < 0) b = Math.abs(b);
        String text = EquationText.deltaZeroForm(b+"","("+a+")");
        String html = EquationHtml.deltaZeroForm(b+"","("+a+")");
        String msg = message.getString("step.eq.delta.zero.one");
        return Step.builder().text(text).html(html).message(msg).build();       
    }
    
    public static Step deltaZeroTwo(long b, long a) {
        a = 2 * a;
        if(b < 0) b = Math.abs(b);
        String text = EquationText.deltaZeroForm(b+"",a+"");
        String html = EquationHtml.deltaZeroForm(b+"",a+"");
        String msg = message.getString("step.eq.delta.zero.two");
        return Step.builder().text(text).html(html).message(msg).build();       
    }    

    public static Step deltaZeroThree(long b, long a) {
        long result = (-1*b / 2*a);
        String text = result+"";
        String html = "<divclass=\"equation-delta\">x = %s</div>".formatted(result);
        String msg = message.getString("step.eq.delta.zero.three");
        return Step.builder().text(text).html(html).message(msg).build();  
    }

    public static Step deltaPositiveForm() {
        String text = EquationText.deltaPositiveForm("-b","&Delta;","a");
        String html = EquationHtml.deltaPositiveForm("-b","&Delta;","2a");
        String msg = message.getString("step.eq.delta.positive.form");
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaPositiveOne(long b, long delta, long a) {
        String text = EquationText.deltaPositiveForm(b+"",delta+"","2("+a+")");
        String html = EquationHtml.deltaPositiveForm(b+"",delta+"","2("+a+")");
        String msg = message.getString("step.eq.delta.positive.one");
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaPositiveTwo(long b, long raiz, long a) {
        String text = EquationText.deltaPositiveForm(b+"",raiz+"",(2*a)+"",false);
        String html = EquationHtml.deltaPositiveForm(b+"",raiz+"",(2*a)+"",false);
        String msg = message.getString("step.eq.delta.positive.two");
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaPositiveRaizOne(long b, long raiz, long a, String signal,long num) {
        a = 2 * a; b = -1 * b;
        String text = EquationText.deltaPositiveForm(b+"",raiz+"",a+"",false);
        String html = EquationHtml.deltaPositiveForm(b+"",raiz+"",a+"",false,signal,num);
        String msg = message.getString("step.eq.delta.raiz.one").formatted(num);
        return Step.builder().text(text).html(html).message(msg).build();
    }

    public static Step deltaPositiveRaizTwo(long b, long raiz, long a, String signal,long num) {
        a = 2 * a; 
        b = "+".equals(signal) ? (-1 * b) + raiz : (-1 * b) - raiz;
        String text = EquationText.deltaPositiveFraction(b+"",a+"",num);
        String html = EquationHtml.deltaPositiveFraction(b+"",a+"",num);
        String msg = message.getString("step.eq.delta.raiz.two");
        return Step.builder().text(text).html(html).message(msg).build();
    }
    
    public static Step deltaPositiveRaizThree(long b, long raiz, long a, String signal,long num) {
        a = 2 * a; 
        b = "+".equals(signal) ? (-1 * b) + raiz : (-1 * b) - raiz;
        long result = b / a;
        String text = EquationText.deltaPositiveFraction(result,num);
        String html = EquationHtml.deltaPositiveFraction(result,num);
        String msg = message.getString("step.eq.delta.raiz.three").formatted(num);
        return Step.builder().text(text).html(html).message(msg).build();
    }    
        
}
