package Controller.ControllerComponent


import Model.ChessComponent.Remis
import util.Observable;

enum State:
    case whitePlayingState, blackPlayingState, whiteWonState, blackWonState, remisState

class ChessContext {
    var state = State.whitePlayingState;

    def handle(event: Event): Unit = {
        val color = event.fen.split(" ")(1)
        if (event.remis) {
            state = State.remisState
        } else {
            color match {
                case "w" => {
                    if (event.noMoves) {
                        state = State.blackWonState
                    } else {
                        state = State.blackPlayingState
                    }
                }
                case "b" => {
                    if (event.noMoves) {
                        state = State.whiteWonState
                    } else {
                        state = State.whitePlayingState
                    }
                }
            }
        }
    }
}
