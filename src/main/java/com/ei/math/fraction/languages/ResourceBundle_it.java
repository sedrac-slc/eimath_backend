package com.ei.math.fraction.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_it extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "inglese"},
            {"lang.de", "Tedesco"},
            {"lang.es", "spagnolo"},
            {"lang.fr", "francese"},
            {"lang.pt", "portoghese"},
            {"lang.it", "italiano"},
            {"step.method.cross", "sistema incrociato"},
            {"step.method.mmc", "minimo comune multiplo "},
            {"step.methd.mult", "Moltiplicazione "},
            {"step.methd.div", "Divisione"},
            {"step.methd.sum.sub", "Schema denominatore uguale"},
            {"step.simplify", "semplificazione delle frazioni: a:mmc/b:mmc=%s, dove: mmc(massimo comune divisore tra numeratore e deminatore)=%s"},
            {"step.start", "espressione iniziale"},
            {"step.frac.irreducible", "frazione irriducibile"},
            {"step.frac.start", "espressione iniziale: %s, metodo: %s"},
            {"step.frac.end", "frazione: t/z=%s/%s, dove: numeratore(t) e denominatore(z)"},
            {"step.sum.sub.eq.den.one", "operazione: a/b%sc/b => (a%sc)/b=%s, dove: (a%sc)=t, z=%s"},
            {"step.mmc.one", "operazione: a/b(mmc/b)%sc/d(mmc/d)=%s, dove: mmc(minimo comune multiplo tra denominatori [%s,%s])=%s"},
            {"step.mmc.two", "operazione: (a*(mmc/b)%sb*(mmc/d))/mmc=%s, dove: mmc=%s, a*(mmc/b) = x e c*(mmc/d) = si"},
            {"step.mmc.three", "operazione: (x%sy)/mmc=%s, dove: x%sy = t e mmc = z"},
            {"step.cross.one", "operazione: (a*d%sb*c)/(d*c)=%s, dove: a*d=x, b*c=y e d*c=z"},
            {"step.cross.two", "operazione: (x%sy)/z=%s, dove: x%sy=t"},
            {"step.mult.one", "operazione: (a*c)/(b*d)=%s, dove: a*c=t e b*d=z"},
            {"step.div.one", "operazione: (a*d)/(b*c)=%s, dove: a*d=t e b*c=z"}
        };
    }

}
