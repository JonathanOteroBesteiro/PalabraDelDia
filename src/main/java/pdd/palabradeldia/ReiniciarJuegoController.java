package pdd.palabradeldia;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReiniciarJuegoController {

    @FXML
    private Button botonReiniciarConfirmar;

    private PalabraDelDiaController mainController;

    public void setMainController(PalabraDelDiaController mainController) {
        this.mainController = mainController;
    }

    @FXML
    private void confirmarReinicio() {
        if (mainController != null) {
            mainController.reiniciarJuego();
        }
        Stage stage = (Stage) botonReiniciarConfirmar.getScene().getWindow();
        stage.close();
    }
}


