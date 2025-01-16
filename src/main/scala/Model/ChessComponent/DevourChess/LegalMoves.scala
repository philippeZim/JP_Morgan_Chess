package Model.ChessComponent.DevourChess

import Model.ChessComponent.BasicChessComponent.StandartChess.BasicChessFacade

import scala.annotation.tailrec

object LegalMoves {
    def getAllLegalMoves (fen : String) : List[(Int,Int)]= {
        @tailrec
        def filterLegalWithoutTake(accumulator: List[(Int, Int)], pseudoMoves: List[(Int, Int)]): List[(Int, Int)] = {
            pseudoMoves match {
                case Nil => accumulator;
                case h :: t => {
                    if (isTakingMove(fen, h._2)) {
                        filterLegalWithTake(List(h), t);
                    } else {
                        filterLegalWithoutTake(h :: accumulator, t);
                    }
                }
            }
        }

        @tailrec
        def filterLegalWithTake(accumulator: List[(Int, Int)], pseudoMoves: List[(Int, Int)]): List[(Int, Int)] = {
            pseudoMoves match {
                case Nil => accumulator;
                case h :: t => {
                    if (isTakingMove(fen, h._2)) {
                        filterLegalWithTake(h :: accumulator, t);
                    } else {
                        filterLegalWithTake(accumulator, t);
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
