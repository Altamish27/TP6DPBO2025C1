import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener, MouseListener {

    int frameWidth = 360;
    int frameHeight = 640;

    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;
    Image startImage;
    Image gameOverImage;

    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    Timer gameLoop;
    Timer pipesCooldown;
    int gravity = 1;

    boolean isGameOver = false;
    boolean isMenu = true;

    JLabel scoreLabel;
    int score = 0;

    // posisi dan ukuran gambar start
    int startImgX = (frameWidth - 200) / 2;
    int startImgY = frameHeight / 2 - 100;
    int startImgW = 200;
    int startImgH = 100;

    public FlappyBird() {
        setPreferredSize(new Dimension(frameWidth, frameHeight));
        setFocusable(true);
        addKeyListener(this);
        addMouseListener(this);
        setLayout(null);

        // load images
        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();
        startImage = new ImageIcon(getClass().getResource("assets/start.png")).getImage();
        gameOverImage = new ImageIcon(getClass().getResource("assets/gameover.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(10, 10, 200, 30);
        add(scoreLabel);

        pipesCooldown = new Timer(1500, e -> placePipes());
        pipesCooldown.stop();

        gameLoop = new Timer(1000 / 60, this);
        gameLoop.stop();
    }

    public void placePipes() {
        int openingSpace = frameHeight / 4;
        int maxOffset = pipeHeight - openingSpace;
        int offset = (int) (Math.random() * maxOffset);

        Pipe upperPipe = new Pipe(pipeStartPosX, -offset, pipeWidth, pipeHeight, upperPipeImage);
        upperPipe.setVelocityX(-3);
        pipes.add(upperPipe);

        Pipe lowerPipe = new Pipe(pipeStartPosX, -offset + pipeHeight + openingSpace, pipeWidth, pipeHeight, lowerPipeImage);
        lowerPipe.setVelocityX(-3);
        pipes.add(lowerPipe);
    }

    public void draw(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        if (isMenu && startImage != null) {
            g.drawImage(startImage, startImgX, startImgY, startImgW, startImgH, null);
            g.setFont(new Font("Arial", Font.PLAIN, 18));
            g.setColor(Color.WHITE);

        }

        if (isGameOver && gameOverImage != null) {
            g.drawImage(gameOverImage, (frameWidth - 250) / 2, frameHeight / 2 - 100, 250, 100, null);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void move() {
        if (isGameOver || isMenu) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        for (Pipe pipe : pipes) {
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            if (!pipe.isPassed() && pipe.getImage() == lowerPipeImage &&
                    pipe.getPosX() + pipe.getWidth() < player.getPosX()) {
                pipe.setPassed(true);
                score++;
                scoreLabel.setText("Score: " + score);
            }
        }

        checkCollision();
    }

    public void checkCollision() {
        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (Pipe pipe : pipes) {
            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                isGameOver = true;
                gameLoop.stop();
                pipesCooldown.stop();
            }
        }

        if (player.getPosY() + player.getHeight() >= frameHeight) {
            isGameOver = true;
            gameLoop.stop();
            pipesCooldown.stop();
        }
    }

    public void resetGame() {
        pipes.clear();
        player.setPosY(playerStartPosY);
        player.setVelocityY(-8);
        score = 0;
        scoreLabel.setText("Score: 0");
        isGameOver = false;
        gameLoop.start();
        pipesCooldown.start();
    }


    public void actionPerformed(ActionEvent e) {
        if (!isMenu && !isGameOver) {
            move();
        }
        repaint();
    }

  public void keyTyped(KeyEvent e) {}


    public void keyPressed(KeyEvent e) {
        if (!isGameOver && !isMenu && e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.setVelocityY(-10);
        }

        if (isGameOver && e.getKeyCode() == KeyEvent.VK_R) {
            resetGame();
        }
    }

    @Override public void keyReleased(KeyEvent e) {}

    // mouse click untuk start dari gambar

    public void mousePressed(MouseEvent e) {
        if (isMenu) {
            int mx = e.getX();
            int my = e.getY();

            if (mx >= startImgX && mx <= startImgX + startImgW &&
                    my >= startImgY && my <= startImgY + startImgH) {
                isMenu = false;
                gameLoop.start();
                pipesCooldown.start();
            }
        }
    }

     public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
   public void mouseExited(MouseEvent e) {}
}
