package pdd.palabradeldia;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReiniciarJuegoController {

    @FXML
    private Button botonReiniciarConfirmar;

    private PalabraDelDiaController mainController;

    // Método para establecer el controlador principal
    public void setMainController(PalabraDelDiaController mainController) {
        this.mainController = mainController;
    }

    // Método que se ejecuta al confirmar el reinicio del juego
    @FXML
    private void confirmarReinicio() {
        if (mainController != null) {
            // Llamar al método para reiniciar el juego en el controlador principal
            mainController.reiniciarJuego();
        }
        // Cerrar la ventana de reinicio
        Stage stage = (Stage) botonReiniciarConfirmar.getScene().getWindow();
        stage.close();
    }
}



