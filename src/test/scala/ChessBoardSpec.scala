import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import ChessBoard._
import Piece._

class ChessBoardSpec extends AnyWordSpec {


    "ChessBoard" should {
        "return default board" in {
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
            getDefaultBoard() should equal(board);
        }
        "return the correct board string" in {
            getBoardString(getDefaultBoard()) should be((
              "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "8   |  r  |  n  |  b  |  q  |  k  |  b  |  n  |  r  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "7   |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "6   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "5   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "4   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "3   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "2   |  P  |  P  |  P  |  P  |  P  |  P  |  P  |  P  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "1   |  R  |  N  |  B  |  Q  |  K  |  B  |  N  |  R  |\n" +
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                "       a     b     c     d     e     f     g     h     "
              ));
        }

        "return a correct splitline" in {
            splitLine() should be("    +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        }

        "return a correct pieceLine" in {
            val p = Piece(PieceType.PAWN, Color.BLACK);
            val r = Vector(p, p, p, p, p, p, p, p);
            pieceLine(r, "7") should be("7   |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |\n");
        }

        "return a correct Vector[Vector[Piece]] board given a FEN" in {
            val testFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
            ChessBoard.fenToBoard(testFen) should equal(ChessBoard.getDefaultBoard());
        }
    }

}
