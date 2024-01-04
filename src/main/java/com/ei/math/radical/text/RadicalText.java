package com.ei.math.radical.text;

import com.ei.math.general.Step;
import com.ei.math.radical.Radical;
import com.ei.math.radical.RadicalRoot;
import java.util.List;

public class RadicalText {

    public static String joinRadical(Step step, Radical radical) {
     return "("+step.getText()+")"+radical.simplyText();
    }

    public static String setp(List<Radical> radicals) {
        String join = "";
        for (int i = 0; i < radicals.size(); i++) 
            join += radicals.get(i).text( i != 0);
        return join;
    }

    public static String joinRadicalRoot(Step step, RadicalRoot root) {
       return "[%s]%s".formatted(step.getText(),root.text());
    }
    
}
