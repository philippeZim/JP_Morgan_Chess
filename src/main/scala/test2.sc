def coordinatesToIndex(coord: String): Int = {
    (coord.charAt(0).toInt - 97) + 8 * (8 - (coord.charAt(1).toInt - 48));
}

def moveToIndex(from: String, to: String): (Int, Int) = {
    (coordinatesToIndex(from), coordinatesToIndex(to));
}

val t1 = coordinatesToIndex("a1");

PseudoMoves.onBoard(ChessBoard.coordinatesToIndex("a4"), 1, 2)

def indexToCoordinate(index: Int): String = {
    require(index >= 0 && index <= 63, "Index must be between 0 and 63.")

    val file = ('a' + (index % 8)).toChar   // Convert column (file) number to letter
    val rank = 9 - ((index / 8) + 1)          // Convert row number to rank

    s"$file$rank"                           // Return coordinate as string
}

indexToCoordinate(36)

val a = List((36,27), (42,34), (48,32), (48,40), (49,33), (51,35), (51,43), (53,37), (54,38), (54,46), (55,39), (55,47))

a.foreach((e1, e2) => println((indexToCoordinate(e1), indexToCoordinate(e2))))

println(indexToCoordinate(0))  // Outputs: a1
println(indexToCoordinate(7))  // Outputs: h1
println(indexToCoordinate(8))  // Outputs: a2
println(indexToCoordinate(63)) // Outputs: h8





val a2 = List((8,24), (8,16), (9,25), (9,17), (11,27), (11,19), (14,30), (14,22), (15,31), (15,23), (26,34), (26,35), (37,45), (37,46))
val a3 = List((8,16), (8,24), (9,17), (9,25), (26,34), (26,35), (37,45), (37,46), (14,22), (14,30), (15,23), (15,31))

a2.foreach((e1, e2) => println((indexToCoordinate(e1), indexToCoordinate(e2))))
a3.foreach((e1, e2) => println((indexToCoordinate(e1), indexToCoordinate(e2))))









