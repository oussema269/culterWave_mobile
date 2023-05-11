/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycomany.entities.Evennement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycomany.utils.Statics;
import java.util.Map;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author wajihbenhmida
 */
public class ServiceEvennement {
       public static ServiceEvennement instance = null ;
    
    public static boolean resultOk = true;
    
    
    private ConnectionRequest req;
    
    
      public static ServiceEvennement getInstance() {
        if(instance == null )
            instance = new ServiceEvennement();
        return instance ;
    }
     public ServiceEvennement() {
        req = new ConnectionRequest();
        
    }
     
     public void ajoutEvent(Evennement evennement) {
        
        String url =Statics.BASE_URL+"/evajoutercodenameone?nom="+evennement.getNom()+"&description="+evennement.getDescription()+
                "&lieu="+evennement.getLieu()+"&prix="+evennement.getPrix()+"&date="+evennement.getDate()+"&nb_participants="+evennement.getNb_participants()+"&type_evenement="+evennement.getType_evennement(); 
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
     
public ArrayList<Evennement>affichageFormation() {
        ArrayList<Evennement> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/readJson";
        req.setUrl(url);
        
        req.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp ;
            jsonp = new JSONParser();
            
            try {
                
                Map<String,Object>mapReclamations = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                
                List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapReclamations.get("root");
                System.out.println("zaaaaaaaaaaa"+listOfMaps);
                for(Map<String, Object> obj : listOfMaps) {
                    Evennement fo = new Evennement();
                    
                    //dima id fi codename one float 5outhouha
                    float id = Float.parseFloat(obj.get("id").toString());
                    
                    String nom = obj.get("nom").toString();
                    
                    String description = obj.get("description").toString();
                    String lieu = obj.get("lieu").toString();
                    String prix = obj.get("prix").toString();
                    String date = obj.get("date").toString();
                    String nb_participants = obj.get("nbParticipants").toString();
                    String type_evenement = obj.get("typeEvenement").toString();
                    
                    
                   fo.setId((int)id);
                    fo.setNom(nom);
                    fo.setDescription(description);
                    fo.setLieu(lieu);
                    fo.setPrix(prix);
                    fo.setDate(date);
                    fo.setNb_participants(nb_participants);
                    fo.setType_evennement(type_evenement);
                    
                    //Date
                    result.add(fo);
                    
                    
                    
                }
            
            }catch(Exception ex) {
                
                ex.printStackTrace();
            }
        });  
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    

}


  //delete
       
     public boolean deleteEvennement(int id ) {
        String url = Statics.BASE_URL +"/evdeleteJson/"+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
     
     
                //Update 

public boolean modifierevennement(Evennement evennement) {
        


        
        
String url = Statics.BASE_URL + "/modifiercodenameone/" + evennement.getId()+
       "&nom="+evennement.getNom()+
        "&description="+evennement.getDescription()+
        "&lieu="+evennement.getLieu()+
        "&prix="+evennement.getPrix()+
        "&date="+evennement.getDate()+
        "&nb_participants="+evennement.getNb_participants()+
        "&type_evenement="+evennement.getType_evennement();   
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
   NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }

}
     
     











     

