package com.ei.math.radical.text;

import com.ei.math.general.Step;
import com.ei.math.radical.Radical;
import com.ei.math.radical.RadicalRoot;
import com.ei.math.radical.register.RadicalMessage;
import java.util.List;
import java.util.Locale;

public class RadicalFormatter {
    
    public static final  RadicalMessage message;
    
    static{
        message = new RadicalMessage();
    }  
    
    public static void setLocale(Locale locale){
        message.setLocale(locale);
    }    
    
    public static Step joinStepForRadical(Radical radical,Step step){
        String text = RadicalText.joinRadical(step,radical);
        String html = RadicalHtml.joinRadical(radical,step);
        return Step.builder().text(text).html(html).build();
    }
    
    public static Step joinRadicalGroup(String text, String html,int cod) {
        String exp = "<div class=\"fraction-group-radical\">"+html+"</div>";
        if(!html.contains("denominator"))
            exp = "<div class=\"fraction-group-radical signal-simple\">"+html+"</div>";
        return new Step(cod, text, exp,"");
    }
    
    public static Step joinRadicalGroup(String text, String html) {
        return  joinRadicalGroup(text, html, 0);
    }
    
    public static Step reduceStep(Radical radical){
        String text = radical.text();
        String html = radical.html();
        return Step.builder().text(text).html(html).build();
    }
    
    public static Step reduceStep(Radical radicalOne, Radical radicalTwo){
        String text = radicalOne.text()+"="+radicalTwo.text();
        String html = RadicalHtml.join(radicalOne, radicalTwo);
        return Step.builder().text(text).html(html).build();
    }    
    
    private static Step setpOrFinish(List<Radical> radicals, String key){
        String text = RadicalText.setp(radicals);
        String html = "<div class='mt-2'>"+RadicalHtml.setp(radicals)+"</div>";
        String msg =  message.getString(key);
        return Step.builder().text(text).html(html).message(text).message(msg).build();
    }    

    public static Step setp(List<Radical> radicals){
        return setpOrFinish(radicals,"step.radical.start");
    }
    
    public static Step finish(List<Radical> radicals){
        return setpOrFinish(radicals,"step.radical.finish");
    }    

    public static Step joinRadicalRoot(Step step, RadicalRoot root) {
        String text = RadicalText.joinRadicalRoot(step,root);
        String html = RadicalHtml.joinRadicalRoot(step,root);
        return Step.builder().text(text).html(html).build();
    }
    
}
