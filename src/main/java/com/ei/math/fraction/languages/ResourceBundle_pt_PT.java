package com.ei.math.fraction.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_pt_PT extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Inglês"},
            {"lang.de", "Alemão"},
            {"lang.es", "Espanhol"},
            {"lang.fr", "Frânces"},
            {"lang.pt", "Português"},
            {"lang.it", "Italino   "},
            {"step.method.cross", "Sistema cruzado"},
            {"step.method.mmc", "Mínimo multiplo comun"},
            {"step.methd.mult", "Multiplicação"},            
            {"step.methd.div", "Divisão"},
            {"step.methd.sum.sub", "Padrão denominadores iguais"},
            {"step.simplify", "simplificação de fração: a:mdc/b:mdc=%s, onde: mdc(máximo divisor comum entre o numerador e deminador)=%s"},
            {"step.start", "expressão inicial"},
            {"step.frac.irreducible", "fração irredutivel"},
            {"step.frac.start", "expressão inicial: %s, método: %s"},
            {"step.frac.end", "fracão: t/z=%s/%s, onde: numarador(t) e denominador(z)"},
            {"step.sum.sub.eq.den.one", "operação: a/b%sc/b => (a%sc)/b=%s, onde: (a%sc)=t, z=%s"},
            {"step.mmc.one", "operação: a/b(mmc/b)%sc/d(mmc/d)=%s, onde: mmc(mínimo múltiplo comum entre os denominadores [%s,%s])=%s"},
            {"step.mmc.two", "operação: (a*(mmc/b)%sb*(mmc/d))/mmc=%s, onde: mmc=%s, a*(mmc/b) = x e c*(mmc/d) = y"},
            {"step.mmc.three", "operação: (x%sy)/mmc=%s, onde: x%sy = t e mmc = z"},
            {"step.cross.one", "operação: (a*d%sb*c)/(d*c)=%s, onde: a*d=x, b*c=y e d*c=z"},
            {"step.cross.two", "operação: (x%sy)/z=%s, onde: x%sy=t"},
            {"step.mult.one", "operação: (a*c)/(b*d)=%s, onde: a*c=t e b*d=z"},
            {"step.div.one", "operação: (a*d)/(b*c)=%s, onde: a*d=t e b*c=z"}
        };
    }

}
