//------------------------------------------------------------------------------
// File: NodeWithErrors.scala
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
//------------------------------------------------------------------------------

package es.weso.shexlc.parse.ast

import es.weso.shexlc.internal.errorhandler.Err

/**
  * A node with errors is a node that might have errors and therefore
  * contains a list of errors.
  *
  * @author Guillermo Facundo Colunga
  */
trait NodeWithErrors {

  /**
    * Gets whether the node has errors or not.
    *
    * @return true if the node contains any error, false otherwise.
    */
  def hasErrors: Boolean

  /**
    * Gets the errors generated by the node.
    *
    * @return the errors generated by the node.
    */
  def getErrors: List[Err]

  /**
    * Adds a single error to the node list of errors.
    *
    * @param err to add to the node list of errors.
    */
  def addError(err: Err): Unit
}
