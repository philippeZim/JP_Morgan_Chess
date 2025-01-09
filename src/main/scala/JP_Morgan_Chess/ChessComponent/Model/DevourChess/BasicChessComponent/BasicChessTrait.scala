package JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent

import JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent.StandartChess.{Color, Piece}

trait BasicChessTrait {
    def getBoardString(board : Vector[Piece]) : String

    def fenToBoard(fen: String): Vector[Piece]

    def getAllPseudoLegalMoves(fen: String): List[(Int, Int)]

    def makeMove(fen: String, move: (Int, Int)): String

    def canPromote(fen: String): Int

    def promote(pieceName: String, fen: String, position: Int): String

    def isColorPiece(fen: String, position: Int): Boolean

    def moveToIndex(from: String, to: String): (Int, Int)

    def translateCastle(board: Vector[Piece], move: (Int, Int)): (Int, Int)

    def piecesPositions(board: Vector[Piece], pieces: List[Piece]): List[Int]

    def piecePositions(board: Vector[Piece], piece: Piece): List[Int]

    def extractColor(color: String): (Int, Color, Color)

    def onBoard(beginningPosition: Int, rowDirection: Int, columDirection: Int): Boolean

    def boardToFen(board: Vector[Piece]): String

    def isDifferentColorPiece(fen: String, position: Int): Boolean
    
    def getDefaultFen() : String 
}
