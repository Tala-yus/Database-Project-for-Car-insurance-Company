package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Main extends Application {
	// private TableView<Company> companyTable = new TableView<Company>();
	public ArrayList<Company> companyList = new ArrayList<>();
	// private TableView<Address> addressTable = new TableView<Address>();
	public ArrayList<Address> addressList = new ArrayList<>();
//	private TableView<AccidentReport> accidentTable = new TableView<AccidentReport>();
	public ArrayList<AccidentReport> accidentList = new ArrayList<>();

	public static Connection getConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/DB04";
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "Qaz-12345");
		props.setProperty("ssl", "true");
		Connection conn = DriverManager.getConnection(url, props);
		return conn;
	}

	static public Alert alert(String alert) {
		Alert a = new Alert(Alert.AlertType.INFORMATION);
		a.setContentText(alert);
		return a;
	}

	@Override
	public void start(Stage primaryStage) {
		try {
			// back end

			// front end
			// screen size
			Rectangle2D screenSize = Screen.getPrimary().getVisualBounds();
			// main page
			BorderPane root = new BorderPane();
			root.setStyle("-fx-background-color:black");

			VBox vb = new VBox(60);
			Button comp_button = new Button("Company");
			comp_button.setPrefSize(250, 50);
			comp_button.setFont(Font.font("Verdana", 20));
			comp_button.setStyle("-fx-background-color:#D8BFD8;");
			Button address_button = new Button("Address");
			address_button.setPrefSize(250, 50);
			address_button.setFont(Font.font("Verdana", 20));
			address_button.setStyle("-fx-background-color:#E6E6FA;");
			Button accidentReport_button = new Button("Accident Report");
			accidentReport_button.setPrefSize(250, 50);
			accidentReport_button.setFont(Font.font("Verdana", 20));
			accidentReport_button.setStyle("-fx-background-color:#DDA0DD;");

			vb.getChildren().addAll(comp_button, address_button, accidentReport_button);
			vb.setAlignment(Pos.CENTER);
			root.setCenter(vb);
			root.setPadding(new Insets(0, 0, 0, 20));

			// Company Scene
			BorderPane company = new BorderPane();
			company.setStyle("-fx-background-color:black");

			HBox compHB = new HBox(20);

			VBox compVB1 = new VBox(50);
			VBox compVB2 = new VBox(10);

			HBox compHB1 = new HBox(20);
			Button find_button = new Button("Find");
			find_button.setAlignment(Pos.CENTER);
			find_button.setPrefSize(100, 50);
			find_button.setFont(Font.font("Verdana", 20));
			find_button.setStyle("-fx-background-color:White;");
			compHB1.getChildren().addAll(compVB1, compVB2, find_button);

			VBox compVB3 = new VBox(70);
			compVB3.setPadding(new Insets(100, 0, 0, 100));

			HBox compHB2 = new HBox(10);
			HBox compHB3 = new HBox();

			Button insert_button = new Button("Insert");
			insert_button.setPrefSize(150, 50);
			insert_button.setFont(Font.font("Verdana", 20));
			insert_button.setStyle("-fx-background-color:White;");
			Button remove_button = new Button("Remove");
			remove_button.setPrefSize(150, 50);
			remove_button.setFont(Font.font("Verdana", 20));
			remove_button.setStyle("-fx-background-color:White;");
			Button update_button = new Button("Update");
			update_button.setPrefSize(150, 50);
			update_button.setFont(Font.font("Verdana", 20));
			update_button.setStyle("-fx-background-color:White;");

			compHB2.getChildren().addAll(insert_button, remove_button, update_button);

			Label company_info = new Label("Company:");
			company_info.setFont(new Font("Fleftex", 30));
			company_info.setTextFill(Color.WHITE);
			company_info.setStyle("-fx-font-weight:bold;");

			compHB.getChildren().addAll(compVB3, compHB3);
			compVB3.getChildren().addAll(company_info, compHB1, compHB2);
			compVB3.setPadding(new Insets(100, 0, 0, 20));

			Label company_name = new Label("Company Name: ");
			company_name.setFont(new Font("Fleftex", 20));
			company_name.setTextFill(Color.WHITE);
			Label company_address = new Label("Address Id: ");
			company_address.setFont(new Font("Fleftex", 20));
			company_address.setTextFill(Color.WHITE);
			Label company_email = new Label("Email: ");
			company_email.setFont(new Font("Fleftex", 20));
			company_email.setTextFill(Color.WHITE);

			compVB1.getChildren().addAll(company_name, company_address, company_email);
			compVB1.setAlignment(Pos.CENTER);

			TextField company_name_tf = new TextField();
			company_name_tf.setPromptText("Company Name");
			company_name_tf.setFont(new Font("Fleftex", 15));
			company_name_tf.setPrefSize(300, 50);
			TextField company_address_tf = new TextField();
			company_address_tf.setPromptText("Address Id");
			company_address_tf.setFont(new Font("Fleftex", 15));
			company_address_tf.setPrefSize(300, 50);
			TextField company_email_tf = new TextField();
			company_email_tf.setPromptText("Email");
			company_email_tf.setFont(new Font("Fleftex", 15));
			company_email_tf.setPrefSize(300, 50);

			compVB2.getChildren().addAll(company_name_tf, company_address_tf, company_email_tf);

			company.setLeft(compVB3);
			insert_button.setOnAction(b -> {
				try {
					Statement statement = getConnection().createStatement();
					if (company_name_tf.getText() != null && company_address_tf.getText() != null
							&& company_email_tf.getText() != null) {
						if (company_email_tf.getText().indexOf("@") > 0) {
							statement.executeUpdate("INSERT INTO company VALUES (" + "'" + company_name_tf.getText()
									+ "'" + "," + Integer.parseInt(company_address_tf.getText()) + "," + "'"
									+ company_email_tf.getText() + "'" + ");");

							companyList.add(new Company(company_name_tf.getText(),
									Integer.valueOf(company_address_tf.getText()), company_email_tf.getText()));
							alert("the company with adress id =" + Integer.valueOf(company_address_tf.getText())
									+ " and comapny name = " + company_name_tf.getText() + " is added").show();

						} else
							alert("invalid email format").show();
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			});

			//
			VBox rightVb = new VBox(500);

			Button home1 = new Button();
			home1.setPrefSize(10, 10);
			Image img = new Image("File:icons8-home.gif");
			ImageView imgV = new ImageView(img);
			imgV.setPickOnBounds(true);
			imgV.setPreserveRatio(true);
			imgV.setFitHeight(60);
			imgV.setFitWidth(60);
			home1.setGraphic(imgV);

			Button sym1 = new Button();
			sym1.setPrefSize(10, 10);
			Image sym1img = new Image("File:icons8-car-insurance-48.png");
			ImageView sym1imgV = new ImageView(sym1img);
			sym1imgV.setPickOnBounds(true);
			sym1imgV.setPreserveRatio(true);
			sym1imgV.setFitHeight(50);
			sym1imgV.setFitWidth(50);
			sym1.setGraphic(sym1imgV);

			rightVb.getChildren().addAll(sym1, home1);
			rightVb.setPadding(new Insets(10, 0, 0, 0));
			company.setRight(rightVb);

			// Company View Table
//			companyTable.setEditable(true);
//
//			VBox tableVb = new VBox();
//
//			TableColumn compNameCol = new TableColumn("Company Name");
//			compNameCol.setMinWidth(100);
//			compNameCol.setCellValueFactory(new PropertyValueFactory<Company, String>("Company Name"));
//
//			TableColumn addressIdCol = new TableColumn("Address ID");
//			addressIdCol.setMinWidth(100);
//			addressIdCol.setCellValueFactory(new PropertyValueFactory<Company, String>("Address ID"));
//
//			TableColumn emailCol = new TableColumn("Email");
//			emailCol.setMinWidth(200);
//			emailCol.setCellValueFactory(new PropertyValueFactory<Company, String>("email"));
//
//			// companyTable.setItems(data);
//			companyTable.getColumns().addAll(compNameCol, addressIdCol, emailCol);
//
//			tableVb.getChildren().addAll(companyTable);
//			tableVb.setAlignment(Pos.CENTER);
//			company.setCenter(tableVb);

			// Address Scene
			BorderPane address = new BorderPane();
			address.setStyle("-fx-background-color:black");

			HBox addressHB = new HBox(20);

			VBox addressVB1 = new VBox(50);
			VBox addressVB2 = new VBox(25);

			HBox addressHB1 = new HBox(20);
			Button find2_button = new Button("Find");
			find2_button.setAlignment(Pos.CENTER);
			find2_button.setPrefSize(100, 50);
			find2_button.setFont(Font.font("Verdana", 20));
			find2_button.setStyle("-fx-background-color:White;");
			addressHB1.getChildren().addAll(addressVB1, addressVB2, find2_button);

			VBox addressVB3 = new VBox(70);
			addressVB3.setPadding(new Insets(100, 0, 0, 100));

			HBox addressHB2 = new HBox(10);
			HBox addressHB3 = new HBox();

			Button insert2_button = new Button("Insert");
			insert2_button.setPrefSize(150, 50);
			insert2_button.setFont(Font.font("Verdana", 20));
			insert2_button.setStyle("-fx-background-color:White;");
			Button remove2_button = new Button("Remove");
			remove2_button.setPrefSize(150, 50);
			remove2_button.setFont(Font.font("Verdana", 20));
			remove2_button.setStyle("-fx-background-color:White;");
			Button update2_button = new Button("Update");
			update2_button.setPrefSize(150, 50);
			update2_button.setFont(Font.font("Verdana", 20));
			update2_button.setStyle("-fx-background-color:White;");

			addressHB2.getChildren().addAll(insert2_button, remove2_button, update2_button);

			Label address_info = new Label("Company:");
			address_info.setFont(new Font("Fleftex", 30));
			address_info.setTextFill(Color.WHITE);
			address_info.setStyle("-fx-font-weight:bold;");

			addressHB.getChildren().addAll(addressVB3, addressHB3);
			addressVB3.getChildren().addAll(address_info, addressHB1, addressHB2);
			addressVB3.setPadding(new Insets(70, 0, 0, 20));

			Label address_id = new Label("Address Id: ");
			address_id.setFont(new Font("Fleftex", 20));
			address_id.setTextFill(Color.WHITE);
			Label address_city = new Label("City: ");
			address_city.setFont(new Font("Fleftex", 20));
			address_city.setTextFill(Color.WHITE);
			Label address_street = new Label("Street: ");
			address_street.setFont(new Font("Fleftex", 20));
			address_street.setTextFill(Color.WHITE);
			Label address_zip = new Label("Zip: ");
			address_zip.setFont(new Font("Fleftex", 20));
			address_zip.setTextFill(Color.WHITE);

			addressVB1.getChildren().addAll(address_id, address_city, address_street, address_zip);
			addressVB1.setAlignment(Pos.CENTER);

			TextField address_id_tf = new TextField();
			address_id_tf.setPromptText("Address ID");
			address_id_tf.setFont(new Font("Fleftex", 15));
			address_id_tf.setPrefSize(300, 50);
			TextField city_tf = new TextField();
			city_tf.setPromptText("City");
			city_tf.setFont(new Font("Fleftex", 15));
			city_tf.setPrefSize(300, 50);
			TextField street_tf = new TextField();
			street_tf.setPromptText("Street");
			street_tf.setFont(new Font("Fleftex", 15));
			street_tf.setPrefSize(300, 50);
			TextField zip_tf = new TextField();
			zip_tf.setPromptText("Zip");
			zip_tf.setFont(new Font("Fleftex", 15));
			zip_tf.setPrefSize(300, 50);

			addressVB2.getChildren().addAll(address_id_tf, city_tf, street_tf, zip_tf);

			address.setLeft(addressVB3);

			//
			VBox rightVb2 = new VBox(500);

			Button home2 = new Button();
			home2.setPrefSize(10, 10);
			Image img2 = new Image("File:icons8-home.gif");
			ImageView imgV2 = new ImageView(img2);
			imgV2.setPickOnBounds(true);
			imgV2.setPreserveRatio(true);
			imgV2.setFitHeight(60);
			imgV2.setFitWidth(60);
			home2.setGraphic(imgV2);

			Button sym2 = new Button();
			sym2.setPrefSize(10, 10);
			Image sym2img = new Image("File:icons8-car-insurance-48.png");
			ImageView sym2imgV = new ImageView(sym2img);
			sym2imgV.setPickOnBounds(true);
			sym2imgV.setPreserveRatio(true);
			sym2imgV.setFitHeight(50);
			sym2imgV.setFitWidth(50);
			sym2.setGraphic(sym2imgV);

			rightVb2.getChildren().addAll(sym2, home2);
			rightVb2.setPadding(new Insets(10, 0, 0, 0));
			address.setRight(rightVb2);

			// address table

			VBox table2Vb = new VBox();

//			TableColumn addressId2Col = new TableColumn("Address ID");
//			addressId2Col.setMinWidth(100);
//			addressId2Col.setCellValueFactory(new PropertyValueFactory<Address, String>("Address ID"));
//
//			TableColumn cityCol = new TableColumn("City");
//			cityCol.setMinWidth(100);
//			cityCol.setCellValueFactory(new PropertyValueFactory<Address, String>("City"));
//
//			TableColumn streetCol = new TableColumn("Street");
//			streetCol.setMinWidth(200);
//			streetCol.setCellValueFactory(new PropertyValueFactory<Address, String>("Street"));
//
//			TableColumn zipCol = new TableColumn("Zip");
//
//			zipCol.setMinWidth(200);
//			zipCol.setCellValueFactory(new PropertyValueFactory<Address, String>("zip"));
//
//			// companyTable.setItems(data);
//			addressTable.getColumns().addAll(addressId2Col, cityCol, streetCol, zipCol);
//
//			table2Vb.getChildren().addAll(addressTable);
//			table2Vb.setAlignment(Pos.CENTER);
//			address.setCenter(table2Vb);

			// Accident Report Scene
			BorderPane accidentReport = new BorderPane();
			accidentReport.setStyle("-fx-background-color:black");

			HBox accidentHB = new HBox(20);

			VBox accidentVB1 = new VBox(50);
			VBox accidentVB2 = new VBox(25);

			HBox accidentHB1 = new HBox(20);
			Button find3_button = new Button("Find");
			find3_button.setAlignment(Pos.CENTER);
			find3_button.setPrefSize(100, 50);
			find3_button.setFont(Font.font("Verdana", 20));
			find3_button.setStyle("-fx-background-color:White;");
			accidentHB1.getChildren().addAll(accidentVB1, accidentVB2, find3_button);

			VBox accidentVB3 = new VBox(70);
			accidentVB3.setPadding(new Insets(100, 0, 0, 20));

			HBox accidentHB2 = new HBox(10);
			HBox accidentHB3 = new HBox();

			Button insert3_button = new Button("Insert");
			insert3_button.setPrefSize(150, 50);
			insert3_button.setFont(Font.font("Verdana", 20));
			insert3_button.setStyle("-fx-background-color:White;");
			Button remove3_button = new Button("Remove");
			remove3_button.setPrefSize(150, 50);
			remove3_button.setFont(Font.font("Verdana", 20));
			remove3_button.setStyle("-fx-background-color:White;");
			Button update3_button = new Button("Update");
			update3_button.setPrefSize(150, 50);
			update3_button.setFont(Font.font("Verdana", 20));
			update3_button.setStyle("-fx-background-color:White;");

			accidentHB2.getChildren().addAll(insert3_button, remove3_button, update3_button);

			Label accident_info = new Label("Accident Report: ");
			accident_info.setFont(new Font("Fleftex", 30));
			accident_info.setTextFill(Color.WHITE);
			accident_info.setStyle("-fx-font-weight:bold;");

			accidentHB.getChildren().addAll(accidentVB3, accidentHB3);
			accidentVB3.getChildren().addAll(accident_info, accidentHB1, accidentHB2);
			accidentVB3.setPadding(new Insets(20, 0, 0, 20));

			Label accident_id = new Label("Accident ID: ");
			accident_id.setFont(new Font("Fleftex", 20));
			accident_id.setTextFill(Color.WHITE);
			Label vehicle_id = new Label("Vehicle ID ");
			vehicle_id.setFont(new Font("Fleftex", 20));
			vehicle_id.setTextFill(Color.WHITE);
			Label description = new Label("Description: ");
			description.setFont(new Font("Fleftex", 20));
			description.setTextFill(Color.WHITE);
			Label location = new Label("Location: ");
			location.setFont(new Font("Fleftex", 20));
			location.setTextFill(Color.WHITE);
			Label date = new Label("Date: ");
			date.setFont(new Font("Fleftex", 20));
			date.setTextFill(Color.WHITE);

			accidentVB1.getChildren().addAll(accident_id, vehicle_id, description, location, date);
			accidentVB1.setAlignment(Pos.CENTER);

			TextField accident_id_tf = new TextField();
			accident_id_tf.setPromptText("Accident ID");
			accident_id_tf.setFont(new Font("Fleftex", 15));
			accident_id_tf.setPrefSize(300, 50);
			TextField vehicle_id_tf = new TextField();
			vehicle_id_tf.setPromptText("Vehicle ID");
			vehicle_id_tf.setFont(new Font("Fleftex", 15));
			vehicle_id_tf.setPrefSize(300, 50);
			TextField description_tf = new TextField();
			description_tf.setPromptText("Description");
			description_tf.setFont(new Font("Fleftex", 15));
			description_tf.setPrefSize(300, 50);
			TextField location_tf = new TextField();
			location_tf.setPromptText("Location");
			location_tf.setFont(new Font("Fleftex", 15));
			location_tf.setPrefSize(300, 50);
			TextField date_tf = new TextField();
			date_tf.setPromptText("Location");
			date_tf.setFont(new Font("Fleftex", 15));
			date_tf.setPrefSize(300, 50);

			accidentVB2.getChildren().addAll(accident_id_tf, vehicle_id_tf, description_tf, location_tf, date_tf);

			accidentReport.setLeft(accidentVB3);

			//
			VBox rightVb3 = new VBox(500);

			Button home3 = new Button();
			home3.setPrefSize(10, 10);
			Image img3 = new Image("File:icons8-home.gif");
			ImageView imgV3 = new ImageView(img3);
			imgV3.setPickOnBounds(true);
			imgV3.setPreserveRatio(true);
			imgV3.setFitHeight(60);
			imgV3.setFitWidth(60);
			home3.setGraphic(imgV3);

			Button sym3 = new Button();
			sym3.setPrefSize(10, 10);
			Image sym3img = new Image("File:icons8-car-insurance-48.png");
			ImageView sym3imgV = new ImageView(sym3img);
			sym3imgV.setPickOnBounds(true);
			sym3imgV.setPreserveRatio(true);
			sym3imgV.setFitHeight(50);
			sym3imgV.setFitWidth(50);
			sym3.setGraphic(sym3imgV);

			rightVb3.getChildren().addAll(sym3, home3);
			rightVb3.setPadding(new Insets(10, 0, 0, 0));
			accidentReport.setRight(rightVb3);

			// accident report table

			VBox table3Vb = new VBox();

//			TableColumn accidentId2Col = new TableColumn("Accident ID");
//			accidentId2Col.setMinWidth(100);
//			accidentId2Col.setCellValueFactory(new PropertyValueFactory<AccidentReport, String>("Address ID"));
//
//			TableColumn vehicleIdCol = new TableColumn("Vehicle ID");
//			vehicleIdCol.setMinWidth(200);
//			vehicleIdCol.setCellValueFactory(new PropertyValueFactory<AccidentReport, String>("Vehicle ID"));
//
//			TableColumn descriptionCol = new TableColumn("Description");
//			descriptionCol.setMinWidth(200);
//			descriptionCol.setCellValueFactory(new PropertyValueFactory<AccidentReport, String>("description"));
//
//			TableColumn locationCol = new TableColumn("Location");
//			locationCol.setMinWidth(100);
//			locationCol.setCellValueFactory(new PropertyValueFactory<AccidentReport, String>("Location"));
//
//			TableColumn dateCol = new TableColumn("Date");
//			dateCol.setMinWidth(200);
//			dateCol.setCellValueFactory(new PropertyValueFactory<AccidentReport, String>("Date"));
//
//			// companyTable.setItems(data);
//			addressTable.getColumns().addAll(addressId2Col, vehicleIdCol, descriptionCol, locationCol, dateCol);
//
//			table3Vb.getChildren().addAll(accidentTable);
//			table3Vb.setAlignment(Pos.CENTER);
//			accidentReport.setCenter(table3Vb);

			// all scenes' identifiers
			Scene scene = new Scene(root, screenSize.getWidth(), screenSize.getHeight());
			Scene company_scene = new Scene(company, screenSize.getWidth(), screenSize.getHeight());
			Scene address_scene = new Scene(address, screenSize.getWidth(), screenSize.getHeight());
			Scene accident_report_scene = new Scene(accidentReport, screenSize.getWidth(), screenSize.getHeight());
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			comp_button.setOnAction(e -> primaryStage.setScene(company_scene));
			address_button.setOnAction(e -> primaryStage.setScene(address_scene));
			accidentReport_button.setOnAction(e -> primaryStage.setScene(accident_report_scene));

			home1.setOnAction(e -> primaryStage.setScene(scene));
			home2.setOnAction(e -> primaryStage.setScene(scene));
			home3.setOnAction(e -> primaryStage.setScene(scene));

			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setMaximized(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
