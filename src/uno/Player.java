
package uno;

import java.util.ArrayList;
public class Player {
    private ArrayList<Carta> carteInMano = new ArrayList<>();
    private String nome;
    public Player(String nome){
        this.nome = nome;
    }
    public Player(){}

    public void aggiungiCarta(Carta carta){
        if(carta!=null){
            this.carteInMano.add(carta);
            this.sortCarte();
        }
    }
    private void sortCarte(){
        ArrayList<Carta> carteSort = new ArrayList<>();
        for(int i=0;i<carteInMano.size();i++){ // Rosso
            if(this.carteInMano.get(i).getColore() == 0){
                //carteSort[pos] = this.CarteInMano[i];
                carteSort.add(carteInMano.get(i));
            }
        }
        for(int i=0;i<carteInMano.size();i++){ // Blu
            if(this.carteInMano.get(i).getColore() == 1){
                carteSort.add(carteInMano.get(i));
            }
        }
        for(int i=0;i<carteInMano.size();i++){ // Verde
            if(this.carteInMano.get(i).getColore() == 2){
                carteSort.add(carteInMano.get(i));
            }
        }
        for(int i=0;i<carteInMano.size();i++){ // Giallo
            if(this.carteInMano.get(i).getColore() == 3){
                carteSort.add(carteInMano.get(i));
            }
        }
        for(int i=0;i<carteInMano.size();i++){
            if(this.carteInMano.get(i).getColore()==4){ // +4
                carteSort.add(carteInMano.get(i));
            }
        }
        for(int i=0;i<carteInMano.size();i++){
            if(this.carteInMano.get(i).getColore()==5){ // cambioColore
                carteSort.add(carteInMano.get(i));
            }
        }

        int[] breakpoint={0,0,0,0};
        boolean[] firstFound={false,false,false,false};
        for(int i=0;i<carteSort.size();i++){
            if(carteSort.get(i).getColore()==0){//Rosso
                if(firstFound[0]==false){
                    firstFound[0] = true;
                    breakpoint[0] = i;
                }
            }
            if(carteSort.get(i).getColore()==1){//Blu
                if(firstFound[1]==false){
                    firstFound[1] = true;
                    breakpoint[1] = i;
                }
            }
            if(carteSort.get(i).getColore()==2){//Verde
                if(firstFound[2]==false){
                    firstFound[2] = true;
                    breakpoint[2] = i;
                }
            }
            if(carteSort.get(i).getColore()==3){//Giallo
                if(firstFound[3]==false){
                    firstFound[3] = true;
                    breakpoint[3] = i;
                }
            }
        }
        for(int i=breakpoint[0];i<breakpoint[1]-1;i++){//rosso
            if(carteSort.get(i).getColore()==carteSort.get(i+1).getColore()){
                if(carteSort.get(i).getNumero()>carteSort.get(i+1).getNumero()){
                    Carta tempCarta = carteSort.get(i+1);
                    carteSort.set(i+1, carteSort.get(i));
                    carteSort.set(i, tempCarta);
                }
            }
        }
        for(int i=breakpoint[1];i<breakpoint[2]-1;i++){//blu
            if(carteSort.get(i).getColore()==carteSort.get(i+1).getColore()){
                if(carteSort.get(i).getNumero()>carteSort.get(i+1).getNumero()){
                    Carta tempCarta = carteSort.get(i+1);
                    carteSort.set(i+1, carteSort.get(i));
                    carteSort.set(i, tempCarta);
                }
            }
        }
        for(int i=breakpoint[2];i<breakpoint[3]-1;i++){ //verde
            if(carteSort.get(i).getColore()==carteSort.get(i+1).getColore()){
                if(carteSort.get(i).getNumero()>carteSort.get(i+1).getNumero()){
                    Carta tempCarta = carteSort.get(i+1);
                    carteSort.set(i+1, carteSort.get(i));
                    carteSort.set(i, tempCarta);
                }
            }
        }
        for(int i=breakpoint[3];i<carteSort.size()-2;i++){ //Giallo,size-2 perchè size inizia da 1
            if(carteSort.get(i).getColore()==carteSort.get(i+1).getColore()){
                if(carteSort.get(i).getNumero()>carteSort.get(i+1).getNumero()){
                    Carta tempCarta = carteSort.get(i+1);
                    carteSort.set(i+1, carteSort.get(i));
                    carteSort.set(i, tempCarta);
                }
            }
        }
        carteInMano = carteSort;
    }
    protected void setColore(int pos,int colore){
        carteInMano.get(pos).setColore(colore);
    }
    public void rimuoviCarta(Carta carta){
        if(this.carteInMano.get(this.carteInMano.indexOf(carta))!=null){
            this.carteInMano.remove(this.carteInMano.indexOf(carta));
        }
    }
    public void giocaFrame(Tavolo tavolo,Mazzo mazzo,Frame frame,Player[] players){
        Player player = this;
        frame.disegnaCarte(player, tavolo, mazzo, players);
        while(!frame.isReady()){
            Thread.onSpinWait();
        }

    }
    public boolean checkCard(Carta carta,Tavolo tavolo){
        if(tavolo.getLast()==null){
            return true;
        }
        if(tavolo.getLast().getNumero()==20){
            return true;
        }
        if(carta.getNumero()== 15 || carta.getNumero()==16){
            return true;
        }
        return !(tavolo.getLast().getColore()!=carta.getColore() && tavolo.getLast().getNumero() != carta.getNumero());
    }
    public boolean checkCard(int pos,Tavolo tavolo){
        if(pos==-1){
            return true;
        }
        if(pos<-1 || pos > carteInMano.size()-1){
//            System.out.println("Questa carta non esiste! ");
            return false;
        }
        if(tavolo.getLast()==null){
            return true;
        }
        if(tavolo.getLast().getNumero()==20){
            return true;
        }
        if(carteInMano.get(pos).getNumero() ==15 || carteInMano.get(pos).getNumero() ==16){
            return true;
        }
        if((tavolo.getLast().getColore()!=this.carteInMano.get(pos).getColore())&& tavolo.getLast().getNumero()!=this.carteInMano.get(pos).getNumero()){
//            System.out.println("Non puoi giocare questa carta!");
            return false;
        }
        return true;
    }

    public boolean checkWin(){
        return carteInMano.isEmpty();
    }

    public String getNome(){
        return this.nome;
    }
    public int numCarteInMano(){
        return this.carteInMano.size();
    }
    public Carta getCarta(int n){
        return this.carteInMano.get(n);
    }
}
