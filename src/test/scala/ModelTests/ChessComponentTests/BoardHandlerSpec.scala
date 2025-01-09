package ModelTests.ChessComponentTests

import JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent.StandartChess.{ChessBoard, Color, EmptySquareHandler, EnemySquareHandler, OnBoardHandler, Piece, PseudoMoves}
import Model.ChessComponent
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class BoardHandlerSpec extends AnyWordSpec {
    "OnBoardHandler" should {
        "return false if he doesn't get another Handler" in {
            val handler = new OnBoardHandler(None)
            handler.handle(7, 6, 0, ChessBoard.getDefaultBoard(), Color.BLACK) should be (false)
        }
    }
    "EmptySquareHandler" should {
        "return false if he doesn't get another Handler" in {
            val handler = new EmptySquareHandler(None)
            handler.handle(7, 6, 0, ChessBoard.getDefaultBoard(), Color.BLACK) should be(false);
        }
    }
}
