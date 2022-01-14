package platform_popularity_analysis;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Main extends Application {

    private static Dataset socialData;
    private TableView<Statistic> tableView;
    private LineChart<Number, Number> lineChart;
    private VBox tableVBox;
    private VBox lineChartVBox;
    private VBox checkBoxes;
    private GridPane mainGrid;

    //Create series for linechart
    XYChart.Series<Number, Number> facebook = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> flickr = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> friendster = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> googleBz = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> googlePlus = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> hi5 = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> instagram = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> mySpace = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> whatsApp = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> tumblr = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> orkut = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> twitter = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> tiktok = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> youtube = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> snapchat = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> weibo = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> reddit = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> pinterest = new XYChart.Series<Number, Number>();
    XYChart.Series<Number, Number> weChat = new XYChart.Series<Number, Number>();
    ArrayList<XYChart.Series<Number, Number>> seriesList = new ArrayList<XYChart.Series<Number, Number>>();
    
    

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        
        primaryStage.setTitle("Natalia Garcia-Arias; Social Media Platform Growth Over Time");

        //Import data
        socialData = readStatistic();

        //Create table and linechart
        tableView = createTableView();
        lineChart = createLineChart();


        //Generate and configure grid
        mainGrid = new GridPane();
        mainGrid.setAlignment(Pos.CENTER);
        mainGrid.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
        mainGrid.setHgap(10);
        mainGrid.setVgap(10);
        mainGrid.setPadding(new Insets(20, 20, 20, 20));

        
        //Generate and configure tableVbox
        tableVBox = new VBox(5);
        tableVBox.setAlignment(Pos.TOP_CENTER);
        tableVBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,BorderStrokeStyle.SOLID, null, null)));
        tableVBox.setPadding(new Insets (20, 20, 20, 20));

        //Generate and configure lineChartVBox
        lineChartVBox = new VBox(5);
        lineChartVBox.setAlignment(Pos.CENTER);
        lineChartVBox.setPadding(new Insets (20, 20, 20, 20));
        lineChartVBox.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,BorderStrokeStyle.SOLID, null, null)));
        lineChartVBox.getChildren().add(lineChart);


        //Generate and configure checkBoxes vBox
        checkBoxes = new VBox(5);
        checkBoxes.setAlignment(Pos.TOP_LEFT);
        checkBoxes.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, null, null)));
        checkBoxes.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY,BorderStrokeStyle.SOLID, null, null)));
        checkBoxes.setPadding(new Insets (60, 20, 20, 20));
        Label filterLabel;


        //Generate boxes and initialize elements
        HBox tableBox = new HBox(5);
        VBox sortButtons = new VBox(5);

        Button sortByPlatform;
        Button sortByYear;
        Button sortByUsers;

        HBox searchBar = new HBox(5);
        searchBar.setAlignment(Pos.TOP_LEFT);

        Label searchLabel;
        TextField searchField;
        Button goBtn;

        //Add boxes to the grid
        mainGrid.add(tableVBox, 2,0);
        mainGrid.add(lineChartVBox, 1, 0);
        mainGrid.add(checkBoxes, 0, 0);

        
        //Add series to seriesList
        seriesList.add(facebook);
        seriesList.add(flickr);
        seriesList.add(friendster);
        seriesList.add(googlePlus);
        seriesList.add(hi5);
        seriesList.add(instagram);
        seriesList.add(mySpace);
        seriesList.add(whatsApp);
        seriesList.add(tumblr);
        seriesList.add(orkut);
        seriesList.add(twitter);
        seriesList.add(tiktok);
        seriesList.add(youtube);
        seriesList.add(snapchat);
        seriesList.add(weibo);
        seriesList.add(reddit);
        seriesList.add(pinterest);
        seriesList.add(weChat);


        //Create lable for the checkbox filters and add to box
        filterLabel = new Label("Select Social Media Platform: ");
        checkBoxes.getChildren().add(filterLabel);
         

       for (XYChart.Series<Number, Number> currentSeries : seriesList){
        //Create new checkbox filter for every series
        CheckBox checkBox = new CheckBox(currentSeries.getName());
        checkBoxes.getChildren().addAll(checkBox);

        //Add Listener to each checkbox
        checkBox.selectedProperty().addListener(
            (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {

                //Show series data if selected, remove if not
                if (checkBox.isSelected()){
                    
                    lineChart.getData().addAll(currentSeries);
                } else {
                    lineChart.getData().remove(currentSeries);
                }
            });
       }

        //Sort Button - sort by platform
        sortByPlatform = new Button("Sort by Platform");
        sortByPlatform.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                
                //Updates ObservableList with new sortedList
                ObservableList<Statistic> sortedListA = FXCollections.observableArrayList(socialData.sortByPlatform());
                tableView.setItems(sortedListA);
            }
        });

        //Sort Button - sort by year
        sortByYear = new Button("Sort by Year");
        sortByYear.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){

                ObservableList<Statistic> sortedListB = FXCollections.observableArrayList(socialData.sortByYear());
                tableView.setItems(sortedListB);
            }
        });

        //Sort Button - sort by users
        sortByUsers = new Button("Sort by Users");
        sortByUsers.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){

                ObservableList<Statistic> sortedListC = FXCollections.observableArrayList(socialData.sortByUsers());
                tableView.setItems(sortedListC);
            }
        });

        //Add buttons to sortButtons box
        sortButtons.getChildren().addAll(sortByYear, sortByPlatform, sortByUsers);


        //Create TextField for Search and Go button
        searchLabel = new Label("Search:");
        searchField = new TextField("");
        searchField.setPromptText("Search here");
        goBtn = new Button("Go");
       
        //Event Handler - Completes a linear search when button is clicked and updates the observable list
        goBtn.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                //Gets text from the TextField
                String key = searchField.getText();
                
                //Updates ObservableList with new searchedList
                ObservableList<Statistic> searchedList = FXCollections.observableArrayList(socialData.linearSearch(key));
                tableView.setItems(searchedList);
            }
        });

        //Add elements to their boxes
        searchBar.getChildren().addAll(searchLabel, searchField, goBtn);
        tableBox.getChildren().addAll(tableView, sortButtons);
        tableVBox.getChildren().addAll(searchBar,tableBox);

         //Creating the scene
         Scene scene = new Scene(mainGrid,1700,800);
         primaryStage.setScene(scene);
         primaryStage.show();
    
    } 

    /**
     * A method that reads data from 'users-by-social' csv file and generates new Dataset
     * @return - returns socialData dataset with imported information
     * @throws IOException
     */
    public Dataset readStatistic() throws IOException {
        
        //File Reading
        String filename = "src/platform_popularity_analysis/users-by-social.csv";

        //Reads the file until the file ends
        socialData = new Dataset();
        try {
            BufferedReader sourceData = new BufferedReader(new FileReader(filename));
            String line = "";

            while ((line = sourceData.readLine()) != null){
                String[] splitData = line.split(",");

                //Creating objects
                Statistic statistic = new Statistic(splitData[0], Integer.parseInt(splitData[1]), Long.parseLong(splitData[2]));
                socialData.addStat(statistic);
                
            } 
            sourceData.close();

        } catch (Exception e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }   

        return socialData;
    }

    /**
     * A method that generates a table to display the socialData
     * @return - returns tableView
     */
    public TableView<Statistic> createTableView()throws FileNotFoundException{

        ObservableList<Statistic> data = FXCollections.observableArrayList(socialData.getDataset());

        //Creates 'Social Media Platform' Column
        TableColumn<Statistic, String> platformNameCol = new TableColumn<>("Social Media Platform");
        platformNameCol.setCellValueFactory(new PropertyValueFactory<>("platformName"));

        //Creates 'Year' Column
        TableColumn<Statistic, Integer> statYearCol = new TableColumn<>("Year");
        statYearCol.setCellValueFactory(new PropertyValueFactory<>("year"));
        
        
        //Creates 'Monthly Active Users' Column
        TableColumn<Statistic, Long> monthlyActiveUsersCol = new TableColumn<>("Monthly Active Users");
        monthlyActiveUsersCol.setCellValueFactory(new PropertyValueFactory<>("formattedUsers"));
        

        //Create tableview and add data
        TableView<Statistic> tableView = new TableView<>();
        tableView.setItems(data);
        tableView.getColumns().addAll(platformNameCol, statYearCol, monthlyActiveUsersCol);


        //Generates a popup window if user double-clicks on a row
        tableView.setRowFactory( tv ->   {
            TableRow<Statistic> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
                    Statistic currentStat = row.getItem();
                    try {
                        popupInformation(currentStat);
                    } catch (Exception e) {
                        System.out.println("something went wrong");
                        e.printStackTrace();
                    }
                  
                }
            });
            return row ;
        });

        //Sets preferred height/width and adds to grid
        tableView.setPrefHeight(600);
        tableView.setPrefWidth(500);

        //Remove default extra columnm
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        return tableView;
    }

    /**
     * A method that generates a lineChart to display the data
     * @return - returns the linechart
     *
     */
    public LineChart<Number, Number> createLineChart(){
         
        //Variables
         LineChart<Number, Number> lineChart;
         NumberAxis xAxis;
         NumberAxis yAxis;
         
         //Label and Bounds for the X-Axis
         xAxis = new NumberAxis();
         xAxis.setLabel("Year");
         xAxis.setAutoRanging(false);
         xAxis.setLowerBound(2001);
         xAxis.setUpperBound(2020);
         xAxis.setTickUnit(1);
         xAxis.setMinorTickVisible(false);

         xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            private final DecimalFormat format = new DecimalFormat("####");
            @Override
            public String toString(Number object) {
              return format.format(object.intValue());
            }
            @Override
            public Number fromString(String string) {
              return null;
            }
          });
        
         //Label and Bounds for the Y-Axis
         yAxis = new NumberAxis();
         yAxis.setLabel("Monthly Active Users");

         //Instantiate lineChart with axis
         lineChart = new LineChart<Number, Number>(xAxis, yAxis);
         lineChart.setTitle("Growth of Active Social Media Users by Platform & Year");
         lineChart.setPrefHeight(600);
         lineChart.setPrefWidth(700);
         

         //Add statistic data into the respective series (by platform name)
         for (Statistic currentStat : socialData.getDataset()){
           
            //Helper current string 
            String currentPlatform = currentStat.getPlatformName();

            switch(currentPlatform){

                case "Facebook": addToSeries(currentStat, facebook);
        
                break;

                case "Flickr": addToSeries(currentStat, flickr);
            
                break;

                case "Friendster": addToSeries(currentStat, friendster);
        
                break;

                case "Google Buzz": addToSeries(currentStat, googleBz);
            
                break;

                case "Google+": addToSeries(currentStat, googlePlus);
                
                break;

                case "Hi5": addToSeries(currentStat, hi5);
                
                break;

                case "Instagram": addToSeries(currentStat, instagram);
                
                break;

                case "MySpace": addToSeries(currentStat, mySpace);
                
                break;

                case "Whatsapp": addToSeries(currentStat, whatsApp);
                
                break;

                case "Tumblr": addToSeries(currentStat, tumblr);
                
                break;

                case "Orkut": addToSeries(currentStat, orkut);
                
                break;

                case "Twitter": addToSeries(currentStat, twitter);
                
                break;

                case "TikTok": addToSeries(currentStat, tiktok);
                
                break;

                case "YouTube": addToSeries(currentStat, youtube);
                
                break;

                case "Snapchat": addToSeries(currentStat, snapchat);
                
                break;

                case "Weibo": addToSeries(currentStat, weibo);
                
                break;

                case "Reddit": addToSeries(currentStat, reddit);
                
                break;

                case "Pinterest": addToSeries(currentStat, pinterest);
                
                break;

                case "WeChat": addToSeries(currentStat, weChat);
                
                break;
            }
         }
         return lineChart;
    }

    /**
     * A helper method that adds chart data to the series based on input
     * @param currentStat - current statistic to be added
     * @param currentSeries - current series to add to
     */
    public static void addToSeries(Statistic currentStat, XYChart.Series<Number, Number> currentSeries){
        
        //Sets the series name to the respective platform name
        currentSeries.setName(currentStat.getPlatformName());

        //Adds currentStat data to currentSeries
        currentSeries.getData().add(new XYChart.Data<Number, Number>(currentStat.getYear(), currentStat.getMonthlyActiveUsers()));
    }

    /**
     * A method that generates a popup window displaying individual record data
     * @param currentStat - inputted statistic to display
     * @throws FileNotFoundException
     */
    public static void popupInformation(Statistic currentStat) throws FileNotFoundException{
        
        //Get information to display
        String name = currentStat.getPlatformName();
        String year = String.valueOf(currentStat.getYear());
        String monthlyUsers = currentStat.getFormattedUsers();
        String yearlyUsers = currentStat.getFormattedYearlyUsers();
        Image currentLogo = currentStat.getLogo();

        //Create stage
        Stage popupStage = new Stage();
        popupStage.setTitle(name + ", " + year);

        //Create vbox and configure attributes
        VBox content = new VBox();
        content.setPadding(new Insets(10, 10, 10, 10));
        content.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

        //Border pane to fit images
        BorderPane pane = new BorderPane();
        pane.setMaxHeight(100);
        pane.setMaxWidth(200);


        //Create imageview for displaying the social media platform logo
        ImageView viewLogo = new ImageView(currentLogo);
        viewLogo.setPreserveRatio(true);
        viewLogo.setFitHeight(80);
        viewLogo.fitWidthProperty().bind(pane.widthProperty()); 
        pane.setCenter(viewLogo);

        //Generate and configure labels
        Label platformField = new Label("", viewLogo);
        platformField.setContentDisplay(ContentDisplay.TOP);

        Label yearField = new Label("Year: " + year);
        yearField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));

        Label monthlyUsersField = new Label("Monthly Active Users: " + monthlyUsers);
        monthlyUsersField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));

        Label yearlyUsersField = new Label("Estimated Yearly Active Users: "+ yearlyUsers);
        yearlyUsersField.setFont(Font.font("Arial", FontWeight.NORMAL, 15));

        //Add generated content to VBox
        content.getChildren().addAll(pane, platformField, yearField, monthlyUsersField, yearlyUsersField);

        //Generate scene
        Scene popupScene = new Scene(content, 400, 180);
        popupStage.setScene(popupScene);
        popupStage.show();

    }

}