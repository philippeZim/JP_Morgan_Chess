import PieceType.EMPTY

import scala.annotation.tailrec //Pe4; pe5; Bc4; nc6; Qf3; pd6; Qf7;

object ScholarsMate {

    val moves: List[(Int, Int)] = List((52, 36), (12, 28), (61, 34), (1, 18), (59, 45), (11, 19), (45, 13));

    def movesToString(board: Vector[Piece], moves: List[(Int, Int)]): String = {
        @tailrec def sub(currentBoard: Vector[Piece], acc: String, moves: List[(Int, Int)]): String = {
            moves match {
                case Nil => acc;
                case h :: t => {
                    val cur = makeMove(currentBoard, h)
                    sub(cur, acc + ChessBoard.getBoardString(cur) + "\n\n", t);
                }
            }
        }

        sub(board, ChessBoard.getBoardString(board) + "\n\n", moves);
    }

    def makeMove(board: Vector[Piece], move: (Int, Int)): Vector[Piece] = {
        val (from, to) = move
        val piece = board(from)
        board.updated(to, piece).updated(from, Piece(PieceType.EMPTY, Color.EMPTY))
    }
}
