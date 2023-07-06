package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

/**
 * represents the Task class for a json creator
 */
@JsonTypeName("TASK")
public class Task extends EventType {
  private boolean complete;

  @JsonCreator
  public Task(@JsonProperty("name") String name,
              @JsonProperty("description") String description,
              @JsonProperty("complete") boolean complete) {
    super(TypeOfEvent.TASK.toString(), name, description);
    this.complete = complete;
  }

  /**
   * determines if complete
   *
   * @return whether something is complete
   */
  public boolean isComplete() {
    return complete;
  }

  public void setComplete(boolean completeStatus) {
    this.complete = completeStatus;
  }
}
