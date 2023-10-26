package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;

/**
 * a Json representation of a Week
 *
 * @param name the name of the Week
 *
 * @param maxTasks the maximum number of Tasks allowed on a Week
 *
 * @param maxEvents the maximum number of Events allowed on a Week
 *
 * @param week the week content
 *
 * @param qAndNotes the quotes and notes content
 *
 * @param tasks the Tasks on a task queue
 *
 * @param theme the theme of a Week
 *
 * @param themeContents the contents of the Theme
 */

public record WeekJson(
    @JsonProperty("name-of-week") String name,
    @JsonProperty("max-tasks") int maxTasks,
    @JsonProperty("max-events") int maxEvents,
    @JsonProperty("week") DayJson[] week,
    @JsonProperty("quotes-and-notes") String qAndNotes,
    @JsonProperty("task-queue") TaskJson[] tasks,
    @JsonProperty("theme") String theme,
    @JsonProperty("theme-contents") String[] themeContents) {

}
