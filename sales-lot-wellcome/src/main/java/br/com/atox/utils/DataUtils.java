package br.com.atox.utils;

import java.text.Normalizer;
import java.util.function.Function;

/**
 * Classe util para higienização de dados
 * DataUtils
 */
public class DataUtils {

    /**
     * Função responsável por normalizar string para formato númerico
     * @return String normalizada com valores numericos
     */
    public static Function<String, String> normalizeToNumeric(){
        return (s) -> {

            if(null == s){
                return s;
            }
            
            s = s.replaceAll("[^a-zA-Z0-9]", "")
                  .trim();
            if(!s.matches("[0-9]+")){
                return null;
            }
            return s;
         };
    }

    /**
     * Função responsável por normalizar strings
     * @return String normalizada
     */
    public static Function<String, String> normalizeToString(){
        return (s) -> {

            if(null == s){
                return s;
            }
            s = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "")
                                                            .replaceAll("  ", " ")
                                                            .trim()
                                                            .toUpperCase();
         
            return s;
         };
    }

}