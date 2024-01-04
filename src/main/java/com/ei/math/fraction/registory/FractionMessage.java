package com.ei.math.fraction.registory;

import java.util.Locale;
import java.util.ResourceBundle;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
/**
 * <p> {@code FractionMessage} is the concrete class that translates the Step object's messages
 * </p>
 * 
 * @author  Sedrac Lucas Calupeteca
 * @since   1.0
 */
@Getter
@Setter
@NoArgsConstructor
public class FractionMessage {
    protected Locale locale;
    @Getter(AccessLevel.NONE)
    protected String baseName ="com.ei.math.fraction.languages.ResourceBundle"; // "messages"; 
    @Getter(AccessLevel.NONE)
    protected ResourceBundle message;
    
    {
       locale = Locale.getDefault();
    }

    public FractionMessage(Locale locale) {
        this.locale = locale;
    }
    /**
     * set language using ISO standard
     * @param lang {@code string} 
     */     
    public FractionMessage(String lang) {
        this.locale = new Locale(lang);
    }     
    /**
     * set language and country using ISO standard
     * @param lang {@code string} 
     * @param country {@code string} 
     */         
    public FractionMessage(String lang,String country) {
        this.locale = new Locale(lang, country);
    }    
    /**
     * Get a message in the properties file (ResourceBundle) with a locale.
     * @param key {@code string} properties (ResourceBundle) file code or key
     * @param locale {@code Locale}
     * <pre>{
     *  message = ResourceBundle.getBundle(baseName, locale);
     * }</pre>
     * @return  message.getString(key);
     */     
    public String getString(String key,Locale locale) {
        message = ResourceBundle.getBundle(baseName,locale);
        return message.getString(key);
    }
    /**
     * Get a message in the properties file (ResourceBundle) with a locale default.
     * @param key {@code string} properties (ResourceBundle) file code or key
     * @return  getString(key, locale);
     */    
     public String getString(String key){
        return getString(key,locale);
    }
     
 
}
