package aView

import Controller.Controller
import util.Observer

import scala.io.StdIn.readLine

class Tui(controller: Controller) extends Observer{

    controller.add(this)

    def processInputLine(input: String):Unit = {
        if (!input.matches("[a-h][1-8][a-h][1-8]")) {
            println("Denk nochmal nach Bro")
        } else {
            controller.play(input)
        }
    }

    override def update: Unit =  println(controller.createOutput())

    override def specialCase: Unit = {
        println("Welche Beförderung soll der Bauer erhalten? (Syntaxbeispiel: Knight)")
        var input : String = readLine()
        while(!input.matches("^(Queen|Rook|Bishop|Knight)$")) {
            println("Das Piece gibt's net du Honk")
            input = readLine()
        }
        controller.promotePawn(input)
    }
}
