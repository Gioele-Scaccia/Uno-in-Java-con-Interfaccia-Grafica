package uno;
import java.util.ArrayList;
import java.util.Random;
public class Mazzo {
    private ArrayList<Carta> carteInMazzo = new ArrayList<>();
    public Mazzo(){
    }
    private void inizializzaMazzo(){
        for(int i=0;i<4;i++){
            for(int j=0;j<10;j++){
                carteInMazzo.add(new Carta(i,j));
            }
            for(int j=1;j<=9;j++){
                carteInMazzo.add(new Carta(i,j));
            }
            for(int j=0;j<2;j++){
            carteInMazzo.add(new Carta(i,12)); //carta +2
            carteInMazzo.add(new Carta(i,13)); //cambioGiro
            carteInMazzo.add(new Carta(i,14)); //skip
            }

        }
        for(int i=0;i<4;i++){
            carteInMazzo.add(new Carta(4,15)); //+4
            carteInMazzo.add(new Carta(5,16)); //cambioColore
        }
        this.Shuffle();
    }
    private void Shuffle(){
        Random r = new Random();
        Carta[] carteImmischiate = new Carta[carteInMazzo.size()];
        int[] numeriRandom = r.ints(0,carteInMazzo.size()).distinct().limit(carteInMazzo.size()).toArray();
        for(int i=0;i<carteInMazzo.size();i++){
            carteImmischiate[i] = this.carteInMazzo.get(numeriRandom[i]);
        }
        for(int i=0;i<this.carteInMazzo.size();i++){
            this.carteInMazzo.set(i, carteImmischiate[i]);
        }
    }
    public void distribuisci(Player player){
        for(int j=0;j<7;j++){
            player.aggiungiCarta(this.carteInMazzo.removeLast());
        }
    }
    
    public void istanzaGioco(Tavolo tavolo, Player[] players,Frame frame){
        //this.stringa();
        this.inizializzaMazzo();
        for (Player player : players) {
            this.distribuisci(player);
        }
        tavolo.giocare(this, players,frame);
        
    }
    public Carta pescaCarta(Tavolo tavolo,Player player){
        if(this.carteInMazzo.isEmpty()){
            if(tavolo.getNumCarteGiocate()==0){
                return null;
            }
            this.carteInMazzo = tavolo.getCarteButtate();
            this.Shuffle();
        }
        return this.carteInMazzo.removeLast();
    }
    protected Carta getLast(){
        if(!carteInMazzo.isEmpty()){
            return this.carteInMazzo.getLast();
        }
        return null;
    }
    protected int getNum(){
        return carteInMazzo.size();
    }
}
