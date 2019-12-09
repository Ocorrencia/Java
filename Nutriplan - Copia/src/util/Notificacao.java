/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.alee.managers.notification.NotificationIcon;
import com.alee.managers.notification.NotificationManager;
import java.awt.Font;
import javax.swing.JLabel;

public class Notificacao {

    static JLabel infMessage = new JLabel();

    public static void infoBox(String infoMessage, boolean sucess) {

        infMessage.setFont(new Font("Arial", Font.BOLD, 30));
        infMessage.setText(infoMessage);
        NotificationManager.setLocation(NotificationManager.LEFT);
        if (sucess) {
            NotificationManager.showInnerNotification(infMessage, NotificationIcon.information.getIcon());
        } else {
            NotificationManager.showInnerNotification(infMessage, NotificationIcon.error.getIcon());
        }
        /*   new Thread() {
            @Override
            public void run() {
                final WebClock clock = new WebClock();
                clock.setClockType(ClockType.timer);
                clock.setTimeLeft(3000);
                clock.start();
                while (true) {
                    if (clock.getTimeLeft() <= 0) {
                        NotificationManager.hideAllNotifications();
                        break;
                    } else {
                        NotificationManager.updateNotificationLayouts();
                    }
                }
            }
        }.start();*/
    }
}
