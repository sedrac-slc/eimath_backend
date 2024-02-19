package com.ei.math.radical.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_fr_FR extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"step.radical.start", "expression initiale"},
            {"step.radical.finish", "expression finale"},
        };
    }

}
