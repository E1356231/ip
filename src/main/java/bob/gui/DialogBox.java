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
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;


/**
 * Represents a dialog box used in the chatbot UI.
 * A DialogBox typically contains a message and an avatar image,
 * and can be styled differently depending on whether it is from the user or the bot.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * @param s string, bot's response, user's input
     * @param i image of user/bot
     * @param isUser checks if it's user or chatbot
     */
    public DialogBox(String s, Image i, boolean isUser) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!isUser && s.startsWith("ERROR! ")) {
            Text errorText = new Text(s.substring(0, 6));
            errorText.setFont(Font.font("System", FontWeight.BOLD, 12));
            errorText.setFill(Color.RED);
            Text remainingText = new Text(s.substring(6));
            remainingText.setFont(Font.font("System", FontWeight.NORMAL, 12));
            remainingText.setFill(Color.BLACK);

            TextFlow text = new TextFlow(errorText, remainingText);
            dialog.setText("");
            dialog.setGraphic(text);
        } else {
            dialog.setText(s);
            if (isUser) {
                dialog.getStyleClass().add("user-label");
            } else {
                dialog.getStyleClass().add("bot-label");
            }
        }

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

