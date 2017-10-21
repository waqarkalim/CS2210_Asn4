public class BlockedTicTacToe {

	// Initialising all the instance variables

	private int board_size;
	private int inline;
	private int max_levels;

	private char[][] gameBoard;

	int count = 0;

	// Creates an object for BlockedTicTacToe()
	/**
	 * @param board_size
	 * @param inline
	 * @param max_levels
	 */
	public BlockedTicTacToe(int board_size, int inline, int max_levels) {
		this.board_size = board_size;
		this.inline = inline;
		this.max_levels = max_levels;
		gameBoard = new char[board_size][board_size];

		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				gameBoard[i][j] = ' ';
			}
		}
	};

	// The createDictionary() method creates a dictionary for the hashtable
	/**
	 * @return
	 */
	public TTTDictionary createDictionary() {
		TTTDictionary configurations = new TTTDictionary(4999);
		return configurations;
	};

	// The repeatedConfig() method takes the configurations as an input and
	// checks to see if the inputed configuration has
	// been repeated in the hashtable and if it is, then the score of that
	// configuration is returned
	/**
	 * @param configurations
	 * @return
	 */
	public int repeatedConfig(TTTDictionary configurations) {

		if (count == 0)
			return -1;
		count++;

		String s = "";
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				s += gameBoard[i][j];
			}
		}
		int getValue = ((TTTRecord) configurations.get(s)).getScore();
		if (getValue == 0) {
			return -1;
		}
		return getValue;
	};

	// The insertConfig() method takes configurations, score and the level as
	// inputs and inserts that configuration in the
	// hashtable
	/**
	 * @param configurations
	 * @param score
	 * @param level
	 */
	public void insertConfig(TTTDictionary configurations, int score, int level) {
		String s = "";
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				s += gameBoard[i][j];
			}
		}
		configurations.put(new TTTRecord(s, score, level));
	};

	// The storePlay() method takes row, column and symbol as an input and
	// stores that symbol in the row and column
	// position in the gameBoard
	/**
	 * @param row
	 * @param col
	 * @param symbol
	 */
	public void storePlay(int row, int col, char symbol) {
		gameBoard[row][col] = symbol;
	};

	// The squareIsEmpty() method takes row and column as inputs and return true
	// if that position in the gameBoard is
	// not occupied by any symbol
	/**
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean squareIsEmpty(int row, int col) {
		return (gameBoard[row][col] == ' ');
	};

	// The HorizontalWinCheck() takes a symbol as an input and checks whether
	// that symbol wins horizontally
	/**
	 * @param symbol
	 * @return
	 */
	private boolean HorizontalWinCheck(char symbol) {
		for (int i = 0; i < board_size; i++) {
			int count = 0;
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == symbol){
//					System.out.println("Horizontal check count: " + count);
					count++;
					}
				else if (count == inline){
					return true;
					}
				else if (count > inline) {
					return true;
				}
				else if (gameBoard[i][j] != symbol) {
					count = 0;
					}
			}
		}
		return false;
	};
	// The VerticalWinCheck() takes a symbol as an input and checks whether that
	// symbol wins vertically
	/**
	 * @param symbol
	 * @return
	 */
	private boolean VerticalWinCheck(char symbol) {
		for (int j = 0; j < board_size; j++) {
			int count = 0;
			for (int i = 0; i < board_size; i++) {
				if (gameBoard[i][j] == symbol){
//					System.out.println("Vertical check count: " + count);
					count++;
					}
				else if (count == inline){
					return true;
					}
				else if (count > inline){
					return true;
				}
				else if (gameBoard[i][j] != symbol) {
					count = 0;
					}
			}
		}
		return false;
	};
	// The DiagonalWinCheck() takes a symbol as an input and checks whether that
	// symbol wins diagonally
	/**
	 * @param symbol
	 * @return
	 */
	private boolean DiagonalWinCheck(char symbol) {

		int countneeded = inline - 1;
		int count;
		for (int row = 0; row < board_size; row++) {
			for (int col = 0; col < board_size; col++) {
				if ((col + countneeded < board_size) && (row + countneeded < board_size)) {
					count = 0;
					for (int i = 0; i < inline; i++) {
						if (gameBoard[row + i][col + i] == symbol) {
							count++;
						}
					}
					if (count == inline)
						return true;
				}
				if ((col + countneeded < board_size) && (row - countneeded >= 0)) {
					count = 0;
					for (int i = 0; i < inline; i++) {
						if (gameBoard[row - i][col + i] == symbol) {
							count++;
						}
					}
					if (count == inline)
						return true;
				}
			}
		}
		return false;
	};
	

	// The wins() method takes a symbol as an input and check if that symbol
	// wins at all on the gameBoard
	/**
	 * @param symbol
	 * @return
	 */
	public boolean wins(char symbol) {
		return (HorizontalWinCheck(symbol) || VerticalWinCheck(symbol) || DiagonalWinCheck(symbol));
	};

	// The isDraw() method checks to see the position of the board gives a draw
	// or not
	/**
	 * @return
	 */
	public boolean isDraw() {
		boolean emptyfound = false;
		for (int i = 0; i < board_size; i++) {
			for (int j = 0; j < board_size; j++) {
				if (gameBoard[i][j] == ' ') {
					emptyfound = true;
				}
			}
		}
		if (!(emptyfound) && !(wins('x')) && !(wins('o')))
			return true;
		return false;
	};

	// The evalBoard() method checks to how the player or computer is playing
	// and return numbers relating to a different
	// situation
	/**
	 * @return
	 */
	public int evalBoard() {
		if (wins('o') && !wins('x')){
			return 3;
			}
		else if (wins('x') && !wins('o')){
			return 0;
			}
		else if (isDraw()){
			return 1;
			}
		else {
			return 2;
			}
	};
}

