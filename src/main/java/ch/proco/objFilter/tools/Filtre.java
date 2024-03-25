package ch.proco.objFilter.tools;

import java.util.List;

/**
 * Méthodes de filtrage
 */
public class Filtre {

    public static boolean filtre(Elem data, List<FiltreElem> filters) {
        boolean result = true;

        for (FiltreElem filter : filters) {
            result = result && eval(data, filter);
            if (!result) {
                return false;
            }
        }

        return true;
    }

    private static boolean eval(Elem data, FiltreElem filter) {
        switch (filter.getIdField()) {
            case 0:
                if (data.getId() == Integer.valueOf(filter.getValue())) {
                    return true;
                }
                break;
            case 1:
                if (data.getTitle().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 2:
                if (data.getGenres().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 3:
                if (data.getOriginal_language().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 4:
                if (data.getOverview().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 5:
                if (data.getPopularity() == Double.valueOf(filter.getValue())) {
                    return true;
                }
                break;
            case 6:
                if (data.getProduction_companies().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 7:
                if (data.getRelease_date().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 8:
                if (data.getBudget() == Double.valueOf(filter.getValue())) {
                    return true;
                }
                break;
            case 9:
                if (data.getRevenue() == Double.valueOf(filter.getValue())) {
                    return true;

                }
                break;
            case 10:
                if (data.getRuntime() == Double.valueOf(filter.getValue())) {
                    return true;
                }
                break;
            case 11:
                if (data.getStatus().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 12:
                if (data.getTagline().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 13:
                if (data.getVote_average() == Integer.valueOf(filter.getValue())) {
                    return true;
                }
                break;
            case 14:
                if (data.getVote_count() == Integer.valueOf(filter.getValue())) {
                    return true;
                }
                break;
            case 15:
                if (data.getCredits().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 16:
                if (data.getKeywords().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 17:
                if (data.getPoster_path().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 18:
                if (data.getBackdrop_path().equals(filter.getValue())) {
                    return true;
                }
                break;
            case 19:
                if (data.getRecommendations().equals(filter.getValue())) {
                    return true;
                }
                break;
        }
        return false;
    }

}
