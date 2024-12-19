package Model.UndoRedoComponent

import Controller.ControllerComponent.ControllerTrait
import Model.UndoRedoComponent.Command

trait Command {
    def doStep:Unit
    def undoStep:Unit
    def redoStep:Unit
}


class SetCommand(fen : String, exFen : String, controller: ControllerTrait) extends Command {
    override def doStep: Unit = controller.fen = fen
    override def undoStep: Unit = controller.fen = exFen
    override def redoStep: Unit =  controller.fen = fen
}
