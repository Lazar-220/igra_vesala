/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author milos
 */
public class Slovo implements Serializable {
    private char karakter;
    private List<Integer>pozicije;
    private int rbIgraca;

    public int getRbIgraca() {
        return rbIgraca;
    }

    public void setRbIgraca(int rbIgraca) {
        this.rbIgraca = rbIgraca;
    }

    public Slovo(char karakter, List<Integer> pozicije, int rbIgraca) {
        this.karakter = karakter;
        this.pozicije = pozicije;
        this.rbIgraca = rbIgraca;
    }

    
    public Slovo() {
    }

    public Slovo(char karakter, List<Integer> pozicije) {
        this.karakter = karakter;
        this.pozicije = pozicije;
    }

    public char getKarakter() {
        return karakter;
    }

    public void setKarakter(char karakter) {
        this.karakter = karakter;
    }

    public List<Integer> getPozicije() {
        return pozicije;
    }

    public void setPozicije(List<Integer> pozicije) {
        this.pozicije = pozicije;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Slovo other = (Slovo) obj;
        return this.karakter == other.karakter;
    }

    @Override
    public String toString() {
        return "Slovo{" + "karakter=" + karakter + ", pozicije=" + pozicije + '}';
    }
    
}
