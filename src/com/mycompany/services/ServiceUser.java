/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.io.Storage;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.user;
import com.mycomany.utils.Statics;
import com.mycompany.gui.MakeNewPass;
import com.mycompany.gui.ProfileForm;
import com.mycompany.gui.SessionManager;
import com.mycompany.gui.SignInForm;
import java.util.Map;

/**
 *
 * @author sarra
 */
public class ServiceUser {
    //singleton 

    public static ServiceUser instance = null;

    public static boolean resultOk = true;
    String json;
static user user2 =new user();
    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceUser getInstance() {
        if (instance == null) {
            instance = new ServiceUser();
        }
        return instance;
    }

    public ServiceUser() {
        req = new ConnectionRequest();

    }
    //Signup

    public void signup(TextField nom, TextField prenom, TextField email, TextField password, ComboBox<String> roles, Resources res) {

        String url = Statics.BASE_URL + "/api/register?nom=" + nom.getText().toString()
                + "&prenom=" + prenom.getText().toString()
                + "&email=" + email.getText().toString()
                + "&password=" + password.getText().toString() + "&roles=" + roles.getSelectedItem().toString();

        req.setUrl(url);

        //Control saisi
        if (nom.getText().equals("") && password.getText().equals("") && email.getText().equals("")) {

            Dialog.show("Erreur", "Veuillez remplir les champs", "OK", null);

        }

        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e) -> {

            //njib data ly7atithom fi form 
            byte[] data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 

            //    System.out.println("data ===>" + responseData);
        }
        );

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public void signin(TextField email, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/api/log?email=" + email.getText().toString() + "&password=" + password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json == "failed") {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);
                    user user1 = new user();
                    Map<String, Object> mapuser = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    //   System.out.println(mapuser);

                    //Session 
                    if (mapuser.size() == 1) // l9a user
                    {
                        Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                    }

                    // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    {
                        user1.setNom((String) mapuser.get("nom"));
                        user1.setPrenom((String) mapuser.get("prenom"));
                        user1.setEmail((String) mapuser.get("email"));
                        user1.setMotdepasse((String) mapuser.get("password"));
                        user1.setId(Double.valueOf((double) mapuser.get("id")).intValue());
                        user1.setType((String) mapuser.get("role"));
                        Storage.getInstance().writeObject("myObjectKey", user1);
                        user myObject = (user) Storage.getInstance().readObject("myObjectKey");
                        System.out.println("test"+myObject);

                        new ProfileForm(rs).show();
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void edit(int id, TextField nom, TextField prenom, ComboBox<String> roles, TextField email, TextField password, Resources rs) {

        String url = Statics.BASE_URL + "/api/edit?email=" + email.getText().toString() + "&password=" + password.getText().toString() + "&nom=" + nom.getText().toString() + "&prenom=" + prenom.getText().toString() + "&id=" + id + "&role=" + roles.getSelectedItem().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);
                    user user1 = new user();
                    Map<String, Object> mapuser = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    //   System.out.println(mapuser);

                    //Session 
                    if (mapuser.size() > 0) // l9a user
                    // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    {
                        user1.setNom((String) mapuser.get("nom"));
                        user1.setPrenom((String) mapuser.get("prenom"));
                        user1.setEmail((String) mapuser.get("email"));
                        user1.setMotdepasse((String) mapuser.get("password"));
                        user1.setId(Double.valueOf((double) mapuser.get("id")).intValue());
                        user1.setType((String) mapuser.get("role"));
                        Storage.getInstance().writeObject("myObjectKey", user1);
                        /*   user myObject = (user) Storage.getInstance().readObject("myObjectKey");
                        System.out.println("test"+myObject);*/
                            
                        new ProfileForm(rs).show();
                    }

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void delete(int id, Resources rs) {

        String url = Statics.BASE_URL + "/api/delete?id=" + id;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);

                    new SignInForm(rs).show();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

    public void forgetpassword(String email, Resources rs) {

        String url = Statics.BASE_URL + "/api/Forget?email="+email;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);

                    new MakeNewPass(rs).show();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    public void makenwpassword(String email,String password,String code, Resources rs) {

        String url = Statics.BASE_URL + "/api/MakeNewPassword?email="+email+"&password="+password+"&code="+code;
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        System.out.println(url);

        req.addResponseListener((e) -> {

            JSONParser j = new JSONParser();

            String json = new String(req.getResponseData()) + "";

            try {

                if (json.equals("failed")) {
                    Dialog.show("Echec d'authentification", "Username ou mot de passe éronné", "OK", null);
                } else {
                    //System.out.println("data ==" + json);

                    new SignInForm(rs).show();

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }

}
