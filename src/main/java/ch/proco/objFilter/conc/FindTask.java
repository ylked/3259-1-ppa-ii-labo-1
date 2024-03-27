/*
 * File : FindTask.java
 * Author : Nima Dekhli
 * Date : 2024-03-25
 *
 * Description : Represents the recursive search of any or of all elements matching the given filters.
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

package ch.proco.objFilter.conc;

import ch.proco.objFilter.tools.Elem;
import ch.proco.objFilter.tools.Filtre;
import ch.proco.objFilter.tools.FiltreElem;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * This class represents the recursive search of any or of all elements matching the given filters.
 * A batch size can be specified and if the size is not reached, it will divide the work
 * between two tasks, until batch size is reached. Then, it performs a simple linear search.
 */
public class FindTask extends RecursiveAction {
    private final Elem[] data;
    private final List<FiltreElem> filters;
    private final int start;
    private final int end;
    private final int size;
    private final boolean all;
    private final List<Elem> result = new LinkedList<>();
    private final static AtomicBoolean found = new AtomicBoolean(false);

    /**
     * Creates a new recursive Task to find movie
     *
     * @param data    The data to search into
     * @param filters The filters to apply
     * @param start   The index of the first element to consider in the search
     * @param end     The index of the last element to consider in the search
     * @param size    The batch size
     * @param all     True to find all elements matching the filters, false not to
     */
    public FindTask(Elem[] data, List<FiltreElem> filters, int start, int end, int size, boolean all) {
        this.data = data;
        this.filters = filters;
        this.start = start;
        this.end = end;
        this.size = size;
        this.all = all;
    }

    /**
     * Creates a new recursive Task to find movie
     *
     * @param data    The data to search into
     * @param filters The filters to apply
     * @param start   The index of the first element to consider in the search
     * @param end     The index of the last element to consider in the search
     * @param size    The batch size
     */
    public FindTask(Elem[] data, List<FiltreElem> filters, int start, int end, int size) {
        this(data, filters, start, end, size, false);
    }


    @Override
    protected void compute() {
        // If we found an element and we are in searchAny mode, we stop the search
        if (!all && found.get()) {
            return;
        }
        // If the batch size is reached, we perform a simple linear search
        if (end - start + 1 <= size) {
            for (int i = start; i <= end; ++i) {
                if (Filtre.filtre(data[i], filters)) {
                    result.add(data[i]);

                    // Inform the other tasks that we found an element
                    found.set(true);

                    // If we found an element, and we are in searchAny mode, we stop the search
                    if (!all) break;
                }
                // Each 100 elements, we check if we found an element, and we are in searchAny mode
                // If so, we stop the search
                if (!all && i % 100 == 0 && found.get()) break;
            }
        } else {
            // We split the work in two
            int mid = (end - start) / 2 + start;

            // We check that the split is correct
            assert start <= mid;
            assert end >= mid;

            // We create two new tasks
            FindTask left = new FindTask(data, filters, start, mid, size, all);
            FindTask right = new FindTask(data, filters, mid + 1, end, size, all);

            // We fork the tasks
            left.fork();
            right.fork();

            // We join the tasks
            left.join();
            right.join();

            // We gather the results
            result.addAll(left.getAllResults());
            result.addAll(right.getAllResults());
        }
    }

    /**
     * Retrieves one result. Use it if you searched any element matching filters
     *
     * @return The single result element
     */
    public Elem getSingleResult() {
        if (!result.isEmpty()) {
            return result.getFirst();
        } else {
            return null;
        }
    }

    /**
     * Retrieves the list of all results. Use it if you search all elements matching the filters.
     *
     * @return The results
     */
    public List<Elem> getAllResults() {
        return result;
    }

    /**
     * Resets the search status. Must be called before each new search (but not before each fork).
     * This resets the flag that stops the search once one element has been found in searchAny mode.
     * Not useful in searchAll.
     */
    public void reset() {
        found.set(false);
    }
}
