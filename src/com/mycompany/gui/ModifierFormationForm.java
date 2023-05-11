/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Formation;
import com.mycompany.services.ServiceFormation;

/**
 *
 * @author Lenovo
 */
public class ModifierFormationForm  extends BaseForm {
    
    Form current;

    /**
     *
     * @param res
     * @param r
     */
    public ModifierFormationForm(Resources res , Formation  f) {
  super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout Reclamation");
        getContentPane().setScrollVisible(false);
        
        
        //super.addSideMenu(res);  
        
        TextField titre= new TextField(f.getTitre(), "titre",20,TextField.ANY);
        TextField description= new TextField(f.getDescription(), "description",20,TextField.ANY);
        TextField type= new TextField(f.getType(), "type",20,TextField.ANY);
        TextField pays= new TextField(f.getPays(), "pays",20,TextField.ANY);
        TextField debut= new TextField(f.getDebut(), "debut",20,TextField.ANY);
        TextField fin= new TextField(f.getFin(), "fin",20,TextField.ANY);
    
        
        
         Button btnModifier = new Button("Modifier");
       btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
       
       btnModifier.addPointerPressedListener(l ->   { 
         
       
       //appel fonction modfier reclamation men service
       
       if(ServiceFormation.getInstance().modifierFormation(f)) { // if true
           f.setTitre(titre.getText());
           f.setDescription(description.getText());
           f.setType(type.getText());
           f.setPays(pays.getText());
           f.setDebut(debut.getText());
           f.setFin(fin.getText());
           new Listeformation(res).show();
       }
        });
       Button btnAnnuler = new Button("Annuler");
       btnAnnuler.addActionListener(e -> {
           new Listeformation(res).show();
           
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
                new FloatingHint(titre),
                createLineSeparator(),
                new FloatingHint(description),
                createLineSeparator(),
                new FloatingHint(type),
                createLineSeparator(),
                new FloatingHint(pays),
                createLineSeparator(),
                new FloatingHint(debut),
                createLineSeparator(),
                new FloatingHint(fin),
                createLineSeparator(),
             
                  //ligne de séparation
                btnModifier,
                btnAnnuler
                
               
        );
        
        add(content);
        show();
    }
    

    
}
