package Tile;

import MainPackage.GamePanel;
import MainPackage.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;

    public int[][][] mapTileNum;

    int currentTileCount=325;

    public BufferedImage loading, loading_title, saving_title;

    public TileManager(GamePanel gp)
    {
        this.gp=gp;
        this.tile= new Tile[currentTileCount];
        mapTileNum= new int[gp.maxMap][gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();

        set_collision_tiles();
        loadMap(0);
    }

    public void  getTileImage()
    {

        for(int i=0; i<currentTileCount;i++)
        tile[i]= new Tile();

        for(int i=0; i<currentTileCount; i++)
            setup(i, "tile" + i, false);
        try {
            loading = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Background/loading.jpg"));
            loading_title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/loading_title.png"));
            saving_title= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Title/saving_title.png"));
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw_loading_screen(Graphics2D g2)
    {
    g2.drawImage(loading, 0,0, gp.screenWidth, gp.screenHeight, null);

    if(gp.gameState== gp.gameLoadingState)
        g2.drawImage(loading_title, 1300, 850, 386, 97, null);
    else if(gp.gameState==gp.savingState)
        g2.drawImage(saving_title, 1300, 850, 386, 97, null);

    }

    public void draw(Graphics2D g2)
    {

        int worldCol=0;
        int worldRow=0;



        while(worldCol<gp.maxWorldCol && worldRow< gp.maxWorldRow)
        {
            int tileNum= mapTileNum[gp.currentMap][worldCol][worldRow];

            int worldX= worldCol*gp.tileSize;
            int worldY= worldRow*gp.tileSize;
            int screenX= worldX-gp.player.worldX+gp.player.screenX;
            int screenY= worldY-gp.player.worldY+gp.player.screenY;

            if(worldX +gp.tileSize>gp.player.worldX-gp.player.screenX &&
                worldX-gp.tileSize<gp.player.worldX+gp.player.screenX &&
                worldY+gp.tileSize>gp.player.worldY-gp.player.screenY &&
                worldY -gp.tileSize<gp.player.worldY+gp.player.screenY)
            {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }

            worldCol++;


            if(worldCol==gp.maxWorldCol)
            {
                worldCol=0;

                worldRow++;

            }



        }

    }

    public void set_collision_tiles()
    {

        tile[0].collison=true;

        for(int i=6; i<=14; i++)
            tile[i].collison=true;

        tile[18].collison=true;

        for(int i=27; i<=35; i++)
            tile[i].collison=true;

        tile[37].collison=true;

        for(int i=48; i<=61; i++)
            tile[i].collison=true;

        for(int i=82; i<=91; i++)
            tile[i].collison=true;

        for(int i=93; i<=99; i++)
            tile[i].collison=true;

        tile[123].collison=true;

        for(int i=135; i<=169; i++)
            tile[i].collison=true;

        for(int i=171; i<=179; i++)
            tile[i].collison=true;

        for(int i=181; i<=193; i++)
            tile[i].collison=true;

        for(int i=195; i<=196; i++)
            tile[i].collison=true;

        for(int i=198; i<=202; i++)
            tile[i].collison=true;

        for(int i=213; i<=231; i++)
            tile[i].collison=true;

        for(int i=233; i<=238; i++)
            tile[i].collison=true;

        for(int i=240; i<=243; i++)
            tile[i].collison=true;

        for(int i=248; i<=253; i++)
            tile[i].collison=true;

        for(int i=255; i<=284; i++)
            tile[i].collison=true;

        tile[260].collison=false;

        for(int i=307; i<=318; i++)
            tile[i].collison=true;

    }



    public void setup(int index, String imagePath, boolean collison)
    {
        UtilityTool uTool= new UtilityTool();

        try{
            tile[index]= new Tile();
            tile[index].image = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tile/"+imagePath+".png"));
            tile[index].image=uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collison=collison;
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadMap(int i)
    {

        try{

            InputStream is= getClass().getResourceAsStream("/Map/Map"+(i+1)+".csv");

            BufferedReader br= new BufferedReader(new InputStreamReader(is));

            int col=0;
            int row=0;

            while(col<gp.maxWorldCol && row<gp.maxWorldRow)
            {
                String line = br.readLine();



                while(col<gp.maxWorldCol)
                {
                    String numbers[]= line.split(",");

                    //this will replace with one or more space, just using " " would split by one space only



                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[i][col][row]= num;
                    col++;
                }
                if(col==gp.maxWorldCol)
                {
                    col=0;
                    row++;
                }

            }
            br.close();
        }
        catch (Exception e){

        }

        /*for(int i=0; i< gp.MaxScreenRow; i++)
        {
            for(int j=0; j<gp.MaxScreenCol; j++)
                System.out.print(mapTileNum[j][i]+",");

            System.out.println();
        }*/
    }
}
