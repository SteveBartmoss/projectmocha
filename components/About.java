

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

    Scene aboutScene = new Scene(aboutContent, 300, 150);
    aboutStage.setScene(aboutScene);
    aboutStage.showAndWait();
  }
  
}
