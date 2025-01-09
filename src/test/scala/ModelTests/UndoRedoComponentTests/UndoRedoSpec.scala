package ModelTests.UndoRedoComponentTests

import JP_Morgan_Chess.ControllerComponent.DuoChessController.Controller
import JP_Morgan_Chess.ControllerComponent.Extra.ChessContext
import ModelTests.ChessComponentTests.ControllerFakeSpy
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class UndoRedoSpec extends AnyWordSpec {
    "UndoInvoker" should {
        "undo and redo moves correctly" in {
            val controller = new ControllerStub("0-0")
            controller.play(1, 2) //e2e4
            controller.play(3, 4) //e7e5
            controller.fen should be ("3-4")

            controller.undo()
            controller.fen should be ("1-2")

            controller.undo()
            controller.fen should be ("0-0")

            controller.undo()
            controller.fen should be ("0-0")

            controller.redo()
            controller.fen should be ("1-2")

            controller.redo()
            controller.fen should be ("3-4")

            controller.redo()
            controller.fen should be ("3-4")


        }
    }
}
