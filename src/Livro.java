
/**
 *
 * @author Fernando Andrauss
 */
public class Livro {
 
    private String titulo;
    private String autor;
    private Double avaliacao;
 
    public Livro(String title, String author, Double rating) {
        this.titulo = title;
        this.autor = author;
        this.avaliacao = rating;
    }
 
    public String getTitulo() {
        return titulo;
    }
 
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
 
    public String getAutor() {
        return autor;
    }
 
    public void setAutor(String autor) {
        this.autor = autor;
    }
 
    public Double getAvaliacao() {
        return avaliacao;
    }
 
    public void setAvaliacao(Double avaliacao) {
        this.avaliacao = avaliacao;
    }

    @Override
    public String toString() {
        return "Livro{" + "titulo=" + titulo + ", autor=" + autor + ", avaliacao=" + avaliacao + '}';
    }

   
 
    
}
