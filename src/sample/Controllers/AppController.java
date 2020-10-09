package sample.Controllers;


import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class AppController {

    @FXML
    private TextField LatStartDeg;

    @FXML
    private TextField LongStartDeg;

    @FXML
    private TextField LatStartMin;

    @FXML
    private TextField LongStartMin;

    @FXML
    private TextField LatFnDeg;

    @FXML
    private TextField LatFinMin;

    @FXML
    private TextField LongFinDeg;

    @FXML
    private TextField LongFinMin;

    @FXML
    private Button rhumbLineCalculation;

    @FXML
    private Button greatCircleCalculation;

    @FXML
    private ChoiceBox<String> CB1;

    @FXML
    private ChoiceBox<String> CB2;

    @FXML
    private ChoiceBox<String> CB3;

    @FXML
    private ChoiceBox<String> CB4;

    @FXML
    private Button clearFields;

    @FXML
    private Label response;

    @FXML
    private TableView<Position> table;

    @FXML
    private TableColumn<Position, Integer> number;

    @FXML
    private TableColumn<Position, Double> latitude;

    @FXML
    private TableColumn<Position, Double> longitude;

    @FXML
    private TableColumn<Position, Double> course;

    @FXML
    private TableColumn<Position, Double> distance;

    @FXML
    private Label distanceGreatCircle;

    @FXML
    private Label vertexLatitude;

    @FXML
    private Label vertexLongitude;


    private double startLatitudeDegrees = 0;
    private double startLatitudeMinutes = 0;
    private double startLongitudeDegrees = 0;
    private double startLongitudeMinutes = 0;
    private double finalLatitudeDegrees = 0;
    private double finalLatitudeMinutes = 0;
    private double finalLongitudeDegrees = 0;
    private double finalLongitudeMinutes = 0;


    @FXML
    void initialize() {
        CB1.getItems().addAll("N", "S");
        CB1.setValue("N");
        CB2.getItems().addAll("N", "S");
        CB2.setValue("N");
        CB3.getItems().addAll("W", "E");
        CB3.setValue("E");
        CB4.getItems().addAll("W", "E");
        CB4.setValue("E");
        table.setVisible(false);

        rhumbLineCalculation.setOnAction(event ->
                calculateRhumbLineCourse(LatStartDeg.getText(), LatStartMin.getText(), LongStartDeg.getText(),
                        LongStartMin.getText(), LatFnDeg.getText(), LatFinMin.getText(), LongFinDeg.getText(),
                        LongFinMin.getText(), CB1.getValue(), CB2.getValue(), CB3.getValue(), CB4.getValue())
        );

        clearFields.setOnAction(event -> {
            LatStartDeg.setText("");
            LongStartDeg.setText("");
            LatStartMin.setText("");
            LongStartMin.setText("");
            LatFnDeg.setText("");
            LatFinMin.setText("");
            LongFinDeg.setText("");
            LongFinMin.setText("");
        });

        greatCircleCalculation.setOnAction(e ->
                calculateGreatCircleCourse(LatStartDeg.getText(), LatStartMin.getText(), LongStartDeg.getText(),
                        LongStartMin.getText(), LatFnDeg.getText(), LatFinMin.getText(), LongFinDeg.getText(),
                        LongFinMin.getText(), CB1.getValue(), CB2.getValue(), CB3.getValue(), CB4.getValue())
        );
    }


    private void calculateGreatCircleCourse(String Input1, String Input2, String Input3, String Input4, String Input5, String Input6,
                                            String Input7, String Input8, String Input9, String Input10, String Input11, String Input12) {


        ArrayList<Double> longitudes = new ArrayList<>();

        try {
            startLatitudeDegrees = Math.toRadians(Double.parseDouble(Input1));
            if (checkLatitude(Input1)) {
                invalidValueForLatitude1(Input1);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForLatitude1(Input1);
            return;
        }
        try {
            startLatitudeMinutes = Math.toRadians(Double.parseDouble(Input2)) / 60;
            if (checkMinutes(Input2)) {
                invalidValueForMinutes1(Input2);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input2);
            return;
        }
        double latStart = (startLatitudeDegrees + startLatitudeMinutes);
        if (Input9.equals("S")) {
            latStart = latStart * (-1);
        } else latStart = latStart * 1;

        try {
            startLongitudeDegrees = Math.toRadians(Double.parseDouble(Input3));
            if (checkLongitude(Input3)) {
                invalidValueForLongitude1(Input3);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForLongitude1(Input3);
            return;
        }
        try {
            startLongitudeMinutes = Math.toRadians(Double.parseDouble(Input4)) / 60;
            if (checkMinutes(Input4)) {
                invalidValueForMinutes1(Input4);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input4);
            return;
        }
        double longStart = startLongitudeDegrees + startLongitudeMinutes;
        if (Input11.equals("W")) {
            longStart = longStart * (-1);
        } else longStart = longStart * 1;

        try {
            finalLatitudeDegrees = Math.toRadians(Double.parseDouble(Input5));
            if (checkLatitude(Input5)) {
                invalidValueForLatitude1(Input5);
                return;
            }
        } catch (NumberFormatException | ArithmeticException e) {
            invalidValueForLatitude1(Input5);
            return;
        }
        try {
            finalLatitudeMinutes = Math.toRadians(Double.parseDouble(Input6)) / 60;
            if (checkMinutes(Input6)) {
                invalidValueForMinutes1(Input6);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input6);
            return;
        }
        double latFin = finalLatitudeDegrees + finalLatitudeMinutes;
        if (Input10.equals("S")) {
            latFin = latFin * (-1);
        } else latFin = latFin * 1;

        try {
            finalLongitudeDegrees = Math.toRadians(Double.parseDouble(Input7));
            if (checkLongitude(Input7)) {
                invalidValueForLongitude1(Input7);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForLongitude1(Input7);
            return;
        }
        try {
            finalLongitudeMinutes = Math.toRadians(Double.parseDouble(Input8)) / 60;
            if (checkMinutes(Input8)) {
                invalidValueForMinutes1(Input8);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input8);
            return;
        }
        double longFin = finalLongitudeDegrees + finalLongitudeMinutes;
        if (Input12.equals("W")) {
            longFin = longFin * (-1);
        } else longFin = longFin * 1;

        double longitudeDifference = longFin - longStart;
        if (longitudeDifference > 180 * 0.0174533) {
            longitudeDifference = longitudeDifference - 360 * 0.0174533;
        }
        if (longitudeDifference < (-180 * 0.0174533)) {
            longitudeDifference = 360 * 0.0174533 + longitudeDifference;
        }
        double RD1 = longFin - longStart;


        double cosDistance = Math.sin(latStart) * Math.sin(latFin) + Math.cos(latStart) * Math.cos(latFin) * Math.cos(longitudeDifference);
        double distanceRadians = Math.acos(cosDistance);

        double distanceDegrees = Math.toDegrees(distanceRadians);
        double distanceKnots = distanceDegrees * 60;


        double cotanGCCourseRadStart = Math.tan(latFin) * Math.cos(latStart) / Math.sin(RD1)
                - Math.sin(latStart) * Math.cos(RD1) / Math.sin(RD1);


        double tanGCCourseRadStart = 1 / cotanGCCourseRadStart;


        double greatCircleCourseRadStart = Math.atan(tanGCCourseRadStart);
      /*  double greatCircleCourseRadFinal = Math.atan(tanGCCourseRadFin);

        double greatCircleCourseDegStart = Math.toDegrees(greatCircleCourseRadStart);*/
        /*greatCircleCourseDegStart = calculateStartCourseDegree(
                greatCircleCourseDegStart,
                longitudeDifferenceDegrees,
                longStart,
                longFin
        );


        double greatCircleCourseDegFinal = Math.toDegrees(greatCircleCourseRadFinal);
        greatCircleCourseDegFinal = GreatCircleCoursesCalculator.calculateFinalCourseDegree(
                greatCircleCourseDegFinal,
                longitudeDifferenceDegrees,
                longStart,
                longFin
        );*/
        double cosLatVertRad = Math.cos(latStart) * Math.sin(greatCircleCourseRadStart);
        double latitudeVertexRadians = Math.acos(cosLatVertRad);
        double latitudeVertexDegrees = Math.toDegrees(latitudeVertexRadians);
        if (latitudeVertexDegrees > 90) {
            latitudeVertexDegrees = 180 - latitudeVertexDegrees;
        }
        if (latitudeVertexDegrees < -90) {
            latitudeVertexDegrees = 180 + latitudeVertexDegrees;
        }
        double vertexLongitudeRadians = longStart + Math.atan(1 / ((Math.sin(latStart) * Math.tan(greatCircleCourseRadStart))));
        double vertexLongitudeDegrees = Math.toDegrees(vertexLongitudeRadians);
        if (vertexLongitudeDegrees > 180) {
            vertexLongitudeDegrees = vertexLongitudeDegrees - 360;
        }
        if (vertexLongitudeDegrees < -180) {
            vertexLongitudeDegrees = vertexLongitudeDegrees + 360;
        }

        ObservableList<Position> common = FXCollections.observableArrayList();
        for (int p = 0; p <= 19; p++) {
            common.add(new Position());
        }
        common.forEach(position -> position.setNumber(common.indexOf(position) + 1));
        double a = longitudeDifference / 19;

        double longitude;
        if (longStart > longFin) {
            for (int i = 0; i <= 19; i++) {
                longitude = longStart + i * a;
                common.get(i).setLongitude(longitude * 57.2958);
                longitudes.add(longitude);
            }
        }
        if (longStart < longFin) {
            for (int i = 0; i <= 19; i++) {
                longitude = longStart + i * a;
                common.get(i).setLongitude(longitude * 57.2958);
                longitudes.add(longitude);
            }
            common.get(19).setLongitude(Math.round(longFin * 57.2958));
            longitudes.add(longFin);
        }
        longitudes.stream().filter(p -> p > 180 * 0.0174533).forEach(p -> p = -360 * 0.0174533 + p);
        longitudes.stream().filter(p -> p < -180 * 0.0174533).forEach(p -> p = 360 * 0.0174533 + p);
        List<Double> latitudeList = calc(longitudes, longStart, longFin, longitudeDifference, latStart, latFin);
        common.get(0).setLatitude(Math.toDegrees(latStart));
        for (int i = 0; i <= 17; i++) {
            common.get(i + 1).setLatitude(latitudeList.get(i + 1));
        }
        common.get(19).setLatitude(Math.toDegrees(latFin));
        for (Position p : common) {
            if (p.getLongitude() > 180) {
                p.setLongitude(p.getLongitude() - 360);
            } else if (p.getLongitude() < -180) {
                p.setLongitude(p.getLongitude() + 360);
            }
        }
        for (int i = 0; i <= 18; i++) {
            double c;
            c = course(common, i);
            common.get(i).setCourse(c);
        }
        for (int i = 0; i <= 18; i++) {
            double d = distance(common, i);
            common.get(i).setDistance(Math.round(d));
        }
        ObservableList<Position> fin = transform(common);
        //waypointsList(fin, distanceKnots, latitudeVertexDegrees, vertexLongitudeDegrees);
        drawTable(fin);
        calculateVertex(latitudeVertexDegrees, vertexLongitudeDegrees, distanceKnots);
    }

    private static ObservableList<Position> transform(ObservableList<Position> l1) {
        ObservableList<Position> OP = FXCollections.observableArrayList();
        for (Position position : l1) {
            double a = position.getLatitude();
            double b = position.getLongitude();
            double aMin = (Math.abs(a) - Math.floor(Math.abs(a))) * 60;
            aMin = Math.floor(aMin);
            double bMin = (Math.abs(b) - Math.floor(Math.abs(b))) * 60;
            bMin = Math.floor(bMin);
            double aDeg = Math.floor(Math.abs(a));
            double bDeg = Math.floor(Math.abs(b));
            String N = "N";
            String W = "W";
            if (position.getLatitude() < 0) {
                N = "S";
            }
            if (position.getLongitude() > 0) {
                W = "E";
            }
            String aMinString = Double.toString(aMin);
            String aMinStringCor = aMinString.substring(0, aMinString.indexOf("."));
            String bMinString = Double.toString(bMin);
            String bMinStringCor = bMinString.substring(0, bMinString.indexOf("."));
            String aDegString = Double.toString(aDeg);
            String aDegStringCor = aDegString.substring(0, aDegString.indexOf("."));
            String bDegString = Double.toString(bDeg);
            String bDegStringCor = bDegString.substring(0, bDegString.indexOf("."));
            String finLat = aDegStringCor + "°" + aMinStringCor + "'" + N;
            position.setFinLat(finLat);
            String finLong = bDegStringCor + "°" + bMinStringCor + "'" + W;
            position.setFinLong(finLong);
            OP.add(position);
        }
        return OP;
    }

    private static List<Double> calc(ArrayList<Double> list, Double LongStart, Double LongFin, Double RD,
                                     double LatStart, double LatFin) {
        ArrayList<Double> Lat2 = new ArrayList<>();
        for (double c : list) {
            double LatRad1 = Math.atan((Math.tan(LatStart) * Math.sin(LongFin - c) / Math.sin(RD)) + (Math.tan(LatFin)
                    * Math.sin(c - LongStart) / Math.sin(RD)));
            double LatDeg1 = Math.toDegrees(LatRad1);
            Lat2.add(LatDeg1);
        }
        return Lat2;
    }

    private void drawTable(ObservableList<Position> l1) {
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        latitude.setCellValueFactory(new PropertyValueFactory<>("finLat"));
        longitude.setCellValueFactory(new PropertyValueFactory<>("finLong"));
        course.setCellValueFactory(new PropertyValueFactory<>("course"));
        distance.setCellValueFactory(new PropertyValueFactory<>("distance"));
        table.setItems(l1);
        table.setVisible(true);
    }

    private void calculateVertex(double a, double b, double c) {
        if (a < 0) {
            vertexLatitude.setText("Vertex latitude is " + Math.round(Math.floor(Math.abs(a))) + " ° " +
                    Math.round((Math.abs(a) - Math.floor(Math.abs(a))) * 60) + " ' " + " S");
        } else {
            vertexLatitude.setText("Vertex latitude is " + Math.round(Math.floor(Math.abs(a))) + " ° " +
                    Math.round((Math.abs(a) - Math.floor(Math.abs(a))) * 60) + " ' " + " N");
        }
        if (b < 0) {
            vertexLongitude.setText("Vertex longitude is " + Math.round(Math.floor(Math.abs(b))) + " ° " +
                    Math.round((Math.abs(b) - Math.floor(Math.abs(b))) * 60) + " ' " + " W");
        } else {
            vertexLongitude.setText("Vertex longitude is " + Math.round(Math.floor(Math.abs(b))) + " ° " +
                    Math.round((Math.abs(b) - Math.floor(Math.abs(b))) * 60) + " ' " + " E");
        }
        distanceGreatCircle.setText("Great circle distance is " + Math.round(c) + " miles");
    }

    private double course(ObservableList<Position> l1, Integer i) {
        Position a = l1.get(i);
        Position b = l1.get(i + 1);
        double aRadLat = Math.toRadians(a.getLatitude());
        double bRadLat = Math.toRadians(b.getLatitude());
        double aRadLong = Math.toRadians(a.getLongitude());
        double bRadLong = Math.toRadians(b.getLongitude());
        double longitudeDifference = bRadLong - aRadLong;
        if (longitudeDifference > 180 * 0.0174533) {
            longitudeDifference = longitudeDifference - 360 * 0.0174533;
        }
        if (longitudeDifference < (-180 * 0.0174533)) {
            longitudeDifference = 360 * 0.0174533 + longitudeDifference;
        }
        double longitudeDifferenceDegrees = Math.toDegrees(longitudeDifference);
        double longitudeDifferenceMinutes = longitudeDifferenceDegrees * 60;
        double meridionalDifference = 3437.75 * Math.log(Math.tan((45 * 0.0174533) + (bRadLat / 2)) / Math.tan((45 * 0.0174533) +
                (aRadLat / 2)));
        double rhumblineCourseRadians = Math.acos(meridionalDifference / (Math.sqrt((Math.pow(meridionalDifference, 2)) + (Math.pow(longitudeDifferenceMinutes, 2)))));
        if (longitudeDifferenceDegrees < 0) {
            rhumblineCourseRadians = 360 * 0.0174533 - rhumblineCourseRadians;
        }
        /*double loxodromiaCourse = Math.atan(longitudeDifference /
                (Math.log(Math.tan(45 * 0.0174533 + (bRadLat / 2)) *
                        Math.pow((1 - 0.0167 * Math.sin(bRadLat)) / (1 + 0.0167 * Math.sin(bRadLat)),0.0167/2)) -
                        Math.log(Math.tan(45 * 0.0174533 + (aRadLat / 2))*
                                Math.pow((1 - 0.0167 * Math.sin(bRadLat)) / (1 + 0.0167 * Math.sin(bRadLat)),0.0167/2))));
        double x = Math.toDegrees(loxodromiaCourse);*/
        return Math.round(Math.toDegrees(rhumblineCourseRadians));
    }

    private static double distance(ObservableList<Position> l1, Integer i) {
        Position a = l1.get(i);
        Position b = l1.get(i + 1);
        double aRadLat = Math.toRadians(a.getLatitude());
        double bRadLat = Math.toRadians(b.getLatitude());
        double aRadLong = Math.toRadians(a.getLongitude());
        double bRadLong = Math.toRadians(b.getLongitude());
        double RD = bRadLong - aRadLong;
        if (RD > 180 * 0.0174533) {
            RD = RD - 360 * 0.0174533;
        }
        if (RD < (-180 * 0.0174533)) {
            RD = 360 * 0.0174533 + RD;
        }
        double longitudeDifferenceDegrees = Math.toDegrees(RD);
        double longitudeDifferenceMinutes = longitudeDifferenceDegrees * 60;
        double meridionalDifference = 3437.75 * Math.log(Math.tan((45 * 0.0174533) + (bRadLat / 2)) / Math.tan((45 * 0.0174533) +
                (aRadLat / 2)));
        double rhumbLineCourseRadians = Math.acos(meridionalDifference / (Math.sqrt((Math.pow(meridionalDifference, 2)) + (Math.pow(longitudeDifferenceMinutes, 2)))));
        if (longitudeDifferenceDegrees < 0) {
            rhumbLineCourseRadians = 360 * 0.0174533 - rhumbLineCourseRadians;
        }
        double latitudeDifferenceRadians = bRadLat - aRadLat;
        double latitudeDifferenceMinutes = Math.toDegrees(latitudeDifferenceRadians) * 60;
        double rhumbLineDistance = latitudeDifferenceMinutes / Math.cos(rhumbLineCourseRadians);
        return Math.round(rhumbLineDistance);
    }


    private void calculateRhumbLineCourse(String Input1, String Input2, String Input3, String Input4, String Input5, String Input6,
                                          String Input7, String Input8, String Input9, String Input10, String Input11, String Input12) {

        try {
            startLatitudeDegrees = Math.toRadians(Double.parseDouble(Input1));
            if (checkLatitude(Input1)) {
                invalidValueForLatitude1(Input1);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForLatitude1(Input1);
            return;
        }
        try {
            startLatitudeMinutes = Math.toRadians(Double.parseDouble(Input2)) / 60;
            if (checkMinutes(Input2)) {
                invalidValueForMinutes1(Input2);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input2);
            return;
        }
        double startLatitude = (startLatitudeDegrees + startLatitudeMinutes);
        if (Input9.equals("S")) {
            startLatitude = startLatitude * (-1);
        } else startLatitude = startLatitude * 1;
        try {
            startLongitudeDegrees = Math.toRadians(Double.parseDouble(Input3));
            if (checkLongitude(Input3)) {
                invalidValueForLongitude1(Input3);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForLongitude1(Input3);
            return;
        }
        try {
            startLongitudeMinutes = Math.toRadians(Double.parseDouble(Input4)) / 60;
            if (checkMinutes(Input4)) {
                invalidValueForMinutes1(Input4);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input4);
            return;
        }
        double startLongitude = startLongitudeDegrees + startLongitudeMinutes;
        if (Input11.equals("W")) {
            startLongitude = startLongitude * (-1);
        } else startLongitude = startLongitude * 1;
        try {
            finalLatitudeDegrees = Math.toRadians(Double.parseDouble(Input5));
            if (checkLatitude(Input5)) {
                invalidValueForLatitude1(Input5);
                return;
            }
        } catch (NumberFormatException | ArithmeticException e) {
            invalidValueForLatitude1(Input5);
            return;
        }
        try {
            finalLatitudeMinutes = Math.toRadians(Double.parseDouble(Input6)) / 60;
            if (checkMinutes(Input6)) {
                invalidValueForMinutes1(Input6);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input6);
            return;
        }
        double finalLatitude = finalLatitudeDegrees + finalLatitudeMinutes;
        if (Input10.equals("S")) {
            finalLatitude = finalLatitude * (-1);
        } else finalLatitude = finalLatitude * 1;
        try {
            finalLongitudeDegrees = Math.toRadians(Double.parseDouble(Input7));
            if (checkLongitude(Input7)) {
                invalidValueForLongitude1(Input7);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForLongitude1(Input7);
            return;
        }
        try {
            finalLongitudeMinutes = Math.toRadians(Double.parseDouble(Input8)) / 60;
            if (checkMinutes(Input8)) {
                invalidValueForMinutes1(Input8);
                return;
            }
        } catch (ArithmeticException | NumberFormatException e) {
            invalidValueForMinutes1(Input8);
            return;
        }
        double finalLongitude = finalLongitudeDegrees + finalLongitudeMinutes;
        if (Input12.equals("W")) {
            finalLongitude = finalLongitude * (-1);
        } else finalLongitude = finalLongitude * 1;
        double latitudeDifferenceRadians = finalLatitude - startLatitude;
        double latitudeDifferenceMinutes = Math.toDegrees(latitudeDifferenceRadians) * 60;
        double longitudeDifferenceRadians = finalLongitude - startLongitude;
        double longitudeDifferenceDegrees = Math.toDegrees(longitudeDifferenceRadians);
        if (longitudeDifferenceDegrees >= 180)
            longitudeDifferenceDegrees = longitudeDifferenceDegrees - 360;
        else if (longitudeDifferenceDegrees <= (-180))
            longitudeDifferenceDegrees = longitudeDifferenceDegrees + 360;
        double longitudeDifferenceMinutes = longitudeDifferenceDegrees * 60;
        double meridionalDifference = 3437.75 * Math.log(Math.tan(((45 * 0.0174533) + (finalLatitude / 2))) /
                Math.tan((45 * 0.0174533) + (startLatitude / 2)));
        double loxodromiaCourseRadians = Math.acos(meridionalDifference /
                (Math.sqrt((Math.pow(meridionalDifference, 2)) + (Math.pow(longitudeDifferenceMinutes, 2)))));
        if (longitudeDifferenceDegrees < 0) {
            loxodromiaCourseRadians = 360 * 0.0174533 - loxodromiaCourseRadians;
        }
        /*double loxodromiaCourse = Math.atan(longitudeDifferenceRadians /
                (Math.log(Math.tan(45 * 0.0174533 + finalLatitude / 2)) -
                        Math.log(Math.tan(45 * 0.0174533) + (startLatitude / 2))));
        double x = Math.toDegrees(loxodromiaCourse);*/
        double loxodromiaCourseDegrees = Math.toDegrees(loxodromiaCourseRadians);
        double loxodromiaDistance = latitudeDifferenceMinutes / Math.cos(loxodromiaCourseRadians);


        response.setText("Rhumbline course is " + Math.round(loxodromiaCourseDegrees) + "°" +
                " distance is " + Math.round(loxodromiaDistance) + " miles");
    }

    private void invalidValueForLatitude1(String j) {
        response.setText(j + " is wrong value,value should be between 0 " +
                "and 90 degrees and does not contain letter or special symbols");
    }

    private void invalidValueForMinutes1(String j) {
        response.setText(j + " is wrong value,value should be between 0 "
                + "and 60 minutes and does not contain letter or special symbols");
    }

    private void invalidValueForLongitude1(String j) {
        response.setText(j + " is wrong value,value should be between 0 "
                + "and 180 degrees and does not not contains letters or special symbols");
    }

    /*private static void invalidValueForLatitude(String j) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error");
        stage.setMinWidth(600);
        Label LB7 = new Label();
        LB7.setMinWidth(450);
        LB7.setMinHeight(100);
        LB7.setFont(new Font("Times New Roman", 30));
        LB7.setText(j + "  is an invalid value,value of latitude should be between 0° and 90°");
        Button B1 = new Button("Close");
        B1.setMinSize(120, 40);
        B1.setOnAction(e -> stage.close());
        VBox VB1 = new VBox(10);
        VB1.getChildren().addAll(B1, LB7);
        VB1.setAlignment(Pos.CENTER);
        Scene SC1 = new Scene(VB1);
        stage.setScene(SC1);
        stage.show();
    }

    private static void invalidValueForLongitude(String j) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error");
        stage.setMinWidth(600);
        Label LB7 = new Label();
        LB7.setMinWidth(450);
        LB7.setMinHeight(100);
        LB7.setFont(new Font("Times New Roman", 30));
        LB7.setText(j + "  is an invalid value,value of longitude should be between 0° and 180°");
        Button B1 = new Button("Close");
        B1.setMinSize(120, 40);
        B1.setOnAction(e -> stage.close());
        VBox VB1 = new VBox(10);
        VB1.getChildren().addAll(B1, LB7);
        VB1.setAlignment(Pos.CENTER);
        Scene SC1 = new Scene(VB1);
        stage.setScene(SC1);
        stage.show();
    }*/

   /* private static void invalidValueForMinutes(String j) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Error");
        stage.setMinWidth(600);
        Label LB7 = new Label();
        LB7.setMinWidth(450);
        LB7.setMinHeight(100);
        LB7.setFont(new Font("Times New Roman", 30));
        LB7.setText(j + "  is an invalid value,value of minutes should be between 0' and 60' ");
        Button B1 = new Button("Close");
        B1.setMinSize(120, 40);
        B1.setOnAction(e -> stage.close());
        VBox VB1 = new VBox(10);
        VB1.getChildren().addAll(B1, LB7);
        VB1.setAlignment(Pos.CENTER);
        Scene SC1 = new Scene(VB1);
        stage.setScene(SC1);
        stage.show();
    }

    private void checkValue(String s, Predicate<String> p, Consumer<String> c) {
        if (p.test(s)) {
            c.accept(s);
        }
    }*/

    private boolean checkLatitude(String s) {
        return (Double.parseDouble(s) < 0 | Double.parseDouble(s) > 90);
    }

    private boolean checkMinutes(String s) {
        return (Double.parseDouble(s) < 0 | Double.parseDouble(s) > 60);
    }

    private boolean checkLongitude(String s) {
        return (Double.parseDouble(s) < 0 | Double.parseDouble(s) > 180);
    }
}
