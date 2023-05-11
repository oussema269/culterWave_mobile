package com.culturewave.rayen;


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
import com.codename1.ui.Container;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.util.UIBuilder;
import com.culturewave.rayen.daos.ReclamationDAO;
import com.culturewave.rayen.guis.AddReclamation;
import com.culturewave.rayen.guis.Addcommande;
import com.culturewave.rayen.guis.addpanier;
import com.culturewave.rayen.guis.afficherpanier;


public class Rayen {

     private Form current;
    public static Form mainForm;
    private Button addBookBtn,listBooksBtn;
    private Container mainContainer;
    private Resources theme;

    public void init(Object context) {
        // use two network threads instead of one
        updateNetworkThreadCount(3);// why not 3 :D
        theme = UIManager.initFirstTheme("/theme");
        
        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);
        
        // Pro only feature
        Log.bindCrashProtection(true);
        
        addNetworkErrorListener(err -> {
            // prevent the event from propagating
            err.consume();
            if(err.getError() != null) {
                Log.e(err.getError());
            }
            Log.sendLogAsync();
            Dialog.show("Connection Error", "There was a networking error in the connection to " + err.getConnectionRequest().getUrl(), "OK", null);
        });    
        
        
    }
    
    public void start() {
        if(current != null){
            current.show();
            return;
        }
       // new afficherpanier(theme).show();
        UIBuilder ui = new UIBuilder();
        addBookBtn = new Button("Ajouter une Reclamation");
        addBookBtn.getUnselectedStyle().setFgColor(5542241);
        listBooksBtn = new Button("mes Reclamations");
        listBooksBtn.getUnselectedStyle().setFgColor(5542241);
        mainContainer = new Container();
        mainContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
        mainContainer.add(addBookBtn);
        mainContainer.add(listBooksBtn);
        mainForm=new Form();
        mainForm.setLayout(new BorderLayout());
        mainForm.add(BorderLayout.CENTER,mainContainer);
        addBookBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                AddReclamation addBookUi = new AddReclamation();
                addBookUi.show();
            }
        });
        listBooksBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ReclamationDAO().findAllReclamation();
            }
        });
        mainForm.show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
        
    }

}
