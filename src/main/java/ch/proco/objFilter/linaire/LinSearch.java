/*
 * File : LinSearch.java
 * Author : Nima Dekhli, HE-Arc
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

import java.util.LinkedList;
import java.util.List;

import ch.proco.objFilter.tools.Elem;
import ch.proco.objFilter.tools.Filtre;
import ch.proco.objFilter.tools.FiltreElem;

/**
 * Recherche sérielle
 */
public class LinSearch {

    /**
     * Recherche du premier élément
     * !!! Interrompre la recherche dès que trouvé
     *
     * @param data    => le dataset
     * @param filters => le(s) filtre(s)
     * @return si existe return le champ trouvé, sinon null
     */
    public static Elem trouve(Elem[] data, List<FiltreElem> filters) {
        for (Elem e : data) {
            if (Filtre.filtre(e, filters)) {
                return e;
            }
        }
        return null;
    }

    /**
     * Recherche de chaque éléments
     *
     * @param data    => le dataset
     * @param filters => le(s) filtre(s)
     * @return si existe return le(s) champ(s) trouvé(s), sinon liste vide
     */
    public static List<Elem> trouveTous(Elem[] data, List<FiltreElem> filters) {
        List<Elem> found = new LinkedList<>();

        for (Elem e : data) {
            if (Filtre.filtre(e, filters)) {
                found.add(e);
            }
        }

        return found;
    }

}
