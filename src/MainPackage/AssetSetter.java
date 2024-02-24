package MainPackage;

import Enemy.Bull;
import Enemy.ChombBomb;
import Enemy.Hedgehog;
import Enemy.Pumpkin;

import java.util.Random;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp)
    {
        this.gp=gp;
    }

    public void setEnemy() {

        for (int i = 0; i < 6; i++) {

            gp.enemy[i] = new Hedgehog(gp);

            while (true) {
                Random random1 = new Random();

                int X = random1.nextInt(4200 - 2500) + 2500;

                Random random2 = new Random();

                int Y = random2.nextInt(6900 - 4176) + 4176;


                gp.enemy[i].worldX = X;
                gp.enemy[i].worldY = Y;

                gp.enemy[i].collisonOn=false;

            gp.cChecker.checkTile(gp.enemy[i]);
            gp.cChecker.check_decoraton(gp.enemy[i]);

            if(gp.enemy[i].collisonOn==false)
                break;

            }
        }

        for (int i = 6; i < 20; i++) {

            gp.enemy[i] = new ChombBomb(gp);

            while (true) {
                Random random1 = new Random();

                int X = random1.nextInt(8200 - 880) + 880;

                Random random2 = new Random();

                int Y = random2.nextInt(6900 - 3176) + 3176;


                gp.enemy[i].worldX = X;
                gp.enemy[i].worldY = Y;

                gp.enemy[i].collisonOn=false;

                gp.cChecker.checkTile(gp.enemy[i]);
                gp.cChecker.check_decoraton(gp.enemy[i]);

                if(gp.enemy[i].collisonOn==false)
                    break;

            }
        }

        for(int i=20; i<35; i++)
        {
            gp.enemy[i] = new ChombBomb(gp);

            gp.enemy[i].is_locked=true;
            gp.enemy[i].lock_high_x=3000;
            gp.enemy[i].lock_low_x=870;
            gp.enemy[i].lock_low_y=5600;
            gp.enemy[i].lock_high_y=7200;

            while (true) {
                Random random1 = new Random();

                int X = random1.nextInt(1776 - 880) + 880;

                Random random2 = new Random();

                int Y = random2.nextInt(6900 - 5832) + 5832;


                gp.enemy[i].worldX = X;
                gp.enemy[i].worldY = Y;

                gp.enemy[i].collisonOn=false;

                gp.cChecker.checkTile(gp.enemy[i]);
                gp.cChecker.check_decoraton(gp.enemy[i]);

                if(gp.enemy[i].collisonOn==false)
                    break;

            }




        }

        for(int i=35; i<50; i++)
        {
            gp.enemy[i] = new ChombBomb(gp);

            while (true) {
                Random random1 = new Random();

                int X = random1.nextInt( 8300- 3528) + 3528;

                Random random2 = new Random();

                int Y = random2.nextInt(3700 - 660) + 660;


                gp.enemy[i].worldX = X;
                gp.enemy[i].worldY = Y;

                gp.enemy[i].collisonOn=false;

                gp.cChecker.checkTile(gp.enemy[i]);
                gp.cChecker.check_decoraton(gp.enemy[i]);

                if(gp.enemy[i].collisonOn==false)
                    break;

            }
        }

        gp.enemy[40]= new Bull(gp);
        gp.enemy[40].worldX=1000;
        gp.enemy[40].worldY=7500;

        gp.enemy[41]= new Bull(gp);
        gp.enemy[41].worldX=1360;
        gp.enemy[41].worldY=8080;

        gp.enemy[42]= new Bull(gp);
        gp.enemy[42].worldX=1856;
        gp.enemy[42].worldY=7680;

        gp.enemy[43]= new Bull(gp);
        gp.enemy[43].worldX=1728;
        gp.enemy[43].worldY=7376;

        gp.enemy[44]= new Bull(gp);
        gp.enemy[44].worldX=2808;
        gp.enemy[44].worldY=8312;

        gp.enemy[45]= new Bull(gp);
        gp.enemy[45].worldX=3284;
        gp.enemy[45].worldY=8064;

        gp.enemy[46]= new Bull(gp);
        gp.enemy[46].worldX=3652;
        gp.enemy[46].worldY=7376;

        gp.enemy[47]= new Bull(gp);
        gp.enemy[47].worldX=3838;
        gp.enemy[47].worldY=7928;

        gp.enemy[48]= new Bull(gp);
        gp.enemy[48].worldX=4468;
        gp.enemy[48].worldY=7312;

        gp.enemy[49]= new Bull(gp);
        gp.enemy[49].worldX=5036;
        gp.enemy[49].worldY=7128;

        gp.enemy[50]= new Bull(gp);
        gp.enemy[50].worldX=1640;
        gp.enemy[50].worldY=7848;

        gp.enemy[51]= new Bull(gp);
        gp.enemy[51].worldX=2676;
        gp.enemy[51].worldY=8316;

        for(int i=52; i<80; i++)
        {
            gp.enemy[i] = new Pumpkin(gp);

            while (true) {
                Random random1 = new Random();

                int X = random1.nextInt( 7110- 6100) + 6100;

                Random random2 = new Random();

                int Y = random2.nextInt(4790 - 3260) + 3260;


                gp.enemy[i].worldX = X;
                gp.enemy[i].worldY = Y;

                gp.enemy[i].collisonOn=false;

                gp.cChecker.checkTile(gp.enemy[i]);
                gp.cChecker.check_decoraton(gp.enemy[i]);

                if(gp.enemy[i].collisonOn==false)
                    break;

            }
        }

    }
}
