import Model.Stockfish

import scala.concurrent.Await
import scala.concurrent.duration.{Duration, MILLISECONDS}
import scala.util.{Failure, Success, Try, Using}
import Model.ExeInteraction.ec
import scala.concurrent.ExecutionContext.Implicits.global

val process = Stockfish("C:\\Users\\eronz\\IdeaProjects\\JP_Morgan_Chess\\src\\main\\resources\\stockfish\\main.exe") // Replace with your executable

process.start() match {
    case Success(true) => println("starting was successful!")
    case Success(false) => println("failed starting")
    case Failure(_) => println("error accured while starting Stockfish")
}


process.writeInput("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1 15") match {
    case Success(()) => ()
    case Failure(_) => "error writing to Stockfish"
}

val outFuture = process.readOutput()
val outputResult = Await.result(outFuture, Duration.create(100000, MILLISECONDS))


val finalRes: String = outputResult match {
    case Success(e) => e
    case _ => ""
}



println(finalRes)