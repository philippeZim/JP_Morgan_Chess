import Color.{BLACK, WHITE}
import Model.ChessBoard
import PieceType.{BISHOP, KING, KNIGHT, PAWN, QUEEN, ROOK}

import scala.annotation.tailrec

object ChessBoard {


    val start_board: Vector[Vector[String]] = Vector(Vector("r", "n", "b", "q", "k", "b", "n", "r"), Vector("p", "p", "p", "p", "p", "p", "p", "p"), Vector(".", ".", ".", ".", ".", ".", ".", "."), Vector(".", ".", ".", ".", ".", ".", ".", "."), Vector(".", ".", ".", ".", ".", ".", ".", "."), Vector(".", ".", ".", ".", ".", ".", ".", "."), Vector("P", "P", "P", "P", "P", "P", "P", "P"), Vector("R", "N", "B", "Q", "K", "B", "N", "R"))


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


val startingBoard: Vector[Vector[Int]] = Vector(Vector(-2, -3, -4, -5, -6, -4, -3, -2), Vector(-1, -1, -1, -1, -1, -1, -1, -1), Vector(0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0), Vector(0, 0, 0, 0, 0, 0, 0, 0), Vector(1, 1, 1, 1, 1, 1, 1, 1), Vector(2, 3, 4, 5, 6, 4, 3, 2))

def convert_board(board: Vector[Vector[Int]]): Vector[Vector[String]] = {
    val pieceMap: Map[Int, String] = Map(0 -> ".", 1 -> "P", 2 -> "R", 3 -> "N", 4 -> "B", 5 -> "Q", 6 -> "K", -1 -> "p", -2 -> "r", -3 -> "n", -4 -> "b", -5 -> "q", -6 -> "k")

    def unpack_fun(el: Int): String = {
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

    @tailrec def sub(moves: List[(Int, Int)]): Boolean = {
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

    @tailrec def sub(moves: List[(Int, Int)]): Boolean = {
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

    @tailrec def sub2(cr: Int, cc: Int, dr: Int, dc: Int): Boolean = {
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

    @tailrec def sub(moves: List[(Int, Int)]): Boolean = {
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

    @tailrec def sub2(cr: Int, cc: Int, dr: Int, dc: Int): Boolean = {
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

    @tailrec def sub(moves: List[(Int, Int)]): Boolean = {
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

    @tailrec def sub(moves: List[(Int, Int)]): Boolean = {
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
    pawn_attack(board, pos, attack_color) || knight_attack(board, pos, attack_color) || horizontal_attack(board, pos, attack_color) || vertical_attack(board, pos, attack_color) || king_attack(board, pos, attack_color)
}

def make_move(board: Vector[Vector[Int]], move: (Int, Int, Int, Int)): Vector[Vector[Int]] = {
    val (fr, fc, tr, tc) = move
    val piece = board(fr)(fc)
    board.updated(tr, board(tr).updated(tc, piece)).updated(fr, board(fr).updated(fc, 0))
}

def is_king_in_check_after_move(board: Vector[Vector[Int]], king_pos: (Int, Int), move: (Int, Int, Int, Int), attack_color: Int): Boolean = {
    val (fr, fc, tr, tc) = move
    if (fr == king_pos._1 && fc == king_pos._2) {
        return is_pos_attacked(make_move(board, move), (tr, tc), attack_color)
    }
    is_pos_attacked(make_move(board, move), king_pos, attack_color)
}

println("Dies ist die Änderung in Branch example-feature1")
println("Änderung Nr.2 Philippe")

enum Color:
    case BLACK, WHITE, EMPTY

enum PieceType:
    case PAWN, ROOK, KNIGHT, BISHOP, QUEEN, KING, EMPTY

final case class Piece(pieceType: Model.PieceType, color: Model.Color) {

    override def toString(): String = {
        val pieceMap: Map[(Model.PieceType, Model.Color), String] = Map((PieceType.PAWN, Color.WHITE) -> "P", (PieceType.PAWN, Color.BLACK) -> "p", (PieceType.ROOK, Color.WHITE) -> "R", (PieceType.ROOK, Color.BLACK) -> "r", (PieceType.KNIGHT, Color.WHITE) -> "N", (PieceType.KNIGHT, Color.BLACK) -> "n", (PieceType.BISHOP, Color.WHITE) -> "B", (PieceType.BISHOP, Color.BLACK) -> "b", (PieceType.QUEEN, Color.WHITE) -> "Q", (PieceType.QUEEN, Color.BLACK) -> "q", (PieceType.KING, Color.WHITE) -> "K", (PieceType.KING, Color.BLACK) -> "k", (PieceType.EMPTY, Color.EMPTY) -> ".");
        pieceMap.get((this.pieceType, this.color)) match {
            case None => "?"
            case Some(value) => value
        }
    }
}

def splitLine(): String = {
    "      " + "+-----" * 8 + "+\n"
}

def peaceLine(line: Vector[Model.Piece], columnNumber: String): String = {
    s"  $columnNumber   |" + line.map(el => s"  ${el.toString()}  |").mkString("") + "\n";
}

def getBoardString(board: Vector[Vector[Model.Piece]]): String = {
    def sub(ind: Int, acc: String): String = {
        if (ind == 8) {
            val letters = "abcdefgh";
            return acc + splitLine() + "         " + letters.map(el => s"$el     ").mkString("");
        }
        sub(ind + 1, acc + splitLine() + peaceLine(board(ind), s"${8 - ind}"));

    }

    sub(0, "")
}

def getDefaultBoard(): Vector[Vector[Model.Piece]] = {
    val p = Model.Piece(PieceType.PAWN, Color.BLACK);
    val r = Model.Piece(PieceType.ROOK, Color.BLACK);
    val n = Model.Piece(PieceType.KNIGHT, Color.BLACK);
    val b = Model.Piece(PieceType.BISHOP, Color.BLACK);
    val q = Model.Piece(PieceType.QUEEN, Color.BLACK);
    val k = Model.Piece(PieceType.KING, Color.BLACK);

    val P = Model.Piece(PieceType.PAWN, Color.WHITE);
    val R = Model.Piece(PieceType.ROOK, Color.WHITE);
    val N = Model.Piece(PieceType.KNIGHT, Color.WHITE);
    val B = Model.Piece(PieceType.BISHOP, Color.WHITE);
    val Q = Model.Piece(PieceType.QUEEN, Color.WHITE);
    val K = Model.Piece(PieceType.KING, Color.WHITE);
    val point = Model.Piece(PieceType.EMPTY, Color.EMPTY);
    val board: Vector[Vector[Model.Piece]] = Vector(Vector(r, n, b, q, k, b, n, r), Vector(p, p, p, p, p, p, p, p), Vector(point, point, point, point, point, point, point, point), Vector(point, point, point, point, point, point, point, point), Vector(point, point, point, point, point, point, point, point), Vector(point, point, point, point, point, point, point, point), Vector(P, P, P, P, P, P, P, P), Vector(R, N, B, Q, K, B, N, R))
    board
}

val e = getBoardString(getDefaultBoard());


val res: Vector[List[Model.Piece]] = Vector(List(), List(), List(), List(), List(), List(), List(), List())

val testFen = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1";
testFen.toList
def fenToBoard(fen: String): Vector[Vector[Model.Piece]] = {
    @tailrec def sub2(acc: List[Model.Piece], n: Int): List[Model.Piece] = {
        n match {
            case el if el > 0 => sub2(Model.Piece(PieceType.EMPTY, Color.EMPTY) :: acc, n - 1)
            case _ => acc
        }
    }

    @tailrec def sub(fen: List[Char], cur: List[Model.Piece], acc: List[List[Model.Piece]]): List[List[Model.Piece]] = {
        fen match {
            case Nil => cur.reverse :: acc
            case h :: t => {
                h match {
                    case '/' => sub(t, List(), cur.reverse :: acc)
                    case 'p' => sub(t, Model.Piece(PieceType.PAWN, Color.BLACK) :: cur, acc)
                    case 'r' => sub(t, Model.Piece(PieceType.ROOK, Color.BLACK) :: cur, acc)
                    case 'n' => sub(t, Model.Piece(PieceType.KNIGHT, Color.BLACK) :: cur, acc)
                    case 'b' => sub(t, Model.Piece(PieceType.BISHOP, Color.BLACK) :: cur, acc)
                    case 'q' => sub(t, Model.Piece(PieceType.QUEEN, Color.BLACK) :: cur, acc)
                    case 'k' => sub(t, Model.Piece(PieceType.KING, Color.BLACK) :: cur, acc)
                    case 'P' => sub(t, Model.Piece(PieceType.PAWN, Color.WHITE) :: cur, acc)
                    case 'R' => sub(t, Model.Piece(PieceType.ROOK, Color.WHITE) :: cur, acc)
                    case 'N' => sub(t, Model.Piece(PieceType.KNIGHT, Color.WHITE) :: cur, acc)
                    case 'B' => sub(t, Model.Piece(PieceType.BISHOP, Color.WHITE) :: cur, acc)
                    case 'Q' => sub(t, Model.Piece(PieceType.QUEEN, Color.WHITE) :: cur, acc)
                    case 'K' => sub(t, Model.Piece(PieceType.KING, Color.WHITE) :: cur, acc)
                    case el if el.isDigit => sub(t, sub2(cur, el.toInt - 48), acc)
                }
            }
        }
    }

    sub(fen.split(" ")(0).toList, List(), List()).reverse.map(_.toVector).toVector
}

fenToBoard(testFen)


def coordinatesToIndex(co: String): (Int, Int) = {
    (co.charAt(0).toInt - 97, co.charAt(1).toInt - 49)
}


def onBoard(pos: Int, rd: Int, cd: Int): Boolean = {
    val nr = pos + rd * 8
    if (nr < 0 || nr > 63) {
        return false
    }
    val nc = pos + cd
    if (pos / 8 != nc / 8) {
        return false
    }
    true
}

val t1 = onBoard(8, -2, 1)