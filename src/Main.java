public class Main {
    public static void main(String args[]) {
        String str = "2as3(kd2(ab))";
        System.out.println(algoritm1(str));
    }

    private static StringBuilder expansion(String str) {
        StringBuilder sb = new StringBuilder();
        StringBuilder digit = new StringBuilder();
        char[] carray = str.toCharArray();
        int factor;
        for (char c : carray) {
            if (Character.isDigit(c)) {
                digit.append(c);
            } else {
                if (digit.length() > 0) {
                    factor = Integer.parseInt(digit.toString());
                    while (factor-- > 0)
                        sb.append(c);
                    digit = new StringBuilder();
                } else {
                    sb.append(c);
                }
            }
        }
        return sb;
    }

    public static StringBuilder algoritm1(String str) {
        char[] carray = str.toCharArray();
        StringBuilder digit = new StringBuilder();
        StringBuilder sb = new StringBuilder(str);
        for (int j = 0; j < carray.length; j++) {
            if (carray[j] == '(') {
                for (int i = j + 1; i < carray.length; i++) {
                    if (carray[i] == '(') {
                        j = --i;
                        break;
                    }
                    if (carray[i] == ')') {
                        StringBuilder sub = expansion(sb.substring(j + 1, i));
                        for (int k = j - 1; Character.isDigit(carray[k]); k--)
                            digit.insert(0, carray[k]);
                        if (digit.length() > 0) {
                            sub = repeat(sub, Integer.parseInt(digit.toString()));
                        }
                        sb = sb.replace(j - digit.length(), i + 1, sub.toString());
                        digit = new StringBuilder();
                        j = 0;
                        break;
                    }
                }
            }
            carray = sb.toString().toCharArray();
        }
        return expansion(sb.toString());
    }

    private static StringBuilder repeat(StringBuilder sb, int n) {
        StringBuilder repeat = new StringBuilder();
        for(; n > 0 ; n--)
            repeat.append(sb);
        return repeat;
    }
}