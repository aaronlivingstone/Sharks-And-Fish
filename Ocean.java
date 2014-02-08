/* Ocean.java */

/**
 *  The Ocean class defines an object that models an ocean full of sharks and
 *  fish.  Descriptions of the methods you must implement appear below.  They
 *  include a constructor of the form
 *
 *      public Ocean(int i, int j, int starveTime);
 *
 *  that creates an empty ocean having width i and height j, in which sharks
 *  starve after starveTime timesteps.
 *
 *  See the README file accompanying this project for additional details.
 */

public class Ocean {

  /**
   *  Do not rename these constants.  WARNING:  if you change the numbers, you
   *  will need to recompile Test4.java.  Failure to do so will give you a very
   *  hard-to-find bug.
   */

    public final static int EMPTY = 0;
    public final static int SHARK = 1;
    public final static int FISH = 2;

  /**
   *  Define any variables associated with an Ocean object here.  These
   *  variables MUST be private.
   */
    private int starveTime; // Mine
    private int width; // Mine
    private int height; // Mine
    private int[][][] oceanArray; // Mine

  /**
   *  The following methods are required for Part I.
   */

  /**
   *  Ocean() is a constructor that creates an empty ocean having width i and
   *  height j, in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public Ocean(int i, int j, int starveTime) {
    // Your solution here.
      assert i > 0 : "Wrong ocean size";
      assert j > 0 : "Wrong ocean size";
      this.starveTime = starveTime;
      width = i;
      height = j;  
      oceanArray = new int[i][j][2]; 
  }

  /**
   *  width() returns the width of an Ocean object.
   *  @return the width of the ocean.
   */

  public int width() {
      // Replace the following line with your solution.
      return width;
  }

  /**
   *  height() returns the height of an Ocean object.
   *  @return the height of the ocean.
   */

  public int height() {
    // Replace the following line with your solution.
      return height;
  }

  /**
   *  starveTime() returns the number of timesteps sharks survive without food.
   *  @return the number of timesteps sharks survive without food.
   */

  public int starveTime() {
    // Replace the following line with your solution.
      return starveTime;
  }

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */
  
  public void addFish(int x, int y) {
      // Your solution here.
      x = convertCord(x, 1);
      y = convertCord(y, 0);
      if (oceanArray[x][y][0] == EMPTY) {
          oceanArray[x][y][0] = FISH;
      }
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here.
      x = convertCord(x, 1);
      y = convertCord(y, 0);
      if (oceanArray[x][y][0] == EMPTY) {
          oceanArray[x][y][0] = SHARK;
          oceanArray[x][y][1] = 0;
      }
  }

  /**
   *  cellContents() returns EMPTY if cell (x, y) is empty, FISH if it contains
   *  a fish, and SHARK if it contains a shark.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int cellContents(int x, int y) {
    // Replace the following line with your solution.
      x = convertCord(x, 1);
      y = convertCord(y, 0);
      return oceanArray[x][y][0];
  }

  /**
   *  timeStep() performs a simulation timestep as described in README.
   *  @return an ocean representing the elapse of one timestep.
   */

