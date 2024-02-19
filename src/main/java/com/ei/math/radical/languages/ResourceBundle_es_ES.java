package com.ei.math.radical.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_es_ES extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"step.radical.start", "expresión inicial"},
            {"step.radical.finish", "expresión final en la secuencia"},
        };
    }

}
