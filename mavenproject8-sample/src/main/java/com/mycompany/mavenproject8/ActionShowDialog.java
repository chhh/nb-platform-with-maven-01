/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject8;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import org.apache.commons.lang3.StringUtils;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Help",
        id = "com.mycompany.mavenproject8.ActionShowDialog"
)
@ActionRegistration(
        iconBase = "com/mycompany/mavenproject8/079-clock.png",
        displayName = "#CTL_ActionShowDialog"
)
@ActionReference(path = "Menu/Help", position = 1600, separatorBefore = 1550)
@Messages("CTL_ActionShowDialog=Show Dialog With Time")
public final class ActionShowDialog implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        String time = " The time is: " + Date.from(Instant.now()) + " ";
        time = StringUtils.center(time, time.length() + 20, "~");
        showDialog(null, new JLabel(time), "Current time", JOptionPane.INFORMATION_MESSAGE);
    } 

    public static void showDialog(Component parent, final Component component, String title, int msgType) {
        JOptionPane.showMessageDialog(parent, wrapInScrollForDialog(component), title, msgType);
    }

    public static JScrollPane wrapInScrollForDialog(Component component) {
        // wrap a scrollpane around the component
        final JScrollPane scrollPane = new JScrollPane(component);
        // make the dialog resizable
        component.addHierarchyListener(e -> {
            Window window = SwingUtilities.getWindowAncestor(component);
            if (window instanceof java.awt.Dialog) {
                Dialog dialog = (Dialog) window;
                if (!dialog.isResizable()) {
                    dialog.setResizable(true);
                }
            }
        });
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        return scrollPane;
    }
}
