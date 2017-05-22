// Create a JavaScript array
var anArray = [1, "13", false];

// Convert the JavaScript array to a Java int[] array
var javaIntArray = Java.to(anArray, "int[]");
print(javaIntArray[0]); // prints the number 1
print(javaIntArray[1]); // prints the number 13
print(javaIntArray[2]); // prints the number 0

// Convert the JavaScript array to a Java String[] array
var javaStringArray = Java.to(anArray, Java.type("java.lang.String[]"));
print(javaStringArray[0]); // prints the string "1"
print(javaStringArray[1]); // prints the string "13"
print(javaStringArray[2]); // prints the string "false"

// Convert the JavaScript array to a Java Object[] array
var javaObjectArray = Java.to(anArray);
print(javaObjectArray[0]); // prints the number 1
print(javaObjectArray[1]); // prints the string "13"
print(javaObjectArray[2]); // prints the boolean value "false"
