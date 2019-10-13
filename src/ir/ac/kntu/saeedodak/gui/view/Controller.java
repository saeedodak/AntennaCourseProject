package ir.ac.kntu.saeedodak.gui.view;

import ir.ac.kntu.saeedodak.solution.*;
import ir.ac.kntu.saeedodak.solution.Point;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.util.*;
import java.io.File;
import java.io.IOException;

import javafx.scene.control.Alert.AlertType;

import javafx.util.Pair;

public class Controller {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button btn6;

    @FXML
    private Button btn7;

    @FXML
    private Button btn8;

    @FXML
    private void handleBtn1() {
        try {
            Desktop.getDesktop().open(new File("src\\ir\\ac\\kntu\\saeedodak\\Book1.xlsx"));
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void handleBtn2() {

        Stage chartStage = new Stage();
        chartStage.setMaximized(true);
        chartStage.setTitle("Charts");
        chartStage.initModality(Modality.WINDOW_MODAL);

        NumberAxis xAxis = new NumberAxis(0, 50, 20);
        xAxis.setLabel("n");

        NumberAxis yAxis = new NumberAxis(0, 120, 100);
        yAxis.setLabel("D0");

        LineChart linechart = new LineChart(xAxis, yAxis);
        linechart.setMinSize(1330, 700);

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series1.setName("Exact");
        series2.setName("Kraus");
        series3.setName("Tai");

        Problem2 two = new Problem2();

        for(int i=1; i<=50; i++) {
            series1.getData().add(new XYChart.Data(i, two.exactValue(i)));
            series2.getData().add(new XYChart.Data(i, two.krausValue(i)));
            series3.getData().add(new XYChart.Data(i, two.taiVlaue(i)));
        }

        linechart.getData().addAll(series1, series2, series3);

        Group root = new Group(linechart);

        Scene scene = new Scene(root, 600, 10000);

        chartStage.setTitle("Line Chart");

        chartStage.setScene(scene);

        chartStage.show();
    }

    @FXML
    private void handleBtn3() {

        Dialog < Pair < Pair <String, String>, Pair <String, String> > >  dialog = new Dialog <> ();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Do Calculator");

        ButtonType ButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField theta1 = new TextField();
        theta1.setPromptText("in degree");
        TextField theta2 = new TextField();
        theta2.setPromptText("in degree");
        TextField phi1 = new TextField();
        phi1.setPromptText("in degree");
        TextField phi2 = new TextField();
        phi2.setPromptText("in degree");

        grid.add(new Label("Theta1 :"), 0, 0);
        grid.add(theta1, 1, 0);
        grid.add(new Label("Theta2 :"), 0, 1);
        grid.add(theta2, 1, 1);
        grid.add(new Label("Phi1 :"), 0, 2);
        grid.add(phi1, 1, 2);
        grid.add(new Label("Phi2 :"), 0, 3);
        grid.add(phi2, 1, 3);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType) {
                return new Pair <> (new Pair <> (theta1.getText(), theta2.getText()),
                        new Pair <> (phi1.getText(), phi2.getText()));
            }
            return null;
        });

        Optional < Pair <Pair <String, String>, Pair <String, String>> > result = dialog.showAndWait();

        String value = "" + result.get();

