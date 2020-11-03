package com.tlw.swing;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintStream;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.Document;

public class JTextAreaConsole extends JTextArea {
	private static final long serialVersionUID = 881696257447657598L;
	static final int SLEEP_VALUE=50;
	public JTextAreaConsole(InputStream[] inStreams) {
        for (int i = 0; i < inStreams.length; ++i)
            startConsoleReaderThread(inStreams[i]);
    }

    public JTextAreaConsole(){
		try {
			LoopedStreams ls = new LoopedStreams();
	        PrintStream ps = new PrintStream(ls.getOutputStream());
	        System.setOut(ps);
	        System.setErr(ps);
	        startConsoleReaderThread(ls.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    private void startConsoleReaderThread(InputStream inStream) {
        final BufferedReader br = new BufferedReader(new InputStreamReader(
                inStream));
        new Thread(new Runnable() {
            public void run() {
                StringBuffer sb = new StringBuffer();
                try {
                    String s;
                    Document doc = getDocument();
                    while ((s = br.readLine()) != null) {
                        boolean caretAtEnd = false;
                        caretAtEnd = getCaretPosition() == doc.getLength() ? true
                                : false;
                        sb.setLength(0);
                        append(sb.append(s).append('\n').toString());
                        if (caretAtEnd)
                            setCaretPosition(doc.getLength());
                        Thread.sleep(SLEEP_VALUE);
                    }
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "BufferedReader"
                            + e);
                    System.exit(1);
                }catch(Exception e){
                	e.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        JFrame f = new JFrame("ConsoleTextArea");
        JTextAreaConsole consoleTextArea = new JTextAreaConsole();
        consoleTextArea.setFont(java.awt.Font.decode("monospaced"));
        f.getContentPane().add(new JScrollPane(consoleTextArea),
                java.awt.BorderLayout.CENTER);
        f.setBounds(50, 50, 300, 300);
        f.setVisible(true);

        f.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                System.exit(0);
            }
        });

        startWriterTestThread("#1", System.err, 200, 50);
        startWriterTestThread("#2", System.out, 300, 50);
        startWriterTestThread("#3", System.out, 400, 50);
        startWriterTestThread("#4", System.out, 500, 50);
        startWriterTestThread("#5", System.err, 600, 50);
    }

    private static void startWriterTestThread(final String name,
            final PrintStream ps, final int delay, final int count) {
        new Thread(new Runnable() {
            public void run() {
                for (int i = 1; i <= count; ++i) {
                    ps.println("Thread" + name + ", hello !, i=" + i);
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException e) {
                    }
                }
            }
        }).start();
    }
    class LoopedStreams {
        private PipedOutputStream pos = new PipedOutputStream();

        private boolean keepRunning = true;

        private ByteArrayOutputStream baos = new ByteArrayOutputStream() {
            public void close() {
                keepRunning = false;
                try {
                    super.close();
                    pos.close();
                } catch (IOException e) {
                    System.exit(1);
                }
            }
        };

        private PipedInputStream pis = new PipedInputStream() {
            public void close() {
                keepRunning = false;
                try {
                    super.close();
                } catch (IOException e) {
                    System.exit(1);
                }
            }
        };
        public LoopedStreams() throws IOException {
            pos.connect(pis);
            startByteArrayReaderThread();
        }
        public InputStream getInputStream() {
            return pis;
        }

        public OutputStream getOutputStream() {
            return baos;
        } 

        private void startByteArrayReaderThread() {
            new Thread(new Runnable() {
                public void run() {
                    while (keepRunning) {
                        if (baos.size() > 0) {
                            byte[] buffer = null;
                            synchronized (baos) {
                                buffer = baos.toByteArray();
                                baos.reset();
                            }
                            try {
                                pos.write(buffer, 0, buffer.length);
                            } catch (IOException e) {
                                System.exit(1);
                            }
                        } else
                            try {
                                Thread.sleep(SLEEP_VALUE);
                            } catch (InterruptedException e) {
                            }
                    }
                }
            }).start();
        }
    }
}
