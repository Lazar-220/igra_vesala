/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import forme.ServerskaForma;
import java.util.ArrayList;
import java.util.List;
import model.Slovo;
import server.ObradaKlijentskihZahteva;
import transfer.ServerskiOdgovor;

/**
 *
 * @author milos
 */
public class Controller {
    private ServerskaForma sf;
    private int brojPokusajaIgrac1=0;
    private int brojPokusajaIgrac2=0;
    private int brojPogodjenihIgrac1=0;
    private int brojPogodjenihIgrac2=0;
    private List<Integer>rbIgraca=new ArrayList<>();
    private String izabranaRec;
    private List<ObradaKlijentskihZahteva>igraci=new ArrayList<>();
    private List<String>listaReci=new ArrayList<>();
    private static Controller instance;

    public ServerskaForma getSf() {
        return sf;
    }

    public void setSf(ServerskaForma sf) {
        this.sf = sf;
    }

    
    public List<Integer> getRbIgraca() {
        return rbIgraca;
    }

    public void setRbIgraca(List<Integer> rbIgraca) {
        this.rbIgraca = rbIgraca;
    }

    
    
    public String getIzabranaRec() {
        return izabranaRec;
    }

    public void setIzabranaRec(String izabranaRec) {
        this.izabranaRec = izabranaRec;
    }

    
    public List<ObradaKlijentskihZahteva> getIgraci() {
        return igraci;
    }

    public void setIgraci(List<ObradaKlijentskihZahteva> igraci) {
        this.igraci = igraci;
    }

    
    public List<String> getListaReci() {
        return listaReci;
    }

    public void setListaReci(List<String> listaReci) {
        this.listaReci = listaReci;
    }

    
    public static Controller getInstance() {
        if(instance==null){
            instance=new Controller();
        }
        return instance;
    }

    private Controller() {
        listaReci.add("PETAO");
        listaReci.add("MACAK");
        listaReci.add("LAZAR");
        listaReci.add("VETAR");
        listaReci.add("METAR");
    }

    public Slovo proveriSlovo(Slovo slovo) {
        List<Integer>pozicije=new ArrayList<>();
        char[]niz=izabranaRec.toCharArray();
        for(int i=0;i<izabranaRec.length();i++){
            if(niz[i]==slovo.getKarakter()){
                pozicije.add(i+1);
            }
        }
        slovo.setPozicije(pozicije);
        return slovo;
    }

    public int dodajRb() {
        rbIgraca.add(rbIgraca.size()+1);
        return rbIgraca.size();
    }

    public void proveraStatusaIgre(Slovo slovo) {
        if(slovo.getRbIgraca()==1){
            brojPokusajaIgrac1++;
            for(int i=0;i<slovo.getPozicije().size();i++){
                brojPogodjenihIgrac1++;
            }
            sf.popuniTextBox(brojPogodjenihIgrac1,brojPokusajaIgrac1,slovo.getRbIgraca());
        }else if(slovo.getRbIgraca()==2){
            brojPokusajaIgrac2++;
            for(int i=0;i<slovo.getPozicije().size();i++){
                brojPogodjenihIgrac2++;
            }
            sf.popuniTextBox(brojPogodjenihIgrac2,brojPokusajaIgrac2,slovo.getRbIgraca());
        }
        if((brojPogodjenihIgrac1==5&&brojPogodjenihIgrac2==5)&&brojPokusajaIgrac1>brojPokusajaIgrac2){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je 2. igrac!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }else if((brojPogodjenihIgrac1==5&&brojPogodjenihIgrac2==5)&&brojPokusajaIgrac1<brojPokusajaIgrac2){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je 1. igrac!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }else if((brojPokusajaIgrac1==10&&brojPokusajaIgrac2==10)&&brojPogodjenihIgrac2==5&&brojPogodjenihIgrac1!=5){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je 2. igrac!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }else if((brojPokusajaIgrac1==10&&brojPokusajaIgrac2==10)&&brojPogodjenihIgrac1==5&&brojPogodjenihIgrac2!=5){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je 1. igrac!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }else if((brojPokusajaIgrac1==10&&brojPokusajaIgrac2==10)&&brojPogodjenihIgrac2!=5&&brojPogodjenihIgrac1!=5){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je kompjuter!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }else if(brojPogodjenihIgrac1==5&&(brojPogodjenihIgrac2!=5&&brojPokusajaIgrac2==10)){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je 1. igrac!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }else if(brojPogodjenihIgrac2==5&&(brojPogodjenihIgrac1!=5&&brojPokusajaIgrac1==10)){
            ServerskiOdgovor so=new ServerskiOdgovor("Pobedio je 2. igrac!", operacije.Operacije.krajIgre);
            for(ObradaKlijentskihZahteva igrac:controller.Controller.getInstance().getIgraci()){
                igrac.posaljiOdgovor(so);
            }
        }
        
    }
    
}
