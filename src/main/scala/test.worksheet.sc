import Controller.State
import Model.{ChessBoard, Event, LegalMoves, Remis, SetCommand,ProcessManager}

import java.io.IOException


var process = ProcessManager()
process.startExecutable("C:\\Users\\eronz\\IdeaProjects\\JP_Morgan_Chess\\src\\main\\resources\\stockfish\\main.exe")


process.writeToExecutable("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1" + " 15")
// Read response from the executable
val response = process.readFromExecutable
System.out.println("Response from executable: " + response)