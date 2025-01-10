package Model.ChessComponent

import Model.ChessComponent.BasicChess.StandartChess.Piece
import cController.ControllerComponent.Extra.Event

trait ChessTrait {
    def getBoardString(board : Vector[Piece]) : String
    
    def fenToBoard(fen: String): Vector[Piece]
    
    def getAllLegalMoves(fen: String): List[(Int, Int)]
    
    def makeMove(fen: String, move: (Int, Int)): String
    
    def canPromote(fen: String): Int
    
    def promote(pieceName: String, fen : String, position : Int) : String
    
    def isColorPiece(fen : String, position : Int) : Boolean
    
    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int)
    
    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean

    def getBestMove(fen: String, depth: Int): String

    def getDefaultFen() : String

    def translateMoveStringToInt(fen: String, move: String): (Int, Int)

    def getDefaultBoard(): Vector[Piece]
}
