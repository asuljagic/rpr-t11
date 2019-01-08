package ba.unsa.etf.rpr;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Main extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static String ispisiGradove() {
        ArrayList<Grad> gradovi = GeografijaDAO.getGeografija().gradovi();
        String rez = "";
        for (Grad grad : gradovi) {
            rez += grad.getNaziv() + " (" + grad.getDrzava().getNaziv() + ")" + " - " +
                    grad.getBrojStanovnika() + "\n";
        }
        return rez;
    }

    public static void glavniGrad() {
        System.out.println("Unesite ime drzave: ");
        Scanner scanner = new Scanner(System.in);
        String drzava = scanner.nextLine();
        Grad grad = GeografijaDAO.getGeografija().glavniGrad(drzava);
        if (grad != null) {
            System.out.println("Glavni grad drzave " + drzava + " je " + grad.getNaziv());
        } else {
            System.out.println("Nepostojeca drzava");
        }

    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        //zbog promjene forme trebace nam jedan stage koji ce imati ulogu primaryStage
        stage = primaryStage;
        ResourceBundle bundle = ResourceBundle.getBundle("Translation");
        Parent root = FXMLLoader.load(getClass().getResource("forma.fxml"), bundle);
        stage.setTitle("Spisak Drzava");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }



    public static void loadView(Locale locale) throws IOException {

        ResourceBundle bundle = ResourceBundle.getBundle("Translation", locale);
        Parent root = FXMLLoader.load(Main.class.getResource("forma.fxml"), bundle);
        stage.setTitle("Spisak drzava");
        stage.setScene(new Scene(root, 640, 480));
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }


}
