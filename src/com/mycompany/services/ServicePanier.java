/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.mycomany.utils.Statics;
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
import com.mycomany.entities.panier;
import com.mycomany.entities.produit;


/**
 *
 * @author rassa
 */
public class ServicePanier {
    public  ArrayList<panier> paniers =new ArrayList();
    panier pan;
    int quantitee;
    float total;
      public static ServicePanier instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<panier> listCategorie=new ArrayList<>();

    public ServicePanier() {
        req = new ConnectionRequest();
    }
//rendre l constructeur prive w tkhalik testaana 
    public static ServicePanier getInstance() {
        if (instance == null) {
            instance = new ServicePanier();
        }
        return instance;
    }

    
    
     public boolean addpanier(panier panier,int id_produit) {

      //String description=t.getDescription();
        
       
         String url =Statics.BASE_URL+"/addcart?idClient="+panier.getId_user()+"&idProduct="+id_produit+"&qt="+1;

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
     
     
     
     
       public panier parsePanier(String jsonText) {
         float totale=0;
         panier t = new panier();
         
        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
            paniers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PanierListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) PanierListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               float id = Float.parseFloat(obj.get("idpanier").toString());
              
               float iduser=Float.parseFloat(obj.get("iduser").toString());
                 float quantite=Float.parseFloat(obj.get("quantite").toString());
                String nomproduit = obj.get("nomproduit").toString();
                 float prix=Float.parseFloat(obj.get("prix").toString());
                 float idprod=Float.parseFloat(obj.get("idprod").toString());
                 float count=Float.parseFloat(obj.get("count").toString());
                 
                 
                 produit p=new produit((int)idprod, nomproduit,prix);
                 
                 
                 t.addproduct(p);
                 
                 
                  t.setQuantite((int)quantite);
                 totale+=prix*quantite;
                 
                t.setId_panier((int) id);
               
                t.setId_user((int)iduser);
               // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
               t.setCount((int)count);
                
                paniers.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      //  t.setTotal_panier(totale);
        //paniers.add(t);
        return t;
    }
       
       
       
       
       
       
       
       public float parsetotal(String jsonText) {
         float totale=0;
         panier t = new panier();
         
        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
            paniers = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> PanierListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) PanierListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               float id = Float.parseFloat(obj.get("idpanier").toString());
              
               float iduser=Float.parseFloat(obj.get("iduser").toString());
                 float quantite=Float.parseFloat(obj.get("quantite").toString());
                String nomproduit = obj.get("nomproduit").toString();
                 float prix=Float.parseFloat(obj.get("prix").toString());
                 float idprod=Float.parseFloat(obj.get("idprod").toString());
                 
                 
                 produit p=new produit((int)idprod, nomproduit,prix);
                 
                 
                 t.addproduct(p);
                 
                 
                  t.setQuantite((int)quantite);
                 totale+=prix*quantite;
                 
                t.setId_panier((int) id);
               
                t.setId_user((int)iduser);
               // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
               
                
                paniers.add(t);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      //  t.setTotal_panier(totale);
        //paniers.add(t);
        return totale;
    }

       public float gettotal(int id){
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url =Statics.BASE_URL+"/affcart?id="+id;
          req.setUrl(url);
          req.setPost(false);
          req.addRequestHeader("accept", "application/json");
          req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
            total = parsetotal(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return total;
     }
    public panier getAllPanier(int id){
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url =Statics.BASE_URL+"/affcart?id="+id;
          req.setUrl(url);
          req.setPost(false);
          req.addRequestHeader("accept", "application/json");
          req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
            pan = parsePanier(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return pan;
     }
    
     
    
     public float parseqt(String jsonText) {
         
         
          float  quantite = 0;
         
        System.out.println("+++++++++++");
        System.out.println(jsonText);
        System.out.println("++++++++++++");
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> PanierListJson
                    = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            java.util.List<Map<String, Object>> list = (java.util.List<Map<String, Object>>) PanierListJson.get("root");
            for (Map<String, Object> obj : list) {
                
               
              
               //float iduser=Float.parseFloat(obj.get("iduser").toString());
                 
                  quantite=Float.parseFloat(obj.get("quantite").toString());
              
                 
                 
                 
               
                // t.setId_user((int)iduser);
               // t.setStatus(((int) Float.parseFloat(obj.get("status").toString())));
               
                
                
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
      //  t.setTotal_panier(totale);
        //paniers.add(t);
        return quantite;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
       public int getqt(int id,int idprod){
           
//          String url = Statics.BASE_URL+"/mobile/listReclamtion/";
          String url =Statics.BASE_URL+"/affqt?id="+id+"&idprod="+idprod;
          req.setUrl(url);
          req.setPost(false);
          req.addRequestHeader("accept", "application/json");
          req.addResponseListener(new ActionListener<NetworkEvent>() {
    @Override
    public void actionPerformed(NetworkEvent evt) {

        byte[] responseData = req.getResponseData();
        if (responseData != null) {
            quantitee = (int)parseqt(new String(responseData));
            req.removeResponseListener(this);
//            String response = new String(responseData);
//            System.out.println(response);
        } else {
            System.out.println("Response data is null");
        }
    }
});

          NetworkManager.getInstance().addToQueueAndWait(req);
         
         
         return quantitee;
     }
        
     
     
     
     
     public boolean deletepanier(int id , int idprod) {
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url =Statics.BASE_URL+"/deletepanier?iduser="+id+"&idprod="+idprod;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
       public boolean increment(int id,int idprod) {
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url =Statics.BASE_URL+"/increment?id="+id+"&idprod="+idprod;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
     
     
     
         public boolean decrement(int id,int idprod) {
        System.out.println("********");
        //String url = Statics.BASE_URL + "create?name=" + t.getName() + "&status=" + t.getStatus();
       String url =Statics.BASE_URL+"/decrement?id="+id+"&idprod="+idprod;
        req.setUrl(url);
        req.setPost(false);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
    
    
}
