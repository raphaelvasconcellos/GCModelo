/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.modelo.excecoes;

import java.util.ResourceBundle;

/**
 *
 * @author tarle
 */
public abstract class GCException extends Exception {
    
    public GCException(String key) {
        super(ResourceBundle.getBundle("MessageBundle").getString(key));
    }
    
    public GCException(String key, Exception e) {
        super(ResourceBundle.getBundle("MessageBundle").getString(key), e);
    }
}
