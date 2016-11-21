/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.util.regex.*;

public class ValidacoesUtil {

    public static boolean validarTamanhoTexto(String value, int tamanho) {
        if(value == null)
            return true;
        
        return value.length() <= tamanho;
    }

    public static boolean validarEmail(String value) {
        if(StringUtil.isNullOrWhiteSpace(value))
            return false;
        
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(value);
        return m.find();
    }

    public static boolean validarLetras(String value) {
        if(StringUtil.isNullOrWhiteSpace(value))
            return true;
        
        for(char c : value.toCharArray())
            if(!Character.isAlphabetic(c))
                return false;
        
        return true;
    }

    public static boolean validarInteiro(String value) {
        if(StringUtil.isNullOrWhiteSpace(value))
            return false;
        
        for(char c : value.toCharArray())
            if(!Character.isDigit(c))
                return false;
        
        return true;
    }

    public static boolean validarCep(String value) {
        if(StringUtil.isNullOrWhiteSpace(value))
            return false;
        return value.matches("^[0-9]{2}(\\.)?[0-9]{3}(\\-)?[0-9]{3}$");
    }

    public static boolean validarTelefone(String value) {
        if(StringUtil.isNullOrWhiteSpace(value))
            return false;
       
        return value.matches("^.((10)|([1-9][1-9]).)\\s9?[6-9][0-9]{3}-[0-9]{4}$") ||
                value.matches("^.((10)|([1-9][1-9]).)\\s[2-5][0-9]{3}-[0-9]{4}$");
    }
}
