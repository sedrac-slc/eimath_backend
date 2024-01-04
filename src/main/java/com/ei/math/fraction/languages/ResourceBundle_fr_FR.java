package com.ei.math.fraction.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_fr_FR extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Anglais"},
            {"lang.de", "Allemand"},
            {"lang.es", "Espagnol"},
            {"lang.fr", "Français"},
            {"lang.pt", "Portugais"},
            {"lang.it", "Italien"},
            {"step.method.cross", "Système croisé"},
            {"step.method.mmc", "Multiple moins commun"},
            {"step.methd.mult", "Multiplication"},
            {"step.methd.div", "Division"},
            {"step.methd.sum.sub", "Modèle de dénominateur égal"},
            {"step.simplify", "simplification de fraction: a:mmc/b:mmc=%s, où: mmc(plus grand diviseur commun entre le numérateur et le dminateur)=%s"},
            {"step.start", "expression initiale"},
            {"step.frac.irreducible", "fraction irréductible"},
            {"step.frac.start", "expression initiale: %s, méthode: %s"},
            {"step.frac.end", "fraction: t/z=%s/%s, où: numérateur(t) et dénominateur(z)"},
            {"step.sum.sub.eq.den.one", "opération: a/b%sc/b => (a%sc)/b=%s, où: (a%sc)=t, z=%s"},
            {"step.mmc.one", "opération: a/b(mmc/b)%sc/d(mmc/d)=%s, où: mmc(plus petit commun multiple entre les dénominateurs [%s,%s])=%s"},
            {"step.mmc.two", "opération: (a*(mmc/b)%sb*(mmc/d))/mmc=%s, où: mmc=%s, a*(mmc/b) = x et c*(mmc/d) = y"},
            {"step.mmc.three", "opération: (x%sy)/mmc=%s, où: x%sy = t et mmc = z"},
            {"step.cross.one", "opération: (a*d%sb*c)/(d*c)=%s, où: a*d=x, b*c=y et d*c=z"},
            {"step.cross.two", "opération: (x%sy)/z=%s, où: x%sy=t      "},
            {"step.mult.one", "opération: (a*c)/(b*d)=%s, où: a*c=t et b*d=z"},
            {"step.div.one", "opération: (a*d)/(b*c)=%s, où: a*d=t et b*c=z"}
        };
    }

}
