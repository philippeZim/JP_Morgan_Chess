def indexToCoordinates(index: Int): String = {
    val file = (index % 8)
    val rank = 7 - (index / 8)

    val fileChar = (file + 97).toChar
    val rankChar = (rank + 49).toChar

    s"$fileChar$rankChar"
}

