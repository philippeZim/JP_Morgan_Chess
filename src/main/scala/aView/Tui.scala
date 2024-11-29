package aView

import Controller.Controller
import util.Observer

import scala.io.StdIn.readLine

class Tui(controller: Controller) extends Observer{

    controller.add(this)

    def processInputLine(input: String):Unit = {
        if (!input.matches("(([a-h][1-8][a-h][1-8])|undo|redo)")) {
            println("Denk nochmal nach Bro")
        } else if(input == "undo"){
            controller.undo()
        } else if(input == "redo") {
            controller.redo()
        } else {
            controller.play(input)
        }
    }

    override def update: Unit =  println(controller.createOutput())

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
