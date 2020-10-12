package videoteca.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RemoveFilmDlg extends JDialog{
    private Container contentPane;
    
    private final JTextField titleTxf;
    private final JTextField yearTxf;
    private final JComboBox<String> genreCbx;
    private final JButton okBtn;
    private final JButton cancelBtn;

    private String genre;
    private boolean wasOk;
    
    private ActionListener okHandler= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            wasOk =true;
            setVisible(false);
        }
    };
    
    private ActionListener cancelHandler= new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            setVisible(false);
        }
    };
    
    private void addEntryLine(String text, JComponent comp, int aling){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(aling));
        panel.add(new JLabel(text+":"));
        panel.add(comp);
        contentPane.add(panel);
    }
    
    private void addButtons(){
        JPanel buttons= new JPanel();
        buttons.add(okBtn);
        buttons.add(cancelBtn);
        contentPane.add(buttons);
    }
    
    public String getFilmTitle() {
        return titleTxf.getText();
    }
    
    public String getYear() {
        return yearTxf.getText();
    }
    
    public String getGenre() {
        if (genre.equals("Otro") && isOk()) {
            String newGenre = JOptionPane.showInputDialog(RemoveFilmDlg.this, 
                "Introduzca el género de la película",
                "Indique género",
                JOptionPane.PLAIN_MESSAGE);
            if (newGenre != null) {
                genre = newGenre;
            } 
        }

        return genre;
    }
    
    public boolean isOk() {
        return wasOk;
    }

    public RemoveFilmDlg(JFrame f){
        super(f, "Eliminar película");
        setResizable(false);
        setModal(true);
        setLocationRelativeTo(f);
        wasOk = false;
        genre = "";
        
        titleTxf = new JTextField(15);
        yearTxf = new JTextField(6);
        
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

        
        okBtn = new JButton("Aceptar");
        okBtn.addActionListener(okHandler);
        cancelBtn = new JButton("Cancelar");
        cancelBtn.addActionListener(cancelHandler);
        
        contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        addEntryLine("Título   ", titleTxf,FlowLayout.RIGHT);
        addEntryLine("Año     ", yearTxf,FlowLayout.LEFT);
        addEntryLine("Género", genreCbx,FlowLayout.LEFT);
        addButtons();
        
        pack(); 
    }

}
