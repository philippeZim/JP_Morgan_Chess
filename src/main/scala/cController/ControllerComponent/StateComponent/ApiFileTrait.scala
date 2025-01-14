package cController.ControllerComponent.StateComponent

import cController.ControllerComponent.Extra.{ChessContext, State}

trait ApiFileTrait {
    def to(context: ChessContext, fen: String): DataWrapper
    
    def from(data : DataWrapper) : (String, State)
    
    def printTo(context : ChessContext, fen : String) : Unit
}
