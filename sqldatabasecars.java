package tryjavafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Callback;

public class ButtonSceneExample extends Application {
	
	  //TABLE VIEW AND DATA
    private ObservableList<ObservableList> data;
    private String tablename = "";
    private TableView tableview;
    public static void main(String[] args) {
        launch(args);
    }
  
   
    private void primaryComboBox(ComboBox<String> comboBox, String tableName, String columnName) {
        comboBox.getItems().clear();

        try {
        	
            // Establish a database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT " + columnName + " FROM " + tableName);
   
        

            // Populate ComboBox with the retrieved values
            ObservableList<String> madeList = FXCollections.observableArrayList();
         
            while (rs.next()) {
                madeList.add(rs.getString(columnName));
            }

            comboBox.setItems(madeList);

            // Close resources
            rs.close();
            stmt.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void buildData(String tablename) {
        Connection c;
        data = FXCollections.observableArrayList();
        try {
        	Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            //SQL FOR SELECTING ALL OF CUSTOMER
            String SQL = "SELECT * from "+tablename;            
            

          
            
            //ResultSet
            ResultSet rs = con.createStatement().executeQuery(SQL);
            tableview.getColumns().clear();
			tableview.getItems().clear();
            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
  
    public void search(ArrayList<String> list) {
        Connection c;
        data = FXCollections.observableArrayList();	
            String name=" ";
			String model=" ";
			String year=" ";
			String made=" ";
			String type=" ";
			String city=" ";
			String country=" ";
			String no=" ";
			String price=" ";
			String weight=" ";
			String street=" ";
			String id=" ";
			String building=" ";
			String part=" ";
			String car=" ";
			String f_name=" ";
			String l_name=" ";
			String job=" ";
			String address=" ";
			String date=" ";
			String customer=" ";
		
        try {
        	Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
        
	        String SQL = "SELECT * FROM " + tablename;

            if(tablename.equals("car")) {
            	 name =list.get(0);
            	 model =list.get(1);
            	 year =list.get(2);
            	 made =list.get(3);

            	 if (!name.isEmpty() || !model.isEmpty() || !year.isEmpty() || !made.isEmpty()) {
                     SQL += " WHERE ";

                     if (!name.isEmpty()) {
                         SQL += " name = '" + name + "' AND ";
                     }
                     if (!model.isEmpty()) {
                         SQL += " model = '" + model + "' AND ";
                     }
                     if (!year.isEmpty()) {
                         SQL += " year = '" + year + "' AND ";
                     }
                     if (!made.isEmpty()) {
                         SQL += " made = '" + made + "' AND ";
                     }

                     // Remove the trailing "AND" from the query
                     if (SQL.endsWith("AND ")) {
                         SQL = SQL.substring(0, SQL.length() - 5);
                     }
                 }
             }
            if(tablename.equals("manufacture")) {
           	 name =list.get(0);
           	 type =list.get(1);
           	 city =list.get(2);
           	 country =list.get(3);

           	 if (!name.isEmpty() || !type.isEmpty() || !city.isEmpty() || !country.isEmpty()) {
                    SQL += " WHERE ";

                    if (!name.isEmpty()) {
                        SQL += " name = '" + name + "' AND ";
                    }
                    if (!type.isEmpty()) {
                        SQL += " type = '" + type + "' AND ";
                    }
                    if (!city.isEmpty()) {
                        SQL += " city = '" + city + "' AND ";
                    }
                    if (!country.isEmpty()) {
                        SQL += " country = '" + country + "' AND ";
                    }

                    // Remove the trailing "AND" from the query
                    if (SQL.endsWith("AND ")) {
                        SQL = SQL.substring(0, SQL.length() - 5);
                    }
                }
            }
            if(tablename.equals("car_part")) {
           	 car =list.get(0);
           	 part =list.get(1);
           	
           	 if (!car.isEmpty() || !part.isEmpty()) {
                    SQL += " WHERE ";

                    if (!car.isEmpty()) {
                        SQL += " car = '" + car + "' AND ";
                    }
                    if (!part.isEmpty()) {
                        SQL += " part = '" + part + "' AND ";
                    }
                  

                    // Remove the trailing "AND" from the query
                    if (SQL.endsWith("AND ")) {
                        SQL = SQL.substring(0, SQL.length() - 5);
                    }
                }
            }
            if(tablename.equals("device")) {
           	 no =list.get(0);
           	 name =list.get(1);
           	 price =list.get(2);
           	 weight =list.get(3);
           	 made =list.get(4);

           	 if (!no.isEmpty() || !name.isEmpty() || !year.isEmpty() || !made.isEmpty()|| !made.isEmpty()) {
                    SQL += " WHERE ";

                    if (!no.isEmpty()) {
                        SQL += " no = '" + no + "' AND ";
                    }
                    if (!name.isEmpty()) {
                        SQL += " name = '" + name + "' AND ";
                    }
                    if (!price.isEmpty()) {
                        SQL += " price = '" + price + "' AND ";
                    }
                    if (!weight.isEmpty()) {
                        SQL += " weight = '" + weight + "' AND ";
                    }
                    if (!made.isEmpty()) {
                        SQL += " made = '" + made + "' AND ";
                    }

                    // Remove the trailing "AND" from the query
                    if (SQL.endsWith("AND ")) {
                        SQL = SQL.substring(0, SQL.length() - 5);
                    }
                }
            }
            if(tablename.equals("orders")) {
           	 id =list.get(0);
           	 date =list.get(1);
           	 customer =list.get(2);
           	 car =list.get(3);

           	 if (!id.isEmpty() || !date.isEmpty() || !customer.isEmpty() || !car.isEmpty()) {
                    SQL += " WHERE ";

                    if (!id.isEmpty()) {
                        SQL += " id = '" + id + "' AND ";
                    }
                    if (!date.isEmpty()) {
                        SQL += " date = '" + date + "' AND ";
                    }
                    if (!customer.isEmpty()) {
                        SQL += " customer = '" + customer + "' AND ";
                    }
                    if (!car.isEmpty()) {
                        SQL += " car = '" + car + "' AND ";
                    }

                    // Remove the trailing "AND" from the query
                    if (SQL.endsWith("AND ")) {
                        SQL = SQL.substring(0, SQL.length() - 5);
                    }
                }
            }
            if(tablename.equals("customer")) {
           	 id =list.get(0);
           	 f_name =list.get(1);
           	 l_name =list.get(2);
           	 address =list.get(3);
           	 job =list.get(4);

           	 if (!id.isEmpty() || !f_name.isEmpty() || !l_name.isEmpty() || !address.isEmpty()|| !job.isEmpty()) {
                    SQL += " WHERE ";

                    if (!id.isEmpty()) {
                        SQL += " id = '" + id + "' AND ";
                    }
                    if (!f_name.isEmpty()) {
                        SQL += " f_name = '" + f_name + "' AND ";
                    }
                    if (!l_name.isEmpty()) {
                        SQL += " l_name = '" + l_name + "' AND ";
                    }
                    if (!address.isEmpty()) {
                        SQL += " address = '" + address + "' AND ";
                    }
                    if (!job.isEmpty()) {
                        SQL += " job = '" + job + "' AND ";
                    }

                    // Remove the trailing "AND" from the query
                    if (SQL.endsWith("AND ")) {
                        SQL = SQL.substring(0, SQL.length() - 5);
                    }
                }
            }
            if(tablename.equals("address")) {
           	 id =list.get(0);
           	 building =list.get(1);
           	 street =list.get(2);
           	 city =list.get(3);
           	 country =list.get(4);

           	 if (!id.isEmpty() || !building.isEmpty() || !street.isEmpty() || !city.isEmpty()|| !country.isEmpty()) {
                    SQL += " WHERE ";

                    if (!id.isEmpty()) {
                        SQL += " id = '" + id + "' AND ";
                    }
                    if (!building.isEmpty()) {
                        SQL += " building = '" + building + "' AND ";
                    }
                    if (!street.isEmpty()) {
                        SQL += " street = '" + street + "' AND ";
                    }
                    if (!city.isEmpty()) {
                        SQL += " city = '" + city + "' AND ";
                    }
                    if (!country.isEmpty()) {
                        SQL += " country = '" + country + "' AND ";
                    }
                    // Remove the trailing "AND" from the query
                    if (SQL.endsWith("AND ")) {
                        SQL = SQL.substring(0, SQL.length() - 5);
                    }
                }
            }
            System.out.println("SQL Query: " + SQL);

            //ResultSet
            ResultSet rs = con.createStatement().executeQuery(SQL);

            tableview.getColumns().clear();
			tableview.getItems().clear();
            /**
             * ********************************
             * TABLE COLUMN ADDED DYNAMICALLY *
             *********************************
             */
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                //We are using non property style for making dynamic table
                final int j = i;
                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
                        return new SimpleStringProperty(param.getValue().get(j).toString());
                    }
                });

                tableview.getColumns().addAll(col);
                System.out.println("Column [" + i + "] ");
            }
            

            /**
             * ******************************
             * Data added to ObservableList *
             *******************************
             */
            while (rs.next()) {
                //Iterate Row
                ObservableList<String> row = FXCollections.observableArrayList();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    //Iterate Column
                    row.add(rs.getString(i));
                }
                System.out.println("Row [1] added " + row);
                data.add(row);

            }

            //FINALLY ADDED TO TableView
            tableview.setItems(data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error on Building Data");
        }
    }
    public void update(ArrayList<String> list) {
    	Connection c;
        data = FXCollections.observableArrayList();
        
        String name = "";
        String model = "";
        String year = "";
        String made = "";
        String type = "";
        String city = "";
        String country = "";
        String id = "";
        String date = "";
        String customer = "";
        String car = "";
        String no = "";
        String weight = "";
        String f_name = "";
        String l_name = "";
        String address = "";
        String job = "";
        String building = "";
        String street = "";
        String price="";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            Statement stmt = con.createStatement();

            // SQL FOR UPDATING DATA IN THE CAR TABLE
 

            String SQL = "";
            if (tablename.equals("car")) {
                name = list.get(0); 
                model = list.get(1);
                year = list.get(2);
                made = list.get(3);
                SQL = "UPDATE `car` SET  `model` = '" + model + "', `year` = '" + year + "', `made` = '" + made + "' WHERE `name` = '" + name + "'";
            }
            if (tablename.equals("manufacture")) {
                name = list.get(0);
                type = list.get(1);
                city = list.get(2);
                country = list.get(3);
                SQL = "UPDATE `manufacture` SET  `type` = '" + type + "', `city` = '" + city + "', `country` = '" + country + "' WHERE `name` = '" + name + "'";
            }

            if (tablename.equals("orders")) {
                id = list.get(0);
                date = list.get(1);
                customer = list.get(2);
                car = list.get(3);
                SQL = "UPDATE `orders` SET  `date` = '" + date + "', `customer` = '" + customer + "', `car` = '" + car + "' WHERE `id` = '" + id + "'";
            }

            if (tablename.equals("device")) {
                no = list.get(0);
                name = list.get(1);
                price = list.get(2);
                weight = list.get(3);
                made = list.get(4);
                SQL = "UPDATE `device` SET  `name` = '" + name + "', `price` = '" + price + "', `weight` = '" + weight + "', `made` = '" + made + "' WHERE `no` = '" + no + "'";
            }

            if (tablename.equals("customer")) {
                id = list.get(0);
                f_name = list.get(1);
                l_name = list.get(2);
                address = list.get(3);
                job = list.get(4);
                SQL = "UPDATE `customer` SET  `f_name` = '" + f_name + "', `l_name` = '" + l_name + "', `address` = '" + address + "', `job` = '" + job + "' WHERE `id` = '" + id + "'";
            }

            if (tablename.equals("address")) {
                id = list.get(0);
                building = list.get(1);
                street = list.get(2);
                city = list.get(3);
                country = list.get(4);
                SQL = "UPDATE `address` SET  `building` = '" + building + "', `street` = '" + street + "', `city` = '" + city + "', `country` = '" + country + "' WHERE `id` = '" + id + "'";
            }

        

            
            
            
            
            
            
            
            
            System.out.println("SQL Query: " + SQL);

            int rowsAffected = stmt.executeUpdate(SQL);
            if (rowsAffected > 0) {
                System.out.println("Record updated successfully.");
                // Refresh TableView or rebuild data after update
                buildData(tablename);
            }
            stmt.close();
        } catch (Exception e) {
        	 System.out.println("Invalid year value: " + year);
        	    e.printStackTrace();
        }
    }
    public void insert(ArrayList<String> list) {
        Connection c;
        data = FXCollections.observableArrayList(); 
        // Declare strings outside if statements
            String SQL = "";
            String name = "";
            String model = "";
            String year = "";
            String made = "";
            String type = "";
            String city = "";
            String country = "";
            String id = "";
            String date = "";
            String customer = "";
            String car = "";
            String no = "";
            String weight = "";
            String f_name = "";
            String l_name = "";
            String address = "";
            String job = "";
            String building = "";
            String street = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            Statement stmt = con.createStatement();

          
       

            // Assign values within if statements
            if (tablename.equals("car")) {
                name = list.get(0);
                model = list.get(1);
                year = list.get(2);
                made = list.get(3);

                SQL = "INSERT INTO `car` (`name`, `model`, `year`, `made`) VALUES ('" + name + "', '" + model + "', '" + year + "', '" + made + "')";
            }

            if (tablename.equals("manufacture")) {
                name = list.get(0);
                type = list.get(1);
                city = list.get(2);
                country = list.get(3);

                SQL = "INSERT INTO `manufacture` (`name`, `type`, `city`, `country`) VALUES ('" + name + "', '" + type + "', '" + city + "', '" + country + "')";
            }

            if (tablename.equals("orders")) {
                id = list.get(0);
                date = list.get(1);
                customer = list.get(2);
                car = list.get(3);

                SQL = "INSERT INTO `orders` (`id`, `date`, `customer`, `car`) VALUES ('" + id + "', '" + date + "', '" + customer + "', '" + car + "')";
            }

            if (tablename.equals("device")) {
                no = list.get(0);
                name = list.get(1);
                String price = list.get(2);
                weight = list.get(3);
                String made1 = list.get(4);

                SQL = "INSERT INTO `device` (`no`, `name`, `price`, `weight`, `made`) VALUES ('" + no + "', '" + name + "', '" + price + "', '" + weight + "', '" + made1 + "')";
            }

            if (tablename.equals("customer")) {
                id = list.get(0);
                f_name = list.get(1);
                l_name = list.get(2);
                address = list.get(3);
                job = list.get(4);

                SQL = "INSERT INTO `customer` (`id`, `f_name`, `l_name`, `address`, `job`) VALUES ('" + id + "', '" + f_name + "', '" + l_name + "', '" + address + "', '" + job + "')";
            }

            if (tablename.equals("address")) {
                id = list.get(0);
                building = list.get(1);
                street = list.get(2);
                city = list.get(3);
                country = list.get(4);

                SQL = "INSERT INTO `address` (`id`, `building`, `street`, `city`, `country`) VALUES ('" + id + "', '" + building + "', '" + street + "', '" + city + "', '" + country + "')";
            }

            if (tablename.equals("car_part")) {
                car = list.get(0);
                String part = list.get(1);

                SQL = "INSERT INTO `car_part` (`car`, `part`) VALUES ('" + car + "', '" + part + "')";
            }

            // Execute the SQL statement using your database connection
            // ...

            System.out.println("SQL Query: " + SQL);

            int rowsAffected = stmt.executeUpdate(SQL);
            if (rowsAffected > 0) {
                System.out.println("Address inserted successfully.");
                // Refresh TableView or rebuild data after insertion
                buildData(tablename);
            } else {
                System.out.println("Address insertion failed.");
            }
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error inserting Address");}
    }
    public void delete(ArrayList<String> list) {
    	Connection c;
        data = FXCollections.observableArrayList();
  
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cars", "root", "");
            Statement stmt = con.createStatement();

            // SQL FOR UPDATING DATA IN THE CAR TABLE
 

            String SQL = "";
                String id = list.get(0);

            if (tablename.equals("car")) {

                SQL = "DELETE FROM `car` WHERE `name` = '" + id + "'";
            }

            if (tablename.equals("manufacture")) {
                SQL = "DELETE FROM `manufacture` WHERE `name` = '" + id + "'";
            }

            if (tablename.equals("orders")) {
                SQL = "DELETE FROM `orders` WHERE `id` = '" + id + "'";
            }

            if (tablename.equals("device")) {
                SQL = "DELETE FROM `device` WHERE `no` = '" + id + "'";
            }

            if (tablename.equals("customer")) {
                SQL = "DELETE FROM `customer` WHERE `id` = '" + id + "'";
            }

            if (tablename.equals("address")) {
                SQL = "DELETE FROM `address` WHERE `id` = '" + id + "'";
            }

            if (tablename.equals("car_part")) {
                SQL = "DELETE FROM `car_part` WHERE `car` = '" + id + "'";
            }


        
            System.out.println("SQL Query: " + SQL);

            int rowsAffected = stmt.executeUpdate(SQL);
            if (rowsAffected > 0) {
                System.out.println("Record updated successfully.");
                // Refresh TableView or rebuild data after update
                buildData(tablename);
            }
            stmt.close();
        } catch (Exception e) {
        	 System.out.println("Invalid year value: " );
        	    e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) {
    	
    	
        primaryStage.setTitle("Button Stage Example");

        // Create buttons
        Button car = new Button("car");
        Button manufacture = new Button("manufacture");
        Button car_part = new Button("car_part");
        Button customer = new Button("customer");
        Button address = new Button("address");
        Button orders = new Button("orders");
        Button device = new Button("device");
        Button search = new Button("search");
        Button insert = new Button("insert");
        Button delete = new Button("delete");
      	Button updateButton = new Button("Update");

        // Create a GridPane layout
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10); // Horizontal gap between buttons
        gridPane.setVgap(10); // Vertical gap between buttons

        // Add buttons to the GridPane
        gridPane.add(car, 0, 0);
        gridPane.add(manufacture, 1, 0);
        gridPane.add(car_part, 2, 0);
        gridPane.add(customer, 3, 0);
        gridPane.add(address, 1, 1);
        gridPane.add(orders, 2, 1);
        gridPane.add(device, 3, 1);

        VBox vbox = new VBox(search,insert,delete,updateButton);

        vbox.setSpacing(10); // Adjust the spacing between buttons in the VBox

        BorderPane.setMargin(vbox, new javafx.geometry.Insets(0, 40, 0, 0));

        // Create a BorderPane to accommodate both the GridPane and VBox
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(gridPane); // Set the GridPane in the center
        tableview = new TableView();    

        borderPane.setBottom(tableview); 
        borderPane.setLeft(vbox);
        TextField textField1 = new TextField();
	        TextField textField2 = new TextField();
	        TextField textField3 = new TextField();
 	      VBox vbox2 = new VBox();

	
        // Set actions for buttons
    	car.setOnAction(event -> {
    		 
    		tablename="car";
   	        buildData(tablename);

    	    // You can perform other actions with the entered text here
    	});
      	manufacture.setOnAction(event -> {
 		  
      		tablename="manufacture";
   	        buildData(tablename);

 		
 	    // You can perform other actions with the entered text here
 	});
      	car_part.setOnAction(event -> {
 		
      		tablename="car_part";
   	        buildData(tablename);

 		
 	    // You can perform other actions with the entered text here
 	});
      	customer.setOnAction(event -> {
 		 
      		tablename="customer";
   	        buildData(tablename);

 		
 	    // You can perform other actions with the entered text here
 	});
      	address.setOnAction(event -> {
 		
      		tablename="address";
   	        buildData(tablename);

 		
 	    // You can perform other actions with the entered text here
 	});
      	orders.setOnAction(event -> {
 		 
 	        
      		tablename="orders";
   	        buildData(tablename);

 	    // You can perform other actions with the entered text here
 	});

  	device.setOnAction(event -> {
 		 
 	        
  		tablename="device";
   	        buildData(tablename);

 	    // You can perform other actions with the entered text here
 	});
  	search.setOnAction(event -> {
  	    // Create a new stage for search
  	    Stage searchStage = new Stage();
  	    searchStage.setTitle("Search " + tablename);

	    // Create a button for executing the search
	    Button executeSearchButton = new Button("Search");
  	    VBox searchVBox = new VBox(10);
        ArrayList<String> list = new ArrayList<>();
if(tablename.equals("car") ) {

	 TextField nametxt = new TextField();
	    TextField modeltxt = new TextField();
	    TextField yeartxt = new TextField();
	    TextField madetxt = new TextField();
 	    executeSearchButton.setOnAction(searchEvent -> {
  	        // Get the search criteria from text fields
  	        String name = nametxt.getText();
  	        String model = modeltxt.getText();
  	        String year = yeartxt.getText();
  	        String made = madetxt.getText();
list.add(name);
list.add(model);
list.add(year);
list.add(made);

  	        // Call the search function with the specified criteria
  	        search(list);
  	      nametxt.clear();
  	    modeltxt.clear();
  	  yeartxt.clear();
  	madetxt.clear();

  	    });

  	    // Create a VBox to organize the elements in the search stage
  	     searchVBox = new VBox(10);
  	    searchVBox.setAlignment(Pos.CENTER);
  	    searchVBox.getChildren().addAll(
  	            new Label("Search Criteria:"),
  	            new HBox(10, new Label("name:"), nametxt),
  	            new HBox(10, new Label("model:"), modeltxt),
  	            new HBox(10, new Label("year:"), yeartxt),
  	            new HBox(10, new Label("made:"), madetxt),
  	            executeSearchButton  	    );

}
if (tablename.equals("manufacture")) {

    TextField nametxt = new TextField();
    TextField typetxt = new TextField();
    TextField citytxt = new TextField();
    TextField countrytxt = new TextField();
    executeSearchButton.setOnAction(searchEvent -> {
        // Get the search criteria from text fields
        String name = nametxt.getText();
        String type = typetxt.getText();
        String city = citytxt.getText();
        String country = countrytxt.getText();
        
        list.add(name);
        list.add(type);
        list.add(city);
        list.add(country);

        // Call the search function with the specified criteria
        search(list);
        nametxt.clear();
        typetxt.clear();
        citytxt.clear();
        countrytxt.clear();

    });

    // Create a VBox to organize the elements in the search stage
    searchVBox = new VBox(10);
    searchVBox.setAlignment(Pos.CENTER);
    searchVBox.getChildren().addAll(
            new Label("Search Criteria:"),
            new HBox(10, new Label("name:"), nametxt),
            new HBox(10, new Label("type:"), typetxt),
            new HBox(10, new Label("city:"), citytxt),
            new HBox(10, new Label("country:"), countrytxt),
            executeSearchButton
    );
}
if( tablename.equals("orders")) {

	 TextField idtxt = new TextField();
	    TextField datetxt = new TextField();
	    TextField customertxt = new TextField();
	    TextField cartxt = new TextField();
	    executeSearchButton.setOnAction(searchEvent -> {
  	        // Get the search criteria from text fields
  	        String id = idtxt.getText();
  	        String date = datetxt.getText();
  	        String customerr = customertxt.getText();
  	        String carr = cartxt.getText();
  	      list.add(id);
    	    list.add(date);
    	    list.add(customerr);
    	    list.add(carr);

    	      	        // Call the search function with the specified criteria
    	      	        search(list);
    	      	      idtxt.clear();
    	      	    datetxt.clear();
    	      	  cartxt.clear();
    	      	  	customertxt.clear();

    	    });
  	   
  	    // Create a VBox to organize the elements in the search stage
  	     searchVBox = new VBox(10);
  	    searchVBox.setAlignment(Pos.CENTER);
  	    searchVBox.getChildren().addAll(
  	            new Label("Search Criteria:"),
  	            new HBox(10, new Label("ID:"), idtxt),
  	            new HBox(10, new Label("date:"), datetxt),
  	            new HBox(10, new Label("customer:"), customertxt),
  	            new HBox(10, new Label("car:"), cartxt),
  	            executeSearchButton 
  	            );
}
if(tablename.equals("device")) {

	 TextField notxt = new TextField();
	    TextField nametxt = new TextField();
	    TextField pricetxt = new TextField();
	    TextField weighttxt = new TextField();
	    TextField madetxt = new TextField();
	    executeSearchButton.setOnAction(searchEvent -> {
  	        // Get the search criteria from text fields
  	        String  no = notxt.getText();
  	        String  name = nametxt.getText();
  	        String price = pricetxt.getText();
  	        String weight = weighttxt.getText();
  	        String madee = madetxt.getText();

  	      list.add(no);
  	    list.add(name);
  	    list.add(price);
  	    list.add(weight);
  	    list.add(madee);

  	      	        // Call the search function with the specified criteria
  	      	        search(list);
  	      	    notxt.clear();
  	      	nametxt.clear();
  	      pricetxt.clear();
  	    weighttxt.clear();
  	  madetxt.clear();

  	    });
	   
	    // Create a VBox to organize the elements in the search stage
	     searchVBox = new VBox(10);
	    searchVBox.setAlignment(Pos.CENTER);
	    searchVBox.getChildren().addAll(
	            new Label("Search Criteria:"),
	            new HBox(10, new Label("no:"), notxt),
	            new HBox(10, new Label("name:"), nametxt),
	            new HBox(10, new Label("price:"), pricetxt),
	            new HBox(10, new Label("weight:"), weighttxt),	    
	            new HBox(10, new Label("made:"), madetxt),

	            executeSearchButton 
	            );
}
if(tablename.equals("customer") ) {

	 TextField idtxt = new TextField();
	    TextField f_nametxt = new TextField();
	    TextField l_nametxt = new TextField();
	    TextField addresstxt = new TextField();
	    TextField jobtxt = new TextField();
	    executeSearchButton.setOnAction(searchEvent -> {
  	        // Get the search criteria from text fields
  	        String id = idtxt.getText();
  	        String f_name = f_nametxt.getText();
  	        String l_name = l_nametxt.getText();
  	        String addresss = addresstxt.getText();
  	        String job = jobtxt.getText();

    	      list.add(id);
    	    list.add(f_name);
    	    list.add(l_name);
    	    list.add(addresss);
    	    list.add(job);

    	      	        // Call the search function with the specified criteria
    	      	        search(list);
    	      	      idtxt.clear();
    	      	    f_nametxt.clear();
    	      	  l_nametxt.clear();
    	      	addresstxt.clear();
    	      	jobtxt.clear();

    	    });
  	   
  	    // Create a VBox to organize the elements in the search stage
  	     searchVBox = new VBox(10);
  	    searchVBox.setAlignment(Pos.CENTER);
  	    searchVBox.getChildren().addAll(
  	            new Label("Search Criteria:"),
  	            new HBox(10, new Label("id"), idtxt),
  	            new HBox(10, new Label("fisrt name:"), f_nametxt),
  	            new HBox(10, new Label("last name:"), l_nametxt),
  	            new HBox(10, new Label("address:"), addresstxt),	    
  	            new HBox(10, new Label("job:"), jobtxt),

  	            executeSearchButton 
  	            );
}
if(tablename.equals("address")) {

	    TextField idtxt = new TextField();
	    TextField buildingtxt = new TextField();
	    TextField streettxt = new TextField();
	    TextField citytxt = new TextField();
	    TextField countrytxt = new TextField();
	    executeSearchButton.setOnAction(searchEvent -> {
  	        // Get the search criteria from text fields
  	        String name = buildingtxt.getText();
  	        String model = streettxt.getText();
  	        String year = citytxt.getText();
  	        String made = countrytxt.getText();
  	        String id = idtxt.getText();
   	     
  	        list.add(id);
 	      list.add(name);
 	    list.add(model);
 	    list.add(year);
 	    list.add(made);

 	      	        // Call the search function with the specified criteria
 	      	        search(list);
 	      	     buildingtxt.clear();
 	      	  streettxt.clear();
 	      	citytxt.clear();
 	      	countrytxt.clear();
 	      	idtxt.clear();
 	    });
	   
	    // Create a VBox to organize the elements in the search stage
	     searchVBox = new VBox(10);
	    searchVBox.setAlignment(Pos.CENTER);
	    searchVBox.getChildren().addAll(
	            new Label("Search Criteria:"),
	            new HBox(10, new Label("id"), idtxt),
	            new HBox(10, new Label("building:"), buildingtxt),
	            new HBox(10, new Label("street:"), streettxt),
	            new HBox(10, new Label("city:"), citytxt),	    
	            new HBox(10, new Label("country:"), countrytxt),

	            executeSearchButton 
	            );

}
if(tablename.equals("car_part") ) {

	 TextField cartxt = new TextField();
	    TextField parttxt = new TextField();
	    
	    executeSearchButton.setOnAction(searchEvent -> {
  	        // Get the search criteria from text fields
  	        String carrr = cartxt.getText();
  	        String partt = parttxt.getText();
  	       

  	      
   	    list.add(carrr);
   	    list.add(partt);

   	      	        // Call the search function with the specified criteria
   	      	        search(list);
   	      	   cartxt.clear();
   	      	parttxt.clear();
	  		        
   	    });
  	   
  	    // Create a VBox to organize the elements in the search stage
  	     searchVBox = new VBox(10);
  	    searchVBox.setAlignment(Pos.CENTER);
  	    searchVBox.getChildren().addAll(
  	            new Label("Search Criteria:"),
  	            new HBox(10, new Label("car"), cartxt),
  	            new HBox(10, new Label("part:"), parttxt),

  	            executeSearchButton 
  	            );
}
  	  

  	    // Create a TableView for displaying search results
  	   
  	

  	    // Set up the search stage
  	    Scene searchScene = new Scene(searchVBox, 400, 300);
  	    searchStage.setScene(searchScene);
  	    searchStage.show();
  	});

  	insert.setOnAction(event -> {
  	  	// Create a new stage for insert
  	  		Stage insertStage = new Stage();
  	  		insertStage.setTitle("Insert " + tablename);

  	  		// Create a button for executing the insert
  	  		Button executeInsertButton = new Button("Insert");
  	  		VBox insertVBox = new VBox(10);
  	  		ArrayList<String> list = new ArrayList<>();
  	  	  ComboBox<String> ComboBox = new ComboBox<>();
  	  	  ComboBox<String> ComboBox2 = new ComboBox<>();

  	  		if (tablename.equals("car")) {
  	  		    TextField nametxt = new TextField();
  	  		    TextField modeltxt = new TextField();
  	  		    TextField yeartxt = new TextField();
  	  		    
  	  		    // Create a ComboBox to display "made" values
  	  		  
  	  		    // Populate ComboBox with "made" values from the car table
  	  	      primaryComboBox(ComboBox, "car", "made");

  	  		    executeInsertButton.setOnAction(insertEvent -> {
  	  		        // Get the insert data from text fields and ComboBox
  	  		        String name = nametxt.getText();
  	  		        String model = modeltxt.getText();
  	  		        String year = yeartxt.getText();
  	  		        String made = ComboBox.getValue(); // Get the selected "made" value from ComboBox

  	  		        // Add data to the list
  	  		        list.add(name);
  	  		        list.add(model);
  	  		        list.add(year);
  	  		        list.add(made);

  	  		        // Call the insert method with the specified data
  	  		        insert(list);

  	  		        // Clear text fields after insert
  	  		        nametxt.clear();
  	  		        modeltxt.clear();
  	  		        yeartxt.clear();
  	  		        ComboBox.getSelectionModel().clearSelection();
  	  		    });

  	  		    // Create a VBox to organize the elements in the insert stage
  	  		    insertVBox = new VBox(10);
  	  		    insertVBox.setAlignment(Pos.CENTER);
  	  		    insertVBox.getChildren().addAll(
  	  		            new Label("Insert Data:"),
  	  		            new HBox(10, new Label("Name:"), nametxt),
  	  		            new HBox(10, new Label("Model:"), modeltxt),
  	  		            new HBox(10, new Label("Year:"), yeartxt),
  	  		            new HBox(10, new Label("Made:"), ComboBox),
  	  		            executeInsertButton
  	  		    );
  	  		}
  	  	if (tablename.equals("manufacture")) {
  	      TextField nametxt = new TextField();
  	      TextField typetxt = new TextField();
  	      TextField citytxt = new TextField();
  	      TextField countrytxt = new TextField();

  	      executeInsertButton.setOnAction(insertEvent -> {
  	          // Get the insert data from text fields
  	          String name = nametxt.getText();
  	          String type = typetxt.getText();
  	          String city = citytxt.getText();
  	          String country = countrytxt.getText();

  	          // Add data to the list
  	          list.add(name);
  	          list.add(type);
  	          list.add(city);
  	          list.add(country);

  	          // Call the insert method with the specified data
  	          insert(list);

  	          // Clear text fields after insert
  	          nametxt.clear();
  	          typetxt.clear();
  	          citytxt.clear();
  	          countrytxt.clear();
  	      });

  	      // Create a VBox to organize the elements in the insert stage
  	      insertVBox = new VBox(10);
  	      insertVBox.setAlignment(Pos.CENTER);
  	      insertVBox.getChildren().addAll(
  	              new Label("Insert Data:"),
  	              new HBox(10, new Label("Name:"), nametxt),
  	              new HBox(10, new Label("Type:"), typetxt),
  	              new HBox(10, new Label("City:"), citytxt),
  	              new HBox(10, new Label("Country:"), countrytxt),
  	              executeInsertButton
  	      );
  	  }

  	  if (tablename.equals("orders")) {
  	      TextField idtxt = new TextField();
  	      TextField datetxt = new TextField();
  	      TextField cartxt = new TextField();
  	      primaryComboBox(ComboBox, "orders", "customer");

  	      executeInsertButton.setOnAction(insertEvent -> {
  	          // Get the insert data from text fields
  	          String id = idtxt.getText();
  	          String date = datetxt.getText();
  	          String customerr = ComboBox.getValue();
  	          String carr = cartxt.getText();

  	          // Add data to the list
  	          list.add(id);
  	          list.add(date);
  	          list.add(customerr);
  	          list.add(carr);

  	          // Call the insert method with the specified data
  	          insert(list);

  	          // Clear text fields after insert
  	          idtxt.clear();
  	          datetxt.clear();
		        ComboBox.getSelectionModel().clearSelection();
  	          cartxt.clear();
  	      });

  	      // Create a VBox to organize the elements in the insert stage
  	      insertVBox = new VBox(10);
  	      insertVBox.setAlignment(Pos.CENTER);
  	      insertVBox.getChildren().addAll(
  	              new Label("Insert Data:"),
  	              new HBox(10, new Label("ID:"), idtxt),
  	              new HBox(10, new Label("Date:"), datetxt),
  	              new HBox(10, new Label("Customer:"), ComboBox),
  	              new HBox(10, new Label("Car:"), cartxt),
  	              executeInsertButton
  	      );
  	  }

  

  	  if (tablename.equals("device")) {
  	      TextField notxt = new TextField();
  	      TextField nametxt = new TextField();
  	      TextField pricetxt = new TextField();
  	      TextField weighttxt = new TextField();
	  	      primaryComboBox(ComboBox, "device", "made");

  	      executeInsertButton.setOnAction(insertEvent -> {
  	          // Get the insert data from text fields
  	          String no = notxt.getText();
  	          String name = nametxt.getText();
  	          String price = pricetxt.getText();
  	          String weight = weighttxt.getText();
  	          String made = ComboBox.getValue();

  	          // Add data to the list
  	          list.add(no);
  	          list.add(name);
  	          list.add(price);
  	          list.add(weight);
  	          list.add(made);

  	          // Call the insert method with the specified data
  	          insert(list);

  	          // Clear text fields after insert
  	          notxt.clear();
  	          nametxt.clear();
  	          pricetxt.clear();
  	          weighttxt.clear();
  		        ComboBox.getSelectionModel().clearSelection();
  	      });

  	      // Create a VBox to organize the elements in the insert stage
  	      insertVBox = new VBox(10);
  	      insertVBox.setAlignment(Pos.CENTER);
  	      insertVBox.getChildren().addAll(
  	              new Label("Insert Data:"),
  	              new HBox(10, new Label("No:"), notxt),
  	              new HBox(10, new Label("Name:"), nametxt),
  	              new HBox(10, new Label("Price:"), pricetxt),
  	              new HBox(10, new Label("Weight:"), weighttxt),
  	              new HBox(10, new Label("Made:"), ComboBox),
  	              executeInsertButton
  	      );
  	  }

  	  // Repeat the similar structure for customer, address, and car_part sections
  	  // ...

  	  if (tablename.equals("customer")) {
  	      TextField idtxt = new TextField();
  	      TextField f_nametxt = new TextField();
  	      TextField l_nametxt = new TextField();
  	      TextField jobtxt = new TextField();
  	      primaryComboBox(ComboBox, "customer", "address");

  	      executeInsertButton.setOnAction(insertEvent -> {
  	          // Get the insert data from text fields
  	          String id = idtxt.getText();
  	          String f_name = f_nametxt.getText();
  	          String l_name = l_nametxt.getText();
  	          String addresss = ComboBox.getValue();
  	          String job = jobtxt.getText();

  	          // Add data to the list
  	          list.add(id);
  	          list.add(f_name);
  	          list.add(l_name);
  	          list.add(addresss);
  	          list.add(job);

  	          // Call the insert method with the specified data
  	          insert(list);

  	          // Clear text fields after insert
  	          idtxt.clear();
  	          f_nametxt.clear();
  	          l_nametxt.clear();
		        ComboBox.getSelectionModel().clearSelection();
  	          jobtxt.clear();
  	      });

  	      // Create a VBox to organize the elements in the insert stage
  	      insertVBox = new VBox(10);
  	      insertVBox.setAlignment(Pos.CENTER);
  	      insertVBox.getChildren().addAll(
  	              new Label("Insert Data:"),
  	              new HBox(10, new Label("ID:"), idtxt),
  	              new HBox(10, new Label("First Name:"), f_nametxt),
  	              new HBox(10, new Label("Last Name:"), l_nametxt),
  	              new HBox(10, new Label("Address:"), ComboBox),
  	              new HBox(10, new Label("Job:"), jobtxt),
  	              executeInsertButton
  	      );
  	  }

  	  if (tablename.equals("address")) {
  	      TextField idtxt = new TextField();
  	      TextField buildingtxt = new TextField();
  	      TextField streettxt = new TextField();
  	      TextField citytxt = new TextField();
  	      TextField countrytxt = new TextField();

  	      executeInsertButton.setOnAction(insertEvent -> {
  	          // Get the insert data from text fields
  	          String id = idtxt.getText();
  	          String building = buildingtxt.getText();
  	          String street = streettxt.getText();
  	          String city = citytxt.getText();
  	          String country = countrytxt.getText();

  	          // Add data to the list
  	          list.add(id);
  	          list.add(building);
  	          list.add(street);
  	          list.add(city);
  	          list.add(country);

  	          // Call the insert method with the specified data
  	          insert(list);

  	          // Clear text fields after insert
  	          idtxt.clear();
  	          buildingtxt.clear();
  	          streettxt.clear();
  	          citytxt.clear();
  	          countrytxt.clear();
  	      });

  	      // Create a VBox to organize the elements in the insert stage
  	      insertVBox = new VBox(10);
  	      insertVBox.setAlignment(Pos.CENTER);
  	      insertVBox.getChildren().addAll(
  	              new Label("Insert Data:"),
  	              new HBox(10, new Label("ID:"), idtxt),
  	              new HBox(10, new Label("Building:"), buildingtxt),
  	              new HBox(10, new Label("Street:"), streettxt),
  	              new HBox(10, new Label("City:"), citytxt),
  	              new HBox(10, new Label("Country:"), countrytxt),
  	              executeInsertButton
  	      );
  	  }

  	  if (tablename.equals("car_part")) {
  	      TextField cartxt = new TextField();
  	      TextField parttxt = new TextField();

  	      executeInsertButton.setOnAction(insertEvent -> {
  	          // Get the insert data from text fields
  	          String carr = cartxt.getText();
  	          String part = parttxt.getText();

  	          // Add data to the list
  	          list.add(carr);
  	          list.add(part);

  	          // Call the insert method with the specified data
  	          insert(list);

  	          // Clear text fields after insert
  	          cartxt.clear();
  	          parttxt.clear();
  	      });

  	      // Create a VBox to organize the elements in the insert stage
  	      insertVBox = new VBox(10);
  	      insertVBox.setAlignment(Pos.CENTER);
  	      insertVBox.getChildren().addAll(
  	              new Label("Insert Data:"),
  	              new HBox(10, new Label("Car:"), cartxt),
  	              new HBox(10, new Label("Part:"), parttxt),
  	              executeInsertButton
  	      );
  	  }


  	  		// Set up the insert stage
  	  		Scene insertScene = new Scene(insertVBox, 400, 300);
  	  		insertStage.setScene(insertScene);
  	  		insertStage.show();

  	  	});


  	// Attach an event handler to the update button
  	updateButton.setOnAction(event -> {
  	    // Create a new stage for update
  	    Stage updateStage = new Stage();
  	    updateStage.setTitle("Update " + tablename);

  	    // Create a button for executing the update
  	    Button executeUpdateButton = new Button("Update");
  	    VBox updateVBox = new VBox(10);

  	    ArrayList<String> list = new ArrayList<>();
	        ComboBox<String> ComboBox = new ComboBox<>();
	        ComboBox<String> ComboBox2 = new ComboBox<>();
	        ComboBox<String> ComboBox3 = new ComboBox<>();

  	    if (tablename.equals("car")) {
  	        TextField modeltxt = new TextField();
  	        TextField yeartxt = new TextField();
  	        // Create a ComboBox to display "made" values
  	        // Populate ComboBox with "made" values from the car table
  	      primaryComboBox(ComboBox, "car", "name");
  	      primaryComboBox(ComboBox2, "car", "made");

  	        executeUpdateButton.setOnAction(updateEvent -> {
  	            // Get the update data from text fields and ComboBox
  	            String name =  ComboBox.getValue(); 
  	            String model = modeltxt.getText();
  	            String year = yeartxt.getText();
  	            String made = ComboBox2.getValue();

  	            // Add data to the list
  	            list.add(name);
  	            list.add(model);
  	            list.add(year);
  	            list.add(made);

  	            // Call the update method with the specified data
  	            update(list);

  	            // Clear text fields after update
  	            ComboBox.getSelectionModel().clearSelection();
  	            modeltxt.clear();
  	            yeartxt.clear();
  	            ComboBox2.getSelectionModel().clearSelection();

  	        });

  	        // Create a VBox to organize the elements in the update stage
  	        updateVBox = new VBox(10);
  	        updateVBox.setAlignment(Pos.CENTER);
  	        updateVBox.getChildren().addAll(
  	                new Label("Update Data:"),
  	                new HBox(10, new Label("Name:"), ComboBox),
  	                new HBox(10, new Label("Model:"), modeltxt),
  	                new HBox(10, new Label("Year:"), yeartxt),
  	                new HBox(10, new Label("Made:"), ComboBox2),
  	                executeUpdateButton
  	        );
  	    }
  	  if (tablename.equals("manufacture")) {
  	    TextField typetxt = new TextField();
  	    TextField citytxt = new TextField();
  	    TextField countrytxt = new TextField();
  	    // Create a ComboBox to display "name" values
  	    primaryComboBox(ComboBox, "manufacture", "name");

  	    executeUpdateButton.setOnAction(updateEvent -> {
  	        // Get the update data from text fields and ComboBox
  	        String name = ComboBox.getValue();
  	        String type = typetxt.getText();
  	        String city = citytxt.getText();
  	        String country = countrytxt.getText();

  	        // Add data to the list
  	        list.add(name);
  	        list.add(type);
  	        list.add(city);
  	        list.add(country);

  	        // Call the update method with the specified data
  	        update(list);

  	        // Clear text fields after update
  	        ComboBox.getSelectionModel().clearSelection();
  	        typetxt.clear();
  	        citytxt.clear();
  	        countrytxt.clear();
  	    });

  	    // Create a VBox to organize the elements in the update stage
  	    updateVBox = new VBox(10);
  	    updateVBox.setAlignment(Pos.CENTER);
  	    updateVBox.getChildren().addAll(
  	            new Label("Update Data:"),
  	            new HBox(10, new Label("Name:"), ComboBox),
  	            new HBox(10, new Label("Type:"), typetxt),
  	            new HBox(10, new Label("City:"), citytxt),
  	            new HBox(10, new Label("Country:"), countrytxt),
  	            executeUpdateButton
  	    );
  	}
  	if (tablename.equals("device")) {
  	    TextField nametxt = new TextField();
  	    TextField pricetxt = new TextField();
  	    TextField weighttxt = new TextField();
  	    // Create a ComboBox to display "no" values
  	    primaryComboBox(ComboBox, "device", "no");
  	    // Create a ComboBox to display "made" values
  	    primaryComboBox(ComboBox2, "device", "made");

  	    executeUpdateButton.setOnAction(updateEvent -> {
  	        // Get the update data from text fields and ComboBox
  	        String no = ComboBox.getValue();
  	        String name = nametxt.getText();
  	        String price = pricetxt.getText();
  	        String weight = weighttxt.getText();
  	        String made = ComboBox2.getValue();

  	        // Add data to the list
  	        list.add(no);
  	        list.add(name);
  	        list.add(price);
  	        list.add(weight);
  	        list.add(made);

  	        // Call the update method with the specified data
  	        update(list);

  	        // Clear text fields after update
  	        ComboBox.getSelectionModel().clearSelection();
  	        nametxt.clear();
  	        pricetxt.clear();
  	        weighttxt.clear();
  	        ComboBox2.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the update stage
  	    updateVBox = new VBox(10);
  	    updateVBox.setAlignment(Pos.CENTER);
  	    updateVBox.getChildren().addAll(
  	            new Label("Update Data:"),
  	            new HBox(10, new Label("No:"), ComboBox),
  	            new HBox(10, new Label("Name:"), nametxt),
  	            new HBox(10, new Label("Price:"), pricetxt),
  	            new HBox(10, new Label("Weight:"), weighttxt),
  	            new HBox(10, new Label("Made:"), ComboBox2),
  	            executeUpdateButton
  	    );
  	}
  	if (tablename.equals("orders")) {
  	    TextField datetxt = new TextField();
  	    // Create a ComboBox to display "id" values
  	    primaryComboBox(ComboBox, "orders", "id");
  	    // Create a ComboBox to display "car" values
  	    primaryComboBox(ComboBox2, "orders", "car");
  	    primaryComboBox(ComboBox3, "orders", "customer");

  	    executeUpdateButton.setOnAction(updateEvent -> {
  	        // Get the update data from text fields and ComboBox
  	        String id = ComboBox.getValue();
  	        String date = datetxt.getText();
  	        String customerr = ComboBox3.getValue();
  	        String carr = ComboBox2.getValue();

  	        // Add data to the list
  	        list.add(id);
  	        list.add(date);
  	        list.add(customerr);
  	        list.add(carr);

  	        // Call the update method with the specified data
  	        update(list);

  	        // Clear text fields after update
  	        ComboBox.getSelectionModel().clearSelection();
  	        datetxt.clear();
  	        ComboBox3.getSelectionModel().clearSelection();
  	        ComboBox2.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the update stage
  	    updateVBox = new VBox(10);
  	    updateVBox.setAlignment(Pos.CENTER);
  	    updateVBox.getChildren().addAll(
  	            new Label("Update Data:"),
  	            new HBox(10, new Label("ID:"), ComboBox),
  	            new HBox(10, new Label("Date:"), datetxt),
  	            new HBox(10, new Label("Customer:"), ComboBox3),
  	            new HBox(10, new Label("Car:"), ComboBox2),
  	            executeUpdateButton
  	    );
  	}
  	if (tablename.equals("customer")) {
  	    TextField idtxt = new TextField();
  	    TextField f_nametxt = new TextField();
  	    TextField l_nametxt = new TextField();
  	    TextField addresstxt = new TextField();
  	    TextField jobtxt = new TextField();
  	    // Create a ComboBox to display "id" values
  	    primaryComboBox(ComboBox, "customer", "id");
  	    // Create a ComboBox to display "address" values
  	    primaryComboBox(ComboBox2, "customer", "address");

  	    executeUpdateButton.setOnAction(updateEvent -> {
  	        // Get the update data from text fields and ComboBox
  	        String id = ComboBox.getValue();
  	        String f_name = f_nametxt.getText();
  	        String l_name = l_nametxt.getText();
  	        String addresss = ComboBox2.getValue();
  	        String job = jobtxt.getText();

  	        // Add data to the list
  	        list.add(id);
  	        list.add(f_name);
  	        list.add(l_name);
  	        list.add(addresss);
  	        list.add(job);

  	        // Call the update method with the specified data
  	        update(list);

  	        // Clear text fields after update
  	        ComboBox.getSelectionModel().clearSelection();
  	        f_nametxt.clear();
  	        l_nametxt.clear();
  	        ComboBox2.getSelectionModel().clearSelection();
  	        addresstxt.clear();
  	        jobtxt.clear();
  	    });

  	    // Create a VBox to organize the elements in the update stage
  	    updateVBox = new VBox(10);
  	    updateVBox.setAlignment(Pos.CENTER);
  	    updateVBox.getChildren().addAll(
  	            new Label("Update Data:"),
  	            new HBox(10, new Label("ID:"), ComboBox),
  	            new HBox(10, new Label("First Name:"), f_nametxt),
  	            new HBox(10, new Label("Last Name:"), l_nametxt),
  	            new HBox(10, new Label("Address:"), ComboBox2),
  	            new HBox(10, new Label("Job:"), jobtxt),
  	            executeUpdateButton
  	    );
  	}
  	if (tablename.equals("address")) {
  	    TextField idtxt = new TextField();
  	    TextField buildingtxt = new TextField();
  	    TextField streettxt = new TextField();
  	    TextField citytxt = new TextField();
  	    TextField countrytxt = new TextField();
  	    // Create a ComboBox to display "id" values
  	    primaryComboBox(ComboBox, "address", "id");

  	    executeUpdateButton.setOnAction(updateEvent -> {
  	        // Get the update data from text fields and ComboBox
  	        String id = ComboBox.getValue();
  	        String building = buildingtxt.getText();
  	        String street = streettxt.getText();
  	        String city = citytxt.getText();
  	        String country = countrytxt.getText();

  	        // Add data to the list
  	        list.add(id);
  	        list.add(building);
  	        list.add(street);
  	        list.add(city);
  	        list.add(country);

  	        // Call the update method with the specified data
  	        update(list);

  	        // Clear text fields after update
  	        ComboBox.getSelectionModel().clearSelection();
  	        buildingtxt.clear();
  	        streettxt.clear();
  	        citytxt.clear();
  	        countrytxt.clear();
  	    });

  	    // Create a VBox to organize the elements in the update stage
  	    updateVBox = new VBox(10);
  	    updateVBox.setAlignment(Pos.CENTER);
  	    updateVBox.getChildren().addAll(
  	            new Label("Update Data:"),
  	            new HBox(10, new Label("ID:"), ComboBox),
  	            new HBox(10, new Label("Building:"), buildingtxt),
  	            new HBox(10, new Label("Street:"), streettxt),
  	            new HBox(10, new Label("City:"), citytxt),
  	            new HBox(10, new Label("Country:"), countrytxt),
  	            executeUpdateButton
  	    );
  	}

  	    // Set up the update stage
  	    Scene updateScene = new Scene(updateVBox, 400, 300);
  	    updateStage.setScene(updateScene);
  	    updateStage.show();
  	});


  	  
 // Attach an event handler to the delete button
  	delete.setOnAction(event -> {
  	    // Create a new stage for delete
  	    Stage deleteStage = new Stage();
  	    deleteStage.setTitle("Delete " + tablename);

  	    // Create a button for executing the delete
  	    Button executeDeleteButton = new Button("Delete");
  	    VBox deleteVBox = new VBox(10);

  	    ArrayList<String> list = new ArrayList<>();
  	    ComboBox<String> ComboBox = new ComboBox<>();

  	    if (tablename.equals("car")) {
  	        // Create a ComboBox to display "name" values
  	        primaryComboBox(ComboBox, "car", "name");

  	        executeDeleteButton.setOnAction(deleteEvent -> {
  	            // Get the delete data from ComboBox
  	            String name = ComboBox.getValue();

  	            // Add data to the list
  	            list.add(name);

  	            // Call the delete method with the specified data
  	            delete(list);

  	            // Clear ComboBox after delete
  	            ComboBox.getSelectionModel().clearSelection();
  	        });

  	        // Create a VBox to organize the elements in the delete stage
  	        deleteVBox = new VBox(10);
  	        deleteVBox.setAlignment(Pos.CENTER);
  	        deleteVBox.getChildren().addAll(
  	                new Label("Delete Data:"),
  	                new HBox(10, new Label("Name:"), ComboBox),
  	                executeDeleteButton
  	        );
  	    }
  	  if (tablename.equals("manufacture")) {
  	    // Create a ComboBox to display "name" values
  	    primaryComboBox(ComboBox, "manufacture", "name");

  	    executeDeleteButton.setOnAction(deleteEvent -> {
  	        // Get the delete data from ComboBox
  	        String name = ComboBox.getValue();

  	        // Add data to the list
  	        list.add(name);

  	        // Call the delete method with the specified data
  	        delete(list);

  	        // Clear ComboBox after delete
  	        ComboBox.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the delete stage
  	    deleteVBox = new VBox(10);
  	    deleteVBox.setAlignment(Pos.CENTER);
  	    deleteVBox.getChildren().addAll(
  	            new Label("Delete Data:"),
  	            new HBox(10, new Label("Name:"), ComboBox),
  	            executeDeleteButton
  	    );
  	}
  	if (tablename.equals("orders")) {
  	    // Create a ComboBox to display "id" values
  	    primaryComboBox(ComboBox, "orders", "id");

  	    executeDeleteButton.setOnAction(deleteEvent -> {
  	        // Get the delete data from ComboBox
  	        String id = ComboBox.getValue();

  	        // Add data to the list
  	        list.add(id);

  	        // Call the delete method with the specified data
  	        delete(list);

  	        // Clear ComboBox after delete
  	        ComboBox.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the delete stage
  	    deleteVBox = new VBox(10);
  	    deleteVBox.setAlignment(Pos.CENTER);
  	    deleteVBox.getChildren().addAll(
  	            new Label("Delete Data:"),
  	            new HBox(10, new Label("ID:"), ComboBox),
  	            executeDeleteButton
  	    );
  	}
  	if (tablename.equals("device")) {
  	    // Create a ComboBox to display "no" values
  	    primaryComboBox(ComboBox, "device", "no");

  	    executeDeleteButton.setOnAction(deleteEvent -> {
  	        // Get the delete data from ComboBox
  	        String no = ComboBox.getValue();

  	        // Add data to the list
  	        list.add(no);

  	        // Call the delete method with the specified data
  	        delete(list);

  	        // Clear ComboBox after delete
  	        ComboBox.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the delete stage
  	    deleteVBox = new VBox(10);
  	    deleteVBox.setAlignment(Pos.CENTER);
  	    deleteVBox.getChildren().addAll(
  	            new Label("Delete Data:"),
  	            new HBox(10, new Label("No:"), ComboBox),
  	            executeDeleteButton
  	    );
  	}
  	if (tablename.equals("customer")) {
  	    // Create a ComboBox to display "id" values
  	    primaryComboBox(ComboBox, "customer", "id");

  	    executeDeleteButton.setOnAction(deleteEvent -> {
  	        // Get the delete data from ComboBox
  	        String id = ComboBox.getValue();

  	        // Add data to the list
  	        list.add(id);

  	        // Call the delete method with the specified data
  	        delete(list);

  	        // Clear ComboBox after delete
  	        ComboBox.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the delete stage
  	    deleteVBox = new VBox(10);
  	    deleteVBox.setAlignment(Pos.CENTER);
  	    deleteVBox.getChildren().addAll(
  	            new Label("Delete Data:"),
  	            new HBox(10, new Label("ID:"), ComboBox),
  	            executeDeleteButton
  	    );
  	}
  	if (tablename.equals("address")) {
  	    // Create a ComboBox to display "id" values
  	    primaryComboBox(ComboBox, "address", "id");

  	    executeDeleteButton.setOnAction(deleteEvent -> {
  	        // Get the delete data from ComboBox
  	        String id = ComboBox.getValue();

  	        // Add data to the list
  	        list.add(id);

  	        // Call the delete method with the specified data
  	        delete(list);

  	        // Clear ComboBox after delete
  	        ComboBox.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the delete stage
  	    deleteVBox = new VBox(10);
  	    deleteVBox.setAlignment(Pos.CENTER);
  	    deleteVBox.getChildren().addAll(
  	            new Label("Delete Data:"),
  	            new HBox(10, new Label("ID:"), ComboBox),
  	            executeDeleteButton
  	    );
  	}
  	if (tablename.equals("car_part")) {
  	    // Create a ComboBox to display "car" values
  	    primaryComboBox(ComboBox, "car_part", "car");

  	    executeDeleteButton.setOnAction(deleteEvent -> {
  	        // Get the delete data from ComboBox
  	        String carr = ComboBox.getValue();

  	        // Add data to the list
  	        list.add(carr);

  	        // Call the delete method with the specified data
  	        delete(list);

  	        // Clear ComboBox after delete
  	        ComboBox.getSelectionModel().clearSelection();
  	    });

  	    // Create a VBox to organize the elements in the delete stage
  	    deleteVBox = new VBox(10);
  	    deleteVBox.setAlignment(Pos.CENTER);
  	    deleteVBox.getChildren().addAll(
  	            new Label("Delete Data:"),
  	            new HBox(10, new Label("Car:"), ComboBox),
  	            executeDeleteButton
  	    );
  	}


  	    // Set up the delete stage
  	    Scene deleteScene = new Scene(deleteVBox, 300, 200);
  	    deleteStage.setScene(deleteScene);
  	    deleteStage.show();
  	});



        
        Scene scene = new Scene(borderPane, 1000, 700);

        primaryStage.setScene(scene);

        // Show the stage
        primaryStage.show();
    }


}
