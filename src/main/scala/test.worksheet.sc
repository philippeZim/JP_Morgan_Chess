object ChessBoard {


  val start_board: Vector[Vector[String]] = Vector(
    Vector("r", "n", "b", "q", "k", "b", "n", "r"),
    Vector("p", "p", "p", "p", "p", "p", "p", "p"),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector("P", "P", "P", "P", "P", "P", "P", "P"),
    Vector("R", "N", "B", "Q", "K", "B", "N", "R")
  )


  def printBoard(board: Vector[Vector[String]]): Unit = {
    val letters = "abcdefgh"
    val horizontalLine = "   " + "+-----" * 8 + "+"

    for (i <- 0 until 8) {
      println(horizontalLine)
      print(s"${8 - i}  |")
      for (cell <- board(i)) {
        print(s"  $cell  |")
      }
      println()
    }
    println(horizontalLine)
    println("    " + letters.map(letter => s"  $letter   ").mkString(""))
    println()
  }
}


val startingBoard: Vector[Vector[Int]] = Vector(
    Vector(-2, -3, -4, -5, -6, -4, -3, -2),
    Vector(-1, -1, -1, -1, -1, -1, -1, -1),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(1, 1, 1, 1, 1, 1, 1, 1),
    Vector(2, 3, 4, 5, 6, 4, 3, 2)
)

def convert_board(board: Vector[Vector[Int]]): Vector[Vector[String]] = {
    val pieceMap: Map[Int, String] = Map(
        0 -> ".",
        1 -> "P",
        2 -> "R",
        3 -> "N",
        4 -> "B",
        5 -> "Q",
        6 -> "K",
        -1 -> "p",
        -2 -> "r",
        -3 -> "n",
        -4 -> "b",
        -5 -> "q",
        -6 -> "k"
    )
    def unpack_fun(el:Int): String = {
        pieceMap.get(el) match {
            case None => "?"
            case Some(value) => value
        }
    }
    board.map(row => row.map(el => unpack_fun(el)))
}

ChessBoard.printBoard(convert_board(startingBoard))



