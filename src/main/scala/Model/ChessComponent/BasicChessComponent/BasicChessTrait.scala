package Model.ChessComponent.BasicChessComponent

import Model.ChessComponent.BasicChessComponent.StandartChess.{Color, Piece}

trait BasicChessTrait {
    /**
     * getBoardString returns the string view of the current board state for the TUI given by the Vector parameter
     * @param board current board state as vector
     * @return string view of the current board
     */
    def getBoardString(board : Vector[Piece]) : String

    /**
     * fenToBoard translates the fen-String representation of the board state as a Vector[Piece] Type
     *
     * @param fen current board state as fen-String
     * @return board state as a Vector[Piece]
     * */
    def fenToBoard(fen: String): Vector[Piece]

    /**
     * getAllPseudoLegalMoves calculates all pseudo legal Moves (moves that the pieces can do without checking if the king would be checked after)
     * that can be played in the current position (represented by the fen-String) by the Color that is to move.
     * @param fen current board state as fen-String
     * @return List of Int-Tupels (fromSquare, toSquare)
     * */
    def getAllPseudoLegalMoves(fen: String): List[(Int, Int)]

    /**
     * makeMove gets a Int-Tupel as a move to make and transforms the given fen into a new fen with the given move made
     *
     * @param fen  current board state as fen-String
     * @param move move as a Int-Tupel (fromSquare, toSquare)
     * @return new board state as fen-String
     * */
    def makeMove(fen: String, move: (Int, Int)): String

    /**
     * canPromote checks if a pawn promotion is possible on the given board state and returns the index of the square of the promoteable pawn
     * @param fen current board state as fen-String
     * @return index of the square of the promoteable pawn
     * */
    def canPromote(fen: String): Int

    /**
     * promote changes the current board state after a promotion happend. The Pawn will be replaced by the given Piece
     * @param pieceName One Letter representing the requested piece
     * @param fen current board state as fen-String
     * @param position index of the square of the promoteable pawn
     * @return new board state as fen-String
     * */
    def promote(pieceName: String, fen: String, position: Int): String

    /**
     * isColorPiece checks if a piece a given position is a piece of the Color that is to move right now
     * @param fen current board state as fen-String
     * @param position the position that should be checked
     * @return true if there is a piece of the color that is to move. Otherwise false
     * */
    def isColorPiece(fen: String, position: Int): Boolean

    /**
     * translateCastle translates a raw King Move (over 2 squares) into a castling move that the model will understand
     * @param board current board state as Vector[Piece],
     * @param move move as a Int-Tupel (fromSquare, toSquare)
     * @return move (if move was a castle move it is a little bit different (negative number in front)
     * */
    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int)

    /**
     * piecesPositions searches given pieces on the board and returns a list of all squares the given pieces are currently on
     * @param board current board state as Vector[Piece]
     * @param pieces list of pieces to look for
     * @return list of all squares the given piece is currently on
     */
    def piecesPositions(board: Vector[Piece], pieces: List[Piece]): List[Int]

    /**
     * piecePositions searches a given piece on the board and returns a list of all squares the given piece is currently on
     * @param board  current board state as Vector[Piece]
     * @param pieces a piece to look for
     * @return list of all squares the given piece is currently on
     * */
    def piecePositions(board: Vector[Piece], piece: Piece): List[Int]

    /**
     * extractColor gets who's turn it is as a string and returns the direction the pawns are moving, the enum Color that
     * can be taken in this turn and the enum Color that can move right now
     * @param color "w" if white is to move, "b" if black is to move
     * @return the direction the pawns are moving, the enum Color that can be taken in this turn and the enum Color that can move right now
     */
    def extractColor(color: String): (Int, Color, Color)

    /**
     * onBoard checks if a given move leaves the piece of the premisses of the board
     * @param beginningPosition where the piece starts
     * @param rowDirection how many rows in what direction
     * @param columDirection how many colums in what direction
     * @return true if it remains on the board. Otherwise false
     */
    def onBoard(beginningPosition: Int, rowDirection: Int, columDirection: Int): Boolean

    /**
     * boardToFen translates the Vector[Piece] Representation of the board to a fen-String
     * @param board current board state as Vector[Piece]
     * @return current board state as fen
     */
    def boardToFen(board: Vector[Piece]): String

    /**
     * isDifferentColorPiece
     * @param fen
     * @param position
     * @return
     */
    def isDifferentColorPiece(fen: String, position: Int): Boolean

    def getDefaultFen() : String

    def translateMoveStringToInt(fen: String, move: String): (Int, Int)

    def getDefaultBoard(): Vector[Piece]
}
