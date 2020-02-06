package commandline;

import java.sql.SQLException;
import java.util.Scanner;

public class TopTrumpsController {
	private TopTrumpsModel model;
	private TopTrumpsView view;
	private int playerInput;
	private int noPlayers;
	private int category;

	public TopTrumpsController(TopTrumpsModel model) {
		this.model = model;
		this.view = new TopTrumpsView(model, this);
	}

	public void play() throws SQLException {
		Scanner scanner = new Scanner(System.in);
		playerInput = 0;

		view.choosePlayOrStats();
		playerInput = scanner.nextInt();
		while (!(playerInput == 1) && !(playerInput == 2)) {
			view.checkInput();
			playerInput = scanner.nextInt();
		}
		if (playerInput == 1) {
			view.printStats();
		}
		if (playerInput == 2) {
			noPlayers = 0;
			view.choosePlayers();
			noPlayers = scanner.nextInt();
			while (!(noPlayers >= 2) && !(noPlayers <= 5)) {
				view.checkInput();
				noPlayers = scanner.nextInt();
			}
			view.startGame();
			model.setNoOfPlayers(noPlayers);
			while (!model.gameWon()) {
				model.deal();
				view.playRound();
				if (model.getWinnerOfRound() == 0) {
					category = 0;
					view.humanChooseCat();
					category = scanner.nextInt();
					while (!(category >= 1) && !(category <= 5)) {
						view.checkInput();
						category = scanner.nextInt();
					}
					model.setTrump(category);
				}

				else {
					model.aiPick(model.getPlayersArrayList().get(model.getWinnerOfRound()));
					view.AIChooseCat();
				}
				model.checkRound();
				if (model.getWinnerOfRound() == -1) {
					view.draw();
					model.setWinnerofRound(model.getPrevWinRound());
				} else if (model.getWinnerOfRound() == 0) {
					view.humanWin();
					model.setPrevWinRound(model.getWinnerOfRound());
				} else {
					view.AIWin();
					model.setPrevWinRound(model.getWinnerOfRound());
				}
			}
			view.gameWon();
		}
	}
}

