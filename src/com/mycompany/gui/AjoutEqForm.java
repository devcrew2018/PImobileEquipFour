/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.GenericListCellRenderer;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.Equipements;
import com.mycompany.Entite.Fournisseurs;
import com.mycompany.Service.ServiceEquipement;
import com.mycompany.Service.ServiceFournisseur;
import java.util.HashMap;
import java.util.Map;




/**
 *
 * @author pc-dell
 */
public class AjoutEqForm {
    Form f;
    
    TextField libelle;
    TextField reference;
    TextField date;
    Button btnv;
    Button btnr;
    Container c,c1,c4,c5;
    Label etat,four,a,b;
     
    public AjoutEqForm(){
        f=new Form("Equipement",new BoxLayout(BoxLayout.Y_AXIS));
      f.setUIID("ProfileContainer");
        c=new Container(new BoxLayout(BoxLayout.X_AXIS));
        c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        c4=new Container(new BoxLayout(BoxLayout.X_AXIS));
        c5=new Container(new BoxLayout(BoxLayout.X_AXIS));
        etat=new Label("Etat :");
         four=new Label("Fournisseur :");
         a=new Label("Domaine :");
         b=new Label("Salle :");
          etat.getUnselectedStyle().setFgColor(-00000000);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        etat.getUnselectedStyle().setFont(l1_font);
        four.getUnselectedStyle().setFont(l1_font);
        four.getUnselectedStyle().setFgColor(-00000000);
        a.getUnselectedStyle().setFont(l1_font);
        a.getUnselectedStyle().setFgColor(-00000000);
        b.getUnselectedStyle().setFont(l1_font);
        b.getUnselectedStyle().setFgColor(-00000000);
        
String[] characters = { "Fonctionnel", "Non Fonctionnel", "En maintenance"};
Picker p = new Picker();
p.setStrings(characters);
p.setSelectedString(characters[0]);
Picker p1 = new Picker();
p1.setStrings(allf());
p1.setSelectedString("Fournisseur");
        TextField libelle=new TextField("", "Libelle");
        TextField reference=new TextField("", "Reference");
         f.add(libelle);
         f.add(reference);
        String[] dom = { "Musculation", "Fitness", "Boxe", "Lutte Libre", "Natation", "Aerobic sportive"};         
Picker domaine = new Picker();
domaine.setStrings(dom);
         domaine.setText("Domaine");
         c5.add(a);c5.add(domaine);
         f.add(c5);
          String[] character = { "A1", "A2", "G1", "B1", "B2", "N", "Atelier"};
         
Picker salle = new Picker();
salle.setStrings(character);
         salle.setText("Salle");
         c4.add(b);c4.add(salle);
         f.add(c4);
       c.add(etat);c.add(p);
       c1.add(four);c1.add(p1);
        f.add(c);
        f.add(c1);
       
        btnv=new Button("Valider");
        btnr=new Button("Reset");
         f.add(btnv);
        f.add(btnr);
        btnr.addActionListener((e) -> {
            libelle.clear();
            reference.clear();
            domaine.setText("Domaine");
            salle.setText("Salle");
            p1.setText("Fournisseur");
            p.setText("Fonctionnel");
        });
        Validator v = new Validator();
       v.addConstraint(libelle, new RegexConstraint("[a-zA-Z]$", "pas de numero"), new  LengthConstraint(3));
       v.addConstraint(reference, new LengthConstraint(3));
        v.addSubmitButtons(btnv);
            btnv.addActionListener((e) -> {
                 if((domaine.getText().equalsIgnoreCase("Domaine"))||(salle.getText().equalsIgnoreCase("Salle"))||(p1.getText().equalsIgnoreCase("Fournisseur"))||(p.getText().equalsIgnoreCase("Etat")))
        {
            Dialog.show("Zvertissement", "veuillez remplir tous les champs", "ok", null);
            
        }else{
                ServiceEquipement ser=new ServiceEquipement();
             Equipements four=new Equipements(libelle.getText(),reference.getText(),domaine.getText(),salle.getText(),p.getText(),p1.getText());
             System.out.println(four);
             ser.ajoutTask(four);
             EquipementForm ff=new EquipementForm();
             ToastBar.showMessage("Ajout effectué avec succée",  FontImage.MATERIAL_INFO);
            for (int i = 0; i < ser.getList2().size(); i++) {
                       Equipements fou=new Equipements();
           fou=ser.getList2().get(i);
            ff.addItem(fou);
            }
               ff.getF().show();}
        });
}
    public Form getF() {
        return f;
    }
     public String[] allf()
     {
         ServiceFournisseur ser =new ServiceFournisseur();
         int tailleDeLaBoucle =ser.getList2().size() ;
         String[] s = new String[tailleDeLaBoucle];
          for (int i = 0; i < tailleDeLaBoucle; i++) {
              s[i] = ser.getList2().get(i).getNom();
     }
         return s;
     }
}
