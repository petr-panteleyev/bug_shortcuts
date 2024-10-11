package shortcuts;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

import static javafx.scene.input.KeyCombination.SHORTCUT_DOWN;

class MainDialog extends Dialog {
    private final Label label = new Label();
    private final TextField textField = new TextField();

    public MainDialog() {
        getDialogPane().setContent(new VBox(10, label, textField));
        getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

        Map<KeyCodeCombination, Runnable> accelerators = Map.of(
                new KeyCodeCombination(KeyCode.BACK_SLASH, SHORTCUT_DOWN), () -> label.setText("BACK_SLASH"),
                new KeyCodeCombination(KeyCode.CLOSE_BRACKET, SHORTCUT_DOWN), () -> label.setText("CLOSE_BRACKET"),
                new KeyCodeCombination(KeyCode.OPEN_BRACKET, SHORTCUT_DOWN), () -> label.setText("OPEN_BRACKET")
        );

        /* Option 1 */
        getDialogPane().getScene().addEventFilter(KeyEvent.KEY_RELEASED, event -> {
            for (var a : accelerators.entrySet()) {
                if (a.getKey().match(event)) {
                    a.getValue().run();
                    event.consume();
                }
            }
        });

        /* Option 2 */
//        getDialogPane().getScene().getAccelerators().putAll(accelerators);

        Platform.runLater(textField::requestFocus);
    }
}

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        new MainDialog().showAndWait();
    }

    public static void main(String[] args){
        launch(args);
    }
}
