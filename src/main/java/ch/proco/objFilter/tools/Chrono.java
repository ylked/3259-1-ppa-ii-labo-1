/*
 * File : Chrono.java
 * Author : Nima Dekhli
 * Date : 2024-03-25
 *
 * Description : This class is useful to measure the execution time of some code fragments.
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

/**
 * This class is useful to measure the execution time of some code fragments.
 * The chronometer has a precision of a nanoSecond and returns the results as
 * double encoded milliseconds.
 */
public class Chrono {
    private long start = 0;
    private long stop = 0;

    public Chrono() {
    }

    /**
     * Start the chronometer
     */
    public void start() {
        assert start == 0;
        this.start = System.nanoTime();
    }

    /**
     * Stop the chronometer
     */
    public void stop() {
        assert stop == 0;
        this.stop = System.nanoTime();
    }

    /**
     * Reset the chronometer
     */
    public void reset() {
        this.start = 0;
        this.stop = 0;
    }

    /**
     * Get the elapsed time in milliseconds
     *
     * @return the elapsed time in milliseconds
     */
    public double getEllapsedMillis() {
        return (stop - start) / 1000000.0;
    }

    /**
     * Create a new Chrono, start it and return it
     *
     * @return The new Chrono, started
     */
    public static Chrono createAndStart() {
        Chrono chrono = new Chrono();
        chrono.start();
        return chrono;
    }
}
