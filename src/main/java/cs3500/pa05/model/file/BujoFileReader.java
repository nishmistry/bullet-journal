package cs3500.pa05.model.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BulletJournal;
import cs3500.pa05.model.Event;
import cs3500.pa05.model.Task;
import cs3500.pa05.model.theme.BlueTheme;
import cs3500.pa05.model.theme.DarkTheme;
import cs3500.pa05.model.theme.LightTheme;
import cs3500.pa05.model.theme.SuperDarkTheme;
import java.io.IOException;


/**
 * Class to represent reading from a bujo file.
 */
public class BujoFileReader extends BujoFile {

  /**
   * Constructor to instantiate a bujo file reader.
   *
   * @param filePath the path to the file.
   */
  public BujoFileReader(String filePath) {
    super(filePath);
  }

  /**
   * represents reading the file
   *
   * @return the bullet journal
   */
  public BulletJournal readFile() {
    BulletJournal bulletJournal = null;
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper = registerSubClasses(mapper);
      bulletJournal = mapper.readValue(bujoFile, BulletJournal.class);
    }  catch (IOException io) {
      System.out.println("File not found: " + bujoFile.getName());
    }
    return bulletJournal;
  }

  /**
   * represents registering the subclasses
   *
   * @param mapper represents the object mapper
   *
   * @return the specific object mapper that has been registered
   */
  private ObjectMapper registerSubClasses(ObjectMapper mapper) {
    mapper.registerSubtypes(LightTheme.class);
    mapper.registerSubtypes(DarkTheme.class);
    mapper.registerSubtypes(BlueTheme.class);
    mapper.registerSubtypes(SuperDarkTheme.class);
    mapper.registerSubtypes(Task.class);
    mapper.registerSubtypes(Event.class);
    return mapper;
  }

  public String findPassword() {
    BulletJournal bulletJournal = readFile();
    return bulletJournal.getPassword();
  }
}
