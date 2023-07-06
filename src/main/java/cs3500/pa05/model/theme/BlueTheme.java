package cs3500.pa05.model.theme;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Class to represent a blue theme.
 */

@JsonTypeName("blueTheme")
public class BlueTheme extends Theme {

  /**
   * represents the blue theme constructor
   */
  public BlueTheme() {
    super("blueTheme", "NAVAJOWHITE", "#86b7ff", "#99c2ff", "Helvetica",
        new ArrayList<>());
    icons.add("src/main/resources/blueTheme/save.png");
    icons.add("src/main/resources/blueTheme/settings.png");
    icons.add("src/main/resources/blueTheme/add.png");
    icons.add("src/main/resources/blueTheme/load.png");
  }
}
