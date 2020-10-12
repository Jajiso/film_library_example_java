package videoteca.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Clase NewFilmDlg que representa el diÃ¡logo para aÃ±adir pelÃ­culas
 */
public class NewFilmDlg extends JDialog {
    // Panel principal del diÃ¡logo
    private Container contentPane;

    // Componentes visuales del diÃ¡logo
    private final JTextField titleTxf;
    private final JComboBox<String> genreCbx;
    private final JTextField yearTxf;
    private final JButton okBtn;
    private final JButton cancelBtn;

    private boolean wasOk;  // wasOk = true  : los datos introducidos son vÃ¡lidos
                            // wasOk = false : los datos introducidos no se tienen en cuenta
    private String genre;   // almacena el gÃ©nero de la pelÃ­cula introducida
    
    // Listener que valida los datos de la nueva pelÃ­cula
    private ActionListener okHandler= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            wasOk =true;
            setVisible(false);
        }
    };

    // Listener que descarta los datos de la nueva pelÃ­cula
    private ActionListener cancelHandler= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            setVisible(false);
        }
    };

    /**
     * AÃ±ade un nuevo componente al panel principal precedido con 
     * una etiqueta y con la alineaciÃ³n indicada
     */ 
    private void addEntryLine(String text, JComponent comp, int aling){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(aling));
        panel.add(new JLabel(text+":"));
        panel.add(comp);
        contentPane.add(panel);
    }

    /**
     * AÃ±ade los botones de la aplicaciÃ³n al panel principal 
     */ 
    private void addButtons(){
        JPanel buttons= new JPanel();
        buttons.add(okBtn);
        buttons.add(cancelBtn);
        contentPane.add(buttons);
    }

    /**
     * Constructor del diÃ¡logo al que se le pasa una referencia 
     * a la ventana principal de la que depende
     * 
     * @param f referencia al frame de la ventana principal
     */ 
    public NewFilmDlg(JFrame f) {

        // Se inicializan las opciones del diÃ¡logo
        super(f, "Nueva película");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(f);
        wasOk = false;
        genre = "";

        // Se crea el componente para introducir el tÃ­tulo de la pelÃ­cula
        titleTxf = new JTextField(15);

        // Se crea el componente para introducir el aÃ±o de la pelÃ­cula
        yearTxf = new JTextField(6);

        // Se crea el componente para introducir el gÃ©nero de la pelÃ­cula
        genreCbx = new JComboBox<>(new String[]{"Otro", "Musical", "Ciencia ficción", "Épica", "Animación"});
        genreCbx.setSelectedItem("Otro");
        genre = (String) genreCbx.getSelectedItem();
        genreCbx.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    genre = (String) genreCbx.getSelectedItem();
                }
            }

        });

        // Se crean los botones de interacciÃ³n con el diÃ¡logo
        okBtn = new JButton("Aceptar");
        okBtn.addActionListener(okHandler);
        cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(cancelHandler);

        // Interfaz del panel principal al que se le aÃ±aden los componentes visuales
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        addEntryLine("Título   ", titleTxf,FlowLayout.RIGHT);
        addEntryLine("Año     ", yearTxf,FlowLayout.LEFT);
        addEntryLine("Género", genreCbx,FlowLayout.LEFT);
        addButtons();
        
        pack(); 
    }

    /**
     * Devuelve el nombre de la pelÃ­cula
     * 
     * @return tÃ­tulo de la pelÃ­cula introducida
     */ 
    public String getFilmTitle() {
        return titleTxf.getText();
    }

    /**
     * Devuelve el aÃ±o de estreno de la pelÃ­cula
     * 
     * @return aÃ±o de la pelÃ­cula introducida
     */ 
    public String getYear() {
        return yearTxf.getText();
    }

    /**
     * Devuelve el gÃ©nero de la pelÃ­cula
     * 
     * @return gÃ©nero de la pelÃ­cula introducida
     */ 
    public String getGenre() {
        if (genre.equals("Otro") && isOk()) {
            String newGenre = JOptionPane.showInputDialog(NewFilmDlg.this, 
                "Introduzca el género de la película",
                "Nuevo género",
                JOptionPane.PLAIN_MESSAGE);
            if (newGenre != null) {
                genre = newGenre;
            } 
        }

        return genre;
    }

    /**
     * Devuelve si los datos introducidos se aceptan o no
     * 
     * @return true los datos de la pelÃ­cula introducida son vÃ¡lidos o
     * @return false y los datos de la pelÃ­cula introducida no se tienen en cuenta
     */ 
    public boolean isOk() {
        return wasOk;
    }
}