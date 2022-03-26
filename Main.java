import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

class Main {
	  
	public static void main(String[] args) {
		char[][] board = new char[3][3];
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}

	  Scanner in = new Scanner(System.in);
    Random rand = new Random();
    boolean isNumber = false;
    int usrinput = 0;
    System.out.println("Welcome to Tic Tac Toe!");
    System.out.println("Enter (1) for single player and (2) for multiplayer");

    while (!isNumber) {
    try {
      usrinput = in.nextInt();
      if (usrinput == 1 || usrinput == 2) {
      isNumber = true;
      }
      else {
        System.out.println("Choose (1) for Single Player or (2) for Multiplayer");
      }
    } catch (InputMismatchException error) {
      System.out.println("Please enter 1 or 2 only!");
      in.next();
     }
    }

    String p1 = null;
    String p2 = null;
    int row = 0;
		int col = 0;


    if(usrinput == 1) {
      System.out.println("Please enter your name");
      p1 = in.next();
	
		boolean player1 = true;

		boolean gameEnded = false;
		while(!gameEnded) {

			gameBoard(board);

      char c = '-';
			if(player1) {
				System.out.println("It is " + p1 + "'s Turn (x):");
        c = 'x';
        while(true) {
        isNumber = false; 
        while(!isNumber) {
        try{
				System.out.print("Please enter a row number (1, 2, or 3): ");

				row = in.nextInt();
        row = row - 1;
				System.out.print("Please enter a column number (1, 2, or 3): ");
				col = in.nextInt();
        col = col - 1;
        isNumber = true;

        } catch (InputMismatchException error) {
        System.out.println("Please enter integers only!");
        in.next();
        }
      }
				if(row < 0 || col < 0 || row > 2 || col > 2) {
					System.out.println("This position is not on the board. Please try again.");
				} 
        else if(board[row][col] != '-') {
					System.out.println("This position is already filled. Please find another spot!");
				} 
        else {
					break;
				}
			}
			board[row][col] = c;
			} 
      else {
        System.out.println("It is bot's Turn (o):");
        c = 'o';
        row = rand.nextInt(3);
        col = rand.nextInt(3);
        while (true) {
        if(board[row][col] != '-') {
					row = rand.nextInt(3);
          col = rand.nextInt(3);
				}
        else {
        board[row][col] = c;
        break;
        }
      }
			}

			if(playerHasWon(board) == 'x') {
				System.out.println(p1 + " has won!");
				gameEnded = true;
        System.exit(0);
			} 
      else if(playerHasWon(board) == 'o') {
        gameBoard(board);
				System.out.println("The bot has won!");
				gameEnded = true;
        System.exit(0);
			} 
      else if(boardFull(board)) {
					System.out.println("It's a tie!");
					gameEnded = true;
  		    System.exit(0);
      } 
        else {
					player1 = !player1;
				}
		}
		gameBoard(board);
  }
  else if(usrinput == 2) {
      System.out.println("Please enter the name for player 1 and player 2 ");
		  p1 = in.next();
      p2 = in.next();
    }
	
		boolean player1 = true;

		boolean gameEnded = false;
		while(!gameEnded) {

			gameBoard(board);

			if(player1) {
				System.out.println("It is " + p1 + "'s Turn (x):");
			} 
      else {
				System.out.println("It is " + p2 + "'s Turn (o):");
			}

			char c = '-';
			if(player1) {
				c = 'x';
			} else {
				c = 'o';
			}

			row = 0;
			col = 0;

			while(true) {
        isNumber = false; 
        while(!isNumber) {
        try{
				System.out.print("Please enter a row number (1, 2, or 3): ");

				row = in.nextInt();
        row = row - 1;
				System.out.print("Please enter a column number (1, 2, or 3): ");
				col = in.nextInt();
        col = col - 1;
        isNumber = true;

        } catch (InputMismatchException error) {
        System.out.println("Please enter integers only!");
        in.next();
        }
      }
				if(row < 0 || col < 0 || row > 2 || col > 2) {
					System.out.println("This position is not on the board. Please try again.");
				} 
        else if(board[row][col] != '-') {
					System.out.println("This position is already filled. Please find another spot!");
				} 
        else {
					break;
				}
			}

			board[row][col] = c;

			if(playerHasWon(board) == 'x') {
				System.out.println(p1 + " has won!");
				gameEnded = true;
			} 
      else if(playerHasWon(board) == 'o') {
				System.out.println(p2 + " has won!");
				gameEnded = true;
			} 
      else {
				if(boardFull(board)) {
					System.out.println("It's a tie!");
					gameEnded = true;
				} 
        else {
					player1 = !player1;
				}
			}
		}
		gameBoard(board);
  }
  
	public static void gameBoard(char[][] board) {
		System.out.println("Board:");
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public static char playerHasWon(char[][] board) {
		for(int i = 0; i < 3; i++) {
			if(board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
				return board[i][0];
			}
		}
		for(int j = 0; j < 3; j++) {
			if(board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
				return board[0][j];
			}
		}
		if(board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
			return board[0][0];
		}
		if(board[2][0] == board[1][1] && board[1][1] ==  board[0][2] && board[2][0] != '-') {
			return board[2][0];
		}
		return ' ';
	}
	public static boolean boardFull(char[][] board) {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(board[i][j] == '-') {
					return false;
				}
			}
		}
		return true;
	}

  }
