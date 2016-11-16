/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.util.regex.*;

/**
 *
 * @author Guilherme
 */
public class ValidacoesUtil {

    /*
    * Validar tamanho do texto
     */
    public static boolean validarTamanhoTexto(String Texto, Integer Tamanho) {

        //Se o tamanho for igual retorna true
        return Texto.length() <= Tamanho;
    }

    /*
    Validar email
     */
    public static boolean validarEmail(String email) {//
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        return m.find();
    }

    /*
    Validar letras
     */
    public static boolean validarLetras(String letras) {
        Pattern p = Pattern.compile("[a-zA-Z\\s]+");
        Matcher m = p.matcher(letras);
        return m.find();
    }

    /*
    Validar inteiros
     */
    public static boolean validarInteiro(String letras) {//
        Pattern p = Pattern.compile("[0-9]+");
        Matcher m = p.matcher(letras);
        return m.find();
    }

    /*
    Validar Cep
     */
    public static boolean validarCep(String letras) {//
        Pattern p = Pattern.compile("^[0-9]{2}\\.[0-9]{3}\\-[0-9]{3}$");
        Matcher m = p.matcher(letras);
        return m.find();
    }

    /*
    Validar Telefone
     */
    public static boolean validarTelefone(String letras) {//
        Pattern p = Pattern.compile("^\\([1-9]{2}\\) [2-9][0-9]{3,4}\\-[0-9]{4}$");
        Matcher m = p.matcher(letras);
        return m.find();
    }

    /*
    * trocar strings em uma string
     */
    public static String trocarString(String Texto, String Fixa, String Substituicao) {
        String TextoTrocado = Texto.replaceAll(Fixa, Substituicao);
        return TextoTrocado;
    }
}
