/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sketcher;

/**
 *
 * @author Marvin
 */
public class Sketcher {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //new Window();
        new Window_alt();
        Rectangle rect = (l, w) -> { l*=2; w*=3; return l * w; };
        //System.out.println("Flaeche: " + rect.getArea(4, 3));
        Fib f = (n) -> {
            return n; //To change body of generated lambdas, choose Tools | Templates.
        };
        Calc calc = (a, b) -> {
            return a * b; //To change body of generated lambdas, choose Tools | Templates.
        };
       
        System.out.println("9 * 7 = " + calc.mul(9, 7));

    }
    interface Rectangle {
        public double getArea(double length, double width);
    }
    interface Fib{
        public int getFib(int n);
    }
    interface Calc{
        public double mul(int a, int b);
    }
    
}
