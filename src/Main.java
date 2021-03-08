import LexicalAnalyzer.Analyzer;
import LexicalAnalyzer.AnalyzerExceptions;

import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Analyzer a = new Analyzer();

        a.addVar("variable");
        File file = new File("src/Resouces/test.sfaa");
        try {
            a.getTokens(file);
        } catch (AnalyzerExceptions analyzerExceptions) {
            analyzerExceptions.printStackTrace();
        }

    }
}
