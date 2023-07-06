package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * represents the week class
 */
public class Week {

  private String name;
  private String quote;
  private List<Day> daysList;
  private int maxTasks;
  private int maxEvents;

  /**
   * represents the week constructor
   *
   * @param name represents the name of the week
   */
  public Week(String name) {
    this.name = name;
    this.daysList = new ArrayList<>(7);
    this.maxTasks = 5;
    this.maxEvents = 5;
    initializeDays(maxTasks, maxEvents);
    this.quote = "";
  }

  /**
   * represents the week constructor
   *
   * @param name the json creator name
   * @param daysList the json creator for days list
   * @param quote the json creator for quote
   */
  @JsonCreator
  public Week(@JsonProperty("name") String name,
              @JsonProperty("daysList") List<Day> daysList,
              @JsonProperty("quote") String quote,
              @JsonProperty("maxTasks") int maxTasks,
              @JsonProperty("maxEvents") int maxEvents) {
    this.name = name;
    this.daysList = daysList;
    this.quote = quote;
    setMaxTasks(maxTasks);
    setMaxEvents(maxEvents);
  }

  /**
   * Gets the name
   *
   * @return the name of the week
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name
   *
   * @param name represents setting the name of the week
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the days list
   *
   * @return the days list
   */
  public List<Day> getDaysList() {
    return daysList;
  }

  /**
   * Sets the quote of the week
   *
   * @param newQuote represents the new quote
   */
  public void setQuote(String newQuote) {
    this.quote = newQuote;
  }

  /**
   * Gets the quote of the week
   *
   * @return the quote of the week
   */
  public String getQuote() {
    return this.quote;
  }


  /**
   * Initializes the days of the week
   */
  private void initializeDays(int maxTasks, int maxEvents) {
    daysList.add(new Day("Monday", maxTasks, maxEvents));
    daysList.add(new Day("Tuesday", maxTasks, maxEvents));
    daysList.add(new Day("Wednesday", maxTasks, maxEvents));
    daysList.add(new Day("Thursday", maxTasks, maxEvents));
    daysList.add(new Day("Friday", maxTasks, maxEvents));
    daysList.add(new Day("Saturday", maxTasks, maxEvents));
    daysList.add(new Day("Sunday", maxTasks, maxEvents));
  }

  /**
   * Gets the Max Tasks for the week
   *
   * @return int representing the max tasks
   */
  public int getMaxTasks() {
    return this.maxTasks;
  }

  /**
   * Sets the Max Tasks for the week
   *
   * @param newMax represents the new max
   */
  public void setMaxTasks(int newMax) {
    this.maxTasks = newMax;
    for (Day day : this.daysList) {
      day.setMaxTasks(newMax);
    }
  }

  /**
   * Checks to see if an EventType can be added to a certain day
   *
   * @param day String representing the day
   * @param eventType EventType representing the event/task to try and add
   * @return boolean representing if it is possible to add or not
   */
  public boolean checkAddEventType(String day, EventType eventType) {
    boolean result = false;
    for (Day currDay : daysList) {
      if (currDay.getName().equals(day)) {
        result = currDay.checkAddEventType(eventType);
      }
    }
    return result;
  }

  /**
   * Adds the eventType to the desired day
   *
   * @param day String representing the day of the week
   * @param eventType EventType representing the event to be added
   */
  public void addEventType(String day, EventType eventType) {
    for (Day currDay : daysList) {
      if (currDay.getName().equals(day)) {
        currDay.addEventType(eventType);
      }
    }
  }

  /**
   * Sets the new Maximum Events for a day in the Week and Day objects
   *
   * @param newMax int representing the new desired max
   */
  public void setMaxEvents(int newMax) {
    this.maxEvents = newMax;
    for (Day day : this.daysList) {
      day.setMaxEvents(newMax);
    }
  }

  /**
   * Gets the int representing the limit of max events for a week
   *
   * @return int representing the limit for events
   */
  public int getMaxEvents() {
    return this.maxEvents;
  }

  /**
   * Finds the count of the "busiest" day (number of tasks) in a week
   *
   * @return int representing the number of tasks on the busiest day in the week
   */
  @JsonIgnore
  public int getCurrMaxTaskCount() {
    ArrayList<Integer> maxTask = new ArrayList<>();
    for (Day day : this.daysList) {
      maxTask.add(day.getTaskCount());
    }
    Collections.sort(maxTask);
    return maxTask.get(6);
  }

  /**
   * Finds the count of the "busiest" day (number of events) in a week
   *
   * @return int representing the number of events on the busiest day in the week
   */
  @JsonIgnore
  public int getCurrMaxEventCount() {
    ArrayList<Integer> maxEvent = new ArrayList<>();
    for (Day day : this.daysList) {
      maxEvent.add(day.getEventCount());
    }
    Collections.sort(maxEvent);
    return maxEvent.get(6);
  }

  /**
   * Finds the day an EventType is associated with
   *
   * @param eventType the EventType in question
   * @return String representing the day that the EventType is associated with
   */
  public String dayOfEventType(EventType eventType) {
    String result = null;
    for (Day day : this.daysList) {
      if (day.getEventTypeList().contains(eventType)) {
        result = day.getName();
      }
    }
    return result;
  }
}
