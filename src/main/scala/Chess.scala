import scala.io.StdIn.readLine

import Controller.Controller
import Model.ChessBoard
import aView.Tui

object Chess {
    val controller = new Controller("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    val tui = new Tui(controller);
    controller.notifyObservers;

    
    def readInput(input: String): Unit = {
        input match {
            case "q" => ()
            case _ => {
                println("Bitte gib einen Zug ein: (Format z.B. von a1 nach c3 = a1c3)");
                val newInput = readLine();
                tui.processInputLine(newInput);
                readInput(newInput);
            }
        }
    }
    def main(args: Array[String]): Unit = {
        readInput("");
    }
}
