package components;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class About {

  public void getAboutInfo(){
    Stage aboutStage = new Stage();

    aboutStage.setTitle("About MochaEditor");
    aboutStage.initModality(Modality.APPLICATION_MODAL);

    VBox aboutContent = new VBox();
    aboutContent.setPadding(new Insets(10));
    aboutContent.setSpacing(10);

    Label header = new Label("MochaEditor");
    header.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
    Label info = new Label("MochaEditor es un editor de texto desarrollado en JavaFX. \nVersión: 1.0\nDesarrollado por: Tu Nombre");

    Button closeButton = new Button("Cerrar");
    closeButton.setOnAction(e -> aboutStage.close());

    aboutContent.getChildren().addAll(header, info, closeButton);

    Scene aboutScene = new Scene(aboutContent, 500, 150);
    aboutScene.getStylesheets().add(getClass().getResource("../themes/darkTheme.css").toExternalForm());
    aboutStage.setScene(aboutScene);
    aboutStage.showAndWait();
  }
  
}
