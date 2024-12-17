package Model

import java.io.{BufferedReader, BufferedWriter, InputStreamReader, OutputStreamWriter}
import scala.io.Source
import scala.util.{Try, Using}
import scala.concurrent.{ExecutionContext, Future}
import scala.concurrent.ExecutionContext.Implicits.global // Use your own EC


class Stockfish(command: String) {

    private var process: Option[Process] = None

    /**
     * Starts the external executable process.
     *
     * @return Success(true) if the process started, Failure(Throwable) if it failed.
     */
    def start(): Try[Boolean] = {
        Try {
            val builder = new ProcessBuilder(command.split(" ").toList: _*)
            process = Some(builder.start())
            process.isDefined
        }
    }


    /**
     * Writes a string to the input stream of the external process.
     *
     * @param input The string to write to the input.
     * @return Success(Unit) if the write was successful, Failure(Throwable) if it failed.
     */
    def writeInput(input: String): Try[Unit] = {
        process match {
            case Some(proc) if proc.isAlive =>
                Try {
                    Using(new BufferedWriter(new OutputStreamWriter(proc.getOutputStream))) { writer =>
                        writer.write(input)
                        writer.newLine()
                        writer.flush()
                    }.get
                }
            case _ =>
                scala.util.Failure(new IllegalStateException("Process not started or has been terminated."))
        }
    }
    /**
     * Reads the entire output from the process's output stream and returns it as a string.
     *
     * @return A Future containing a Try with the process's output as a String if successful,
     *        or Failure(Throwable) if an error occurred. The Future completes when all of the output stream is processed
     */
    def readOutput(): Future[Try[String]] = Future {
        process match {
            case Some(proc) if proc.isAlive =>
                Try {
                    val br = new BufferedReader(new InputStreamReader(proc.getInputStream))
                    br.readLine()
                }
            case _ =>
                scala.util.Failure(new IllegalStateException("Process not started or has been terminated."))
        }
    }

    /**
     * Terminates the external process.
     *
     * @return Success(true) if the process was terminated, Failure(Throwable) if it was not terminated or if an error occurred.
     */
    def terminate(): Try[Boolean] = {
        process match {
            case Some(proc) if proc.isAlive =>
                Try {
                    proc.destroy()
                    proc.waitFor()
                    process = None
                    !proc.isAlive
                }
            case _ =>
                scala.util.Failure(new IllegalStateException("Process not started or has been terminated."))
        }
    }
}

object Stockfish {
    def apply(command: String): Stockfish = new Stockfish(command)
}
