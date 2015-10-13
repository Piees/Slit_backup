/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.GUI;

import java.util.ArrayList;
import java.util.List;
import org.jdesktop.swingx.JXLabel;

/**
 *
 * @author Arild-Bærbar
 */
public class Modul {
    String name;
    JXLabel modulInnhold;
    int status;
    
    public Modul(String name, JXLabel modulInnhold) {
        this.name = name;
        this.modulInnhold = modulInnhold;
        status = 0;
    }
    
    public String getName() {
        return name;
    }
    
    public JXLabel getInnhold() {
        return modulInnhold;
    }
    
    public String getStatus()   {
        if(status == 0) {
            return "Ikke levert";
        } else if(status == 1)  {
            return "Ikke vurdert";
        } else if(status == 2)  {
            return "Under vurdering";
        } else if(status == 3)  {
            return "Godkjent";
        } else {
            return "Ikke godkjent";
        }
        
    }
    public static List makeModules()   {
        ArrayList<Modul> modules = new ArrayList<Modul>();
        int i = 1;
        while (i < 6) {
            JXLabel innhold = new JXLabel("Her er innholdet i modul " + i + "   ");
            Modul modul = new Modul("Modul " + i + "   ", innhold);
            modules.add(modul);
            i++;
        }
        return modules;
    }
}
