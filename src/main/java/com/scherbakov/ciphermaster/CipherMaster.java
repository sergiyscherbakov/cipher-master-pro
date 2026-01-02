package com.scherbakov.ciphermaster;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class CipherMaster extends Application {

    private static final String UKR_ALPHABET = "абвгґдеєжзиіїйклмнопрстуфхцчшщьюя";
    private static final String ENG_ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    private TextArea inputArea;
    private TextArea outputArea;
    private ComboBox<String> methodBox;
    private ToggleGroup modeGroup;
    private Slider caesarSlider;
    private TextField vigenereKeyField;
    private Label settingsLabel;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CipherMaster Pro - Java Edition");

        // UI Components
        inputArea = new TextArea();
        inputArea.setPromptText("Введіть текст тут...");
        inputArea.setPrefHeight(150);

        outputArea = new TextArea();
        outputArea.setPromptText("Результат з'явиться тут...");
        outputArea.setEditable(false);
        outputArea.setPrefHeight(150);

        methodBox = new ComboBox<>();
        methodBox.getItems().addAll("Шифр Цезаря", "Шифр Віженера", "Атбаш (Дзеркальний)");
        methodBox.setValue("Шифр Цезаря");

        modeGroup = new ToggleGroup();
        RadioButton encryptBtn = new RadioButton("Зашифрувати");
        encryptBtn.setToggleGroup(modeGroup);
        encryptBtn.setSelected(true);
        RadioButton decryptBtn = new RadioButton("Дешифрувати");
        decryptBtn.setToggleGroup(modeGroup);

        caesarSlider = new Slider(1, 32, 3);
        caesarSlider.setShowTickLabels(true);
        caesarSlider.setShowTickMarks(true);

        vigenereKeyField = new TextField("КЛЮЧ");
        vigenereKeyField.setVisible(false);

        settingsLabel = new Label("Зсув (Цезар):");

        Button processBtn = new Button("ВИКОНАТИ");
        processBtn.setMaxWidth(Double.MAX_VALUE);
        processBtn.setStyle("-fx-background-color: #4f46e5; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10;");

        // Logic for showing/hiding settings
        methodBox.setOnAction(e -> {
            String selected = methodBox.getValue();
            if (selected.equals("Шифр Цезаря")) {
                caesarSlider.setVisible(true);
                vigenereKeyField.setVisible(false);
                settingsLabel.setText("Зсув (Цезар):");
            } else if (selected.equals("Шифр Віженера")) {
                caesarSlider.setVisible(false);
                vigenereKeyField.setVisible(true);
                settingsLabel.setText("Ключове слово:");
            } else {
                caesarSlider.setVisible(false);
                vigenereKeyField.setVisible(false);
                settingsLabel.setText("Атбаш не потребує додаткових налаштувань.");
            }
        });

        processBtn.setOnAction(e -> processText());

        // Layout
        VBox root = new VBox(15);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f1f5f9;");

        HBox modeBox = new HBox(20, encryptBtn, decryptBtn);
        modeBox.setAlignment(Pos.CENTER);

        VBox settingsContainer = new VBox(5, settingsLabel, caesarSlider, vigenereKeyField);
        settingsContainer.setPadding(new Insets(10));
        settingsContainer.setStyle("-fx-background-color: #e2e8f0; -fx-background-radius: 10;");

        root.getChildren().addAll(
                new Label("Вхідний текст:"), inputArea,
                new Label("Оберіть метод:"), methodBox,
                modeBox,
                settingsContainer,
                processBtn,
                new Label("Результат:"), outputArea
        );

        Scene scene = new Scene(root, 500, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void processText() {
        String text = inputArea.getText();
        if (text == null || text.isEmpty()) return;

        boolean isDecrypt = ((RadioButton) modeGroup.getSelectedToggle()).getText().equals("Дешифрувати");
        String result = "";
        String method = methodBox.getValue();

        if (method.equals("Шифр Цезаря")) {
            int shift = (int) caesarSlider.getValue();
            result = caesarCipher(text, isDecrypt ? -shift : shift);
        } else if (method.equals("Шифр Віженера")) {
            result = vigenereCipher(text, vigenereKeyField.getText(), isDecrypt);
        } else {
            result = atbashCipher(text);
        }

        outputArea.setText(result);
    }

    // --- CIPHER LOGIC ---

    private String caesarCipher(String text, int shift) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            sb.append(shiftChar(c, shift));
        }
        return sb.toString();
    }

    private char shiftChar(char c, int shift) {
        boolean isUpper = Character.isUpperCase(c);
        char lower = Character.toLowerCase(c);
        String alphabet = "";

        if (ENG_ALPHABET.indexOf(lower) != -1) alphabet = ENG_ALPHABET;
        else if (UKR_ALPHABET.indexOf(lower) != -1) alphabet = UKR_ALPHABET;
        else return c;

        int oldIdx = alphabet.indexOf(lower);
        int newIdx = (oldIdx + shift) % alphabet.length();
        if (newIdx < 0) newIdx += alphabet.length();

        char res = alphabet.charAt(newIdx);
        return isUpper ? Character.toUpperCase(res) : res;
    }

    private String vigenereCipher(String text, String key, boolean decrypt) {
        if (key == null || key.isEmpty()) return text;
        StringBuilder sb = new StringBuilder();
        String keyLower = key.toLowerCase();
        int keyIdx = 0;

        for (char c : text.toCharArray()) {
            boolean isUpper = Character.isUpperCase(c);
            char lower = Character.toLowerCase(c);
            String alphabet = "";

            if (ENG_ALPHABET.indexOf(lower) != -1) alphabet = ENG_ALPHABET;
            else if (UKR_ALPHABET.indexOf(lower) != -1) alphabet = UKR_ALPHABET;
            else {
                sb.append(c);
                continue;
            }

            char kChar = keyLower.charAt(keyIdx % keyLower.length());
            int kShift = alphabet.indexOf(kChar);
            if (kShift == -1) kShift = 0;

            int actualShift = decrypt ? -kShift : kShift;
            sb.append(shiftChar(c, actualShift));
            keyIdx++;
        }
        return sb.toString();
    }

    private String atbashCipher(String text) {
        StringBuilder sb = new StringBuilder();
        for (char c : text.toCharArray()) {
            boolean isUpper = Character.isUpperCase(c);
            char lower = Character.toLowerCase(c);
            String alphabet = "";

            if (ENG_ALPHABET.indexOf(lower) != -1) alphabet = ENG_ALPHABET;
            else if (UKR_ALPHABET.indexOf(lower) != -1) alphabet = UKR_ALPHABET;
            else {
                sb.append(c);
                continue;
            }

            int idx = alphabet.indexOf(lower);
            int mirrorIdx = alphabet.length() - 1 - idx;
            char res = alphabet.charAt(mirrorIdx);
            sb.append(isUpper ? Character.toUpperCase(res) : res);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
