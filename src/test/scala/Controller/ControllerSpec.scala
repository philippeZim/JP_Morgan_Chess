package Controller

import Model.{ChessBoard, LegalMoves, PseudoMoves}
import aView.Tui
import util.Observer
import Controller.ChessContext

import scala.language.{reflectiveCalls}
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

    "A Controller" should {
        "play a move" in {
            val startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            val controller = new Controller(startingFen, new ChessContext(), "")
            class TestObserver(var updated : Boolean, controller: Controller) extends Observer {
                controller.add(this)
                def isUpdated: Boolean = updated
                override def update: Unit = updated = true
            }
            val testOb = new TestObserver(false, controller)
            controller.play("e2e4")
            testOb.isUpdated should be (true)
            val correctFen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"
            controller.fen should be (correctFen)
            controller.remove(testOb)
            controller.subscribers.isEmpty should be (true)
        }
        "detect a wrong move" in {
            val startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            val controller = new Controller(startingFen, new ChessContext(), "")
            controller.play("f5f6")
            val correctFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            controller.fen should be(correctFen)
        }
        "detects a white win" in {
            val mattFen1 = "r1bqkbnr/ppp2Qpp/2np4/4p3/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4"
            val controller = new Controller(mattFen1, new ChessContext(), "")
            controller.play("f5f6")
            val correctFen = "r1bqkbnr/ppp2Qpp/2np4/4p3/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4"
            controller.fen should be(correctFen)
        }
        "detects a black win" in {
            val mattFen2 = "rnb1k1nr/pppp1ppp/8/2b1p3/4P3/1PNP4/P1P2qPP/R1BQKBNR w KQkq - 0 5"
            val controller = new Controller(mattFen2, new ChessContext(), "")
            controller.play("f5f6")
            val correctFen = "rnb1k1nr/pppp1ppp/8/2b1p3/4P3/1PNP4/P1P2qPP/R1BQKBNR w KQkq - 0 5"
            controller.fen should be(correctFen)
        }

        "detect a Remis" in {
            val remisFen = "K3k3/8/1q6/8/8/8/8/8 w KQkq - 0 1"
            val controller = new Controller(remisFen, new ChessContext(), "")
            controller.play("a8a7")
            controller.context.state should be (Controller.State.remisState)
        }
    }
}
