package Model.UndoRedoComponent

import Controller.Controller

class UndoInvoker extends UndoRedoTrait {
    private var undoStack: List[Command]= Nil
    private var redoStack: List[Command]= Nil
    def doStep(command: Command) = {
        undoStack = command::undoStack
        redoStack = Nil
        command.doStep
    }
    def undoStep  = {
        undoStack match {
            case  Nil =>
            case head::stack => {
                head.undoStep
                undoStack=stack
                redoStack= head::redoStack
            }
        }
    }
    
    def redoStep = {
        redoStack match {
            case Nil =>
            case head :: stack => {
                head.redoStep
                redoStack = stack
                undoStack = head :: undoStack
            }
        }
    }

    def newCommand(fen : String, exFen : String, controller: Controller) : Command = {
        new SetCommand(fen : String, exFen : String, controller: Controller)
    }
}

