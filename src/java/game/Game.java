package game;

/**
 *
 * @author Alan Johnson
 */
public class Game {
    
    enum Turn {Player_1, Player_2};
    enum Space {Empty, X, O};
    enum Status {Playing, Winner_1, Winner_2, Tied};
    
    Space spaces[][] = new Space[3][3];
    Turn turn;
    Status status;
    private int turnNum;
    
    public Game () {
        newGame();
    }
    
    void setSpace (int i, int j) {
        
        Space space;
        
        switch (turn) {
            
            case Player_1:
                space = Space.X;
                turn = Turn.Player_2;
                break;
                
            case Player_2:
                space = Space.O;
                turn = Turn.Player_1;
                break;
                
            default:
                space = Space.Empty;
            
        }
        
        spaces[i][j] = space;
        
        if (turnNum++ > 4) {
            if (checkBoard(i, j))
                gameWon();
            else if (turnNum > 9)
                status = Status.Tied;
        }
        
    }
    
    private void gameWon () {
        switch (turn) {
            case Player_1:
                status = Status.Winner_2;
                break;
                
            case Player_2:
                status = Status.Winner_1;
                break;
        }
    }
    
    
    
    void newGame () {
        
        turnNum = 1;
        turn = Turn.Player_1;
        status = Status.Playing;
        
        for (int i = 0; i < 3; i++) {
            
            for (int j = 0; j < 3; j++) {
                
                spaces[i][j] = Space.Empty;
                
            }
        }
    }  //  end method newGame()
    
    private boolean checkBoard (int x, int y) {
        
        Space checkSpace = Space.Empty;
        
        //  Sets checkSpace to opposite of current turn, because turn has already
        //  been switched
        switch (turn) {
            
            case Player_1:
                checkSpace = Space.O;
                break;
                
            case Player_2:
                checkSpace = Space.X;
                break;
            
        }
        
        //  Check the column
        for (int i = 0; i < 3; i++) {

            if (spaces[x][i] != checkSpace) {
                break;
            }

            if (i == 2) {
                return true;
            }

        }

        //  Check the row
        for (int i = 0; i < 3; i++) {

            if (spaces[i][y] != checkSpace) {
                break;
            }

            if (i == 2) {
                return true;
            }

        }
        
        //  Check for diagonals:
        if (spaces[1][1] == checkSpace) {
            
            //  First the "\" (Top-Left to Bottom-Right):
            if (spaces[0][0] == checkSpace
             && spaces[2][2] == checkSpace)
                return true;
            
            //  Then the "/" (Top-Right to Bottom-Left):
            if (spaces[2][0] == checkSpace
             && spaces[0][2] == checkSpace)
                return true;
            
            
        }
        
         
     
        return false;
        
    }
    
}
