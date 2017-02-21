package single.touch.android.vogella.com.touchapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
//import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SingleTouchEventView extends View {
    private Paint paint = new Paint();
    //private Path path = new Path();
    private float pWidth;
    float [] hlines = new float[56];
    float [] vlines = new float[56];
    Cells[][] grid = new Cells[13][13];

    public SingleTouchEventView(Context context, AttributeSet attrs) {
        super(context, attrs);

        paint.setAntiAlias(true);
        paint.setStrokeWidth(6f);
        paint.setColor(Color.BLACK);
       // paint.setStyle(Paint.Style.STROKE);
        //paint.setStrokeJoin(Paint.Join.ROUND);
    }

    public void make_lines(){
        for (int i = 0; i<14;i++)
        {
            hlines[4*i] = 0;//start at the left side of the screen
            hlines[4*i+1] = (pWidth*(i))/13;//this is the y value
            hlines[4*i+2] = pWidth;//end at the right side of the screen
            hlines[4*i+3] = (pWidth*(i))/13;//this is the y value aas well

            vlines[4*i] = (pWidth*(i))/13;//this is the x value
            vlines[4*i+1] = 0;
            vlines[4*i+2] = (pWidth*(i))/13;//as well as thhhis
            vlines[4*i+3] = pWidth;
        }
    }

    public void make_cells() {
        float k, l;
        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {
                k = ((vlines[4*(j+1)]-vlines[4*j])/2)+vlines[4*j];
                l = ((hlines[4*(i+1)+1]-hlines[4*i+1])/2)+hlines[4*i+1];
                grid[i][j] = new Cells(i, j, k, l);
            }
        }
    }

    public void draw_circles(Canvas canvas) {


        for (int i = 0; i < 13; i++) {
            for (int j = 0; j < 13; j++) {

                if((grid[i][j]).this_life_status){
                canvas.drawCircle((grid[i][j]).x_center_point,
                        (grid[i][j]).y_center_point, 25, paint);}
            }

        }
    }


    //this method shouuld map a touch event to  Cells object, then perhaps toggle the
    public void map_touch_event(float x,float y)

    {

        int i= 0, j= 0;
        while(x > vlines[4*i]){i++;}
        while(y > hlines[4*j+1]){j++;}
        //grid [i-1][j-1] will be the cell to toggle
        (grid[i-1][j-1]).toggle_cell();

    }




    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        pWidth = MeasureSpec.getSize(widthMeasureSpec);
        //int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
       // pWidth = parentWidth;
        //pHeight = parentHeight;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        make_lines();
        make_cells();
        canvas.drawColor(Color.WHITE);
        canvas.drawLines(hlines, paint);
        canvas.drawLines(vlines, paint);
        draw_circles(canvas);

        //canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();
        //i need to map the location of the event to a cell
        map_touch_event(eventX,eventY);

       /* switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(eventX, eventY);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(eventX, eventY);
                break;
            case MotionEvent.ACTION_UP:
                // nothing to do
                break;
            default:
                return false;
        }*/

        // Schedules a repaint.
        invalidate();
        return true;
    }

}