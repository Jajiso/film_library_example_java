package videoteca.gui;

import videoteca.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase VideotecaGUI que representa la ventana principal de la videoteca
 */
public class VideotecaGUI extends JFrame {
    
    private JTextArea listP;
    private JButton addFilmJB, removeFilmJB, listGenreJB;
    private JRadioButton titleRB, yearRB;
    private JScrollPane scroll;
    private Videoteca pelis;
    
    //Creamos y definimos el comportamiento de los botones de radio
    private class GestorBtnRad implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getSource() == titleRB){
                pelis.setOrder(0);
                listP.setText(""+pelis);
            }else if (e.getSource() == yearRB){
                pelis.setOrder(1);
                listP.setText(""+pelis);
            }
        }
    } 
            
    public VideotecaGUI(String t){
        super(t);
        
        pelis = new Videoteca(t);
        initVideoteca(pelis);
        
        setLayout(new BorderLayout());
        
        JPanel panelBTN = new JPanel();
        panelBTN.setLayout(new BoxLayout(panelBTN, BoxLayout.Y_AXIS));
        JPanel panelRBTN = new JPanel();
        
        //Creamos los botones
        addFilmJB = new JButton("Añadir película");
        removeFilmJB = new JButton("Eliminar película"); 
        listGenreJB = new JButton("Géneros de películas");
        addFilmJB.setAlignmentX(addFilmJB.CENTER_ALIGNMENT);
        removeFilmJB.setAlignmentX(removeFilmJB.CENTER_ALIGNMENT);
        listGenreJB.setAlignmentX(listGenreJB.CENTER_ALIGNMENT);
        
        panelBTN.add(addFilmJB);
        panelBTN.add(removeFilmJB);
        panelBTN.add(listGenreJB);
        
        //Creamos y agrupamos los botones de radio, con su respectiva etiqueta
        titleRB = new JRadioButton("Por Título", true);
        yearRB = new JRadioButton("Por año");
        
        ButtonGroup grupoRB = new ButtonGroup();
        grupoRB.add(titleRB);
        grupoRB.add(yearRB);
        
        panelRBTN.add(new JLabel("Orden:"));
        panelRBTN.add(titleRB);
        panelRBTN.add(yearRB);
        
        //Creamos el area donde se mostraran las pelis y añadimos el scroll
        listP = new JTextArea(pelis.toString());
        listP.setEditable(false);
        scroll = new JScrollPane(listP);
        
        //Añadimos todo al panel principal
        add(panelBTN, BorderLayout.WEST);
        add(panelRBTN, BorderLayout.SOUTH);
        add(scroll, BorderLayout.CENTER);
        add(new JPanel(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.EAST);
        pack();
        
        //Definimos los parametros de la ventana principal
        setLocation(900, 300);
        setSize(600,350);
        setMinimumSize(new Dimension(300,200));
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Definimos el comportamiento de los correspondientes botones
        addFilmJB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    NewFilmDlg a = new NewFilmDlg(VideotecaGUI.this);
                    a.setVisible(true);
                    if (a.isOk() ) {
                        pelis.add(new Film(a.getFilmTitle(), a.getGenre(), Integer.parseInt(a.getYear())));
                        listP.setText(""+pelis);
                    }
                }
            });
        removeFilmJB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    RemoveFilmDlg a = new RemoveFilmDlg(VideotecaGUI.this);
                    a.setVisible(true);
                    if (a.isOk() ) {
                        boolean removed = pelis.remove(new Film(a.getFilmTitle(),a.getGenre(),Integer.parseInt(a.getYear())));
                        if(removed == false){
                            JOptionPane.showMessageDialog(null, "Esta película no existe", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        listP.setText(""+pelis);
                    }
                }
            });
        listGenreJB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    GenresListDlg a = new GenresListDlg(VideotecaGUI.this, pelis);
                    a.setVisible(true);
                }
            });
        titleRB.addActionListener(new GestorBtnRad());
        yearRB.addActionListener(new GestorBtnRad());
    }

    
    
    
// DESCOMENTAR PARA INICIALIZAR LA VIDEOTECA
    /**
     * Inicializa la videoteca con pelÃ­culas de varios gÃ©neros
     */ 
    private void initVideoteca(Videoteca videoteca){
        videoteca.setOrder(0);
        
        videoteca.add(new Film("Los miserables", "Musical", 2012));
        videoteca.add(new Film("El fantasma de la ópera", "Musical", 2004));
        videoteca.add(new Film("Cantando bajo la lluvia", "Musical", 1952));
        videoteca.add(new Film("Forrest Gump", "Comedia", 1994));
        videoteca.add(new Film("Star Wars: Episodio IV - Una nueva esperanza", "Ciencia ficción", 1977));
        videoteca.add(new Film("Star Wars: Episodio V - El Imperio contraataca", "Ciencia ficción", 1980));
        videoteca.add(new Film("Star Wars: Episodio VI - El regreso del Jedi", "Ciencia ficción", 1983));
        videoteca.add(new Film("Star Wars: Episodio I - La amenaza fantasma", "Ciencia ficción", 1999));
        videoteca.add(new Film("Star Wars: Episodio II - El ataque de los clones", "Ciencia ficción", 2002));
        videoteca.add(new Film("Star Wars: Episodio III - La venganza de los Sith", "Ciencia ficción", 2005));
        videoteca.add(new Film("Star Wars: Episodio VII - El despertar de la Fuerza", "Ciencia ficción", 2015));
        videoteca.add(new Film("Rogue One: Una historia de Star Wars", "Ciencia ficción", 2016));
        videoteca.add(new Film("Lo que el viento se llevó", "Épica", 1939));
        videoteca.add(new Film("Lawrence de Arabia", "Épica", 1962));
        videoteca.add(new Film("La lista de Schindler", "Épica", 1993));
        videoteca.add(new Film("El rey león", "Animación", 1994));
        videoteca.add(new Film("La bella y la bestia", "Animación", 1991));
        videoteca.add(new Film("Buscando a Nemo", "Animación", 2004));
        videoteca.add(new Film("Buscando a Nemo", "Dibujos", 2004));
    }

    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }catch(Exception e){
            
        }
        new VideotecaGUI("Mi videoteca");
    }
}