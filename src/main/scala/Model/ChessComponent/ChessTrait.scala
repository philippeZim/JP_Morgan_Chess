package Model.ChessComponent

import Model.ChessComponent.BasicChessComponent.StandartChess.Piece
import cController.ControllerComponent.Extra.Event

trait ChessTrait {
    /**
     * getBoardString returns the string view of the current board state for the TUI given by the Vector parameter
     * @param board current board state as vector
     * @return string view of the current board
     */
    def getBoardString(board : Vector[Piece]) : String

    /**
     * fenToBoard translates the fen-String representation of the board state as a Vector[Piece] Type
     * @param fen current board state as fen-String
     * @return board state as a Vector[Piece]
     */
    def fenToBoard(fen: String): Vector[Piece]

    /**
     * getAllLegalMoves calculates all legal Moves that can be played in the current position (represented by the fen-String)
     * by the Color that is to move
     * @param fen current board state as fen-String
     * @return List of Int-Tupels (fromSquare, toSquare)
     */
    def getAllLegalMoves(fen: String): List[(Int, Int)]

    /**
     * makeMove gets a Int-Tupel as a move to make and transforms the given fen into a new fen with the given move made
     * @param fen current board state as fen-String
     * @param move move as a Int-Tupel (fromSquare, toSquare)
     * @return new board state as fen-String
     */
    def makeMove(fen: String, move: (Int, Int)): String

    /**
     * canPromote checks if a pawn promotion is possible on the given board state and returns the index of the square of the promoteable pawn
     * @param fen current board state as fen-String
     * @return index of the square of the promoteable pawn
     */
    def canPromote(fen: String): Int

    /**
     * promote changes the current board state after a promotion happend. The Pawn will be replaced by the given Piece
     * @param pieceName One Letter representing the requested piece
     * @param fen current board state as fen-String
     * @param position index of the square of the promoteable pawn
     * @return new board state as fen-String
     */
    def promote(pieceName: String, fen : String, position : Int) : String

    /**
     * isColorPiece checks if a piece a given position is a piece of the Color that is to move right now
     * @param fen current board state as fen-String
     * @param position the position that should be checked
     * @return true if there is a piece of the color that is to move. Otherwise false
     */
    def isColorPiece(fen : String, position : Int) : Boolean

    /**
     * translateCastle translates a raw King Move (over 2 squares) into a castling move that the model will understand
     * @param board current board state as Vector[Piece], 
     * @param move move as a Int-Tupel (fromSquare, toSquare)
     * @return move (if move was a castle move it is a little bit different (negative number in front)
     */
    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int)

    /**
     * isRemis checks if the given game state is a remis ending
     * @param fen current board state as fen-String
     * @param legalMoves List of possible moves in the current position
     * @return true if it is a remis. Otherwise false
     */
    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean

    /**
     * getBestMove talks to the ChessApi and returns the best move from the chess engine depending on the depth (how
     * many moves in advance should be calculated)
     * @param fen current board state as fen-String
     * @param depth how many moves in advance should be calculated
     * @return best move as a String (fe e2e4)
     */
    def getBestMove(fen: String, depth: Int): String

    /**
     * getDefaultFen returns the starting fen position without the extra info at the end
     * @return starting fen
     */
    def getDefaultFen() : String

    /**
     * translateMoveStringToInt translates a move in the format of e2e4 to a Int-Tupel move
     * @param fen current board state as fen-String
     * @param move as one String
     * @return move as Int-Tupel
     */
    def translateMoveStringToInt(fen: String, move: String): (Int, Int)

    /**
     * getDefaultBoard returns the starting board in the Vector[Piece] format
     * @return starting board in the Vector[Piece] format
     */
    def getDefaultBoard(): Vector[Piece]
}
