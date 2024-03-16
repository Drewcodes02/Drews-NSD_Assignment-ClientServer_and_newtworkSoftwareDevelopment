package Assigment.client.GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;
import Assigment.utils.Utils;
import Assigment.utils.enums.Config;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;


public class GUI extends Application {

    private Stage window;
    private GUIClient client;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.client = new GUIClient();
        window = stage;
        window.setResizable(false);
        window.setOnCloseRequest(e -> {
            e.consume();
            try {
                this.onClose();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        window.setScene(new Scene(this.loginPage(), Config.LOGIN_WIDTH.get(), Config.LOGIN_HEIGHT.get()));
        window.show();
    }

    private void onClose() throws IOException {
        this.client.logout();
        window.close();
        System.exit(0);
    }

    private TilePane mainPage() throws IOException {

        window.setTitle("Logged in as: " + client.getName() + " | Chat with " + client.getCurrentChannel());


        ListView<String> listView = new ListView<>();
        listView.setMinHeight(400);
        listView.setMinWidth(1280);

        MessageListener listener = new MessageListener(client, listView);
        Thread test = new Thread(listener);
        test.start();

        TilePane tilePane = new TilePane();
        tilePane.getChildren().add(listView);

        VBox container = new VBox();
        container.setSpacing(20);

        VBox sendMessageBox = new VBox();
        HBox messages = new HBox();
        sendMessageBox.setSpacing(5);
        messages.setSpacing(5);

        Label message = new Label();
        message.setText("Message");
        message.setId("sendMessage");
        message.setPadding(new Insets(9, 0, 0, 5));

        TextField messageField = new TextField();
        messageField.setPromptText("Enter Message");
        messageField.setId("sendMessage");
        messageField.setMinHeight(36);
        messageField.setMinWidth(200);

        messages.getChildren().addAll(message, messageField);

        Button sendMessageButton = new Button();
        sendMessageButton.setText("Send Message");
        sendMessageButton.setId("sendMessage");

        HBox centerButton = new HBox();
        centerButton.setSpacing(5);
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.mp4", "*.mov"));
        Button imageButton = new Button("Send With Picture");
        imageButton.setId("sendMessage");
        centerButton.setAlignment(Pos.CENTER);
        centerButton.getChildren().addAll(sendMessageButton, imageButton);
        centerButton.setMaxWidth(300);

        sendMessageBox.getChildren().addAll(messages, centerButton);

        VBox biggerSubBox = new VBox();
        HBox subscribeBox = new HBox();
        biggerSubBox.setSpacing(5);
        subscribeBox.setSpacing(5);

        Label username = new Label();
        username.setText("Username");
        username.setId("sendMessage");
        username.setPadding(new Insets(9, 0, 0, 5));

        TextField name = new TextField();
        name.setPromptText("Enter Message");
        name.setId("sendMessage");
        name.setMinHeight(36);
        name.setMinWidth(200);

        HBox alignSubButton = new HBox();
        Button subButton = new Button("Subscribe");
        Button unsubButton = new Button("Un-Subscribe");
        alignSubButton.setSpacing(5);
        alignSubButton.setMaxWidth(300);
        alignSubButton.setAlignment(Pos.CENTER);
        alignSubButton.setId("sendMessage");
        alignSubButton.getChildren().addAll(subButton, unsubButton);

        HBox alignError = new HBox();
        alignError.setSpacing(5);
        Text errorMessage = new Text();
        errorMessage.setId("errorMessage");
        errorMessage.setFill(Color.rgb(171, 37, 4));
        alignError.setAlignment(Pos.CENTER);
        alignError.setMaxWidth(300);
        alignError.getChildren().addAll(errorMessage);
        subscribeBox.getChildren().addAll(username, name);
        biggerSubBox.getChildren().addAll(subscribeBox, alignSubButton, alignError);

        VBox logoutBox = new VBox();
        HBox hLogoutBox = new HBox();
        logoutBox.setSpacing(5);
        hLogoutBox.setSpacing(5);

        Button logoutButton = new Button("Exit");
        logoutButton.setId("sendMessage");
        hLogoutBox.setMaxWidth(300);
        hLogoutBox.setAlignment(Pos.CENTER);
        hLogoutBox.getChildren().addAll(logoutButton);
        logoutBox.getChildren().addAll(hLogoutBox);

        VBox searchBox = new VBox();
        HBox searchHBox = new HBox();
        searchBox.setSpacing(5);
        searchHBox.setSpacing(5);

        Label search = new Label();
        search.setText("Search");
        search.setId("sendMessage");
        search.setPadding(new Insets(9, 0, 0, 5));

        TextField searchField = new TextField();
        searchField.setPromptText("Enter Words");
        searchField.setId("sendMessage");
        searchField.setMinHeight(36);
        searchField.setMinWidth(200);

        Button searchButton = new Button("Search");
        Button stopSearchButton = new Button("Stop searching");

        HBox alignSearchButton = new HBox();
        alignSearchButton.setMaxWidth(300);
        alignSearchButton.setAlignment(Pos.CENTER);
        alignSearchButton.setSpacing(5);
        alignSearchButton.getChildren().addAll(searchButton, stopSearchButton);

        searchButton.setId("sendMessage");
        stopSearchButton.setId("sendMessage");
        searchHBox.setMaxWidth(300);
        searchHBox.getChildren().addAll(search, searchField);
        searchBox.getChildren().addAll(searchHBox, alignSearchButton);

        container.getChildren().addAll(sendMessageBox, biggerSubBox, searchBox, logoutBox);
        tilePane.getChildren().addAll(container);

        sendMessageButton.setOnAction(e -> {
            String temp = messageField.getText().strip();
            try {
                client.sendMessage(temp, null);
                messageField.clear();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });

        imageButton.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                String temp = messageField.getText().strip();
                try {
                    client.sendMessage(temp, Utils.encode(file));
                    messageField.clear();
                    Scene mainScene = new Scene(this.mainPage(), Config.MAIN_WIDTH.get(), Config.MAIN_HEIGHT.get());
                    mainScene.getStylesheets().add("style.css");
                    window.setScene(mainScene);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        subButton.setOnAction(e -> {
            String temp = name.getText().strip();
            if (name.getText().isEmpty() || temp.equalsIgnoreCase("")) {
                errorMessage.setText("Username can't be blank");
            } else if (temp.split(" ").length > 1) {
                errorMessage.setText("Username can't have spaces");
            } else {
                try {
                    client.subscribe(temp);
                    Scene mainScene = new Scene(this.mainPage(), Config.MAIN_WIDTH.get(), Config.MAIN_HEIGHT.get());
                    mainScene.getStylesheets().add("style.css");
                    window.setScene(mainScene);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        unsubButton.setOnAction(e -> {
            try {
                client.unsubscribe();
                Scene mainScene = new Scene(this.mainPage(), Config.MAIN_WIDTH.get(), Config.MAIN_HEIGHT.get());
                mainScene.getStylesheets().add("style.css");
                window.setScene(mainScene);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        logoutButton.setOnAction(e -> {
            try {
                this.onClose();
            } catch (Exception ignored) {
            }
        });


        searchButton.setOnAction(e -> {
            String temp = searchField.getText().strip();
            try {
                test.interrupt();
                listView.getItems().clear();
                messageField.setDisable(true);
                sendMessageButton.setDisable(true);
                imageButton.setDisable(true);
                subButton.setDisable(true);
                unsubButton.setDisable(true);
                name.setDisable(true);

                JSONArray result = client.search(temp);
                HashMap<Integer, String> imagePlaces = new HashMap<>();
                int count = 0;
                for (Object object : result) {
                    JSONObject t = (JSONObject) object;
                    String from = t.getString("from");
                    String body = t.getString("body");
                    listView.getItems().add(from + ": " + body);
                    try {
                        String text = t.getString("pic");
                        imagePlaces.put(count, text);
                    } catch (Exception ignored) {
                    }
                    count++;

                }
                listView.setCellFactory(p -> new ListCell<>() {
                    public final ImageView imageView = new ImageView();

                    @Override
                    public void updateItem(String name, boolean empty) {
                        super.updateItem(name, empty);
                        if (empty) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(name);
                            var t = imagePlaces.get(super.getIndex());
                            if (t != null) {
                                try {
                                    imageView.setImage(Utils.decodeImage(t));
                                    imageView.setFitWidth(Config.PICTURE_WIDTH.get());
                                    imageView.setPreserveRatio(true);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                setGraphic(imageView);
                            }
                        }
                    }
                });
                searchField.clear();
            } catch (Exception exception) {
                exception.printStackTrace();
            }

        });

        stopSearchButton.setOnAction(e -> {

            try {
                Scene mainScene = new Scene(this.mainPage(), Config.MAIN_WIDTH.get(), Config.MAIN_HEIGHT.get());
                mainScene.getStylesheets().add("style.css");
                window.setScene(mainScene);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });

        return tilePane;
    }

    private VBox loginPage() {

        window.setTitle("Login!");

        Label welcome = new Label("Login!");
        Label name = new Label("Name:");
        TextField username = new TextField();
        Button submitButton = new Button();
        final Text errorMessage = new Text();

        HBox alignWelcome = new HBox();
        alignWelcome.setAlignment(Pos.CENTER);
        welcome.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        alignWelcome.getChildren().add(welcome);

        HBox alignBox = new HBox();
        name.setPadding(new Insets(4, 0, 0, 0));
        alignBox.setAlignment(Pos.CENTER);
        alignBox.getChildren().addAll(name, username);
        alignBox.setSpacing(10);

        HBox alignButton = new HBox();
        submitButton.setText("Submit");
        alignButton.setAlignment(Pos.CENTER);
        alignButton.setPadding(new Insets(10, 0, 0, 0));
        alignButton.getChildren().addAll(submitButton);

        HBox alignError = new HBox();
        errorMessage.setFill(Color.rgb(171, 37, 4));
        alignError.setAlignment(Pos.CENTER);
        alignError.getChildren().add(errorMessage);

        VBox vb = new VBox();
        vb.setPadding(new Insets(30));
        vb.getChildren().addAll(alignWelcome, alignBox, alignButton, alignError);
        vb.setSpacing(10);

        submitButton.setOnAction(e -> {
            String temp = username.getText().strip();
            if (username.getText().isEmpty() || temp.equalsIgnoreCase("")) {
                errorMessage.setText("Username can't be blank");
            } else if (temp.split(" ").length > 1) {
                errorMessage.setText("Username can't have spaces");
            } else {
                JSONObject object = new JSONObject();
                object.put("_class", "OpenRequest");
                object.put("identity", temp.toLowerCase());
                boolean loggedIn = false;
                try {
                    loggedIn = client.login(object);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                if (!loggedIn) {
                    errorMessage.setText("Sorry that user is already signed in");
                } else {
                    try {
                        Scene mainScene = new Scene(this.mainPage(), Config.MAIN_WIDTH.get(), Config.MAIN_HEIGHT.get());
                        mainScene.getStylesheets().add("style.css");
                        window.setScene(mainScene);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }
            }
        });
        return vb;
    }
}
