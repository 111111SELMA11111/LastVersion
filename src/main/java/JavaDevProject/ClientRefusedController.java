package JavaDevProject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
public class ClientRefusedController {
    @FXML
    private TableView<ClientRefused> tableprospectrefused;

    public void initialize() {
        ClientBaseHandler clientBaseHandler = new ClientBaseHandler();
        ObservableList<ClientRefused> refusedClients = FXCollections.observableArrayList(clientBaseHandler.getItemsss());
        tableprospectrefused.setItems(refusedClients);
    }
}
