package ModelTests.UndoRedoComponentTests

import Controller.{ChessContext, Controller}
import ModelTests.ChessComponentTests.ControllerFakeStub
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class UndoRedoSpec extends AnyWordSpec {
    "UndoInvoker" should {
        "undo and redo moves correctly" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.play(52, 36) //e2e4
            controller.play(12, 28) //e7e5
            controller.fen should be ("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2")

            controller.undo()
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")

            controller.undo()
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")

            controller.undo()
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")

            controller.redo()
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")

            controller.redo()
            controller.fen should be ("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2")

            controller.redo()
            controller.fen should be ("rnbqkbnr/pppp1ppp/8/4p3/4P3/8/PPPP1PPP/RNBQKBNR w KQkq e6 0 2")


        }
    }
}
