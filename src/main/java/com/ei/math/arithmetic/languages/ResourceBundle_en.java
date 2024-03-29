package com.ei.math.arithmetic.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "English"},
            {"lnag.de", "German"},
            {"lang.es", "Spanish"},
            {"lang.fr", "French"},
            {"lang.pt", "Portuguese"},
            {"lang.ru", "Russian"},
            {"lang.it", "italian"},
            {"lang.jp", "japanese"},
            {"lang.cn", "Chinese"},
            {"step.arith.start", "initial expression"},
            {"mmc", "least common multiple"},
            {"step.arith.seq.finish", "final expression in the sequence"},
            {"step.arith.seq", "operation performs (%s), fraction list %s"},
            {"step.arith.cross.one", "finding least common multiple of denominators %s"},
            {"step.arith.cross.two", "write the fractions based on the least common multiple (mmc=%s) found"},
            {"step.arith.cross.three", "solve(%s) the operation between the numerators, the denominator is mmc(%s)"},
            {"step.arith.cross.four", "result of the operation will be the new numerator and denominator is the least common multiple"},
            {"step.arith.irreducible", "irreducible fraction"},
            {"step.arith.simplify", "fraction simplification by the greatest common divisor (%s)"},
            {"step.arith.group", "grouping between addition(%s) and subtraction(%s) operations"},
            {"step.arith.eq.oper", "perform the operation %s"}
        };
    }

}
