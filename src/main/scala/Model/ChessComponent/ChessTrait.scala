package Model.ChessComponent

import Controller.ControllerComponent.RealChessController.Event
import Model.ChessComponent.BasicChess.StandartChess.Piece

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
}
