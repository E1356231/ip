package bob.gui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;

public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    public DialogBox(String s, Image i, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(s);
        displayPicture.setImage(i);
        if (isUser) {
            dialog.getStyleClass().add("user-label");
            this.setAlignment(Pos.CENTER_RIGHT);
        } else {
            dialog.getStyleClass().add("bot-label");
            flip();
        }
        Circle circle = new Circle(
                displayPicture.getFitHeight() / 2,
                displayPicture.getFitWidth() / 2,
                Math.min(displayPicture.getFitHeight(), displayPicture.getFitWidth()) / 2
        );
        displayPicture.setClip(circle);
    }
    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.CENTER_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i, true);
    }

    public static DialogBox getBobDialog(String s, Image i) {
        return new DialogBox(s, i, false);
    }
}

