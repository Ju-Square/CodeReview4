// Julia Hinterecker
package com.company;

import com.company.Products;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

public class ProductListView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Set action prices");
        // Data to work with
        ObservableList<Products> items =
                FXCollections.observableArrayList(
                        new Products(
                                "Pfeffer",
                                "1 Stück",
                                3.49,
                                2.79,
                                "/img/pfeffer__600x600.jpg",
                                "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird."),
                        new Products(
                                "Schafmilchkäse",
                                "200 Gramm Packung",
                                2.59,
                                1.99,
                                "/img/cheese_salakis__600x600.jpg",
                                "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt."),
                        new Products(
                                "Vöslauer",
                                "1.5 Liter Flasche",
                                0.75,
                                0.49,
                                "/img/voslauer__600x600.jpg",
                                "Spritziges Vöslauer Mineralwasser"),
                        new Products(
                                "Zucker",
                                "500 Gramm Paket",
                                1.39,
                                0.89,
                                "/img/zucker__600x600.jpg",
                                "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde."
                        )
                );

                ListView<Products> list = new ListView<Products>();
                list.setItems(items);

                // Labels to work with
        Label labelName = new Label("Prod. Name");
        Label labelSize = new Label("Quantity");
        Label labelOldPrice = new Label("Old Price");
        Label labelNewPrice = new Label("New Price");
        Label labelDescriptionTitle = new Label("Description");
        labelDescriptionTitle.setFont(Font.font("", FontWeight.BOLD,20));

        Label labelDescription = new Label();
        labelDescription.setPrefWidth(300);
        labelDescription.setWrapText(true);

        Label euro1 = new Label("EUR");
        Label euro2 = new Label("EUR");

        // TextFields for Display
        TextField textName = new TextField();
        textName.setEditable(false);
        TextField textSize = new TextField();
        textSize.setEditable(false);
        // TextFields that are possbile to insert new Data
        TextField textOldPrice = new TextField();
        TextField textNewPrice = new TextField();
        // Image Display
        ImageView image1 = new ImageView();
        image1.setFitWidth(200);
        image1.setFitHeight(200);

        // Buttons
        Button buttonUpdate = new Button("Update");
        Button buttonReport = new Button("Report");

        // Layout
        HBox hBoxProductName = new HBox(10, labelName, textName);
        HBox hBoxSize = new HBox(27, labelSize, textSize);
        HBox hBoxOldPrice = new HBox(25, labelOldPrice, textOldPrice);
        HBox hBoxNewPrice = new HBox(20, labelNewPrice, textNewPrice);
        HBox hBoxImage = new HBox(image1);
        HBox hBoxDescriptionTitle = new HBox(10, labelDescriptionTitle);
        HBox hBoxButtons = new HBox (buttonUpdate,buttonReport);
        hBoxButtons.setSpacing(10);

        VBox vBoxFields = new VBox(hBoxProductName, hBoxSize, hBoxOldPrice, hBoxNewPrice, hBoxImage, hBoxDescriptionTitle, labelDescription , hBoxButtons);
        vBoxFields.setSpacing(20);
        vBoxFields.setPrefWidth(350);

        HBox hBoxMain = new HBox(vBoxFields, list);

        // View products

        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Products>() {

            public void changed(ObservableValue<? extends Products> ov, Products old_value, Products new_value) {
                textName.setText(new_value.getProductName());
                textSize.setText(new_value.getProductSize());
                textOldPrice.setText(Double.toString(new_value.getProductOldPrice()));
                textNewPrice.setText(Double.toString(new_value.getProductActionPrice()));
                String productImage = new_value.getImage();
                InputStream input = this.getClass().getResourceAsStream(productImage);
                Image productImg = new Image(input);
                image1.setImage(productImg);

                labelDescription.setText(new_value.getProductDescription());
            }
        });

        // Update Button

        buttonUpdate.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        int selectedIndex = list.getSelectionModel().getSelectedIndex();
                        if (selectedIndex != .1){
                            double newProductOldPrice = Double.valueOf(textOldPrice.getText());
                            double newProductActionPrice = Double.valueOf(textNewPrice.getText());

                            list.getItems().get(selectedIndex).setProductOldPrice(newProductOldPrice);
                            list.getItems().get(selectedIndex).setProductActionPrice(newProductActionPrice);
                            list.refresh();
                        }
                    }
                }
        );

        // Save report

        buttonReport.setOnAction(actionEvent -> {
            try {
                File file = new File("report.txt");
                if(!file.exists()) {
                    file.createNewFile();
                }
                PrintWriter printwriter1 = new PrintWriter(file);
                for(int i = 0; i < items.size(); i++){
                    printwriter1.println(items.get(i).getProductName());
                    printwriter1.println(items.get(i).getProductSize());
                    printwriter1.println(items.get(i).getProductDescription());
                    printwriter1.println("Instead: " + items.get(i).getProductOldPrice());
                    printwriter1.println("Action price: " +items.get(i).getProductActionPrice());
                    printwriter1.println();
                }
                printwriter1.close();
                System.out.println("Report created");
            }
            catch (IOException exception){
                exception.printStackTrace();
            }

        });
        Scene scene = new Scene (hBoxMain);
        primaryStage.setScene(scene);
        primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }
}
