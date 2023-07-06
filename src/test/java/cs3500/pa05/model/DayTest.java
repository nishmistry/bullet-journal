package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * represents the day test
 */
class DayTest {

  private String name;
  private String description;
  private String startDate;
  private String duration;
  private Boolean isComplete;
  private List<EventType> eventTypeList;

  /**
   * represents testing the day constructor
   */
  @BeforeEach
  public void setUp() {
    name = "Meeting";
    description = "meeting for the future";
    startDate = "12:23";
    duration = "12:45";
    isComplete = true;
    eventTypeList = new ArrayList<>();
  }

  /**
   * represents testing getting the name of the day
   */
  @Test
  void getName() {
    String expectedName = "Monday";
    Day day = new Day(expectedName, 5, 8);

    String actualName = day.getName();
    assertEquals(expectedName, actualName, "Should match expected name");
  }

  /**
   * represents testing getting the event type list
   */
  @Test
  void getEventTypeList() {
    List<EventType> expectedEventTypeList = new ArrayList<>();
    Event expectedEvent = new Event(name, description, startDate, duration);
    Task expectTask = new Task(name, description, isComplete);
    expectedEventTypeList.add(expectedEvent);
    expectedEventTypeList.add(expectTask);
    Day day = new Day("Monday", 5, 8);
    day.setEventTypeList(expectedEventTypeList);
    List<EventType> actualEventTypeList = day.getEventTypeList();
    Event actualEvent = null;
    Task actualTask = null;
    for (EventType actualEventType : actualEventTypeList) {
      if (actualEventType.getType().equals(TypeOfEvent.EVENT.toString())) {
        actualEvent = (Event) actualEventType;
      }
      if (actualEventType.getType().equals(TypeOfEvent.TASK.toString())) {
        actualTask = (Task) actualEventType;
      }
    }
    assertEquals(expectedEvent.getType(), actualEvent.getType());
    assertEquals(expectedEvent.getDuration(), actualEvent.getDuration());
    assertEquals(expectedEvent.getName(), actualEvent.getName());
    assertEquals(expectedEvent.getStartDate(), actualEvent.getStartDate());

    assertEquals(expectTask.getType(), actualTask.getType());
    assertEquals(expectTask.getDescription(), actualTask.getDescription());
    assertTrue(expectTask.isComplete());
    assertEquals(expectTask.getName(), actualTask.getName());
    expectTask.setComplete(false);
    assertFalse(expectTask.isComplete());
  }

  /**
   * represents testing the equals test
   */
  @Test
  void equalsTest() {
    Day day = new Day("Monday", 5, 8);
    assertTrue(day.equals(day));
    assertFalse(day.equals(null));
    assertFalse(day.equals(name));
    day.setName("Tuesday");
    assertEquals("Tuesday", day.getName());
  }

  /**
   * represents testing hashcode
   */
  @Test
  void hashCodeTest() {
    Day day = new Day("Monday", 5, 8);
    Day day1 = new Day("Monday", 5, 8);
    assertTrue(day.hashCode() == day1.hashCode());
  }

  /**
   * represents testing separating the event type
   */
  @Test
  void testSeparateEventType() {
    Day day = new Day("Tuesday", 5, 10);
    day.addEventType(new Event("Walking Dog", "walking my dawg",
        "12:34", "12:56"));
    day.addEventType(new Task("Walking Dog", "walking my dawg", false));
    int[] counts;
  }

  /**
   * represents removing and eventType from a list
   */
  @Test
  void testRemoveEventType() {
    Day day = new Day("Tuesday", 4, 5);
    Event event = new Event("Walking", "walking with mom", "11:23",
        "13:23");
    Task task = new Task("Dancing Raas", "RAS nationals with Nakhraas",
        true);
    Task task2 = new Task("Finishing Summer class", "done with ood",
        false);
    day.addEventType(event);
    day.addEventType(task2);
    day.addEventType(task);
    assertEquals(3, day.getEventTypeList().size());
    day.removeEventType(event);
    assertEquals(2, day.getEventTypeList().size());
  }
}