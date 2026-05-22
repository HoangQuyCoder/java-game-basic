import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class WhacAMoleController {
    private final WhacAMoleModel model;
    private final WhacAMoleView view;
    private Timer setMoleTimer;
    private Timer setPlantTimer;
    private final Random random = new Random();

    public WhacAMoleController(WhacAMoleModel model, WhacAMoleView view) {
        this.model = model;
        this.view = view;
        initController();
    }

    private void initController() {
        // Register click listener with view
        view.setTileClickListener(new WhacAMoleView.TileClickListener() {
            @Override
            public void onTileClicked(int index) {
                handleTileClick(index);
            }
        });
        // Setup timers
        setMoleTimer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMoleTick();
            }
        });
        setPlantTimer = new Timer(1500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handlePlantTick();
            }
        });
    }

    public void startGame() {
        model.reset();
        view.resetUI();
        setMoleTimer.start();
        setPlantTimer.start();
    }

    private void handleTileClick(int index) {
        if (model.isGameOver()) {
            return;
        }
        if (model.isMole(index)) {
            model.addScore(10);
            view.updateScore(model.getScore());
        } else if (model.isPlant(index)) {
            model.setGameOver(true);
            view.showGameOver(model.getScore());
            setMoleTimer.stop();
            setPlantTimer.stop();
            view.disableAllTiles();
        }
    }

    private void handleMoleTick() {
        // Remove mole from current tile
        int currentMole = model.getMoleIndex();
        if (currentMole != -1) {
            view.clearTileIcon(currentMole);
            model.setMoleIndex(-1);
        }
        // Randomly select another tile
        int num = random.nextInt(WhacAMoleModel.NUM_TILES);
        // If tile is occupied by plant, skip tile for this turn
        if (model.getPlantIndex() == num) {
            return;
        }
        // Set tile to mole
        model.setMoleIndex(num);
        view.setMoleIcon(num);
    }

    private void handlePlantTick() {
        // Remove plant from current tile
        int currentPlant = model.getPlantIndex();
        if (currentPlant != -1) {
            view.clearTileIcon(currentPlant);
            model.setPlantIndex(-1);
        }
        // Randomly select another tile
        int num = random.nextInt(WhacAMoleModel.NUM_TILES);
        // If tile is occupied by mole, skip tile for this turn
        if (model.getMoleIndex() == num) {
            return;
        }
        // Set tile to plant
        model.setPlantIndex(num);
        view.setPlantIcon(num);
    }
}
