import scala.annotation.tailrec
//Pe4; pe5; Bc4; nc6; Qf3; pd6; Qf7;

object ScholarsMate {

    val moves: List[(Int, Int, Int, Int)] = List(
        (6, 4, 4, 4),
        (1, 4, 3, 4),
        (7, 5, 4, 2),
        (0, 1, 2, 2),
        (7, 3, 5, 5),
        (1, 3, 2, 3),
        (5, 5, 1, 5)
    )

    def movesToString(
        board: Vector[Vector[Piece]],
        moves: List[(Int, Int, Int, Int)]
    ): String = {
        @tailrec
        def sub(
            currentBoard: Vector[Vector[Piece]],
            acc: String,
            moves: List[(Int, Int, Int, Int)]
        ): String = {
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

    def makeMove(
        board: Vector[Vector[Piece]],
        move: (Int, Int, Int, Int)
    ): Vector[Vector[Piece]] = {
        val (fromRow, fromCol, toRow, toCol) = move
        val piece = board(fromRow)(fromCol)
        board
            .updated(toRow, board(toRow).updated(toCol, piece))
            .updated(
                fromRow,
                board(fromRow)
                    .updated(fromCol, Piece(PieceType.EMPTY, Color.EMPTY))
            )
    }
}
