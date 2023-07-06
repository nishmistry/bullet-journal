package cs3500.pa05.model.theme;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class to represent a dark theme.
 */

@JsonTypeName("darkTheme")
public class DarkTheme extends Theme {

  /**
   * represents the dark theme constructor
   */
  public DarkTheme() {
    super("darkTheme", "FLORALWHITE", "#1D1D1D", "#2a2a2a", "Georgia",
        new ArrayList<>());
    icons.add("src/main/resources/darkTheme/save.png");
    icons.add("src/main/resources/darkTheme/settings.png");
    icons.add("src/main/resources/darkTheme/add.png");
    icons.add("src/main/resources/darkTheme/load.png");
  }
}
