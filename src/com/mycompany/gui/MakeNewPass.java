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
public class MakeNewPass extends BaseForm {

    public MakeNewPass(Resources res) {
        super(new BorderLayout());

        if (!Display.getInstance().isTablet()) {
            BorderLayout bl = (BorderLayout) getLayout();
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
        TextField code = new TextField("", "code", 20, TextField.ANY);
        TextField password = new TextField("", "password", 20, TextField.PASSWORD);

        email.setSingleLineTextArea(false);
        code.setSingleLineTextArea(false);
           password.setSingleLineTextArea(false);
        Button makepass = new Button("complete");
        Button signin = new Button("Sign in");


        signin.addActionListener(e -> new SignInForm(res).show());
        signin.setUIID("Link");

        Label doneHaveAnAccount = new Label("Vous n'avez aucune compte?");

        Container content = BoxLayout.encloseY(
                new FloatingHint(email),
                createLineSeparator(),
                new FloatingHint(code),
                createLineSeparator(),
                new FloatingHint(password),
                createLineSeparator(),
                makepass,
                FlowLayout.encloseCenter(signin)
        );
        content.setScrollableY(true);
        add(BorderLayout.SOUTH, content);
        makepass.requestFocus();

        makepass.addActionListener(e
                -> {
           ServiceUser.getInstance().makenwpassword(email.getText().toString(), password.getText().toString(), code.getText().toString(),res);

        });

        //Mp oublie event
        // mp.addActionListener((e) -> {
        //   new ActivateForm(res).show();
        // });
    }

}
