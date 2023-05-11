
package com.culturewave.rayen.guis;


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
import com.culturewave.reclamation.entities.produit;
import com.culturewave.rayen.services.ServiceProduit;

/**
 *
 * @author Lenovo
 */
public class ModifierProduitForm extends BaseForm {
    
    Form current;

    /**
     *
     * @param res
     * @param r
     */
    public ModifierProduitForm(Resources res , produit r) {
         super("Newsfeed",BoxLayout.y()); //herigate men Newsfeed w l formulaire vertical
    
        Toolbar tb = new Toolbar(true);
        current = this ;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajout produit");
        getContentPane().setScrollVisible(false);
        
        
        super.addSideMenu(res);
       
          TextField id_cat = new TextField(String.valueOf(r.getId_cat()), "ID catégorie", 20, TextField.ANY);
          TextField lib = new TextField(r.getLib(), "Nom du produit", 20, TextField.ANY);
          TextField stock = new TextField(String.valueOf(r.getStock()), "Stock", 20, TextField.NUMERIC);
          TextField prix = new TextField(String.valueOf(r.getPrix()), "Prix", 20, TextField.NUMERIC);

        
        ComboBox etatCombo = new ComboBox();
        
        etatCombo.addItem("Non Traiter");
        
        etatCombo.addItem("Traiter");
      
        
        
        
        
        id_cat.setUIID("NewsTopLine");
        lib.setUIID("NewsTopLine");
        stock.setUIID("NewsTopLine");
        prix.setUIID("NewsTopLine");
         Button btnModifier = new Button("Modifier");
        btnModifier.setUIID("Button");
       
       //Event onclick btnModifer
             btnModifier.addPointerPressedListener(l -> {
                    
                      r.setId_cat(Integer.parseInt(id_cat.getText()));
                      r.setLib(lib.getText());
                      r.setStock(Integer.parseInt(stock.getText()));
                      r.setPrix((float) Double.parseDouble(prix.getText()));
                      
                      // appel fonction modfier produit men service
                        if (ServiceProduit.getInstance().modifierproduit(r)) { // if true
                                   new ListProduitForm(res).show();
                                 }
                                    });
                                Button btnAnnuler = new Button("Annuler");
                                btnAnnuler.addActionListener(e -> {
                                    new ListProduitForm(res).show();
                                });
       
       
       Label l2 = new Label("");
   
        Label l1 = new Label();
        
                    Container content = BoxLayout.encloseY(
                    l1, l2,
                    new FloatingHint(id_cat),
                    createLineSeparator(),
                    new FloatingHint(lib),
                    createLineSeparator(),
                    new FloatingHint(stock),
                    createLineSeparator(),
                    new FloatingHint(prix),
                    createLineSeparator(),
                    btnModifier,
                    btnAnnuler
            );

            add(content);
            show();
        
    }
}
