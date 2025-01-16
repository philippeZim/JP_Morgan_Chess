import Model.ChessComponent.BasicChessComponent.StandartChess.ChessBoard

import scala.io.StdIn.readLine
import Model.ChessComponent.ChessTrait
import aView.GUIComponent.GuiMain
import aView.TUIComponent.Tui
import Model.ChessComponent.RealChess.RealChessFacade
import cController.ControllerComponent.Extra.ChessContext
import cController.ControllerComponent.RealChessController.Controller

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Chess {
    given ChessTrait = RealChessFacade()
    val controller = ChessModule.provideDuoChessXML()
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
