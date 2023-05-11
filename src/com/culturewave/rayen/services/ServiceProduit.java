/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.culturewave.rayen.services;
import com.culturewave.reclamation.entities.produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.culturewave.rayen.utils.Statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author azizt
 */
public class ServiceProduit {
      //singleton 

    public static ServiceProduit instance = null;

    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceProduit getInstance() {
        if (instance == null) {
            instance = new ServiceProduit();
        }
        return instance;
    }

    public ServiceProduit() {
        req = new ConnectionRequest();

    }
        //ajout 

    /**
     *
     * @param Produit
     */
    public void ajoutProduit(produit Produit) {
        String url = Statics.BASE_URL+ "/addProduitMobile?idCat="+Produit.getId_cat()+"&lib="+Produit.getLib()+"&prix="+Produit.getPrix()+"&stock="+Produit.getStock();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
           
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
      //affichage

    /**
     *
     * @return
     */
    
    public ArrayList<produit>affichageproduits() {
        ArrayList<produit> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/AllProduitsMobile";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapproduits = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapproduits.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        produit re = new produit();
                        
                        //dima id fi codename one float 5outhouha
                        float id = Float.parseFloat(obj.get("id").toString());
                        float id_cat = Float.parseFloat(obj.get("id_cat").toString());    
                        String lib = obj.get("lib").toString();
                        float stock = Float.parseFloat(obj.get("stock").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
                        

                        re.setId((int)id);
                        re.setId_cat((int)id_cat);
                        re.setLib(lib);
                        re.setStock((int)stock);
                        re.setPrix((int)prix);
                        //insert data into ArrayList result
                        result.add(re);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }
    
        
    //Delete 

    /**
     *
     * @param id
     * @return
     */
    public boolean deleteProduit(int id ) {
        String url = Statics.BASE_URL +"/deleteProduitMobile/"+id;
  
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

    /**
     *
     * @param Produit
     * @return
     */
    public boolean modifierproduit(produit Produit) {
        
        
         String url =Statics.BASE_URL+"/updateProduitMobile/"+Produit.getId()+"?idCat="+Produit.getId_cat()+"&lib="+Produit.getLib()+"&prix="+Produit.getPrix()+"&stock="+Produit.getStock();
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
