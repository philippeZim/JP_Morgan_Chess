package ControllerTests

import Model.ChessComponent.BasicChess.StandartChess.ChessBoard
import Model.ChessComponent.ChessTrait
import Model.ChessComponent.RealChess.RealChessFacade
import ModelTests.ChessComponentTests.ControllerFakeSpy
import cController.ControllerComponent.DuoChessController.Controller
import cController.ControllerComponent.Extra.ChessContext
import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class UndoRedoSpec extends AnyWordSpec {
    "UndoInvoker" should {
        "undo and redo moves correctly" in {
            /*
            given ChessTrait = RealChessFacade()
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
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
            */

        }
    }
}
