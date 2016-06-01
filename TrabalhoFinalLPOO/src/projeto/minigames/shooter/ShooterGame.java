package projeto.minigames.shooter;

import java.util.ArrayList;

import projeto.logic.ControllerInformationPacket;
import projeto.logic.Estatistica;
import projeto.logic.GameObject;
import projeto.logic.Input;
import projeto.logic.Minigame;
import projeto.logic.Parede;
import projeto.logic.Vector2;
import projeto.logic.iEstatisticaAlert;
import projeto.logic.ControllerInformationPacket.Type;
import projeto.minigames.shooter.Player;

/**
 * @author PeaceOff
 * @version 1.0
 * @created 03-mai-2016 15:30:45
 */
public class ShooterGame extends Minigame {
	
	Player[] players;
	ArrayList<ControllerInformationPacket> controllerPackets = new ArrayList<ControllerInformationPacket>();
	private int scores[] = new int[2];
	private final int c = 1000;
	private final int l = 700;
	private final int p = 40;
	
	public ShooterGame(Input i, iEstatisticaAlert estA) {
		super(i, estA);
		players = new Player[8];
	}
	
	public void finalize() throws Throwable {
		super.finalize();
	}

	public void initGame(){  

		controllerPackets.add(new ControllerInformationPacket(0f, 0.5f, 0.5f, 0.5f, Type.JOYSTICK,(byte)0));
		controllerPackets.add(new ControllerInformationPacket(0.5f, 0.5f, 0.5f, 0.5f, Type.JOYSTICK,(byte)1));
		
		GameObject d11 = new Parede(m_Input, new Vector2(0,0), new Vector2(c,p));
		GameObject d12 = new Parede(m_Input, new Vector2(0,0), new Vector2(p,l)); 
		GameObject d13 = new Parede(m_Input, new Vector2(c-p,0), new Vector2(p,l));
		GameObject d14 = new Parede(m_Input, new Vector2(0,l-p), new Vector2(c,p));
		
		addGameObject(d11);
		addGameObject(d12);
		addGameObject(d13);
		addGameObject(d14);
			
	}

	public void update(float timeLapsed){
		super.update(timeLapsed);
	}

	@Override
	public void addPlayer(int id) {
		
		Mira m = new Mira(m_Input);
		Player player = new Player(m_Input, id , new Vector2(100,100),this,m);
		addGameObject(player);
		addGameObject(m);
		resetPlayer(player);
		players[id] = player; 
	}
	
	public void resetPlayer(Player p){
		if(p == null)
			return;
		
		p.getCollider().setPosition(randomPos());
		p.getCollider().setVelocity(new Vector2());
	}

	private Vector2 randomPos(){
		Vector2 res = new Vector2();
		res.x = (Math.random() * (c * 0.8)) + (1.1 * p);
		res.y = (Math.random() * (l * 0.8)) + (1.1 * p);
		return res;
	}
	
	@Override
	public void resetGameObject(GameObject gO){
		resetPlayer((Player)gO);
	}
	
	@Override
	public void removePlayer(int id) {
		removeGameObject(players[id].mira);
		removeGameObject(players[id]);
		players[id] = null;
		
	}

	@Override
	public void resetRound() {
	}

	@Override
	public void scoreReceived(int score, int entity_id) {
		if(entity_id < 0 || entity_id >= scores.length)
			return;
		scores[entity_id] += score;
	}

	@Override
	public void resetGame() {
	}

	@Override
	public int[] getScores() {
		return scores;
	}

	@Override
	public void sendEst(int id, Estatistica e) {
		e.setTipoJogo("ShooterGame");
		i_EstAlert.receiveEstatistica(id, e);
		
	}

	@Override
	public Vector2 getDim() {
		return new Vector2(c,l);
	}
	
	@Override
	public String getDica() {
		return "BOOM! Headshot!";
	}

	@Override
	public ArrayList<ControllerInformationPacket> getControllPacket() {
		
		return controllerPackets;
	}
}

