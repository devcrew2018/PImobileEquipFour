/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.MultiButton;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.list.GenericListCellRenderer;
import com.mycompany.Entite.Equipements;
import com.mycompany.Entite.Fournisseurs;
import com.mycompany.Service.ServiceEquipement;
import com.mycompany.Service.ServiceFournisseur;
import java.util.HashMap;
import java.util.Map;
//import jdk.internal.org.objectweb.asm.Opcodes;

/**
 *
 * @author pc-dell
 */
public class HomeForm {
    Form fhome;
    Button btnajout;    
    Toolbar tb;
    public HomeForm(){
        fhome=new Form("Accueil");
       fhome.setUIID("InputContainerBackground");
       tb = fhome.getToolbar();
     //  ServiceEquipement ser=new ServiceEquipement();
        tb.addMaterialCommandToLeftSideMenu("Equipements", FontImage.MATERIAL_WEB_ASSET, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                 EquipementForm ff=new EquipementForm();
                ServiceEquipement ser=new ServiceEquipement();
        for (int i = 0; i < ser.getList2().size(); i++) {
            Equipements four=new Equipements();
            four=ser.getList2().get(i);
            ff.addItem(four);
        }
               ff.getF().show();
            }
        });
         tb.addMaterialCommandToLeftSideMenu("Fournisseurs", FontImage.MATERIAL_WEB_ASSET, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               FournisseurForm ff=new FournisseurForm();
        ServiceFournisseur serviceFournisseur=new ServiceFournisseur();
        for (int i = 0; i < serviceFournisseur.getList2().size(); i++) {
            Fournisseurs four=new Fournisseurs();
            four=serviceFournisseur.getList2().get(i);
            ff.addItem(four);
        }
               ff.getF().show();
            }
        });
    }
    public Form getF() {
        return fhome;
    }
   
}
