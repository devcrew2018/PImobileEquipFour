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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
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
public class modifFourForm {
    Form f;
     public modifFourForm(){
        f=new Form("fournisseur",new BoxLayout(BoxLayout.Y_AXIS));
        f.setUIID("ProfileContainer");
     }
     public void addItem2(Fournisseurs c){
        String tel=Integer.toString(c.getTel());
        String t=Integer.toString(c.getId());
        Container c2=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         Button modif=new Button("modifier");
         Button supp=new Button("supprimer");
         Button aff=new Button("Equipements");
         
         TextField tf1=new TextField("","Nom");
         tf1.setText(c.getNom());
         f.add(tf1);
         TextField tf2=new TextField("","Adresse");
         tf2.setText(c.getAdresse());
         TextField tf3=new TextField("","Telephone");
          tf3.setText(tel);
       TextField tf4=new TextField("","Email");
       tf4.setText(c.getEmail());
       f.add(tf2);
       f.add(tf3);
       f.add(tf4);
       c2.add(modif);
       c2.add(supp);
              c2.add(aff);
       f.add(c2);
       supp.addActionListener((evt) -> {  
           if( Dialog.show("Confirmation de SUPPRESSION", "Nom: "+c.getNom()+"\nTel: "+tel+"\nAdresse: "+c.getAdresse()+"\nE-mail: "+c.getEmail()
                        , "OUI", "NON")){
           ServiceFournisseur ser=new ServiceFournisseur();
           ser.delFour(c.getId());
            FournisseurForm ff=new FournisseurForm();
            ToastBar.showMessage("Suppression effectué avec succée",  FontImage.MATERIAL_INFO);
            for (int i = 0; i < ser.getList2().size(); i++) {
                       Fournisseurs fou=new Fournisseurs();
           fou=ser.getList2().get(i);
            ff.addItem(fou);
            }
               ff.getF().show();
           }
       });
       aff.addActionListener((evt) -> {
           EquipementForm ff=new EquipementForm();
              ServiceEquipement ser=new ServiceEquipement();
        for (int i = 0; i < ser.equifour(c.getNom()).size(); i++) {
            Equipements four=new Equipements();
            four=ser.equifour(c.getNom()).get(i);
            ff.addItem(four);
        }
               ff.getF().show();
       });
        Validator v = new Validator();
       v.addConstraint(tf1, new RegexConstraint("[A-Z]", "pas de numero"),new RegexConstraint("[a-z]", "pas de numero"), new  LengthConstraint(3));
       v.addConstraint(tf2, new LengthConstraint(10));
      v.addConstraint(tf4, RegexConstraint.validEmail());
    v.addConstraint(tf3, new RegexConstraint("[0-9]$", "pas de lettre"), new LengthConstraint(8));   
       // v.addConstraint(tel, new LengthConstraint(8));
       // v.addConstraint(nom, new LengthConstraint(3));
        v.addSubmitButtons(modif);
       modif.addActionListener((evt) -> {  
           if( Dialog.show("Confirmation de MODIFICATION", "Nom: "+c.getNom()+"\nTel: "+tel+"\nAdresse: "+c.getAdresse()+"\nE-mail: "+c.getEmail()
                        , "OUI", "NON")){
           ServiceFournisseur serviceFournisseur=new ServiceFournisseur();
             String a=tf3.getText();
             int tele=Integer.parseInt(a);
             Fournisseurs four=new Fournisseurs(c.getId(),tf1.getText(),tf2.getText(),tele,tf4.getText());
             System.out.println(four);
             serviceFournisseur.edit(four);
             FournisseurForm ff=new FournisseurForm();
            ToastBar.showMessage("Modification effectué avec succée",  FontImage.MATERIAL_INFO);
            for (int i = 0; i < serviceFournisseur.getList2().size(); i++) {
                       Fournisseurs fou=new Fournisseurs();
           fou=serviceFournisseur.getList2().get(i);
            ff.addItem(fou);
            }
            //Dialog.show("Succée", "Modification effectué avec succée", "ok", null);
               ff.getF().show();
           }
       });
    } 
      public Form getF() {
        return f;
    }
}
