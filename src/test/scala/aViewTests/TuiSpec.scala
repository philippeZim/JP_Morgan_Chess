package aViewTests

import Model.ChessComponent.RealChess.RealChessFacade

import scala.language.reflectiveCalls
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec
import aView.TUIComponent.Tui
import cController.ControllerComponent.Extra.ChessContext
import cController.ControllerComponent.DuoChessController.Controller
import cController.ControllerComponent.StateComponent.xmlSolution.XMLApi

class TuiSpec  extends AnyWordSpec with Matchers{

    "Chess Tui" should {
        "interpret move e2 to e4 correctly" in {
            val controller = new Controller(new RealChessFacade, new XMLApi, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            val tui = new Tui(controller)
            tui.processInputLine("e2e4")
            controller.fen should be("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")
        }
        "interpret a wrong move hallo correctly" in {
            val controller = new Controller(new RealChessFacade, new XMLApi, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            val tui = new Tui(controller)
            tui.processInputLine("hallo")
            controller.fen should be("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
        }
    }

}
