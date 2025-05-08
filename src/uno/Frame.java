/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package uno;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.InvocationTargetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
public class Frame{
    private int mostrareBlocco;
    private boolean ready;
    private JButton[] carte;
    private Player[] players;
    private Player player;
    private Tavolo tavolo;
    private Mazzo mazzo;
    public int numGiocatori;
    private JButton quanteCarte;
    private Carta cartaSelezionata=null;
    private JButton buttonSelezionato;
    private GridBagConstraints gbc;
    private JLabel cheGiocatore;
    private JFrame frame;
    private JButton ultimaCartaButton;
    private JButton pescareButton;
    public Frame(){
        gbc = new GridBagConstraints();
        frame = new JFrame("UNO");
        ultimaCartaButton = new JButton();
        pescareButton = new JButton();
        quanteCarte = new JButton();
        this.riInizializza();
    }
    private void riInizializza(){
        try {
            SwingUtilities.invokeAndWait(() -> {
                
                frame.setSize(1920,1080);
                frame.getContentPane().setBackground(Color.MAGENTA);
                frame.setLayout(new GridBagLayout());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                
                gbc.insets = new Insets(10,10,10,10);
                gbc.weightx = 1.0;
                gbc.weighty = 1.0;
                gbc.gridx=2; //righa
                gbc.gridy=0; //colonna
                gbc.anchor = GridBagConstraints.NORTH;
                gbc.fill = GridBagConstraints.NONE;
                
                ultimaCartaButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                ultimaCartaButton.setMargin(new Insets(0,0,0,0));
                ultimaCartaButton.setSize(80,90);
                ultimaCartaButton.setPreferredSize(new Dimension(80, 90));
                ultimaCartaButton.setContentAreaFilled(false);
                ultimaCartaButton.setBorderPainted(false);
                ultimaCartaButton.setFocusPainted(false);
                Image scaledImage = new Carta(20,20).getImg().getScaledInstance(80, 90, Image.SCALE_SMOOTH);
                ultimaCartaButton.setIcon(new ImageIcon(scaledImage));
                frame.add(this.ultimaCartaButton,gbc);
                
                
                gbc.gridx=0;
                gbc.gridy=0;
                gbc.anchor = GridBagConstraints.NORTHWEST;
                
                pescareButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                pescareButton.setMargin(new Insets(0,0,0,0));
                pescareButton.setSize(160,180);
                pescareButton.setPreferredSize(new Dimension(160,180));
                pescareButton.setContentAreaFilled(false);
                pescareButton.setBorderPainted(false);
                pescareButton.setFocusPainted(false);
                ImageIcon icon = new ImageIcon("unoDeck.png");
                scaledImage = icon.getImage().getScaledInstance(50, 75, Image.SCALE_SMOOTH);
                pescareButton.setIcon(new ImageIcon(scaledImage));
                frame.add(this.pescareButton,gbc);
 
                gbc.gridx=0; //righa
                gbc.gridy=0; //colonna
                gbc.anchor = GridBagConstraints.WEST;
                
                cheGiocatore = new JLabel("Giocatore 1");
                frame.add(cheGiocatore,gbc);
                
                gbc.gridx=5; //righa
                gbc.gridy=0; //colonna
                gbc.anchor = GridBagConstraints.EAST;
//                
                quanteCarte.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                quanteCarte.setText("Num Carte");
                quanteCarte.setMargin(new Insets(0,0,0,0));
                quanteCarte.setSize(80,90);
                quanteCarte.setPreferredSize(new Dimension(80, 90));
                quanteCarte.setBackground(Color.BLACK);
                quanteCarte.setForeground(Color.MAGENTA);
//                
//                
//                
//                

                frame.add(quanteCarte,gbc);
                quanteCarte.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(players!=null && player!=null && tavolo!=null &&  mazzo!=null){
                            JDialog popup = new JDialog(frame, "",true);
                            popup.setSize(300,250);
                            popup.setLocation(500, 322);
                            popup.setBackground(Color.MAGENTA);
                            popup.setLayout(new BoxLayout(popup.getContentPane(),BoxLayout.Y_AXIS));
                            JLabel labelN = new JLabel("Tu sei "+player.getNome());
                            popup.add(labelN);
                            popup.add(Box.createVerticalStrut(10));
                            for(Player player : players){
                                JLabel label = new JLabel(player.getNome()+ " ha: "+player.numCarteInMano()+" carte");
                                popup.add(label);
                            }
                            
                            JButton confirmButton = new JButton("OK");
   
                            confirmButton.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            popup.dispose();
                                        }
                                    });
                            popup.add(confirmButton);
                            popup.setVisible(true);
                            
                        }
                    }
                });
                
                                            
                
                
                
                frame.setVisible(true);
                
                
                ultimaCartaButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
                        if(cartaSelezionata != null){
                            if(player.checkCard(cartaSelezionata, tavolo)){
                                tavolo.buttaCarta(cartaSelezionata);
                                
                                if(tavolo.getChiediColore()){
                                    disegnaChiediColore();
                                }
                                setColoreBackground(cartaSelezionata.getColore());
                                
                                player.rimuoviCarta(cartaSelezionata);
                                ready=true;
                                cartaSelezionata=null;
                                cambiaIcona();
                                
                            }
                        }
                    }
                });
                
                pescareButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e){
//                            System.out.println("pescato "+mazzo.getLast().cheCarta());
                        player.aggiungiCarta(mazzo.pescaCarta(tavolo, player));
                        ready=true;
                        
                    }
                });
            });
            for(Component component :frame.getContentPane().getComponents()){
                    component.setName("component");
            }
        } catch (InterruptedException | InvocationTargetException ex) {
            Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String cheColore(int n){
        switch(n){
            case 0 -> {
                return "Rosso";
            }
            case 1 -> {
                return "Blue";
            }
            case 2 -> {
                return "Verde";
            }
            case 3 -> {
                return "Giallo";
            }
        }
        return null;
    }
    protected void hannoVinto(){
        for (Player player1 : players) {
            if (player1.checkWin()) {
                JDialog popup = new JDialog(frame, "HAI VINTO!!",true);
                popup.setSize(300,150);
                popup.setLocation(500, 322);
                popup.setBackground(Color.MAGENTA);
                popup.setLayout(new FlowLayout());
                JButton confirmButton = new JButton(player1.getNome() + " HA VINTO!");
                confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        popup.dispose();
                        frame.dispose();
                    }
                });
                popup.add(confirmButton);
                popup.setVisible(true);
            }
        }
    }
    public int getNumGiocatori(){
        return numGiocatori;
    }
    public void chiediQuantiGiocatori(){
        JDialog popup = new JDialog(frame, "Quanti giocatori ",true);
        popup.setSize(300,150);
        popup.setLocation(500, 322);
        popup.setLayout(new FlowLayout());
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JSlider slider = new javax.swing.JSlider(2, 10,2);
        JButton confirmButton = new JButton("Conferma la scelta ");
        slider.setPaintLabels(true);
        
        slider.addChangeListener(new javax.swing.event.ChangeListener() {
                    @Override
                    public void stateChanged(javax.swing.event.ChangeEvent evt) {
                        confirmButton.setText("Numero giocatori "+slider.getValue());
                    }
                });
        popup.add(slider);
        confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        numGiocatori=slider.getValue();
                        popup.dispose();
                    }
                });
        popup.add(confirmButton);
        popup.setVisible(true);
    }
    public void disegnaChiediColore(){
        JDialog popup = new JDialog(frame, "Scegli colore ",true);
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setSize(400,200);
        popup.setLocation(500, 322);
        popup.setLayout(new FlowLayout());
        JButton rosso = new JButton();
        rosso.setBackground(Color.RED);
        rosso.setBorderPainted(false);
        rosso.setFocusPainted(false);
        rosso.setSize(75,150);
        rosso.setPreferredSize(new Dimension(75,150));
        JButton blu = new JButton();
        blu.setBackground(Color.BLUE);
        blu.setBorderPainted(false);
        blu.setFocusPainted(false);
        blu.setSize(75,150);
        blu.setPreferredSize(new Dimension(75,150));
        JButton verde = new JButton();
        verde.setBackground(Color.GREEN);
        verde.setBorderPainted(false);
        verde.setFocusPainted(false);
        verde.setSize(75,150);
        verde.setPreferredSize(new Dimension(75,150));
        JButton giallo = new JButton();
        giallo.setBackground(Color.YELLOW);
        giallo.setBorderPainted(false);
        giallo.setFocusPainted(false);
        giallo.setSize(75,150);
        giallo.setPreferredSize(new Dimension(75,150));
        
        rosso.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tavolo.setColoreUltimaCarta(0);
                        tavolo.setChiediColore(false);
                        popup.dispose();
                    }
                });
        blu.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tavolo.setColoreUltimaCarta(1);
                        tavolo.setChiediColore(false);
                        popup.dispose();
                    }
                });
        verde.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tavolo.setColoreUltimaCarta(2);
                        tavolo.setChiediColore(false);
                        popup.dispose();
                    }
                });
        giallo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        tavolo.setColoreUltimaCarta(3);
                        tavolo.setChiediColore(false);
                        popup.dispose();
                    }
                });
        
        popup.add(rosso);
        popup.add(blu);
        popup.add(verde);
        popup.add(giallo);
        popup.setVisible(true);
    }
    public void cambiaIcona(){
        Image scaledImage = tavolo.getLast().getImg().getScaledInstance(80, 90, Image.SCALE_SMOOTH);
        ultimaCartaButton.setIcon(new ImageIcon(scaledImage));
    }
    public void haUnaCarta(Player player){
        JDialog popup = new JDialog(frame, "UNO!",true);
        popup.setSize(200,100);
        popup.setLocation(500, 322);
        popup.setLayout(new FlowLayout());
        JButton confirmButton = new JButton("OK");
        JLabel label = new JLabel("Il "+player.getNome()+" ha fatto UNO!" );
        label.setSize(75,75);
        confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        popup.dispose();
                    }
                });
        popup.add(label);
        popup.add(confirmButton);
        popup.setVisible(true);
    }
    public void doContinue(){
        JDialog popup = new JDialog(frame, "Continuare",true);
        popup.setSize(150,100);
        popup.setLocation(500, 322);
        popup.setLayout(new FlowLayout());
        JButton confirmButton = new JButton("Continua");
        confirmButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        popup.dispose();
                    }
                });
        popup.add(confirmButton);
        popup.setVisible(true);
    }
    public void setSiONo(int x){
        JDialog popup = new JDialog(frame, "Mostrare blocco",true);
        popup.setSize(250,100);
        popup.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        popup.setLocation(500, 322);
        popup.setLayout(new FlowLayout());
        JLabel label = new JLabel("Mostrare blocco fra ogni giocata?     ");
        JButton si = new JButton("SI");
        si.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrareBlocco=1;
                        popup.dispose();
                    }
                });
        JButton no = new JButton("NO");
        no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        mostrareBlocco=0;
                        popup.dispose();
                    }
                });
        popup.add(label);
        popup.add(si);
        popup.add(no);
        popup.setVisible(true);
    }
    public void disegnaCarte(Player player, Tavolo tavolo, Mazzo mazzo, Player[] players) {
    try {
        if(this.mostrareBlocco==1){
            frame.setVisible(false);
            this.doContinue();
            frame.setVisible(true);
        }
        this.players = players;
        this.player = player;
        this.tavolo = tavolo;
        this.mazzo = mazzo;

        if (ready) {
            if(Thread.currentThread().getName().equals("main")){
                SwingUtilities.invokeAndWait(() -> {
                    for(Component component : frame.getContentPane().getComponents()){
                        if(component.getName().equals("carta")){
                            frame.getContentPane().remove(component);
                        }
                    }
                    carte = null;
                    cheGiocatore.setText(player.getNome());
                    frame.getContentPane().revalidate();
                    frame.getContentPane().repaint();
                    ready = false;
                });
            }
        }

        carte = new JButton[player.numCarteInMano()];
        for (int i = 0; i < player.numCarteInMano(); i++) {
            gbc.gridx = i;
            gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.SOUTHWEST;
            player.getCarta(i).intImg();
            Image scaledImage = player.getCarta(i).getImg().getScaledInstance(50, 75, Image.SCALE_SMOOTH);

            carte[i] = new JButton();
            carte[i].setIcon(new ImageIcon(scaledImage));
            carte[i].setName("carta");
            carte[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            carte[i].setMargin(new Insets(0, 0, 0, 0));
            carte[i].setSize(50, 75);
            carte[i].setPreferredSize(new Dimension(50, 75));
            carte[i].setContentAreaFilled(false);
            carte[i].setBorderPainted(false);
            carte[i].setFocusPainted(false);
            int index = i;
            if(Thread.currentThread().getName().equals("main")){
                SwingUtilities.invokeAndWait(() -> {
                    frame.getContentPane().add(carte[index], gbc);
                });
            }
            carte[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    carte[index].setSize(carte[index].getWidth() + 15, carte[index].getHeight() + 15);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    carte[index].setSize(carte[index].getWidth() - 15, carte[index].getHeight() - 15);
                }
            });

            carte[index].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                        System.out.println(player.getCarta(index));
                    if (buttonSelezionato != null && buttonSelezionato != carte[index]) {
                        buttonSelezionato.setSize(50, 75);
                    }
                    carte[index].setSize(50 + (15 * 2), 75 + (15 * 2));
                    cartaSelezionata = player.getCarta(index);
                    buttonSelezionato = carte[index];
                }
            });
        }

        if(Thread.currentThread().getName().equals("main")){
            SwingUtilities.invokeAndWait(() -> {
                frame.getContentPane().invalidate();
                frame.getContentPane().revalidate();
                frame.getContentPane().repaint();
            });
        }
        

    } catch (InterruptedException | InvocationTargetException ex) {
        Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
    }
}

    public boolean isReady(){
        return ready;
    }
    public void setColoreBackground(int color){
        if(color > 3 || color < 0){
            frame.getContentPane().setBackground(Color.MAGENTA);
        }else{
            switch(color){
                case 0 -> frame.getContentPane().setBackground(Color.RED);
                case 1 -> frame.getContentPane().setBackground(Color.BLUE);
                case 2 -> frame.getContentPane().setBackground(Color.GREEN);
                case 3 -> frame.getContentPane().setBackground(Color.YELLOW);
            }
        }
        
    }
    
}
