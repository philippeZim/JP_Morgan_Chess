package Model.UndoRedoComponent

import Controller.ControllerComponent.ControllerTrait
import Model.UndoRedoComponent.StackSolution.Command

trait UndoRedoTrait {
    def doStep(command: Command) : Unit

    def undoStep() : Unit
    
    def redoStep() : Unit

    def newCommand(fen : String, exFen : String, controller: ControllerTrait) : Command
}
