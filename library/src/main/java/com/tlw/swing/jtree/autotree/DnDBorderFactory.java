package com.tlw.swing.jtree.autotree;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;
public class DnDBorderFactory
{
  static class DropBehindAllowedBorder
    implements Border
  {
    private static Insets insets = new Insets(0,0,3,0);
    private ImageIcon plusIcon;
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
        g.setColor( Color.GRAY );
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
  class DropNotAllowedBorder
    implements Border
  {
    private Insets insets = new Insets(0,0,0,0);
    private ImageIcon plusIcon;
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
  public DnDBorderFactory()
  {
    setDropBehindAllowedBorder( new DropBehindAllowedBorder() ); // DropOnFolderBorder() );
    setDropNotAllowedBorder( new DropNotAllowedBorder() );
    setOffsetBorder( new OffsetBorder() );
    setEmptyBorder( BorderFactory.createEmptyBorder() );
    setDropInAllowedBorder(new DropInAllowedBorder());
  }
  private Border dropBehindAllowedBorder;
  private Border dropInAllowedBorder;
public Border getDropInAllowedBorder() {
    return dropInAllowedBorder;
}
public void setDropInAllowedBorder(Border dropInAllowedBorder) {
    this.dropInAllowedBorder = dropInAllowedBorder;
}
  public Border getDropBehindAllowedBorder()
  {
    return this.dropBehindAllowedBorder;
  }
  public void setDropBehindAllowedBorder(Border dropAllowedBorder)
  {
    this.dropBehindAllowedBorder = dropAllowedBorder;
  }
  private Border dropNotAllowedBorder;
  public Border getDropNotAllowedBorder()
  {
    return this.dropNotAllowedBorder;
  }
  public void setDropNotAllowedBorder(Border dropNotAllowedBorder)
  {
    this.dropNotAllowedBorder = dropNotAllowedBorder;
  }
  private Border offsetBorder;
  public Border getOffsetBorder()
  {
    return this.offsetBorder;
  }
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
