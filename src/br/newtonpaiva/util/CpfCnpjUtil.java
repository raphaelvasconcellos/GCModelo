/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.newtonpaiva.util;

import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author tarle
 */
public class CpfCnpjUtil {
    public static String formatarCpfCnpj(String cpfCnpj) {
        if(cpfCnpj == null || cpfCnpj.trim().isEmpty())
            return null;
        
        JFormattedTextField format = new JFormattedTextField();
        MaskFormatter mask;
        
        try {
            if(removerFormatacaoCpfCnpj(cpfCnpj).length() == 11)
                mask = new MaskFormatter("###.###.###-##");
            else
                mask = new MaskFormatter("##.###.###/####-##");

            format.setFormatterFactory(new DefaultFormatterFactory(mask));
            format.setText(cpfCnpj);
            
            return format.getText();
        }catch(ParseException e) {
            return null;
        }
    }
    
    public static String removerFormatacaoCpfCnpj(String cpfCnpj) {
        if(cpfCnpj == null || cpfCnpj.trim().isEmpty())
            return null;
        
        return cpfCnpj.replaceAll("[\\./-]", "");
    }
}