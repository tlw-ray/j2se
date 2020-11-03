/*
 * Created on 2006-9-12
 */
package com.tlw.swing.jtree.treewrapper;
/**
 * DnDVetoException is an exception thrown to signal that a drag and drop
 *   operation is not valid.
 * @author Antonio Vieiro (antonio@antonioshome.net), $Author: aviva $
 * @version $Revision: 1.1 $
 */
public class DnDVetoException
  extends Exception
{
	private static final long serialVersionUID = 3542040378659211060L;

/**
   * Creates a new instance of DnDVetoException
   * @param aMessage the message to show.
   */
  public DnDVetoException( String aMessage )
  {
    super( aMessage );
  }


}
