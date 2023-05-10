/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.user;
import com.mycompany.services.ServiceUser;
/**
 *
 * @author dell
 */
public class ForgertP extends BaseForm {

    public ForgertP(Resources res) {
        super(new BorderLayout());
        
        if(!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout)getLayout();
            bl.defineLandscapeSwap(BorderLayout.NORTH, BorderLayout.EAST);
            bl.defineLandscapeSwap(BorderLayout.SOUTH, BorderLayout.CENTER);
        }
    /*    user myObject=(user) Storage.getInstance().readObject("myObjectKey");
        if( myObject !=null){
              new ProfileForm(res).show();
}*/
        getTitleArea().setUIID("Container");
        setUIID("SignIn");
        
        add(BorderLayout.NORTH, new Label(res.getImage("Logo.png"), "LogoLabel"));
        
        TextField email = new TextField("", "email", 50, TextField.ANY);
        
        email.setSingleLineTextArea(false);
        Button SendCode = new Button("Send code");
        Button Signin = new Button("Sign in");
        
        //mp oublié
        
        
        Signin.addActionListener(e -> new SignInForm(res).show());
        Signin.setUIID("Link");
        
        
        
        
        
        
        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                
                SendCode,
                FlowLayout.encloseCenter( Signin)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        SendCode.requestFocus();
        
        SendCode.addActionListener(e -> 
        {
              ServiceUser.getInstance().forgetpassword(email.getText().toString(), res);

           
        });
        
        
        
        //Mp oublie event
        
       // mp.addActionListener((e) -> {
           
         //   new ActivateForm(res).show();
            
            
       // });
        
    }
    
}

