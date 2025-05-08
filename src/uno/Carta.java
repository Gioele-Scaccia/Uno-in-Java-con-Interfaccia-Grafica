package uno;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Carta extends JPanel{
    private Image img;
    private int colore;
    private int numero;
    
    public Carta(int colore,int numero) {
        this.colore = colore;
        this.numero = numero;
        intImg();
        this.setPreferredSize(new Dimension(100, 150));
    }
    public Carta(int colore,int numero,int x,int y) {
        this.colore = colore;
        this.numero = numero;
        
        
        intImg();
        this.setPreferredSize(new Dimension(100, 150));
    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(img, 100, 100, this);
    }
    public Image getImg(){
        return img;
    }
    
    public void intImg(){ //rosso blu verde giallo
        Toolkit tk = Toolkit.getDefaultToolkit();
        switch(colore){ //mi sento di dover specificare che ho seguito l'hint dell'IDE
            case 0->{
                img = switch (numero) {
                case 0 -> tk.getImage("0-Rosso.png");
                case 1 -> tk.getImage("1-Rosso.png");
                case 2 -> tk.getImage("2-Rosso.png");
                case 3 -> tk.getImage("3-Rosso.png");
                case 4 -> tk.getImage("4-Rosso.png");
                case 5 -> tk.getImage("5-Rosso.png");
                case 6 -> tk.getImage("6-Rosso.png");
                case 7 -> tk.getImage("7-Rosso.png");
                case 8 -> tk.getImage("8-Rosso.png");
                case 9 -> tk.getImage("9-Rosso.png");
                case 12 -> tk.getImage("+2-Rosso.png");
                case 13 -> tk.getImage("CambioGiro-Rosso.png");
                case 14 -> tk.getImage("Skip-Rosso.png");
                default -> tk.getImage("unoBackSide.png");
            };
            }
            case 1 ->{
                img = switch (numero) {
                case 0 -> tk.getImage("0-Blu.png");
                case 1 -> tk.getImage("1-Blu.png");
                case 2 -> tk.getImage("2-Blu.png");
                case 3 -> tk.getImage("3-Blu.png");
                case 4 -> tk.getImage("4-Blu.png");
                case 5 -> tk.getImage("5-Blu.png");
                case 6 -> tk.getImage("6-Blu.png");
                case 7 -> tk.getImage("7-Blu.png");
                case 8 -> tk.getImage("8-Blu.png");
                case 9 -> tk.getImage("9-Blu.png");
                case 12 -> tk.getImage("+2-Blu.png");
                case 13 -> tk.getImage("CambioGiro-Blu.png");
                case 14 -> tk.getImage("Skip-Blu.png");
                default -> tk.getImage("unoBackSide.png");
            };
            }
            case 2 ->{
                img = switch (numero) {
                case 0 -> tk.getImage("0-Verde.png");
                case 1 -> tk.getImage("1-Verde.png");
                case 2 -> tk.getImage("2-Verde.png");
                case 3 -> tk.getImage("3-Verde.png");
                case 4 -> tk.getImage("4-Verde.png");
                case 5 -> tk.getImage("5-Verde.png");
                case 6 -> tk.getImage("6-Verde.png");
                case 7 -> tk.getImage("7-Verde.png");
                case 8 -> tk.getImage("8-Verde.png");
                case 9 -> tk.getImage("9-Verde.png");
                case 12 -> tk.getImage("+2-Verde.png");
                case 13 -> tk.getImage("CambioGiro-Verde.png");
                case 14 -> tk.getImage("Skip-Verde.png");
                default -> tk.getImage("unoBackSide.png");
            };
            }
            case 3 ->{
                img = switch (numero) {
                case 0 -> tk.getImage("0-Giallo.png");
                case 1 -> tk.getImage("1-Giallo.png");
                case 2 -> tk.getImage("2-Giallo.png");
                case 3 -> tk.getImage("3-Giallo.png");
                case 4 -> tk.getImage("4-Giallo.png");
                case 5 -> tk.getImage("5-Giallo.png");
                case 6 -> tk.getImage("6-Giallo.png");
                case 7 -> tk.getImage("7-Giallo.png");
                case 8 -> tk.getImage("8-Giallo.png");
                case 9 -> tk.getImage("9-Giallo.png");
                case 12 -> tk.getImage("+2-Giallo.png");
                case 13 -> tk.getImage("CambioGiro-Giallo.png");
                case 14 -> tk.getImage("Skip-Giallo.png");
                default -> tk.getImage("unoBackSide.png");
            };
            }
            default -> {
                img = switch (numero) {
                case 15 -> tk.getImage("+4.png");
                case 16 -> tk.getImage("CambioColore.png");
                default -> tk.getImage("unoBackSide.png");
            };
            }
            
                
        }
    }
    
    public void setCoordx(int x){
    }
    public void setCoordy(int y){
    }
    
    public Carta(){}

    public int getColore() {
        return colore;
    }

    protected void setColore(int colore) {
        this.colore = colore;
    }

    public int getNumero() {
        return numero;
    }

    protected void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Carta{" + "colore=" + colore + ", numero=" + numero + '}';
    }
    
    public String cheCarta(){
        String RESET="\u001b[30m";
        String coloreTesto="";
        switch(colore){
            case 0:
                coloreTesto = "\u001B[31m";
                break;
            case 1:
                coloreTesto= "\u001B[34m";
                break;
            case 2:
                coloreTesto = "\u001B[32m";
                break;
            case 3:
                coloreTesto= "\u001B[33m";
                break;
            default:
                coloreTesto = "\u001B[35m";
                break;
            
        }
        switch(numero){ // dava "unrecheable statement" sui break quindi ho seguito il consiglio dell'IDE
            case 12 -> {
                return coloreTesto+"+2"+RESET;
            }
            case 13 -> {
                return coloreTesto+"cambioGiro"+RESET;
            }
            case 14 -> {
                return coloreTesto+"SKIP"+RESET;
            }
            case 15 -> {
                return coloreTesto+"+4"+RESET;
            }
            case 16 -> {
                return coloreTesto+"cambioColore"+RESET;
            }
            default -> {
                return coloreTesto+numero+RESET;
            }
        }
    }
}