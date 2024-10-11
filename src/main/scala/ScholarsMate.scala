//Pe4; pe5; Bc4; nc6; Qf3; pd6; Qf7;

object ScholarsMate {
    val moves: List[(Int, Int, Int, Int)] = List(
        (6, 4, 4, 4), (1, 4, 3, 4),
        (7, 5, 4, 2), (0, 1, 2, 2),
        (7, 3, 5, 5), (1, 3, 2, 3),
        (5, 5, 1, 5)
    )


    def print_moves(board: Vector[Vector[String]], moves: List[(Int, Int, Int, Int)]): Unit = {
        ChessBoard.printBoard(board)
        moves match {
            case Nil => ()
            case h :: t => print_moves(makeMove(board, h), t)
        }
    }


    def makeMove(board: Vector[Vector[String]], move: (Int, Int, Int, Int)): Vector[Vector[String]] = {
        val (fromRow, fromCol, toRow, toCol) = move
        val piece = board(fromRow)(fromCol)
        board
          .updated(toRow, board(toRow).updated(toCol, piece))
          .updated(fromRow, board(fromRow).updated(fromCol, "."))
    }

}