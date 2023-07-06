package cs3500.pa05.model.file;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test the bujo file reader
 *
 */
class BujoFileReaderTest {
  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  /**
   * testing the setup method
   */
  @BeforeEach
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  /**
   * testing the read file method
   */
  @Test
  void testReadFile() {
    BujoFileReader bujoFileReader = new BujoFileReader("src/test/resources/hello.bujo");
    bujoFileReader.readFile();
    assertEquals("File not found: hello.bujo", outputStreamCaptor.toString()
        .trim());
  }

  /**
   * testing the tear down method
   */
  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }
}