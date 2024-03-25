package ch.proco.objFilter.tools;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Chargement des données
 */
public class ElemLoader {

    public static Elem[] load(Path path) {
        List<Elem> list = new ArrayList<Elem>();

        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader = new BufferedReader(
                     new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                Elem item = processItem(line);
                list.add(item);
            }
        } catch (IOException x) {
            x.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Elem ret[] = new Elem[list.size()];
        return list.toArray(ret);

    }

    private static Elem processItem(String line) {
        Elem ret = new Elem();
        String tokens[] = line.split(";");

        ret.setId(Integer.valueOf(tokens[0].trim()));
        ret.setTitle(tokens[1].trim());
        ret.setGenres(tokens[2].trim());
        ret.setOriginal_language(tokens[3].trim());
        ret.setOverview(tokens[4].trim());
        ret.setPopularity(Double.valueOf(tokens[5].trim()));
        ret.setProduction_companies(tokens[6].trim());
        ret.setRelease_date(tokens[7].trim());
        ret.setBudget(Double.valueOf(tokens[8].trim()));
        ret.setRevenue(Double.valueOf(tokens[9].trim()));
        if (tokens[10].trim().length() > 0)
            ret.setRuntime(Double.valueOf(tokens[10].trim()));
        else
            ret.setRuntime(0);
        ret.setStatus(tokens[11].trim());
        ret.setTagline(tokens[12].trim());
        ret.setVote_average(Double.valueOf(tokens[13].trim()));
        ret.setVote_count(Double.valueOf(tokens[14].trim()));

        if (tokens.length < 16)
            ret.setCredits("None");
        else
            ret.setCredits(tokens[15].trim());

        if (tokens.length < 17)
            ret.setKeywords("None");
        else
            ret.setKeywords(tokens[16].trim());

        if (tokens.length < 18)
            ret.setPoster_path("None");
        else
            ret.setPoster_path(tokens[17].trim());

        if (tokens.length < 19)
            ret.setBackdrop_path("None");
        else
            ret.setBackdrop_path(tokens[18].trim());

        if (tokens.length < 20)
            ret.setRecommendations("None");
        else
            ret.setRecommendations(tokens[19].trim());

        return ret;
    }

}
