package be.kdg.java2;

import be.kdg.java2.database.LaptopDao;
import be.kdg.java2.database.LaptopDbDao;
import be.kdg.java2.service.LaptopsService;
import be.kdg.java2.service.LaptopsServiceImpl;
import be.kdg.java2.view.LaptopsPresenter;
import be.kdg.java2.view.LaptopsView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.Logger;

public class Demo_13 extends Application   {

    private static final Logger logger = Logger.getLogger(Demo_13.class.getName());

    public static void main(String[] args) {
        logger.info("Starting laptops Management System on thread: " + Thread.currentThread().getName());
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) {
        logger.info("Running start methode on thread: " + Thread.currentThread().getName());
        LaptopDbDao laptopDbDao = LaptopDbDao.getInstance("db/laptops");
        LaptopsView laptopsView = new LaptopsView();
        LaptopsService laptopsService = new LaptopsServiceImpl(laptopDbDao);
        new LaptopsPresenter(laptopsView, laptopsService);


        primaryStage.setScene(new Scene(laptopsView));
        primaryStage.show();
    }
}
