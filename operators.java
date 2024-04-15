public class operators {
    
        public static void main(String[] args) {
            if (args.length != 3) {
                System.out.println("Usage: java SimpleCalculator <operator> <operand1> <operand2>");
                return;
            }
    
            char operator = args[0].charAt(0);
            double operand1 = Double.parseDouble(args[1]);
            double operand2 = Double.parseDouble(args[2]);
            double result = 0;
    
            switch (operator) {
                case '+':
                    result = operand1 + operand2;
                    break;
                case '-':
                    result = operand1 - operand2;
                    break;
                case '*':
                    result = operand1 * operand2;
                    break;
                case '/':
                    if (operand2 == 0) {
                        System.out.println("Error: Division by zero");
                        return;
                    }
                    result = operand1 / operand2;
                    break;
                default:
                    System.out.println("Error: Unsupported operator");
                    return;
            }
    
            System.out.println("Result: " + result);
        }
    }
    

