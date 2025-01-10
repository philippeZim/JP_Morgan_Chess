package cController.StateComponent.xmlSolution

import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.Extra.State
import cController.StateComponent.{Data_Wrapper, StateTrait}

import java.io.*

class xmlApi (controller : ControllerTrait) extends StateTrait {
    def to() = {
        <box>
            <fen>
                {controller.fen}
            </fen>
            <state>
                {controller.context.state.ordinal}
            </state>
        </box>

    }

    def from(data: Data_Wrapper) : Unit = {
        val node : scala.xml.Node = data.getNode()
        controller.fen = (node \ "fen").text
        val state = (node \ "state").text.toInt
        controller.context.state = State.fromOrdinal(state)
    }

    def printTo() = {
        val writer = new PrintWriter(new File("GameState.xml"))
        writer.write(to().toString())
        writer.close()
    }
}
