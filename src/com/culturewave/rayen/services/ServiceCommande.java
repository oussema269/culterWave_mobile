/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.culturewave.rayen.services;
import com.culturewave.reclamation.entities.commande;
import DB.DB;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import static com.codename1.io.Log.p;
import static com.codename1.io.Log.p;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.culturewave.reclamation.entities.panier;

/**
 *
 * @author rayen
 */
public class ServiceCommande {
            commande com;
    public  ArrayList<panier> paniers =new ArrayList();
    panier pan;
    int quantitee;
    float total;
      public static ServiceCommande instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
  

    public ServiceCommande() {
        req = new ConnectionRequest();
    }
//rendre l constructeur prive w tkhalik testaana 
    public static ServiceCommande getInstance() {
        if (instance == null) {
            instance = new ServiceCommande();
        }
        return instance;
    }
    
    
    
    public boolean addcommande( int iduser , String nom,String prenom,String email) {

      //String description=t.getDescription();
        
       
         String url =DB.BASE_URL+"/addcommande?iduser="+iduser+"&nom="+nom+"&prenom="+prenom+"&email="+email;

        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK type ya true ya false
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
    
       public commande parseCommande(String jsonText) {
         float totale=0;
         commande c = null ;
                      ArrayList<commande> command = new ArrayList<commande>();

        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> PanierListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) PanierListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               float id = Float.parseFloat(obj.get("idcommande").toString());        
               float iduser=Float.parseFloat(obj.get("iduser").toString());
                String nom = obj.get("nom").toString();
                  String prenom = obj.get("prenom").toString();
                 float total=Float.parseFloat(obj.get("total").toString());
                 
                 
                  c=new commande((int)id, (int)iduser ,nom, prenom,total);
                 
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      //  t.setTotal_panier(totale);
        //paniers.add(t);
        return c;
    }
       
       
    
    
     public commande getAllCommand(int id){
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url =DB.BASE_URL+"/affcommande?id="+id;
          req.setUrl(url);
          req.setPost(false);
          req.addRequestHeader("accept", "application/json");
          req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
             com = parseCommande(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return com;
     }

    public boolean Addcommande(int i, String text, String text0, String text1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
