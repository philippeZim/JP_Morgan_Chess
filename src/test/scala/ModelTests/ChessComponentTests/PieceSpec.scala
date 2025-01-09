package ModelTests.ChessComponentTests

import JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent.StandartChess.{Color, Piece, PieceType}
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class PieceSpec extends AnyWordSpec {
    "Piece" should {
        "have a correct string representation" in {
            val p1 = Piece(PieceType.PAWN, Color.BLACK);
            val p2 = Piece(PieceType.KNIGHT, Color.WHITE);
            val p3 = Piece(PieceType.QUEEN, Color.BLACK);
            p1.toString should be("p");
            p2.toString should be("N");
            p3.toString should be("q");
            Piece(null, null).toString should be("?");
        }

    }
}
