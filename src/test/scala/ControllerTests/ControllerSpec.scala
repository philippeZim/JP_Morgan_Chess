package ControllerTests

import util.Observer
import Controller.ControllerComponent
import Controller.ControllerComponent.RealChessController.{ChessContext, Controller, State}
import _root_.Controller.ControllerComponent.RealChessController
import Model.ChessComponent.RealChess.ChessBoard
import Model.UndoRedoComponent.UndoRedoTrait
import ModelTests.ChessComponentTests.ControllerFakeSpy

import scala.language.reflectiveCalls
import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

class ControllerSpec extends AnyWordSpec with Matchers {

    "A Controller" should {
        "play a move" in {
            val startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            val controller = new Controller(startingFen, new ChessContext(), "")
            class TestObserver(var updated : Boolean, var fen: String, controller: Controller, output : String) extends Observer {
                controller.add(this)
                def isUpdated: Boolean = updated
                override def update: Unit = updated = true
                override def specialCase: Unit = fen = ""
                def specialHatFunktioniert : String = fen
            }
            val testOb = new TestObserver(false, "heyyy", controller, " ")
            controller.play(ChessBoard.moveToIndex("e2","e4"))
            testOb.isUpdated should be (true)
            val correctFen = "rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1"
            controller.fen should be (correctFen)
            controller.remove(testOb)
            controller.subscribers.isEmpty should be (true)

            val possiblePromoSoon = "rnbqkbnr/Ppppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR w KQkq - 0 5"
            val controller2 = new Controller(possiblePromoSoon, new ChessContext(), "")
            val testOb2 = new TestObserver(false, "heyyy", controller2, " ")
            controller2.play(ChessBoard.moveToIndex("a7", "b8"))
            testOb2.specialHatFunktioniert should be ("")

        }
        "detect a wrong move" in {
            val startingFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            val controller = new Controller(startingFen, new ChessContext(), "")
            controller.play(ChessBoard.moveToIndex("f5", "f6"))
            val correctFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"
            controller.fen should be(correctFen)
        }
        "detects a white win" in {
            val mattFen1 = "r1bqkbnr/ppp2Qpp/2np4/4p3/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4"
            val controller = new Controller(mattFen1, new ChessContext(), "")
            controller.play(ChessBoard.moveToIndex("f5", "f6"))
            val correctFen = "r1bqkbnr/ppp2Qpp/2np4/4p3/2B1P3/8/PPPP1PPP/RNB1K1NR b KQkq - 0 4"
            controller.fen should be(correctFen)
        }
        "detects a black win" in {
            val mattFen2 = "rnb1k1nr/pppp1ppp/8/2b1p3/4P3/1PNP4/P1P2qPP/R1BQKBNR w KQkq - 0 5"
            val controller = new Controller(mattFen2, new ChessContext(), "")
            controller.play(ChessBoard.moveToIndex("f5", "f6"))
            val correctFen = "rnb1k1nr/pppp1ppp/8/2b1p3/4P3/1PNP4/P1P2qPP/R1BQKBNR w KQkq - 0 5"
            controller.fen should be(correctFen)
        }

        "detect a Remis" in {
            val remisFen = "K3k3/8/1q6/8/8/8/8/8 w - - 0 1"
            val controller = new Controller(remisFen, new ChessContext(), "")
            controller.play(ChessBoard.moveToIndex("a8", "a7"))
            controller.context.state should be (State.remisState)
        }

        "detect a possible Promotion and ringObservers" in {
            val promotionFen = "rnbqkbnr/Ppppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR w KQkq - 0 5"
            val controller = new Controller(promotionFen, new ChessContext(), "")
            controller.play(ChessBoard.moveToIndex("a7", "b8"))
            controller.context.state should be (RealChessController.State.blackPlayingState)
        }

        "outsource promoting a pawn correctly" in {
            val promotionFen = "rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5"
            val testController = new Controller(promotionFen, new ChessContext, " ")
            testController.promotePawn("Q")
            testController.fen should be ("rQbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
        }

        "return the correct Boolean depending on if on a certain Square is a Piece of the Color to move" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.squareClicked(7)
            controller.activeSquare should be(-5)

            controller.squareClicked(52)
            controller.activeSquare should be(52)
            controller.squareClicked(36)
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")
            controller.activeSquare should be (-5)
        }

        "return the output if asked" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.play(52,36)
            controller.createOutput() should be (
                "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "8   |  r  |  n  |  b  |  q  |  k  |  b  |  n  |  r  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "7   |  p  |  p  |  p  |  p  |  p  |  p  |  p  |  p  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "6   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "5   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "4   |  .  |  .  |  .  |  .  |  P  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "3   |  .  |  .  |  .  |  .  |  .  |  .  |  .  |  .  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "2   |  P  |  P  |  P  |  P  |  .  |  P  |  P  |  P  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "1   |  R  |  N  |  B  |  Q  |  K  |  B  |  N  |  R  |\n" +
                    "    +-----+-----+-----+-----+-----+-----+-----+-----+\n" +
                    "       a     b     c     d     e     f     g     h     ")
        }

        "return correct outputs based on the game state" in {
            val controller = new Controller("rnbqkbnr/ppppp2p/5p2/6pQ/4P3/3P4/PPP2PPP/RNB1KBNR b KQkq - 1 3", new ChessContext(), "")
            controller.play(7,15)
            controller.output should be ("Schwarz wurde vernichtend geschlagen")

            val controller2 = new Controller("rnb1kbnr/pppp1ppp/8/4p3/5PPq/8/PPPPP2P/RNBQKBNR w KQkq - 1 3", new ChessContext(), "")
            controller2.play(59, 8)
            controller2.output should be ("Weiß wurde vernichtend geschlagen")

            val controller3 = new Controller("8/8/8/8/8/1q1k4/8/K7 w - - 0 1", new ChessContext(), "")
            controller3.play(1,0)
            controller3.output should be ("Remis")
        }

        "detect a non valid Move" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.play(0,1)
            controller.output should be ("Das kannste nicht machen Bro (kein legaler Zug)")
        }

        "promote correctly" in {
            val controller = new Controller("rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR w KQkq - 0 5", new ChessContext(), "")
            controller.promotePawn("Q")
            controller.fen should be ("rqbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR w KQkq - 0 5")

            val controller2 = new Controller("rnbqkbnr/Ppppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR w KQkq - 0 5", new ChessContext(), "")
            controller2.play(8,1)
            controller2.fen should be ("rPbqkbnr/1pppppp1/8/8/8/8/P1PPPPpP/RNBQKBNR b KQkq - 0 5")
        }

        "do redo and undo correctly" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.play(ChessBoard.moveToIndex("e2","e4"))
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")

            controller.undo()
            controller.fen should be ("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")

            controller.redo()
            controller.fen should be("rnbqkbnr/pppppppp/8/8/4P3/8/PPPP1PPP/RNBQKBNR b KQkq e3 0 1")
        }

        "implement squareClicked correctly" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.squareClicked(7)
            controller.activeSquare should be(-5)

            controller.squareClicked(60)
            controller.activeSquare should be(60)

            controller.fen = "r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/R3K2R w KQkq - 6 8"
            controller.squareClicked(60)
            controller.squareClicked(62)
            controller.fen should be("r3k2r/pppq1ppp/2np1n2/2b1pb2/2B1PB2/2NP1N2/PPPQ1PPP/R4RK1 b kq - 6 8")
        }

        "switch themes correctly" in {
            val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), "")
            controller.nextTheme()
            controller.current_theme = 1
        }
    }
}
