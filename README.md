# M-LOGO – A Free Implementation of the LOGO Interpreter

## Motivation and Objectives

LOGO was the first programming language I encountered during middle school. Its creator, **Seymour Papert**, developed it primarily for educational purposes.  
Driven by nostalgia and the desire to challenge myself with **object-oriented programming in Java**, I decided to create my own implementation of a **LOGO interpreter**.  

The goal of this project is to develop a stable and functional interpreter capable of:  

- **Executing the original LOGO commands**.  
- Supporting the **creation of custom procedures**.  
- Providing an **intuitive graphical interface** for user interaction.  

---

## Minimal Required Features

- Implementation of the **core original LOGO commands**.  
- **Syntactic validation** of user input to ensure command correctness.  
- Ability to **create and execute user-defined procedures**.  

---

## Optional Features

- **Saving and loading programs** to enable **code persistence**.  
- **Printing drawings** generated by the interpreter.  

---

## Main Challenges

- **Efficient management of procedures**: Implementing a **representation and execution system for procedures in memory** to ensure **good performance**.  
- **Robustness**: The software should handle **invalid input and unexpected situations** without crashing while providing user feedback.  
- **Intuitive graphical interface**: Leveraging my experience with **Java**, I aim to design a **clear and user-friendly UI** that simplifies interaction with the LOGO interpreter.  

---

## Implementation

### Design Pattern Used

This project follows the **Model-View-Controller (MVC)** architecture, ensuring a clean separation between:  

- **Model**: Handles core logic and maintains the turtle's state.  
- **View**: Manages graphical rendering (drawing the turtle and executed commands).  
- **Controller**: Interprets user commands and updates the model and view accordingly.  

Additionally, the **Command Design Pattern** is used to encapsulate individual LOGO commands as objects.  
Each command (e.g., `Forward`, `Left`, `Repeat`) is implemented as a separate class with an `execute()` method, allowing flexibility in command execution and future expansion.

---

### Implemented Commands

The following table lists the **currently implemented** commands, their syntax, and their equivalent LOGO syntax:  

| **Command** | **Syntax (M-LOGO)**     | **Equivalent LOGO Command** | **Description**                                 |
| ----------- | ----------------------- | --------------------------- | ----------------------------------------------- |
| Forward     | `FORWARD <steps>`       | `FD <steps>`                | Moves the turtle forward by `<steps>` units.    |
| Back        | `BACK <steps>`          | `BK <steps>`                | Moves the turtle backward by `<steps>` units.   |
| Left        | `LEFT <degrees>`        | `LT <degrees>`              | Rotates the turtle left by `<degrees>`.         |
| Right       | `RIGHT <degrees>`       | `RT <degrees>`              | Rotates the turtle right by `<degrees>`.        |
| PenUp       | `PENUP`                 | `PU`                        | Lifts the pen, so movement does not draw lines. |
| PenDown     | `PENDOWN`               | `PD`                        | Lowers the pen, enabling drawing.               |
| Repeat      | `REPEAT <n> [commands]` | `REPEAT <n> [commands]`     | Repeats the given commands `<n>` times.         |

---

### To-Do Commands

The following table lists **important LOGO commands not yet implemented**, which could be added in future versions:

| **Command** | **LOGO Syntax**              | **Description**                                                       |
| ----------- | ---------------------------- | --------------------------------------------------------------------- |
| Home        | `HOME`                       | Moves the turtle back to the starting position (0,0) without drawing. |
| ClearScreen | `CS`                         | Clears the drawing area and resets the turtle position.               |
| HideTurtle  | `HT`                         | Hides the turtle cursor from the drawing area.                        |
| ShowTurtle  | `ST`                         | Makes the turtle cursor visible again.                                |
| SetPosition | `SETPOS [x y]`               | Moves the turtle to absolute coordinates `[x, y]`.                    |
| SetHeading  | `SETH <angle>`               | Sets the turtle's direction to an absolute heading `<angle>`.         |
| Wait        | `WAIT <ms>`                  | Pauses execution for `<ms>` milliseconds.                             |
| Print       | `PRINT "text"`               | Displays a message on the console.                                    |
| Make        | `MAKE "var value`            | Assigns a value to a variable.                                        |
| If          | `IF condition [commands]`    | Executes the block of commands if the condition is true.              |
| While       | `WHILE condition [commands]` | Repeats commands while the condition is true.                         |

---

## Example Usage

Each command is executed **one at a time**, even within procedures or loops.

#### **1. Drawing a Nested Square Pattern**

```logo
TO SQUARE
  FORWARD 50
  RIGHT 90
  FORWARD 50
  RIGHT 90
  FORWARD 50
  RIGHT 90
  FORWARD 50
  RIGHT 90
END

TO SQUARE_PATTERN
  SQUARE
  RIGHT 36
END

REPEAT 5 [ SQUARE_PATTERN ]
```

The goal of this project is to develop a stable and functional interpreter capable of:  

- **Executing the original LOGO commands**.  
- Supporting the **creation of custom procedures**.  
- Providing an **intuitive graphical interface** for user interaction.  

---

## Minimal Required Features

- Implementation of the **core original LOGO commands**.  
- **Syntactic validation** of user input to ensure command correctness.  
- Ability to **create and execute user-defined procedures**.  

---

## Optional Features

- **Saving and loading programs** to enable **code persistence**.  
- **Printing drawings** generated by the interpreter.  

---

## Main Challenges

