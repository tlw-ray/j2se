/*
 * Created on 2006-9-12
 */
package com.tlw.swing.jtree.treewrapper;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 * DnDBorderFactory is responsible for creating node borders used under
 *   different drag and drop operations.
 * @author Antonio Vieiro (antonio@antonioshome.net), $Author: aviva $
 * @version $Revision: 1.2 $
 */
public class DnDBorderFactory
{
  /**
   * DropAllowedBorder is a Border that indicates that something is being
   *   droped on top of a valid node.
   * @author Antonio Vieiro (antonio@antonioshome.net), $Author: aviva $
   * @version $Revision: 1.2 $
   */
  static class DropBehindAllowedBorder
    implements Border
  {
    private static Insets insets = new Insets(0,0,3,0);
    private ImageIcon plusIcon;
    /**
     * Creates a new instance of DropAllowedBorder
     */
    public DropBehindAllowedBorder()
    {
      URL iconURL = DropBehindAllowedBorder.class.getResource( "icons/drop-behind-allowed.png" );
      if ( iconURL != null )
        plusIcon = new ImageIcon( iconURL );
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
      int yh = y+height-1;
      if( plusIcon != null )
      {
        plusIcon.paintIcon( c, g, x+8, yh-8 );
      }
      yh-=4;
      g.setColor( Color.DARK_GRAY );
      g.drawLine( x+24, yh, x+48, yh );
    }

    public Insets getBorderInsets(Component c)
    {
      return insets;
    }

    public boolean isBorderOpaque()
    {
      return false;
    }
  }
  static class DropInAllowedBorder implements Border{
      private Insets insets = new Insets(5,0,0,0);
      private ImageIcon plusIcon;
      public DropInAllowedBorder(){
          URL iconURL = DnDBorderFactory.class.getResource( "icons/drop-in-allowed.gif" );
          if ( iconURL != null )
            plusIcon = new ImageIcon( iconURL );
      }
    public Insets getBorderInsets(Component c) {
        return insets;
    }

    public boolean isBorderOpaque() {
        return false;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        if( plusIcon != null )
        {
          plusIcon.paintIcon( c, g, x, y );
        }
        g.setColor( Color.blue );
        g.drawRect( x, y, width-1, height-1 );
    }

  }
  /**
   * OffsetBorder is a Border that contains an offset.
   * This is used to "separate" the node under the drop.
   * @author Antonio Vieiro (antonio@antonioshome.net), $Author: aviva $
   * @version $Revision: 1.2 $
   */
  class OffsetBorder
    implements Border
  {
    private Insets insets = new Insets(5,0,0,0);

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
      // empty
    }

    public Insets getBorderInsets(Component c)
    {
      return insets;
    }

    public boolean isBorderOpaque()
    {
      return false;
    }

  }
  /**
   * DropOnNodeBorder is a Border that indicates that something cannot be
   *   dropped here.
   * @author Antonio Vieiro (antonio@antonioshome.net), $Author: aviva $
   * @version $Revision: 1.2 $
   */
  class DropNotAllowedBorder
    implements Border
  {
    private Insets insets = new Insets(0,0,0,0);
    private ImageIcon plusIcon;
    /**
     * Creates a new instance of DropOnNodeBorder
     */
    public DropNotAllowedBorder()
    {
      URL iconURL = DnDBorderFactory.class.getResource( "icons/drop-not-allowed.png" );
      if ( iconURL != null )
        plusIcon = new ImageIcon( iconURL );
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
      if( plusIcon != null )
      {
        plusIcon.paintIcon( c, g, x, y );
      }
      g.setColor( Color.RED );
      g.drawRect( x, y, width-1, height-1 );
    }

    public Insets getBorderInsets(Component c)
    {
      return insets;
    }

    public boolean isBorderOpaque()
    {
      return false;
    }

  }

  /**
   * Creates a new instance of DnDBorderFactory
   */
  public DnDBorderFactory()
  {
    setDropBehindAllowedBorder( new DropBehindAllowedBorder() ); // DropOnFolderBorder() );
    setDropNotAllowedBorder( new DropNotAllowedBorder() );
    setOffsetBorder( new OffsetBorder() );
    setEmptyBorder( BorderFactory.createEmptyBorder() );
    setDropInAllowedBorder(new DropInAllowedBorder());
  }

  /**
   * Holds value of property dropAllowedBorder.
   */
  private Border dropBehindAllowedBorder;
  private Border dropInAllowedBorder;
  /**
 * @return dropInAllowedBorder
 */
public Border getDropInAllowedBorder() {
    return dropInAllowedBorder;
}

/**
 * @param dropInAllowedBorder the dropInAllowedBorder to set
 */
public void setDropInAllowedBorder(Border dropInAllowedBorder) {
    this.dropInAllowedBorder = dropInAllowedBorder;
}

/**
   * Getter for property dropAllowedBorder.
   *
   * @return Value of property dropAllowedBorder.
   */
  public Border getDropBehindAllowedBorder()
  {
    return this.dropBehindAllowedBorder;
  }

  /**
   * Setter for property dropAllowedBorder.
   *
   * @param dropAllowedBorder New value of property dropAllowedBorder.
   */
  public void setDropBehindAllowedBorder(Border dropAllowedBorder)
  {
    this.dropBehindAllowedBorder = dropAllowedBorder;
  }

  /**
   * Holds value of property dropNotAllowedBorder.
   */
  private Border dropNotAllowedBorder;

  /**
   * Getter for property dropNotAllowedBorder.
   * @return Value of property dropNotAllowedBorder.
   */
  public Border getDropNotAllowedBorder()
  {
    return this.dropNotAllowedBorder;
  }

  /**
   * Setter for property dropNotAllowedBorder.
   * @param dropNotAllowedBorder New value of property dropNotAllowedBorder.
   */
  public void setDropNotAllowedBorder(Border dropNotAllowedBorder)
  {
    this.dropNotAllowedBorder = dropNotAllowedBorder;
  }

  /**
   * Holds value of property offsetBorder.
   */
  private Border offsetBorder;

  /**
   * Getter for property offsetBorder.
   * @return Value of property offsetBorder.
   */
  public Border getOffsetBorder()
  {
    return this.offsetBorder;
  }

  /**
   * Setter for property offsetBorder.
   * @param offsetBorder New value of property offsetBorder.
   */
  public void setOffsetBorder(Border offsetBorder)
  {
    this.offsetBorder = offsetBorder;
  }

  private Border emptyBorder;

  public Border getEmptyBorder()
  {
    return emptyBorder;
  }

  public void setEmptyBorder( Border anEmptyBorder )
  {
    emptyBorder = anEmptyBorder;
  }
}
