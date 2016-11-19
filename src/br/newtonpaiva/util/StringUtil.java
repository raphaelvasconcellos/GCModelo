/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

/**
 *
 * @author tarle
 */
public class StringUtil {
    public static boolean isNullOrWhiteSpace(String value) {
        return value == null || value.trim().isEmpty();
    }
}
