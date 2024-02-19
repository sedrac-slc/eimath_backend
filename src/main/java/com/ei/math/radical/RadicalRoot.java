package com.ei.math.radical;

import com.ei.math.fraction.Fraction;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
public class RadicalRoot implements Serializable{
    protected Fraction base;
    @Builder.Default
    protected Fraction expoent = Fraction.of(1,2);
    
     public static String text(Fraction base,Fraction expoent){ 
         return base.text()+"^"+expoent.text();
     }
     
     public String text(){
         return  text(base, expoent);
     }
   
     public static String html(Fraction base,Fraction expoent){ 
        String sup = !expoent.equals(Fraction.of(1,2)) ? "<sup>"+expoent.getDenominator()+"</sup>" : "";  
        boolean equals = !expoent.positive().equals(Fraction.of(0));         
        return  "<div class=\"rad-group\">"
                   +"<div class=\"rad\">"
                        +sup +(equals ? "<div>&radic;</div>" : "")
                   +"</div>"
                   +(equals ? "<div class=\"rad-base\">"+base.text()+"</div>" : "")
              +"</div>";
     }    
     
     public String html(){
         return html(base, expoent);
     }
     
}
