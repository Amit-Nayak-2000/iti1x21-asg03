public class Transformer {

  /**
   * An static enum  for the types of
   * allowable transformations
   */
  public static enum Type {
    UNKNOWN,
    IDENTITY,
    ROTATION,
    VERTICAL_SYMMETRY,
    HORIZONAL_SYMMETRY,
  }

  /**
   * The list of all allowable symmetries for a n (numRows) x m (numColumns) board
   * by applying the following transformations
   * @param numRows The number of rows in your board
   * @param numColumns The number of columns in your board
   * @return All rotations for a symmetric board
   */
  public static Type[] symmetricTransformations(int numRows, int numColumns) {

    // -------------------
    // IMPLEMENT THIS METHOD
    // TODO: Based on the dimensions there are different allowable transformations
    // HINT: This method will help you determine the `allowable` tranformations
    //       in your TicTacToe game
    // -------------------

    // don't forget to delete this line of code!!!
    Type allowableTransformations[];

    if(numRows == numColumns)
		{
			allowableTransformations = new Type[]{Type.IDENTITY, Type.ROTATION, Type.ROTATION, Type.ROTATION, Type.HORIZONAL_SYMMETRY, Type.ROTATION, Type.ROTATION, Type.ROTATION};
		}
		else
		{
			allowableTransformations = new Type[]{Type.IDENTITY, Type.HORIZONAL_SYMMETRY, Type.VERTICAL_SYMMETRY, Type.HORIZONAL_SYMMETRY};
		}

    return allowableTransformations;
    
  }

  /**
   * Applies the transformation specified as parameter
   * to board
   *
   * If the transformation was successful return true, if not return false;
   */
  public static boolean transform(Type transformation, int numRows, int numColumns, int[] board) {

    switch(transformation) {
    case IDENTITY:
      return identity(numRows, numColumns, board);
    case ROTATION:
      return rotate90(numRows, numColumns, board);
    case VERTICAL_SYMMETRY:
      return verticalFlip(numRows, numColumns, board);
    case HORIZONAL_SYMMETRY:
      return horizontalFlip(numRows, numColumns, board);
    default:
      return false;
    }

  }

  /**
   * Create the identity board, which means do not flip the board at all.
   * Here we ignore the current values within the provided board and
   * populate it with its index value.
   *
   * If we consider a 3x3 board, the identity board would be
   *
   * 0 | 1 | 2
   * ----------
   * 3 | 4 | 5
   * ----------
   * 6 | 7 | 8
   *
   *
   * If the transformation was successful return true, if not return false;
   */
  public static boolean identity(int numRows, int numColumns, int[] board) {
    if(board == null){
      return false;
    }

    for(int i = 0; i < numRows*numColumns; i++){
      board[i] = i;
    }

    return true;
  }

  /**
   * Flip a board horizontally based on the n (numRows) x m (numColumns) grid
   * editing the provided board in place.
   *
   * If we consider a 3x3 board
   *
   * 1 | 2 | 3
   * ----------
   * 4 | 5 | 6
   * ----------
   * 7 | 8 | 9
   *
   * The updated horizontally flipped board would be
   *
   * 7 | 8 | 9
   * ----------
   * 4 | 5 | 6
   * ----------
   * 1 | 2 | 3
   *
   * If the transformation was successful return true, if not return false;
   */
  public static boolean horizontalFlip(int numRows, int numColumns, int[] board) {

    if(board == null){
      return false;
    }

    int temp;

    for(int i = 0; i < numRows / 2; i++){
      for(int j = 0; j < numColumns; j++){
        temp = board[numColumns * (numRows - i - 1) + j];
				board[numColumns * (numRows - i - 1) + j] = board[numColumns * i + j];
				board[numColumns * i + j] = temp;
      }
    }
    
    return true;
  }

 /**
   * Flip a board vertically based on the n (numRows) x m (numColumns) grid
   * editing the provided board in place.
   *
   * If we consider a 3x3 board
   *
   * 1 | 2 | 3
   * ----------
   * 4 | 5 | 6
   * ----------
   * 7 | 8 | 9
   *
   * The updated vertically flipped board would be
   *
   * 3 | 2 | 1
   * ----------
   * 6 | 5 | 4
   * ----------
   * 9 | 8 | 7
   *
   * If the transformation was successful return true, if not return false;
   */
  public static boolean verticalFlip(int numRows, int numColumns, int[] board) {

    if(board == null){
      return false;
    }

    int temp;

    for(int i = 0; i < numColumns / 2; i++){
      for(int j = 0; j < numRows; j++){
        temp = board[(j*numColumns) + numColumns - i - 1];
        board[(j*numColumns) + numColumns - i - 1] = board[(j*numColumns) + i];
				board[(j*numColumns) + i] = temp;
      }
    }
    
    return true;
  }

 /**
   * Rotate a board 90 degrees based on the n x (numRows) x m (numColumns) grid
   * editing the provided board in place.
   *
   * If we consider a 3x3 board
   *
   * 1 | 2 | 3
   * ----------
   * 4 | 5 | 6
   * ----------
   * 7 | 8 | 9
   *
   * The updated 90 rotated board would be
   *
   * 7 | 4 | 1
   * ----------
   * 8 | 5 | 2
   * ----------
   * 9 | 6 | 3
   *
   * You can only rotate n x n boards.
   *
   * If the transformation was successful return true, if not return false;
   */
  public static boolean rotate90(int numRows, int numColumns, int[] board) {

    if(board == null){
      return false;
    }

    int[] temp = new int[numRows * numColumns];
		for(int i = 0; i < board.length; i++)
		{
			temp[i] = board[i];
		}
		
		for(int i = 0; i < board.length; i++)
		{
			board[rotation(numColumns, i % numColumns, numColumns - i / numColumns - 1)] = temp[i];
		}

    return true;
  }

  private static int rotation(int numColumns, int row, int column){
    return ((row*numColumns) + column);
  }

  private static void test(int numRows, int numColumns) {
    int[] test;
    test = new int[numRows*numColumns];

    System.out.println("testing " + numRows + " numRows and " + numColumns + " numColumns.");

    identity(numRows, numColumns, test);
    System.out.println(java.util.Arrays.toString(test));

    horizontalFlip(numRows,numColumns,test);
    System.out.println("HF => " + java.util.Arrays.toString(test));

    horizontalFlip(numRows,numColumns,test);
    System.out.println("HF => " + java.util.Arrays.toString(test));

    verticalFlip(numRows,numColumns,test);
    System.out.println("VF => " + java.util.Arrays.toString(test));

    verticalFlip(numRows,numColumns,test);
    System.out.println("VF => " + java.util.Arrays.toString(test));

    for(int i = 0; i < 4; i++) {
      boolean didTransform = rotate90(numRows,numColumns,test);
      if (didTransform) {
        System.out.println("ROT => " + java.util.Arrays.toString(test));
      }
    }
  }

  public static void main(String[] args) {
    int[] test;
    int numRows, numColumns;

    test(2,2);
    test(2,3);
    test(3,3);
    test(4,3);
    test(4,4);
  }

}