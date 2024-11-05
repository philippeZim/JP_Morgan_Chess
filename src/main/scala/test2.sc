def onBoard(pos: Int, rd: Int, cd: Int): Boolean = {
    val nr = pos + rd * 8
    if (nr < 0 || nr > 63) {
        return false
    }
    val nc = pos + cd
    val oldCol = pos % 8
    if (oldCol + cd < 0 || oldCol + cd > 7) {
        return false
    }
    if (pos / 8 != nc / 8) {
        return false
    }
    true
}

onBoard(0, 0, -1)