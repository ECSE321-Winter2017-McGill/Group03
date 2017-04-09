package ca.mcgill.ecse321.TAMAS.view;

import java.awt.Dimension;
import javax.swing.*;

public class FinalAllocationReminder extends JPanel
{
    private JScrollPane vertical;
    private JTextArea console;

    public FinalAllocationReminder()
    {
        setPreferredSize(new Dimension(200, 250));
        console = new JTextArea(15, 15);

        vertical = new JScrollPane(console);
        vertical.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(vertical);
        

		setVisible(true);

        
    }
    
}