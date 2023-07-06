package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import cs3500.pa05.model.file.BujoFileReader;
import cs3500.pa05.model.file.BujoFileWriter;
import cs3500.pa05.model.theme.LightTheme;
import cs3500.pa05.model.theme.Theme;
import java.io.File;

/**
 * Class to represent a Bullet Journal.
 */
public class BulletJournal {
  private String password;
  private Week week;
  private Theme theme;
  private BujoFileReader fileReader;
  private BujoFileWriter fileWriter;
  @JsonIgnore
  private String filePath;

  /**
   * The constructor to initialize a Bullet Journal.
   */
  public BulletJournal() {
    fileReader = null;
    fileWriter = null;
    this.week = new Week("Custom Week");
    Theme theme = new LightTheme();
    this.theme = theme;
  }

  /**
   * The constructor to initialize a Bullet Journal.
   *
   * @param filePath String of the filePath for the .bujo file
   */
  public BulletJournal(String filePath) {
    fileReader = new BujoFileReader(filePath);
    fileWriter = null;
    BulletJournal readFile = fileReader.readFile();
    this.week = readFile.week;
    this.theme = readFile.theme;
    this.filePath = filePath;
    this.password = readFile.getPassword();
  }

  /**
   * represents the bullet journal for json creator
   *
   * @param week represents week json
   * @param theme represents the theme json
   */
  @JsonCreator
  public BulletJournal(@JsonProperty("password") String password,
                       @JsonProperty("week") Week week,
                       @JsonProperty("theme") Theme theme) {
    this.password = password;
    this.week = week;
    this.theme = theme;
  }

  /**
   * Gets this Bullet Journal's theme.
   *
   * @return the theme of this Bullet Journal.
   */
  public Theme getTheme() {
    return theme;
  }

  /**
   * Gets this Bullet Journal's week.
   *
   * @return this Bullet Journal's week.
   */
  public Week getWeek() {
    return week;
  }

  /**
   * represents saving the file
   *
   * @param file represents saving the bujo file
   */
  public void saveFile(File file) {
    this.fileWriter = new BujoFileWriter(file.getPath());
    fileWriter.writeToFile(this);
  }

  /**
   * represents setting the theme
   *
   * @param theme represents the theme that is being set to
   */
  public void setTheme(Theme theme) {
    this.theme = theme;
  }

  /**
   * represents setting the week
   *
   * @param week represents the week
   */
  public void setWeek(Week week) {
    this.week = week;
  }

  public void setFilePath(String newPath) {
    this.filePath = newPath;
  }

  public String getFilePath() {
    return this.filePath;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
