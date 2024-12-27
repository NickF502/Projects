package src;

import java.util.Vector;

public class Player {

	private String username;
	private String password;
	
	protected Inventory inventory;
	protected Vector<Long> gamePoints = new Vector<Long>();
	
	public Player(String user, String pass) {
		username = user;
		password = pass;
		
		inventory = new Inventory();
		
		initializeGamePointsVector();
		
	}
	
	void initializeGamePointsVector() {
		for(int i=0; i<Mappings.gameToID.size(); i++) {
			gamePoints.add(10000L);
		}
	}
	
	void loadGamePoints(int index, Long amount) {
		gamePoints.set(index, amount);
	}
	
	String getUsername() {
		return username;
	}
	
	String getPassword() {
		return password;
	}
	
	Long getPoints() {
		if(GachaMain.gameMode == "Main") return 0L;
		return gamePoints.get(Mappings.gameToID.get(GachaMain.gameMode));
	}
	
	String getFormattedPoints() {
		return NumberFormatter.formatInteger(getPoints());
	}
	
	void setPoints(Long a) {
		gamePoints.set(Mappings.gameToID.get(GachaMain.gameMode), a);
	}
	
	
	void addItem(String item) {
		inventory.addItemtoInventory(item);
	}
	
	
	String getGamePoints() {
		String gp = "";
		for(Long points : gamePoints) {
			gp += points + "\n";
		}
		return gp;
	}
	
	
}


