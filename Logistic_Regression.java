import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Logistic_Regression {
    static double inputs[][][];
    static double y_actual[];
    static int n = -1;

    static double w1 = 0.0, w2 = 0.0, w3 = 0.0,b = 0.1;

    public static void load_data(String filepath) {
        try {
            BufferedReader count = new BufferedReader(new FileReader(filepath));
            while (count.readLine() != null)
                n++;
            count.close();

            inputs = new double[n][1][3];
            y_actual = new double[n];

            BufferedReader file = new BufferedReader(new FileReader(filepath));
            file.readLine();
            String line;
            int i = 0;

            while ((line = file.readLine()) != null) {
                String words[] = line.split(",");
                if (words.length == 4) {
                    inputs[i][0][0] = Double.parseDouble(words[0].trim());
                    inputs[i][0][1] = Double.parseDouble(words[1].trim());
                    inputs[i][0][2] = Double.parseDouble(words[2].trim());
                    y_actual[i] = Double.parseDouble(words[3].trim());
                }
                i++;
            }
            file.close();

        } catch (IOException e) {
            System.out.println("Error Found: " + e.getMessage());
        }

    }

    public static void train_model() {
        if (inputs == null || y_actual == null) {
            System.out.println("Data is not loaded. Exit");
            return;
        }

        double z = 0.0;
        double sigma = 0.0, Loss = 0.0, avgLoss = 0.01;
        double grad_w3 = 0.0, grad_w1 = 0.0, grad_w2 = 0.0, grad_b = 0.01, lr = 0.01;

        int maxIterations = 10000, iterations = 0;
        while (avgLoss >= 0.01 && iterations < maxIterations) {
            int i = 0;
            Loss=0.0;
            while (i < n) {
                z = w1 * inputs[i][0][0] + w2 * inputs[i][0][1] + w3 * inputs[i][0][2] + b;
                sigma = 1.0 / (1.0 + Math.exp(-z));

                Loss += -(y_actual[i] * Math.log(sigma) + (1 - y_actual[i]) * Math.log(1 - sigma));

                grad_w1 = (sigma - y_actual[i]) * inputs[i][0][0];
                grad_w2 = (sigma - y_actual[i]) * inputs[i][0][1];
                grad_w3 = (sigma - y_actual[i]) * inputs[i][0][2];
                grad_b = (sigma - y_actual[i]);

                w1 = w1 - lr * grad_w1;
                w2 = w2 - lr * grad_w2;
                w3 = w3 - lr * grad_w3;
                b = b - lr * grad_b;

                i++;

            }

            avgLoss = (Loss / n);
            iterations += i;

        }

    }

    public static int test_model(double x1, double x2, double x3) {
        double z = w1 * x1 + w2 * x2 + w3 * x3 + b;
        double sigma = 1.0 / (1.0 + Math.exp(-z));

        return (sigma>= 0.5) ? 1 : 0;
    }

    public static void main(String[] args) {
        String filepath = "training_data/data.csv";
        load_data(filepath);
        System.out.println("Data Loaded Succesfully.");

        train_model();
        System.out.println("Enter the value of x1, x2, x3");
        Scanner sc = new Scanner(System.in);

        System.out.print("x1 = ");
        double x1 = sc.nextDouble();

        System.out.print("x2 = ");
        double x2 = sc.nextDouble();

        System.out.print("x3 = ");
        double x3 = sc.nextDouble();

        double result = test_model(x1, x2, x3);
        System.out.println(
                "For inputs x1 = " + x1 + ", x2 = " + x2 + ", x3 = " + x3 +
                        " Predicted class: " + result);
    }
}