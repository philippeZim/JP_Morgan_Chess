package Model.ChessComponent.RealChess

import Model.ChessComponent.BasicChessComponent.StandartChess.{BasicChessFacade, Color, Piece, PieceType}

import scala.annotation.tailrec

object Remis {


    def isPatt(fen: String, legalMoves: List[(Int, Int)]): Boolean = {
        if (legalMoves.isEmpty) {
            val board: Vector[Piece] = BasicChessFacade.fenToBoard(fen)
            val fenSplit: List[String] = fen.split(" ").toList;

            val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = BasicChessFacade.extractColor(fenSplit(1));
                val kingPos: Int = BasicChessFacade.piecePositions(board, Piece(PieceType.KING, moveColor)).head
            if (!LegalMoves.isPosAttacked(fen, kingPos)) {
                return true
            }
        }
        false
    }
    
    

    def isMaterial(fen: String): Boolean = {
        
        @tailrec
        def searchPieces(pieceList: List[Char], hadBishop: Boolean, hasWhiteExtra: Option[Boolean]): Boolean = {
            pieceList match {
                case Nil => true; //Changed to true
                case h::t => h match {
                    case 'p' => false
                    case 'P' => false
                    case 'r' => false
                    case 'R' => false
                    case 'q' => false
                    case 'Q' => false
                    case 'n' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (a) {
                                    false
                                } else {
                                    searchPieces(t, hadBishop, Some(false));
                                }
                            case None => searchPieces(t, hadBishop, Some(false));
                        }
                        
                    case 'N' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (!a) {
                                    false
                                } else {
                                    searchPieces(t, hadBishop, Some(true));
                                }
                            case None => searchPieces(t, hadBishop, Some(true));
                        }
                        
                    case 'b' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (a) {
                                    false
                                } else {
                                    false
                                }
                            case None => searchPieces(t, true, Some(false))

                        }
                    case 'B' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (!a) {
                                    false
                                } else {
                                    false
                                }
                            case None =>
                                    searchPieces(t, true, Some(true))
                        }
                    case _ => searchPieces(t, hadBishop, hasWhiteExtra)
                }
            }
        }
        searchPieces(fen.split(" ")(0).toList, false, None);
    }

    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean = {
        isPatt(fen, legalMoves) || isMaterial(fen)
    }
}
