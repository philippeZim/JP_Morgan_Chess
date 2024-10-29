def coordinatesToIndex(coord: String): Int = {
    (coord.charAt(0).toInt - 97) + 8 * (8 - (coord.charAt(1).toInt - 48));
}

def moveToIndex(from: String, to: String): (Int, Int) = {
    (coordinatesToIndex(from), coordinatesToIndex(to));
}

val t1 = coordinatesToIndex("a1");