package Model.ChessComponent.RealChess

import Model.ChessComponent.BasicChess.StandartChess.{BasicChessFacade, Piece}
import Model.ChessComponent.ChessTrait

class RealChessFacade extends ChessTrait {
    def getBoardString(board: Vector[Piece]): String = {
        BasicChessFacade.getBoardString(board)
    }

    def fenToBoard(fen: String): Vector[Piece] = {
        BasicChessFacade.fenToBoard(fen)
    }

    def getAllLegalMoves(fen: String): List[(Int, Int)] = {
        LegalMoves.getAllLegalMoves(fen)
    }

    def makeMove(fen: String, move: (Int, Int)): String = {
        BasicChessFacade.makeMove(fen, move)
    }

    def canPromote(fen: String): Int = {
        BasicChessFacade.canPromote(fen)
    }

    def promote(pieceName: String, fen: String, position: Int): String = {
        BasicChessFacade.promote(pieceName, fen, position)
    }

    def isColorPiece(fen: String, position: Int): Boolean = {
        BasicChessFacade.isColorPiece(fen, position)
    }

    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int) = {
        BasicChessFacade.translateCastle(board, move)
    }

    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean = {
        Remis.isRemis(fen, legalMoves)
    }

    def getBestMove(fen: String, depth : Int): String = {
        ChessApiClient.getBestMove(fen, depth)
    }

    def moveToIndex(from: String, to: String): (Int, Int) = {
        BasicChessFacade.moveToIndex(from, to)
    }

    def getDefaultFen(): String = {
        BasicChessFacade.getDefaultFen()
    }
}
