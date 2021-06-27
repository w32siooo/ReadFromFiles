import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.*;

class Api {

    //Keeps database in memory so we only have to read from files once.
    private Database database;

    Api(Database database) {
        this.database = database;
    }

    //A helper function to sort a map by values.
    private static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                (k2, k1) -> {
                    int compare =
                            map.get(k1).compareTo(map.get(k2));
                    if (compare == 0)
                        return 1;
                    else
                        return compare;
                };

        Map<K, V> sortedByValues =
                new TreeMap<>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    //A simple helper function to convert a arraylist of integers to an int array.
    private static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next();
        }
        return ret;
    }

    //A helper function to find the most frequent elements.
    private static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> elemCountMap = new HashMap<>();
        for (int num : nums) {
            elemCountMap.put(num, elemCountMap.getOrDefault(num, 0) + 1);
        }
//Sort, and find the most frequent elements.
        List<Integer> result =
                elemCountMap.entrySet().stream()
                        .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                        .limit(k)
                        .map(Entry::getKey)
                        .collect(Collectors.toList());
        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    String [] printUserSessions(){

        List sessionList = database.getSessionList();
        List productList = database.getProductList();

        //System Two

        String[] sessionUsers = new String[database.getSessionList().size()];

        //Iterate through our session list.
        for (int i = 0; i < sessionList.size(); i++) {

            //Get the single session data.
            String[] singleSession = (String[]) sessionList.get(i);

            sessionUsers[i] = singleSession[0];

            //Get the session value from the session string array.
            String str = singleSession[1];

            //Remove all whitespaces.
            str = str.replaceAll("\\s", "");

            //Print out the movie viewed in the session data.
            String[] singularProduct = (String[]) productList.get(Integer.parseInt(str) - 1);
            System.out.println("User " + singleSession[0] +" has looked at "+ Arrays.toString(singularProduct));

        }

        return sessionUsers;

    }

    void highestRatedProducts(int recommendations) {

        System.out.println("System one recommends products by the highest rated or the most purchased.");

        //Create map for storing the product Ratings.
        HashMap<Integer, Float> productRating;
        productRating = new HashMap<>();

        // In order to only make one call to our database here we load the productlist in a local variable.
        List productList = database.getProductList();

        //Populate the map with product ratings.
        for (int i = 0; i < productList.size(); i++) {

            String[] singleProduct = (String[]) productList.get(i);

            float val = Float.parseFloat(singleProduct[8]);

            productRating.put(i + 1, val);
        }

        //sort treemap by values
        Map<Integer, Float> sortedMap = sortByValues(productRating);
        // Get Set of entries
        Set<Entry<Integer, Float>> set = sortedMap.entrySet();
        // Get iterator
        Iterator<Entry<Integer, Float>> it = set.iterator();
        // Show TreeMap elements

        int counter = 0;

        System.out.println("The highest rated movies are:");

        while (it.hasNext() && counter < recommendations) {

            Entry<Integer, Float> pair = it.next();

            String[] singleProduct = (String[]) productList.get(pair.getKey() - 1);

            for (String aSingleProduct : singleProduct) {

                System.out.print(aSingleProduct);

            }

            System.out.println();

            counter++;
        }

        //find the most purchased products from users.txt

        List userList = database.getUserList();

        //Placeholder list of integers that we will use to store our list of most purchased movies in.
        ArrayList<Integer> intList = new ArrayList<>();

        for (Object anUserList : userList) {

            String[] singleUser = (String[]) anUserList;

            String temp = singleUser[2].replaceAll("\\s", "");

            String[] arrSplit = temp.split(";");

            for (String anArrSplit : arrSplit) {

                intList.add(Integer.parseInt(anArrSplit));
            }
        }


        int[] toSort;
        toSort = convertIntegers(intList);

        System.out.println("\n\nThe most purchased movies are");

        int[] topMovies = topKFrequent(toSort, 3);

        for (int topMovy : topMovies) {
            String[] singularProduct = (String[]) database.getProductList().get(topMovy - 1);
            for (String aSingularProduct : singularProduct) {
                System.out.print(aSingularProduct + " ");
            }
            System.out.println();
        }

    }

    void generateRecommendations(String userID) {

        //Load the lists we need from the databases.
        List productList = database.getProductList();
        List sessionList = database.getSessionList();

        //System Two

        System.out.println("\nSystem two recommends products (movies) based on User session data");

        //Iterate through our session list.
        for (Object aSessionList : sessionList) {

            //Get the single session data.
            String[] singleSession = (String[]) aSessionList;

            //Get the session value from the session string array.

            if (userID.equals(singleSession[0])) {

                String str = singleSession[1];

                //Remove all whitespaces.
                str = str.replaceAll("\\s", "");

                //Print out the movie viewed in the session data.
                String[] singularProduct = (String[]) productList.get(Integer.parseInt(str) - 1);


                //search productlist for similar hits. Here we grab a genre and search for matches in the product list for the given genre.

                for (int j = 0; j < 4; j++) {
                    printRecs(productList, singularProduct, j);
                }

            }

        }

    }

    //Helper function to generate recommendations based on the movie genres that the relevant userID has watched in their sessiondata array.
    private void printRecs(List productList, String[] singularProduct, int iteration) {
        System.out.println("\nRecommendations based on the "+ singularProduct[iteration+3]+ " genre");

        String toSearchfor =  singularProduct[iteration+3];

        for (Object aProductList : productList) {
            String[] tempProduct = (String[]) aProductList;
            if (Arrays.toString(tempProduct).contains(toSearchfor)) {
                System.out.println(Arrays.toString(tempProduct));
            }
        }
    }
}
