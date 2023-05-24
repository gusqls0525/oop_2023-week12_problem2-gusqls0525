import java.util.Scanner;

public class Problem2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter a, b, c: ");
        int a = input.nextInt();
        int b = input.nextInt();
        int c = input.nextInt();

        Rational x = new Rational(-a, 2 * b);
        Rational y = new Rational(4*a*c - b^2, 4*a);

        System.out.println("h is " + x.toString() + " k is " + y.toString());
        /*
          Rational class를 참고하여 코드 작성
        */
      
    }
  
    static class Rational extends Number implements Comparable<Rational> {
        // Data fields for numerator and denominator
        private long[] r = new long[2]; //분모와 분자 입력받음

        /**Default constructor*/
        public Rational() { //구조체 기본값. 없으면 분 0, 분ㅗ 1임
            this(0, 1);
        }

        //r0과 r1에 분모 분자의 최고공약수인 gcd로 나눔
        public Rational(long numerator, long denominator) { 
            long gcd = gcd(numerator, denominator);
            this.r[0] = numerator/gcd;
            this.r[1] = denominator/gcd;
        }

        /*분모 분자의 수를 입력받아서 최소공약수인 gcd를 구함*/
        private long gcd(long n, long d) {
            long t1 = Math.abs(n);
            long t2 = Math.abs(d);
            long remainder = t1%t2;

            while (remainder != 0) {
                t1 = t2;
                t2 = remainder;
                remainder = t1%t2;
            }

            return t2;
        }

        /*분자 소환*/
        public long getNumerator() {
            return r[0];
        }

        /*분모 소환*/
        public long getDenominator() {
            return r[1];
        }

        /*분모와 분자를 정하는 계산. b/2a에서 분자인 b와 분모인 a를 구한다는 뜻.*/

        /*값 2개 받아서 */
        public Rational add(Rational secondNewRational) {
            long n = r[0]*secondNewRational.getDenominator() +
                    r[1]*secondNewRational.getNumerator();
            long d = r[1]*secondNewRational.getDenominator();
            return new Rational(n, d);
        }

        /**Subtract a rational number from this rational*/
        public Rational subtract(Rational secondNewRational) {
            long n = r[0]*secondNewRational.getDenominator()
                    - r[1]*secondNewRational.getNumerator();
            long d = r[1]*secondNewRational.getDenominator();
            return new Rational(n, d);
        }

        /**Multiply a rational number to this rational*/
        public Rational multiply(Rational secondNewRational) {
            long n = r[0]*secondNewRational.getNumerator();
            long d = r[1]*secondNewRational.getDenominator();
            return new Rational(n, d);
        }

        /**Divide a rational number from this rational*/
        public Rational divide(Rational secondNewRational) {
            long n = r[0]*secondNewRational.getDenominator();
            long d = r[1]*secondNewRational.r[0];
            return new Rational(n, d);
        }

        @Override
        public String toString() { //분모가 1이 아니니까 분수형태로 출력
            if (r[1] == 1)
                return r[0] + "";
            else
                return r[0] + "/" + r[1];
        }

        /**Override the equals method*/
        public boolean equals(Object parm1) {
            /**@todo: Override this java.lang.Object method*/
            if ((this.subtract((Rational)(parm1))).getNumerator() == 0)
                return true;
            else
                return false;
        }

        /**Override the intValue method*/
        public int intValue() {
            /**@todo: implement this java.lang.Number abstract method*/
            return (int)doubleValue();
        }

        /**Override the floatValue method*/
        public float floatValue() {
            /**@todo: implement this java.lang.Number abstract method*/
            return (float)doubleValue();
        }

        /**Override the doubleValue method*/
        public double doubleValue() {
            /**@todo: implement this java.lang.Number abstract method*/
            return r[0]*1.0/r[1];
        }

        /**Override the longValue method*/
        public long longValue() {
            /**@todo: implement this java.lang.Number abstract method*/
            return (long)doubleValue();
        }

        @Override
        public int compareTo(Rational o) {
            /**@todo: Implement this java.lang.Comparable method*/
            if ((this.subtract((Rational)o)).getNumerator() > 0)
                return 1;
            else if ((this.subtract((Rational)o)).getNumerator() < 0)
                return -1;
            else
                return 0;
        }
    }

}
