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

    for (i <- board.indices) {
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
