package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.control.Hyperlink;


/**
 * represents eventType class
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public abstract class EventType {
  @JsonProperty("type")
  protected String type;

  protected String name;

  protected String description;

  /**
   * represents the event type json creator
   *
   * @param type represents the type of event
   * @param name represents the name of the event type
   * @param description represents the description
   */
  @JsonCreator
  public EventType(@JsonProperty("type") String type,
                   @JsonProperty("name") String name,
                   @JsonProperty("description") String description) {
    this.type = type;
    this.name = name;
    this.description = description;
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
   * gets the name of the string
   *
   * @return the string of the name
   */
  public String getName() {
    return name;
  }

  /**
   * sets the description for the event type
   *
   * @param description represents the description for the event
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * gets the description of the event
   *
   * @return the string of the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * represents the type of event type
   *
   * @return the String of event type
   */
  public String getType() {
    return this.type;
  }

  /**
   * Parses through a description for an EventType and extracts all weblinks
   *
   * @return List of Hyperlink objects representing the extracted links
   */
  public List<Hyperlink> descriptionWithLink() {
    List<Hyperlink> links = new ArrayList<>();
    Pattern pattern = Pattern.compile("(www\\.\\S+|https?://\\S+)");
    Matcher matcher = pattern.matcher(description);

    while (matcher.find()) {
      Hyperlink link = new Hyperlink();
      if (matcher.group().contains("http")) {
        link.setText(matcher.group());
      } else {
        link.setText("https://" + matcher.group());
      }
      links.add(link);
      System.out.println(matcher.group());
    }
    return links;
  }
}

