/*
  This source file implements the details of the evaluator. Feel free to check the internal workings,
  but do *NOT* alter any of the definitions.
*/
package Eval;

import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.function.*;

public class LexerEvaluator {
    public static final String LANGUAGE_EXTENSION = "srv";
    public static final String TESTCASE_EXTENSION = "test";


    public static void emitToken(TokenType type, String text) {
        TokenInstance ti = new TokenInstance(type, text);
        tokens.add(ti);
    }

    private static ArrayList<TokenInstance> tokens = new ArrayList<TokenInstance>();
    public static void emitUnrecognizedTokenError(String text) {
        TokenInstance ti = new TokenInstance(TokenType.ERROR, text);
        tokens.add(ti);
        System.err.println("[Lexer Error] Unrecognized token: " + text);
    }

    public static boolean compareWithGroundtruth(ArrayList<TokenInstance> groundtruth) {
        boolean are_equal = true;
        if (groundtruth.size() != tokens.size()) {
            return false;
        }

        for (int i = 0; i < groundtruth.size(); ++i) {
            if (!groundtruth.get(i).equals(tokens.get(i))) {
                return false;
            }
        }

        return true;
    }

    public static ArrayList<TokenInstance> parseTestCase(String path) throws IOException {
        BufferedReader reader;
        ArrayList<TokenInstance> testCase = new ArrayList<>();
        try {
            reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            while (line != null) {
                if (!line.isEmpty()) {
                    String[] fields = line.split("\\$\\$");
                    assert(fields.length == 2);
                    TokenType type = TokenType.valueOf(fields[0]);
                    String text = fields[1];
                    testCase.add(new TokenInstance(type, text));
                }
                line = reader.readLine();
            }
            reader.close();
            return testCase;

        } catch (IOException e) {
            System.err.println("There was an unexpected error when reading the test case at " + path);
            System.err.println("--------------------------------\n");
            throw e;
        }
    }

    public static boolean evaluateTestCase(String path) throws IOException {
        ArrayList<TokenInstance> testCase = parseTestCase(path);
        if (compareWithGroundtruth(testCase)) {
            System.out.println("[Evaluation] Success!");
            return true;
        } else {
            System.out.println("[Evaluation] Your analyzer does not match the expected output. ");
            System.out.println("[Evaluation] ... your analyzer reported the following token sequence: " +
                            tokens.toString());
            System.out.println("[Evaluation] ... but it was expected to encounter the following tokens: " +
                            testCase.toString());
            return false;
        }
    }
}