- **Efficient management of procedures**: Implementing a **representation and execution system for procedures in memory** to ensure **good performance**.  
- **Robustness**: The software should handle **invalid input and unexpected situations** without crashing while providing user feedback.  
- **Intuitive graphical interface**: Leveraging my experience with **Java**, I aim to design a **clear and user-friendly UI** that simplifies interaction with the LOGO interpreter.  

---

## Implementation

### Design Pattern Used

This project follows the **Model-View-Controller (MVC)** architecture, ensuring a clean separation between:  

- **Model**: Handles core logic and maintains the turtle's state.  
- **View**: Manages graphical rendering (drawing the turtle and executed commands).  
- **Controller**: Interprets user commands and updates the model and view accordingly.  

Additionally, the **Command Design Pattern** is used to encapsulate individual LOGO commands as objects.  
Each command (e.g., `Forward`, `Left`, `Repeat`) is implemented as a separate class with an `execute()` method, allowing flexibility in command execution and future expansion.

---

### Implemented Commands

The following table lists the **currently implemented** commands, their syntax, and their equivalent LOGO syntax:  

| **Command** | **Syntax (M-LOGO)**     | **Equivalent LOGO Command** | **Description**                                 |
| ----------- | ----------------------- | --------------------------- | ----------------------------------------------- |
| Forward     | `FORWARD <steps>`       | `FD <steps>`                | Moves the turtle forward by `<steps>` units.    |
| Back        | `BACK <steps>`          | `BK <steps>`                | Moves the turtle backward by `<steps>` units.   |
| Left        | `LEFT <degrees>`        | `LT <degrees>`              | Rotates the turtle left by `<degrees>`.         |
| Right       | `RIGHT <degrees>`       | `RT <degrees>`              | Rotates the turtle right by `<degrees>`.        |
| PenUp       | `PENUP`                 | `PU`                        | Lifts the pen, so movement does not draw lines. |
| PenDown     | `PENDOWN`               | `PD`                        | Lowers the pen, enabling drawing.               |
| Repeat      | `REPEAT <n> [commands]` | `REPEAT <n> [commands]`     | Repeats the given commands `<n>` times.         |

---

Each command is executed **one at a time**, and procedures **cannot call other procedures**.

---

### **1. Drawing a Square**

```logo
TO SQUARE
  FORWARD 50
  RIGHT 90
  FORWARD 50
  RIGHT 90
  FORWARD 50
  RIGHT 90
  FORWARD 50
  RIGHT 90
END

REPEAT 5 [ SQUARE RIGHT 36 ]
```

## Motivation and Objectives

LOGO was the first programming language I encountered during middle school. Its creator, **Seymour Papert**, developed it primarily for educational purposes.  
Driven by nostalgia and the desire to challenge myself with **object-oriented programming in Java**, I decided to create my own implementation of a **LOGO interpreter**.  

The goal of this project is to develop a stable and functional interpreter capable of:  

- **Executing the original LOGO commands**.  
- Supporting the **creation of custom procedures**.  
- Providing an **intuitive graphical interface** for user interaction.  

---

## Minimal Required Features

- Implementation of the **core original LOGO commands**.  
- **Syntactic validation** of user input to ensure command correctness.  
- Ability to **create and execute user-defined procedures**.  

---

## Optional Features

- **Saving and loading programs** to enable **code persistence**.  
- **Printing drawings** generated by the interpreter.  

---

## Main Challenges

- **Efficient management of procedures**: Implementing a **representation and execution system for procedures in memory** to ensure **good performance**.  
- **Robustness**: The software should handle **invalid input and unexpected situations** without crashing while providing user feedback.  
- **Intuitive graphical interface**: Leveraging my experience with **Java**, I aim to design a **clear and user-friendly UI** that simplifies interaction with the LOGO interpreter.  

---

## Implementation

### Design Pattern Used

This project follows the **Model-View-Controller (MVC)** architecture, ensuring a clean separation between:  

- **Model**: Handles core logic and maintains the turtle's state.  
- **View**: Manages graphical rendering (drawing the turtle and executed commands).  
- **Controller**: Interprets user commands and updates the model and view accordingly.  

Additionally, the **Command Design Pattern** is used to encapsulate individual LOGO commands as objects.  
Each command (e.g., `Forward`, `Left`, `Repeat`) is implemented as a separate class with an `execute()` method, allowing flexibility in command execution and future expansion.

---

### Implemented Commands

The following table lists the implemented commands, their syntax, and their equivalent LOGO syntax:  

| **Command** | **Syntax (M-LOGO)**     | **Equivalent LOGO Command** | **Description**                                 |
| ----------- | ----------------------- | --------------------------- | ----------------------------------------------- |
| Forward     | `FORWARD <steps>`       | `FD <steps>`                | Moves the turtle forward by `<steps>` units.    |
| Back        | `BACK <steps>`          | `BK <steps>`                | Moves the turtle backward by `<steps>` units.   |
| Left        | `LEFT <degrees>`        | `LT <degrees>`              | Rotates the turtle left by `<degrees>`.         |
| Right       | `RIGHT <degrees>`       | `RT <degrees>`              | Rotates the turtle right by `<degrees>`.        |
| PenUp       | `PENUP`                 | `PU`                        | Lifts the pen, so movement does not draw lines. |
| PenDown     | `PENDOWN`               | `PD`                        | Lowers the pen, enabling drawing.               |
| Repeat      | `REPEAT <n> [commands]` | `REPEAT <n> [commands]`     | Repeats the given commands `<n>` times.         |

---

### Example Usage

Each command is executed **one at a time**, even within procedures or loops.

#### **Basic Movement**

```logo
FORWARD 50
RIGHT 90
FORWARD 50
```
