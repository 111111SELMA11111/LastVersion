package JavaDevProject;

import javafx.scene.control.TableCell;
import javafx.scene.text.Text;

public class WrappingTableCell<S, T> extends TableCell<S, T> {
    private Text text;

    public WrappingTableCell() {
        text = new Text();
        text.wrappingWidthProperty().bind(widthProperty().subtract(10));  // Un peu de marge
        setGraphic(text);
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            text.setText(null);
        } else {
            text.setText(item.toString());
            setGraphic(text);
        }
    }
}
