import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Coordinate;
import model.Datapoint;
import model.Degree;
import util.DataLoader;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.io.IOException;
import java.util.stream.Collectors;

public class Controller {
    @FXML
    public MenuItem menuNewFile;
    @FXML
    public MenuItem menuLoadFile;
    @FXML
    public MenuItem menuClose;
    @FXML
    public TableView<Datapoint> rawTable;
    @FXML
    public TableColumn<Datapoint, String> rawTimeCol;
    @FXML
    public TableColumn<Datapoint, String> rawNorthLatCol;
    @FXML
    public TableColumn<Datapoint, String> rawNorthLongCol;
    @FXML
    public TableColumn<Datapoint, String> rawCenterLatCol;
    @FXML
    public TableColumn<Datapoint, String> rawCenterLongCol;
    @FXML
    public TableColumn<Datapoint, String> rawSouthLatCol;
    @FXML
    public TableColumn<Datapoint, String> rawSouthLongCol;
    @FXML
    public TableView<Datapoint> inputTable;
    @FXML
    public TableColumn<Datapoint, String> inputTimeCol;
    @FXML
    public TableColumn<Datapoint, String> inputNorthLatCol;
    @FXML
    public TableColumn<Datapoint, String> inputNorthLongCol;
    @FXML
    public TableColumn<Datapoint, String> inputCenterLatCol;
    @FXML
    public TableColumn<Datapoint, String> inputCenterLongCol;
    @FXML
    public TableColumn<Datapoint, String> inputSouthLatCol;
    @FXML
    public TableColumn<Datapoint, String> inputSouthLongCol;
    @FXML
    public LineChart<String, Double> latChart;
    @FXML
    public LineChart<String, Double> longChart;
    @FXML
    public DatePicker inputDateField;
    @FXML
    public TextField inputTimeField;
    @FXML
    public TextField inputNorthLatField;
    @FXML
    public TextField inputNorthLongField;
    @FXML
    public TextField inputCentralLatField;
    @FXML
    public TextField inputCentralLongField;
    @FXML
    public TextField inputSouthLatField;
    @FXML
    public TextField inputSouthLongField;
    @FXML
    public Button inputButton;
    private Stage stage;
    private File dataFile;

