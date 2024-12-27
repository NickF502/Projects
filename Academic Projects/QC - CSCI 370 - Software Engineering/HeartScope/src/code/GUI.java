package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Nicholas Farkash
 */


//FIXME entire class
// ensure use enters correct 
public class GUI {

    private JFrame frame;
    private JPanel panel;
    private JLabel questionLabel;
    private JLabel statusLabel;
    private JTextField responseField;
    private JButton nextButton;

    private String[] responses;
    private int currentQuestionIndex;
    private String[] questions;

    public GUI() {
    
        questions = new String[]{
            "1) What is your Age? (Enter an integer value)",
            "2) What is your Sex? (Enter 'M' for Male, 'F' for Female",
            "3) Which type of Chest Pain have you been diagnosed with? (ATA, NAP, ASY, TA)",
            "4) What is your Resting Blood Pressure? (Enter an integer value)",
            "5) What is your Cholesterol? (Enter an integer value)",
            "6) Is your Fasting Blood Sugar greater than 120 mg/dl? (Enter 1 if Yes, 0 if No)",
            "7) What are your Resting Electrocardiogram results? (Normal, ST, LVH)",
            "8) What is your Max Heart Rate (Enter an integer value)",
            "9) Do you experience exercise-induced angina? (Y/N)",
            "10) What is your Oldpeak value? (Enter a decimal number)",
            "11) What is the ST Slope on your ECG? (Down, Flat, Up)",
            "12) Would you say that you are at risk of Heart Disease? (Enter 1 if Yes, 0 if No)"
        };

        responses = new String[questions.length];
        currentQuestionIndex = 0;

        frame = new JFrame("Questionnaire");
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1)); // Adjust layout for better spacing

        questionLabel = new JLabel(questions[currentQuestionIndex]);
        responseField = new JTextField(20);
        nextButton = new JButton("Next");
        statusLabel = new JLabel("", JLabel.CENTER);

        panel.add(questionLabel);
        panel.add(responseField);
        panel.add(nextButton);
        panel.add(statusLabel);

        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleNextButton();
            }
        });

        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 450);
        frame.setVisible(true);
    }

    public String[] getUserResponses() {
        // Wait for the user to finish all responses
        synchronized (this) {
            while (currentQuestionIndex < questions.length) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return responses;
    }

    public void showMessage(String message) {
        SwingUtilities.invokeLater(() -> statusLabel.setText(message));
    }

    public void displayResults(int prediction, UserData user, UserData averageValues) {
        SwingUtilities.invokeLater(() -> {
            frame.dispose();
            JFrame resultFrame = new JFrame("Results");
            JTextArea resultArea = new JTextArea("Prediction: " + prediction + "\nUser: " + user + "\nAverage Values: " + averageValues);
            resultArea.setEditable(false);
            resultFrame.add(new JScrollPane(resultArea));
            resultFrame.setSize(800, 600);
            resultFrame.setVisible(true);
        });
    }

    private void handleNextButton() {
        responses[currentQuestionIndex] = responseField.getText();
        responseField.setText("");
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.length) {
            questionLabel.setText(questions[currentQuestionIndex]);
        } else {
            synchronized (this) {
                notifyAll();
            }
        }
    }
}

