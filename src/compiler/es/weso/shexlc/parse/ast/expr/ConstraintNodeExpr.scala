//------------------------------------------------------------------------------
// File: ConstraintNodeExpr.scala
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

package es.weso.shexlc.parse.ast.expr

import es.weso.shexlc.parse.ast.Position
import org.antlr.v4.runtime.misc.Interval

/**
  * A base class for all the Constraint Nodes Expressions.
  *
  * @author Guillermo Facundo Colunga
  */
abstract class ConstraintNodeExpr(
  position: Position,
  tokenRange: Interval,
  content: String
) extends ConstraintExpr(position, tokenRange, content) {

  // Override default methods to indicate that this is a Constraint Node
  // Expression.
  override def isConstraintNodeExpr: Boolean = true

  override def asConstraintNodeExpr: ConstraintNodeExpr = this
}
