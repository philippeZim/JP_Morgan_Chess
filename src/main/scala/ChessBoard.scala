import scala.annotation.tailrec

object ChessBoard {


    def splitLine(): String = {
        "    " + "+-----" * 8 + "+\n"
    }

    def pieceLine(line: Vector[Piece], columnNumber: String): String = {
        s"$columnNumber   |" + line.map(el => s"  ${el.toString()}  |").mkString("") + "\n";
    }

    def getBoardString(board: Vector[Vector[Piece]]): String = {
        @tailrec
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
        @tailrec
        def sub2(acc: List[Piece], n: Int): List[Piece] = {
            n match {
                case el if el > 0 => sub2(Piece(PieceType.EMPTY, Color.EMPTY) :: acc, n - 1)
                case _ => acc
            }
        }

        @tailrec
        def sub(fen: List[Char], cur: List[Piece], acc: List[List[Piece]]): List[List[Piece]] = {
            fen match {
                case Nil => cur :: acc
                case h :: t => {
                    h match {
                        case '/' => sub(t, List(), cur :: acc)
                        case 'p' => sub(t, Piece(PieceType.PAWN, Color.BLACK) :: cur, acc)
                        case 'r' => sub(t, Piece(PieceType.ROOK, Color.BLACK) :: cur, acc)
                        case 'n' => sub(t, Piece(PieceType.KNIGHT, Color.BLACK) :: cur, acc)
                        case 'b' => sub(t, Piece(PieceType.BISHOP, Color.BLACK) :: cur, acc)
                        case 'q' => sub(t, Piece(PieceType.QUEEN, Color.BLACK) :: cur, acc)
                        case 'k' => sub(t, Piece(PieceType.KING, Color.BLACK) :: cur, acc)
                        case 'P' => sub(t, Piece(PieceType.PAWN, Color.WHITE) :: cur, acc)
                        case 'R' => sub(t, Piece(PieceType.ROOK, Color.WHITE) :: cur, acc)
                        case 'N' => sub(t, Piece(PieceType.KNIGHT, Color.WHITE) :: cur, acc)
                        case 'B' => sub(t, Piece(PieceType.BISHOP, Color.WHITE) :: cur, acc)
                        case 'Q' => sub(t, Piece(PieceType.QUEEN, Color.WHITE) :: cur, acc)
                        case 'K' => sub(t, Piece(PieceType.KING, Color.WHITE) :: cur, acc)
                        case el if el.isDigit => sub(t, sub2(cur, el.toInt - 48), acc)
                    }
                }
            }
        }

        sub(fen.split(" ")(0).toList, List(), List()).reverse.map(_.toVector).toVector
    }


}
