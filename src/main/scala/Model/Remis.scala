package Model

import scala.annotation.tailrec

object Remis {


    def isPatt(fen: String, legalMoves: List[(Int, Int)]): Boolean = {
        if (legalMoves.isEmpty) {
            val board: Vector[Piece] = ChessBoard.fenToBoard(fen)
            val fenSplit: List[String] = fen.split(" ").toList;

            val (attackColorNum, moveColor, attackColor): (Int, Color, Color) = PseudoMoves.extractColor(fenSplit(1));
            val kingPos: Int = PseudoMoves.piecePositions(board, Piece(PieceType.KING, moveColor)).head
            if (!LegalMoves.isPosAttacked(fen, kingPos)) {
                return true
            }
        }
        false
    }
    
    

    def isMaterial(fen: String): Boolean = {
        
        @tailrec
        def sub1(li: List[Char], hadBishop: Boolean, hasWhiteExtra: Option[Boolean]): Boolean = {
            li match {
                case Nil => false;
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
                                    sub1(t, hadBishop, Some(false));
                                }
                            case None => sub1(t, hadBishop, Some(false));
                        }
                        
                    case 'N' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (!a) {
                                    false
                                } else {
                                    sub1(t, hadBishop, Some(true));
                                }
                            case None => sub1(t, hadBishop, Some(true));
                        }
                        
                    case 'b' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (a) {
                                    false
                                } else {
                                    if (hadBishop) {
                                        false
                                    } else {
                                        sub1(t, true, Some(false))
                                    }
                                }
                            case None =>
                                if (hadBishop) {
                                    false
                                } else {
                                    sub1(t, true, Some(false))
                                }
                        }
                    case 'B' =>
                        hasWhiteExtra match {
                            case Some(a) =>
                                if (!a) {
                                    false
                                } else {
                                    if (hadBishop) {
                                        false
                                    } else {
                                        sub1(t, true, Some(true))
                                    }
                                }
                            case None =>
                                if (hadBishop) {
                                    false
                                } else {
                                    sub1(t, true, Some(true))
                                }
                        }
                    case _ => sub1(t, hadBishop, hasWhiteExtra)
                }
            }
        }
        sub1(fen.split(" ")(0).toList, false, None);
    }

    def isRemis(fen: String, legalMoves: List[(Int, Int)]) : Boolean = {
        isPatt(fen, legalMoves) || isMaterial(fen)
    }
}
