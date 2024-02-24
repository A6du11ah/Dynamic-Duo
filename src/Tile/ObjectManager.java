package Tile;

import MainPackage.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class ObjectManager {

    GamePanel gp;

    int object_count=100;

    public int object_varity_count=10;

    public int current_obj=0;

    public SuperObject [][]world_obj;

    public SuperObject [] solo_obj ;

    public ObjectManager (GamePanel gp)
    {
        this.gp=gp;
        world_obj=new SuperObject[gp.maxMap][object_count];
        solo_obj =new SuperObject[object_varity_count];
        for(int i=0; i<object_varity_count; i++)
            solo_obj[i]=new SuperObject();

        define_object();
        place_object();
        get_object_image();
    }

    public void place_object()
    {
        for(int i=0; i<object_count; i++)
            world_obj[0][i]=new SuperObject();

        object_placer_helper(960,8320, 0);
        object_placer_helper(884,8352, 0);
        object_placer_helper(956,8364, 0);
        object_placer_helper(1036,8332, 0);
        object_placer_helper(1076,8368, 0);
        object_placer_helper(1172,8348, 0);
        object_placer_helper(1260,8336, 0);
        object_placer_helper(1120,8356, 0);
        object_placer_helper(1212,8388, 0);
        object_placer_helper(1008,8392, 0);

        object_placer_helper(2792,7980, 2);
        object_placer_helper(2870,7980, 2);
        object_placer_helper(2952,7980, 2);
        object_placer_helper(2760,8040, 2);
        object_placer_helper(2975,7795, 2);
        object_placer_helper(2970,7857, 2);
        object_placer_helper(2965,7925, 2);
        object_placer_helper(3035,7795, 2);
        object_placer_helper(3100,7795, 2);
        object_placer_helper(3180,7797, 2);
        object_placer_helper(3255,7795, 2);
        object_placer_helper(3310,7796, 2);

        object_placer_helper(3396,8224, 7);
        object_placer_helper(3380,8292, 7);
        object_placer_helper(3452,8272, 7);
        object_placer_helper(3468,8324, 7);
        object_placer_helper(3380,8328, 7);
        object_placer_helper(3517,8268, 7);
        object_placer_helper(3280,8316, 7);
        object_placer_helper(3328,8248, 7);

        object_placer_helper(4040,7432, 8);
        object_placer_helper(4122,7448, 8);
        object_placer_helper(4200,7412, 8);
        object_placer_helper(4112,7620, 8);
        object_placer_helper(3972,7972, 8);
        object_placer_helper(4432,7708, 8);

    }

    public void object_placer_helper(int x, int y, int type)
    {
        world_obj[0][current_obj].worldX=x;
        world_obj[0][current_obj].worldY=y;
        world_obj[0][current_obj].id =type;

        current_obj++;
    }

    public void define_object()
    {
        set_object_values(0, 80,80, 4,5,"sword","resource");
        set_object_values(1, 80,80, 4,5,"sword","resource");
        set_object_values(2, 80,80, 4,5,"sword","resource");
        set_object_values(3, 80,80, 4,5,"sword","resource");
        set_object_values(4, 80,80, 4,5,"sword","resource");
        set_object_values(5, 80,80, 4,5,"sword","resource");
        set_object_values(6, 80,80, 4,5,"sword","resource");
        set_object_values(7, 80,80, 4,5,"sword","resource");
        set_object_values(8, 80,80, 4,5,"sword","resource");
        set_object_values(9, 80,80, 4,5,"sword","resource");

        for(int i=0; i<10; i++)
            solo_obj[i].solidArea=new Rectangle(2,2, 80-2, 80-2);
    }

    public void set_object_values(int i, int height, int width, int max_image, int increase_amount, String hit_type, String type)
    {
        solo_obj[i].id =i;
        solo_obj[i].height=height;
        solo_obj[i].width=width;
        solo_obj[i].max_state=max_image;
        solo_obj[i].increase_amount=increase_amount;
        solo_obj[i].hit_type=hit_type;
        solo_obj[i].type=type;
    }

    public void get_object_image()
    {
        try{
        for(int i=0; i<object_varity_count; i++)
        {


            for(int j=0; j<solo_obj[i].max_state; j++)
            {
                solo_obj[i].image[j]= ImageIO.read(getClass().getClassLoader().getResourceAsStream("Object/Obj"+i+"/"+j+".png"));
            }

        }
        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        for(int i=0; i<current_obj; i++)
            if(world_obj[gp.currentMap][i]!=null && world_obj[gp.currentMap][i].current_state==4)
                world_obj[gp.currentMap][i]=null;

        for(int i=0; i<current_obj; i++)
            if(world_obj[0][i]!=null && solo_obj[world_obj[0][i].id].hit_type=="sword")
        gp.cChecker.check_object_sword_hit(i);
    }

    public void draw(Graphics2D g2)
    {

        if(gp.currentMap==0) {
            for (int i = 0; i < object_count; i++) {

                if (world_obj[gp.currentMap][i] != null && world_obj[gp.currentMap][i].current_state!=4) {

                    int screenX = world_obj[gp.currentMap][i].worldX - gp.player.worldX + gp.player.screenX;
                    int screenY = world_obj[gp.currentMap][i].worldY - gp.player.worldY + gp.player.screenY;

                    if (world_obj[gp.currentMap][i].worldX + gp.screenWidth > gp.player.worldX - gp.player.screenX &&
                            world_obj[gp.currentMap][i].worldX - gp.screenWidth < gp.player.worldX + gp.player.screenX &&
                            world_obj[gp.currentMap][i].worldY + gp.screenHeight > gp.player.worldY - gp.player.screenY &&
                            world_obj[gp.currentMap][i].worldY - gp.screenHeight < gp.player.worldY + gp.player.screenY) {
                        g2.drawImage(solo_obj[world_obj[0][i].id].image[world_obj[0][i].current_state], screenX, screenY, solo_obj[world_obj[0][i].id].height, solo_obj[world_obj[0][i].id].width, null);
                    }
                }
            }


        }

    }

}
