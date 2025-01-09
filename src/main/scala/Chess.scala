import scala.io.StdIn.readLine
import Model.ChessComponent.BasicChess.StandartChess.ChessBoard
import Model.ChessComponent.{ChessModule, ChessTrait}
import aView.GUIComponent.GuiMain
import aView.TUIComponent.Tui
import cController.ControllerComponent.RealChessController.{ChessContext, Controller}
import com.google.inject.{Guice, Key}
import com.google.inject.name.Names

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

object Chess {
    val injector = Guice.createInjector(new ChessModule())
    val gameMode : ChessTrait = injector.getInstance(Key.get(classOf[ChessTrait], Names.named("DevourChess")))
    val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", new ChessContext(), ChessBoard.getBoardString(ChessBoard.getDefaultBoard()), gameMode);
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
