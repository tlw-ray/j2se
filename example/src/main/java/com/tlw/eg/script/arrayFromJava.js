// Get the Java File type object
var File = Java.type("java.io.File");
// Create a Java array of File objects
var listCurDir = new File(".").listFiles();
// Convert the Java array to a JavaScript array
var jsList = Java.from(listCurDir);
// Print the JavaScript array
print(jsList);
