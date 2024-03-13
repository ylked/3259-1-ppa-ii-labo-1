package ch.proco.objFilter.linaire;

import java.util.ArrayList;
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
	 * @param data => le dataset
	 * @param filters => le(s) filtre(s)
	 * @return si existe return le champ trouvé, sinon null
	 */
	public static Elem trouve (Elem[] data, List<FiltreElem> filters) {
		for(Elem e : data){
			if(Filtre.filtre(e, filters)){
				return e;
			}
		}
		return null;
	}
	
	/**
	 * Recherche de chaque éléments
	 * @param data => le dataset
	 * @param filters => le(s) filtre(s)
	 * @return si existe return le(s) champ(s) trouvé(s), sinon liste vide
	 */
	public static List<Elem> trouveTous (Elem[] data,  List<FiltreElem> filters) {
		List<Elem> found = new LinkedList<>();

		for(Elem e : data){
			if(Filtre.filtre(e, filters)){
				found.add(e);
			}
		}

		return found;
	}

}
