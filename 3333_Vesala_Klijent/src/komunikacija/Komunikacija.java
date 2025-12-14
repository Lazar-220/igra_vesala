/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Slovo;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;

/**
 *
 * @author milos
 */
public class Komunikacija extends Thread{
    private static Komunikacija instance;
    private Socket s;

    public static Komunikacija getInstance() {
        if(instance==null){
            instance=new Komunikacija();
        }
        return instance;
    }

    private Komunikacija() {
        try {
            s=new Socket("localhost", 9000);
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public void run() {
        while(true){
            ServerskiOdgovor so=primiOdgovor();
            switch (so.getOperacija()) {
                case operacije.Operacije.pocetakIgre:
                    kontroler.Kontroler.getInstance().obavestiIgracaOPocetku();
                    break;
                case operacije.Operacije.pogadjanje:
                    kontroler.Kontroler.getInstance().pogadjanje((Slovo)so.getOdgovor());
                    break;
                case operacije.Operacije.login:
                    kontroler.Kontroler.getInstance().postaviRb((int)so.getOdgovor());
                    break;
                case operacije.Operacije.krajIgre:
                    kontroler.Kontroler.getInstance().krajIgre((String)so.getOdgovor());
                    break;
                default:
                    throw new AssertionError();
            }
        }
    }
    
    
    
    
    
    
    
    public ServerskiOdgovor primiOdgovor() {
        try {
            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            return (ServerskiOdgovor) ois.readObject();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
        return null;
    }

    public void posaljiZahtev(KlijentskiZahtev kz) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
            oos.writeObject(kz);
            oos.flush();
        } catch (IOException ex) {
            Logger.getLogger(Komunikacija.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
