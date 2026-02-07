package project20280.stacksqueues;

class Q5 {
    public static boolean checkParentheses(String in) {
        LinkedStack<Object> stack = new LinkedStack<>();
        String openers = "[({";
        String closers = "])}";

        for (char c : in.toCharArray()) {
            if (openers.contains(String.valueOf(c))) stack.push(c);

            if (closers.contains(String.valueOf(c))) {
                if (stack.isEmpty()) return false;

                if (c == ']' && (char) stack.top() == '[') {
                    stack.pop();
                } else if (c == ')' && (char) stack.top() == '(') {
                    stack.pop();
                } else if (c == '}' && (char) stack.top() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String[] inputs = {
            "[]]()()", // not correct
            "c[d]", // correct
            "a{b[c]d}e", // correct
            "a{b(c]d}e", // not correct; ] doesn't ←- match
            "a[b{c}d]e}", // not correct; nothing ←- matches final }
            "a{b(c) ", // not correct; Nothing ←- matches opening {
            "][]][][[]][]][][[[", //
            "(((abc))((d)))))", //
        };

        for (String input : inputs) {
            boolean isBalanced = checkParentheses(input);
            System.out.println("isBalanced ?" + (isBalanced ? " yes! " : " no! ") + input);
        }
    }
}