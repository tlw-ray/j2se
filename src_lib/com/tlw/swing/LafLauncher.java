package com.tlw.swing;
//尚未完成！
//可以通过指定某种LookAndFeel运行某个窗体程序
public class LafLauncher {
    public static void main(String[] args) {
    }
}
/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
*   XP Look and Feel                                                           *
*                                                                              *
*  (C) Copyright 2002, by Stefan Krause                                        *
*                                                                              *
*                                                                              *
*   This library is free software; you can redistribute it and/or modify it    *
*   under the terms of the GNU Lesser General Public License as published by   *
*   the Free Software Foundation; either version 2.1 of the License, or (at    *
*   your option) any later version.                                            *
*                                                                              *
*   This library is distributed in the hope that it will be useful,            *
*   but WITHOUT ANY WARRANTY; without even the implied warranty of             *
*   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.                       *
*   See the GNU Lesser General Public License for more details.                *
*                                                                              *
*   You should have received a copy of the GNU General Public License along    *
*   with this program; if not, write to the Free Software Foundation, Inc.,    *
*   59 Temple Place, Suite 330, Boston, MA 02111-1307, USA.                    *
*                                                                              *
* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
/**
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Method;

import javax.swing.UIManager;

import com.stefankrause.xplookandfeel.XPLookAndFeel;


 *
 * A launcher for the XP Look and Feel. Just add Launcher in front
 * of the real classname in your java command line and it'll set the
 * XP look and feel as the default and will not permit any changes...
 *

public class Launcher {
        public static void main(String[] args) throws Exception {
                System.setProperty("sun.java2d.ddscale", "true");
                UIManager.setLookAndFeel("com.stefankrause.xplookandfeel.XPLookAndFeel");

        UIManager.addPropertyChangeListener(
          new PropertyChangeListener() {
          public void propertyChange(PropertyChangeEvent event) {
            Object oldLF = event.getOldValue();
            Object newLF = event.getNewValue();

            if ((newLF instanceof XPLookAndFeel) == false) {
              try {
                UIManager.setLookAndFeel(new XPLookAndFeel());
              } catch (Exception e) {
                e.printStackTrace();
              }
            }

          }
        });
                Class c=Class.forName(args[0]);
                Method m=c.getMethod("main",new Class[] { String[].class });

                String[] copyOfArgs=new String[ args.length -1];
                for (int i = 1; i < args.length; i++) {
                        copyOfArgs[i-1]=args[i];
                }
                m.invoke(c,new Object[] { copyOfArgs });
        }
}
*/
