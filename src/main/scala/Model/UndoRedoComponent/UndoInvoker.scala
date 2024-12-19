package Model.UndoRedoComponent

import Controller.ControllerComponent.{Controller, ControllerTrait}

class UndoInvoker extends UndoRedoTrait {
    private var undoStack: List[Command]= Nil
    private var redoStack: List[Command]= Nil
    def doStep(command: Command) = {
        undoStack = command::undoStack
        redoStack = Nil
        command.doStep
    }
    def undoStep() : Unit = {
        undoStack match {
            case  Nil =>
            case head::stack => {
                head.undoStep
                undoStack=stack
                redoStack= head::redoStack
            }
        }
    }
    
    def redoStep() : Unit= {
        redoStack match {
            case Nil =>
            case head :: stack => {
                head.redoStep
                redoStack = stack
                undoStack = head :: undoStack
            }
        }
    }

    def newCommand(fen : String, exFen : String, controller: ControllerTrait) : Command = {
        new SetCommand(fen, exFen, controller)
    }
}

