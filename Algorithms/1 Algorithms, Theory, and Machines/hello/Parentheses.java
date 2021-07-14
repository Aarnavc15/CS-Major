public class Parentheses {
    private static Stack<String> paren;


    public Parentheses() {
        paren = new Stack<String>();
    }


    public static void test(String alpha) {
        for (char a : alpha.toCharArray()) {
            paren.push(Character.toString(a));
        }
    }

    public static boolean balanceChecker() {
        int par = 0;
        int curl = 0;
        int brack = 0;

        while (!paren.isEmpty()) {
            String item = paren.pop();
            StdOut.println(item);
            if (item.equals("{")) {
                curl++;
            }
            else if (item.equals("}")) {
                curl--;
            }

            else if (item.equals("(")) {
                par++;
            }

            else if (item.equals(")")) {
                par--;
            }

            else if (item.equals("[")) {
                brack++;
            }

            else if (item.equals("]")) {
                brack++;
            }
        }
        return (par == 0 && curl == 0 && brack == 0);
    }

    public static void main(String[] args) {
        Parentheses test = new Parentheses();
        test.test("(}");
        StdOut.println(test.balanceChecker());
    }
}
