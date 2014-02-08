/* RunLengthEncoding.java */

/**
 *  The RunLengthEncoding class defines an object that run-length encodes an
 *  Ocean object.  Descriptions of the methods you must implement appear below.
 *  They include constructors of the form
 *
 *      public RunLengthEncoding(int i, int j, int starveTime);
 *      public RunLengthEncoding(int i, int j, int starveTime,
 *                               int[] runTypes, int[] runLengths) {
 *      public RunLengthEncoding(Ocean ocean) {
 *
 *  that create a run-length encoding of an Ocean having width i and height j,
 *  in which sharks starve after starveTime timesteps.
 *
 *  The first constructor creates a run-length encoding of an Ocean in which
 *  every cell is empty.  The second constructor creates a run-length encoding
 *  for which the runs are provided as parameters.  The third constructor
 *  converts an Ocean object into a run-length encoding of that object.
 *
 *  See the README file accompanying this project for additional details.
 */

public class RunLengthEncoding {

  /**
   *  Define any variables associated with a RunLengthEncoding object here.
   *  These variables MUST be private.
   */
    private DList dOcean;
    private int starveTime;
    private int width;
    private int height;
    private int[] runLengths;
    private int[] runTypes;
    private int readPosition = 1;
    
  /**
   *  The following methods are required for Part II.
   */

  /**
   *  RunLengthEncoding() (with three parameters) is a constructor that creates
   *  a run-length encoding of an empty ocean having width i and height j,
   *  in which sharks starve after starveTime timesteps.
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   */

  public RunLengthEncoding(int i, int j, int starveTime) {
      // Your solution here
      width = i;
      height = j;
      this.starveTime = starveTime;
      dOcean = new DList();
      dOcean.insertFront(0, 0, (i * j));
  }

  /**
   *  RunLengthEncoding() (with five parameters) is a constructor that creates
   *  a run-length encoding of an ocean having width i and height j, in which
   *  sharks starve after starveTime timesteps.  The runs of the run-length
   *  encoding are taken from two input arrays.  Run i has length runLengths[i]
   *  and species runTypes[i].
   *  @param i is the width of the ocean.
   *  @param j is the height of the ocean.
   *  @param starveTime is the number of timesteps sharks survive without food.
   *  @param runTypes is an array that represents the species represented by
   *         each run.  Each element of runTypes is Ocean.EMPTY, Ocean.FISH,
   *         or Ocean.SHARK.  Any run of sharks is treated as a run of newborn
   *         sharks (which are equivalent to sharks that have just eaten).
   *  @param runLengths is an array that represents the length of each run.
   *         The sum of all elements of the runLengths array should be i * j.
   */

  public RunLengthEncoding(int i, int j, int starveTime,
                           int[] runTypes, int[] runLengths) {
    // Your solution here.
      assert runTypes.length == runLengths.length : "Bad input";
      
      int total = 0;
      width = i;
      height = j;
      this.starveTime = starveTime;

      this.runTypes = new int[runTypes.length];
      this.runLengths = new int[runLengths.length];
      
      System.arraycopy(runTypes, 0, this.runTypes, 0, runTypes.length);
      System.arraycopy(runLengths, 0, this.runLengths, 0, runLengths.length);

      for (int x : runLengths) {
          total += x;
      }
      assert total == (width * height) : "Bad runLengths";

      dOcean = new DList();
      for (int k = 0; k < runTypes.length; k++) {
          dOcean.insertBack(runTypes[k], 0, runLengths[k]);
      } 
  }

  /**
   *  restartRuns() and nextRun() are two methods that work together to return
   *  all the runs in the run-length encoding, one by one.  Each time
   *  nextRun() is invoked, it returns a different run (represented as an
   *  array of two ints), until every run has been returned.  The first time
   *  nextRun() is invoked, it returns the first run in the encoding, which
   *  contains cell (0, 0).  After every run has been returned, nextRun()
   *  returns null, which lets the calling program know that there are no more
   *  runs in the encoding.
   *
   *  The restartRuns() method resets the enumeration, so that nextRun() will
   *  once again enumerate all the runs as if nextRun() were being invoked for
   *  the first time.
   *
   *  (Note:  Don't worry about what might happen if nextRun() is interleaved
   *  with addFish() or addShark(); it won't happen.)
   */

  /**
   *  restartRuns() resets the enumeration as described above, so that
   *  nextRun() will enumerate all the runs from the beginning.
   */

  public void restartRuns() {
    // Your solution here
      readPosition = 1;
  }

  /**
   *  nextRun() returns the next run in the enumeration, as described above.
   *  If the runs have been exhausted, it returns null.  The return value is
   *  an array of two ints (constructed here), representing the type and the
   *  size of the run, in that order.
   *  @return the next run in the enumeration, represented by an array of
   *          two ints.  The int at index zero indicates the run type
   *          (Ocean.EMPTY, Ocean.SHARK, or Ocean.FISH).  The int at index one
   *          indicates the run length (which must be at least 1).
   */

  public int[] nextRun() {
    // Replace the following line with your solution.
      if (readPosition > dOcean.size) {
          return null;
      }
      
      int[] retArray = new int[2];
      retArray[0] = dOcean.getItem(readPosition);
      retArray[1] = dOcean.getNumElements(readPosition);
      readPosition++;
      return retArray;
  }

