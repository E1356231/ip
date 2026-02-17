package bob.gui;

import bob.Bob;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Bob bob;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bob.png"));

    /**
     * Initializes the GUI after the FXML components are loaded.
     * This method is automatically called by the JavaFX framework after
     * the FXML file has been loaded. It performs the following tasks:
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getBobDialog(getGreeting(), bobImage)
        );

    }

    private String getGreeting() {
        return "Hello! I'm Bob.\nWhat's on your agenda for today?";
    }

    /** Injects the Bob instance */
    public void setBob(Bob b) {
        bob = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bob.getResponse(input);
        boolean isExit = false;
        if (response.endsWith("EXIT_SIGNAL")) {
            isExit = true;
            response = response.replace("EXIT_SIGNAL", ""); // remove before displaying
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBobDialog(response, bobImage)
        );
        userInput.clear();
        if (isExit) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(e -> Platform.exit());
            delay.play();
        }
    }
}

