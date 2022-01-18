package com.ray.clock;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;

public class JFrameExt extends JFrame
{
    private static final long serialVersionUID = 1L;
    Point loc = null;
    Point tmp = null;
    boolean isDragged = false;
    public final static int RESIZE_WIDTH=5;
    public final static int MIN_WIDTH=20;
    public final static int MIN_HEIGHT=20;
    boolean isTop=false;
        boolean isTopRight=false;
        boolean isRight=false;
        boolean isBottomRight=false;
        boolean isBottom=false;
        boolean isBottomLeft=false;
        boolean isLeft=false;
        boolean isTopLeft=false;
        
        
        
    public JFrameExt() {
    //this.firstCon=firstCon;
    this.setDragable();
    }

    private void setDragable() {
    this.addMouseListener(new MouseLis());
        this.addMouseMotionListener(new MouseMotion());
    }

    class MouseLis extends MouseAdapter {
    public void mouseReleased(MouseEvent e) {

    // super.mouseReleased(arg0);
    isDragged = false;
    JFrameExt.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    // Component.

    }

    public void mousePressed(MouseEvent e) {

    // super.mousePressed(arg0);
    //System.out.println("pX:"+e.getX()+" pY:"+e.getY());
    int x=e.getX();
        int y=e.getY();
        int width=JFrameExt.this.getWidth();
        int height=JFrameExt.this.getHeight();
        int RESIZE_WIDTH=JFrameExt.RESIZE_WIDTH;
        tmp = new Point(e.getX(), e.getY());
    if(!(x<=RESIZE_WIDTH||x>(width-RESIZE_WIDTH)||y<=RESIZE_WIDTH||y>(height-RESIZE_WIDTH))){

    isDragged = true;
    JFrameExt.this.setCursor(new Cursor(Cursor.MOVE_CURSOR));
    }
    }



    }

    class MouseMotion extends MouseMotionAdapter{

    public void mouseDragged(MouseEvent e){


    if(isDragged){
    loc=new Point(JFrameExt.this.getLocation().x+e.getX()-tmp.x,JFrameExt.this.getLocation().y+e.getY()-tmp.y);
    JFrameExt.this.setLocation(loc);
    //System.out.println("");
    }else{
    //if((JFrameExt.this.getWidth()>JFrameExt.MIN_WIDTH||JFrameExt.this.getHeight()>JFrameExt.MIN_HEIGHT)){
        /*  if(isRight&&((JFrameExt.this.getWidth()>JFrameExt.MIN_WIDTH)||(e.getX()-tmp.x)>0)){
        //  JFrameExt.this.setSize(e.getX(),JFrameExt.this.getHeight());
            JFrameExt.this.setBounds(JFrameExt.this.getX(), JFrameExt.this.getY(), e.getX(),JFrameExt.this.getHeight());
            }else if(isBottom&&((JFrameExt.this.getHeight()>JFrameExt.MIN_HEIGHT)||(e.getY()-tmp.y)>0)){
        JFrameExt.this.setSize(JFrameExt.this.getWidth(),e.getY());
            }else if(isBottomRight){
        JFrameExt.this.setSize(e.getX(), e.getY());
            }*/
    //System.out.println("dX:"+e.getX()+" dY:"+e.getY());
    int x=e.getX();
    int y=e.getY();
    int width=JFrameExt.this.getWidth();
    int height=JFrameExt.this.getHeight();
    int nextX=JFrameExt.this.getX();
    int nextY=JFrameExt.this.getY();
    int nextWidth=JFrameExt.this.getWidth();
    int nextHeight=JFrameExt.this.getHeight();
    if(isTopLeft||isLeft||isBottomLeft){
    nextX+=x;
    nextWidth-=x;
    }
    if(isTopLeft|isTop||isTopRight){
    nextY+=y;
    nextHeight-=y;
    }
    if(isTopRight||isRight||isBottomRight){
    nextWidth=x;
    }
    if(isBottomLeft||isBottom||isBottomRight){
    nextHeight=y;
    }
    if(nextWidth<=JFrameExt.MIN_WIDTH){
    nextWidth=JFrameExt.MIN_WIDTH;

    if(isTopLeft||isLeft||isBottomLeft){
    nextX=JFrameExt.this.getX()+width-nextWidth;
    }

    }
    if(nextHeight<=JFrameExt.MIN_HEIGHT){
    nextHeight=JFrameExt.MIN_HEIGHT;
    if(isTop||isTopLeft||isTopRight){
    nextY=JFrameExt.this.getY()+height-nextHeight;
    }

    }
    JFrameExt.this.setBounds(nextX, nextY, nextWidth, nextHeight);
    //}
    //System.out.println("nX:"+nextX+" nY:"+nextY);
    }


    }
        public void mouseMoved(MouseEvent e) {
        
            // super.mouseMoved(arg0);
            // System.out.println(e.getX());
        int x=e.getX();
        int y=e.getY();
        int width=JFrameExt.this.getWidth();
        int height=JFrameExt.this.getHeight();
        int cursorType=Cursor.DEFAULT_CURSOR;
        int RESIZE_WIDTH=JFrameExt.RESIZE_WIDTH;
        isTop=false;
        isTopRight=false;
        isRight=false;
        isBottomRight=false;
        isBottom=false;
        isBottomLeft=false;
        isLeft=false;
        isTopLeft=false;
        
        if(x>RESIZE_WIDTH&&x<(width-RESIZE_WIDTH)&&y<=RESIZE_WIDTH){
        isTop=true;
        cursorType=Cursor.N_RESIZE_CURSOR;
        }else if(x>(width-RESIZE_WIDTH)&&y<=RESIZE_WIDTH){
        isTopRight=true;
        cursorType=Cursor.NE_RESIZE_CURSOR;
        }else if(x>(width-RESIZE_WIDTH)&&y>RESIZE_WIDTH&&y<(height-RESIZE_WIDTH)){
        isRight=true;
        cursorType=Cursor.E_RESIZE_CURSOR;
        }else if(x>(width-RESIZE_WIDTH)&&y>(height-RESIZE_WIDTH)){
        isBottomRight=true;
        cursorType=Cursor.SE_RESIZE_CURSOR;
        }else if(x>RESIZE_WIDTH&&x<(width-RESIZE_WIDTH)&&y>(height-RESIZE_WIDTH)){
        isBottom=true;
        cursorType=Cursor.S_RESIZE_CURSOR;
        }else if(x<=RESIZE_WIDTH&&y>(height-RESIZE_WIDTH)){
        isBottomLeft=true;
        cursorType=Cursor.SW_RESIZE_CURSOR;
        }else if(x<=RESIZE_WIDTH&&y>RESIZE_WIDTH&&y<(height-RESIZE_WIDTH)){
        isLeft=true;
        cursorType=Cursor.W_RESIZE_CURSOR;
        
        }else if(x<=RESIZE_WIDTH&&y<=RESIZE_WIDTH){
        isTopLeft=true;
        cursorType=Cursor.NW_RESIZE_CURSOR;
        }
        JFrameExt.this.setCursor(new Cursor(cursorType));
        
        
        
                
        }


    }





}