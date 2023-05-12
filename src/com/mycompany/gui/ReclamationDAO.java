/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Reclamation;
import com.mycompany.gui.Areclamation;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


/**
 *
 * @author dell
 */
public class ReclamationDAO {
    

    private ConnectionRequest connectionRequest;
    public static Form listOfReclamations;

    

    public void addReclamation(Reclamation reclamation) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog d = new Dialog("ajouter une reclamation");
                TextArea popupBody = new TextArea("succes d'ajout de reclamation");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                Button closeBtn = new Button("Close");
                closeBtn.addActionListener((evt) -> d.dispose());
                d.add(BorderLayout.SOUTH, closeBtn);

                d.showDialog();

            }
        };
        connectionRequest.setUrl("http://localhost/rayen/insert.php?typeReclamation=" + reclamation.getTypeReclamation()
                + "&contenu=" + reclamation.getContenu()
                + "&datepro=" + reclamation.getDatepro()
                + "&idReclamateur=" + reclamation.getIdReclamateur()
                + "&idCibleReclamation=" + reclamation.getIdCibleReclamation());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void removeReclamation(Reclamation b) {
        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog d = new Dialog("supprimer une reclamation");
                TextArea popupBody = new TextArea("succes de suppression de reclamation");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);

                Button closeBtn = new Button("Close");
                closeBtn.addActionListener((evt) -> d.dispose());
                d.add(BorderLayout.SOUTH, closeBtn);

                d.showDialog();

            }
        };
        connectionRequest.setUrl("http://localhost/rayen/remove.php?idReclamation=" + b.getIdReclamation());
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void findAllReclamation() {
        connectionRequest = new ConnectionRequest() {
            List<Reclamation> reclamations = new ArrayList<>();

            @Override
            protected void readResponse(InputStream in) throws IOException {

                JSONParser json = new JSONParser();
                try {
                    Reader reader = new InputStreamReader(in, "UTF-8");

                    try {
                        Map<String, Object> data = json.parseJSON(reader);
                        // ... process the parsed data

                        List<Map<String, Object>> content = (List<Map<String, Object>>) data.get("root");
                        reclamations.clear();

                        for (Map<String, Object> obj : content) {
                            reclamations.add(new Reclamation(Integer.parseInt((String) obj.get("idReclamation")), (String) obj.get("typeReclamation"), (String) obj.get("contenu"), (String) obj.get("datepro"), Integer.parseInt((String) obj.get("idReclamateur")),Integer.parseInt((String) obj.get("idCibleReclamation")))
                            );
                        }
                    } catch (IOException err) {
                        Log.e(err);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            protected void postResponse() {
                //System.out.println(libs.size());
                listOfReclamations = new Form();
                Button addrecBtn;
                addrecBtn = new Button("Ajouter une Reclamation");
                addrecBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddReclamation addrecui = new AddReclamation();
                addrecui.show();
            }
        });
                com.codename1.ui.List uiLibsList = new com.codename1.ui.List();
                ArrayList<String> libsNoms = new ArrayList<>();
                for (Reclamation l : reclamations) {
                    libsNoms.add(l.getContenu());
                }
                com.codename1.ui.list.DefaultListModel<String> listModel = new com.codename1.ui.list.DefaultListModel<>(libsNoms);
                uiLibsList.setModel(listModel);
                uiLibsList.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Reclamation currentReclamation = reclamations.get(uiLibsList.getCurrentSelected());
                        new Areclamation(currentReclamation.getIdReclamation(), currentReclamation.getTypeReclamation(), currentReclamation.getContenu(), currentReclamation.getDatepro(), currentReclamation.getIdReclamateur(), currentReclamation.getIdCibleReclamation()).show();
                    }
                });
                listOfReclamations.setLayout(new BorderLayout());
                listOfReclamations.add(BorderLayout.NORTH, uiLibsList);
                
                listOfReclamations.show();
            }
        };
        connectionRequest.setUrl("http://localhost/rayen/getreclamations.php");
        NetworkManager.getInstance().addToQueue(connectionRequest);
    }

    public void updateReclamation(Reclamation b) {

        connectionRequest = new ConnectionRequest() {
            @Override
            protected void postResponse() {
                Dialog d = new Dialog("Popup Title");
                TextArea popupBody = new TextArea("Projet updated");
                popupBody.setUIID("PopupBody");
                popupBody.setEditable(false);
                d.setLayout(new BorderLayout());
                d.add(BorderLayout.CENTER, popupBody);
                Button closeBtn = new Button("Close");
                closeBtn.addActionListener((evt) -> d.dispose());
                d.add(BorderLayout.SOUTH, closeBtn);
                d.show();
            }
        };
        /*
    connectionRequest.setUrl("http://localhost/dorra/update.php?id_user_id="+b.getId_user()
            +"&id_projet="+ b.getId_projet()
            +"&titre="+b.getTitre()
            +"&description="+b.getDescription()
            +"&prix="+b.getPrix()
            +"&competence="+b.getCompetence());*/

        connectionRequest.setUrl("http://localhost/rayen/update.php?typeReclamation=" + b.getTypeReclamation()
                + "&contenu=" + b.getContenu()
                + "&datepro=" + b.getDatepro()
                + "&idReclamateur=" + b.getIdReclamateur()
                + "&idCibleReclamation=" + b.getIdCibleReclamation()
                + "&idReclamation=" + b.getIdReclamation()
        );

        NetworkManager.getInstance().addToQueue(connectionRequest);
    }
    
}
