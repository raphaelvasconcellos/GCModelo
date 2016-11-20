/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo.excecoes;

/**
 *
 * @author Guilherme
 */
public class CursoInvalidoException extends Exception {

    public CursoInvalidoException(String message) {
        super(message);
    }
    
    public CursoInvalidoException(String message, Exception cause) {
        super(message, cause);
    }
}
