package aViewTests

import JP_Morgan_Chess.ControllerComponent.DuoChessController.Controller
import JP_Morgan_Chess.ControllerComponent.Extra.ChessContext
import JP_Morgan_Chess.aView.TUIComponent.Tui
import scala.language.reflectiveCalls
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class TuiSpec  extends AnyWordSpec with Matchers{

    "Chess Tui" should {
        "interpret move e2 to e4 correctly" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            val tui = new Tui(controller)
            tui.processInputLine("e2e4")
            controller.fen should be("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")
        }
        "interpret a wrong move hallo correctly" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            val tui = new Tui(controller)
            tui.processInputLine("hallo")
            controller.fen should be("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
        }
    }

}
