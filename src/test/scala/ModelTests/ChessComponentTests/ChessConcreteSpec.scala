package ModelTests.ChessComponentTests

import Model.ChessComponent.BasicChessComponent.StandartChess.ChessBoard
import Model.ChessComponent.RealChess.ChessFacade
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class ChessConcreteSpec extends AnyWordSpec {
    "ChessConcreteSpec" should {
        "return the correct Boolean depending on if on a certain Square is a Piece of the Color to move and return correct castling Moves" in {
            val controllerFake = new ControllerFakeSpy("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
            controllerFake.squareClicked(7)
            controllerFake.activeSquare should be (-5)

            controllerFake.squareClicked(60)
            controllerFake.activeSquare should be (60)

            controllerFake.fen = "r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/R3K2R w KQkq - 6 8"
            controllerFake.squareClicked(60)
            controllerFake.squareClicked(62)
            controllerFake.fen should be ("r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/R4RK1 b kq - 6 8")

            controllerFake.fen = "r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/R3K2R w KQkq - 6 8"
            controllerFake.squareClicked(60)
            controllerFake.squareClicked(58)
            controllerFake.fen should be("r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/2KR3R b kq - 6 8")

            controllerFake.fen = "r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/2KR3R b kq - 7 8"
            controllerFake.squareClicked(4)
            controllerFake.squareClicked(2)
            controllerFake.fen should be("2kr3r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/2KR3R w - - 7 9")

            controllerFake.fen = "r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/2KR3R b kq - 7 8"
            controllerFake.squareClicked(4)
            controllerFake.squareClicked(6)
            controllerFake.fen should be("r4rk1/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/2KR3R w - - 7 9")
        }

        "return a correct fen if asked to promote" in {
            val controllerFake = new ControllerFakeSpy("rnbqkbnr/Ppppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR w KQkq - 0 5")
            controllerFake.play(8, 1)
            controllerFake.fen should be ("rQbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5") //Hard  coded, dass es ne Queen werden soll
        }

        "return false if isColorPiece gets a wrong string" in {
            val controllerFake = new ControllerFakeSpy("rQbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR k KQkq - 0 5")
            controllerFake.squareClicked(60)
            controllerFake.activeSquare should be (-5)
        }

        "return detect non-Legal moves" in {
            val controllerFake = new ControllerFakeSpy("rQbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
            controllerFake.play(30,33)
            controllerFake.play(31,32)
            controllerFake.counter should be (2)
        }
    }
}
