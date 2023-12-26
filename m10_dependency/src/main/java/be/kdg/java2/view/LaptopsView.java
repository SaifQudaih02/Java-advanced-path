package be.kdg.java2.view;
import be.kdg.java2.model.Laptop;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.logging.Logger;

public class LaptopsView extends BorderPane {
    private static final Logger logger = Logger.getLogger(LaptopsView.class.getName());
    private TableView<Laptop> tableViewLaptops;

    private TextField tfPrice;
    private TextField textFieldName;
    private DatePicker dpReleaseDate;
    private Button btnSave;

    @SuppressWarnings("unchecked")
    public  LaptopsView() {
        tableViewLaptops = new TableView<>();
        TableColumn<Laptop, String> column1 = new TableColumn<>("Name");
        column1.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Laptop, Double> column2 = new TableColumn<>("Price");
        column2.setCellValueFactory(new PropertyValueFactory<>("price"));
        TableColumn<Laptop, LocalDate> column3 = new TableColumn<>("Release Date");
        column3.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));

        tableViewLaptops.getColumns().addAll(column1, column2, column3);

        setCenter(tableViewLaptops);
        BorderPane.setMargin(tableViewLaptops, new javafx.geometry.Insets(10));

        textFieldName = new TextField();
        textFieldName.setPromptText("Name");
        tfPrice = new TextField();
        tfPrice.setPromptText("Price");
        dpReleaseDate = new DatePicker();
        dpReleaseDate.setPromptText("Release Date");
        btnSave = new Button("Save");

        HBox hbox = new HBox(textFieldName, tfPrice, dpReleaseDate, btnSave);
        hbox.setSpacing(5);
        BorderPane.setMargin(hbox, new javafx.geometry.Insets(10));

        this.setBottom(hbox);
    }

    TableView getTvLaptops() {
        return tableViewLaptops;
    }

     TextField getTfName() {
        return textFieldName;
    }

     TextField getTfPrice() {
        return tfPrice;
    }

     DatePicker getDpReleaseDate() {
        return dpReleaseDate;
    }

    Button getBtnSave() {
        return btnSave;
    }
}
