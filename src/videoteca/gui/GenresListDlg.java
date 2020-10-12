package videoteca.gui;

import videoteca.*;
import java.awt.*;
import javax.swing.*;

public class GenresListDlg extends JDialog{
    
    private JTextArea genre;
    
    public GenresListDlg(JFrame f, Videoteca pelis){
        super(f, "Genres");
        
        genre = new JTextArea(pelis.getGenres().toString());
        genre.setEditable(false);
        
        JScrollPane s = new JScrollPane(genre);
        add(s);
        add(new JPanel(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.EAST);
        add(new JPanel(), BorderLayout.WEST);
        add(new JPanel(), BorderLayout.SOUTH);
        
        setModal(true);
        setLocationRelativeTo(f);
        setSize(480, 110);
        setMinimumSize(new Dimension(150, 100));
    }
    
}
