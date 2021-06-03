package org.autoflowchart.gui;

import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.autoflowchart.logic.Drawer;
import org.autoflowchart.logic.Processor;
import org.autoflowchart.logic.Saver;
import org.autoflowchart.objects.Layout;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Controller
{
    public Button aboutButton = null;
    public Button importButton = null;
    public Button flowchartButton = null;
    public Button settingsButton = null;
    public Button exportButton = null;
    public Button viewButton = null;
    public VBox settingsVBox;
    public TextArea codeTextArea;
    public ColorPicker terminalBackColorPicker;
    public ColorPicker terminalLineColorPicker;
    public TextField terminalTextField;
    public TextField terminalLineTextField;
    public ColorPicker processBackColorPicker;
    public ColorPicker processLineColorPicker;
    public TextField processTextField;
    public TextField processLineTextField;
    public ColorPicker decisionBackColorPicker;
    public ColorPicker decisionLineColorPicker;
    public TextField decisionTextField;
    public TextField decisionLineTextField;
    public ColorPicker functionBackColorPicker;
    public ColorPicker functionLineColorPicker;
    public TextField functionTextField;
    public TextField functionLineTextField;
    public ColorPicker outputBackColorPicker;
    public ColorPicker outputLineColorPicker;
    public TextField outputTextField;
    public TextField outputLineTextField;
    public Stage primaryStage;
    public Canvas canvas;
    public StackPane canvasStackPane;

    public Processor processor = new Processor();
    public Drawer drawer = new Drawer();
    public Saver saver = new Saver();

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    public void aboutButtonClicked(Event e) throws IOException {
        Parent root = (Parent) FXMLLoader.load(this.getClass().getResource("window_about.fxml"));

        Stage stage = new Stage();
        stage.setTitle("About");
        stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.setX(500);
        stage.setY(300);
        stage.setWidth(600);
        stage.setHeight(300);
        stage.showAndWait();
    }

    @FXML
    public void importButtonClicked(Event e) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        FileReader fr = new FileReader(selectedFile);
        int i;
        String code = "";
        while((i=fr.read())!=-1){
            code += String.valueOf(((char)i));
        }
        fr.close();
        codeTextArea.setText(code);
    }

    @FXML
    public void flowchartButtonClicked(Event e) throws IOException
    {
        Layout layout = this.processor.process(codeTextArea.getText());

        canvas.setWidth(layout.width + Drawer.OFFSET_X * 2);
        canvas.setHeight(layout.height + Drawer.OFFSET_Y * 2);
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.fitToWidthProperty().set(true);
        scrollPane.fitToHeightProperty().set(true);
        scrollPane.pannableProperty().set(true);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.hbarPolicyProperty().setValue(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(canvas);
        canvasStackPane.getChildren().add(scrollPane);
        try {
            this.drawer.draw(layout, canvas.getGraphicsContext2D());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    @FXML
    public void settingsButtonClicked(Event e){
        codeTextArea.setDisable(true);
        codeTextArea.setVisible(false);
        settingsVBox.setVisible(true);
        settingsVBox.setDisable(false);
    }

    @FXML
    public void exportButtonClicked(Event e) throws IOException
    {
        Layout layout = this.processor.process(codeTextArea.getText());

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("src"));
        File selectedDirectory = directoryChooser.showDialog(primaryStage);
        String path = selectedDirectory.getAbsolutePath();
        saver.save(layout, path);
    }

    @FXML
    public void viewButtonClicked(Event e){
        settingsVBox.setVisible(false);
        settingsVBox.setDisable(true);
        codeTextArea.setDisable(false);
        codeTextArea.setVisible(true);
    }

    public Color getTerminalBackgroundColor(){
        return terminalBackColorPicker.getValue();
    }

    public Color getTerminalLineColor(){
        return terminalLineColorPicker.getValue();
    }

    public String getTerminalText(){
        return terminalTextField.getText();
    }

    public String getTerminalLineText(){
        return terminalLineTextField.getText();
    }

    public Color getProcessBackgroundColor(){
        return processBackColorPicker.getValue();
    }

    public Color getProcessLineColor(){
        return processLineColorPicker.getValue();
    }

    public String getProcessText(){
        return processTextField.getText();
    }

    public String getProcessLineText(){
        return processLineTextField.getText();
    }

    public Color getDecisionBackgroundColor(){
        return decisionBackColorPicker.getValue();
    }

    public Color getDecisionLineColor(){
        return decisionLineColorPicker.getValue();
    }

    public String getDecisionText(){
        return decisionTextField.getText();
    }

    public String getDecisionLineText(){
        return decisionLineTextField.getText();
    }

    public Color getFunctionBackgroundColor(){
        return functionBackColorPicker.getValue();
    }

    public Color getFunctionLineColor(){
        return functionLineColorPicker.getValue();
    }

    public String getFunctionText(){
        return functionTextField.getText();
    }

    public String getFunctionLineText(){
        return functionLineTextField.getText();
    }

    public Color getOutputBackgroundColor(){
        return outputBackColorPicker.getValue();
    }

    public Color getOutputLineColor(){
        return outputLineColorPicker.getValue();
    }

    public String getOutputText(){
        return outputTextField.getText();
    }

    public String getOutputLineText(){
        return outputLineTextField.getText();
    }

    class ResizableCanvas extends Canvas {

        public ResizableCanvas() {
            widthProperty().addListener(evt -> draw());
            heightProperty().addListener(evt -> draw());
        }

        private void draw() {
            double width = getWidth();
            double height = getHeight();
        }

        @Override
        public boolean isResizable() {
            return true;
        }

        @Override
        public double prefWidth(double height) {
            return getWidth();
        }

        @Override
        public double prefHeight(double width) {
            return getHeight();
        }
    }
}
