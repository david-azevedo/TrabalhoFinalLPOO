package projeto.logic;

public interface IMinigameTools {
	
	void resetGame();
	
	void resetRound();
	
	void scoreReceived(int score, int entity_id);
	
	void sendEst(int id, Estatistica e);
	
	void newGameObject(GameObject gO);
	
	void resetGameObject(GameObject gO);
	
}