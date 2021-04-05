/**
 *  Tests the Body constructor.
 */
public class TestBody {

    public static void main(String[] args) {
        Body samh = new Body(1, 0, 0, 0, 10, "");
        Body Aegir = new Body(3, 3, 0, 0, 5, "");
        Body Rocinante = new Body(5, -3, 0, 0, 50, "");
        checkBody(Aegir, Rocinante);
    }

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     */
    private static void checkEquals(double expected, double actual, String label) {
        if (expected == actual) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected
                    + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected
                    + " and you gave " + actual);
        }
    }
        /**
         *  Checks Body constructors to make sure they are setting instance
         *  variables correctly.
         */
        private static void checkBody(Body Aegir, Body Rocinante) {

            System.out.println("Checking first case:");

            double forceX =  samh.calcForceExertedByX(rocinante);
            checkEquals(1.0672e-9, forceX, "samh.calcForceExertedByX(rocinante)");

            double forceY = samh.calcForceExertedByY(rocinante);
            checkEquals(-5.8695e-10, forceY, "samh.calcForceExertedByY(rocinante)");

            double forceNet = samh.calcForceExertedBy(rocinante);
            checkEquals(1.334e-9, forceNet, "samh.calcForceExertedBy(rocinante)");

        }

}