package ModelTests.ChessComponentTests

import Model.UndoRedoComponent.UndoRedoTrait
import cController.ControllerComponent.ControllerTrait
import cController.ControllerComponent.RealChessController.Command

class InvokerDummy extends UndoRedoTrait {
    def doStep(command: Command): Unit = ???
    def newCommand (fen: String, exFen: String, controller: ControllerTrait): Command = ???
    def redoStep(): Unit = ???
    def undoStep(): Unit = ???
}
