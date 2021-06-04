package org.autoflowchart.gui;

import javafx.fxml.FXML;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import org.autoflowchart.logic.Drawer;
import org.autoflowchart.logic.Processor;
import org.autoflowchart.logic.Saver;
import org.autoflowchart.objects.CustomizerOptions;
import org.autoflowchart.objects.Layout;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class Controller
{
    public Button aboutButton = null;
    public Button importButton = null;
    public Button flowchartButton = null;
    public Button settingsButton = null;
    public Button exportButton = null;
    public Button viewButton = null;
    public SplitPane splitPane;
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

    public CustomizerOptions customizerOptions = new CustomizerOptions();

    public void initialize ()
    {
        System.out.println("INITIALIZE");
        /*
        Set<Node> set = splitPane.lookupAll(".split-pane-divider");
        System.out.println(set);
        for (Node node : set) {
            node.setVisible(false);
            node.setDisable(true);
            System.out.println("found");
        }*/
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    public void aboutButtonClicked(Event e) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("window_about.fxml"));

        Stage stage = new Stage();
        stage.setTitle("About");
        stage.getIcons().add(new Image("file:resources/images/icon.png"));
        stage.setX(500);
        stage.setY(300);
        stage.setWidth(600);
        stage.setHeight(300);
        Scene scene = new Scene(root, 300.0D, 150.0D);
        stage.setScene(scene);
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
        Layout layout;
        try {
            layout = this.processor.process(codeTextArea.getText());
        } catch (Exception ex) {
            ex.printStackTrace();
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Incorrect code");
            errorAlert.setContentText("There are some errors in your code. Please make sure it compiles and works properly. If you are sure and yet still see this error, please create issue on our repository: https://github.com/Namerif/AutoFlowchart.");
            errorAlert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            errorAlert.showAndWait();
            return;
        }

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
        this.generateOptions();
        try {
            this.drawer.draw(layout, canvas.getGraphicsContext2D(), customizerOptions);
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

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        String path = selectedFile.getAbsolutePath();
        this.generateOptions();
        saver.save(layout, path, customizerOptions);
    }

    @FXML
    public void viewButtonClicked(Event e){
        settingsVBox.setVisible(false);
        settingsVBox.setDisable(true);
        codeTextArea.setDisable(false);
        codeTextArea.setVisible(true);

    }

    public void generateOptions () {
        CustomizerOptions options = customizerOptions;
        options.terminalColor = convert(getTerminalBackgroundColor());
        options.terminalOutlineColor = convert(getTerminalLineColor());
        options.terminalSize = Double.parseDouble(getTerminalText());
        options.processColor = convert(getProcessBackgroundColor());
        options.processOutlineColor = convert(getProcessLineColor());
        options.processSize = Double.parseDouble(getProcessText());
        options.decisionColor = convert(getDecisionBackgroundColor());
        options.decisionOutlineColor = convert(getDecisionLineColor());
        options.decisionSize = Double.parseDouble(getDecisionText());
        options.outputColor = convert(getOutputBackgroundColor());
        options.outputOutlineColor = convert(getOutputLineColor());
        options.outputSize = Double.parseDouble(getOutputText());
    }

    public java.awt.Color convert (Color color)
    {
        int r = (int)(color.getRed() * 255);
        int g = (int)(color.getGreen() * 255);
        int b = (int)(color.getBlue() * 255);
        java.awt.Color newColor = new java.awt.Color(r, g, b);
        return newColor;
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
