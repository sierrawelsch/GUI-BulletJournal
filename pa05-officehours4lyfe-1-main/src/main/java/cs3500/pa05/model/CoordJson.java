package cs3500.pa05.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * the Json representation of a Coord object
 *
 * @param row row value
 *
 * @param col column value
 */

public record CoordJson(
    @JsonProperty("row") int row,
    @JsonProperty("col") int col) {

  /**
   * converts the CoordJson to a Coord object
   *
   * @return a Coord object
   */

  public Coord jsonToCoord() {
    return new Coord(row, col);
  }
}
