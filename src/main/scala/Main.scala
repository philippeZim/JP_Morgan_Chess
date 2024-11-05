@main def main(): Unit = {
    //println(ScholarsMate.movesToString(ChessBoard.getDefaultBoard(), ScholarsMate.moves));



    val b = ChessBoard.fenToBoard("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1")
    println(ChessBoard.boardToFen(b))
}
