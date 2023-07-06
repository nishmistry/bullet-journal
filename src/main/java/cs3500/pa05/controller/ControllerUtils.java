package cs3500.pa05.controller;

import cs3500.pa05.model.file.BujoFileReader;
import cs3500.pa05.view.BulletJournalView;
import cs3500.pa05.view.PasswordView;
import cs3500.pa05.view.View;
import javafx.stage.Stage;

/**
 * represents the controller utility class
 */
public class ControllerUtils {
  /**
   * returns the main screen which is the week overview
   *
   * @param bulletJournalController the bullet journal controller to use
   * @param stage                   the stage to set the scene on
   */
  public static void goToMainScreen(Controller bulletJournalController, Stage stage) {
    View bulletJournalView = new BulletJournalView(bulletJournalController, "WeekOverview.fxml");
    stage.setScene(bulletJournalView.load());
    bulletJournalController.run();
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * represents handling the password
   *
   * @param path  the path to the file of the bujo file (empty string if it is a new file)
   * @param c     the controller to use
   * @param v     the view to use
   * @param stage the stage to set the scene on
   */
  public static void handlePassword(String path, Controller c, View v, Stage stage) {
    Controller passwordController;
    if (!path.isEmpty()) {
      BujoFileReader reader = new BujoFileReader(path);
      passwordController = new PasswordController(reader.findPassword(), c, v, stage);
    } else {
      passwordController = new PasswordController(path, stage);
    }
    View passwordView = new PasswordView(passwordController, "Password.fxml");
    stage.setScene(passwordView.load());
    passwordController.run();
    stage.centerOnScreen();
  }
}
