package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the Coord class
 */
class CoordTest {
  Coord coord;
  CoordJson json;

  /**
   * initializes a Coord and a CoordJson object used for testing
   */
  @BeforeEach
  void setup() {
    coord = new Coord(5, 4);
    json = new CoordJson(5, 4);
  }

  /**
   * tests that a Coord stores the proper int value that represents the row
   */
  @Test
  void getRow() {
    assertEquals(coord.getRow(), 5);
  }

  @Test
  void getCol() {
    assertEquals(coord.getCol(), 4);
  }

  @Test
  void coordToJson() {
    assertEquals(coord.coordToJson(), json);
  }
}