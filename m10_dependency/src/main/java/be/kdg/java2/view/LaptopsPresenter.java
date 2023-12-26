package be.kdg.java2.view;

import be.kdg.java2.exceptios.LaptopException;
import be.kdg.java2.model.Laptop;
import be.kdg.java2.service.LaptopsService;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import java.util.logging.Logger;

public class LaptopsPresenter {

    private static final Logger logger = Logger.getLogger(LaptopsPresenter.class.getName());

    private final LaptopsView laptopsView;

    private final LaptopsService laptopsService;
    public LaptopsPresenter(LaptopsView laptopsView, LaptopsService laptopsService) {
        this.laptopsView = laptopsView;
        this.laptopsService = laptopsService;
        loadLaptops();
        addEventHandlers();
    }

    private void addEventHandlers() {
        laptopsView.getBtnSave().setOnAction(event -> {
            try {
                Laptop laptop = new Laptop(
                        laptopsView.getTfName().getText(),
                        Double.parseDouble(laptopsView.getTfPrice().getText()),
                        laptopsView.getDpReleaseDate().getValue());
                laptopsService.addLaptop(laptop);
                clearInputFields();
            } catch (LaptopException | NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK);
                alert.showAndWait();
            }
            loadLaptops();
        });
    }

    private void clearInputFields() {
        laptopsView.getTfName().clear();
        laptopsView.getTfPrice().clear();
        laptopsView.getDpReleaseDate().setValue(null);
    }

    private void loadLaptops() {
        try {
            laptopsView.getTvLaptops().setItems(FXCollections.observableList(laptopsService.getAllLaptops()));
        } catch (LaptopException | IllegalArgumentException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }
}
