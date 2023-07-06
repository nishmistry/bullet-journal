package cs3500.pa05.controller;

import cs3500.pa05.model.BulletJournal;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

/**
 * Utility class to initialize elements
 */
public class InitializeUtils {

  /**
   * changes the vbox colors
   *
   * @param verticalBox          the vbox to change colors in
   * @param bulletJournal the bullet journal to grab the theme from
   */
  public static void changeVboxColors(VBox verticalBox, BulletJournal bulletJournal) {
    String backgroundColor = bulletJournal.getTheme().getBackgroundColor() + ";";
    String accentColor = bulletJournal.getTheme().getAccentColor() + ";";
    verticalBox.setStyle(
        "-fx-background-color: "
            + backgroundColor
            + " -fx-background: "
            + accentColor
            + "-fx-border-color: "
            + accentColor);
  }

  /**
   * changes the anchor pane colors
   *
   * @param ap            the anchor pane to change colors in
   * @param bulletJournal the bullet journal to grab the theme from
   */
  public static void changeAnchorPaneColors(AnchorPane ap, BulletJournal bulletJournal) {
    String backgroundColor = bulletJournal.getTheme().getBackgroundColor() + ";";
    String accentColor = bulletJournal.getTheme().getAccentColor() + ";";
    ap.setStyle(
        "-fx-background-color: "
            + backgroundColor
            + " -fx-background: "
            + accentColor
            + "-fx-border-color: "
            + accentColor);
  }

  /**
   * changes the color of the labels
   *
   * @param labelList     the list of labels to change colors
   * @param bulletJournal the bullet journal to grab the theme from
   */
  public static void changeLabelColor(List<Label> labelList, BulletJournal bulletJournal) {
    Paint color = Paint.valueOf(bulletJournal.getTheme().getFontColor());
    String fontFamily = bulletJournal.getTheme().getFontFamily();
    for (Label label : labelList) {
      label.setTextFill(color);
      label.setFont(Font.font(fontFamily, label.getFont().getSize()));
    }
  }

  /**
   * initializes the list of images
   *
   * @param icons the paths of all the images to convert to images
   * @return the list of image
   */
  public static List<Image> initializeImageList(List<String> icons) {
    List<Image> images = new ArrayList<>();
    for (String path : icons) {
      Image image = new Image(new File(path).toURI().toString());
      images.add(image);
    }
    return images;
  }
}