        Problem2 two = new Problem2();
        double t1 = Double.parseDouble(value.split("=")[0]);
        double t2 = Double.parseDouble(value.split("=")[1]);
        double p1 = Double.parseDouble(value.split("=")[2]);
        double p2 = Double.parseDouble(value.split("=")[3]);

        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Answer Dialog");
        alert.setHeaderText("Answer");
        alert.setContentText("D0 : " + two.solvePart2(t1, t2, p1, p2));
        alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        alert.showAndWait();

    }

    @FXML
    private void handleBtn4() {

        Dialog < Pair <String, String> > dialog = new Dialog <> ();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("PLF Calculator");

        ButtonType ButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField alpha = new TextField();
        alpha.setPromptText("a+jb,c+jd");
        TextField beta = new TextField();
        beta.setPromptText("a+jb,c+jd");

        grid.add(new Label("E_t :"), 0, 0);
        grid.add(alpha, 1, 0);
        grid.add(new Label("E_r :"), 0, 1);
        grid.add(beta, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType) {
                return new Pair <> (alpha.getText(), beta.getText());
            }
            return null;
        });

        Optional < Pair <String, String> > result = dialog.showAndWait();

        String value = "" + result.get();
        String S = (value.split("=")[0]) + ",0+j0";
        Point foo = new Point(S);
        S = (value.split("=")[1]) + ",0+j0";
        Point bar = new Point(S);

        Problem3 three = new Problem3();
        double ans = three.solvePartA(foo, bar);

        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Answer Dialog");
        alert.setHeaderText("Answer");
        alert.setContentText("PLF : " + ans);
        alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        alert.showAndWait();

    }

    @FXML
    private void handleBtn5() {

        Dialog < Pair <String, String> > dialog = new Dialog <> ();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Polarization Calculator");

        ButtonType ButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField alpha = new TextField();
        alpha.setPromptText("a+jb");
        TextField beta = new TextField();
        beta.setPromptText("a+jb");

        grid.add(new Label("E_x :"), 0, 0);
        grid.add(alpha, 1, 0);
        grid.add(new Label("E_y :"), 0, 1);
        grid.add(beta, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType) {
                return new Pair <> (alpha.getText(), beta.getText());
            }
            return null;
        });

        Optional < Pair <String, String> > result = dialog.showAndWait();

        String value = "" + result.get();
        String S = (value.split("=")[0]) + "," +  (value.split("=")[1]) + ",0+j0";
        Point foo = new Point(S);

        Problem3 three = new Problem3();
        String ans = three.getPolarization(foo);

        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Answer Dialog");
        alert.setHeaderText("Answer");
        alert.setContentText("Polarization : " + ans);
        alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        alert.showAndWait();
    }

    @FXML
    private void handleBtn6() {

        Dialog < Pair < Pair < Pair <String, String>, Pair <String, String> >, Pair < Pair <String, String>, String > > > dialog = new Dialog <> ();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Enter Friis Formula Parameters.");

        ButtonType ButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 10, 10, 10));

        TextField Pt = new TextField();
        Pt.setPromptText("Pt");
        TextField F = new TextField();
        F.setPromptText("f");
        TextField R = new TextField();
        R.setPromptText("R");
        TextField Gor = new TextField();
        Gor.setPromptText("G_or");
        TextField Got = new TextField();
        Got.setPromptText("G_ot");
        TextField alpha = new TextField();
        alpha.setPromptText("vector1 = a+jb,c+jd,e+jf");
        TextField beta = new TextField();
        beta.setPromptText("vector2 = a+jb,c+jd,e+jf");

        grid.add(new Label("Pt :"), 0, 0);
        grid.add(Pt, 1, 0);
        grid.add(new Label("f :"), 0, 1);
        grid.add(F, 1, 1);
        grid.add(new Label("R :"), 0, 2);
        grid.add(R, 1, 2);
        grid.add(new Label("G_or :"), 0, 3);
        grid.add(Gor, 1, 3);
        grid.add(new Label("G_ot :"), 0, 4);
        grid.add(Got, 1, 4);
        grid.add(new Label("Vector1 :"), 0, 5);
        grid.add(alpha, 1, 5);
        grid.add(new Label("Vector2 :"), 0, 6);
        grid.add(beta, 1, 6);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType) {
                return new Pair <> ( new Pair <> ( new Pair <> (Pt.getText(), F.getText()), new Pair <> (R.getText(), Gor.getText()) ) ,
                                        new Pair <> ( new Pair <> (Got.getText(), alpha.getText()), beta.getText() )
                );
            }
            return null;
        });

        Optional < Pair < Pair < Pair <String, String>, Pair <String, String> >, Pair < Pair <String, String>, String > > >  result = dialog.showAndWait();

        String value = "" + result.get();
        double _Pt = Integer.parseInt(value.split("=")[0]);
        double _f = Integer.parseInt(value.split("=")[1]);
        double _R = Integer.parseInt(value.split("=")[2]);
        double gor = Integer.parseInt(value.split("=")[3]);
        double got = Integer.parseInt(value.split("=")[4]);
        Point A = new Point(value.split("=")[5]);
        Point B = new Point(value.split("=")[6]);

        Problem4 four = new Problem4();
        double ans = four.solve(_Pt, _f,_R, gor, got, A, B);

        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Answer Dialog");
        alert.setHeaderText("Answer");
        alert.setContentText("Pr : " + ans);
        alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        alert.showAndWait();

    }

    @FXML
    private void handleBtn7() {

        Dialog < Pair <String, String> > dialog = new Dialog <> ();
        dialog.setTitle("Input Dialog");
        dialog.setHeaderText("Enter N and M.");

        ButtonType ButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField N = new TextField();
        N.setPromptText("N");
        TextField M = new TextField();
        M.setPromptText("M");

        grid.add(new Label("N :"), 0, 0);
        grid.add(N, 1, 0);
        grid.add(new Label("M :"), 0, 1);
        grid.add(M, 1, 1);

        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton -> {
            if(dialogButton == ButtonType) {
                return new Pair<>(N.getText(), M.getText());
            }
            return null;
        });

        Optional < Pair <String, String> > result = dialog.showAndWait();

        String value = "" + result.get();
        int n = Integer.parseInt(value.split("=")[0]);
        int m = Integer.parseInt(value.split("=")[1]);
        Problem5 five = new Problem5(n, m);

        Alert alert = new Alert(AlertType.NONE);
        alert.setTitle("Answer Dialog");
        alert.setHeaderText("Answer");
        alert.setContentText("D0 : " + five.solve());
        alert.getDialogPane().getButtonTypes().add(new ButtonType("OK", ButtonBar.ButtonData.OK_DONE));
        alert.showAndWait();

    }

    @FXML
    private void handleBtn8() {
        System.exit(0);
    }

}
