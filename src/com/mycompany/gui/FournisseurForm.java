/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Fournisseurs;
import com.mycompany.Service.ServiceFournisseur;

/**
 *
 * @author pc-dell
 */
public class FournisseurForm {
    Form ff;
    TextField t;
     Button c;
      Button v;
      TableLayout tl; Resources theme;
    
    public FournisseurForm() {
        tl=new TableLayout(1,2);
        ff=new Form(" Fournisseurs");
        ff.setUIID("ProfileContainer");
        t=new TextField();
       v=new Button("liste des fournisseurs");
       v.setVisible(false);
         t=new TextField("","Chercher");
         Container c5 = new Container(tl);
         theme = UIManager.initFirstTheme("/theme_1");
         Image img=theme.getImage("loupe2.png");
       c=new Button("",img); 
       c5.add(tl.createConstraint().widthPercentage(90), t);
c5.add(tl.createConstraint().widthPercentage(10),c);
        ff.add(c5);
        ff.add(v);
                ServiceFournisseur serviceFournisseur=new ServiceFournisseur();
       c.addActionListener((evt) -> {
            FournisseurForm a=new FournisseurForm();
            a.v.setVisible(true);
            for (int i = 0; i < serviceFournisseur.chercher(t.getText()).size(); i++) {
            Fournisseurs four=new Fournisseurs();
            four=serviceFournisseur.chercher(t.getText()).get(i);
                        a.addItem(four);
        }
            a.getF().show();
       });
           v.addActionListener((evt) -> {
                FournisseurForm a=new FournisseurForm();
        for (int i = 0; i < serviceFournisseur.getList2().size(); i++) {
            Fournisseurs four=new Fournisseurs();
            four=serviceFournisseur.getList2().get(i);
            a.addItem(four);
        }
                a.getF().show();
           });
        
         ff.getToolbar().addCommandToLeftBar("<",null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        HomeForm fhome=new HomeForm();
                       fhome.getF().show();
                    }
                });
         ff.getToolbar().addCommandToRightBar("+", null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        AjoutFoForm af=new AjoutFoForm();
                        af.getF().getToolbar().addCommandToLeftBar("<", null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       ff.show();
                    }
                });
                       af.getF().show();
                    }
                });
    }

    public Form getF() {
        return ff;
    }

    public void addItem(Fournisseurs c){
        
        Container c1=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label l1=new Label(c.getNom());
        String tel = String.valueOf(c.getTel());
        Label l3=new Label(tel);
        l1.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog d=new Dialog();
               if( d.show("Fournisseur", "Nom: "+c.getNom()+"\nTel: "+tel+"\nAdresse: "+c.getAdresse()+"\nE-mail: "+c.getEmail()
                        , "Modif/Supp", "Annuler"))
               {
       modifFourForm fff=new modifFourForm();
        fff.getF().getToolbar().addCommandToLeftBar("<",null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       ff.show();
                    }
                });
        Fournisseurs test=new Fournisseurs(c.getId(),c.getNom(), c.getAdresse(), c.getTel(), c.getEmail());
       
          fff.addItem2(test);

            fff.getF().show();
               }
            }
        });
        c2.add(l1); 
        c2.add(l3); 
        c1.add(c2);
        c1.setLeadComponent(l1);
        ff.add(c1);
    } 

    
    
}
