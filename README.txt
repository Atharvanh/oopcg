OOPCG JAVA PRACTICALS 1-10

All ten programs are standalone and contain no comments.
Requires a JDK (Java Development Kit), Java 8 or later.
No external libraries are required. Graphics use built-in Swing/AWT.

HOW TO RUN
Open a terminal in this folder. Compile and run one practical:
javac Practical01.java
java Practical01
Replace 01 with 02 through 10 for the other practicals.
File names and public class names must match exactly.
Graphics programs 06-09 take input in the terminal, then open a window.
Use a desktop environment for graphics windows. Close the window to finish.
Practical10 opens its pattern directly. To save a PNG instead:
java Practical10 --save

PRACTICAL CONTENTS
01 Dynamic calculator: +, -, *, /, %, conditional logic and error handling.
02 No-argument, parameterized and copy-style constructors.
   The explicit no-argument constructor is often called a default constructor
   in lab exercises. Strictly, Java's default constructor is compiler-generated
   only when a class declares no constructor.
03 Single, multilevel and hierarchical class inheritance using extends.
04 Arithmetic, array bounds, number format, input mismatch, checked IOException,
   try/catch/finally and throw. Run again to select another case.
05 Method overloading, overriding and built-in + for numbers and strings.
   Java does not support user-defined operator overloading.
06 User-entered polygon: translation, rotation, X/Y shearing and X/Y scaling.
07 Equilateral triangle or rhombus: all four transformations.
08 Rotation about the X-axis and an arbitrary 2D pivot.
09 Cohen-Sutherland clipping with window and line entered by the user.
10 DDA dotted/thick rectangles and Bresenham dashed/solid diamonds.

GRAPHICS CONVENTIONS AND ASSUMPTIONS
06-09: X increases right, Y increases up; origin is window center.
One coordinate unit is one pixel. Use coordinates roughly between -250 and 250.
Large transformations can move a shape outside the window.
Blue = original, red = transformed/clipped. Coincident edges appear red.
Rotation angles are degrees, positive counterclockwise in the XY plane.
Shearing uses x'=x+shx*y, y'=y+shy*x simultaneously from original coordinates.
Set unused shear to 0. Set unchanged scale to 1. Scaling is about the origin.
Translation and scaling accept independent X and Y values.
07: choose 1 for an equilateral triangle, 2 for a rhombus; side length must be
positive. Rhombus interior angle must be strictly between 0 and 180 degrees.
08: a true rotation about X leaves the 2D plane. For initial z=0, it uses
x'=x, y'=y*cos(angle), z'=y*sin(angle). The program prints 3D coordinates and
draws the XY projection. A 180-degree rotation becomes reflection about X.
The second option rotates entirely in 2D about a user-entered pivot.
10: the referenced pattern picture was not supplied. A sample layout uses
two nested rectangles (dotted and thick) and two nested diamonds (dashed and
solid) to demonstrate all requested styles. It is not an exact reproduction
of an unavailable diagram. Practical10_pattern.png previews this layout.

SAMPLE INPUTS
01: 12 / 3
06: 3 vertices: 0 0, 100 0, 0 100; choose 2; angle 45.
07: shape 1; side 120; transformation 1; tx 40; ty 30.
08: 3 vertices: 0 0, 100 0, 0 100; choice 2; angle 90; pivot 20 20.
09: window -100 -100 100 100; line -200 50 200 50.
Enter numbers without commas. Follow the terminal prompts.

These are small educational programs. Except for the error-handling exercises,
enter numeric values of the requested type. Input values are kept in memory.
See VIVA_ANSWERS.txt and TEST_RESULTS.txt for revision notes and test coverage.
