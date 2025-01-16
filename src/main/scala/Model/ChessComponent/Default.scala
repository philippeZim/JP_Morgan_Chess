package Model.ChessComponent

import Model.ChessComponent.RealChess.RealChessFacade

object Default {
    given ChessTrait = RealChessFacade()
}
