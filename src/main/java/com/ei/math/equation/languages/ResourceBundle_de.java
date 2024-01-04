package com.ei.math.equation.languages;

import java.util.ListResourceBundle;
/**
 * @author SEDRAC LUCAS CALUPEECA
 */
public class ResourceBundle_de extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
            {"lang.en", "Englisch"},
            {"lnag.de", "Deutsch"},
            {"lang.es", "Spanisch"},
            {"lang.fr", "Französisch"},
            {"lang.pt", "Portugiesisch"},
            {"lang.ru", "Russisch"},
            {"lang.it", "Italienisch"},
            {"lang.jp", "japanisch"},
            {"lang.cn", "Chinesisch"},
            {"step.eq.start", "Anfangsausdruck"},
            {"step.eq.finish", "reduzierter Ausdruck"},
            {"step.eq.oper", "Führen Sie die Operation durch %s"},
            {"step.eq.res.opers", "Auflösen der Vorgänge %s"},
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
