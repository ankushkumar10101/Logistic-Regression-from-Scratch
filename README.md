# Logistic Regression in Java (From Scratch)

This Java program implements **Logistic Regression** using **Gradient Descent** from scratch, without relying on any third-party ML libraries.

---

## File Structure

```
.
├── Logistic_Regression.java
└── training_data/
    └── data.csv
```

---

## Overview

This program:
- Loads a dataset of feature values and binary class labels from a CSV file.
- Trains a logistic regression model using gradient descent and binary cross-entropy loss.
- Uses the sigmoid activation function for binary classification.
- Predicts the class for new data provided by the user.

---

##  Input Format

The `data.csv` file must follow this format:

```
x1,x2,x3,y
1.2,3.4,5.6,1
2.3,4.5,6.7,0
...
```

Each line contains:
- `x1, x2, x3`: Feature values
- `y`: Class label (0 or 1)

---

##  How the Code Works

### 1. `load_data(String filepath)`
- Reads the CSV file and skips the header.
- Stores feature values in `inputs[n][1][3]`
- Stores class labels in `y_actual[n]`

### 2. `train_model()`
- Initializes weights (`w1`, `w2`, `w3`) and bias `b` to small values.
- Applies **gradient descent** using the following:
  - **Sigmoid function**:  
    `sigma = 1 / (1 + e^(-z))` where `z = w1*x1 + w2*x2 + w3*x3 + b`
  - **Loss Function**: Binary Cross Entropy  
    `L = -[y * log(sigma) + (1 - y) * log(1 - sigma)]`
  - **Weight updates**:
    ```
    w1 = w1 - lr * grad_w1
    w2 = w2 - lr * grad_w2
    w3 = w3 - lr * grad_w3
    b  = b  - lr * grad_b
    ```
- Stops training when:
  - `avgLoss < 0.01`, or
  - Maximum iterations (10,000) are reached

### 3. `test_model(double x1, double x2, double x3)`
- Takes new feature inputs.
- Calculates sigmoid output.
- Returns 1 if predicted probability ≥ 0.5, else 0.

### 4. `main(String[] args)`
- Loads data from the CSV file.
- Trains the model.
- Prompts user to input values for x1, x2, and x3.
- Displays the predicted class.

---

## Example Output

```
Data Loaded Successfully.
Enter the value of x1, x2, x3
x1 = 1.5
x2 = 2.5
x3 = 3.5
For inputs x1 = 1.5, x2 = 2.5, x3 = 3.5 Predicted class: 1
```

---

## Requirements

- Java JDK 8 or higher
- A CSV file with training data located at `training_data/data.csv`

---

## How to Run

1. **Compile the program:**
   ```bash
   javac Logistic_Regression.java
   ```

2. **Run the program:**
   ```bash
   java Logistic_Regression
   ```

---

## Notes

- Ensure the CSV file has no missing or malformed values.
- You can tune:
  - `learning rate (lr)` for model convergence
  - `avgLoss` threshold for training stop
  - `maxIterations` for performance tradeoff

---

## Concepts Covered

- Binary classification
- Logistic regression theory
- Sigmoid function and binary cross-entropy
- Gradient descent optimization
- Java file I/O and error handling

---


