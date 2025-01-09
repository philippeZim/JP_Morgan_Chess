package JP_Morgan_Chess.ChessComponent.Model.DevourChess

import JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent.StandartChess.BasicChessFacade
import scala.annotation.tailrec

object LegalMoves {
    def getAllLegalMoves (fen : String) : List[(Int,Int)]= {
        @tailrec
        def filterLegalWithoutTake(acc: List[(Int, Int)], pseudo: List[(Int, Int)]): List[(Int, Int)] = {
            pseudo match {
                case Nil => acc;
                case h :: t => {
                    if (isTakingMove(fen, h._2)) {
                        filterLegalWithTake(List(h), t);
                    } else {
                        filterLegalWithoutTake(h :: acc, t);
                    }
                }
            }
        }

        @tailrec
        def filterLegalWithTake(acc: List[(Int, Int)], pseudo: List[(Int, Int)]): List[(Int, Int)] = {
            pseudo match {
                case Nil => acc;
                case h :: t => {
                    if (isTakingMove(fen, h._2)) {
                        filterLegalWithTake(h :: acc, t);
                    } else {
                        filterLegalWithTake(acc, t);
                    }
                }
            }
        }
        filterLegalWithoutTake(List(), BasicChessFacade.getAllPseudoLegalMoves(fen));
    }

    def isTakingMove(fen : String, attackedPosition : Int) : Boolean = {
        BasicChessFacade.isDifferentColorPiece(fen, attackedPosition)
    }
}
