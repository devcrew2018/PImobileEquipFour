/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.Entite.Equipements;
import com.mycompany.Entite.Fournisseurs;
import com.mycompany.Service.ServiceEquipement;
import com.mycompany.Service.ServiceFournisseur;

/**
 *
 * @author pc-dell
 */
public class modifequiForm {
     Form f;

    public modifequiForm() {
        f=new Form("Equipement",new BoxLayout(BoxLayout.Y_AXIS));
        f.setUIID("ProfileContainer");
    }
    public void addItem2(Equipements c){
        Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container c3=new Container(new BoxLayout(BoxLayout.X_AXIS));
          Container c4=new Container(new BoxLayout(BoxLayout.X_AXIS));
          Container c5=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Button modif=new Button("modifier");
         Button supp=new Button("supprimer");
         Button aff=new Button("Fournisseur");
         Label etat=new Label("Etat :");
        Label  fo=new Label("Fournisseur :");
        Label  s=new Label("Salle :");
        Label  d=new Label("Domaine :");
        etat.getUnselectedStyle().setFgColor(-00000000);
        Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        etat.getUnselectedStyle().setFont(l1_font);
        fo.getUnselectedStyle().setFont(l1_font);
        fo.getUnselectedStyle().setFgColor(-00000000);
        s.getUnselectedStyle().setFont(l1_font);
        s.getUnselectedStyle().setFgColor(-00000000);
        d.getUnselectedStyle().setFont(l1_font);
        d.getUnselectedStyle().setFgColor(-00000000);
         TextField libelle=new TextField("","Libelle");
         libelle.setText(c.getLibelle());
         f.add(libelle);
        TextField ref=new TextField("","Reference");
         ref.setText(c.getReference());
         f.add(ref);
 String[] dom = { "Musculation", "Fitness", "Boxe", "Lutte Libre", "Natation", "Aerobic sportive"};         
Picker domaine = new Picker();
domaine.setStrings(dom);
         domaine.setText(c.getDomaine());
         c5.add(d);c5.add(domaine);
         f.add(c5);
          String[] character = { "A1", "A2", "G1", "B1", "B2", "N", "Atelier"};
         
Picker salle = new Picker();
salle.setStrings(character);
         salle.setText(c.getSalle());
         c4.add(s);c4.add(salle);
         f.add(c4);
         String[] characters = { "Fonctionnel", "Non Fonctionnel", "En maintenance"};
Picker p = new Picker();
p.setStrings(characters);
Picker p1 = new Picker();
p1.setStrings(allf());       
         p.setText(c.getEtat());
         c1.add(etat);c1.add(p);
         f.add(c1);
         p1.setText(c.getFournisseur());
          c3.add(fo);c3.add(p1);
         f.add(c3);
         TextField date=new TextField();
         date.setText(c.getDate());
         date.setEditable(false);
         f.add(date);
       c2.add(modif);
       c2.add(supp);
       c2.add(aff);
       f.add(c2);
       supp.addActionListener((evt) -> {  
           if( Dialog.show("Fournisseur", "Libelle: "+c.getLibelle()+"\nReference: "+c.getReference()+"\nDomaine: "+c.getDomaine()+"\nSalle: "+c.getSalle()+"\nDate d'ajout: "+c.getDate()+"\nEtat: "+c.getEtat()+"\nFournisseur: "+c.getFournisseur()
                        , "OUI", "NON")){
               ServiceEquipement ser=new ServiceEquipement();
           ser.delEq(c.getId());
           EquipementForm ff=new EquipementForm();
           ToastBar.showMessage("Suppression effectué avec succée",  FontImage.MATERIAL_INFO);
            for (int i = 0; i < ser.getList2().size(); i++) {
                       Equipements four=new Equipements();
           four=ser.getList2().get(i);
            ff.addItem(four);
            }
               ff.getF().show();
           }
       });
        aff.addActionListener((evt) -> {
              ServiceFournisseur ser=new ServiceFournisseur();
            Fournisseurs four=new Fournisseurs();
            four=ser.fourequi(c.getFournisseur()).get(0);
        Dialog.show("Confirmation de MODIFICATION", "Nom: "+four.getNom()+"\nTel: "+four.getTel()+"\nAdresse: "+four.getAdresse()+"\nE-mail: "+four.getEmail()
                        , "OK", null);
               
       });
        Validator v = new Validator();
       v.addConstraint(libelle, new RegexConstraint("[a-zA-Z]$", "pas de numero"), new  LengthConstraint(3));
       v.addConstraint(ref, new LengthConstraint(3));
        v.addSubmitButtons(modif);
       modif.addActionListener((evt) -> {  
           if( Dialog.show("Confirmation de MODIFICATION","Libelle: "+c.getLibelle()+"\nReference: "+c.getReference()+"\nDomaine: "+c.getDomaine()+"\nSalle: "+c.getSalle()+"\nDate d'ajout: "+c.getDate()+"\nEtat: "+c.getEtat()+"\nFournisseur: "+c.getFournisseur()
                       , "OUI", "NON")){
           ServiceEquipement ser=new ServiceEquipement();
             Equipements four=new Equipements(c.getId(),libelle.getText(),ref.getText(),domaine.getText(),salle.getText(),date.getText(),p.getText(),p1.getText());
             System.out.println(four);
             ser.edit(four);
             EquipementForm ff=new EquipementForm();
             ToastBar.showMessage("Modification effectué avec succée",  FontImage.MATERIAL_INFO);
            for (int i = 0; i < ser.getList2().size(); i++) {
                       Equipements e=new Equipements();
           e=ser.getList2().get(i);
            ff.addItem(e);
            }
               ff.getF().show();
           }
       });
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
     public Form getF(){
         return f;
     }
    
}
