/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.culturewave.rayen.utils;

import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.culturewave.rayen.Rayen;

/**
 *
 * @author rayen
 */
public class Statics {
    public static final String BASE_URL="http://127.0.0.1:8000";//hathi url mt3 symfony 
    public static void setLabelStyle(Label l){
        l.getUnselectedStyle().setFgColor(-16777216);
        l.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_ITALIC, Font.SIZE_MEDIUM));
    }
     
     public static Button createBackBtn(){
          new Rayen().start();
         Button b=new Button("Back");
         b.getUnselectedStyle().setFgColor(5542241);
         b.addActionListener((ActionListener) (ActionEvent evt) -> {
             Rayen.mainForm.show();
         });
         return b;
     }
    
}
