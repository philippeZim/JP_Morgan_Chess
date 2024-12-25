package Model.ChessComponent.DevourChess

import Model.ChessComponent.BasicChess.StandartChess.Color.{BLACK, WHITE}
import Model.ChessComponent.BasicChess.StandartChess.PieceType.{KING, KNIGHT, PAWN, QUEEN, ROOK}
import Model.ChessComponent.BasicChess.StandartChess.{BasicChessFacade, Color, Piece, PieceType}

object Remis {
    //für den Fall, dass 2 pieces sich nicht gegenseitig schlagen können (also eig. nur bei 2 Läufern auf unterschiedlichen Farben
    def isRemis(fen : String) : Boolean = {
        val bishopPieceList = List(Piece(PieceType.BISHOP, BLACK), Piece(PieceType.BISHOP, WHITE))
        val OtherPieceList = List(Piece(PAWN, BLACK), Piece(PAWN, WHITE), Piece(ROOK, BLACK), Piece(ROOK, WHITE),
            Piece(QUEEN, WHITE), Piece(QUEEN, BLACK), Piece(KING, WHITE), Piece(KING, BLACK), Piece(KNIGHT, WHITE),
            Piece(KNIGHT, BLACK))
        val board = BasicChessFacade.fenToBoard(fen)
        
        if(BasicChessFacade.piecesPositions(board, OtherPieceList).nonEmpty) {
            return false           
        }
        val bishopPositions = BasicChessFacade.piecesPositions(board, bishopPieceList)
        if(bishopPositions.size != 2) {
            return false
        }
        if(BasicChessFacade.isColorPiece(fen, bishopPositions(0)) == BasicChessFacade.isColorPiece(fen, bishopPositions(1))) {
            return false
        }
        val Color1 = bishopPositions(0) match {
            case white1 if(bishopPositions(0) / 8) % 2 == 0 && bishopPositions(0) % 2 == 0 => Color.WHITE
            case black1 if(bishopPositions(0) / 8) % 2 == 0 && bishopPositions(0) % 2 == 1 => Color.BLACK
            case black2 if(bishopPositions(0) / 8) % 2 == 1 && bishopPositions(0) % 2 == 0 => Color.BLACK
            case white2 if(bishopPositions(0) / 8) % 2 == 1 && bishopPositions(0) % 2 == 1 => Color.WHITE
        }
        val Color2 = bishopPositions(1) match {
            case white1 if(bishopPositions(1) / 8) % 2 == 0 && bishopPositions(1) % 2 == 0 => Color.WHITE
            case black1 if(bishopPositions(1) / 8) % 2 == 0 && bishopPositions(1) % 2 == 1 => Color.BLACK
            case black2 if(bishopPositions(1) / 8) % 2 == 1 && bishopPositions(1) % 2 == 0 => Color.BLACK
            case white2 if(bishopPositions(1) / 8) % 2 == 1 && bishopPositions(1) % 2 == 1 => Color.WHITE
        }
        Color1 != Color2
    }
}
