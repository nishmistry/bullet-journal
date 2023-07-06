package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import cs3500.pa05.model.theme.BlueTheme;
import cs3500.pa05.model.theme.DarkTheme;
import cs3500.pa05.model.theme.SuperDarkTheme;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * testing the bullet journal
 */
class BulletJournalTest {
  private String type;
  private String fontColor;
  private String backgroundColor;
  private String accentColor;
  private String fontFamily;
  private List<String> icons;
  private String quote;
  private int tasks;

  /**
   * testing the setup
   */
  @BeforeEach
  public void setUp() {
    type = "type";
    fontColor = "Blue";
    backgroundColor = "LightBlue";
    accentColor = "Black";
    fontFamily = "Arial";
    icons = new ArrayList<>();
    icons.add("settings");
    icons.add("save");
    tasks = 5;
    quote = "We did it!";
  }

  /**
   * testing the constructor
   */
  @Test
  public void testConstructor() {
    BulletJournal bulletJournal = new BulletJournal();
    assertNotNull(bulletJournal);
    assertEquals("Custom Week", bulletJournal.getWeek().getName());
    assertEquals("BLACK", bulletJournal.getTheme().getFontColor());
  }

  /**
   * testing the constructor that takes in a bujo file
   */
  @Test
  public void testConstructorBujoFile() {
    BulletJournal bulletJournal = new BulletJournal("src/test/resources/sampleTest.bujo");
    assertNotNull(bulletJournal);
    assertEquals("Custom Week", bulletJournal.getWeek().getName());
    assertEquals("#FFFFFF", bulletJournal.getTheme().getBackgroundColor());
    assertEquals("BLACK", bulletJournal.getTheme().getFontColor());
    assertEquals("src/test/resources/sampleTest.bujo", bulletJournal.getFilePath());
    DarkTheme darkTheme = new DarkTheme();
    bulletJournal.setTheme(darkTheme);
    assertEquals("FLORALWHITE", bulletJournal.getTheme().getFontColor());
    SuperDarkTheme superDarkTheme = new SuperDarkTheme();
    bulletJournal.setTheme(superDarkTheme);
    assertEquals("WHITE", bulletJournal.getTheme().getFontColor());
    BlueTheme blueTheme = new BlueTheme();
    bulletJournal.setTheme(blueTheme);
    assertEquals("#86b7ff", bulletJournal.getTheme().getBackgroundColor());
    File file = new File("src/test/resources/test.bujo");
    bulletJournal.saveFile(file);
    assertTrue(file.exists());
    file.delete();
  }

  /**
   * testing the setters in a Bullet Journal
   */
  @Test
  public void testSetters() {
    BulletJournal bulletJournal = new BulletJournal();
    bulletJournal.setPassword("hello");
    assertEquals("hello", bulletJournal.getPassword());
    Week week = new Week("My week");
    bulletJournal.setWeek(week);
  }
}