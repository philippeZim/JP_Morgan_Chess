class Figure (color: String, name: String, position: Square) {
    override def equals(that: Figure): Boolean = (this.color == that.color) && (this.name == that.name) && (this.position == that.position)
    def move (target: Square) : Boolean =
        if(target.isFull) {
            return false
        } else {
            this.position = target
            return true
        }

}
