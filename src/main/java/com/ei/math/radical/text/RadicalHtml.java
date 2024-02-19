package com.ei.math.radical.text;

import com.ei.math.fraction.Fraction;
import com.ei.math.general.Step;
import com.ei.math.radical.Radical;
import com.ei.math.radical.RadicalRoot;
import java.util.List;

public class RadicalHtml {

    private static String radicalFormat(Radical radical){
        boolean radicalTwo = radical.getExpoent().getDenominator() == 2;
        return "<div class=\"radical-group\">" +
                    ( radicalTwo ? "" : "<sup class=\"expoent\">"+radical.getExpoent().getDenominator()+"</sup>" )+
                    "<div class=\"signal-root\">&radic;</div>" +
                    "<div class=\"base\">"+radical.getBase()+"</div>" +
                "</div>";
    }
    
    private static String fractionGroupOne(Radical radical , String html){
        
        return "<div class='fraction-arithmetic-group'>" +
                    "<div class='fraction-arithmetic-group-item'>"
                        +html+
                    "</div>"
                    + radicalFormat(radical)+
                "</div>";
    }
    
    private static String verifyType(Radical radical, String html){
      return fractionGroupOne(radical, html); 
    }
    
    public static String joinRadical(Radical radical , Step step) {
        return verifyType(radical, step.getHtml());
    }
    
    public static String html(Radical radical, boolean signal){
        String sig = signal ? (radical.getCoefficient().isPositive() ? "+" : "-") : "";
        Fraction coefficient = signal && radical.getCoefficient().isNegative() ? radical.getCoefficient().positive() : radical.getCoefficient();
        String coeffi = !coefficient.equals(Fraction.of(1)) ? "<div class=\"coefficient\">"+coefficient.text()+"</div>" : "";
        return "<div class=\"radical\">"
                    +sig + coeffi +RadicalRoot.html(radical.getBase(), radical.getExpoent())
              +"</div>";
    }
    
    public static String html(Radical radical) { return html(radical, false); }
        
    public static String join(Radical radicalOne, Radical radicalTwo){
        return "<div class=\"rad-dflex\">"+radicalOne.html()+"<div>=</div>"+radicalTwo.html()+"</div>";
    }

    public static String setp(List<Radical> radicals) {
        String join = "";
        for (int i = 0; i < radicals.size(); i++) 
            join += radicals.get(i).html(i != 0);
        return "<div class=\"rad-dflex\">"+join+"</div>";
    }

    public static String joinRadicalRoot(Step step, RadicalRoot root) {
        return "<div class=\"rad-arith-group\"><div class=\"arith-r\">%s</div><div class=\"rad-r\">%s</div></div>"
                .formatted(step.getHtml(),root.html());
    }
    
}
