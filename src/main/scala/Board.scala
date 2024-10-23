class Board (current: Vector[Vector[Square]]) {
    current = Vector(
        Vector(new Square(8, "a", null), new Square(8, "b", null), new Square(8, "c", null), new Square(8, "d", null), new Square(8, "e", null), new Square(8, "f", null), new Square(8, "g", null), new Square(8, "h", null)),
        Vector(new Square(7, "a", null), new Square(7, "b", null), new Square(7, "c", null), new Square(7, "d", null), new Square(7, "e", null), new Square(7, "f", null), new Square(7, "g", null), new Square(7, "h", null)),
        Vector(new Square(6, "a", null), new Square(6, "b", null), new Square(6, "c", null), new Square(6, "d", null), new Square(6, "e", null), new Square(6, "f", null), new Square(6, "g", null), new Square(6, "h", null)),
        Vector(new Square(5, "a", null), new Square(5, "b", null), new Square(5, "c", null), new Square(5, "d", null), new Square(5, "e", null), new Square(5, "f", null), new Square(5, "g", null), new Square(5, "h", null)),
        Vector(new Square(4, "a", null), new Square(4, "b", null), new Square(4, "c", null), new Square(4, "d", null), new Square(4, "e", null), new Square(4, "f", null), new Square(4, "g", null), new Square(4, "h", null)),
        Vector(new Square(3, "a", null), new Square(3, "b", null), new Square(3, "c", null), new Square(3, "d", null), new Square(3, "e", null), new Square(3, "f", null), new Square(3, "g", null), new Square(3, "h", null)),
        Vector(new Square(2, "a", null), new Square(2, "b", null), new Square(2, "c", null), new Square(2, "d", null), new Square(2, "e", null), new Square(2, "f", null), new Square(2, "g", null), new Square(2, "h", null)),
        Vector(new Square(1, "a", null), new Square(1, "b", null), new Square(1, "c", null), new Square(1, "d", null), new Square(1, "e", null), new Square(1, "f", null), new Square(1, "g", null), new Square(1, "h", null))
    )

    current(0)(0) = new Figure("b", "Rook", current(0, 0))
    current(0)(1) = new Figure("b", "Knight", current(0, 1))
    current(0)(2) = new Figure("b", "Bishop", current(0, 2))
    current(0)(3) = new Figure("b", "Queen", current(0, 3))
    current(0)(4) = new Figure("b", "King", current(0, 4))
    current(0)(5) = new Figure("b", "Bishop", current(0, 5))
    current(0)(6) = new Figure("b", "Knight", current(0, 6))
    current(0)(7) = new Figure("b", "Rook", current(0, 7))

    current(7)(0) = new Figure("w", "Rook", current(7, 0))
    current(7)(1) = new Figure("w", "Knight", current(7, 1))
    current(7)(2) = new Figure("w", "Bishop", current(7, 2))
    current(7)(3) = new Figure("w", "Queen", current(7, 3))
    current(7)(4) = new Figure("w", "King", current(7, 4))
    current(7)(5) = new Figure("w", "Bishop", current(7, 5))
    current(7)(6) = new Figure("w", "Knight", current(7, 6))
    current(7)(7) = new Figure("w", "Rook", current(7, 7))

    for (i <- current(1).indices) {
        current(1, i) = new Figure("b", "Pawn", current(1,i))
    }
    for (i <- current(6).indices) {
        current(6, i) = new Figure("w", "Pawn", current(6,i))
    }


    def printBoard : Unit = {
        val letters = "abcdefgh"
        val horizontalLine = "   " + "+-----" * 8 + "+"

        for (i <- current.indices) {
        println(horizontalLine)
        print(s"${8 - i}  |")
        for (cell <- current(i)) {
            print(s"  $cell  |")
        }
        println()
        }
        println(horizontalLine)
        println("    " + letters.map(letter => s"  $letter   ").mkString(""))
        println()
    }
}
