//--------------------------------------------------------------------------------------------------
// File: CompilerGeneratedSource.scala
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

package es.weso.shexlc.internal

import java.io.FileWriter

import es.weso.shexlc.IRGen.TargetIR

/**
 * A Shape Expression Compiler Generated Source Represents the source generated by the compiler in an specific target
 * language.
 *
 * @author Guillermo Facundo Colunga
 */
trait CompilerGeneratedSource {

  /**
   * Gets the standardized target language in which the source has been generated.
   *
   * @return the standardized target language.
   */
  def getLanguage: TargetIR

  /**
   * Gets the source as a plain String object.
   *
   * @return the source as a plain String object.
   */
  def getSource: String

  /**
   * Writes the generated source in a given filepath.
   *
   * @param filePath is the place where the source will be written.
   */
  def toFile(filePath: String)
}

/**
 * A Shape Expression Compiler Generated Source Represents the source generated by the compiler in an specific target
 * language.
 *
 * @author Guillermo Facundo Colunga
 */
object CompilerGeneratedSource {

  /**
   * Builds a new compiler generated source for the given language and souurce.
   *
   * @param language in which the source has been generated.
   * @param source that has been generated.
   * @return the new generated source object that contains the source and the target language.
   */
  def buildFor(language: TargetIR, source: String): CompilerGeneratedSource = new CompilerGeneratedSource {

    /**
     * Gets the standardized target language in which the source has been generated.
     *
     * @return the standardized target language.
     */
    override def getLanguage: TargetIR = language

    /**
     * Gets the source as a plain String object.
     *
     * @return the source as a plain String object.
     */
    override def getSource: String = source

    /**
     * Writes the generated source in a given filepath.
     *
     * @param filePath is the place where the source will be written.
     */
    override def toFile(filePath: String): Unit = {
      // Create a file at the destination folder.
      val file = new FileWriter(filePath)

      // Writing the file with the contests of the source.
      file.write(source)

      // Flushing and closing the file.
      file.flush()
      file.close()
    }
  }
}
