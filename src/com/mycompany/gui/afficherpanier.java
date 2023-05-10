/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import static com.codename1.ui.events.ActionEvent.Type.Theme;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import com.mycomany.entities.panier;
import com.mycomany.entities.produit;
import com.mycompany.services.ServicePanier;
import com.codename1.components.ImageViewer;
import static com.codename1.ui.CN.*;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import java.io.IOException;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.io.NetworkEvent;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Font;
import com.codename1.ui.Slider;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.mycomany.entities.user;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rassa
 */
public class afficherpanier extends BaseForm{
      Form current;
                           user myObject = (user) Storage.getInstance().readObject("myObjectKey");


    public afficherpanier(Resources res) {
        
        super("panier", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("panier");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        addTab(swipe, res.getImage("timeline-background.jpg"), spacer1, "  ", "", " ");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
                                            
        
        ButtonGroup barGroup = new ButtonGroup();
                  Container co=new Container(BoxLayout.xCenter());
                   // ArrayList <panier> panier = new ArrayList();
                   
                   panier panier = ServicePanier.getInstance().getAllPanier(48);
                   ArrayList<produit> prod=panier.getProducts();
                   float total=ServicePanier.getInstance().gettotal(48);                                                                                               
                    int count =ServicePanier.getInstance().getAllPanier(48).getCount();
                    
                    
                    
Button pdfButton = new Button("Export PDF");
pdfButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
        try {
            generatePDF(prod);
            Dialog.show("Succès", "Le fichier PDF a été créé avec succès.", "OK", null);
        } catch (IOException ex) {
            Dialog.show("Erreur", "Impossible de créer le fichier PDF.", "OK", null);
        } catch (DocumentException ex) {
            Logger.getLogger(afficherpanier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        });

// add the button to your user interface
add(pdfButton);

        /*                  
  Button commande = new Button("commander");
  commande.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                               new addcommande(res).show();
                                                    }   
                                            });
                                 
                           
                            add(commande);
                           */

                 for (produit fi : prod) {
                     Container ct = new Container(BoxLayout.y());
                     
                            //Label l3 = new Label("totale : "+panier.getTotal_panier());
                            
                          System.out.println(panier.getId_user());
                          System.out.println(fi.getId());
                       int qt=ServicePanier.getInstance().getqt(panier.getId_user(), fi.getId());
                        //   ct.add(l3);
                           
                                Label l = new Label("nomproduit : "+fi.getLib());
                                ct.add(l);
                                Label l4 = new Label("prix : "+fi.getPrix());
                                ct.add(l4);
                           Label l2 = new Label("quantite : "+qt);
                           ct.add(l2);
                          
                           

                            

                            Button plus = new Button("+");
                            Button minus = new Button("-");
                            Button Supprimer = new Button("Supprimer");
                            plus.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                              ServicePanier.getInstance().increment(panier.getId_user(),fi.getId()); 
                               new afficherpanier(res).show();
                                                    }   
                                            });
                                 minus.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                              ServicePanier.getInstance().decrement(panier.getId_user(),fi.getId()); 
                               new afficherpanier(res).show();
                                                    }   
                                            });
                            
                            
                            
                            
                            
                          Supprimer.addActionListener(new ActionListener() {
                                            @Override
            public void actionPerformed(ActionEvent evt) {               
                if (Dialog.show("Confirmation", "Voulez vous supprimer cett produit ?", "Oui", "Annuler")) {

               ServicePanier.getInstance().deletepanier(panier.getId_user(),fi.getId());
            // Success message
            Dialog.show("Success", "produit deleted successfully", "OK", null);
             new afficherpanier(res).show();


                            }
                   
                }   
        });
                       ct.add(plus);
                       ct.add(minus);
                       ct.add(Supprimer);


                       Label separator = new Label("","Separator");
                       ct.add(separator);
                     add(ct); 
               }
                 Label tot = new Label("Total" + total);
tot.getAllStyles().setFgColor(0x00FF00); // set the foreground color to green
tot.getAllStyles().setAlignment(Component.BOTTOM | Component.RIGHT); // align the label to the bottom right
add(tot);
Label count1 = new Label("panier" + count);
count1.getAllStyles().setFgColor(0x00FF00); // set the foreground color to green
count1.getAllStyles().setAlignment(Component.BOTTOM | Component.LEFT); // align the label to the bottom right
add(count1);



                  
    }
        
    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                image,
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    public static void generatePDF(List<produit> prod) throws IOException, DocumentException {
                              user myObject = (user) Storage.getInstance().readObject("myObjectKey");

                panier panier = ServicePanier.getInstance().getAllPanier(48);
                   
        // create a new PDF document
        Document document = new Document();

        // create a file on the device's file system to write the PDF to
String filePath = FileSystemStorage.getInstance().getAppHomePath() + "product_list.pdf";
        OutputStream os = FileSystemStorage.getInstance().openOutputStream(filePath);

          try {
              // create a PDF writer to write the document to the output stream
              PdfWriter.getInstance(document, os);
          } catch (DocumentException ex) {
              Logger.getLogger(afficherpanier.class.getName()).log(Level.SEVERE, null, ex);
          }

        // open the document
        document.open();

        // add a heading to the document
        document.add(new Paragraph("Liste des produits"));
          prod = panier.getProducts();

                   float total=ServicePanier.getInstance().gettotal(48);                                                                                               
                    int count =ServicePanier.getInstance().getAllPanier(48).getCount();
        // add a table to the document with the product details
        for (produit fi : prod) {
            document.add(new Paragraph("Nom du produit : " + fi.getLib()));
            document.add(new Paragraph("Prix : " + fi.getPrix()));
            // add other product details here
            document.add(new Paragraph("\n"));
        }
                    document.add(new Paragraph("\n"));
            document.add(new Paragraph("total"+total));

        // close the document
        document.close();

        // close the output stream
        os.close();
    }
    
    
}