  public Ocean timeStep() {
      // Replace the following line with your solution.
      // return new Ocean(1, 1, 1);
      int sharkCount = 0;
      int fishCount = 0;
      Ocean newOcean = new Ocean(width, height, starveTime);
      
      for (int i = 0; i < width; i++) {
          for (int j = 0; j < height; j++) {
              sharkCount = countNeighbors(i, j, 1);
              fishCount = countNeighbors(i, j, 0);
              if (oceanArray[i][j][0] == SHARK) {
                  if (fishCount > 0) {
                      newOcean.oceanArray[i][j][0] = SHARK;
                      newOcean.oceanArray[i][j][1] = 0;
                  } else if (oceanArray[i][j][1] < starveTime) {
                      newOcean.oceanArray[i][j][0] = SHARK;
                      newOcean.oceanArray[i][j][1] = (oceanArray[i][j][1] + 1);
                  } 
              } else if (oceanArray[i][j][0] == FISH) {
                  if (sharkCount == 0) {
                      newOcean.oceanArray[i][j][0] = FISH;
                  }
                  if (sharkCount > 1) {
                      newOcean.oceanArray[i][j][0] = SHARK;
                      newOcean.oceanArray[i][j][1] = 0;
                  }
              } else {
                  if (fishCount >= 2 && sharkCount <= 1) {
                      newOcean.oceanArray[i][j][0] = FISH;
                  } else if (fishCount >= 2 && sharkCount >= 2) {
                      newOcean.oceanArray[i][j][0] = SHARK;
                      newOcean.oceanArray[i][j][1] = 0;
                  }
              }
          }
      }
      return newOcean;
  }

 /**
   *  My function to count surrounding fish/sharks.
   */
    public int countNeighbors(int x, int y, int opt){ // Mine
        // option == 1 returns Shark count
        // option != 1 returns Fish count
        int fishCount = 0;
        int sharkCount = 0;
        int x2, y2;
        for (int i = (x - 1); i <= (x + 1); i++) {
            for (int j = (y - 1); j <= (y + 1); j++) {
                x2 = convertCord(i, 1);
                y2 = convertCord(j, 0);
                if (oceanArray[x2][y2][0] == FISH) {
                    fishCount++;
                } else if (oceanArray[x2][y2][0] == SHARK) {
                    sharkCount++;
                }
            }
        }
        if (oceanArray[x][y][0] == FISH){ // removing count for center square
            fishCount--;
        } else if (oceanArray[x][y][0] == SHARK) {
            sharkCount--;
        }
        if (opt == 1) {
            return sharkCount;
        }
        return fishCount;
    }

  /**
   *  my function to fix array coordinate values.
   */

    public int convertCord(int z, int opt) {
        // opt == 1 for x coordinate
        // opt != 1 for y coordinate
        if (opt == 1) {
            z = z % width;
            if (z < 0) {
                z = width + z;
            }
            return z;
        }
        z = z % height;
        if (z < 0) {
            z = height + z;
        }
        return z % height;
    }

  /**
   *  The following method is required for Part II.
   */

  /**
   *  addShark() (with three parameters) places a shark in cell (x, y) if the
   *  cell is empty.  The shark's hunger is represented by the third parameter.
   *  If the cell is already occupied, leave the cell as it is.  You will need
   *  this method to help convert run-length encodings to Oceans.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   *  @param feeding is an integer that indicates the shark's hunger.  You may
   *         encode it any way you want; for instance, "feeding" may be the
   *         last timestep the shark was fed, or the amount of time that has
   *         passed since the shark was last fed, or the amount of time left
   *         before the shark will starve.  It's up to you, but be consistent.
   */

  public void addShark(int x, int y, int feeding) {
    // Your solution here.
      x = convertCord(x, 1);
      y = convertCord(y, 0);
      if (oceanArray[x][y][0] == EMPTY) {
          oceanArray[x][y][0] = SHARK;
          oceanArray[x][y][1] = feeding;
      }
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  sharkFeeding() returns an integer that indicates the hunger of the shark
   *  in cell (x, y), using the same "feeding" representation as the parameter
   *  to addShark() described above.  If cell (x, y) does not contain a shark,
   *  then its return value is undefined--that is, anything you want.
   *  Normally, this method should not be called if cell (x, y) does not
   *  contain a shark.  You will need this method to help convert Oceans to
   *  run-length encodings.
   *  @param x is the x-coordinate of the cell whose contents are queried.
   *  @param y is the y-coordinate of the cell whose contents are queried.
   */

  public int sharkFeeding(int x, int y) {
    // Replace the following line with your solution.
      x = convertCord(x, 1);
      y = convertCord(y, 0);
      return oceanArray[x][y][1];
  }

}

 
