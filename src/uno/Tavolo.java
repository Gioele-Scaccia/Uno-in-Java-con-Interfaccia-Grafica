/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uno;

import java.util.ArrayList;
import java.io.*;
public class Tavolo {
    private boolean chiediColore=false;
    private boolean cambiogiro=false;
    private boolean skip = false;
    private boolean dareCarte=false;
    private int quanteCarteDare=0;
    InputStreamReader input = new InputStreamReader(System.in);
    BufferedReader tastiera = new BufferedReader(input);
    private ArrayList<Carta> carteGiocate = new ArrayList<>();
    private int turno=0;
    private int giro=1;
    public Tavolo(){    }
    public int getTurno(){
        return turno;
    }
    public void dareCarteAlProssimo(int quante){
        dareCarte=true;
        quanteCarteDare=quante;
    }
    public boolean getChiediColore(){
        return this.chiediColore;
    }
    public void setChiediColore(boolean n){
        this.chiediColore = n;
    }
    protected void setColoreUltimaCarta(int n){
        this.carteGiocate.getLast().setColore(n);
    }
    public void buttaCarta(Carta carta){
        if(carta.getColore()==4 || carta.getColore()==5){//>3 //+4 e cambioColore
            this.chiediColore=true;
            carteGiocate.add(carta);
        }
        
        switch (carta.getNumero()) {
            case 12 -> {
                //+2
                this.dareCarteAlProssimo(2);
                carteGiocate.add(carta);
            }
            case 13 -> {
                //cambioGiro
                cambiogiro=true;
                carteGiocate.add(carta);
            }
            case 14 -> {
                //skip
                skip=true;
                carteGiocate.add(carta);
            }
            case 15 ->{ //+4
                this.dareCarteAlProssimo(4);
            }
            default -> carteGiocate.add(carta);
        }
    }
    public ArrayList<Carta> getCarteButtate(){
        return carteGiocate;
    }
    public Carta getLast(){
        if(!carteGiocate.isEmpty()){
            return carteGiocate.getLast();
        }
        return null;
        
    }
    public Carta getFirst(){
        if(!carteGiocate.isEmpty()){
            return carteGiocate.getFirst();
        }
        return null;
        
    }
    public int getNumCarteGiocate(){
        return carteGiocate.size();
    }
    public void gioca(Mazzo mazzo,Player[] player,Frame frame){
        if(this.dareCarte){
            for(int i=0;i<this.quanteCarteDare;i++){
                if(mazzo.getNum()==0 && this.carteGiocate.isEmpty()){
                    System.out.println("Impossibile dare altre carte al "+player[turno].getNome());
                }
                Carta cartaPescata = mazzo.pescaCarta(this, player[turno]);
                if(cartaPescata!=null){
                    player[this.turno].aggiungiCarta(cartaPescata);
                }
                
            }
            System.out.println(player[turno].getNome() + " sara' skippato!");
            dareCarte=false;
            this.quanteCarteDare=0;
        }
        
        else{
            player[turno].giocaFrame(Tavolo.this, mazzo, frame, player);
        }
        if(player[turno].numCarteInMano()==1){
            frame.haUnaCarta(player[turno]);
        }
        if(player[turno].checkWin()){
            frame.hannoVinto();
        }
        else{//se non ha vinto
            if(cambiogiro){
                giro=giro*-1;
                cambiogiro=false;
                if(player.length==2){
                    this.gioca(mazzo, player, frame);
                }
            }
            if(skip){
                turno=turno+giro;
                skip=false;
            }
            turno += giro;
            
            this.giocare(mazzo, player,frame);
        }
    }
    protected void increaseTurno(){
        turno=turno+giro;
    }
    public void giocare(Mazzo mazzo,Player[] players,Frame frame){
        if(Thread.currentThread().getName().equals("main")){
            try{
                if(turno > -1 && turno < players.length){
                    this.gioca(mazzo, players,frame);
                }else if(turno <= -1){
                    turno=players.length-1;
                    this.giocare(mazzo, players,frame);
                }else if(turno >= players.length){
                    turno = 0;
                    this.giocare(mazzo, players,frame);
                }

            }catch(Exception e){
             e.printStackTrace();
            }
        }
    }
}
