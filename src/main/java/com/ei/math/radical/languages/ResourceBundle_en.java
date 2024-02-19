package com.ei.math.radical.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"step.radical.start", "initial expression"},
            {"step.radical.finish", "final expression"},
        };
    }

}
