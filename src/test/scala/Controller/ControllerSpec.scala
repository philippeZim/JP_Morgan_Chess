package Controller

import Model.{ChessBoard, LegalMoves, PseudoMoves}
import aView.Tui
import util.Observer

import scala.language.reflectiveCalls
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

    "A Controller" should {
        "notify its Observer after playing a move" in {
            val startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            val controller = new Controller(startingFen)
            /*val observer = new Observer {
                var updated: Boolean = false
                def isUpdated: Boolean = updated
                override def update: Unit = updated = true
            } */
            val tui = new Tui(controller)
            controller.play("e2e4")
            tui.update should be(true)
            val correctFen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"
            controller.fen should be (correctFen)
        }
    }
}
