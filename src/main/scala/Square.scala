class Square (colum: Integer, row: String, state: Figure) {
    override def equals(that: Sqaure): Boolean = this.colum == that.colum && this.row == that.row
    def isFull : Boolean = {
        if(this.state == null) {return false}
        else {return true}
    }
}
