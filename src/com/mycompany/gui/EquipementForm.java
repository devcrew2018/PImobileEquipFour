/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.FloatingHint;
import static com.codename1.io.Log.e;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.TextModeLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.Equipements;
import com.mycompany.Entite.Fournisseurs;
import com.mycompany.Service.ServiceEquipement;


/**
 *
 * @author pc-dell
 */
public class EquipementForm {
    Form ff;
    Form stat;
    TableLayout t;
    TextField tf;
     Button c;
     Button v;
     Button btnstat;
     Label l1,l2;
       Resources theme;

    public EquipementForm()  {
         t=new TableLayout(1,2);
        ff= new Form("Equipements");
       ff.setUIID("ProfileContainer");
        Container c1=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c3=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Container c4=new Container(new BoxLayout(BoxLayout.X_AXIS));
         Container main=new Container(new BoxLayout(BoxLayout.Y_AXIS));
         t=new TableLayout(1,2);
         tf=new TextField("","Chercher");
         Container c5 = new Container(t);           
         theme = UIManager.initFirstTheme("/theme_1");
         Image img=theme.getImage("loupe2.png");
         Image img1=theme.getImage("stat.png");
       c=new Button("",img); 
        btnstat=new Button("",img1);
       c5.add(t.createConstraint().widthPercentage(90), tf);
c5.add(t.createConstraint().widthPercentage(10),c);

       v=new Button("liste des Equipements");
      
       l1=new Label("Tri par Salle: ");
       l2=new Label("Tri par Domaine: ");
       c3.add(btnstat);
       c3.add(v);
       String[] character = { "A1", "A2", "G1", "B1", "B2", "N", "Atelier"};
Picker salle = new Picker();
salle.setStrings(character);
         salle.setText("Salle");
         c1.add(l1);c1.add(salle);
         String[] dom = { "Musculation", "Fitness", "Boxe", "Lutte Libre", "Natation", "Aerobic sportive"};         
Picker domaine = new Picker();
domaine.setStrings(dom);
         domaine.setText("Domaine");
       v.setVisible(false);
       c2.add(l2);c2.add(domaine);
       main.add(c5);
               main.add(c3);main.add(c1);main.add(c2);
        ff.add(main);     
        btnstat.addActionListener((evt) -> {
            stat=this.createPieChartForm();
            stat.setUIID("ProfileContainer");
            stat.getToolbar().addCommandToLeftBar("<",null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {        
                       ff.showBack();
                    }
                });
            stat.show();          
        });
        salle.addActionListener((evt) -> {
               EquipementForm a=new EquipementForm();
            ServiceEquipement ser=new ServiceEquipement();
            a.v.setVisible(true);
            for (int i = 0; i < ser.trisalle(salle.getText()).size(); i++) {
            Equipements four=new Equipements();
            four=ser.trisalle(salle.getText()).get(i);   
            a.addItem(four);
        }
            a.getF().show();
        });
         domaine.addActionListener((evt) -> {
               EquipementForm a=new EquipementForm();
            ServiceEquipement ser=new ServiceEquipement();
            a.v.setVisible(true);
            for (int i = 0; i < ser.tridomaine(domaine.getText()).size(); i++) {
            Equipements four=new Equipements();
            four=ser.tridomaine(domaine.getText()).get(i);
            a.addItem(four);
        }
            a.getF().show();
        });
        c.addActionListener((evt) -> {
            EquipementForm a=new EquipementForm();
            ServiceEquipement ser=new ServiceEquipement();
            a.v.setVisible(true);
            for (int i = 0; i < ser.chercher(tf.getText()).size(); i++) {
            Equipements four=new Equipements();
            four=ser.chercher(tf.getText()).get(i);
            a.addItem(four);
        }
            a.getF().show();
       });
         v.addActionListener((evt) -> {
               EquipementForm a=new EquipementForm();
            ServiceEquipement ser=new ServiceEquipement();
        for (int i = 0; i < ser.getList2().size(); i++) {
            Equipements four=new Equipements();
            four=ser.getList2().get(i);
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
                        AjoutEqForm af=new AjoutEqForm();
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
     public void addItem(Equipements c){
        
        Container c1=new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c2=new Container(new BoxLayout(BoxLayout.X_AXIS));
        Label l1=new Label(c.getLibelle());
         Label l2=new Label(c.getEtat());       
        l1.addPointerReleasedListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Dialog d=new Dialog();
               if( d.show("Fournisseur", "Libelle: "+c.getLibelle()+"\nReference: "+c.getReference()+"\nDomaine: "+c.getDomaine()+"\nSalle: "+c.getSalle()+"\nDate d'ajout: "+c.getDate()+"\nEtat: "+c.getEtat()+"\nFournisseur: "+c.getFournisseur()
                        , "Modif/Supp", "Annuler"))
               {                
       modifequiForm fff=new modifequiForm();
        fff.getF().getToolbar().addCommandToLeftBar("<",null, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                       ff.show();
                    }
                });
        Equipements test=new Equipements(c.getId(),c.getLibelle(), c.getReference(), c.getDomaine(), c.getSalle(), c.getDate(), c.getEtat(), c.getFournisseur());
          fff.addItem2(test);
            fff.getF().show();
               }
            }
        });
        if(c.getEtat().equalsIgnoreCase("Non Fonctionnel"))
        {
        l1.getAllStyles().setFgColor(0xFF0000);
        l2.getAllStyles().setFgColor(0xFF0000);
        }
        else if (c.getEtat().equalsIgnoreCase("Fonctionnel"))
        {
             l1.getAllStyles().setFgColor(0x00FF00);
              l2.getAllStyles().setFgColor(0x00FF00);
        }
        else
        {
             l1.getAllStyles().setFgColor(0x0080FF);
              l2.getAllStyles().setFgColor(0x0080FF);
        }
        c2.add(l1); 
        c2.add(l2); 
         c1.add(c2);
        c1.setLeadComponent(l1);
        ff.add(c1);
    } 
      private DefaultRenderer buildCategoryRenderer(int[] colors) {
    DefaultRenderer renderer = new DefaultRenderer();
    renderer.setLabelsTextSize(15);
    renderer.setLegendTextSize(15);
    renderer.setMargins(new int[]{20, 30, 15, 0});
    for (int color : colors) {
        SimpleSeriesRenderer r = new SimpleSeriesRenderer();
        r.setColor(color);
        renderer.addSeriesRenderer(r);
    }
    return renderer;
}

