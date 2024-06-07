package pdd.palabradeldia;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PalabraDelDiaController {

    @FXML
    private TextField comprobarInput;
    @FXML
    private Button botonComprobar;
    @FXML
    private Label box1, box2, box3, box4, box5, box6, box7, box8, box9, box10,
            box11, box12, box13, box14, box15, box16, box17, box18, box19,
            box20, box21, box22, box23, box24, box25;

    private static int valori = 0;
    private static int contador = 0;
    private String palabra;

    // Método para iniciar el juego
    @FXML
    private void iniciarJuego() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Inicio del juego");
        alert.setHeaderText(null);
        alert.setContentText("El juego ha comenzado!");
        alert.showAndWait();
    }

    // Método para cerrar la aplicación
    @FXML
    private void cerrarApp() {
        Stage stage = (Stage) botonComprobar.getScene().getWindow();
        stage.close();
    }

    // Método que se ejecuta al inicializar el controlador
    @FXML
    private void initialize() {
        try {
            // Obtener una palabra aleatoria al inicio del juego
            palabra = obtenerPalabraAleatoria("src/main/resources/palabras.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener una palabra aleatoria de un archivo
    private String obtenerPalabraAleatoria(String archivo) throws IOException {
        List<String> palabras = leerPalabras(archivo);
        Random rand = new Random();
        return palabras.get(rand.nextInt(palabras.size()));
    }

    // Método para leer palabras desde un archivo
    private List<String> leerPalabras(String archivo) throws IOException {
        List<String> palabras = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = br.readLine()) != null) {
            palabras.add(linea.trim());
        }
        br.close();
        return palabras;
    }

    // Método que se ejecuta al hacer clic en el botón de comprobar
    @FXML
    private void botonComprobar() {
        if (comprobarInput == null) {
        }

        Label[] lista = {box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11,
                box12, box13, box14, box15, box16, box17, box18, box19, box20, box21, box22,
                box23, box24, box25};

        String comprobar = comprobarInput.getText().trim();

        if (comprobar.length() != 5) {
            // Mostrar mensaje de error si la palabra no tiene 5 letras
            Alert mensajeError = new Alert(Alert.AlertType.INFORMATION);
            mensajeError.setTitle("Error");
            mensajeError.setHeaderText("Palabra incorrecta");
            mensajeError.setContentText("¡La palabra a insertar debe ser de 5 letras!");
            mensajeError.showAndWait();
        } else {
            // Comprobar la palabra ingresada
            for (int i = 0; i < comprobar.length(); i++) {
                String letra = comprobar.substring(i, i + 1);
                lista[i + valori].setText(letra);
                if (letra.equals(palabra.substring(i, i + 1))) {
                    lista[i + valori].setStyle("-fx-background-color: #15a531");
                } else if (palabra.contains(letra)) {
                    lista[i + valori].setStyle("-fx-background-color: #efca50");
                } else {
                    lista[i + valori].setStyle("-fx-background-color: #c12323");
                }
            }
            valori += 5;
            contador++;

            if (comprobar.equals(palabra)) {
                // Mostrar mensaje de felicitación si se adivina la palabra
                mostrarMensaje("¡Felicidades!", "¡Has adivinado la palabra!", "La palabra correcta es: " + palabra);
                terminarJuego();
            } else if (contador >= 5) {
                // Mostrar mensaje de fin de juego si no se adivina la palabra después de 5 intentos
                mostrarMensaje("Juego terminado", "No has adivinado la palabra", "La palabra correcta era: " + palabra);
                terminarJuego();
            }
        }
    }

    // Método para mostrar un mensaje
    private void mostrarMensaje(String titulo, String encabezado, String contenido) {
        Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
        mensaje.setTitle(titulo);
        mensaje.setHeaderText(encabezado);
        mensaje.setContentText(contenido);
        mensaje.showAndWait();
    }

    // Método para terminar el juego
    private void terminarJuego() {
        comprobarInput.setDisable(true);
        botonComprobar.setDisable(true);
    }

    // Método para abrir la ventana de reinicio
    @FXML
    public void abrirVentanaReinicio() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ReiniciarJuego.fxml"));
        Parent root = fxmlLoader.load();

        // Obtener el controlador de reinicio
        ReiniciarJuegoController reiniciarController = fxmlLoader.getController();

        // Establecer el controlador principal en el controlador de reinicio
        reiniciarController.setMainController(this);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(scene);
        stage.show();
    }

    // Método para reiniciar el juego
    public void reiniciarJuego() {
        // Obtener una nueva palabra aleatoria
        try {
            palabra = obtenerPalabraAleatoria("src/main/resources/palabras.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        valori = 0;
        contador = 0;
        comprobarInput.setText("");
        comprobarInput.setDisable(false);
        botonComprobar.setDisable(false);
        Label[] lista = {box1, box2, box3, box4, box5, box6, box7, box8, box9, box10, box11,
                box12, box13, box14, box15, box16, box17, box18, box19, box20, box21, box22,
                box23, box24, box25};
        for (Label label : lista) {
            label.setText("");
            label.setStyle("-fx-background-color: white;");
        }
    }
}
