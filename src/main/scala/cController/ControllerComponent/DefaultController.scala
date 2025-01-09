package cController.ControllerComponent

import Model.ChessComponent.ChessTrait
import cController.ControllerComponent.DuoChessController.Controller
import Model.ChessComponent.DefaultChessRules.given
import cController.ControllerComponent.Extra.ChessContext

class DefaultController {

    val gameMode: ChessTrait = summon[ChessTrait]
    given ControllerTrait = Controller(using gameMode, gameMode.getDefaultFen(), new ChessContext, "")
}
