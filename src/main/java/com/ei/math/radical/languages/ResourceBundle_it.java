package com.ei.math.radical.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_it extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"step.radical.start", "espressione iniziale"},
            {"step.radical.seq.finish", "espressione finale"},
        };
    }

}
