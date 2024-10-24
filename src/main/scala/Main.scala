@main
def main(): Unit = {
  //ScholarsMate.print_moves(ChessBoard.start_board, ScholarsMate.moves)
  print("")
  print(ChessBoard.getBoardString(ChessBoard.getDefaultBoard()));
  print("!\n\n")

  val a = ("""    +-----+-----+-----+-----+-----+-----+-----+-----+
8   |  r  |  n  |  b  |  q  |  k  |  b  |  n  |  r  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
7   |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
6   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
5   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
4   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
3   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
2   |  P  |  P  |  P  |  P  |  P  |  P  |  P  |  P  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
1   |  R  |  N  |  B  |  Q  |  K  |  B  |  N  |  R  |
    +-----+-----+-----+-----+-----+-----+-----+-----+
       a     b     c     d     e     f     g     h     """);
  
  if (a == ChessBoard.getBoardString(ChessBoard.getDefaultBoard())) {
    print("Ja");
  } else {
    print("Nein\n");
  }

  val b = ChessBoard.getBoardString(ChessBoard.getDefaultBoard())

  if (a.length != b.length) {
    println(s"The lengths of the two strings are different: a has ${a.length} characters, b has ${b.length} characters.\n")
  }
  

  val c1: Array[Char] = a.toCharArray();
  val c2: Array[Char] = b.toCharArray();

  for (i <- 0 to a.length() - 1) {
    if (c1(i).toInt != c2(i).toInt) {
      println(i + ": " + c1(i).toInt + "," + c2(i).toInt);
    }
  }


}