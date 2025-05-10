package uno;

import java.io.*;
public class Uno {
    public static void main(String[] args){
        Mazzo mazzo = new Mazzo();
        Tavolo tavolo = new Tavolo();
        Frame frame = new Frame();
        frame.bloccoFraTurni();
        frame.chiediQuantiGiocatori();
        Player[] players = new Player[frame.getNumGiocatori()];
        for(int i=0;i<players.length;i++)
        {
            players[i] = new Player("Giocatore "+ (i+1));
        }
        
        mazzo.istanzaGioco(tavolo, players, frame);
            
        
    }

}
