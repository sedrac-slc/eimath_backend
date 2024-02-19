package com.ei.math.radical.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_de extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"step.radical.start", "Anfangsausdruck"},
            {"step.radical.finish", "letzter Ausdruck"},
        };
    }

}
