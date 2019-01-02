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
import com.codename1.l10n.ParseException;
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
public class ServiceFournisseur {
    public void ajoutTask(Fournisseurs fou) {
        ConnectionRequest con = new ConnectionRequest();
    
       // String tel=String.valueOf(fou.getTel());
        
        String Url = "http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/fournisseur_add" ;
        con.addArgument("nom", fou.getNom());
        con.addArgument("adresse", fou.getAdresse());
        con.addArgument("telephone", Integer.toString(fou.getTel()));
        con.addArgument("email", fou.getEmail());
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void edit(Fournisseurs fou) {
        ConnectionRequest con = new ConnectionRequest();
    
       // String tel=String.valueOf(fou.getTel());
        String b=Integer.toString(fou.getId());
        String Url = "http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/fournisseurmodif/"+b ;
        con.addArgument("nom", fou.getNom());
        con.addArgument("adresse", fou.getAdresse());
        con.addArgument("telephone", Integer.toString(fou.getTel()));
        con.addArgument("email", fou.getEmail());
        con.setUrl(Url);
        con.setPost(true);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public ArrayList<Fournisseurs> getListTask(String json) {

        ArrayList<Fournisseurs> listFournisseurses = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();
            Map<String, Object> fournisseur = j.parseJSON(new CharArrayReader(json.toCharArray()));     
            List<Map<String, Object>> list = (List<Map<String, Object>>) fournisseur.get("root");
            for (Map<String, Object> obj : list) {
                Fournisseurs f=new Fournisseurs();
                float id = Float.parseFloat(obj.get("id").toString());
                float tel = Float.parseFloat(obj.get("telephone").toString());
                //System.out.println(id);
               // System.out.println(tel);
                f.setId((int) id);
                f.setNom(obj.get("nom").toString());
                f.setAdresse(obj.get("adresse").toString());
                f.setTel((int)tel );
                f.setEmail(obj.get("email").toString());
               // System.out.println(f);
                listFournisseurses.add(f);
            }

        } catch (IOException ex) {
        }
        return listFournisseurses;

    }
    ArrayList<Fournisseurs> listFournisseurses = new ArrayList<>();
    
    public ArrayList<Fournisseurs> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/allfournisseurs");  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFournisseur ser = new ServiceFournisseur();
                listFournisseurses = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listFournisseurses;
    }
    public void delFour(int id){       
        //int a=60;
       String b=Integer.toString(id);
        System.out.println(b);
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/fournisseurdel/"+b;
       // con.addArgument("id", Integer.toString(id));
        con.setUrl(Url);  
        con.setPost(true);
       con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
           // System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public ArrayList<Fournisseurs> chercher(String b){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/fournisseurdyna/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFournisseur ser = new ServiceFournisseur();
                listFournisseurses = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listFournisseurses;
    }
                         public ArrayList<Fournisseurs> fourequi(String b){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symfony/PIVersionuneMobile/web/app_dev.php/fourequi/"+b);  
        con.setPost(false);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceFournisseur ser = new ServiceFournisseur();
                listFournisseurses = ser.getListTask(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
       // System.out.println(listFournisseurses.size());
        return listFournisseurses;
    }
//    public ArrayList<Comment> getList2(String b) {
//        ConnectionRequest con = new ConnectionRequest();
//        con.setUrl("http://localhost/CharityLandV2/web/app_dev.php/api/AfficheCommentMobile/" + b);
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                CommentServices cer = new CommentServices();
//                try {
//                    listComments = cer.getListComment(new String(con.getResponseData()));
//                } catch (ParseException ex) {
//                    System.out.println(ex.getMessage());
//                }
//            }
//        });
//
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return listComments;
//    }
    
}
