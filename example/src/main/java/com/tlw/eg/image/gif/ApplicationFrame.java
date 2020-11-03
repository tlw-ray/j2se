package com.tlw.eg.image.gif;


import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ApplicationFrame extends JFrame {
	private static final long serialVersionUID = -1791434233701559692L;
	private JPanel panel = new JPanel(new ViewLayout());

    public ApplicationFrame(GifFrame[] frames) {
        panel.setBackground(Color.BLACK);
        JScrollPane jsp = new JScrollPane();
        jsp.getViewport().add(panel);
        getContentPane().add(jsp);
        for (int i = 0; i < frames.length; i++) {
            panel.add("", new ImageView(frames[i], i + 1));
        }
    }

    private class ViewLayout implements LayoutManager {

        private List<Component> list = new ArrayList<Component>();

        public void addLayoutComponent(String name, Component comp) {
            list.add(comp);
        }

        public void layoutContainer(Container parent) {
            int size = list.size();
            int offsetX = 0;
            int offsetY = 0;
            int index = 0;
            do {
                Component comp = list.get(index);
                comp.setBounds(offsetX, offsetY, comp.getPreferredSize().width, comp.getPreferredSize().height);
                offsetX += comp.getPreferredSize().width + 5;
                index++;
                if (index % 5 == 0) {
                    offsetX = 0;
                    offsetY += getMaxHeight(index, size) + 5;
                }
            } while (index < size);
        }

        public Dimension minimumLayoutSize(Container parent) {
            // TODO Auto-generated method stub
            return null;
        }

        public Dimension preferredLayoutSize(Container target) {
            synchronized (target.getTreeLock()) {
                int numbers = list.size();
                int width = getMaxWidth(numbers);
                int height = 0;
                for (int i = 0; i < numbers; i += 5) {
                    height += getMaxHeight(i, numbers) + 5;
                }
                return new Dimension(width, height);
            }
        }

        private int getMaxWidth(int numbers) {
            int maxWidth = 0;
            int temp = 0;
            for (int i = 0; i < numbers; i++) {
                temp += list.get(i).getPreferredSize().width + 5;
                if ((i % 4 == 0 || i == numbers - 1) && i != 0) {
                    if (temp > maxWidth) {
                        maxWidth = temp;
                    }
                    temp = 0;
                }
            }
            return maxWidth;
        }

        private int getMaxHeight(int headOfLine, int numbers) {
            int maxHeight = 0;
            for (int i = 0; i < 5 && i + headOfLine < numbers; i++) {
                int height = list.get(i).getPreferredSize().height;
                if (height > maxHeight) {
                    maxHeight = height;
                }
            }
            return maxHeight;
        }

        public void removeLayoutComponent(Component comp) {
        }
    }
}