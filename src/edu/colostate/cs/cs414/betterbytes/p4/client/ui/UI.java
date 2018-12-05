package edu.colostate.cs.cs414.betterbytes.p4.client.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;

import edu.colostate.cs.cs414.betterbytes.p4.client.ClientConnection;
import edu.colostate.cs.cs414.betterbytes.p4.hnefatafl.game.Game;
import edu.colostate.cs.cs414.betterbytes.p4.server.utilities.Tools;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.CreateInvitation;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Message;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.Protocol;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RecordsRequest;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RecordsRequestResponse;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.RespondToInvitation;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.UserLogon;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.UserLogonResponse;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.UserRegistration;
import edu.colostate.cs.cs414.betterbytes.p4.server.wireforms.UserRegistrationResponse;
import edu.colostate.cs.cs414.betterbytes.p4.user.Account;
import edu.colostate.cs.cs414.betterbytes.p4.user.Invitation;

/**
 *
 * @author Daniel McClure, created in NetBeans.
 */
public class UI extends javax.swing.JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3197143613595871851L;
	private javax.swing.JButton ACCEPTBUTTON;
	private javax.swing.JList<String> CURRENTGAMESLIST;
	private javax.swing.JButton DECLINEBUTTON;
	private javax.swing.JList<String> INVITESLIST;
	private javax.swing.JButton LOGINBUTTON;
	private javax.swing.JPasswordField PASSWORD;
	private javax.swing.JButton PROFILESTATSBUTTON;
	private javax.swing.JButton QUITGAMEBUTTON;
	public static javax.swing.JButton REFRESHBUTTON;
	private javax.swing.JButton RESUMEGAMEBUTTON;
	private javax.swing.JTextField USERNAME;
	private javax.swing.JButton VIEWGAMEMANUALBUTTON;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JScrollPane jScrollPane2;
	private DefaultListModel<String> gamesListModel = new DefaultListModel<String>();
	private DefaultListModel<String> invitesListModel = new DefaultListModel<String>();
	private ArrayList<String> loadedGames = new ArrayList<String>();
	private JButton SENDINVITEBUTTON = new JButton("Invite a friend...");
	private JButton SIGNUPBUTTON = new JButton("Create Account");
	private GameFrame gameframe = null;

	private ClientConnection connection;
	public static Account user;
	public static List<Game> gameObjects = null;

	public String title = "Tafl Control Panel    |    Profile: ";

	/**
	 * Creates new form UI
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public UI(ClientConnection connection) throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, UnsupportedLookAndFeelException {
		initComponents();
		this.setTitle(title);
		// this.refreshData();
		this.connection = connection;
		start();
		this.setVisible(true);
		this.setIconImage(Tools.getLocalImg("edu/colostate/cs/cs414/betterbytes/p4/client/data/icon.png"));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		LOGINBUTTON = new javax.swing.JButton();
		PASSWORD = new javax.swing.JPasswordField();
		USERNAME = new javax.swing.JTextField();
		jLabel1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		CURRENTGAMESLIST = new javax.swing.JList<>();
		jScrollPane2 = new javax.swing.JScrollPane();
		INVITESLIST = new javax.swing.JList<>();
		jLabel2 = new javax.swing.JLabel();
		REFRESHBUTTON = new javax.swing.JButton();
		ACCEPTBUTTON = new javax.swing.JButton();
		DECLINEBUTTON = new javax.swing.JButton();
		RESUMEGAMEBUTTON = new javax.swing.JButton();
		QUITGAMEBUTTON = new javax.swing.JButton();
		VIEWGAMEMANUALBUTTON = new javax.swing.JButton();
		PROFILESTATSBUTTON = new javax.swing.JButton();

		LOGINBUTTON.addActionListener(this);
		SIGNUPBUTTON.addActionListener(this);
		REFRESHBUTTON.addActionListener(this);
		ACCEPTBUTTON.addActionListener(this);
		DECLINEBUTTON.addActionListener(this);
		RESUMEGAMEBUTTON.addActionListener(this);
		QUITGAMEBUTTON.addActionListener(this);
		VIEWGAMEMANUALBUTTON.addActionListener(this);
		PROFILESTATSBUTTON.addActionListener(this);
		SENDINVITEBUTTON.addActionListener(this);

		REFRESHBUTTON.setEnabled(false);
		PROFILESTATSBUTTON.setEnabled(false);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Login"));
		jPanel1.setName("Login"); // NOI18N

		LOGINBUTTON.setText("Login");

		PASSWORD.setText("jPasswordField1");

		USERNAME.setText("Username");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(PASSWORD).addComponent(USERNAME).addComponent(LOGINBUTTON)
								.addComponent(SIGNUPBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 111,
										javax.swing.GroupLayout.PREFERRED_SIZE))
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
						.addComponent(USERNAME, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(LOGINBUTTON)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(SIGNUPBUTTON)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		jLabel1.setText("Current Games:");

		jScrollPane1.setViewportView(CURRENTGAMESLIST);

		jScrollPane2.setViewportView(INVITESLIST);

		jLabel2.setText("Invites:");

		REFRESHBUTTON.setText("Refresh");

		ACCEPTBUTTON.setText("Accept");

		DECLINEBUTTON.setText("Decline");

		RESUMEGAMEBUTTON.setText("Resume");

		// QUITGAMEBUTTON.setBackground(new java.awt.Color(153, 0, 0));
		QUITGAMEBUTTON.setForeground(new java.awt.Color(255, 0, 0));
		QUITGAMEBUTTON.setText("Forfeit Game");

		VIEWGAMEMANUALBUTTON.setText("View Game Manual");

		PROFILESTATSBUTTON.setText("Profile Stats");

		USERNAME.setText("ctunnell");
		PASSWORD.setText("TestPassword");

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false).addGroup(layout
						.createSequentialGroup()
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
								.addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
										.addComponent(RESUMEGAMEBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 101,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(QUITGAMEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addComponent(jScrollPane1))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jLabel2)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
										.addGroup(layout.createSequentialGroup()
												.addComponent(ACCEPTBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 95,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(DECLINEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
												javax.swing.GroupLayout.PREFERRED_SIZE))))
						.addComponent(REFRESHBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
						.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(VIEWGAMEMANUALBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(SENDINVITEBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(PROFILESTATSBUTTON, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))

				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup()
								.addComponent(REFRESHBUTTON, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1).addComponent(jLabel2))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 233,
												Short.MAX_VALUE)
										.addComponent(jScrollPane2)))
						.addGroup(layout.createSequentialGroup()
								.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(VIEWGAMEMANUALBUTTON)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(SENDINVITEBUTTON)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(PROFILESTATSBUTTON)))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(RESUMEGAMEBUTTON).addComponent(QUITGAMEBUTTON).addComponent(ACCEPTBUTTON)
						.addComponent(DECLINEBUTTON))
				.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		pack();
	}// </editor-fold>

	/**
	 * @param args the command line arguments
	 * @throws UnknownHostException
	 * @throws InterruptedException
	 */
	public static void main(String args[]) throws UnknownHostException, InterruptedException {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed" desc=" Look and feel setting
		// code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the default
		 * look and feel. For details see
		 * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf. html
		 */
		try {

			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */

		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new UI(null).setVisible(true);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

	public void refreshData() {
		String absPath = System.getProperty("user.dir") + "/src/";
		this.gamesListModel.clear();
		Message reply = connection.send(new RecordsRequest(user.getUsername()));
		if (reply != null && reply.getType() != null && reply.getType().equals(Protocol.RECORDS_REQUEST_RESPONSE)) {
			RecordsRequestResponse rr = (RecordsRequestResponse) reply;
			if (rr != null) {
				this.user = rr.getAccount();
				gameObjects = rr.getGames();
				Tools.log("Rec Games: " + gameObjects.size());
				for (Game g : gameObjects) {
					String enemy = "";
					if (g.getAttacker().getAccount().getUsername().equals(user.getUsername())) {
						enemy = g.getDefender().getAccount().getUsername();
					} else if (g.getDefender().getAccount().getUsername().equals(user.getUsername())) {
						enemy = g.getAttacker().getAccount().getUsername();
					}
					String whosTurn = "N/A";
					if (g.getTurn() != null) {
						whosTurn = g.getTurn().getAccount().getUsername();
					}
					enemy = user.getUsername() + " vs " + enemy + "    |     Player's Turn: " + whosTurn;
					this.gamesListModel.addElement(enemy);
					CURRENTGAMESLIST.setModel(gamesListModel);
				}
				this.invitesListModel.clear();
				if (rr.getAccount() != null && rr.getAccount().getInvites() != null) {					
					for (Invitation i : rr.getAccount().getInvites()) {
						if (i != null) {
							String s = "Invite from: " + i.getSender();
							this.invitesListModel.addElement(s);
						}
					}
					this.INVITESLIST.setModel(invitesListModel);
				}
			}
		}
//		for (File f : new File("edu/colostate/cs/cs414/betterbytes/p3/data/games").listFiles()) {
//			Tools.log(f.getName());
//			this.gamesListModel.addElement(f.getName());
//			CURRENTGAMESLIST.setModel(gamesListModel);
//			this.loadedGames.add(Tools.getFileData(f));
//		}
	}

	public void createAccount() {

		System.out.println("USERNAME: " + USERNAME.getText() + ", PASSWORD: " + PASSWORD.getText());
		Message response = connection.send(new UserRegistration(USERNAME.getText(), PASSWORD.getText()));
		boolean created = ((UserRegistrationResponse) response).getStatus();
		if (created) {
			login();
		} else {
			this.setTitle("FAILED TO CREATE ACCOUNT");
		}
	}

	public void login() {
		// TODO Handle invalid Login
		System.out.println("USERNAME: " + USERNAME.getText() + ", PASSWORD: " + PASSWORD.getText());
		Message response = connection.send(new UserLogon(USERNAME.getText(), PASSWORD.getText()));
		user = ((UserLogonResponse) response).getAcc();
		if (user != null) {
			REFRESHBUTTON.setEnabled(true);
			PROFILESTATSBUTTON.setEnabled(true);
			this.setTitle(title + user.getUsername());
			System.out.println(user.getUsername());
			this.refreshData();
		} else {
			this.setTitle("FAILED TO LOGIN!");
		}

	}

	public void resumeGame() {
		if (this.CURRENTGAMESLIST.getSelectedValue() != null && gameObjects != null) {
			if (gameframe == null) {
				this.gameframe = new GameFrame(gameObjects.get(CURRENTGAMESLIST.getSelectedIndex()), this);
			} else {
				this.gameframe.display(gameObjects.get(CURRENTGAMESLIST.getSelectedIndex()));
			}
			if (this.gameframe != null)
				this.gameframe.setVisible(true);
		}
	}

	public void forfeitGame() {

	}

	public void acceptGame() {
		if (this.INVITESLIST.getSelectedValue() != null && user != null) {
			int ind = INVITESLIST.getSelectedIndex();
			if (user.getInvites().size() > ind) {
				Tools.log("Accepting.....");
				ClientConnection.getInstance().send(new RespondToInvitation(user.getInvites().get(ind), true));
				Tools.sleep(700);
				this.refreshData();
			} else {
				Tools.log(ind + " in list length of: " + user.getInvites().size());
			}
		}
	}

	public void declineGame() {
		if (this.INVITESLIST.getSelectedValue() != null && user != null) {
			int ind = INVITESLIST.getSelectedIndex();
			if (user.getInvites().size() > ind) {
				Tools.log("Declining.....");
				ClientConnection.getInstance().send(new RespondToInvitation(user.getInvites().get(ind), false));
				Tools.sleep(700);
				this.refreshData();
			} else {
				Tools.log(ind + " in list length of: " + user.getInvites().size());
			}
		}
	}

	public void gameManual() {
		String desc = "The game is one of pure strategy, played on a square board. A king and a small force of defenders occupy the centre of the board. A larger force of attackers, twice as numerous as the defenders, occupy positions around the edge of the board. The objective of the king is to escape to the periphery of the board, while the objective of the attackers is to capture the king, preventing his escape. The pieces move orthogonally, like rooks in chess, and capture is by surrounding a piece on two opposite sides";
		new TextUI("Game Manual", desc);
	}

	public void profileStats() {
		new TextUI("Profile Stats", this.getStats());
	}

	public String getStats() {
		if (user != null && user.getStats() != null)
			return "Wins: " + user.getStats().getWins() + "   Losses: " + user.getStats().getLosses();
		return "N/A";
	}

	public void sendInviteTo(String username) {
		if (user != null)
			ClientConnection.getInstance().send(new CreateInvitation(user.getUsername(), username));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Tools.log(e.getActionCommand());
		switch (e.getActionCommand()) {
		case "Create Account":
			this.createAccount();
			break;
		case "Login":
			login();
			break;
		case "Refresh":
			this.refreshData();
			break;
		case "Resume":
			this.resumeGame();
			break;
		case "View Game Manual":
			this.gameManual();
			break;
		case "Profile Stats":
			this.profileStats();
			break;
		case "Invite a friend...":
			String email = JOptionPane.showInputDialog(this, "Enter username: ", "Invitation",
					JOptionPane.QUESTION_MESSAGE);
			if (email != null)
				this.sendInviteTo(email);
			break;
		case "Decline":
			this.declineGame();
			break;
		case "Accept":
			this.acceptGame();
			break;
		}
	}

	public void start() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				javax.swing.UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}

	}

}
