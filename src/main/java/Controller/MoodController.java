package Controller;

import Model.MoodEntry;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class MoodController implements Initializable {

    @FXML
    private DatePicker datePickerTanggal;
    @FXML
    private ComboBox<String> comboMood;
    @FXML
    private ColorPicker colorPickerMood;
    @FXML
    private TextField txtPemicu;
    @FXML
    private TableView<MoodEntry> tvMood;
    @FXML
    private TableColumn<MoodEntry, String> colTanggal;
    @FXML
    private TableColumn<MoodEntry, String> colMood;
    @FXML
    private TableColumn<MoodEntry, String> colWarna;
    @FXML
    private TableColumn<MoodEntry, String> colPemicu;

    private ObservableList<MoodEntry> dataMood;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Daftar mood
        comboMood.setItems(FXCollections.observableArrayList(
            "Bahagia", "Tenang", "Cemas", "Sedih", "Marah", "Overwhelmed"
        ));

        dataMood = FXCollections.observableArrayList();

        //Data Dummy
        dataMood.add(new MoodEntry("2025-11-11", "Bahagia", "#FFD700", "Menonton anime romance"));
        dataMood.add(new MoodEntry("2025-11-10", "Cemas", "#87CEEB", "Kelas terlalu ramai"));
        dataMood.add(new MoodEntry("2025-11-09", "Overwhelmed", "#1a1a1a", "Mengingat trauma dibully"));

        //Setup TableView Columns
        colTanggal.setCellValueFactory(new PropertyValueFactory<>("tanggal"));
        colMood.setCellValueFactory(new PropertyValueFactory<>("mood"));
        colWarna.setCellValueFactory(new PropertyValueFactory<>("warna"));
        colPemicu.setCellValueFactory(new PropertyValueFactory<>("pemicu"));

        //Bind data ke TableView
        tvMood.setItems(dataMood);

        //Listener untuk menampilkan detail ke input field
        tvMood.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSel, newSel) -> showMoodDetails(newSel)
        );
    }

    private void showMoodDetails(MoodEntry mood) {
        if (mood != null) {
            datePickerTanggal.setValue(java.time.LocalDate.parse(mood.getTanggal()));
            comboMood.setValue(mood.getMood());
            colorPickerMood.setValue(javafx.scene.paint.Color.web(mood.getWarna()));
            txtPemicu.setText(mood.getPemicu());
        } else {
            datePickerTanggal.setValue(null);
            comboMood.setValue(null);
            colorPickerMood.setValue(null);
            txtPemicu.clear();
        }
    }

    @FXML
    private void handleAddMood(ActionEvent event) {
        if (datePickerTanggal.getValue() == null || comboMood.getValue() == null || txtPemicu.getText().isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Input Error", "Lengkapi seluruh data sebelum menambahkan!");
            return;
        }

        String tanggal = datePickerTanggal.getValue().format(DateTimeFormatter.ISO_DATE);
        String mood = comboMood.getValue();
        String warna = "#" + colorPickerMood.getValue().toString().substring(2, 8);
        String pemicu = txtPemicu.getText();

        dataMood.add(new MoodEntry(tanggal, mood, warna, pemicu));
        clearFields();
        showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data mood berhasil ditambahkan.");
    }

    @FXML
    private void handleEditMood(ActionEvent event) {
        MoodEntry selected = tvMood.getSelectionModel().getSelectedItem();
        if (selected != null) {
            selected.setTanggal(datePickerTanggal.getValue().format(DateTimeFormatter.ISO_DATE));
            selected.setMood(comboMood.getValue());
            selected.setWarna("#" + colorPickerMood.getValue().toString().substring(2, 8));
            selected.setPemicu(txtPemicu.getText());
            tvMood.refresh();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data mood berhasil diperbarui.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih data yang ingin diubah.");
        }
    }

    @FXML
    private void handleDeleteMood(ActionEvent event) {
        MoodEntry selected = tvMood.getSelectionModel().getSelectedItem();
        if (selected != null) {
            dataMood.remove(selected);
            clearFields();
            showAlert(Alert.AlertType.INFORMATION, "Sukses", "Data mood berhasil dihapus.");
        } else {
            showAlert(Alert.AlertType.WARNING, "Peringatan", "Pilih data yang ingin dihapus.");
        }
    }

    private void clearFields() {
        datePickerTanggal.setValue(null);
        comboMood.setValue(null);
        colorPickerMood.setValue(null);
        txtPemicu.clear();
        tvMood.getSelectionModel().clearSelection();
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
