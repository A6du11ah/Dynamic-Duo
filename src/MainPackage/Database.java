package MainPackage;

import java.sql.*;

public class Database {

    GamePanel gp;

    String user="RDBMS_project";
    String password="1138";
    public Database(GamePanel gp)
    {
        this.gp=gp;
    }

    private ResultSet getQueryResult (String query) {
        ResultSet rs = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);
            Statement stmt = con.createStatement();

            rs = stmt.executeQuery(query);
        } catch(Exception e)
        {
            System.out.println(e);
        }

        return rs;
    }

    public void getPlayer () throws SQLException {

        ResultSet rs = getQueryResult("select * from player where save_game_id = " + gp.save_game_id);



        if(rs!=null)
        while(rs.next()){
            gp.player.BoyWorldX = rs.getInt(2);
            gp.player.BoyWorldY = rs.getInt(3);
            gp.player.GirlWorldX = rs.getInt(4);
            gp.player.GirlWOrldY = rs.getInt(5);
            gp.player.current_health=rs.getInt(6);
            gp.player.girl_current_health=rs.getInt(7);

            System.out.println(rs.getInt(2)+","+ rs.getInt(3)+","+ rs.getInt(4)+","+ rs.getInt(5));
        }
    }

    public void deleteGame(int delete_id) throws SQLException{

        getQueryResult("delete from save_game where id = " + delete_id);
        getQueryResult("delete from player where save_game_id =  " + delete_id);
        getQueryResult("delete from object_status where save_game_id = " + delete_id);
        getQueryResult("update save_game set id=id-1 where id > " + delete_id);
        getQueryResult("update player set save_game_id=save_game_id-1 where save_game_id > " + delete_id);
        getQueryResult("update object_status set save_game_id=save_game_id-1 where save_game_id > " + delete_id);

        gp.total_save_game--;



    }

    public void getSaveGames() throws SQLException {

        ResultSet rs = getQueryResult("Select nvl(count(*),0) from save_game");

        while(rs.next()){

            gp.total_save_game=rs.getInt(1);
        }

         rs = getQueryResult("Select * from save_game");

        int i=0;

        if(rs!=null)
        while(rs.next())
        {
            gp.saveLoading.save[i].save_id=rs.getInt(1);
            gp.saveLoading.save[i].save_name=rs.getString(2);
            gp.saveLoading.save[i].last_saved=rs.getString(3);
            gp.saveLoading.save[i].save_time=rs.getInt(4);
            gp.saveLoading.save[i].location=rs.getString(5);

            i++;
        }

    }

    public void updateSaveGames() throws SQLException {



        ResultSet rs = getQueryResult("Select * from save_game");

        int i=0;

        if(rs!=null)
        while(rs.next())
        {
            gp.saveLoading.save[i].save_id=rs.getInt(1);
            gp.saveLoading.save[i].save_name=rs.getString(2);
            gp.saveLoading.save[i].last_saved=rs.getString(3);
            gp.saveLoading.save[i].save_time=rs.getInt(4);
            gp.saveLoading.save[i].location=rs.getString(5);

            i++;
        }

    }



    public void createNewGames () throws SQLException
    {



        int id= gp.total_save_game;

        String location="Unknown";
        String name="Quest Name";
        String last_save= "to_char(sysdate, 'MONTH DD, YYYY HH:MI PM')";
        int game_time=0;





        gp.total_save_game++;

        //String upd = "insert into save_game values (" +id + ",'" + name+"','"+last_save+"',"+game_time+",'" +location+  "')";
        String upd = "insert into save_game values ("+id+", '"+ name+"',"+last_save+" , "+game_time+", '"+location+"')";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);

            Statement stmt = con.createStatement();

            stmt.executeUpdate(upd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public void overwriteSaveGames () throws SQLException
    {



        int id= gp.save_game_id;

        String location="Unknown";
        String name="Quest Name";
        String last_save= "to_char(sysdate, 'MONTH DD, YYYY HH:MI PM')";
        int game_time=0;


        getQueryResult("delete from save_game where id =  " + id);




        //String upd = "insert into save_game values (" +id + ",'" + name+"','"+last_save+"',"+game_time+",'" +location+  "')";
        String upd = "insert into save_game values ("+id+", '"+ name+"',"+last_save+" , "+game_time+", '"+location+"')";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);

            Statement stmt = con.createStatement();

            stmt.executeUpdate(upd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }




    public void getObject () throws SQLException {

        ResultSet rs = getQueryResult("select * from object_status where save_game_id = " + gp.save_game_id);





        while(rs.next()){

            gp.objM.world_obj[0][rs.getInt(1)].current_state=rs.getInt(2);


        }


    }

    public void setPlayer () throws SQLException {

        getQueryResult("delete from player where save_game_id =  " + gp.save_game_id);

        String upd = "insert into player values (" +gp.save_game_id+", " +gp.player.BoyWorldX + "," + gp.player.BoyWorldY + "," + gp.player.GirlWorldX + ","+ gp.player.GirlWOrldY+ ", "+ gp.player.current_health+ ", "+ gp.player.girl_current_health+  ","+gp.save_game_id+")";
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);

            Statement stmt = con.createStatement();

            stmt.executeUpdate(upd);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


        }

        public void setObject () throws SQLException{

            getQueryResult("delete from object_status where save_game_id = " + gp.save_game_id);


                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", user, password);

                    Statement stmt = con.createStatement();

                    for(int i=0; i<gp.objM.current_obj; i++) {
                        if(gp.objM.world_obj[0][i]!=null) {

                            String upd = "insert into object_status values (" + i + "," + gp.objM.world_obj[0][i].current_state + ","+gp.save_game_id+")";
                            stmt.executeUpdate(upd);
                        }
                        else
                        {
                            String upd = "insert into object_status values (" + i + ",4,"+gp.save_game_id+")";
                            stmt.executeUpdate(upd);
                        }

                    }
                } catch (SQLException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }




    }

