/*
 * File : SearchResult.java
 * Author : Nima Dekhli
 * Date : 2024-03-25
 *
 * Description : This class represents the result of a single search
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
 * This class represents the result of a single search
 *
 * @param time    The time taken to perform the search
 * @param results The results of the search
 */
public record SearchResult(double time, List<Elem> results) {
    /**
     * Creates a new search result
     *
     * @param time   The time taken to perform the search
     * @param result The single result of the search
     */
    public SearchResult(double time, Elem result) {
        this(time, result == null ? new LinkedList<>() : List.of(result));
    }
}
