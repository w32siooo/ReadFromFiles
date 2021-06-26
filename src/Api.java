import java.util.*;
import java.util.stream.Collectors;

public class Api<T> {

    private List<T> recommendations;
    private Database database;


    public Api(Database database) {
        this.database = database;
    }

    public static <K, V extends Comparable<V>> Map<K, V>
    sortByValues(final Map<K, V> map) {
        Comparator<K> valueComparator =
                new Comparator<K>() {
                    public int compare(K k2, K k1) {
                        int compare =
                                map.get(k1).compareTo(map.get(k2));
                        if (compare == 0)
                            return 1;
                        else
                            return compare;
                    }
                };

        Map<K, V> sortedByValues =
                new TreeMap<K, V>(valueComparator);
        sortedByValues.putAll(map);
        return sortedByValues;
    }

    public static int[] convertIntegers(List<Integer> integers) {
        int[] ret = new int[integers.size()];
        Iterator<Integer> iterator = integers.iterator();
        for (int i = 0; i < ret.length; i++) {
            ret[i] = iterator.next().intValue();
        }
        return ret;
    }

    //K Most Frequent Elements by sorting HashMap
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> elemCountMap = new HashMap<>();
        for (int num : nums) {
            elemCountMap.put(num, elemCountMap.getOrDefault(num, 0) + 1);
        }
//Sort by values and pick only top k elements
        List<Integer> result =
                elemCountMap.entrySet().stream()
                        .sorted((c1, c2) -> c2.getValue().compareTo(c1.getValue()))
                        .limit(k)
                        .map(i -> i.getKey())
                        .collect(Collectors.toList());
        int[] resultArr = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }
        return resultArr;
    }

    public void highestRatedProducts(int recommendations) {

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
        Map sortedMap = sortByValues(productRating);
        // Get Set of entries
        Set set = sortedMap.entrySet();
        // Get iterator
        Iterator it = set.iterator();
        // Show TreeMap elements

        int counter = 0;

        System.out.println("The highest rated movies are:");

        while (it.hasNext() && counter < recommendations) {

            Map.Entry pair = (Map.Entry) it.next();

            String[] singleProduct = (String[]) productList.get((Integer) pair.getKey() - 1);

            for (int i = 0; i < singleProduct.length; i++) {

                System.out.print(singleProduct[i] + " ");

            }

            counter++;
            System.out.println();
        }


        //find the most purchased products from users.txt


        List userList = database.getUserList();

        ArrayList intList = new ArrayList<Integer>();


        for (int i = 0; i < userList.size(); i++) {

            String[] singleUser = (String[]) userList.get(i);

            String temp = singleUser[2].replaceAll("\\s", "");

            String[] arrSplit = temp.split(";");

            for (int j = 0; j < arrSplit.length; j++) {

                intList.add(Integer.parseInt(arrSplit[j]));
            }
        }

        System.out.println(intList.toString());

        int[] toSort = convertIntegers(intList);

        System.out.println("The most purchased movies are" + Arrays.toString(topKFrequent(toSort, 3)));

        int[] topMovies = topKFrequent(toSort, 3);


        //int occurrences = Collections.frequency(intList, 7);
        //System.out.println("ocurrencies are " + occurrences);

        for (int i = 0; i < topMovies.length; i++) {
            String[] singularProduct = (String[]) database.getProductList().get(topMovies[i]-1);
            for (int j = 0; j < singularProduct.length; j++) {
                System.out.print(singularProduct[j] +" ");
            }
            System.out.println();
        }


    }

    public List<T> generateRecommendations() {

        //System Two

        System.out.println("System two recommends products (movies) based on User session data \n");

        for (int i = 0; i < database.getSessionList().size(); i++) {
            String[] singleSession = (String[]) database.getSessionList().get(i);

            System.out.println("User " + singleSession[0] + " Has viewed  \n");

            String str = singleSession[1];

            str = str.replaceAll("\\s", "");

            //System.out.println(Integer.parseInt(str));


            String[] singularProduct = (String[]) database.getProductList().get(Integer.parseInt(str) - 1);
            for (int j = 0; j < singularProduct.length; j++) {
                System.out.print(singularProduct[j]);
            }
            System.out.println("\nTherefore he is recommended to watch the following movies next:");

            for (int j = 0; j < database.getProductList().size(); j++) {
                String[] tempProduct = (String[]) database.getProductList().get(j);
                for (int k = 0; k < tempProduct.length; k++) {
                    if (tempProduct[k].contains("Thriller")) {

                        //Get all the numbers including thriller.
                        System.out.println(j + 1);
                    }

                }
            }

            //implement system that searches for String value containing relevant movies based on genres.
            //It's literally just String.contains
        }


        return recommendations;
    }
}
