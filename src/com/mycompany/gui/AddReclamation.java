/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.GridLayout;
import com.mycomany.entities.Reclamation;

import com.mycomany.utils.Statics;
/**
 *
 * @author dell
 */
public class AddReclamation extends Form {
    
    
    private final Label l1,l2,l3,l4,l5,l6;
    private final TextField typeReclamationTf,contenuTf,dateproTf,idReclamateurTf,idCibleReclamationTf;
    private final Container mainContainer;
    private final Button addBtn;
    
   public AddReclamation(){
       
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Ajouter un nouveau projet");
        l1.setAlignment(CENTER);
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        
        l6 = new Label("id_user:");
        typeReclamationTf = new TextField();
        
        l2 = new Label("Titre:");
        contenuTf = new TextField(); 
        
        l3 = new Label("Description:");
        dateproTf = new TextField();
        
        l4 = new Label("Prix:");
        idReclamateurTf= new TextField();;
        
        l5 = new Label("Competence:");
        idCibleReclamationTf= new TextField();
        
        addBtn= new Button("Add");
        addBtn.getUnselectedStyle().setFgColor(5542241);
        
        mainContainer.add(l1);
        mainContainer.add(new Label());
        
        mainContainer.add(l6);
        mainContainer.add(typeReclamationTf);
        mainContainer.add(l2);
        
        mainContainer.add(l3);
        
        
        mainContainer.add(contenuTf);
        mainContainer.add(dateproTf);
        
        mainContainer.add(l4);
        
        mainContainer.add(l5);
        
        mainContainer.add(idReclamateurTf);
        mainContainer.add(idCibleReclamationTf);
        mainContainer.add(addBtn);
        
        
        addBtn.addActionListener((ActionListener) (ActionEvent evt) -> {
            // add a book
            Reclamation typedReclamation = new Reclamation(typeReclamationTf.getText(), contenuTf.getText(), dateproTf.getText(), Integer.parseInt(idReclamateurTf.getText()),Integer.parseInt(idCibleReclamationTf.getText()));
            new  ReclamationDAO().addReclamation(typedReclamation);
            });
        this.add(BorderLayout.NORTH, mainContainer);
   }
    
}
