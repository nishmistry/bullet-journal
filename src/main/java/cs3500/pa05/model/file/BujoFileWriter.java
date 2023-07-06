package cs3500.pa05.model.file;

import com.fasterxml.jackson.databind.ObjectMapper;
import cs3500.pa05.model.BulletJournal;

/**
 * represents writing the bujo file
 */
public class BujoFileWriter extends BujoFile {

  /**
   * represents the bujo file writer
   *
   * @param fileName represents the bujo file writer
   */
  public BujoFileWriter(String fileName) {
    super(fileName);
  }

  /**
   * represents writing to the bujo file
   *
   * @param bulletJournal represents the bullet journal
   */
  public void writeToFile(BulletJournal bulletJournal) {
    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.writeValue(bujoFile, bulletJournal);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
}
