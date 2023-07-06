package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * represents the event class that extends the event type
 */
@JsonTypeName("EVENT")
public class Event extends EventType {
  private final String startDate;
  private final String duration;

  /**
   * Constructor for Event
   *
   * @param name String representing the name of the event
   * @param description String representing the description of the event
   * @param startDate String representing the timestamp of the start time of the event
   * @param duration String representing the duration timestamp of the event
   */
  @JsonCreator
  public Event(@JsonProperty("name") String name, @JsonProperty("description") String description,
               @JsonProperty("startDate") String startDate,
               @JsonProperty("duration") String duration) {
    super(TypeOfEvent.EVENT.toString(), name, description);
    this.startDate = startDate;
    this.duration = duration;
  }

  /**
   * sets the name of the event
   *
   * @param name represents the name of the event
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * represents getting the date
   *
   * @return the double start date
   */
  public String getStartDate() {
    return startDate;
  }

  /**
   * gets duration of event
   *
   * @return the duration time of event
   */
  public String getDuration() {
    return duration;
  }
}
