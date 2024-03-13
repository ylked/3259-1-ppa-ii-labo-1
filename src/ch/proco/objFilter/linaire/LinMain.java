package ch.proco.objFilter.linaire;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import ch.proco.objFilter.tools.Elem;
import ch.proco.objFilter.tools.ElemLoader;
import ch.proco.objFilter.tools.FiltreElem;
import ch.proco.objFilter.tools.Chrono;

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

    public static void main(String[] args) {
        Path path = Paths.get("data", "movies.data");

        data = ElemLoader.load(path);
        System.out.println("Number of items: " + data.length);

        runTest("test1");
        runTest("test2");
        runTest("test3");
        runTest("test4");
        runTest("test5", true);
        runTest("test6", true);
    }


    private static void runTest(String name, boolean all) {
        System.out.println("***** " + name + " *****");
        try {
            if (all) {
                findAllAndChrono(data, filters.get(name));
            } else {
                findAndChrono(data, filters.get(name));
            }
        } catch (Exception e) {
            System.out.println("ERROR : an exception occurred");
            System.out.println(e.toString());
        }
        System.out.println();
    }

    private static void runTest(String name) {
        runTest(name, false);
    }

    private static void findAndChrono(Elem[] data, List<FiltreElem> filters) {
        Chrono chrono = new Chrono();
        chrono.start();
        Elem found = LinSearch.trouve(data, filters);
        chrono.stop();

        if (found == null) {
            System.out.println("NOT FOUND ! (" + chrono.getEllapsedMillis() + " ms)");
        } else {
            System.out.println("found an element in " + chrono.getEllapsedMillis() + " ms");
        }
    }

    private static void findAllAndChrono(Elem[] data, List<FiltreElem> filters) {
        Chrono chrono = new Chrono();
        chrono.start();
        List<Elem> found = LinSearch.trouveTous(data, filters);
        chrono.stop();

        if (found.isEmpty()) {
            System.out.println("NOT FOUND ! (" + chrono.getEllapsedMillis() + " ms)");
        } else {
            System.out.println("found " + found.size() + " elements in " + chrono.getEllapsedMillis() + " ms");
        }
    }
}
