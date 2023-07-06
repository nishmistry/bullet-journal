package cs3500.pa05.controller;

import cs3500.pa05.view.BulletJournalView;
import cs3500.pa05.view.View;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

/**
 * represents the password controller
 */
public class PasswordController implements Controller {
  @FXML
  private PasswordField passwordField;
  @FXML
  private Button submit;
  @FXML
  private Label prompt;
  private String password;
  private Controller bulletJournalController;
  private View bulletJournalView;
  private Stage stage;

  /**
   * constructor to instantiate a password controller if a file is selected
   *
   * @param password the password from the selected bullet journal
   * @param bjc      the bullet journal controller
   * @param bjv      the bullet jounral view
   * @param s        the stage to set the scene
   */
  public PasswordController(String password, Controller bjc, View bjv, Stage s) {
    this.password = password;
    bulletJournalController = bjc;
    bulletJournalView = bjv;
    stage = s;
  }

  /**
   * constructor to instantiate a password controller if a new file is made
   *
   * @param password empty password since the user is making a new file
   * @param s        the stage to set the scene
   */
  public PasswordController(String password, Stage s) {
    this.password = password;
    stage = s;
  }

  /**
   * the run method to run a password controller
   */
  @Override
  public void run() {
    if (password.isEmpty()) {
      promptPassword();
    } else {
      submit.setOnMouseClicked(e -> checkPassword());
    }
  }

  /**
   * prompts the user to enter a password
   */
  private void promptPassword() {
    prompt.setText("Please enter a new password.");
    prompt.setPrefWidth(371);
    prompt.setAlignment(javafx.geometry.Pos.CENTER);
    passwordField.setPromptText("Please enter a new password.");
    submit.setOnMouseClicked(e -> loadScreen());
  }

  /**
   * checks if the inputted password matches the actual password
   */
  private void checkPassword() {
    if (passwordField.getText().equals(password)) {
      stage.setScene(bulletJournalView.load());
      bulletJournalController.run();
      stage.centerOnScreen();
    } else {
      prompt.setText("Password is incorrect.");
    }
  }

  /**
   * loads the bullet journal screen
   */
  private void loadScreen() {
    if (!passwordField.getText().isEmpty()) {
      Controller bulletJournalController =
          new BulletJournalController(stage, passwordField.getText());
      View bulletJournalView = new BulletJournalView(bulletJournalController, "WeekOverview.fxml");
      stage.setScene(bulletJournalView.load());
      bulletJournalController.run();
      stage.centerOnScreen();
    }
  }
}
