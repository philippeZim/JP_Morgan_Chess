package Model

import requests.Response

object ChessApiClient {
    val host = "http://127.0.0.1:8000"

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
