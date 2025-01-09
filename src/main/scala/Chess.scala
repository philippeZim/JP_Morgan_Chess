import scala.io.StdIn.readLine
import Controller.{ChessContext, Controller}
import Model.{ChessBoard, Stockfish}
import aView.Tui
import aView.GuiMain
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

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
            input = readLine()
            tui.processInputLine(input)
        }


    }
}
