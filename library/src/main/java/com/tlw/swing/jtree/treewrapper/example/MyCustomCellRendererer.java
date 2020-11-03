/*
 * Created on 2006-9-12
 */
package com.tlw.swing.jtree.treewrapper.example;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 * MyCustomCellRendererer is a cell renderer that shows some (ugly, sorry) icons
 * @author Antonio Vieiro (antonio@antonioshome.net), $Author: aviva $
 * @version $Revision: 1.1 $
 */
class MyCustomCellRendererer
  extends DefaultTreeCellRenderer
{
	private static final long serialVersionUID = -1228954053183251549L;
ImageIcon orangeIcon;
  ImageIcon appleIcon;

  public MyCustomCellRendererer()
  {
    orangeIcon = new ImageIcon( getClass().getResource("orange.png") );
    appleIcon = new ImageIcon( getClass().getResource("apple.png") );
  }

  public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
  {
    super.getTreeCellRendererComponent( tree, value, sel,
      expanded, leaf, row, hasFocus );

    if ( value.toString().startsWith( "orange") )
      setIcon( orangeIcon );
    else if ( value.toString().startsWith("apple") )
      setIcon( appleIcon );
    else
      setIcon( null );

    return this;
  }


}
