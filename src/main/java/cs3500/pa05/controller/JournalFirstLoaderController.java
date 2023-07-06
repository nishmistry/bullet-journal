package cs3500.pa05.controller;

import cs3500.pa05.view.BulletJournalView;
import cs3500.pa05.view.SplashScreenView;
import cs3500.pa05.view.View;
import java.io.File;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * represents loading the initial journal for a controller
 */
public class JournalFirstLoaderController implements Controller {
  @FXML
  private Button openDirectoryButton;
  @FXML
  private Label bujoInstructions;
  @FXML
  private Button newButton;
  private FileChooser fileChooser;

  private Stage stage;


  /**
   * controller to instantiate a journal first loader controller
   *
   * @param stage the stage to set the scene
   */
  public JournalFirstLoaderController(Stage stage) {
    this.stage = stage;
    fileChooser = new FileChooser();
  }

  /**
   * represents running the journal controller
   */
  @Override
  public void run() {
    FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("BUJO", "*.bujo");
    fileChooser.getExtensionFilters().add(filter);
    openDirectoryButton.setOnAction(actionEvent -> splashScreenInitializerDirectory());
    newButton.setOnAction(actionEvent -> splashScreenInitializerNew());
  }

  /**
   * represents handling the directory
   *
   * @param file the file of the .bujo file chosen
   */
  private void handleDirectory(File file) {
    Controller bulletJournalController = new BulletJournalController(
        file.getPath(), stage);
    View bulletJournalView = new BulletJournalView(
        bulletJournalController, "WeekOverview.fxml");
    ControllerUtils.handlePassword(file.getPath(), bulletJournalController, bulletJournalView,
        stage);
  }

  /**
   * initializes the splash screen when a new file is created
   */
  private void splashScreenInitializerNew() {
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2));
    View splash = new SplashScreenView(null, "SplashScreen.fxml");
    Scene splashScene = splash.load();
    stage.setScene(splashScene);
    stage.centerOnScreen();
    splashScreenInitializer(fadeTransition, splashScene);
    fadeTransition.setOnFinished(e -> handleNew());
    fadeTransition.play();
  }

  /**
   * initializes the actual splash screen
   *
   * @param fadeTransition the fade transition to use
   * @param splashScene the splash scene to add to the fade transition
   */
  private void splashScreenInitializer(FadeTransition fadeTransition, Scene splashScene) {
    fadeTransition.setNode(splashScene.getRoot());
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
  }

  /**
   * initializes the splash screen when a file is chosen
   */
  private void splashScreenInitializerDirectory() {
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2));
    View splash = new SplashScreenView(null, "SplashScreen.fxml");
    Scene splashScene = splash.load();
    stage.setScene(splashScene);
    stage.centerOnScreen();
    splashScreenInitializer(fadeTransition, splashScene);
    File selected = fileChooser.showOpenDialog(stage);
    fadeTransition.setOnFinished(e -> handleDirectory(selected));
    fadeTransition.play();
  }

  /**
   * represents prompting the user to create a password if a new file is created
   */
  private void handleNew() {
    ControllerUtils.handlePassword("", null, null, stage);
  }
}
