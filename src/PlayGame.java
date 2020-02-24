import java.awt.*;

public class PlayGame {
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                GameWindow game = new GameWindow();
                game.setVisible(true);
            }
        });
    }
}