    public void initialize() {

        // INITIALIZE RAW TAB BINDINGS
        rawTimeCol.setCellValueFactory((row) -> {
            LocalDateTime moment = row.getValue().getMoment();
            return new SimpleStringProperty(moment.toString());
        });
        rawNorthLatCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getNorthern().getLatitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        rawNorthLongCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getNorthern().getLongitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        rawCenterLatCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getCentral().getLatitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        rawCenterLongCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getCentral().getLongitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        rawSouthLatCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getSouthern().getLatitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        rawSouthLongCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getSouthern().getLongitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });

        // INITIALIZE INPUT TAB BINDINGS
        inputTimeCol.setCellValueFactory((row) -> {
            LocalDateTime moment = row.getValue().getMoment();
            return new SimpleStringProperty(moment.toString());
        });
        inputNorthLatCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getNorthern().getLatitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        inputNorthLongCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getNorthern().getLongitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        inputCenterLatCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getCentral().getLatitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        inputCenterLongCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getCentral().getLongitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        inputSouthLatCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getSouthern().getLatitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
        inputSouthLongCol.setCellValueFactory((row) -> {
            Degree degree = row.getValue().getSouthern().getLongitude();
            return degree != null ? new SimpleStringProperty(degree.toString()) : new SimpleStringProperty("NULL");
        });
    }

    public void inputData(ActionEvent e) {
        Alert a = new Alert(Alert.AlertType.NONE);
        try {
            Degree northernLatitudeInput = Degree.parse(inputNorthLatField.getText());
            Degree northernLongitudeInput = Degree.parse(inputNorthLongField.getText());
            Degree centralLatitudeInput = Degree.parse(inputCentralLatField.getText());
            Degree centralLongitudeInput = Degree.parse(inputCentralLongField.getText());
            Degree southernLatitudeInput = Degree.parse(inputSouthLatField.getText());
            Degree southernLongitudeInput = Degree.parse(inputSouthLongField.getText());

            boolean allFieldsValid = inputDateField.getValue() != null &&
                    inputTimeField.getText() != null &&
                    northernLatitudeInput != null &&
                    northernLongitudeInput != null &&
                    southernLatitudeInput != null &&
                    southernLongitudeInput != null &&
                    centralLatitudeInput != null &&
                    centralLongitudeInput != null;

            if (allFieldsValid) {
                Coordinate northern = new Coordinate(northernLatitudeInput, northernLongitudeInput);
                Coordinate southern = new Coordinate(southernLatitudeInput, southernLongitudeInput);
                Coordinate central = new Coordinate(centralLatitudeInput, centralLongitudeInput);
                LocalDate dateInput = inputDateField.getValue();
                LocalTime timeInput = LocalTime.parse(inputTimeField.getText());
                LocalDateTime dateTime = LocalDateTime.of(dateInput, timeInput);
                Datapoint inputDatapoint = new Datapoint(dateTime, northern, southern, central);
                inputTable.getItems().add(inputDatapoint);
                String outputString = timeInput.getHour() + ":" + timeInput.getMinute() + "," +
                        northernLatitudeInput + "," +
                        northernLongitudeInput + "," +
                        southernLatitudeInput + "," +
                        southernLongitudeInput + "," +
                        centralLatitudeInput + "," +
                        centralLongitudeInput;
                Files.write(Paths.get(dataFile.toURI()), outputString.getBytes(), StandardOpenOption.APPEND);
                inputTimeField.clear();
                inputNorthLatField.clear();
                inputNorthLongField.clear();
                inputSouthLatField.clear();
                inputSouthLongField.clear();
                inputCentralLatField.clear();
                inputCentralLongField.clear();
            } else {
                a.setAlertType(Alert.AlertType.ERROR);
                a.setContentText("Must fill out all fields!");
                a.show();
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("IOException handled!");
            a.show();
        }
    }

    public void newFile(ActionEvent e) {
                // CLEAR ALL DATA
        rawTable.getItems().clear();
        inputTable.getItems().clear();
        latChart.getData().clear();
        longChart.getData().clear();
    }

    public void loadFile(ActionEvent e) {
        try {
            FileChooser fileInput = new FileChooser();
            dataFile  = fileInput.showOpenDialog(stage);
            if (dataFile != null && String.valueOf(dataFile.toPath()).endsWith(".csv")) {
                // LOAD PAYROLL DATA
                DataLoader loader = new DataLoader(dataFile.toURI().toURL());
                List<Datapoint> datapoints = loader.load();
                // PROCESS DATA INTO SERIES
                XYChart.Series<String, Double> northernLatSeries = new XYChart.Series<>("Northern",
                        datapoints.stream()
                                .map(datapoint -> new XYChart.Data<>(
                                        datapoint.getMoment().toString(),
                                        safeAsDouble(datapoint.getNorthern().getLatitude())))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                XYChart.Series<String, Double> northernLongSeries = new XYChart.Series<>( "Northern",
                        datapoints.stream()
                                .map(datapoint -> new XYChart.Data<>(
                                        datapoint.getMoment().toString(),
                                        safeAsDouble(datapoint.getNorthern().getLongitude())))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                XYChart.Series<String, Double> centralLatSeries = new XYChart.Series<>("Central",
                        datapoints.stream()
                                .map(datapoint -> new XYChart.Data<>(
                                        datapoint.getMoment().toString(),
                                        safeAsDouble(datapoint.getCentral().getLatitude())))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                XYChart.Series<String, Double> centralLongSeries = new XYChart.Series<>( "Central",
                        datapoints.stream()
                                .map(datapoint -> new XYChart.Data<>(
                                        datapoint.getMoment().toString(),
                                        safeAsDouble(datapoint.getCentral().getLongitude())))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                XYChart.Series<String, Double> southernLatSeries = new XYChart.Series<>("Southern",
                        datapoints.stream()
                                .map(datapoint -> new XYChart.Data<>(
                                        datapoint.getMoment().toString(),
                                        safeAsDouble(datapoint.getSouthern().getLatitude())))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                XYChart.Series<String, Double> southernLongSeries = new XYChart.Series<>( "Southern",
                        datapoints.stream()
                                .map(datapoint -> new XYChart.Data<>(
                                        datapoint.getMoment().toString(),
                                        safeAsDouble(datapoint.getSouthern().getLongitude())))
                                .collect(Collectors.toCollection(FXCollections::observableArrayList)));
                // UPDATE TABLE DATA
                rawTable.getItems().addAll(datapoints);
                // UPDATE CHART DATA
                latChart.getData().add(northernLatSeries);
                longChart.getData().add(northernLongSeries);
                latChart.getData().add(centralLatSeries);
                longChart.getData().add(centralLongSeries);
                latChart.getData().add(southernLatSeries);
                longChart.getData().add(southernLongSeries);
                // UPDATE GUI DATA
                inputDateField.setValue(datapoints.get(0).getMoment().toLocalDate());
            }
        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }
    private static Double safeAsDouble(Degree degree) {
        if (degree != null) {
            return degree.asDouble();
        }
        return 0.0;
    }

    public void closeApplication(ActionEvent e) {
        System.exit(0);
    }

    public void setStage(Stage s) { stage = s; }
}
