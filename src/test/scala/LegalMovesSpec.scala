import org.scalatest.wordspec.AnyWordSpec
import org.scalatest.matchers.should.Matchers._
import LegalMoves._

class LegalMovesSpec extends AnyWordSpec {
    "Legal Moves " should {
        "return all legal moves for fens" in {
            val expected: List[(Int, Int)] = List((24, 9), (24, 18), (24, 34), (55, 7), (55, 15), (55, 23), (55, 31), (55, 39), (55, 47), (55, 54), (55, 53), (55, 52), (55, 51), (55, 63), (50, 43), (50, 51), (50, 59), (61, 45), (61, 53), (61, 63), (61, 62), (61, 60), (61, 59), (58, 44), (58, 51), (58, 49), (37, 28), (30, 22), (41, 33), (40, 32)).sorted;
            val fen = "rn1kr3/8/p1p5/Np1bbpP1/5P2/PP6/b1K4R/2B2R2 w - - 5 65";
            LegalMoves.getAllLegalMoves(fen).sorted should be(expected);
        }
    }
}
