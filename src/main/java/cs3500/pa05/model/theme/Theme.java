package cs3500.pa05.model.theme;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.image.Image;

/**
 * Abstract class representing a theme.
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")

public abstract class Theme {
  @JsonProperty("type")
  private String type;
  protected final String fontColor;
  protected final String backgroundColor;
  protected final String accentColor;
  protected final String fontFamily;
  protected final List<String> icons;

  /**
   * Instantiates a theme with the given inputs.
   *
   * @param type represents the type of the event
   * @param fontColor fontColor color of the font.
   * @param backgroundColor backgroundColor color of the background.
   * @param fontFamily fontFamily font family.
   * @param icons icons list of icons to use.
   *
   */

  @JsonCreator
  public Theme(@JsonProperty("type") String type,
               @JsonProperty("fontColor") String fontColor,
               @JsonProperty("backgroundColor") String backgroundColor,
               @JsonProperty("accentColor") String accentColor,
               @JsonProperty("fontFamily") String fontFamily,
               @JsonProperty("icons") List<String> icons) {
    this.type = type;
    this.fontColor = fontColor;
    this.backgroundColor = backgroundColor;
    this.accentColor = accentColor;
    this.fontFamily = fontFamily;
    this.icons = icons;
  }

  /**
   * represents getting the font color
   *
   * @return the string font color
   */
  public String getFontColor() {
    return fontColor;
  }

  /**
   * represents getting the background color
   *
   * @return the string background color
   */
  public String getBackgroundColor() {
    return backgroundColor;
  }

  /**
   * represents the font family
   *
   * @return the string font
   */
  public String getFontFamily() {
    return fontFamily;
  }

  public String getAccentColor() {
    return accentColor;
  }

  /**
   * represents getting the icons
   *
   * @return list of strings for icons
   */
  public List<String> getIcons() {
    return icons;
  }

  /**
   * represents the to string method
   *
   * @return the String of type
   */
  @Override
  public String toString() {
    return type;
  }
}
