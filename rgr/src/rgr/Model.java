package rgr;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTabbedPane;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.Insets;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextArea;
import widgets.ChooseRandom;
import widgets.ChooseData;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import widgets.Diagram;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import widgets.stat.StatisticsManager;
import java.awt.CardLayout;
import widgets.trans.TransProcessManager;
import widgets.regres.RegresAnaliser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class Model {

	private JFrame frame;
	private JPanel panl_test;
	private JPanel panel_tz;
	private JPanel panel_2;
	private ChooseRandom chooseRandom;
	private ChooseRandom chooseRandom_1;
	private ChooseRandom chooseRandom_2;
	private ChooseData chooseData;
	private ChooseData chooseData_1;
	private ChooseData chooseData_2;
	private JPanel Start;
	private JScrollPane scrollPane_1;
	private Diagram diagram;
	private Diagram diagram_1;
	private Diagram diagram_2;
	private JButton btnNewButton_1;
	private JTextPane textPane;
	private JCheckBox chckbxNewCheckBox;
	private StatisticsManager statisticsManager;
	private JPanel panel_1;
	private JPanel panel_3;
	private TransProcessManager transProcessManager;
	private ChooseData chooseData_3;
	private ChooseData chooseData_4;
	private ChooseData chooseData_5;
	private JPanel panel;
	private Diagram diagram_3;
	private RegresAnaliser regresAnaliser;
	private ChooseData chooseData_6;
	private JComboBox comboBox;
	private JButton btnNewButton;
	private JButton btnNewButton_2;
	private JCheckBox chckbxNewCheckBox_1;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public Area area;
	public Avto avto;
	public Barge barge;
	public BargeGenerator barGen;
	public Conteiner conteiner;
	public Workers workers; 
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Model window = new Model();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Model() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 840, 537);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{200, 79, 434, 0};
		gridBagLayout.rowHeights = new int[]{261, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 3;
		gbc_panel_2.insets = new Insets(0, 0, 5, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		frame.getContentPane().add(panel_2, gbc_panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{300, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_2.setLayout(gbl_panel_2);
		
		chooseRandom = new ChooseRandom();
		chooseRandom.setTitle("\u0418\u043D\u0442\u0435\u0440\u0432\u0430\u043B \u0447\u0430\u0441\u0443 \u043C\u0456\u0436 \u043F\u043E\u044F\u0432\u0430\u043C\u0438 \u0431\u0430\u0440\u0436");
		GridBagConstraints gbc_chooseRandom = new GridBagConstraints();
		gbc_chooseRandom.anchor = GridBagConstraints.NORTH;
		gbc_chooseRandom.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseRandom.insets = new Insets(0, 0, 5, 0);
		gbc_chooseRandom.gridx = 0;
		gbc_chooseRandom.gridy = 0;
		panel_2.add(chooseRandom, gbc_chooseRandom);
		
		chooseRandom_1 = new ChooseRandom();
		chooseRandom_1.setTitle("\u0427\u0430\u0441 \u043F\u0435\u0440\u0435\u0431\u0443\u0432\u0430\u043D\u043D\u044F \u0430\u0432\u0442\u043E \u0432 \u0434\u043E\u0440\u043E\u0437\u0456");
		GridBagConstraints gbc_chooseRandom_1 = new GridBagConstraints();
		gbc_chooseRandom_1.anchor = GridBagConstraints.NORTH;
		gbc_chooseRandom_1.insets = new Insets(0, 0, 5, 0);
		gbc_chooseRandom_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseRandom_1.gridx = 0;
		gbc_chooseRandom_1.gridy = 1;
		panel_2.add(chooseRandom_1, gbc_chooseRandom_1);
		
		chooseRandom_2 = new ChooseRandom();
		chooseRandom_2.setTitle("\u0427\u0430\u0441 \u043D\u0430 \u0437\u0430\u0432\u0430\u043D\u0442\u0430\u0436\u0435\u043D\u043D\u044F \u0430\u0432\u0442\u043E");
		GridBagConstraints gbc_chooseRandom_2 = new GridBagConstraints();
		gbc_chooseRandom_2.anchor = GridBagConstraints.NORTH;
		gbc_chooseRandom_2.insets = new Insets(0, 0, 5, 0);
		gbc_chooseRandom_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseRandom_2.gridx = 0;
		gbc_chooseRandom_2.gridy = 2;
		panel_2.add(chooseRandom_2, gbc_chooseRandom_2);
		
		chooseData = new ChooseData();
		chooseData.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u043A\u043E\u043D\u0442\u0435\u0439\u043D\u0435\u0440\u0456\u0432 \u0432 \u0431\u0430\u0440\u0436\u0456");
		GridBagConstraints gbc_chooseData = new GridBagConstraints();
		gbc_chooseData.anchor = GridBagConstraints.NORTH;
		gbc_chooseData.insets = new Insets(0, 0, 5, 0);
		gbc_chooseData.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData.gridx = 0;
		gbc_chooseData.gridy = 3;
		panel_2.add(chooseData, gbc_chooseData);
		
		chooseData_1 = new ChooseData();
		chooseData_1.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0431\u0440\u0438\u043D\u0430\u0434");
		GridBagConstraints gbc_chooseData_1 = new GridBagConstraints();
		gbc_chooseData_1.anchor = GridBagConstraints.NORTH;
		gbc_chooseData_1.insets = new Insets(0, 0, 5, 0);
		gbc_chooseData_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData_1.gridx = 0;
		gbc_chooseData_1.gridy = 4;
		panel_2.add(chooseData_1, gbc_chooseData_1);
		
		chooseData_2 = new ChooseData();
		chooseData_2.setTitle("\u0420\u043E\u0437\u043C\u0456\u0440 \u043F\u043B\u043E\u0449\u0430\u0434\u043A\u0438");
		GridBagConstraints gbc_chooseData_2 = new GridBagConstraints();
		gbc_chooseData_2.insets = new Insets(0, 0, 5, 0);
		gbc_chooseData_2.anchor = GridBagConstraints.NORTH;
		gbc_chooseData_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData_2.gridx = 0;
		gbc_chooseData_2.gridy = 5;
		panel_2.add(chooseData_2, gbc_chooseData_2);
		
		chooseData_3 = new ChooseData();
		chooseData_3.setTitle("\u041A\u0456\u043B\u044C\u043A\u0456\u0441\u0442\u044C \u0430\u0432\u0442\u043E");
		GridBagConstraints gbc_chooseData_3 = new GridBagConstraints();
		gbc_chooseData_3.insets = new Insets(0, 0, 5, 0);
		gbc_chooseData_3.anchor = GridBagConstraints.NORTH;
		gbc_chooseData_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData_3.gridx = 0;
		gbc_chooseData_3.gridy = 6;
		panel_2.add(chooseData_3, gbc_chooseData_3);
		
		chooseData_4 = new ChooseData();
		chooseData_4.setTitle("\u041C\u0456\u0441\u0442\u043A\u0456\u0441\u0442\u044C \u0430\u0432\u0442\u043E");
		GridBagConstraints gbc_chooseData_4 = new GridBagConstraints();
		gbc_chooseData_4.anchor = GridBagConstraints.NORTH;
		gbc_chooseData_4.insets = new Insets(0, 0, 5, 0);
		gbc_chooseData_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData_4.gridx = 0;
		gbc_chooseData_4.gridy = 7;
		panel_2.add(chooseData_4, gbc_chooseData_4);
		
		chooseData_5 = new ChooseData();
		chooseData_5.setTitle("\u0427\u0430\u0441 \u043C\u043E\u0434\u0435\u043B\u044E\u0432\u0430\u043D\u043D\u044F");
		GridBagConstraints gbc_chooseData_5 = new GridBagConstraints();
		gbc_chooseData_5.anchor = GridBagConstraints.NORTH;
		gbc_chooseData_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData_5.gridx = 0;
		gbc_chooseData_5.gridy = 8;
		panel_2.add(chooseData_5, gbc_chooseData_5);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.gridwidth = 2;
		gbc_tabbedPane.gridheight = 3;
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.gridx = 1;
		gbc_tabbedPane.gridy = 0;
		frame.getContentPane().add(tabbedPane, gbc_tabbedPane);
		
		panel_tz = new JPanel();
		tabbedPane.addTab("\u0422\u0437", null, panel_tz, null);
		panel_tz.setLayout(new BoxLayout(panel_tz, BoxLayout.X_AXIS));
		
		scrollPane_1 = new JScrollPane();
		panel_tz.add(scrollPane_1);
		
		textPane = new JTextPane();
		textPane.setText("\u0448\u0448\u0448\u0448\u0448\u0448");
		scrollPane_1.setViewportView(textPane);
		
		panl_test = new JPanel();
		tabbedPane.addTab("Test", null, panl_test, null);
		GridBagLayout gbl_panl_test = new GridBagLayout();
		gbl_panl_test.columnWidths = new int[]{166, 0, 0};
		gbl_panl_test.rowHeights = new int[]{0, 0, 0, 0, 0};
		gbl_panl_test.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panl_test.rowWeights = new double[]{1.0, 1.0, 1.0, 0.0, Double.MIN_VALUE};
		panl_test.setLayout(gbl_panl_test);
		
		diagram = new Diagram();
		GridBagConstraints gbc_diagram = new GridBagConstraints();
		gbc_diagram.gridwidth = 2;
		gbc_diagram.insets = new Insets(0, 0, 5, 0);
		gbc_diagram.fill = GridBagConstraints.BOTH;
		gbc_diagram.gridx = 0;
		gbc_diagram.gridy = 0;
		panl_test.add(diagram, gbc_diagram);
		
		diagram_1 = new Diagram();
		GridBagConstraints gbc_diagram_1 = new GridBagConstraints();
		gbc_diagram_1.gridwidth = 2;
		gbc_diagram_1.insets = new Insets(0, 0, 5, 0);
		gbc_diagram_1.fill = GridBagConstraints.BOTH;
		gbc_diagram_1.gridx = 0;
		gbc_diagram_1.gridy = 1;
		panl_test.add(diagram_1, gbc_diagram_1);
		
		diagram_2 = new Diagram();
		GridBagConstraints gbc_diagram_2 = new GridBagConstraints();
		gbc_diagram_2.gridwidth = 2;
		gbc_diagram_2.insets = new Insets(0, 0, 5, 0);
		gbc_diagram_2.fill = GridBagConstraints.BOTH;
		gbc_diagram_2.gridx = 0;
		gbc_diagram_2.gridy = 2;
		panl_test.add(diagram_2, gbc_diagram_2);
		
		chckbxNewCheckBox = new JCheckBox("\u041F\u0440\u043E\u0442\u043E\u043A\u043E\u043B \u043D\u0430 \u043A\u043E\u043D\u0441\u043E\u043B\u044C");
		GridBagConstraints gbc_chckbxNewCheckBox = new GridBagConstraints();
		gbc_chckbxNewCheckBox.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox.gridx = 0;
		gbc_chckbxNewCheckBox.gridy = 3;
		panl_test.add(chckbxNewCheckBox, gbc_chckbxNewCheckBox);
		
		btnNewButton_1 = new JButton("\u0421\u0442\u0430\u0440\u0442");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		panl_test.add(btnNewButton_1, gbc_btnNewButton_1);
		
		Start = new JPanel();
		tabbedPane.addTab("Start", null, Start, null);
		Start.setLayout(new BoxLayout(Start, BoxLayout.X_AXIS));
		
		statisticsManager = new StatisticsManager();
		Start.add(statisticsManager);
		
		panel_1 = new JPanel();
		tabbedPane.addTab("Transient", null, panel_1, null);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		panel_3 = new JPanel();
		panel_1.add(panel_3);
		panel_3.setLayout(new CardLayout(0, 0));
		
		transProcessManager = new TransProcessManager();
		panel_3.add(transProcessManager, "name_399543524995221");
		
		panel = new JPanel();
		tabbedPane.addTab("Regres", null, panel, null);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 225, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		diagram_3 = new Diagram();
		GridBagConstraints gbc_diagram_3 = new GridBagConstraints();
		gbc_diagram_3.gridwidth = 2;
		gbc_diagram_3.insets = new Insets(0, 0, 5, 5);
		gbc_diagram_3.fill = GridBagConstraints.BOTH;
		gbc_diagram_3.gridx = 0;
		gbc_diagram_3.gridy = 0;
		panel.add(diagram_3, gbc_diagram_3);
		
		regresAnaliser = new RegresAnaliser();
		GridBagConstraints gbc_regresAnaliser = new GridBagConstraints();
		gbc_regresAnaliser.gridwidth = 3;
		gbc_regresAnaliser.insets = new Insets(0, 0, 5, 0);
		gbc_regresAnaliser.fill = GridBagConstraints.BOTH;
		gbc_regresAnaliser.gridx = 2;
		gbc_regresAnaliser.gridy = 0;
		panel.add(regresAnaliser, gbc_regresAnaliser);
		
		comboBox = new JComboBox();
		comboBox.setToolTipText("");
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.gridwidth = 2;
		gbc_comboBox.gridheight = 2;
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 2;
		panel.add(comboBox, gbc_comboBox);
		
		chooseData_6 = new ChooseData();
		chooseData_6.setTitle("\u0417\u043D\u0430\u0447\u0435\u043D\u043D\u044F \u0444\u0430\u043A\u0442\u043E\u0440\u0443");
		chooseData_6.setText("1 2 3 5 6 8 9 10 ");
		GridBagConstraints gbc_chooseData_6 = new GridBagConstraints();
		gbc_chooseData_6.gridwidth = 3;
		gbc_chooseData_6.gridheight = 3;
		gbc_chooseData_6.insets = new Insets(0, 0, 5, 0);
		gbc_chooseData_6.fill = GridBagConstraints.HORIZONTAL;
		gbc_chooseData_6.gridx = 2;
		gbc_chooseData_6.gridy = 1;
		panel.add(chooseData_6, gbc_chooseData_6);
		
		chckbxNewCheckBox_1 = new JCheckBox("Ln");
		GridBagConstraints gbc_chckbxNewCheckBox_1 = new GridBagConstraints();
		gbc_chckbxNewCheckBox_1.insets = new Insets(0, 0, 0, 5);
		gbc_chckbxNewCheckBox_1.gridx = 0;
		gbc_chckbxNewCheckBox_1.gridy = 4;
		panel.add(chckbxNewCheckBox_1, gbc_chckbxNewCheckBox_1);
		
		btnNewButton_2 = new JButton("\u041F\u0435\u0440\u0435\u0440\u0438\u0441\u043E\u0432\u0430\u0442\u044C");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 1;
		gbc_btnNewButton_2.gridy = 4;
		panel.add(btnNewButton_2, gbc_btnNewButton_2);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 0, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 3;
		gbc_textField.gridy = 4;
		panel.add(textField, gbc_textField);
		textField.setColumns(10);
		
		btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 4;
		panel.add(btnNewButton, gbc_btnNewButton);
	}

	public JPanel getPanel_1() {
		return panl_test;
	}
	public JPanel getPanel() {
		return panel_tz;
	}


}
