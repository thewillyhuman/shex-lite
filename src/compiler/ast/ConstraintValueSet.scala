package compiler.ast

private[compiler] case class ConstraintValueSet(filename: String, line: Int, column: Int, set: List[ValueSetValidType]) extends ConstraintNode {
  /**
   * Gets the name of the file where the node was originated.
   *
   * @return the name of the file where the node was originated.
   */
  override def getFileName: String = filename

  /**
   * Gets the number of the line where the node was originated.
   *
   * @return the number of the line where the node was originated.
   */
  override def getLine: Int = line

  /**
   * Gets the number of the column where the node was originated.
   *
   * @return the number of the column where the node was originated.
   */
  override def getColumn: Int = column
}
