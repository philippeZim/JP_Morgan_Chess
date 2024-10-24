

object ChessBoard {


  def splitLine(): String = {
    "    " + "+-----" * 8 + "+\n"
  }

  def pieceLine(line: Vector[Piece], columnNumber: String): String = {
    s"$columnNumber   |" + line.map(el => s"  ${el.toString()}  |").mkString("") + "\n";
  }

  def getBoardString(board: Vector[Vector[Piece]]) : String = {
    def sub(ind: Int, acc: String): String = {
      if (ind == 8) {
        val letters = "abcdefgh";
        return acc + splitLine() + "       " + letters.map(el => s"$el     ").mkString("");
      }
      sub(ind + 1, acc + splitLine() + pieceLine(board(ind), s"${8 - ind}"));

    }

    sub(0, "")
  }

  def getDefaultBoard(): Vector[Vector[Piece]] = {
    val p = Piece(PieceType.PAWN, Color.BLACK);
    val r = Piece(PieceType.ROOK, Color.BLACK);
    val n = Piece(PieceType.KNIGHT, Color.BLACK);
    val b = Piece(PieceType.BISHOP, Color.BLACK);
    val q = Piece(PieceType.QUEEN, Color.BLACK);
    val k = Piece(PieceType.KING, Color.BLACK);

    val P = Piece(PieceType.PAWN, Color.WHITE);
    val R = Piece(PieceType.ROOK, Color.WHITE);
    val N = Piece(PieceType.KNIGHT, Color.WHITE);
    val B = Piece(PieceType.BISHOP, Color.WHITE);
    val Q = Piece(PieceType.QUEEN, Color.WHITE);
    val K = Piece(PieceType.KING, Color.WHITE);
    val point = Piece(PieceType.EMPTY, Color.EMPTY);
    val board: Vector[Vector[Piece]] = Vector(
      Vector(r, n, b, q, k, b, n, r),
      Vector(p, p, p, p, p, p, p, p),
      Vector(point, point, point, point, point, point, point, point),
      Vector(point, point, point, point, point, point, point, point),
      Vector(point, point, point, point, point, point, point, point),
      Vector(point, point, point, point, point, point, point, point),
      Vector(P, P, P, P, P, P, P, P),
      Vector(R, N, B, Q, K, B, N, R)
    )
    board
  }

  def fenToBoard(fen: String): Vector[Vector[Piece]] = {
    val res: Vector[List[Piece]] = Vector(List() * 8)
    fen.split(" ")(0).map(el => {
      
    })
  }


}
