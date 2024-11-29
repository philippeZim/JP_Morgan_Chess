package Model

import scala.annotation.tailrec

class Board extends Iterable[Piece] {
    val board: Vector[Piece] = ChessBoard.getDefaultBoard()

    override def iterator: Iterator[Piece] = board.iterator

    override def toString: String = {
        val it = this.iterator
        val rows = for (_ <- 1 to 8) yield {
            (for (_ <- 1 to 8) yield it.next().toString).mkString(" ")
        }
        rows.mkString("\n")
    }
}

