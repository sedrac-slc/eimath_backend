package com.ei.math.fraction.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_de extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Englisch"},
            {"lang.de", "Deutsch"},
            {"lang.es", "Spanisch"},
            {"lang.fr", "Französisch"},
            {"lang.pt", "Portugiesisch"},
            {"lang.it", "Italienisch"},
            {"step.method.cross", "kreuzsystem"},
            {"step.method.mmc", "kleinstes gemeinsames Vielfaches"},
            {"step.methd.mult", "Multiplikation"},
            {"step.methd.div", "Aufteilung"},
            {"step.methd.sum.sub", "Equal denominator pattern"},
            {"step.simplify", "bruchvereinfachung: a:mmc/b:mmc=%s, wobei: mmc(größter gemeinsamer Teiler zwischen Zähler und Nenner)=%s"},
            {"step.start", "afangsausdruck"},
            {"step.frac.irreducible", "irreduzibler Bruch"},
            {"step.frac.start", "anfangsausdruck: %s, methode: %s"},
            {"step.frac.end", "fraktion: t/z=%s/%s, wobei: Zähler(t) und Nenner(z)"},
            {"step.sum.sub.eq.den.one", "betrieb: a/b%sc/b => (a%sc)/b=%s, wobei: (a%sc)=t, z=%s"},
            {"step.mmc.one", "betrieb: a/b(mmc/b)%sc/d(mmc/d)=%s, wobei: mmc(kleinstes gemeinsames Vielfaches zwischen Nennern [%s,%s])=%s"},
            {"step.mmc.two", "betrieb: (a*(mmc/b)%sb*(mmc/d))/mmc=%s, wobei: mmc=%s, a*(mmc/b) = x wo c*(mmc/d) = y"},
            {"step.mmc.three", "betrieb: (x%sy)/mmc=%s, wobei: x%sy = t wo mmc = z"},
            {"step.cross.one", "betrieb: (a*d%sb*c)/(d*c)=%s, wobei: a*d=x, b*c=y und d*c=z"},
            {"step.cross.two", "betrieb: (x%sy)/z=%s, wo: x%sy=t          "},
            {"step.mult.one", "betrieb: (a*c)/(b*d)=%s, wobei: a*c=t und b*d=z"},
            {"step.div.one", "betrieb: (a*d)/(b*c)=%s, wobei: a*d=t und b*c=z"}
        };
    }

}
