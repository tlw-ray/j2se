package com.tlw.eg.image.gif;


import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFileChooser;
import javax.swing.WindowConstants;
import javax.swing.filechooser.FileFilter;

import com.sun.imageio.plugins.gif.GIFImageMetadata;

public class GifAnalysis {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFileChooser fc = new JFileChooser();

                fc.addChoosableFileFilter(new ImageFilter());
                fc.setAcceptAllFileFilterUsed(false);
                int returnVal = fc.showDialog(null, "Attach");
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        File gifFile = fc.getSelectedFile();
                        final GifFrame[] frames;
                        ImageReader reader = null;
                        ImageInputStream imageIn = ImageIO.createImageInputStream(gifFile);
                        Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("gif");
                        if (iter.hasNext()) {
                            reader = iter.next();
                        }
                        reader.setInput(imageIn, false);
                        int count = reader.getNumImages(true);
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
                        }
                        ApplicationFrame frm = new ApplicationFrame(frames);
                        frm.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                        frm.setSize(500, 400);
                        frm.setVisible(true);
                    } catch (IOException e) {
                    }
                } else {
                    System.exit(0);
                }
            }
        });
    }
}

class ImageFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = getExtension(f);
        if (extension != null) {
            return extension.equalsIgnoreCase("gif");
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "*.gif";
    }

    public String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}