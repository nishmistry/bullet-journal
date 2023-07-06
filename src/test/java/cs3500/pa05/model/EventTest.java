package cs3500.pa05.model;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents testing Event Type
 */
class EventTest {

  private String name;
  private String description;
  private String startDate;
  private String duration;

  /**
   * represents setting up event type
   */
  @BeforeEach
  public void setUp() {
    name = "Meeting";
    description = "Team meeting";
    startDate = "12:13";
    duration = "14:50";
  }

  /**
   * represents getting the description of the event type
   */
  @Test
  void getDescriptionTest() {
    Event event = new Event(name, description, startDate, duration);
    Assertions.assertEquals(this.description, event.getDescription());
    event.setName("new name");
    Assertions.assertEquals("new name", event.getName());
  }
}