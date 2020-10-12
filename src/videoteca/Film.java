package videoteca;
//import java.util.*;

public class Film {
    private String title;   // nombre de la pelÃ­cula
    private String genre;   // clasificaciÃ³n temÃ¡tica de la pelÃ­cula
    private int year;       // aÃ±o de estreno de la pelÃ­cula

    /**
     * Constructor al que se le pasa el tÃ­tulo, 
     * el gÃ©nero y el aÃ±o de estreno de la pelÃ­cula
     * 
     * @param title nombre de la pelÃ­cula
     * @param genre gÃ©nero de la pelÃ­cula
     * @param year aÃ±o de estreno de la pelicula
     */ 
    public Film(String title, String genre, int year) {
        this.title = title;
        this.genre = genre;
        this.year = year;
    }

    /**
     * Devuelve el nombre de la pelÃ­cula
     * 
     * @return tÃ­tulo de la pelÃ­cula
     */ 
    public String getTitle() {
        return title;
    }

    /**
     * Devuelve la clasificaciÃ³n temÃ¡tica de la pelÃ­cula
     * 
     * @return gÃ©nero de la pelÃ­cula
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Devuelve el aÃ±o de estreno de la pelÃ­cula
     * 
     * @return aÃ±o de la pelÃ­cula
     */ 
    public int getYear() {
        return year;
    }
    
    public String toString(){
        return getTitle() + " | " + getGenre() + " | " + getYear();
    }
    
    @Override
    public boolean equals(Object a){
        if(a != null && a instanceof Film){
            Film pelicula = (Film) a;
            if(pelicula.getTitle().equals(title) && pelicula.getYear() == year){
                return true;
            } 
        }
        return false;
    }
}
