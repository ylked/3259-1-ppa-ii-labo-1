package ch.proco.objFilter.conc;

import java.util.ArrayList;
import java.util.List;

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
	 * @param data => le dataset
	 * @param filters => le(s) filtre(s)
	 * @param size => taille des batchs concurrants
	 * @return si existe return le champ trouvé, sinon null
	 */
	public static Elem trouve(Elem[] data, List<FiltreElem> filters, int size) {
		//TODO
		return null;
	}

	
	/**
	 * Recherche de chaque éléments
	 * @param data => le dataset
	 * @param filters => le(s) filtre(s)
	 * @param size => taille des batchs concurrants
	 * @return si existe return le(s) champ(s) trouvé(s), sinon liste vide
	 */
	public static List<Elem> trouveTous(Elem[] data, List<FiltreElem> filters, int size) {
		//TODO
		return new ArrayList<Elem>();
	}

}
