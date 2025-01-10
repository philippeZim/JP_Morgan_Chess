package aView.TUIComponent

import Model.ChessComponent.BasicChess.StandartChess.ChessBoard
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.DuoChessController.Controller
import util.Observer

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

class Tui(controller: ControllerTrait) extends Observer {

    controller.add(this)

    def processInputLine(input: String):Unit = {
        input match {
            case "undo" => controller.undo();
            case "redo" => controller.redo();
            case move if move.matches("(([a-h][1-8][a-h][1-8])|undo|redo)") => controller.play(ChessBoard.translateMoveStringToInt(controller.fen, move))
            case _ => println("Denk nochmal nach Bro")
        }
    }

    override def update: Unit =  {
        println(controller.createOutput())
        println("Bitte gib einen Zug ein: (Format z.B. von a1 nach c3 = a1c3)")
    }

    override def specialCase: Unit = {
        println("Welche Beförderung soll der Bauer erhalten? (Eingabemöglichkeiten: Q,q,N,n,B,b,R,r)")
        var input : String = readLine()
        while(!input.matches("^(Q|R|B|N|q|r|b|n)$")) {
            println("Alter, da steht sogar, was du eingeben kannst!!!")
            input = readLine()
        }
        controller.promotePawn(input)
    }
}
