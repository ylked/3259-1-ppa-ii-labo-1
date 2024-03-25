/*
 * File : TestResults.java
 * Author : Nima Dekhli
 * Date : 2024-03-25
 *
 * Description : This class represents the results of a test
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

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents the results of a test
 */
public class TestResults {

    private final List<Double> time;
    private int found;

    /**
     * Creates a new empty test result
     */
    public TestResults() {
        this.time = new LinkedList<>();
        this.found = -1;
    }

    /**
     * Get the number of results found
     *
     * @return The number of results found
     */
    public int getFound() {
        return this.found;
    }

    /**
     * Add a new result to the test results
     *
     * @param result The result to add
     */
    public void addResult(SearchResult result) {
        this.time.add(result.time());
        if (result.results() != null) {
            this.found = result.results().size();
        } else {
            this.found = 0;
        }
    }

    /**
     * Get the average time of the test
     *
     * @return the average time of the test
     */
    public double getAverage() {
        return time.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    /**
     * Get the median time of the test
     *
     * @return the median time of the test
     */
    public double getMedian() {
        List<Double> sortedTimes = time.stream().sorted().toList();
        int middle = sortedTimes.size() / 2;

        // because there is a high number of tests, we don't really care if
        // size is an even number and that median is not **exactly** correct
        return sortedTimes.get(middle);
    }
}
