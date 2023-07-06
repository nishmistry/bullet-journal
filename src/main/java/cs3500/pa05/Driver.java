package cs3500.pa05;

import cs3500.pa05.controller.Controller;
import cs3500.pa05.controller.JournalFirstLoaderController;
import cs3500.pa05.view.JournalFirstLoaderView;
import cs3500.pa05.view.View;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * represents the driver class
 */
public class Driver extends Application {

  /**
   * represents the start method
   *
   *  @param stage represents the stage
   */
  @Override
  public void start(Stage stage) {
    Controller journal = new JournalFirstLoaderController(stage);
    View view = new JournalFirstLoaderView(journal, "JournalFirstLoader.fxml");
    stage.setScene(view.load());
    journal.run();
    stage.show();
    stage.centerOnScreen();
  }

  /**
   * represents entry point for class
   *
   * @param args represents arguments
   */
  public static void main(String[] args) {
    launch();
  }
}
