// Create a JavaImporter object with specified packages and classes to import
var Gui = new JavaImporter(java.awt, javax.swing);

// Pass the JavaImporter object to the "with" statement and access the classes
// from the imported packages by their simple names within the statement's body
with (Gui) {
    var awtframe = new Frame("AWT Frame");
    var jframe = new JFrame("Swing JFrame");
    
    awtframe.setSize(400,300);
    jframe.setSize(500,600);
    
    awtframe.setVisible(true);
    jframe.setVisible(true);
};
