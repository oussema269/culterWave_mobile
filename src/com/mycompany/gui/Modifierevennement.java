/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Evennement;
import com.mycompany.services.ServiceEvennement;

/**
 *
 * @author wajihbenhmida
 */
public class Modifierevennement extends BaseForm {
        Form current;

    /**
     *
     * @param res
     * @param r
     */
    public Modifierevennement(Resources res , Evennement  f) {
    super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("");
        getContentPane().setScrollVisible(false);
        
        
        //super.addSideMenu(res);  
        
        TextField nom= new TextField(f.getNom(), "nom",20,TextField.ANY);
        TextField description= new TextField(f.getDescription(), "description",20,TextField.ANY);
        TextField lieu= new TextField(f.getLieu(), "lieu",20,TextField.ANY);
        TextField prix= new TextField(f.getPrix(), "prix",20,TextField.ANY);
        TextField date= new TextField(f.getDate(), "date",20,TextField.ANY);
        TextField nb_participants= new TextField(f.getNb_participants(), "nb_participants",20,TextField.ANY);
    
        TextField type_evenement= new TextField(f.getType_evennement(), "fin",20,TextField.ANY);
        
        
         Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
         
       
       //appel fonction modfier reclamation men service
       
       if(ServiceEvennement.getInstance().modifierevennement(f)) { // if true
          
           f.setNom(nom.getText());
           f.setDescription(description.getText());
           f.setLieu(lieu.getText());
           f.setPrix(prix.getText());
           f.setDate(date.getText());
           f.setNb_participants(nb_participants.getText());
           f.setType_evennement(type_evenement.getText());

           new Listevennement(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new Listevennement(res).show();
           
       });
       Label l2 = new Label("");
       
       Label l3 = new Label("");
       
       Label l4 = new Label("");
       
       Label l5 = new Label("");
       
       Label l6 = new Label("");
       
       Label l7 = new Label("");
       
       
        Label l1 = new Label();
        
        Container content = BoxLayout.encloseY(
                l1, l2, 
                new FloatingHint(nom),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(lieu),
                createLineSeparator(),
                new FloatingHint(prix),
                createLineSeparator(),
                new FloatingHint(date),
                createLineSeparator(),
                new FloatingHint(nb_participants),
                createLineSeparator(),
                 new FloatingHint(type_evenement),
                createLineSeparator(),
             
                  //ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
    }
    
}
