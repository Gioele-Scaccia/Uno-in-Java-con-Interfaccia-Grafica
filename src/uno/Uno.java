package uno;

import java.io.*;
public class Uno {
    public static void main(String[] args) throws IOException {
        int quantiGiocatori=0;
        int siONo=-1;
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader tastiera = new BufferedReader(input);
        Mazzo mazzo = new Mazzo();
        Tavolo tavolo = new Tavolo();
        Frame frame = new Frame();
        frame.setSiONo(siONo);
        frame.chiediQuantiGiocatori();
        quantiGiocatori=frame.getNumGiocatori();
        Player[] players = new Player[quantiGiocatori];
        for(int i=0;i<players.length;i++)
        {
            players[i] = new Player("Giocatore "+ (i+1));
        }
        
        mazzo.istanzaGioco(tavolo, players, frame);
            
        
    }

}
