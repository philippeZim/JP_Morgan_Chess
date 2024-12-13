import scala.io.StdIn.readLine
import Controller.{ChessContext, Controller}
import Model.ChessBoard
import aView.Tui
import aView.GuiMain

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Chess {
    val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), ChessBoard.getBoardString(ChessBoard.getDefaultBoard()));
    val tui = new Tui(controller)
    controller.notifyObservers


    def main(args: Array[String]): Unit = {

        GuiMain.setController(controller)
        Future {
            GuiMain.main(args)
        }

        var input: String = ""
        while (input != "end") {
            println("Bitte gib einen Zug ein: (Format z.B. von a1 nach c3 = a1c3)")
            input = readLine()
            tui.processInputLine(input)
        }
    }
}
