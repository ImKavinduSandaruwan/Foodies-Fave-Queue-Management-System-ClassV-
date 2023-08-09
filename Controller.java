package com.example.classversion_20221150;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.classversion_20221150.Main.allQueues;
import static com.example.classversion_20221150.Main.waitingList;


public class Controller {
    @FXML
    private Button viewQueuesButton;
    @FXML
    private TextArea showQueueLabel;
    @FXML
    private Button goBackButton;
    @FXML
    private Button quitButton;
    @FXML
    private Button customerDetailsButton;
    @FXML
    private TextArea customerDetailsTextArea;
    @FXML
    private Button searchCustomerButton;
    @FXML
    private TextField searchLabel;
    @FXML
    private TextArea detailsTextField;

    /**
     * This method is for display all the queues.
     */
    @FXML
    public void handleViewQueues() throws IOException {
        Stage stage = (Stage) viewQueuesButton.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("secondWindow.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("View Queue");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This method display queues.
     */
    @FXML
    public void showQueue(){
        StringBuilder sb = new StringBuilder();
        sb.append("""
               \n *****************
                *    Cashiers   *
                *****************
                """);

        for(int i = 0; i < allQueues[2].getLength(); i++){
            for(FoodQueue queue : allQueues){
                try {
                    if (queue.getValue(i) == null) {
                        sb.append("   X  ");
                    } else {
                        sb.append("   0  ");
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    sb.append("      ");
                }
            }
            sb.append("\n");
        }
        showQueueLabel.setText(sb.toString());
    }

    /**
     * This method is for go back to the main window.
     */
    @FXML
    public void handleGoBack() throws IOException{
        Stage stage = (Stage) goBackButton.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("mainWindow.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Main window");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This method is for quit from the program.
     */
    @FXML
    public void handleQuit(){
        Stage stage = (Stage) quitButton.getScene().getWindow();
        stage.close();
    }

    /**
     * this method open new window to show customer details
     */
    @FXML
    public void handleCustomerDetails() throws IOException{
        Stage stage = (Stage) customerDetailsButton.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("thirdWindow.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("View Queue");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This method display waiting queue details.
     */
    @FXML
    public void showCustomerDetails() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < waitingList.getLength(); i++) {
            if(waitingList.get(i) != null) {
                sb.append("First name:- ").append(waitingList.get(i).getFirstName()).append(" ");
                sb.append("Last name:- ").append(waitingList.get(i).getLastName()).append(" ");
                sb.append("Burger amount:- ").append(waitingList.get(i).getBurgerAmount()).append("\n");
            }
        }
        customerDetailsTextArea.setText(sb.toString());
    }

    /**
     *this method opens a new window to search customer details
     */
    @FXML
    public void searchCustomers() throws IOException{
        Stage stage = (Stage) searchCustomerButton.getScene().getWindow();
        stage.close();

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("fifthWindow.fxml"));
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setTitle("Search Details");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * this method is for search customer details and display it.
     */
    @FXML
    public void showSearchCustomerDetails(){
        String searchName = searchLabel.getText();
        ArrayList<Customer> customerDetailsArray = new ArrayList<>();
        for(var queue : allQueues){
            for(int i = 0; i < queue.getLength(); i++){
                if(queue.getValue(i) != null){
                    customerDetailsArray.add(queue.getValue(i));
                }
            }
        }
        for (Customer customer : customerDetailsArray) {
            if (customer.getFirstName().equals(searchName)) {
                detailsTextField.setText("First name: " + customer.getFirstName() + "\n"
                        + "Last name: " + customer.getLastName() + "\n" +
                        "Burger Amount: " + customer.getBurgerAmount());
            }
        }
    }
}