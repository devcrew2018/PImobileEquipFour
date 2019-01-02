/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Equipements;
import com.mycompany.Entite.Fournisseurs;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author pc-dell
 */
public class ServiceEquipement {
        public void ajoutTask(Equipements fou) {
        ConnectionRequest con = new ConnectionRequest();
    
       // String tel=String.valueOf(fou.getTel());
        
        String Url = "http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/equip_add" ;
        con.addArgument("libelle", fou.getLibelle());
        con.addArgument("reference", fou.getReference());
        con.addArgument("domaine", fou.getDomaine());
        //con.addArgument("telephone", Integer.toString(fou.getTel()));
        con.addArgument("salle", fou.getSalle());
        con.addArgument("date", fou.getDate());
        con.addArgument("etat", fou.getEtat());
        con.addArgument("fournisseur", fou.getFournisseur());
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        public void edit(Equipements fou) {
        ConnectionRequest con = new ConnectionRequest();
    
       // String tel=String.valueOf(fou.getTel());
        String b=Integer.toString(fou.getId());
        String Url = "http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/equimodif/"+b ;
       con.addArgument("libelle", fou.getLibelle());
        con.addArgument("reference", fou.getReference());
        con.addArgument("domaine", fou.getDomaine());
        //con.addArgument("telephone", Integer.toString(fou.getTel()));
        con.addArgument("salle", fou.getSalle());
        
        con.addArgument("date", fou.getDate());
        con.addArgument("etat", fou.getEtat());
        con.addArgument("fournisseur", fou.getFournisseur());
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
            public ArrayList<Equipements> getListTask(String json) {

        ArrayList<Equipements> listEquipements = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> equipement = j.parseJSON(new CharArrayReader(json.toCharArray()));     
            List<Map<String, Object>> list = (List<Map<String, Object>>) equipement.get("root");
            for (Map<String, Object> obj : list) {
                Equipements f=new Equipements();
                float id = Float.parseFloat(obj.get("id").toString());
              
                f.setId((int) id);
                f.setLibelle(obj.get("libelle").toString());
                f.setReference(obj.get("reference").toString());
                f.setDomaine(obj.get("domaine").toString());
                f.setSalle(obj.get("salle").toString());
                f.setDate(obj.get("date").toString());
                f.setEtat(obj.get("etat").toString());
                f.setFournisseur(obj.get("fournisseur").toString());
               // System.out.println(f);
                listEquipements.add(f);
            }

        } catch (IOException ex) {
        }
        return listEquipements;

    }
            ArrayList<Equipements> listEquipements = new ArrayList<>();
         public ArrayList<Equipements> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/allequipements");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEquipement ser = new ServiceEquipement();
                listEquipements = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listEquipements;
    }
         public void delEq(int id){       
        //int a=60;
       String b=Integer.toString(id);
        System.out.println(b);
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/equip_del/"+b;
       // con.addArgument("id", Integer.toString(id));
        con.setUrl(Url);  
        con.setPost(true);
       con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
         public ArrayList<Equipements> chercher(String b){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/equipementdyna/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEquipement ser = new ServiceEquipement();
                listEquipements = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listEquipements;
    }
         String s;
             public String etat(String b){      
               //  int i=0;
               
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/equi_etat/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEquipement ser = new ServiceEquipement();
                 s =new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
         return s;
        
    }
                      public ArrayList<Equipements> equifour(String b){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/equifour/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEquipement ser = new ServiceEquipement();
                listEquipements = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listEquipements;
    }
          public ArrayList<Equipements> trisalle(String b){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/trisalle/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEquipement ser = new ServiceEquipement();
                listEquipements = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listEquipements;
    }
           public ArrayList<Equipements> tridomaine(String b){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/tridomaine/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceEquipement ser = new ServiceEquipement();
                listEquipements = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listEquipements;
    }
             
    
}
