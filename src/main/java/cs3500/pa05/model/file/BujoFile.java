package cs3500.pa05.model.file;

import java.io.File;

/**
 * represents the bujo file
 */
public abstract class BujoFile {
  protected File bujoFile;

  /**
   * represents the bujo file
   *
   * @param fileName represents the file name
   */
  public BujoFile(String fileName) {
    bujoFile = new File(fileName);
  }

}
