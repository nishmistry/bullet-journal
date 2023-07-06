package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * represents the week test
 */
class WeekTest {

  private Day day;
  private List<Day> days;
  private String type;
  private Week week;
  private String name;
  @Mock
  private Day dayMock;

  /**
   * represents testing setting up the week constructor
   */
  @BeforeEach
  public void setUp() {
    name = "My Week";
    type = "type";
    week = new Week(name);

  }

  /**
   * represents testing getting the week
   */
  @Test
  void getWeekTest() {
    assertEquals(this.name, week.getName());
    String newName = "new week";
    week.setName(newName);
    assertEquals(newName, week.getName());
    ArrayList<Day> expectedDaysList = new ArrayList<>();
    expectedDaysList.add(new Day("Monday", 5, 8));
    expectedDaysList.add(new Day("Tuesday", 5, 8));
    expectedDaysList.add(new Day("Wednesday", 5, 8));
    expectedDaysList.add(new Day("Thursday", 5, 8));
    expectedDaysList.add(new Day("Friday", 5, 8));
    expectedDaysList.add(new Day("Saturday", 5, 8));
    expectedDaysList.add(new Day("Sunday", 5, 8));
    assertTrue(expectedDaysList.size() == week.getDaysList().size());
    assertTrue(expectedDaysList.containsAll(week.getDaysList()));
    assertTrue(week.getDaysList().containsAll(expectedDaysList));
    String newQuote = "new quote";
    week.setQuote(newQuote);
    assertEquals(newQuote, week.getQuote());
  }

  /**
   * represents testing adding event type
   */
  @Test
  void testAddEventType() {
    Event event = new Event("Walking Romeo",
        "action of walking my cool dog", "12:89", "14.32");
    week.addEventType("Monday", event);
    week.getDaysList();
    List<EventType> mondayList = null;
    for (Day day : week.getDaysList()) {
      if (day.getName().equals("Monday")) {
        mondayList = day.getEventTypeList();
      }
    }
    assertTrue(mondayList.contains(event));
  }

  /**
   * represents testing checking add Event type
   */
  @Test
  void checkAddEventTypeTest() {
    Week week = new Week("Sample Week");
    List<Day> dayList = week.getDaysList();
    Day selectedDay = dayList.get(0);
    EventType eventType = new Event("Event", "small description",
        "12:12", "12:34");
    boolean result = week.checkAddEventType(selectedDay.getName(), eventType);
    if (result) {
      System.out.println("Event type added successfully to " + selectedDay.getName());
    } else {
      System.out.println("Failed to add event type to " + selectedDay.getName());
    }
    assertEquals(true, week.checkAddEventType(selectedDay.getName(), eventType));
    EventType task = new Task("Task", "small task description", true);
    result = week.checkAddEventType(selectedDay.getName(), task);
    assertTrue(result);
    task = new Task("Task", "small task description", false);
    result = week.checkAddEventType(selectedDay.getName(), task);
    assertTrue(result);
    Week week1 = new Week("My week");
    week1.setMaxTasks(1);
    week1.setMaxEvents(1);
    week1.addEventType("Tuesday", new Task("Workout",
        "my tuesday", false));
    week1.addEventType("Friday", new Task("Swimming",
        "my day", false));
    week1.addEventType("Tuesday", new Event("Diving", "How to dive",
        "12:34", "12:54"));
    week1.addEventType("Thursday", new Event("Studying", "for test",
        "15:23", "16:23"));
    assertFalse(week1.checkAddEventType("Tuesday", task));
    assertFalse(week1.checkAddEventType("Tuesday", eventType));
  }

  /**
   * represents testing getting the current max events
   */
  @Test
  void testGetCurrMaxEvent() {
    Week week = new Week("Test week");
    week.addEventType("Monday", new Event("Swimming", "Swimming "
        + "with michael phelps", "12:11", "12:15"));
    week.addEventType("Tuesday", new Event("Diving", "diving "
        + "with michael phelps", "12:31", "12:35"));
    int maxEventCount = week.getCurrMaxEventCount();
    assertEquals(1, maxEventCount);
  }

  /**
   * represents testing getting the current max tasks
   */
  @Test
  void testGetCurrMaxTask() {
    Week week = new Week("Test another week");
    Task task = new Task("Finish PA05", "almost done,"
        + "just need to finish testing!", false);
    week.addEventType("Wednesday", task);
    week.addEventType("Thursday", new Task("Eat IceCream", "to celebrate"
        + "almost being done!", true));
    int maxTaskCount = week.getCurrMaxTaskCount();
    assertEquals(1, maxTaskCount);
    task.setComplete(true);
    week.addEventType("Wednesday", task);
    int maxTaskCount2 = week.getCurrMaxTaskCount();
    assertEquals(2, maxTaskCount2);
  }

  /**
   * represents testing the day of Event type
   */
  @Test
  void dayOfEventTypeTest() {
    Week week = new Week("My week");
    Event event = new Event("Swim", "swimming", "12:34", "13:23");
    Event event1 = new Event("JumpRope", "jumproping",
        "13:23", "12:34");
    week.addEventType("Tuesday", event);
    week.addEventType("Friday", event1);
    assertEquals("Tuesday", week.dayOfEventType(event));
    assertNotEquals("Monday", week.dayOfEventType(event1));
  }
}