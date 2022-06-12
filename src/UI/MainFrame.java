package src.UI;

import src.Constants;
import src.Controller.Controller;

import javax.swing.*;
import java.awt.*;

final class UIHelper {
    private UIHelper() {

    }

    public static GridLayout createGridLayout(int rows, int columns) {
        GridLayout gridLayout = new GridLayout(rows, columns);
        gridLayout.setVgap(Constants.GRIDLAYOUT_VERTICAL_GAP);
        gridLayout.setHgap(Constants.GRIDLAYOUT_HORIZONTAL_GAP);
        return gridLayout;
    }
}

public class MainFrame extends JFrame{
    public MainFrame() {
        super(Constants.APP_NAME);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(createTablePanel(), BorderLayout.CENTER);
        // TODO: getContentPane().add(createButtonsPanel(), BorderLayout.EAST);
        pack();
    }
    
    private JPanel createTablePanel() {
        
        JPanel mainPanel = UiHelper.createPanel(
                new BorderLayout(), Constants.COMPONENT_PADDING, Constants.COMPONENT_PADDING, Constants.COMPONENT_PADDING, 0);
        
        //mainPanel.add(new JScrollPane(Controller.getTransactionsTable()), BorderLayout.CENTER);
        
        mainPanel.add(new JScrollPane(new JTable((new String[][]{{"A", "B"},{"C", "D"}}), (new String[]{"A", "B"}))), BorderLayout.CENTER);
        
        return mainPanel;
        
    }

    private JPanel createButtonsPanel() {

        /* TODO: Uncomment this
        JPanel topButtonsPanel = UiHelper.createPanel(UiHelper.createGridLayout(4,1));
        
        topButtonsPanel.add(UiHelper.createButton("Add Entry", new AddEntryButtonActionListener()));
        topButtonsPanel.add(UiHelper.createButton("Delete Entries", new DeleteEntriesButtonActionListener()));
        topButtonsPanel.add(UiHelper.createButton("Delete All Entries", new DeleteAllEntriesButtonActionListener()));
        topButtonsPanel.add(UiHelper.createButton("Sort Entries", new SortEntriesButtonActionListener()));

        JPanel bottomButtonPanel = UiHelper.createPanel(UiHelper.createGridLayout(1,1));
        bottomButtonPanel.add(UiHelper.createButton("Exit", new ExitButtonActionListener()));
        */
        JPanel mainPanel = new JPanel(new BorderLayout());
        /* TODO: Uncomment this
        mainPanel.add(topButtonsPanel, BorderLayout.NORTH);
        mainPanel.add(bottomButtonPanel, BorderLayout.SOUTH);
        */
        return mainPanel;
        
    }
}
