package cs3500.pa05.model.theme;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.text.Font;

/**
 * Class to represent a light theme.
 */

@JsonTypeName("lightTheme")
public class LightTheme extends Theme {

  /**
   * represents the light theme constructor
   */
  public LightTheme() {

    super("lightTheme", "BLACK", "#FFFFFF", "#FFFFFF", "System",
        new ArrayList<>());
    icons.add("src/main/resources/lightTheme/save.png");
    icons.add("src/main/resources/lightTheme/settings.png");
    icons.add("src/main/resources/lightTheme/add.png");
    icons.add("src/main/resources/lightTheme/load.png");
  }
}
