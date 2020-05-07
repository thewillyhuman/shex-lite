//--------------------------------------------------------------------------------------------------
// File: AbstractASTNode.scala
//
// Short version for non-lawyers:
//
// The ShEx Lite Project is dual-licensed under GNU 3.0 and
// MIT terms.
//
// Longer version:
//
// Copyrights in the ShEx Lite project are retained by their contributors. No
// copyright assignment is required to contribute to the ShEx Lite project.
//
// Some files include explicit copyright notices and/or license notices.
// For full authorship information, see the version control history.
//
// Except as otherwise noted (below and/or in individual files), ShEx Lite is
// licensed under the GNU, Version 3.0 <LICENSE-GNU> or
// <https://choosealicense.com/licenses/gpl-3.0/> or the MIT license
// <LICENSE-MIT> or <http://opensource.org/licenses/MIT>, at your option.
// In case of incompatibility between project licenses, GNU/GPLv3 will be
// applied.
//
// The ShEx Lite Project includes packages written by third parties.
//--------------------------------------------------------------------------------------------------

package es.weso.shexlc.parse.ast

import es.weso.shexlc.internal.errorhandler.{CompilationEvent, Err, Warn}
import es.weso.shexlc.parse.ast.visitor.Visitable
import org.antlr.v4.runtime.misc.Interval

import scala.collection.mutable.ListBuffer

/**
 * An abstract ast node is a node from the abstract syntax tree. It contains information about the position, the
 * token range and the content of it as a String.
 *
 * The position represents the line and the column in the source file, notice that the column starts at index 0
 * meanwhile the line starts at index 1.
 *
 * The token range is a range defined as (start, stop) that indicates the start token and the stop token that matched
 * the rule applied to create this node.
 *
 * The content is a string the contains the range of source code that contains this node. For that reason the nodes
 * from the ast that are not leafs contain also the content of the child nodes.
 *
 * @param position is the line and column from the input files where the rule that created the node was located.
 * @param tokenRange is the range as (start, end) of tokens that the rule contain.
 * @param content is a string that contains all the source code from the input files that match the rule.
 */
abstract class AbstractASTNode(position: Position, tokenRange: Interval, content: String) extends NodeWithPosition
                                                                                  with NodeWithTokenRange
                                                                                  with NodeWithParent
                                                                                  with NodeWithErrors
                                                                                  with NodeWithWarnings
                                                                                  with Visitable {

  // The parent of this node, null if it is the root.
  private[this] var parent: Option[NodeWithParent] = Option.empty

  // The compiler events from this node.
  private[this] var compilerEvents = new ListBuffer[CompilationEvent].empty
  private[this] var _hasErrors = false
  private[this] var _hasWarnings = false

  /**
   * Gets the position object that points to the source file.
   *
   * @return a position object containing the position in the source file.
   */
  override def getPosition: Position = position

  /**
   * Gets the range of tokens from the source on which the node was generated.
   *
   * @return the range of tokens from the source on which the node was generated.
   */
  override def getRange: Interval = tokenRange

  /**
   * Gets the content of the node as a String, for example for a node that contains the assignment of a and 3 the content
   * would be 'a = 3'.
   *
   * @return the content of the node as a String.
   */
  override def getContent: String = content

  /**
   * Gets the position object that points to the source file.
   *
   * @return a position object containing the position in the source file.
   */
  override def getParent: Option[NodeWithParent] = this.parent

  /**
   * Sets the parent for the current node.
   *
   * @param parent to be set as the parent of the current node.
   */
  override def setParent(parent: NodeWithParent): Unit = this.parent = Option(parent)

  /**
   * Sets the current node as the parent of the given node.
   *
   * @param node where the parent will be set as the current one.
   */
  override def setAsParentOf(node: NodeWithParent): Unit = node.setParent(this)

  /**
   * Gets the root of the tree where this leaf is in.
   *
   * @return the root of the tree where this leaf is in.
   */
  override def findRootNode: NodeWithParent = {
    var root: NodeWithParent = this
    while (root.getParent.isDefined) root = root.getParent.get
    root
  }

  /**
   * Gets whether the node has errors or not.
   *
   * @return true if the node contains any error, false otherwise.
   */
  override def hasErrors: Boolean = this._hasErrors

  /**
   * Gets the errors generated by the node.
   *
   * @return the errors generated by the node.
   */
  override def getErrors: List[Err] = {
    this.compilerEvents.filter(item => item.isError).toList.asInstanceOf[List[Err]]
  }

  /**
   * Adds a single error to the node list of errors.
   *
   * @param err to add to the node list of errors.
   */
  override def addError(err: Err): Unit = {
    this.compilerEvents += err
    this._hasErrors = true
  }

  /**
   * Gets whether the node has warnings or not.
   *
   * @return true if the node contains any warning, false otherwise.
   */
  override def hasWarnings: Boolean = this._hasWarnings

  /**
   * Gets the warnings generated by the node.
   *
   * @return the warnings generated by the node.
   */
  override def getWarnings: List[Warn] = {
    this.compilerEvents.filter(item => item.isWarning).toList.asInstanceOf[List[Warn]]
  }

  /**
   * Adds a single warning to the node list of warnings.
   *
   * @param warn to add to the node list of warnings.
   */
  override def addWarning(warn: Warn): Unit = {
    this.compilerEvents += warn
    this._hasWarnings = true
  }
}
