/******************************************************************************
 *  Compilation:  javac Voronoi.java
 *  Execution:    java Voronoi
 *  Dependencies: Draw.java DrawListener.java
 *
 *  Plots the points that the user clicks, and draws the Voronoi diagram.
 *  We assume the points lie on an M-by-M grid and use a brute force
 *  discretized algorithm. Each insertion takes time proportional to M^2.
 *
 *  Limitations
 *  -----------
 *    - Running time scales (badly) with M
 *    - Fortune's algorithm can compute a Voronoi diagram on N
 *      points in time proportional to N log N, but it is
 *      subtantially more complicated than this program which is intended
 *      to demonstrate callbacks and GUI operations.
 *      commit
 *
 ******************************************************************************/

import org.w3c.dom.css.RGBColor;

import java.awt.*;
import java.util.Random;

public class Voronoi implements DrawListener {
    private static int Y = 1000;
    private static int X = 1900;
    private Point[][] nearest = new Point[X][Y];  // which point is pixel (i, j) nearest? x then y

    private Draw draw = new Draw();


    public Voronoi() {
        draw.setCanvasSize(X, Y);
        draw.setXscale(0, X);
        draw.setYscale(0, Y);
        draw.addListener(this);
        draw.enableDoubleBuffering();
        draw.show();
        draw.pause(20);

        generateDots(10);

    }

    public void generateDots(int d1){

        for ( int d = 0; d < d1; d++) {


            Random rand = new Random();
            int x = rand.nextInt(1600);
            int y = rand.nextInt(800);


            Point clickPoint = new Point(x, y);
            //StdOut.println("Inserting:       " + clickPoint);

            //int rgb = rand.nextInt(255);
            // compare each pixel (i, j) and find nearest point
            draw.setPenColor(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));


            for (int i = 0; i < X; i++) {
                for (int j = 0; j < Y; j++) {
                    Point matrixDot = new Point(i, j);
                    if ((nearest[i][j] == null) || (matrixDot.distanceTo(clickPoint) < matrixDot.distanceTo(nearest[i][j]))) {
                        //System.out.println("matrixDot->clickPoint"+matrixDot.distanceTo(clickPoint));
                        nearest[i][j] = clickPoint;
                        //System.out.println(count);
                        //count++;
                        draw.filledSquare(i + 0.5, j + 0.5, 0.5);

                        //draw.filledSquare(i,2,1);
                    }
                }
            }

            // draw the point afterwards
            draw.setPenColor(Color.BLACK);
            draw.filledCircle(x, y, 2);
            draw.show();
            draw.pause(1);
            //StdOut.println("Done processing: " + clickPoint);
        }



    }

    public void mousePressed(double x, double y) {






        System.out.println("click cords = "+"x : "+x+" y : "+y);

        System.out.println("nearest dot = x : "+nearest[(int) x][(int) y].x()+" y : "+nearest[(int) x][(int) y].y());
    /*
        for (Point[] p:nearest) {
            for (Point p1:p) {
                System.out.println("Point = x : "+p1.x()+" y : "+p1.y());

            }

        }

     */
    }
    /**
     * z - 90 keycode
     * x - 88 keycode
     */
    @Override
    public void keyPressed(int keycode) {
          if(keycode == 90){
            System.out.println("90");


        } else if (keycode == 88) {
            System.out.println("88");


        }


    }

    // test client
    public static void main(String[] args) {
        new Voronoi();
    }


}