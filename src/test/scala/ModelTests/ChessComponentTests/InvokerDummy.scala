package ModelTests.ChessComponentTests

import JP_Morgan_Chess.ControllerComponent.ControllerTrait
import JP_Morgan_Chess.ControllerComponent.Extra.Command
import Model.UndoRedoComponent.UndoRedoTrait

class InvokerDummy extends UndoRedoTrait {
    def doStep(command: Command): Unit = ???
    def newCommand (fen: String, exFen: String, controller: ControllerTrait): Command = ???
    def redoStep(): Unit = ???
    def undoStep(): Unit = ???
}
