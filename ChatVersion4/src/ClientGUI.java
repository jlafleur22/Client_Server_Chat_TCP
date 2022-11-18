import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClientGUI extends JFrame{
    //GUI variables
    private JTextArea history;
    private JScrollPane historyScroll;
    private JTextField msgEntry;
    private JButton sendButton;
    private JPanel clientPanel;

    //Client connection Variables
    private Socket socket;
    private Scanner scanner;
    private PrintWriter printWriter;


    public ClientGUI(){
        createWindow();
        formWindowOpened();
    }

    public void createWindow(){
        setTitle("Client");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 800);
        clientPanel.setBorder(new EmptyBorder(5,5,5,5));
        clientPanel.setLayout(new BorderLayout(0,0));
        setContentPane(clientPanel);

        GridBagLayout gbl_serverPanel = new GridBagLayout();
        gbl_serverPanel.columnWidths = new int[]{900, 50};//Sum 950, must be 990 or less, based on bounds
        gbl_serverPanel.rowHeights = new int[]{740, 35};//Sum 775,must be 790 or less, based on bounds
        gbl_serverPanel.columnWeights = new double[]{1.0,1.0};
        gbl_serverPanel.rowWeights = new double[]{1.0,Double.MIN_VALUE};
        clientPanel.setLayout(gbl_serverPanel);

        GridBagConstraints gbc_history = new GridBagConstraints();
        gbc_history.insets = new Insets(5,5,5,5);
        gbc_history.fill = GridBagConstraints.BOTH;
        gbc_history.gridx = 0;
        gbc_history.gridy = 0;
        gbc_history.gridwidth = 2;
        clientPanel.add(history, gbc_history);

        GridBagConstraints gbc_msgEntry = new GridBagConstraints();
        gbc_msgEntry.insets = new Insets(0,5,5,5);
        gbc_msgEntry.fill = GridBagConstraints.BOTH;
        gbc_msgEntry.gridx = 0;
        gbc_msgEntry.gridy = 1;
        gbc_msgEntry.gridwidth = 1;
        clientPanel.add(msgEntry, gbc_msgEntry);
        msgEntry.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    String message = msgEntry.getText();
                    printWriter.println(message);
                    printWriter.flush();
                    history.append("Client: " + message + "\n");
                    msgEntry.setText("");
                }
            }
        });

        GridBagConstraints gbc_sendButton = new GridBagConstraints();
        gbc_sendButton.insets = new Insets(0,0,5,5);
        gbc_sendButton.fill = GridBagConstraints.BOTH;
        gbc_sendButton.gridx = 1;
        gbc_sendButton.gridy = 1;
        gbc_sendButton.gridwidth = 1;
        sendButton.addActionListener(e -> {
            String message = msgEntry.getText();
            printWriter.println(message);
            printWriter.flush();
            history.append("Client: " + message + "\n");
            msgEntry.setText("");
        });
        clientPanel.add(sendButton, gbc_sendButton);
        //Make it Visible
        setVisible(true);
        //Put focus in text entry
        msgEntry.requestFocusInWindow();
    }

    private void formWindowOpened() {
        try {
            socket = new Socket("localhost", 8192);
            scanner = new Scanner(socket.getInputStream());
            printWriter = new PrintWriter(socket.getOutputStream());

            Thread clientThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while(true){
                        String message = scanner.nextLine();
                        history.append("Server: " + message + "\n");
                    }
                }
            });
            clientThread.start();
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public static void main(String[] args){
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            e.printStackTrace();
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI().setVisible(true);
            }
        });
    }

}
