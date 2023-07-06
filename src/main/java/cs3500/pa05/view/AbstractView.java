package cs3500.pa05.view;

import cs3500.pa05.controller.Controller;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

/**
 * represents the abstract view
 */
public abstract class AbstractView implements View {
  protected final FXMLLoader loader;
  protected final String fxmlFile;

  /**
   * represents the abstract view
   *
   * @param c represents the controller
   * @param fxmlFile represents the fxml file
   */
  public AbstractView(Controller c, String fxmlFile) {
    this.loader = new FXMLLoader();
    this.fxmlFile = fxmlFile;
    this.loader.setLocation(getClass().getClassLoader().getResource(this.fxmlFile));
    this.loader.setController(c);
  }

  /**
   * Loads a scene from initial screen.
   *
   * @return the layout
   */
  @Override
  public Scene load() {
    try {
      return this.loader.load();
    } catch (IOException exc) {
      throw new IllegalStateException("Unable to load " + this.fxmlFile + " layout.");
    }
  }
}
