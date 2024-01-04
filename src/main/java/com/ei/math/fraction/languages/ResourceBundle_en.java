package com.ei.math.fraction.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "English"},
            {"lang.de", "German"},
            {"lang.es", "Spanish"},
            {"lang.fr", "French"},
            {"lang.pt", "Portuguese"},
            {"lang.it", "italian"},
            {"step.method.cross", "cross system"},
            {"step.method.mmc", "least common multiple"},
            {"step.methd.mult", "Multiplication "},
            {"step.methd.div", "Division"},
            {"step.methd.sum.sub", "Equal denominator pattern"},
            {"step.simplify", "fraction simplification: a:mmc/b:mmc=%s, where: mmc(greatest common divisor between numerator and deminator)=%s"},
            {"step.start", "initial expression"},
            {"step.frac.irreducible", "irreducible fraction"},
            {"step.frac.start", "initial expression: %s, method: %s"},
            {"step.frac.end", "fraction: t/z=%s/%s, where: numerator(t) and denominator(z)"},
            {"step.sum.sub.eq.den.one", "operation: a/b%sc/b => (a%sc)/b=%s, where: (a%sc)=t, z=%s"},
            {"step.mmc.one", "operation: a/b(mmc/b)%sc/d(mmc/d)=%s, where: mmc(least common multiple between denominators [%s,%s])=%s"},
            {"step.mmc.two", "operation: (a*(mmc/b)%sb*(mmc/d))/mmc=%s, where: mmc=%s, a*(mmc/b) = x and c*(mmc/d) = y"},
            {"step.mmc.three", "operation: (x%sy)/mmc=%s, where: x%sy = t and mmc = z"},
            {"step.cross.one", "operation: (a*d%sb*c)/(d*c)=%s, where: a*d=x, b*c=y and d*c=z"},
            {"step.cross.two", "operation: (x%sy)/z=%s, where: x%sy=t"},
            {"step.mult.one", "operation: (a*c)/(b*d)=%s, where: a*c=t e b*d=z"},
            {"step.div.one", "operation: (a*d)/(b*c)=%s, where: a*d=t e b*c=z"}
        };
    }

}
