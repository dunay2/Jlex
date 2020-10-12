// Template file for Compilers practical exercise.

// Using this template, you will need to implement your lexical analyzer using JLex.
// To complete the exercise, you need to:

//   - Complete the implementation of the printExecutionSummary function below.
//   - Implement the necessary JLex rules and states (if any) at the bottom of the file.

// The exercise statement contains instructions for development using this template.


import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import Eval.LexerEvaluator;
import Eval.TokenType;

%%
%{

private static int numIdentifier = 0;
private static int numReserved = 0;
private static int numError = 0;

/*
    Place your code here to declare any necessary static variables that you may require to implement
    the printExecutionSummary function below:
*/
public static void printExecutionSummary() {
    /*
        Place your code here that prints an execution summary with:
        - The total number of identifiers found in the source code
        - The total number of reserved keywords found in the source code
        - The total number of lexer errors found in the source code
    */
         System.out.println("Execution Summary:");
         System.out.println(" - Total number of identifiers found: " + numIdentifier);
         System.out.println(" - Total number of reserved keywords found: " + numReserved);
         System.out.println(" - Total number of lexer errors found: " + numError);
}

/*
  ======================================================
  Below is the main function. It must *NOT* be altered.
  ======================================================
*/
public static void main (String argv[]) throws Exception {

       if (argv.length < 2) {

          System.out.println("Wrong number of parameters!");
          return;

       } else {

         String mode = argv[0];
         if (!mode.equals("run") && !mode.equals("evaluate")) {
             System.err.println("[Arguments Error] The first parameter must be either \"run\" or \"evaluate\".");
         } else {
             String caseName = argv[1];
             String filePath = caseName + "." + LexerEvaluator.LANGUAGE_EXTENSION;
             FileInputStream sourceCodeInputStream = null;   // input file

             try {
                 sourceCodeInputStream = new FileInputStream(filePath);
             }
             catch (FileNotFoundException e) {
                 System.out.println(filePath + ": Unable to open file");
                 return;
             }
             Yylex yy = new Yylex(sourceCodeInputStream);
             while (yy.yylex() != -1);
             sourceCodeInputStream.close();

             printExecutionSummary();

             if (mode.equals("evaluate")) {
                 LexerEvaluator.evaluateTestCase(caseName + "." + LexerEvaluator.TESTCASE_EXTENSION);
             }
         }

       }
}

%}

// Place your JLex code here:
// NOTE: You will need to delete this message and fill in the necessary JLex directives for it to continue.

%%

