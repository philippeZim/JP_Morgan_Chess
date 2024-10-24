import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import ChessBoard._
import Piece._

class ChessBoardSpec extends AnyWordSpec {

    
    
   "ChessBoard" should {
      "return default board" in {
         getDefaultBoard()(0)(0) should equal (Piece(PieceType.ROOK, Color.BLACK));
      }
      "return the correct board string" in {
         getBoardString(getDefaultBoard()) should be (("""    +-----+-----+-----+-----+-----+-----+-----+-----+
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
       a     b     c     d     e     f     g     h     """));
      }

      "return a correct splitline" in {
         splitLine() should be ("    +-----+-----+-----+-----+-----+-----+-----+-----+\r\n");
      }

      "return a correct pieceLine" in {
         val p = Piece(PieceType.PAWN, Color.BLACK);
         val r = Vector(p, p, p, p, p, p, p, p);
         pieceLine(r, "7") should be ("7   |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |\r\n");
      }
   }
        
}
