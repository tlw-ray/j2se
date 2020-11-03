/* * Created on 2005-2-19 */
package com.tlw.swing;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.util.Enumeration;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;
//来自http://blog.csdn.net/dyhml/archive/2005/02/19/294128.aspx
/** * 提供对AWT,Swing有用的静态方法 *  * @author  <a href="http:\\blog.csdn.net\dyhml">VirusCamp</a> */
public class SwingLafUtil { /**     * 由此类中方法使用的默认 java.awt.Toolkit     */
    public static final Toolkit toolkit = Toolkit.getDefaultToolkit();
    /**     * 将window全屏化,对Window,Frame,Dialog,JWindow,JFrame,JDialog有效     * @param window 全屏化的window     * @see Window,Frame,Dialog,JWindow,JFrame,JDialog     */
    public static void setSizeFullOfScreen(Window window) {
        Dimension screenSize = toolkit.getScreenSize();
        window.setBounds(0, 0, screenSize.width, screenSize.height);
    }
    /**     * 将window设置在屏幕中间,对Window,Frame,Dialog,JWindow,JFrame,JDialog有效     * @param window 设置在屏幕中间的window     * @see Window,Frame,Dialog,JWindow,JFrame,JDialog     */
    public static void setToScreenCenter(Window window) {
        Dimension screenSize = toolkit.getScreenSize();
        Dimension windowSize = window.getSize();
        if (windowSize.height > screenSize.height)
            windowSize.height = screenSize.height;
        if (windowSize.width > screenSize.width)
            windowSize.width = screenSize.width;
        window.setLocation((screenSize.width - windowSize.width) / 2,
                           (screenSize.height - windowSize.height) / 2);
    }
    /**     * 设置全局字体     * @param font 全局字体     */
    public static void initGlobalFontSetting(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration keys = UIManager.getDefaults().keys();
                                keys.hasMoreElements();
                ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource)
                UIManager.put(key, fontRes);
        }
    } /**     * 在Component上设置光标(Cursor)     * @param c 在此Component上设置光标(Cursor)     * @param image 设置的光标(Cursor)图案     */
    public static void setCursor(Component c, Image image) {
        Cursor cursor = toolkit.createCustomCursor(image, new Point(0, 0), null);
        c.setCursor(cursor);
    }
    /**     * 设置全局LookAndFeel,此后建立的Swing部件使用此LookAndFeel     * @param lafstr 设置的LookAndFeel     * @return 0:设置成功,1:设置失败     */
    public static int setGlobalLookAndFeel(String lafstr) {
        try {
            UIManager.setLookAndFeel(lafstr);
            return 0;
        } catch (Exception e) {
            return 1;
        }
    } /**     * 此后建立的JFrame,JDialog是否使用LookAndFeel的窗口装饰(标题栏,边框等)     */
    private static boolean isUndecorated = false;
    /**     * 返回此后建立的JFrame,JDialog是否使用LookAndFeel的窗口装饰(标题栏,边框等)     * @return 此后建立的JFrame,JDialog是否使用LookAndFeel的窗口装饰(标题栏,边框等)     */public static boolean
            isUndecorated() {
        return isUndecorated;
    }
    /**     * 设置此后建立的JFrame,JDialog是否使用LookAndFeel的窗口装饰(标题栏,边框等)     * @param 是否此后建立的JFrame,JDialog使用LookAndFeel的窗口装饰(标题栏,边框等)     */
    public static void setUndecorated(boolean undecorated) {
        LookAndFeel laf = UIManager.getLookAndFeel();
        SwingLafUtil.isUndecorated = undecorated;
        JFrame.setDefaultLookAndFeelDecorated(undecorated &&
                                              laf.getSupportsWindowDecorations());
        JDialog.setDefaultLookAndFeelDecorated(undecorated &&
                                               laf.getSupportsWindowDecorations());
    }
    /**     * 设置JFrame是否使用LookAndFeel的窗口装饰(标题栏,边框等)     * @param 是否使用LookAndFeel的窗口装饰(标题栏,边框等)     */
    public static void setUndecorated(JFrame frame, boolean undecorated) {
        if (frame.isDisplayable())
            frame.dispose();
        frame.setUndecorated(undecorated &&
                             UIManager.getLookAndFeel().getSupportsWindowDecorations());
        JRootPane jRootPane = frame.getRootPane();
        jRootPane.setWindowDecorationStyle(jRootPane.getWindowDecorationStyle());
        if (frame.isDisplayable())
            frame.setVisible(true);
    }
    /**     * 设置JDialog是否使用LookAndFeel的窗口装饰(标题栏,边框等)     * @param 是否使用LookAndFeel的窗口装饰(标题栏,边框等)     */
    public static void setUndecorated(JDialog dialog, boolean undecorated) {
        if (dialog.isDisplayable())
            dialog.dispose();
        dialog.setUndecorated(undecorated &&
                              UIManager.getLookAndFeel().getSupportsWindowDecorations());
        JRootPane jRootPane = dialog.getRootPane();
        jRootPane.setWindowDecorationStyle(jRootPane.getWindowDecorationStyle());
        if (dialog.isDisplayable())
            dialog.setVisible(true);
    }
    /**     * 更新本程序建立的所有JFrame的LookAndFeel     */
    public static void updateUI() {
        Frame[] frames = Frame.getFrames();
        for (int i = 0;
                     i < frames.length;
                     i++)
            updateUI(frames[i]);
    }
    /**     * 更新Component的LookAndFeel     * @param target 操作对象     */
    public static void updateUI(Component target) {
        if (target instanceof JFrame)
            updateUI((JFrame) target, isUndecorated);
        if (target instanceof JDialog)
            updateUI((JDialog) target, isUndecorated);
        SwingUtilities.updateComponentTreeUI(target);
    }
    /**     * 更新JFrame的LookAndFeel,改变JFrame的边框,标题     * @param frame 操作对象     * @param undecorated 是否使用LookAndFeel的窗口装饰(标题栏,边框等)     */

    public static void updateUI(JFrame frame, boolean undecorated) {
        setUndecorated(frame, undecorated);
        SwingUtilities.updateComponentTreeUI(frame);
    }
    /**     * 更新JDialog的LookAndFeel,改变JDialog的边框,标题     * @param dialog 操作对象     * @param undecorated 是否使用LookAndFeel的窗口装饰(标题栏,边框等)     */
    public static void updateUI(JDialog dialog, boolean undecorated) {
        setUndecorated(dialog, undecorated);
        SwingUtilities.updateComponentTreeUI(dialog);
    }
}
