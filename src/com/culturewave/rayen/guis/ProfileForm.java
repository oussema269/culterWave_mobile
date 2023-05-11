/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package com.culturewave.rayen.guis;

import com.codename1.components.ScaleImageLabel;
import com.codename1.io.Storage;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.culturewave.reclamation.entities.user;
import com.culturewave.rayen.services.ServiceUser;
import java.util.Vector;

/**
 * The user profile form
 *
 * @author Shai Almog
 */
public class ProfileForm extends BaseForm {
 static int u;
    public ProfileForm(Resources res) {
        super("Newsfeed", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Profile");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);

        tb.addSearchCommand(e -> {
        });

        user myObject = (user) Storage.getInstance().readObject("myObjectKey");
        Image img = res.getImage("profile-background.jpg");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 3) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 3);
        }
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

        Label facebook = new Label("786 followers", res.getImage("facebook-logo.png"), "BottomPad");
        Label twitter = new Label("486 followers", res.getImage("twitter-logo.png"), "BottomPad");
        facebook.setTextPosition(BOTTOM);
        twitter.setTextPosition(BOTTOM);

        add(LayeredLayout.encloseIn(
                sl,
                BorderLayout.south(
                        GridLayout.encloseIn(3,
                                facebook,
                                FlowLayout.encloseCenter(
                                        new Label(res.getImage("profile-pic.jpg"), "PictureWhiteBackgrond")),
                                twitter
                        )
                )
        ));

        TextField username = new TextField(myObject.getNom());
        username.setUIID("TextFieldBlack");
        addStringValue("Username", username);
        u=myObject.getId();
        TextField filedprenom = new TextField(myObject.getPrenom());
        filedprenom.setUIID("TextFieldBlack");
        addStringValue("filedprenom", filedprenom);

        TextField email = new TextField(myObject.getEmail(), "E-Mail", 50, TextField.EMAILADDR);
        email.setUIID("TextFieldBlack");
        addStringValue("E-Mail", email);

        TextField password = new TextField(myObject.getMotdepasse(), "Password", 20, TextField.PASSWORD);
        password.setUIID("TextFieldBlack");
        addStringValue("Password", password);

        Vector<String> vectorRole;
        vectorRole = new Vector();

        vectorRole.add("user");
        vectorRole.add("formateur");
        vectorRole.add("vendeur");

        ComboBox<String> roles = new ComboBox<>(vectorRole);
        addStringValue("Role", roles);
        Button next = new Button("Edit");
        Button delete = new Button("Delete account");

        add(next);
        add(delete);

        next.requestFocus();
        next.addActionListener((e) -> {

            ServiceUser.getInstance().edit(
                    myObject.getId(),
                    username,
                    filedprenom,
                    roles,
                    email,
                    password,
                    res
            );
            Dialog.show("Success", "account is saved", "OK", null);
            new ProfileForm(res).show();
        });

        delete.requestFocus();
        delete.addActionListener((e) -> {

            ServiceUser.getInstance().delete(
                    myObject.getId(),
                    res
            );
            Dialog.show("Success", "account is deleted", "OK", null);
            Storage.getInstance().clearStorage();

            // Show the WalkthruForm
            new SignInForm(res).show();

        });

    }

    private void addStringValue(String s, Component v) {
        add(BorderLayout.west(new Label(s, "PaddedLabel")).
                add(BorderLayout.CENTER, v));
        add(createLineSeparator(0xeeeeee));
    }
}
