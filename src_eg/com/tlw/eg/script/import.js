// Load compatibility script
load("nashorn:mozilla_compat.js");
// Import the java.awt package
importPackage(java.awt);
// Import the java.awt.Frame class
importClass(java.awt.Frame);
// Create a new Frame object
var frame = new java.awt.Frame("hello");
frame.setSize(400, 300);
// Call the setVisible() method
frame.setVisible(true);
// Access a JavaBean property
print("frame.title: "+frame.title);
print("frame.getTitle(): "+frame.getTitle());
