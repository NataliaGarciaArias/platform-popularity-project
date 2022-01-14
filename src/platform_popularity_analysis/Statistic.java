package platform_popularity_analysis;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
public class Statistic {
     
    //Instance variables
    private String platformName;
    private int year;
    private long monthlyActiveUsers;
    private DecimalFormat usersFormat = new DecimalFormat("###,###,###,###");
    private String logoPath;
    private Image logo;
    /**
     * Constructor - creates new instance of a statistic
     * 
     * @param platformName - String that stores the name of the social media platform
     * @param year - Integer that stores the year of the statistic
     * @param monthlyActiveUsers - Double that stores the number of monthly active users on the platform
     */
    public Statistic (String platformName, int year, long monthlyActiveUsers){
        this.platformName = platformName;
        this.year = year;
        this.monthlyActiveUsers = monthlyActiveUsers;
    }

    //Getter method for the name of the social platform
    public String getPlatformName(){
        return platformName;
    }

    //Setter method for the name of the social platform
    public void setPlatformName(String newName){
        this.platformName = newName;
    }

    //Getter method for the year of the statistic
    public int getYear(){
        return year;
    }

    //Setter method for the year of the statistic
    public void setYear(int newYear){
        this.year = newYear;
    }

    /**
     * A method that adds a logo to the designated social media platform
     * @return - returns Image object
     * @throws FileNotFoundException
     */
    public Image getLogo() throws FileNotFoundException{
        switch(platformName){
            case "Facebook": logoPath = "src/platform_popularity_analysis/logos/facebook_logo.png";
        
                break;

                case "Flickr": logoPath = "src/platform_popularity_analysis/logos/flickr_logo.png";
            
                break;

                case "Friendster": logoPath = "src/platform_popularity_analysis/logos/friendster_logo.png";
        
                break;

                case "Google Buzz": logoPath = "src/platform_popularity_analysis/logos/googleBuzz_logo.png";
            
                break;

                case "Google+": logoPath = "src/platform_popularity_analysis/logos/googlePlus_logo.png";
                
                break;

                case "Hi5": logoPath = "src/platform_popularity_analysis/logos/hi5_logo.png";
                
                break;

                case "Instagram": logoPath = "src/platform_popularity_analysis/logos/instagram_logo.png";
                
                break;

                case "MySpace": logoPath = "src/platform_popularity_analysis/logos/mySpace_logo.png";
                
                break;

                case "Whatsapp": logoPath = "src/platform_popularity_analysis/logos/whatsApp_logo.png";
                
                break;

                case "Tumblr": logoPath = "src/platform_popularity_analysis/logos/tumblr_logo.png";
                
                break;

                case "Orkut": logoPath = "src/platform_popularity_analysis/logos/orkut_logo.png";
                
                break;

                case "Twitter": logoPath = "src/platform_popularity_analysis/logos/twitter_logo.png";
                
                break;

                case "TikTok": logoPath = "src/platform_popularity_analysis/logos/tiktok_logo.png";
                
                break;

                case "YouTube": logoPath = "src/platform_popularity_analysis/logos/youtube_logo.png";
                
                break;

                case "Snapchat": logoPath = "src/platform_popularity_analysis/logos/snapchat_logo.png";
                
                break;

                case "Weibo": logoPath = "src/platform_popularity_analysis/logos/weibo_logo.jpg";
                
                break;

                case "Reddit": logoPath = "src/platform_popularity_analysis/logos/reddit_logo.png";
                
                break;

                case "Pinterest": logoPath = "src/platform_popularity_analysis/logos/pinterest_logo.png";
                
                break;

                case "WeChat": logoPath = "src/platform_popularity_analysis/logos/wechat_logo.png";
                
                break;
        }
        FileInputStream inputstream = new FileInputStream(logoPath) ;
        logo = new Image(inputstream);

        return logo;
    }
    //Getter method for the number of monthly active users
    public long getMonthlyActiveUsers(){
        return monthlyActiveUsers;
    }

    //Setter method for the number of monthly active users
    public void setMonthlyUsers(long newValue){
        this.monthlyActiveUsers = newValue;
        
    }
    /**
     * A method that formats the MonthlyUsers value
     * @return - returns formatted value
     */
    public String getFormattedUsers(){
        String formattedUsers = usersFormat.format(monthlyActiveUsers);
        return formattedUsers;
    }

    /**
     * A method that calculates the average yearly users using the monthly data
     * @return - returns calculated long value
     */
    public long getYearlyUsers(){
        long yearly = monthlyActiveUsers * 12;

        return yearly;
    }
    /**
     * A method that formats the YearlyUsers value
     * @return - returns formatted value
     */
    public String getFormattedYearlyUsers(){
        String formattedUsers = usersFormat.format(getYearlyUsers());
        return formattedUsers;
    }

    /**
     * A method that generates default string representation of data
     * @return - returns formatted string value
     */
    public String toString(){
        return "In " + year + ", " + platformName + " had " + usersFormat.format(monthlyActiveUsers) + " monthly active users";
    }
}
