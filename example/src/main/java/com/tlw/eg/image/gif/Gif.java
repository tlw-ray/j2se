package com.tlw.eg.image.gif;


import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.sun.imageio.plugins.gif.GIFImageMetadata;

public class Gif extends JComponent {
	private static final long serialVersionUID = -6850421423588879685L;
	private ImageReader reader;
    private int count = 0;
    private GifFrame[] frames;
    private Map<Integer, Integer[]> frameMap = new HashMap<Integer, Integer[]>();
    private int index = 0;
    private int delayFactor;

    @Override
    protected void paintComponent(Graphics g) {
        // TODO fill background if part of gif are transparent.
        g.drawImage(frames[0].image, frames[0].x, frames[0].y, this);
        if (index > 0) {
            Integer[] array = frameMap.get(index);
            for (Integer i : array) {
                g.drawImage(frames[i].image, frames[i].x, frames[i].y, this);
            }
        }
    }

    private int getFirstIndex(int index) {
        int tempIndex = index;
        while (tempIndex > 1) {
            if (tempIndex - 1 > 0 && frames[tempIndex - 1].disposalMethod == 2) {
                return index;
            }
            tempIndex--;
        }
        return tempIndex;
    }

    public Gif(File gifFile, int delayFactor) {
        this.delayFactor = delayFactor;
        try {
            ImageInputStream imageIn = ImageIO.createImageInputStream(gifFile);
            Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("gif");
            if (iter.hasNext()) {
                reader = iter.next();
            }
            reader.setInput(imageIn, false);
            count = reader.getNumImages(true);
            frames = new GifFrame[count];
            for (int i = 0; i < count; i++) {
                frames[i] = new GifFrame();
                frames[i].image = reader.read(i);
                frames[i].x = ((GIFImageMetadata) reader.getImageMetadata(i)).imageLeftPosition;
                frames[i].y = ((GIFImageMetadata) reader.getImageMetadata(i)).imageTopPosition;
                frames[i].width = ((GIFImageMetadata) reader.getImageMetadata(i)).imageWidth;
                frames[i].height = ((GIFImageMetadata) reader.getImageMetadata(i)).imageHeight;
                frames[i].disposalMethod = ((GIFImageMetadata) reader.getImageMetadata(i)).disposalMethod;
                frames[i].delayTime = ((GIFImageMetadata) reader.getImageMetadata(i)).delayTime;
                if (frames[i].delayTime == 0) {
                    frames[i].delayTime = 1;
                }
            }
            for (int i = 1; i < count; i++) {
                if (frames[i].disposalMethod == 2) {
                    // restoreToBackgroundColor
                    frameMap.put(new Integer(i), new Integer[]{i});
                    continue;
                }
                // doNotDispose
                int firstIndex = getFirstIndex(i);
                List<Integer> l = new ArrayList<Integer>();
                for (int j = firstIndex; j <= i; j++) {
                    l.add(j);
                }
                frameMap.put(new Integer(i), l.toArray(new Integer[]{}));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Thread t = new Thread(new Delay());
        t.start();
    }

    private class Delay implements Runnable {

        public void run() {
            while (true) {
                repaint();
                try {
                    Thread.sleep(frames[index].delayTime * delayFactor);
                } catch (InterruptedException e) {
                }

                index++;
                if (index >= count) {
                    index = 0;
                }
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(640, 480);
    }
    
    public static void main(String[] args) {
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
            	URL u=Gif.class.getClassLoader().getResource("tlw/example/image/sample.gif");
            	File file=new File(u.getFile());
                JFrame frm = new JFrame();
                frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frm.getContentPane().add(new Gif(file, 5));
                frm.pack();
                frm.setVisible(true);
            }
        });
    }
}