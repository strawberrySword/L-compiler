
import java.io.*;
import java.io.PrintWriter;

import java_cup.runtime.Symbol;

public class Main {

    static public void main(String argv[]) {
        Lexer l;
        Symbol s;
        FileReader file_reader;
        PrintWriter file_writer;
        String inputFilename = argv[0];
        String outputFilename = argv[1];

        try {
            /**
             * *****************************
             */
            /* [1] Initialize a file reader */
            /**
             * *****************************
             */
            file_reader = new FileReader(inputFilename);

            /**
             * *****************************
             */
            /* [2] Initialize a file writer */
            /**
             * *****************************
             */
            file_writer = new PrintWriter(outputFilename);

            /**
             * ***************************
             */
            /* [3] Initialize a new lexer */
            /**
             * ***************************
             */
            l = new Lexer(file_reader);

            /**
             * ********************
             */
            /* [4] Read next token */
            /**
             * ********************
             */
            s = l.next_token();

            /**
             * *****************************
             */
            /* [5] Main reading tokens loop */
            /**
             * *****************************
             */
            while (s.sym != TokenNames.EOF) {
                /**
                 * ******************
                 */
                /* [7] Print to file */
                /**
                 * ******************
                 */
                String value = s.value != null ? "(" + s.value + ")" : "";
                file_writer.print(TokenTypeNames.values()[s.sym] + value);
                file_writer.print("[");
                file_writer.print(l.getLine());
                file_writer.print(",");
                file_writer.print(l.getTokenStartPosition());
                file_writer.print("]");
                file_writer.print("\n");

                /**
                 * ********************
                 */
                /* [8] Read next token */
                /**
                 * ********************
                 */
                s = l.next_token();
            }

            /**
             * ***************************
             */
            /* [9] Close lexer input file */
            /**
             * ***************************
             */
            l.yyclose();

            /**
             * ***********************
             */
            /* [10] Close output file */
            /**
             * ***********************
             */
            file_writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
