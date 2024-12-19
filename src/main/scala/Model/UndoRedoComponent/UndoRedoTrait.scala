package Model.UndoRedoComponent

import Controller.ControllerComponent.ControllerTrait

trait UndoRedoTrait {
    def doStep(command: Command) : Unit

    def undoStep() : Unit
    
    def redoStep() : Unit

    def newCommand(fen : String, exFen : String, controller: ControllerTrait) : Command
}
