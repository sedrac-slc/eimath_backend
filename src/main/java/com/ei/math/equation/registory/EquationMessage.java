package com.ei.math.equation.registory;

import com.ei.math.fraction.registory.FractionMessage;
import com.ei.math.fraction.text.FractionFormatter;
import java.util.Locale;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EquationMessage extends FractionMessage{
    
    {
        super.baseName = "com.ei.math.equation.languages.ResourceBundle";
    }

    public static String get(String key) {
        return (new EquationMessage()).getString(key);
    }


    @Override
    public void setLocale(Locale locale) {
        FractionFormatter.setLocale(locale);
        this.locale  = locale;
    }
    

    
   
}
