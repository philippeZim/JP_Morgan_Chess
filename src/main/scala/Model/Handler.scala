package Model

trait Handler {
    def handle(request : (Int, Int, Int, Vector[Piece], Color)) : Boolean
}

class BaseHandler (val next : Handler) extends Handler {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = true
}

class OnBoardHandler (next : Handler) extends BaseHandler(next) {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = {
        if(PseudoMoves.onBoard(request._1, request._2, request._3)) {
            next.handle((request._1, request._2, request._3, request._4, request._5))
        } else {
            false
        }
    }
}

class EmptySquareHandler (next : Handler) extends BaseHandler(next) {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = {
        if((request._4(request._1 + request._2 * 8 + request._3).color == Color.EMPTY)) {
            true
        } else {
            next.handle((request._1, request._2, request._3, request._4, request._5))
        }
    }
}

class EnemySquareHandler (next : Handler) extends BaseHandler(next) {
    override def handle(request: (Int, Int, Int, Vector[Piece], Color)): Boolean = {
        if(request._4(request._1 + request._2 * 8 + request._3).color == request._5) {
            true
        } else {
            false
        }
    }
}
