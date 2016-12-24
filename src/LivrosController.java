
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.Rating;

/**
 *
 * @author Fernando Andrauss
 */
public class LivrosController implements Initializable {

    @FXML
    private TableView<Livro> table;

    @FXML
    private TableColumn<Livro, String> titleColumn;

    @FXML
    private TableColumn<Livro, String> authorColumn;

    @FXML
    private TableColumn<Livro, Double> ratingColumn;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField tvTitulo;

    @FXML
    private TextField tvAutor;

    @FXML
    private Rating rating;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnRemove;

    ObservableList<Livro> livros = FXCollections.observableArrayList(
            new Livro("Java FX Pro", "Autor 01", 3.0),
            new Livro("Java FX Interfaces com qualidade", "Bruno Oliveira", 5.0),
            new Livro("Java FX 8", "Autor 2", 4.0));

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setItems(livros);

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("autor"));
        btnAdd.setOnAction(e -> addLivro());
        btnRemove.setOnAction(e -> removeLivro());

        ratingColumn.setCellFactory(t -> new TableCell<Livro, Double>() {
            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    Livro b = ((Livro) getTableRow().getItem()); // Pega o livro da linha
                    System.out.println("it" + item);
                    System.out.println("|B " + b);
                    Rating r = null;
                    if (item == null && b == null) {
                        r = makeRating(0);
                    } else {
                        r = makeRating(item == null
                                ? b.getAvaliacao().doubleValue() // Atualiza o Rating com o valor do livro inicialmente
                                : item); // Atualiza o Rating com o valor do evento
                    }

                    r.ratingProperty().addListener((v, o, n) -> {
                        // Listner para atualizar a avaliação do livro quando o Rating for altarado 
                        b.setAvaliacao(n.doubleValue());

                        // Seleciona a linha que está sendo alterada
                        table.getSelectionModel().select(getTableRow().getIndex());

                        // Aqui deve ser definida a ação de salvar o livro com o valor atualizado no BD
                        System.out.println("SALVAR NO BD --> " + b.toString());
                    });
                    // Mostra o Rating na coluna
                    setGraphic(r);
                }
            }

        });

        // Evento para mostrar os dados do Livro quando selecionar na tabela
        TableView.TableViewSelectionModel<Livro> selectionModel = table.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((Observable observable) -> {
            if (selectionModel.getSelectedItem() != null) {
                textArea.appendText(selectionModel.getSelectedItem().toString() + "\n");
            }
        });

    }

    private Rating makeRating(double rate) {
        Rating rating = new Rating();
        rating.setOrientation(Orientation.HORIZONTAL);
        rating.setUpdateOnHover(false);
        rating.setPartialRating(false);
        rating.setRating(rate);
        return rating;

    }

    private void addLivro() {
        Livro l = new Livro(tvTitulo.getText(), tvAutor.getText(), rating.getRating());
        table.getItems().add(l);

        tvTitulo.setText(null);
        tvAutor.setText(null);
        rating.setRating(0);
    }

    private void removeLivro() {
        if (table.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        table.getItems().remove(table.getSelectionModel().getSelectedIndex());
    }

}