  /**
   *  toOcean() converts a run-length encoding of an ocean into an Ocean
   *  object.  You will need to implement the three-parameter addShark method
   *  in the Ocean class for this method's use.
   *  @return the Ocean represented by a run-length encoding.
   */

  public Ocean toOcean() {
    // Replace the following line with your solution.
    //return new Ocean(1, 1, 1);
      int[][] flatArray = new int[height * width][2];
      int[][][] squareArray = new int[width][height][2];
      int l = 0;
      int a, b, c;
     
      Ocean newOcean = new Ocean(width, height, starveTime);

      // Convert to flat Array
      for (int i = 1; i <= dOcean.getSize(); i++){
          a = dOcean.getItem(i);
          b = dOcean.getSTime(i);
          c = dOcean.getNumElements(i);
          for (int k = 0; k < c; k++) {
              flatArray[l][0] = a;
              flatArray[l][1] = b;
              l++;
          }
      }
      
      // Convert to Square Array
      for (int i = 0; i < flatArray.length; i++) {
          squareArray[i % width][i / width][0] = flatArray[i][0];
          squareArray[i % width][i / width][1] = flatArray[i][1];
      }

      // Insert items to Ocean Array
      for (int i = 0; i < width; i++) {
          for (int j = 0; j < height; j++) {
              if (squareArray[i][j][0] == 1) {
                  newOcean.addShark(i, j, squareArray[i][j][1]);
              } else if (squareArray[i][j][0] == 2) {
                  newOcean.addFish(i, j);
              }
          }
      }
      return newOcean;
  }

  /**
   *  The following method is required for Part III.
   */

  /**
   *  RunLengthEncoding() (with one parameter) is a constructor that creates
   *  a run-length encoding of an input Ocean.  You will need to implement
   *  the sharkFeeding method in the Ocean class for this constructor's use.
   *  @param sea is the ocean to encode.
   */

  public RunLengthEncoding(Ocean sea) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      width = sea.width();
      height = sea.height();
      starveTime = sea.starveTime();
      
      int[][][] squareArray2 = new int[width][height][2];
      int[][] flatArray2 = new int[width * height][2];

      dOcean = new DList();

      // build square array
      for (int m = 0; m < width; m++) {
          for (int n = 0; n < height; n++) {
              squareArray2[m][n][0] = sea.cellContents(m, n);
              squareArray2[m][n][1] = sea.sharkFeeding(m, n);
          }
      }

      // build flat array
      int m12 = -1;
      for (int m2 = 0; m2 < (width * height); m2++) {
          if ((m2 % width) == 0) {
              m12++;
          }
          flatArray2[m2][0] = squareArray2[m2 % width][m12][0];
          flatArray2[m2][1] = squareArray2[m2 % width][m12][1];
      }

      // convert runtime
      for (int m3 = 0; m3 < (width * height); m3++) {
          dOcean.insertBack(flatArray2[m3][0], flatArray2[m3][1], 1);
      }

      // squish list
      dOcean.squish();

      check();
  }


  /**
   *  The following methods are required for Part IV.
   */

  /**
   *  addFish() places a fish in cell (x, y) if the cell is empty.  If the
   *  cell is already occupied, leave the cell as it is.  The final run-length
   *  encoding should be compressed as much as possible; there should not be
   *  two consecutive runs of sharks with the same degree of hunger.
   *  @param x is the x-coordinate of the cell to place a fish in.
   *  @param y is the y-coordinate of the cell to place a fish in.
   */

  public void addFish(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      int target = (width * y) + x + 1;
      dOcean.insertSpecial(target, 2);
      dOcean.squish();
      //System.out.println("my dOcean: " + dOcean); // for troubleshooting

      dOcean.squish();
      //System.out.println("my dOcean: " + dOcean); // for troubleshooting
      check();
  }

  /**
   *  addShark() (with two parameters) places a newborn shark in cell (x, y) if
   *  the cell is empty.  A "newborn" shark is equivalent to a shark that has
   *  just eaten.  If the cell is already occupied, leave the cell as it is.
   *  The final run-length encoding should be compressed as much as possible;
   *  there should not be two consecutive runs of sharks with the same degree
   *  of hunger.
   *  @param x is the x-coordinate of the cell to place a shark in.
   *  @param y is the y-coordinate of the cell to place a shark in.
   */

  public void addShark(int x, int y) {
    // Your solution here, but you should probably leave the following line
    //   at the end.
      int target = (width * y) + x + 1;
      dOcean.insertSpecial(target, 1);
      dOcean.squish();
      // System.out.println("my dOcean: " + dOcean); // for troubleshooting
      check();
  }

  /**
   *  check() walks through the run-length encoding and prints an error message
   *  if two consecutive runs have the same contents, or if the sum of all run
   *  lengths does not equal the number of cells in the ocean.
   */

  private void check() {
      int total = 0;
      for (int i = 1; i < dOcean.getSize(); i++) {
          if (dOcean.getItem(i) == dOcean.getItem(i + 1) && dOcean.getSTime(i) == dOcean.getSTime(i + 1)) {
                  System.out.println("Same Type error");
              }
      }
      for (int i = 1; i <= dOcean.getSize(); i++) {
          total += dOcean.getNumElements(i);
      }
      if (total != (width * height) || total < 1) {
          System.out.println("Total error");
      }
  }

}
