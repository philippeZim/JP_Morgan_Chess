package Model.ChessComponent.BasicChess.StandartChess

import Model.ChessComponent.BasicChess.BasicChessTrait

object BasicChessFacade extends BasicChessTrait {
    def getBoardString(board: Vector[Piece]): String = {
        ChessBoard.getBoardString(board)
    }

    def fenToBoard(fen: String): Vector[Piece] = {
        ChessBoard.fenToBoard(fen)
    }

    def getAllPseudoLegalMoves(fen: String): List[(Int, Int)] = {
        PseudoMovesFacade.subSystemOperation(fen)
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

    def piecePositions(board: Vector[Piece], piece: Piece): List[Int] = {
        PseudoMoves.piecePositions(board, piece)
    }
    
    def piecesPositions(board: Vector[Piece], pieces: List[Piece]): List[Int] = {
        PseudoMoves.piecesPositions(board, pieces)
    }

    def extractColor(color: String): (Int, Color, Color) = {
        PseudoMoves.extractColor(color)
    }

    def onBoard(beginningPosition: Int, rowDirection: Int, columDirection: Int): Boolean = {
        PseudoMoves.onBoard(beginningPosition, rowDirection, columDirection)
    }

    def boardToFen(board: Vector[Piece]): String = {
        ChessBoard.boardToFen(board)
    }

    def isDifferentColorPiece(fen: String, position: Int): Boolean = {
        ChessBoard.isDifferentColorPiece(fen, position)
    }
}
