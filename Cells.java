package single.touch.android.vogella.com.touchapp;

/**
 * Created by Hugo Quiroz on 2/20/2017.
 */

public class Cells {

    int X;
    int Y;
    float x_center_point;
    float y_center_point;
    boolean this_life_status = false;
    boolean next_life_status = false;
 //   boolean north, northe, east, southe, south, southw, west, northw;

    public Cells(int a, int b, float c, float d){
        X = a;
        Y = b;
        x_center_point =c;
        y_center_point = d;
    }


    public void toggle_cell()//this method toggles the life status of a cell
    {

        if(this_life_status==true){
            this_life_status = false;
        }
        else{
            this_life_status = true;
        }
    }



/*
    public void check_neighbor_cells(){

        int live_neighbors = 0;

        //if((grid[X+1][Y+1]).this_life_status)




        //if our cell is currently alive then check some more stuff
        if (this_life_status == true)
        {
            //celll dies due to overpoppulation
            if(live_neighbors>3)
            {
                next_life_status = false;
            }
            //cell dies if it gets lonely
            if(live_neighbors<2)
            {
                next_life_status = false;
            }
        }

        //this is for a currently dead cell that will be born next cycle
        if(this_life_status == false && live_neighbors == 3){
            next_life_status = true;
        }

    }
*/

    }
