package com.ei.math.radical.register;

import com.ei.math.fraction.registory.FractionMessage;
import com.ei.math.radical.text.RadicalFormatter;
import java.util.Locale;

public class RadicalMessage extends FractionMessage{
    
        {
        super.baseName = "com.ei.math.radical.languages.ResourceBundle";
    }

    public static String get(String key) {
        return (new RadicalMessage()).getString(key);
    }


    @Override
    public void setLocale(Locale locale) {
        RadicalFormatter.setLocale(locale);
        this.locale  = locale;
    }
    
}
