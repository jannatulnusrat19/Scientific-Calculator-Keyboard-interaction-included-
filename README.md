**Description**

1. Calculator Initialization:
   - The calculator is built using **Java Swing** and initialized with a modern graphical user interface (GUI).  
   - It supports both **basic arithmetic operations** and **advanced scientific calculations**.

2. User Input: 
   - The calculator accepts input through:  
     - **Button Clicks:** Using the mouse for interaction.  
     - **Keyboard Input:** Allows users to type numbers and operators directly.

3. Operations: 
   - Basic Operations:
     - Addition (`+`), Subtraction (`-`), Multiplication (`*`), and Division (`/`).  
   - Scientific Functions: 
     - Square Root (`√`): Computes the square root of a number.  
     - Exponentiation (`^`): Raises a base number to a given power.  
     - Trigonometric Functions: Includes `sin`, `cos`, and `tan`.  
     - Logarithmic Function: Computes the log base 10 of a number.  
     - Pi (`π`): Inserts the value of π (3.14159) for calculations.

4. History Management:  
   - The calculator maintains a history of calculations, which can be viewed by clicking the **"History"** button.  
   - Each operation performed is saved for review.

5. Error Handling:
   - Division by zero and invalid inputs are handled gracefully with user-friendly error messages.

6. **User Interface**: 
   - A clean and interactive GUI with buttons for input.  
   - Display area for showing user input and calculation results.
---
 ### 1. **Calculator()**  
- **Purpose:** The constructor initializes the calculator's user interface using **JFrame**, **JTextField**, and **JPanel** components.  
- **Key Actions:**  
   - Creates the display area and buttons.  
   - Adds event listeners for buttons and key inputs.  
   - Sets the calculator's layout, size, and design.
---
### 2. **Calculate()**  
- **Purpose:** Performs basic arithmetic operations (+, -, *, /) based on the selected operator.  
- **Key Actions:**  
   - Handles division by zero and invalid operations.  
   - Updates the result variable based on operator.

---
### 3. **ButtonClickListener (Inner Class)**  
- **Purpose:** Listens to button clicks and handles user input.  
- **Key Actions:**  
   - Appends digits and operators to the display.  
   - Performs calculations when = is clicked.  
   - Supports special functions like sqrt, sin, cos, tan, log, and pi.  
   - Clears the display or shows calculation history.

---

### 4. **KeyAdapter (KeyListener)**  
- **Purpose:** Allows users to interact with the calculator using keyboard inputs.  
- **Key Actions:**  
   - Handles numeric keys, operators (+, -, *, /), and special keys like Backspace.  
   - Supports scientific functions with specific keypresses (s for sqrt, p for pi).

---

### 5. **main()**  
- **Purpose:** Launches the calculator program by invoking the Calculator constructor on the Swing event thread.

---

### Special Function Handling in **ButtonClickListener**:  
- **Square Root (sqrt):** Computes √num1 using Math.sqrt.  
- **Power (^):** Allows for exponentiation.  
- **Trigonometric Functions:**  
   - sin: Calculates sin in degrees using Math.sin with Math.toRadians.  
   - cos: Calculates cos in degrees.  
   - tan: Calculates tan in degrees.  
- **Logarithm (log):** Calculates base-10 logarithm using Math.log10.  
- **Pi (pi):** Displays the value of π using Math.PI.

---

  
