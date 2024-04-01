package org.example.mpp_ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.mpp_ui.Service.UserService;

import java.io.IOException;
import java.util.Properties;

public class LoginView {
    private static final Logger logger = LogManager.getLogger();
    private UserService users;
    public void initialize(){
        Properties props=new Properties();
        try {
            props.load(HelloApplication.class.getResourceAsStream("/BD.config"));
        } catch (IOException e) {
            System.out.println("Cannot find bd.config "+e);
        }
        users = new UserService(props);
    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private void onLoginButtonClick() throws IOException {
        logger.traceEntry();
        System.out.println(username.getText() + "|" + password.getText());
        if(users.CheckUser(username.getText(), password.getText())){
            System.out.println("LOGGED IN!");
        }else{
            System.out.println("WRONG PASSOWRD!");
        }
        logger.traceExit();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("user-view.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(fxmlLoader.load()));
        stage.setTitle("User View");
        stage.show();
    }
}
