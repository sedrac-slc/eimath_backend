package com.ei.math.equation.languages;

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
            {"step.eq.start", "initial expression"},
            {"step.eq.finish", "reduced expression"},
            {"step.eq.oper", "perform the operation %s"},
            {"step.eq.res.opers", "resolve the operations %s"},
            {"step.eq.sub", "substituindo o valor da incognita %s=%s"},
            {"step.eq.delta.form", "fórmula do delta"},
            {"step.eq.delta.one", "substituindo os valores na equação"},
            {"step.eq.delta.two", "calculando o valor de b^2"},
            {"step.eq.delta.three", "calculando o valor de 4ac"},
            {"step.eq.delta.four", "resultado do delta"}, 
            {"step.eq.delta.zero.form","formulá reduzida de bhaskara, para equação com apenas uma raiz"},
            {"step.eq.delta.zero.one","substituindo os valores na formulá"},
            {"step.eq.delta.zero.two","calculando o valor de 2a"},
            {"step.eq.delta.zero.three","a raiz da equação"},
            {"step.eq.delta.no","esta equação tem zeros da função"},
            {"step.eq.delta.positive.form","formulá de bhaskara, para equação com duas raizes"},
            {"step.eq.delta.positive.one","substituindo os valores na formulá"},
            {"step.eq.delta.positive.two","calculado a  raiz quadrada do delta"},
            {"step.eq.delta.raiz.one","calculado a raiz(%s)"},            
            {"step.eq.delta.raiz.two","resultadado da operação no númerado"},
            {"step.eq.delta.raiz.three","raiz(%s) da equação"},            
        };
    }

}
