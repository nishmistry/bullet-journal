package cs3500.pa05.model.theme;

import com.fasterxml.jackson.annotation.JsonTypeName;
import java.util.ArrayList;
import javafx.scene.text.Font;

/**
 * Class to represent a super dark theme.
 */
@JsonTypeName("superDarkTheme")
public class SuperDarkTheme extends Theme {

  /**
   * represents the super dark theme constructor
   */
  public SuperDarkTheme() {
    super("superDarkTheme", "WHITE", "#000000", "#0d0d0d", "Comic Sans MS",
        new ArrayList<>());
    icons.add("src/main/resources/superDarkTheme/save.png");
    icons.add("src/main/resources/superDarkTheme/settings.png");
    icons.add("src/main/resources/superDarkTheme/add.png");
    icons.add("src/main/resources/superDarkTheme/load.png");
  }
}
