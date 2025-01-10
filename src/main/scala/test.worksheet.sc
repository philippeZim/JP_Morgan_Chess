import ChessApiClient.getBestMove
import requests.Response

object ChessApiClient {
    val host = "https://d948-141-37-128-1.ngrok-free.app"

    def getBestMove(fen: String, depth: Int = 10): String = {
        val payload = ujson.Obj(
            "fen" -> fen,
            "depth" -> depth
        )

        val response: Response = requests.post(
            url = s"$host/bestmove/",
            data = payload.render(),
            headers = Map("Content-Type" -> "application/json")
        )

        if (response.statusCode == 200) {
            val json = ujson.read(response.text())
            json("best_move").str
        } else {
            throw new Exception(s"Error: ${response.statusCode}, ${response.text()}")
        }
    }
}
print(ChessApiClient.getBestMove("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"))
