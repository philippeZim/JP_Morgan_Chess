package aView

import Controller.Controller
import util.Observer

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
}
