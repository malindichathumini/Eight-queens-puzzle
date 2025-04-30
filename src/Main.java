

import javax.swing.SwingUtilities;
import model.GameModel;
import view.GameView;
import controller.GameController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameModel model = new GameModel();
            GameView view   = new GameView();
            GameController ctrl = new GameController(model, view);
            ctrl.initController();
        });
    }
}

