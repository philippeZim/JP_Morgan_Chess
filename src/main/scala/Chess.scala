import JP_Morgan_Chess.ChessComponent.ChessTrait
import JP_Morgan_Chess.ChessComponent.Model.DevourChess.BasicChessComponent.StandartChess.ChessBoard
import JP_Morgan_Chess.ControllerComponent.ControllerTrait
import JP_Morgan_Chess.ControllerComponent.DuoChessController.Controller
import JP_Morgan_Chess.ControllerComponent.Extra.ChessContext
import JP_Morgan_Chess.aView.GUIComponent.GuiMain
import JP_Morgan_Chess.aView.TUIComponent.Tui
import scala.io.StdIn.readLine
import Model.ChessComponent.Default.given

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Chess {
    val gameMode: ChessTrait = summon[ChessTrait]
    val controller : ControllerTrait = summon[ControllerTrait]
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
