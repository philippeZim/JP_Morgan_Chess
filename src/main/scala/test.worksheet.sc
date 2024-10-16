import scala.annotation.tailrec

object ChessBoard {


  val start_board: Vector[Vector[String]] = Vector(
    Vector("r", "n", "b", "q", "k", "b", "n", "r"),
    Vector("p", "p", "p", "p", "p", "p", "p", "p"),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector(".", ".", ".", ".", ".", ".", ".", "."),
    Vector("P", "P", "P", "P", "P", "P", "P", "P"),
    Vector("R", "N", "B", "Q", "K", "B", "N", "R")
  )


  def printBoard(board: Vector[Vector[String]]): Unit = {
    val letters = "abcdefgh"
    val horizontalLine = "   " + "+-----" * 8 + "+"

    for (i <- 0 until 8) {
      println(horizontalLine)
      print(s"${8 - i}  |")
      for (cell <- board(i)) {
        print(s"  $cell  |")
      }
      println()
    }
    println(horizontalLine)
    println("    " + letters.map(letter => s"  $letter   ").mkString(""))
    println()
  }
}


val startingBoard: Vector[Vector[Int]] = Vector(
    Vector(-2, -3, -4, -5, -6, -4, -3, -2),
    Vector(-1, -1, -1, -1, -1, -1, -1, -1),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(0, 0, 0, 0, 0, 0, 0, 0),
    Vector(1, 1, 1, 1, 1, 1, 1, 1),
    Vector(2, 3, 4, 5, 6, 4, 3, 2)
)

def convert_board(board: Vector[Vector[Int]]): Vector[Vector[String]] = {
    val pieceMap: Map[Int, String] = Map(
        0 -> ".",
        1 -> "P",
        2 -> "R",
        3 -> "N",
        4 -> "B",
        5 -> "Q",
        6 -> "K",
        -1 -> "p",
        -2 -> "r",
        -3 -> "n",
        -4 -> "b",
        -5 -> "q",
        -6 -> "k"
    )
    def unpack_fun(el:Int): String = {
        pieceMap.get(el) match {
            case None => "?"
            case Some(value) => value
        }
    }
    board.map(row => row.map(el => unpack_fun(el)))
}

ChessBoard.printBoard(convert_board(startingBoard))

def pawn_attack(board: Vector[Vector[Int]], pos: (Int, Int), attack_color: Int): Boolean = {
    val (r, c) = pos
    val attacks: List[(Int, Int)] = List((attack_color, attack_color), (attack_color, attack_color * -1))
    @tailrec
    def sub(moves: List[(Int, Int)]): Boolean = {
        moves match {
            case Nil => false
            case (rd, cd) :: t => {
                val nr = rd + r
                val nc = cd + c
                if (0 <= nr && nr < 8 && 0 <= nc && nc < 8 && board(nr)(nc) == attack_color) {
                    return true
                }
                sub(t)
            }
        }
    }
    sub(attacks)
}

def knight_attack(board: Vector[Vector[Int]], pos: (Int, Int), attack_color: Int): Boolean = {
    val (r, c) = pos
    val attacks: List[(Int, Int)] = List((-2, 1), (-2, -1), (-1, 2), (1, 2), (2, 1), (2, -1), (1, -2), (-1, -2))
    @tailrec
    def sub(moves: List[(Int, Int)]): Boolean = {
        moves match {
            case Nil => false
            case (rd, cd) :: t => {
                val nr = rd + r
                val nc = cd + c
                if (0 <= nr && nr < 8 && 0 <= nc && nc < 8 && board(nr)(nc) == 3 * attack_color) {
                    return true
                }
                sub(t)
            }
        }
    }
    sub(attacks)
}

def horizontal_attack(board: Vector[Vector[Int]], pos: (Int, Int), attack_color: Int): Boolean = {
    val (r, c) = pos
    val attacks: List[(Int, Int)] = List((-1, 0), (1, 0), (0, 1), (0, -1))

    @tailrec
    def sub2(cr: Int, cc: Int, dr: Int, dc: Int): Boolean = {
        if (0 <= cr && cr < 0 && cc <= 0 && cc < 8) {
            val x: Int = board(cr)(cc)
            if (x == 0) {
                sub2(cr + dr, cc + dc, dr, dc)
            } else {
                x == 2 * attack_color || x == 5 * attack_color
            }
        } else {
            false
        }
    }
    @tailrec
    def sub(moves: List[(Int, Int)]): Boolean = {
        moves match {
            case Nil => false
            case (rd, cd) :: t => {
                if (sub2(r + rd, c + cd, rd, cd)) {
                    return true
                }
                sub(t)
            }
        }
    }
    sub(attacks)
}

def vertical_attack(board: Vector[Vector[Int]], pos: (Int, Int), attack_color: Int): Boolean = {
    val (r, c) = pos
    val attacks: List[(Int, Int)] = List((1, 1), (-1, 1), (-1, -1), (1, -1))

    @tailrec
    def sub2(cr: Int, cc: Int, dr: Int, dc: Int): Boolean = {
        if (0 <= cr && cr < 0 && cc <= 0 && cc < 8) {
            val x: Int = board(cr)(cc)
            if (x == 0) {
                sub2(cr + dr, cc + dc, dr, dc)
            } else {
                x == 4 * attack_color || x == 5 * attack_color
            }
        } else {
            false
        }
    }
    @tailrec
    def sub(moves: List[(Int, Int)]): Boolean = {
        moves match {
            case Nil => false
            case (rd, cd) :: t => {
                if (sub2(r + rd, c + cd, rd, cd)) {
                    return true
                }
                sub(t)
            }
        }
    }
    sub(attacks)
}

def king_attack(board: Vector[Vector[Int]], pos: (Int, Int), attack_color: Int): Boolean = {
    val (r, c) = pos
    val attacks: List[(Int, Int)] = List((1, 1), (-1, 1), (-1, -1), (1, -1), (-1, 0), (1, 0), (0, 1), (0, -1))

    @tailrec
    def sub(moves: List[(Int, Int)]): Boolean = {
        moves match {
            case Nil => false
            case (rd, cd) :: t => {
                val nr = rd + r
                val nc = cd + c
                if (0 <= nr && nr < 8 && 0 <= nc && nc < 8 && board(nr)(nc) == 6 * attack_color) {
                    return true
                }
                sub(t)
            }
        }
    }

    sub(attacks)
}


def is_pos_attacked(board: Vector[Vector[Int]], pos: (Int, Int), attack_color: Int): Boolean = {
    pawn_attack(board, pos, attack_color) || knight_attack(board, pos, attack_color) ||
      horizontal_attack(board, pos, attack_color) || vertical_attack(board, pos, attack_color) ||
      king_attack(board, pos, attack_color)
}

def make_move(board: Vector[Vector[Int]], move: (Int, Int, Int, Int)): Vector[Vector[Int]] = {
    val (fr, fc, tr, tc) = move
    val piece = board(fr)(fc)
    board.updated(tr, board(tr).updated(tc, piece))
      .updated(fr, board(fr).updated(fc, 0))
}

def is_king_in_check_after_move(board: Vector[Vector[Int]], king_pos: (Int, Int), move: (Int, Int, Int, Int), attack_color: Int): Boolean = {
    val (fr, fc, tr, tc) = move
    if (fr == king_pos._1 && fc == king_pos._2) {
        return is_pos_attacked(make_move(board, move), (tr, tc), attack_color)
    }
    is_pos_attacked(make_move(board, move), king_pos, attack_color)
}

println("Hallo ich bin eine Änderung")
