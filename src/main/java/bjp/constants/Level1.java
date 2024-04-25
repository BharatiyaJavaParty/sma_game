package bjp.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Level1 {
    public static ArrayList<int[]> trees = new ArrayList<>();
    public static ArrayList<int[]> doubleTrees = new ArrayList<>();
    public static ArrayList<int[]> homeTrees = new ArrayList<>();
    public static ArrayList<int[]> houses = new ArrayList<>();
    public static ArrayList<int[]> buildings = new ArrayList<>();
    public static ArrayList<int[]> bushes = new ArrayList<>();
    public static ArrayList<int[]> lakes = new ArrayList<>();

    static{
        trees.addAll(Arrays.asList(
            new int[]{1, 31}, new int[]{2, 3}, new int[]{3, 31}, new int[]{4, 31},
            new int[]{5, 31}, new int[]{6, 31}, new int[]{7, 31}, new int[]{8, 31},
            new int[]{9, 31}, new int[]{10, 31}, new int[]{11, 31}, new int[]{12, 31},
            new int[]{13, 31}, new int[]{14, 31}, new int[]{15, 31}, new int[]{16, 31},
            new int[]{17, 31}, new int[]{18, 31}, new int[]{19, 31}, new int[]{20, 31},
            new int[]{21, 31}, new int[]{22, 31}, new int[]{23, 31}, new int[]{24, 31},
            new int[]{25, 31}, new int[]{26, 31}, new int[]{27, 31}, new int[]{28, 31},
            new int[]{29, 31}, new int[]{30, 31}, new int[]{31, 31}, new int[]{32, 31},
            new int[]{33, 31}, new int[]{34, 31}, new int[]{35, 31}, new int[]{36, 31},
            new int[]{37, 31}, new int[]{38, 31}, new int[]{39, 31}, new int[]{40, 31},
            new int[]{41, 31}, new int[]{42, 31}, new int[]{43, 31}, new int[]{44, 31},
            new int[]{45, 31}, new int[]{46, 31}, new int[]{47, 31}, new int[]{48, 31},
            new int[]{49, 31}, new int[]{50, 31},
            new int[]{51, 2}, new int[]{51, 3}, new int[]{51, 4},
            new int[]{52, 2}, new int[]{52, 3}, new int[]{52, 4},
            new int[]{53, 2}, new int[]{53, 3}, new int[]{53, 4},
            new int[]{54, 2}, new int[]{54, 3}, new int[]{54, 4}, new int[]{56, 31},
            new int[]{1, 2}, new int[]{1, 4}, new int[]{1, 5}, new int[]{2, 2},
            new int[]{3, 4}, new int[]{2, 3}, new int[]{3, 2},
            new int[]{4, 4}, new int[]{47, 23}, new int[]{47, 24}, new int[]{47, 25},
            new int[]{47, 26}, new int[]{48, 23}, new int[]{48, 24}, new int[]{48, 25},
            new int[]{48, 27}, new int[]{49, 24}, new int[]{49, 25}, new int[]{49, 26},
            new int[]{49, 27}, new int[]{50, 23}, new int[]{50, 27}, new int[]{51, 23},
            new int[]{3, 28}, new int[]{1, 27}, new int[]{2, 28}, new int[]{3, 27},
            new int[]{3, 28}, new int[]{2, 27}, new int[]{2, 28}, new int[]{2, 30},
            new int[]{50, 11}, new int[]{52, 11}, new int[]{51, 12}, new int[]{53, 12},
            new int[]{53, 13}, new int[]{50, 14}, new int[]{52, 14}, new int[]{49, 15},
            new int[]{50, 15}, new int[]{51, 15}, new int[]{52, 15}, new int[]{54, 15},
            new int[]{49, 16}, new int[]{52, 16}, new int[]{53, 16}, new int[]{52, 17},
            new int[]{50, 18}, new int[]{49, 11}, new int[]{51, 10}, new int[]{50, 12},
            new int[]{50, 16}, new int[]{52, 12}, new int[]{49, 13}, new int[]{49, 17},
            new int[]{52, 18}, new int[]{53, 14}, new int[]{53, 17}, new int[]{54, 13},
            new int[]{54, 16}, new int[]{51, 13}, new int[]{51, 16}, new int[]{17, 11},
            new int[]{22, 11}, new int[]{46, 16}, new int[]{40, 16}, new int[]{39, 16},
            new int[]{22, 27}, new int[]{27, 27}
        ));

        doubleTrees.addAll(Arrays.asList(
            new int[]{51, 3}, new int[]{52, 3}, new int[]{53, 2}, new int[]{52, 2},
            new int[]{53, 3}, new int[]{53, 4}, new int[]{54, 3}, new int[]{54, 2},
            new int[]{55, 4}, new int[]{55, 6},
            new int[]{2, 3}, new int[]{3, 4}, new int[]{1, 2}, new int[]{2, 2},
            new int[]{1, 2}, new int[]{2, 1},
            new int[]{46, 25}, new int[]{47, 25}, new int[]{46, 24}, new int[]{48, 25},
            new int[]{48, 26}, new int[]{49, 26}, new int[]{50, 24}, new int[]{49, 26},
            new int[]{4, 29}, new int[]{1, 28}, new int[]{3, 28},
            new int[]{49, 11}, new int[]{51, 10}, new int[]{50, 12}, new int[]{50, 16},
            new int[]{52, 12}, new int[]{49, 13}, new int[]{49, 17}, new int[]{52, 18},
            new int[]{53, 14}, new int[]{53, 17}, new int[]{54, 13}, new int[]{54, 16},
            new int[]{51, 13}, new int[]{51, 16}, new int[]{35, 12}, new int[]{36, 12},
            new int[]{39, 12}, new int[]{40, 12}, new int[]{43, 12}, new int[]{43, 12},
            new int[]{3, 12}, new int[]{4, 12}, new int[]{5, 12}, new int[]{7, 12},
            new int[]{8, 12}, new int[]{10, 12}, new int[]{11, 12}, new int[]{13, 12},
            new int[]{6, 18}, new int[]{36, 19}, new int[]{47, 19}, new int[]{1, 28}
        ));

        homeTrees.addAll(Arrays.asList(
            new int[]{26, 3}, new int[]{27, 3}, new int[]{28, 3}, new int[]{26, 4}, 
            new int[]{29, 3}, new int[]{30, 3}, new int[]{31, 3}, new int[]{29, 4}, 
            new int[]{32, 3}, new int[]{33, 3}, new int[]{34, 3}, new int[]{32, 4}, 
            new int[]{35, 3}, new int[]{36, 3}, new int[]{37, 3}, new int[]{35, 4}, 
            new int[]{38, 3}, new int[]{39, 3}, new int[]{40, 3}, new int[]{38, 4}, 
            new int[]{41, 3}, new int[]{42, 3}, new int[]{43, 3}, new int[]{41, 4}, 
            new int[]{44, 4}, new int[]{44, 3},
            new int[]{34, 8}, new int[]{35, 8}, new int[]{36, 8}, new int[]{34, 9}, 
            new int[]{37, 8}, new int[]{38, 8}, new int[]{39, 8}, new int[]{37, 9}, 
            new int[]{40, 8}, new int[]{41, 8}, new int[]{42, 8}, new int[]{40, 9}, 
            new int[]{43, 8}, new int[]{44, 8}, new int[]{45, 8}, new int[]{43, 9}, 
            new int[]{46, 9}, new int[]{46, 8},
            new int[]{34, 25}, new int[]{35, 25}, new int[]{36, 25}, new int[]{34, 26}, 
            new int[]{37, 25}, new int[]{38, 25}, new int[]{39, 25}, new int[]{37, 26}, 
            new int[]{40, 25}, new int[]{41, 25}, new int[]{42, 25}, new int[]{40, 26}, 
            new int[]{43, 26}, new int[]{43, 25},
            new int[]{2, 8}, new int[]{3, 8}, new int[]{4, 8}, new int[]{2, 9},
            new int[]{5, 8}, new int[]{6, 8}, new int[]{7, 8}, new int[]{5, 9},
            new int[]{8, 8}, new int[]{9, 8}, new int[]{10, 8}, new int[]{8, 9},
            new int[]{11, 8}, new int[]{12, 8}, new int[]{13, 8}, new int[]{11, 9}, 
            new int[]{14, 9}, new int[]{14, 8},
            new int[]{3, 22}, new int[]{4, 22}, new int[]{5, 22}, new int[]{3, 23},
            new int[]{6, 22}, new int[]{7, 22}, new int[]{8, 22}, new int[]{6, 23},
            new int[]{9, 22}, new int[]{10, 22}, new int[]{11, 22}, new int[]{9, 23},
            new int[]{6, 5}, new int[]{7, 5}, new int[]{8, 5}, new int[]{10, 5},
            new int[]{12, 5}, new int[]{17, 8}, new int[]{22, 8}, new int[]{23, 6},
            new int[]{12, 20}, new int[]{15, 20}, new int[]{36, 20}, new int[]{27, 29},
            new int[]{36, 18}, new int[]{17, 6}, new int[]{42, 17}, new int[]{43, 17},
            new int[]{47, 18}, new int[]{47, 17}, new int[]{34, 23}, new int[]{37, 23},
            new int[]{34, 23}, new int[]{41, 23}, new int[]{44, 23}, new int[]{34, 23},
            new int[]{14, 24}, new int[]{15, 24}, new int[]{16, 24}, new int[]{17, 24},
            new int[]{22, 23}, new int[]{27, 23}, new int[]{22, 29}, new int[]{23, 29},
            new int[]{24, 29}, new int[]{25, 29}, new int[]{26, 29}, new int[]{14, 25},
            new int[]{17, 25}
        ));

        houses.addAll(Arrays.asList(
            new int[]{27, 4}, new int[]{30, 4}, new int[]{33, 4}, 
            new int[]{36, 4}, new int[]{39, 4}, new int[]{42, 4},
            new int[]{35, 9}, new int[]{38, 9}, new int[]{41, 9}, new int[]{44, 9},
            new int[]{35, 26}, new int[]{38, 26}, new int[]{41, 26},
            new int[]{3, 9}, new int[]{6, 9}, new int[]{9, 9}, new int[]{12, 9},
            new int[]{4, 23}, new int[]{7, 23}, new int[]{10, 23}, new int[]{15, 25},
            new int[]{35, 23}, new int[]{42, 23}, new int[]{6, 25}, new int[]{9, 25}
        ));

        buildings.addAll(Arrays.asList(
            new int[]{37, 18}, new int[]{39, 17}, new int[]{41, 18}, new int[]{43, 18},
            new int[]{45, 17}, new int[]{7, 17}, new int[]{9, 18}, new int[]{11, 17},
            new int[]{13, 17}
        ));

        bushes.addAll(Arrays.asList(
            new int[]{26, 5}, new int[]{29, 5}, new int[]{32, 5}, new int[]{35, 5},
            new int[]{38, 5}, new int[]{41, 5}, new int[]{44, 5}, new int[]{34, 10}, new int[]{37, 10}, 
            new int[]{40, 10}, new int[]{43, 10}, new int[]{46, 10}, new int[]{34, 27}, new int[]{37, 27},
            new int[]{40, 27}, new int[]{43, 27}, new int[]{2, 10}, new int[]{5, 10}, new int[]{8, 10},
            new int[]{11, 10}, new int[]{14, 10}, new int[]{3, 24}, new int[]{6, 24}, new int[]{9, 24},
            new int[]{34, 24}, new int[]{37, 24}, new int[]{41, 24}, new int[]{44, 24},new int[]{23, 23},
            new int[]{24, 23},new int[]{25, 23},new int[]{26, 23}, new int[]{18, 8}, new int[]{21, 8},
            new int[]{19, 8}, new int[]{20, 8}, new int[]{17, 9}, new int[]{17, 10}, new int[]{22, 9},
            new int[]{22, 10}, new int[]{22, 24}, new int[]{22, 25}, new int[]{22, 26}, new int[]{27, 24},
            new int[]{27, 25}, new int[]{27, 26}, new int[]{23, 27}, new int[]{24, 27}, new int[]{25, 27},
            new int[]{26, 27},  new int[]{17, 27},  new int[]{14, 27}
        ));

        lakes.addAll(Arrays.asList(
            new int[]{23, 24}, new int[]{24, 24}, new int[]{25, 24}, new int[]{26, 24},
            new int[]{23, 25}, new int[]{24, 25}, new int[]{25, 25}, new int[]{26, 25},
            new int[]{23, 26}, new int[]{24, 26}, new int[]{25, 26}, new int[]{26, 26},
            new int[]{18, 9}, new int[]{19, 9}, new int[]{20, 9}, new int[]{21, 9},
            new int[]{18, 10}, new int[]{19, 10}, new int[]{20, 10}, new int[]{21, 10},
            new int[]{18, 11}, new int[]{19, 11}, new int[]{20, 11}, new int[]{21, 11}
        ));

    }



}
