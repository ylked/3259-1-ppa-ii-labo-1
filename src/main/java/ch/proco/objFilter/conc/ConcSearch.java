/*
 * File : ConcSearch.java
 * Author : Nima Dekhli, HE-Arc
 * Date : 2024-03-25
 *
 * Description : Perform a search on a dataset using linear search
 *
 * Version : 1.0
 */
package ch.proco.objFilter.conc;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

import ch.proco.objFilter.tools.Elem;
import ch.proco.objFilter.tools.FiltreElem;

/**
 * Recherche concurrente
 * On utilisera java.util.concurrent.ForkJoinPool
 * et java.util.concurrent.RecursiveTask;
 */
public class ConcSearch {

    /**
     * Recherche du premier élément
     * !!! Interrompre la recherche dès que trouvé
     *
     * @param data    => le dataset
     * @param filters => le(s) filtre(s)
     * @param size    => taille des batchs concurrants
     * @return si existe return le champ trouvé, sinon null
     */
    public static Elem trouve(Elem[] data, List<FiltreElem> filters, int size) {
        ForkJoinPool pool = new ForkJoinPool();
        FindTask task = new FindTask(data, filters, 0, data.length - 1, size);
        task.reset();
        pool.invoke(task);
        pool.shutdown();
        return task.getSingleResult();
    }


    /**
     * Recherche de chaque éléments
     *
     * @param data    => le dataset
     * @param filters => le(s) filtre(s)
     * @param size    => taille des batchs concurrants
     * @return si existe return le(s) champ(s) trouvé(s), sinon liste vide
     */
    public static List<Elem> trouveTous(Elem[] data, List<FiltreElem> filters, int size) {
        ForkJoinPool pool = new ForkJoinPool();
        FindTask task = new FindTask(data, filters, 0, data.length - 1, size, true);
        task.reset();
        pool.invoke(task);
        pool.shutdown();
        return task.getAllResults();
    }

}
