/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author milos
 */
public class PokreniServer extends Thread {
    private ServerSocket  serverSocket;
    private boolean kraj=false;

    @Override
    public void run() {
        try {
            System.out.println("Soket je otvoren");
            serverSocket=new ServerSocket(9000) ;
            while(!kraj){
                Socket s=serverSocket.accept();
                if(controller.Controller.getInstance().getIgraci().size()>=2){
                    return;
                }
                ObradaKlijentskihZahteva nit=new ObradaKlijentskihZahteva(s);
                controller.Controller.getInstance().getIgraci().add(nit);
                System.out.println("Klijent je povezan");
                nit.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void zaustavi(){
        System.out.println("Soket je zatvoren");
        kraj=true;
        try {
            serverSocket.close();
        } catch (IOException ex) {
            Logger.getLogger(PokreniServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
