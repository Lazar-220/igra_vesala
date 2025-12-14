/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroler;

import forme.KlijentskaForma;
import java.util.ArrayList;
import java.util.List;
import model.Slovo;

/**
 *
 * @author milos
 */
public class Kontroler {
    
    private static Kontroler instance;
    

    private KlijentskaForma kf;

    public KlijentskaForma getKf() {
        return kf;
    }

    public void setKf(KlijentskaForma kf) {
        this.kf = kf;
    }
    
    

    
    public static Kontroler getInstance() {
        if(instance==null){
            instance=new Kontroler();
        }
        return instance;
    }

    private Kontroler() {
        
        
    }

    public void obavestiIgracaOPocetku() {
        kf.obavestiIgracaOPocetku();
    }

    public void pogadjanje(Slovo s) {
        kf.pogadjanje(s);
    }

    public void postaviRb(int rb) {
        kf.setRb(rb);
    }

    public void krajIgre(String poruka) {
        kf.krajIgre(poruka);
    }

    
    
}
