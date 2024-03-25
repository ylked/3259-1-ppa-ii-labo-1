/*
 * File : LinMain.java
 * Author : Nima Dekhli
 * Date : 2024-03-25
 *
 * Description : Perform a search on a dataset using linear search
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

package ch.proco.objFilter.linaire;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import ch.proco.objFilter.tools.*;

/**
 * Perform a search on a dataset using linear search
 */
public class LinMain {
    private static Elem[] data;
    private static final Map<String, List<FiltreElem>> filters = Map.of(
            "test1", List.of(
                    new FiltreElem(1, "Ant-Man and the Wasp: Quantumania"),
                    new FiltreElem(2, "Action-Adventure-Science Fiction"),
                    new FiltreElem(7, "15.02.2023")
            ),
            "test2", List.of(
                    new FiltreElem(1, "The Electric Comet"),
                    new FiltreElem(2, "Documentary"),
                    new FiltreElem(7, "01.01.2013")
            ),
            "test3", List.of(
                    new FiltreElem(1, "XXXX")
            ),
            "test4", List.of(
                    new FiltreElem(0, "Drama")
            ),
            "test5", List.of(
                    new FiltreElem(2, "Drama"),
                    new FiltreElem(3, "fr")
            ),
            "test6", List.of(
                    new FiltreElem(0, "Documentary")
            )
    );

    private static final int testNumber = 1000;
    private static final ResultCollector collector = new ResultCollector(new int[]{1});

    public static void main(String[] args) throws FileNotFoundException {
        Path path = Paths.get("data", "movies.data");

        data = ElemLoader.load(path);
        System.out.println("Number of items: " + data.length);


        runTest("test1");
        runTest("test2");
        runTest("test3");
        runTest("test4");
        runTest("test5", true);
        runTest("test6", true);

        collector.saveAsCSV("lineSearchResults.csv");
    }


    /**
     * Runs a test with the given name. The test is run <code>testNumber</code> times.
     *
     * @param name the name of the test
     * @param all  if true, the test will search for all elements matching the filters
     */
    private static void runTest(String name, boolean all) {
        System.out.println("***** " + name + " *****");
        TestResults batchResult = new TestResults();

        try {
            for (int i = 0; i < testNumber; i++) {
                SearchResult result = all ?
                        findAllAndChrono(data, filters.get(name))
                        : findAndChrono(data, filters.get(name));

                batchResult.addResult(result);
            }
            showResult(all, batchResult);
            collector.addResult(name, List.of(batchResult.getMedian()));
        } catch (Exception e) {
            System.out.println("ERROR : an exception occurred");
            System.out.println(e.toString());
        }
        System.out.println();
    }

    /**
     * Runs a test with the given name. The test is run <code>testNumber</code> times.
     *
     * @param name the name of the test
     */
    private static void runTest(String name) {
        runTest(name, false);
    }

    /**
     * Show the result of a test in the console
     *
     * @param all    True to find all elements matching the filters, false not to
     * @param result the result of the test
     */
    private static void showResult(boolean all, TestResults result) {
        int numberFound = result.getFound();
        double medianTime = result.getMedian();
        if (all) {
            if (numberFound <= 0) {
                System.out.println("  not found in " + medianTime + " ms (median)");
            } else {
                System.out.println("  found " + numberFound + " elements in " + medianTime + " ms (median)");
            }
        } else {
            if (numberFound <= 0) {
                System.out.println("  not found in " + medianTime + " ms (median)");
            } else {
                System.out.println("  found in " + medianTime + " ms (median)");
            }
        }
        System.out.println();
    }


    /**
     * Find an element in the data matching the filters and measure the time it took
     *
     * @param data    the data to search into
     * @param filters the filters to apply
     * @return the result of the search
     */
    private static SearchResult findAndChrono(Elem[] data, List<FiltreElem> filters) {
        Chrono chrono = Chrono.createAndStart();
        Elem found = LinSearch.trouve(data, filters);
        chrono.stop();

        return new SearchResult(chrono.getEllapsedMillis(), found);
    }

    /**
     * Find all elements in the data matching the filters and measure the time it took
     *
     * @param data    the data to search into
     * @param filters the filters to apply
     * @return the result of the search
     */
    private static SearchResult findAllAndChrono(Elem[] data, List<FiltreElem> filters) {
        Chrono chrono = Chrono.createAndStart();
        List<Elem> found = LinSearch.trouveTous(data, filters);
        chrono.stop();

        return new SearchResult(chrono.getEllapsedMillis(), found);
    }
}
