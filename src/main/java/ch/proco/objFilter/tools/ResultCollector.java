/*
 * File : ResultCollector.java
 * Author : Nima Dekhli
 * Date : 2024-03-25
 *
 * Description : This class is useful to collect and save the results of a benchmark
 *
 * Version : 1.0
 *
 * Copyright 2024 Nima Dekhli
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the “Software”), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense,
 * and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED “AS IS”, WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
 * ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package ch.proco.objFilter.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is useful to collect and save the results of a benchmark
 */
public class ResultCollector {
    private String[] header;
    private List<String[]> lines;
    public static final String PATH = "results/";

    /**
     * Create a new ResultCollector
     *
     * @param batchSizes The batch sizes used in the benchmark
     */
    public ResultCollector(int[] batchSizes) {
        this.header = new String[batchSizes.length + 1];
        this.header[0] = "names";

        for (int i = 0; i < batchSizes.length; i++) {
            this.header[i + 1] = String.valueOf(batchSizes[i]);
        }

        lines = new LinkedList<>();
    }

    /**
     * Add a result to the collector
     *
     * @param name   The name of the test
     * @param result The result of the test
     */
    public void addResult(String name, List<Double> result) {
        assert result.size() == columnLength();
        String[] line = new String[columnLength() + 1];
        line[0] = name;

        for (int i = 0; i < columnLength(); i++) {
            line[i + 1] = String.valueOf(result.get(i));
        }

        lines.add(line);
    }

    private int columnLength() {
        return this.header.length - 1;
    }


    private String toCsv(String[] line) {
        return String.join(",", line);
    }

    /**
     * Save the results as a CSV file <br>
     * <strong>Source</strong> : <a href="https://www.baeldung.com/java-csv"><em>"How to Write to a CSV File in Java"</em>, Baeldung</a>
     */
    public void saveAsCSV(String filename) throws FileNotFoundException {
        createDirectoryIfNotExists();
        File outputFile = new File(PATH + filename);
        try (PrintWriter pw = new PrintWriter(outputFile)) {
            pw.println(toCsv(header));
            lines.stream().map(this::toCsv).forEach(pw::println);
        }
        assert outputFile.exists();
    }

    private static String getOsName(){
        return System.getProperty("os.name").replace(" ", "_");
    }

    public static String getFilenameConcurrent(){
        long time = System.currentTimeMillis();
        String os = getOsName();
        return "conc_search_results_" + os + "_" + time + ".csv";
    }
    public static String getFilenameLinear(){
        long time = System.currentTimeMillis();
        String os = getOsName();
        return "lin_search_results_" + os + "_" + time + ".csv";
    }

    private static void createDirectoryIfNotExists(){
        File directory = new File(PATH);
        if (!directory.exists()){
            directory.mkdirs();
        }
    }
}
