package cs3500.pa05.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Represents tests for the CoordJson class
 */
class CoordJsonTest {
  CoordJson json;

  /**
   * initializes a CoordJson object used for testing
   */
  @BeforeEach
  void setup() {
    json = new CoordJson(1, 2);
  }

  /**
   * tests that a CoordJson stores the correct row and col int values
   */
  @Test
  void jsonToCoord() {
    assertEquals(json.jsonToCoord().getRow(), 1);
    assertEquals(json.jsonToCoord().getCol(), 2);
  }
}