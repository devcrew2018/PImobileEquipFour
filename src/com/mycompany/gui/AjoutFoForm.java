/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import static com.codename1.io.Log.p;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.table.TableLayout.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.Fournisseurs;
import com.mycompany.Service.ServiceFournisseur;

/**
 *
 * @author pc-dell
 */
public class AjoutFoForm {
    Form f;
    TextField nom;
    TextField adresse;
    TextField tel;
    TextField email;
    Button btnv;
    Button btnr;
    public AjoutFoForm(){
        f=new Form("Fournisseur",new BoxLayout(BoxLayout.Y_AXIS));
      f.setUIID("ProfileContainer");
        TextField nom=new TextField("", "Nom");
        TextField adresse=new TextField("", "Adresse");
        TextField tel=new TextField("", "Telephone");
        TextField email=new TextField("", "Email");
        f.add(nom);
        f.add(adresse);
        f.add(tel);
        f.add(email);
        btnv=new Button("Valider");
        btnr=new Button("Reset");
         f.add(btnv);
        f.add(btnr);
        btnr.addActionListener((e) -> {
            nom.clear();
            adresse.clear();
            tel.clear();
            email.clear();
        });

        Validator v = new Validator();
       v.addConstraint(nom, new RegexConstraint("[a-zA-Z]$", "pas de numero"), new  LengthConstraint(3));
       v.addConstraint(adresse, new LengthConstraint(10));
      v.addConstraint(email, RegexConstraint.validEmail());
    v.addConstraint(tel, new RegexConstraint("[0-9]$", "pas de lettre"), new LengthConstraint(8));   
        v.addSubmitButtons(btnv);
         btnv.addActionListener((e) -> {
             
             ServiceFournisseur serviceFournisseur=new ServiceFournisseur();
             String a=tel.getText();
             int tele=Integer.parseInt(a);
             Fournisseurs four=new Fournisseurs(nom.getText(),adresse.getText(),tele,email.getText());
             System.out.println(four);
             serviceFournisseur.ajoutTask(four);
             FournisseurForm ff=new FournisseurForm();
             ToastBar.showMessage("Ajout effectué avec succée",  FontImage.MATERIAL_INFO);
            for (int i = 0; i < serviceFournisseur.getList2().size(); i++) {
                       Fournisseurs fou=new Fournisseurs();
           fou=serviceFournisseur.getList2().get(i);
            ff.addItem(fou);
            }        
              ff.getF().show();
              
        });
    }
    public Form getF() {
        return f;
    }
    
}
