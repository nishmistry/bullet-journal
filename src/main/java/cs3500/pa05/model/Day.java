package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class to represent a day in the Bullet Journal.
 */
public class Day {
  private String name;
  private List<EventType> eventTypeList;
  private int maxTasks;
  private int maxEvents;

  /**
   * Instantiates a day with the given name.
   *
   * @param name name of the day.
   */
  public Day(String name, int maxTasks, int maxEvents) {
    this.name = name;
    this.eventTypeList = new ArrayList<>();
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
  }

  /**
   * represents the json creator of Day
   *
   * @param name represents the name of the day
   * @param eventTypeList represents the event list
   */
  @JsonCreator
  public Day(@JsonProperty("name") String name,
             @JsonProperty("eventTypeList") List<EventType> eventTypeList,
             @JsonProperty("maxTasks") int maxTasks,
             @JsonProperty("maxEvents") int maxEvents) {
    this.name = name;
    this.eventTypeList = eventTypeList;
    this.maxTasks = maxTasks;
    this.maxEvents = maxEvents;
  }

  /**
   * represents getting the name
   *
   * @return the string of getting the name
   */
  public String getName() {
    return name;
  }

  /**
   * represents setting the name
   *
   * @param name represents the day name
   *
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * represents getting the event type list
   *
   * @return the list of event types
   */
  public List<EventType> getEventTypeList() {
    return eventTypeList;
  }


  /**
   * represents setting the event type list
   *
   * @param eventTypeList represents the list of event type
   */
  public void setEventTypeList(List<EventType> eventTypeList) {
    this.eventTypeList = eventTypeList;
  }

  /**
   * represents adding the event type to the list
   *
   * @param e represents event type
   */
  public void addEventType(EventType e) {
    eventTypeList.add(e);
  }

  /**
   * represents setting the max tasks
   *
   * @param newMax represents adding a new ma
   */
  public void setMaxTasks(int newMax) {
    this.maxTasks = newMax;
  }

  /**
   * for testing
   *
   * @param o object o
   *
   * @return the boolean if it is equal
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Day day = (Day) o;
    return Objects.equals(name, day.name);
  }

  /**
   * represents the hashcode
   *
   * @return int representing the hashcode of the object
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }

  public void setMaxEvents(int newMax) {
    this.maxEvents = newMax;
  }

  /**
   * Checks to see if an EventType can be added
   *
   * @param eventType the EventType to check
   * @return boolean representing if the EventType can be added
   */
  public boolean checkAddEventType(EventType eventType) {
    int[] taskEventCount = seperateEventType();
    if (eventType.getType().equals(TypeOfEvent.TASK.toString())) {
      return maxTasks > taskEventCount[0];
    } else {
      return maxEvents > taskEventCount[1];
    }
  }

  /**
   * Separates the eventType list into an array with counts for tasks and events
   *
   * @return An array of integers, index 0 representing the count of tasks and index 1
   *         representing the count of events.
   */
  public int[] seperateEventType() {
    int eventCount = 0;
    int taskCount = 0;
    for (EventType eventType : eventTypeList) {
      if (eventType.getType().equals(TypeOfEvent.EVENT.toString())) {
        eventCount++;
      } else {
        taskCount++;
      }
    }
    return new int[]{taskCount, eventCount};
  }

  @JsonIgnore
  public int getTaskCount() {
    int[] array = seperateEventType();
    return array[0];
  }

  @JsonIgnore
  public int getEventCount() {
    int[] array = seperateEventType();
    return array[1];
  }

  public void removeEventType(EventType eventType) {
    eventTypeList.remove(eventType);
  }
}
