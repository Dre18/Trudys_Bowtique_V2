package APP.NotificationsandEvents;

import java.awt.*;
import java.awt.TrayIcon.MessageType;

public class Notification{

    
    /** 
     * @throws AWTException
     */
    public void displayTray(){
        try{

             //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        // if()

        trayIcon.displayMessage("Trudy's Bowtique Store Manager", "ALERT Stock is below 50% \n"
       +"Please address the Stock Management System", MessageType.INFO);

        }
        catch(Exception i)
        {}

       
    }
}

