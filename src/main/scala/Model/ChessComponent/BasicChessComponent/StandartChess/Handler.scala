package Model.ChessComponent.BasicChessComponent.StandartChess

import Model.ChessComponent.*
import Model.ChessComponent.BasicChessComponent.*
import Model.ChessComponent.RealChess.*

trait Handler {
    def handle(request : (Int, Int, Int, Vector[Piece], Color)) : Boolean
}

class BaseHandler (val next : Option[Handler]) extends Handler {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = true
}

class OnBoardHandler (next : Option[Handler]) extends BaseHandler(next) {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = {
        if(PseudoMoves.onBoard(request._1, request._2, request._3)) {
            next match {
                case Some(a) => a.handle(request._1, request._2, request._3, request._4, request._5)
                case None => return false
            }
        } else {
            false
        }
    }
}

class EmptySquareHandler (next : Option[Handler]) extends BaseHandler(next) {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = {
        if ((request._4(request._1 + request._2 * 8 + request._3).color == Color.EMPTY)) {
            true
        } else {
            next match {
                case Some(a) => a.handle(request._1, request._2, request._3, request._4, request._5)
                case None => return false
            }
        }
    }
}

class EnemySquareHandler (next : Option[Handler]) extends BaseHandler(next) {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = {
        if(request._4(request._1 + request._2 * 8 + request._3).color == request._5) {
            true
        } else {
            false
        }
    }
}