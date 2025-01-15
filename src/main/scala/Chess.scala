import scala.io.StdIn.readLine
import Model.ChessComponent.BasicChessComponent.StandartChess.{BasicChessFacade, ChessBoard}
import Model.ChessComponent.ChessTrait
import aView.GUIComponent.GuiMain
import aView.TUIComponent.Tui
import cController.ControllerComponent.ControllerTrait
import com.google.inject.{Guice, Key}
import com.google.inject.name.Names
import cController.ControllerComponent.DuoChessController.Controller
import cController.ControllerComponent.SoloChessController.EngineController
import cController.ControllerComponent.Extra.ChessContext

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Chess {
    val injector = Guice.createInjector(new ChessModule())
    val controller : ControllerTrait = injector.getInstance(Key.get(classOf[ControllerTrait], Names.named("DuoChess")))
    //val controller = Controller(gameMode, "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext, gameMode.getBoardString(gameMode.getDefaultBoard()))
    val tui = new Tui(controller)
    controller.notifyObservers

    def main(args: Array[String]): Unit = {
        GuiMain.setController(controller)
        Future {
            GuiMain.main(args)
        }

        var input: String = ""
        while (input != "end") {
            input = readLine()
            tui.processInputLine(input)
        }
    }
}
