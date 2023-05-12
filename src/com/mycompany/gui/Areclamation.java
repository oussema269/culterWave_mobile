/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
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
public class Areclamation extends Form {
    
    
    
    private final Label l1,l2,l3,l4,l5,l6,l7;
    private final TextField typeReclamationTf,contenuTf,dateproTf,idReclamateurTf,idCibleReclamationTf,idReclamationTf;
    private final Container mainContainer;
    private final Button editBtn,removeBtn;
    private Reclamation currentReclamation;
    private Reclamation rmReclamation;
    public Areclamation(int idReclamation,String typeReclamation, String contenu, String datepro, int idReclamateur,int idCibleReclamation){
      
        this.setLayout(new BorderLayout());
        mainContainer = new Container();
        mainContainer.setLayout(new GridLayout(8,2));
        l1 = new Label("Votre Reclamation");
        //l1.getUnselectedStyle().setAlignment(Component.CENTER);
        l1.getUnselectedStyle().setFgColor(-16777216);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        l1.getUnselectedStyle().setFont(l1_font);
        
        l7 = new Label("idReclamation:");
        idReclamationTf = new TextField(String.valueOf(idReclamation)); 
        
        l6 = new Label("typeReclamation:");
        typeReclamationTf = new TextField(typeReclamation); 
        
        l2 = new Label("contenu:");
        contenuTf = new TextField(contenu); 
        
        l3 = new Label("datepro:");
        dateproTf = new TextField(datepro);
        
        l4 = new Label("idReclamateur:");
        idReclamateurTf= new TextField(String.valueOf(idReclamateur));
        
        l5 = new Label("idCibleReclamation:");
        idCibleReclamationTf= new TextField(String.valueOf(idCibleReclamation));
        
        
        
        editBtn= new Button("Edit");
        editBtn.getUnselectedStyle().setFgColor(5542241);
        
        removeBtn= new Button("Remove");
        removeBtn.getUnselectedStyle().setFgColor(5542241);
        
        mainContainer.add(l1);
        mainContainer.add(new Label());
        
        mainContainer.add(l7);
        mainContainer.add(idReclamationTf);
        
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
        mainContainer.add(editBtn);
        mainContainer.add(removeBtn);
      
        
        
        rmReclamation = new Reclamation(idReclamation,typeReclamationTf.getText(), contenuTf.getText(), dateproTf.getText(), Integer.parseInt(idReclamateurTf.getText()),Integer.parseInt(idCibleReclamationTf.getText()));
        editBtn.addActionListener(evt -> {
            currentReclamation = new Reclamation(idReclamation,typeReclamationTf.getText(), contenuTf.getText(), dateproTf.getText(), Integer.parseInt(idReclamateurTf.getText()),Integer.parseInt(idCibleReclamationTf.getText()));
        /*   System.out.println("id_projet: " + currentProjet.getId_projet());
System.out.println("titre: " + currentProjet.getTitre());
System.out.println("description: " + currentProjet.getDescription());
System.out.println("prix: " + currentProjet.getPrix());
System.out.println("competence: " + currentProjet.getCompetence());*/

    new ReclamationDAO().updateReclamation(currentReclamation);
    
});

        removeBtn.addActionListener(new ActionListener() {
             
            @Override
            public void actionPerformed(ActionEvent evt) {
                
                new ReclamationDAO().removeReclamation(rmReclamation);
            }
        });
        this.add(BorderLayout.NORTH, mainContainer);
        
    }
    
}
