package ch.proco.objFilter.linaire;

import java.nio.file.Path;
import java.nio.file.Paths;

import ch.proco.objFilter.tools.Elem;
import ch.proco.objFilter.tools.ElemLoader;

public class LinMain {

	public static void main(String[] args) {
		Path path = Paths.get("data", "movies.data");

		Elem data[] = ElemLoader.load(path);
		System.out.println("Number of items: " + data.length);

		// !!! afficher le temps d'execution pour chaque requête !!!

		// -------------------
		// Test 1: findAny, existe, dans les premiers
		// Valeurs du filtre :
		// title = "Ant-Man and the Wasp: Quantumania"
		// genres = "Action-Adventure-Science Fiction"
		// release_date = "15.02.2023"
		// -------------------

		// -------------------
		// Test 2: findAny, existe, dans les derniers
		// Valeurs du filtre :
		// title = "The Electric Comet"
		// genres = "Documentary"
		// release_date = "01.01.2013"
		// -------------------

		// -------------------
		// Test 3: findAny, n'existe pas
		// Valeurs du filtre :
		// title = "XXXX"
		// -------------------

		// -------------------
		// Test 4: findAny, erreur de filtre (type non compatible int<->String)
		// Valeurs du filtre :
		// id = "Drama"
		// -------------------

		// -------------------
		// Test 5: findAll, existes
		// Valeurs du filtre :
		// genres = "Drama"
		// original_language = "fr"
		// -------------------

		// -------------------
		// Test 6: findAll, erreur de filtre (type non compatible int<->String)
		// Valeurs du filtre :
		// id = "Documentary"
		// -------------------

	}

}
