import java.util.Scanner;

/**
 * Created by sdmsi
 * CGRA 151
 * 13/10/2016.
 */
public class Calculator {

    private Calculator() {
        System.out.println("what operation would you like to do(+,-,*,/)");
        Scanner s = new Scanner(System.in);
        String op= s.next();
        System.out.println("What is the first number");
        double first= s.nextInt();
        System.out.println("What is the second number");
        double second= s.nextInt();
        switch (op){
            case "+":
                first+=second;
                break;
            case "-":
                first-=second;
                break;
            case "/":
                first/=second;
                break;
            case "*":
                first*=second;
                break;
        }
        System.out.println("Answer: "+first);
    }

    public static void main(String[] arguments) {
        new Calculator();
    }
}
