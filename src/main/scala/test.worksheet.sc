


// Example usage:
val pathStock = "C:\\Users\\eronz\\IdeaProjects\\JP_Morgan_Chess\\src\\main\\resources\\stockfish\\stockfish-windows-x86-64-avx2.exe"

val s = new Stockfish(pathStock)
val (process, out, in, err) = s.initConnection()
val bestMove = s.getBestMove("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1", out, in)
println(s"Best move: $bestMove")
s.closeConnection(process, out, in, err)