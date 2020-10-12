package videoteca;
import java.util.*;

public class Videoteca{
    
    private String title;
    private Set<Film> videoteca;
    
    public Videoteca(String titulo){
        title = titulo;
        videoteca = new TreeSet<Film>(new claseOrden0());
    }
    
    public boolean add(Film pelicula){
        return videoteca.add(pelicula);
    }
    
    public boolean remove(Film pelicula){
        return videoteca.remove(pelicula);
    }
    
    public void setOrder(int orden){
        if (orden == 0){
            Set<Film> nuevo = new TreeSet<Film>(new claseOrden0());
            nuevo.addAll(videoteca);
            videoteca = nuevo;
        } else if(orden == 1){
            Set<Film> nuevo = new TreeSet<Film>(new claseOrden1());
            nuevo.addAll(videoteca);
            videoteca = nuevo;
        } else{
            System.out.println("orden invalido");
        }
    }
    
    public Set<Film> getFilms(){
        return videoteca;
    }
    
    public List<String> getGenres(){
        Map<String, Integer> generos = new HashMap<String, Integer>();
        
        for(Film i : videoteca){
            int num = 1;
            if(generos.containsKey(i.getGenre())){
                num = generos.get(i.getGenre()) + 1;
            }
            generos.put(i.getGenre(), num);
        }
        
        List<String> genreList = new ArrayList<>();
        for(Map.Entry<String, Integer> i : generos.entrySet()){
            genreList.add(i.getKey() + ": " + i.getValue());
        }
        return genreList;
    }
    
    public String toString(){
        String result = title + '\n';
        int pos = 1;
        
        for(Film i : videoteca){
            result += pos + ") " + i.toString() + '\n';
            pos++;
        }
        
        return result;
    }
    

    //Clases para el orden del conjunto Film
    
    private static class claseOrden0 implements Comparator<Film>{
        public int compare(Film a, Film b){
            if(a.getTitle().equalsIgnoreCase(b.getTitle()) ){
                if(a.getGenre().equalsIgnoreCase(b.getGenre())){
                    return Integer.compare(a.getYear(), b.getYear());
                } else{
                    return a.getGenre().compareToIgnoreCase(b.getGenre());
                }
            } else{
                return a.getTitle().compareToIgnoreCase(b.getTitle());
            }
        }
    }
    
    private static class claseOrden1 implements Comparator<Film>{
        public int compare(Film a, Film b){
            if(a.getYear() == (b.getYear()) ){
                if(a.getTitle().equalsIgnoreCase(b.getTitle())){
                    return a.getGenre().compareToIgnoreCase(b.getGenre());
                } else{
                    return a.getTitle().compareToIgnoreCase(b.getTitle());
                }
            } else{
                return Integer.compare(a.getYear(), b.getYear());
            }
        }
    }
    
}