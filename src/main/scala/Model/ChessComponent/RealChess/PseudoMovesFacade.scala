package Model.ChessComponent.RealChess

object PseudoMovesFacade {
    val moveObject = PseudoMoves

    def subSystemOperation(fen: String): List[(Int, Int)] = {
        moveObject.pseudoVerticalMoves(
            moveObject.pseudoHorizontalMoves(
                moveObject.pseudoKingMoves(
                    moveObject.pseudoKnightMoves(
                        moveObject.pseudoPawnMoves(fen), fen), fen), fen), fen);
    }
}
