package platform_popularity_analysis;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashSet;

public class Dataset {

    //Instance variable(s)
    private ArrayList<Statistic> statsDataset;
 
    /**
     * Constructor - creates new Dataset
     */
    public Dataset(){
        statsDataset = new ArrayList<Statistic>();
    }

    /**
     * A method that gets the whole dataset
     * @return - returns statsDataset ArrayList
     */
    public ArrayList<Statistic> getDataset(){
        return statsDataset;
    }

    /**
     * A method that adds a new statistic to the Statistics Dataset
     * @param newStatistic - new stat to be added
     */
    public void addStat(Statistic newStatistic){
        statsDataset.add(newStatistic);
    }

    /**
     * A method that removes a statistic from the Statistic Dataset
     * @param i - index of the statistic
     */
    public void removeStat(int i){
        statsDataset.remove(i);
    }
    /**
     * 
     * A method that gets a statistic based off the given index
     * 
     * @param i - index of the object
     * @return - returns statistics object
     */
    public Statistic getStatistic(int i){
        
        return statsDataset.get(i);
    }

    /**
     * A method that iterates through the dataset and returns a list of all social media platforms
     * @return - HashSet<String> of all platforms (no duplicates)
     */
    public ArrayList<String> getAllPlatforms(){

        Iterator<Statistic> it = statsDataset.iterator();
        HashSet<String> platforms = new HashSet<String>();

        for (int i = 0; i < statsDataset.size(); i++){
            
            Statistic statistic = it.next();
            platforms.add(statistic.getPlatformName());
        }

        ArrayList<String> platformsList = new ArrayList(platforms);

        return platformsList;
    }

    /**
     * A method that conducts a linear search for the inputted value
     * @param key - user-inputted string value
     * @return - returns a new statistic-type arraylist with search results
     */
    public ArrayList<Statistic> linearSearch(String key){

        //Creates arraylist to be returned
        ArrayList<Statistic> results = new ArrayList<Statistic>();

        //Creates elements to be referred to during the search
        String nameElement;
        String yearElement;
        String usersElement;

        for (int i = 0; i < statsDataset.size(); i++){

            //Passes on the name, year, users, values of the object to the element variables
            nameElement = statsDataset.get(i).getPlatformName();
            yearElement = String.valueOf(statsDataset.get(i).getYear());
            usersElement = String.valueOf(statsDataset.get(i).getYear());

            //Checks if the key is a match to any of the elements and adds the match to the results list
            if (key.equalsIgnoreCase(nameElement)){
                results.add(getStatistic(i));

            } else if (key.equals(yearElement)){
                results.add(getStatistic(i));

            } else if (key.equals(usersElement)){
                results.add(getStatistic(i));
            } else if (key.equals("")){
                results = statsDataset;
            }
        }
        return results;
    }

    /**
     * A method that sorts the dataset alphabetically by platform name
     * @return - returns a sorted ArrayList
     */
    public ArrayList<Statistic> sortByPlatform(){
        
        int min;
        int listSize = statsDataset.size();

        for (int i = 0; i < (listSize - 1); i++){

            min = i;

            for (int j = i + 1; j < listSize; j++){

                String value1 = statsDataset.get(j).getPlatformName();
                String value2 = statsDataset.get(min).getPlatformName();

                if (value1.compareToIgnoreCase(value2) < 0 ){
                    min = j;
                }
            }

            if (i != min){

                Statistic temp = statsDataset.get(min);
                statsDataset.set(min, statsDataset.get(i));
                statsDataset.set(i, temp);

            }  
        }
        return statsDataset;
    }

    /**
     * A method that sorts the dataset numerically by year in ascending order
     * @return - returns a sorted ArrayList
     */
    public ArrayList<Statistic> sortByYear(){
        
        int min;
        int listSize = statsDataset.size();

        for (int i = 0; i < (listSize - 1); i++){

            min = i;

            for (int j = i + 1; j < listSize; j++){

                int value1 = statsDataset.get(j).getYear();
                int value2 = statsDataset.get(min).getYear();

                if (value1 < value2 ){
                    min = j;
                }
            }

            if (i != min){

                Statistic temp = statsDataset.get(min);
                statsDataset.set(min, statsDataset.get(i));
                statsDataset.set(i, temp);

            }  
        }
        return statsDataset;
    }

    /**
     * A method that sorts the dataset numerically by users in ascending order
     * @return - returns a sorted ArrayList
     */
    public ArrayList<Statistic> sortByUsers(){
        
        int min;
        int listSize = statsDataset.size();

        for (int i = 0; i < (listSize - 1); i++){

            min = i;

            for (int j = i + 1; j < listSize; j++){

                long value1 = statsDataset.get(j).getMonthlyActiveUsers();
                long value2 = statsDataset.get(min).getMonthlyActiveUsers();

                if (value1 < value2 ){
                    min = j;
                }
            }

            if (i != min){

                Statistic temp = statsDataset.get(min);
                statsDataset.set(min, statsDataset.get(i));
                statsDataset.set(i, temp);

            }
            
        }
        return statsDataset;
    }
}
