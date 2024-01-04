package com.ei.math.equation.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_pt_PT extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Inglês"},
            {"lnag.de", "Alemão"},
            {"lang.es", "Espanhol"},
            {"lang.fr", "Frânces"},
            {"lang.pt", "Português"},
            {"lang.ru", "Russo"},
            {"lang.it", "Italino"},
            {"lang.jp", "Janponês"},
            {"lang.cn", "Chinês"},
            {"step.eq.start", "expressão inicial"},
            {"step.eq.finish", "expressão reduzida"},
            {"step.eq.oper", "efectuar a operação %s"},
            {"step.eq.res.opers", "resolver as operações %s"},
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
            {"step.eq.delta.raiz.one","calculado a operação para encontra a raiz(%s)"},            
            {"step.eq.delta.raiz.two","resultadado da operação no númerado"},
            {"step.eq.delta.raiz.three","raiz(%s)"},
        };
    }

}
