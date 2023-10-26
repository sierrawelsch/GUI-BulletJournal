package cs3500.pa05.model;

/**
 * represents the location of an item
 */

public class Coord {
  public int row;
  public int col;

  public Coord(int row, int col) {
    this.row = row;
    this.col = col;
  }

  /**
   * retrieves the row value of a location
   *
   * @return the row value of a location
   */

  public int getRow() {
    return row;
  }

  /**
   * retrieves the column value of a location
   *
   * @return the column value of a location
   */

  public int getCol() {
    return col;
  }

  /**
   * converts the Coord object to a CoordJson object
   *
   * @return the CoordJson object that represents the Coord
   */

  public CoordJson coordToJson() {
    return new CoordJson(row, col);
  }
}