/**
 * Builds a category series using the provided values.
 *
 * @param titles the series titles
 * @param values the values
 * @return the category series
 */
protected CategorySeries buildCategoryDataset(String title, double[] values) {
    CategorySeries series = new CategorySeries(title);
    series.add("Fonctionnel",values[0]);
        series.add("Non Fonctionnel",values[1]);
            series.add("En maintenance",values[2]);          
    return series;
}

public Form createPieChartForm() {
    // Generate the values
    ServiceEquipement ser=new ServiceEquipement();
     double d1 = Float.parseFloat(ser.etat("Fonctionnel"));
     double d2= Float.parseFloat(ser.etat("Non Fonctionnel"));
     double d3 = Float.parseFloat(ser.etat("En maintenance"));
    double[] values = new double[]{d1, d2,d3};

    // Set up the renderer
    int[] colors = new int[]{0x4C9900, 0xFF0000, ColorUtil.BLUE};
    Font l1_font = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL);
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    renderer.setZoomButtonsVisible(true);
    renderer.setZoomEnabled(true);
    renderer.setChartTitleTextSize(20);
    renderer.setDisplayValues(true);
    renderer.setShowLabels(true);
    renderer.setLabelsTextFont(l1_font);
    renderer.setLabelsTextSize(20);
    renderer.setLabelsColor(0x000033);
   // SimpleSeriesRenderer r = renderer.getSeriesRendererAt(0);
//    r.setGradientEnabled(true);
////    r.setGradientStart(0, ColorUtil.green(0x4C9900));
////    r.setGradientStop(0, ColorUtil.green(0x4C9900));
// r.setGradientStart(0, 0x4C9900);
//    r.setGradientStop(0, 0x4C9900);
//    r.setHighlighted(true);

    // Create the chart ... pass the values and renderer to the chart object.
    PieChart chart = new PieChart(buildCategoryDataset("Etat des equipements", values), renderer);

    // Wrap the chart in a Component so we can add it to a form
    ChartComponent c = new ChartComponent(chart);

    // Create a form and show it.
    Form f = new Form("Etat des equipements", new BorderLayout());
    f.add(BorderLayout.CENTER, c);
    return f;

}
    
}

