/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo.excessoes;

/**
 *
 * @author Tarley Lana
 */
public class ContratoHistoricoInvalidoException extends Exception {
    public ContratoHistoricoInvalidoException(String msg){
        //super operador que chama a superclasse
        super(msg);
    }
}
