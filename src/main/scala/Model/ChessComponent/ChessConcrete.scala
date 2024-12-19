package Model.ChessComponent

import Controller.ControllerComponent.Event

object ChessConcrete extends ChessTrait {
    def getBoardString(board: Vector[Piece]): String = {
        ChessBoard.getBoardString(board)
    }

    def fenToBoard(fen: String): Vector[Piece] = {
        ChessBoard.fenToBoard(fen)
    }

    def getAllLegalMoves(fen: String): List[(Int, Int)] = {
        LegalMoves.getAllLegalMoves(fen)
    }

    def makeMove(fen: String, move: (Int, Int)): String = {
        ChessBoard.makeMove(fen, move)
    }

    def canPromote(fen: String): Int = {
        ChessBoard.canPromote(fen)
    }

    def promote(pieceName: String, fen: String, position: Int): String = {
        ChessBoard.promote(pieceName, fen, position)
    }

    def isColorPiece(fen: String, position: Int): Boolean = {
        ChessBoard.isColorPiece(fen, position)
    }

    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int) = {
        ChessBoard.translateCastle(board, move)
    }

    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean = {
        Remis.isRemis(fen, legalMoves)
    }
}
