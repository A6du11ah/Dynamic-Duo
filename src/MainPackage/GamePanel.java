package MainPackage;

import Hud.*;
import Tile.DecorationManager;
import Tile.ObjectManager;
import Tile.TileManager;
import entity.Entity;
import entity.Player;
import Tile.Map;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class GamePanel extends JPanel implements  Runnable{
    //

    final int originalTileSize=16; //individual block..may hold a whole player
    final int scale=3; //16 is too small..so we need to scale it

    public final int tileSize = originalTileSize * scale; //original tile that would be displayed
    public final int MaxScreenCol= 36; //no of tiles in a column
    public final int MaxScreenRow= 20;// no of tiles in row

    public final int maxMap=1;
    public int currentMap=0;
    public final int screenWidth= tileSize * MaxScreenCol; //sets total screen column
    public final int screenHeight= tileSize * MaxScreenRow; //sets total screen row

    //World Settings
    public final int maxWorldCol=279;
    public final int maxWorldRow=223;
    public final int worldWidth=tileSize*maxWorldCol;
    public final int worldHeight= tileSize* maxWorldRow;


    Thread gameThread;
    int FPS=60;

    public KeyHandler keyH= new KeyHandler(this);
    public Player player = new Player(this, keyH);
    public TileManager tileM= new TileManager(this);
    public ObjectManager objM= new ObjectManager(this);

    public AssetSetter aSetter = new AssetSetter(this);
    public TitleScreen titleScreen= new TitleScreen(this);

    public int save_game_id=0;

    int enemy_count=80;
    public Entity enemy[]=new Entity[enemy_count];

    Map map= new Map(this);

    DecorationManager decM= new DecorationManager(this);

    public CollisionChecker cChecker= new CollisionChecker(this);

    public HudManager hud=new HudManager(this);
    public PauseScreen pauseScreen= new PauseScreen(this);
    public SaveLoading saveLoading= new SaveLoading(this);

    public PauseSaving pauseSaving=new PauseSaving(this);

    public InventoryScreen inventory= new InventoryScreen(this);


    public Database db=new Database(this);
    public boolean playerGameCollisonOn =true;

    public String next_selected= "";

    public int gameState;
    public final int playState=0;
    public final int mapState=1;

    public final int gameLoadingState=2;

    public final int pauseState=3;
    public final int savingState=4;

    public final int saveLoadingState=5;

    public final int titleState=6;

    public final int inventoryState=7;

    public int total_save_game=0;





    public GamePanel()
    {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        this.gameState=gameLoadingState;

       // setupGame();
    }


    public void setupGame()
    {
       // aSetter.setEnemy();
        this.gameState=titleState;

        try
        {

            db.getSaveGames();

            db.getPlayer();
            db.getObject();



        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void startGameThread()
    {
        gameThread= new Thread(this);
        gameThread.start(); //automatically calls run method
    }


    @Override
    public void run() {

        double drawInterval = 1000000000/ (double) FPS;
        double nextDrawTime= System.nanoTime() + drawInterval;
        //our gameloop

        while(gameThread != null) {


            update();

            repaint(); //we are calling paintComponent option

            try
            {
                double remainingTime = nextDrawTime - System.nanoTime();

                remainingTime= remainingTime/1000000; //the sleep function only takes mili, so we convert to it

                if(remainingTime<0)
                    remainingTime=0;

                Thread.sleep((long) remainingTime);

                nextDrawTime+= drawInterval; //set next interval
            }
            catch(InterruptedException e)
            {
                //convention
            }
        }

    }

    public void update()
    {


        if(gameState==playState) {
            player.update();
            decM.updateAnimation();
            objM.update();

            for(int i=0; i<enemy.length; i++)
            {

                if(enemy[i]!=null)
                {
                    enemy[i].update();

                    if(enemy[i].current_health<=0)
                        enemy[i]=null;
                }

            }


        }
        else if(gameState==pauseState)
            pauseScreen.update();
        else if(gameState==titleState)
            titleScreen.update();
        else if (gameState==saveLoadingState)
            saveLoading.update();
        else if(gameState==savingState)
            pauseSaving.update();
        else if(gameState==inventoryState)
            inventory.update();
    }



    public void paintComponent(Graphics g) {
        super.paintComponent(g);



        Graphics2D g2 = (Graphics2D) g;

        if(gameState==gameLoadingState)
            tileM.draw_loading_screen(g2);
        else if (gameState == playState) {
            tileM.draw(g2);
            decM.draw_water(g2);

            player.draw(g2);

            for(int i=0; i<enemy.length; i++)
                if(enemy[i]!=null)
                    enemy[i].draw(g2);

            decM.draw(g2);
            objM.draw(g2);
            hud.draw_hud(g2);
        }
        else if(gameState== mapState)
            map.drawFullMapScreen(g2);
        else if(gameState==pauseState)
            pauseScreen.draw(g2);
        else if(gameState==savingState)
            pauseSaving.draw(g2);
        else if(gameState==titleState)
            titleScreen.draw(g2);
        else if(gameState==saveLoadingState)
            saveLoading.draw(g2);
        else if(gameState==inventoryState)
            inventory.draw(g2);
    }
}
