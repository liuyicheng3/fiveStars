package com.lyc.fivestars.starts;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lyc on 14-10-19.
 */
public class CutImageView extends View {
    int full_wide,full_height;
    int starWide=0,headerWide,footerWide,speratorWide=0;
    double star=0.1;
    int maxStar=5;
    int cover_wide=0,uncover_wide=0;
    BitmapDrawable  bd_on,bd_off;
    Paint paint;
    Context ctx;
    public enum Position{UP,Middle,bottom};
    Position position=Position.Middle;

    public CutImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint=new Paint();
        ctx=context;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void setBd_on(BitmapDrawable bd_on) {
        this.bd_on = bd_on;
    }

    public void setBd_off(BitmapDrawable bd_off) {
        this.bd_off = bd_off;
    }

    public void setSepratorWide(int wide){
        speratorWide=wide;

    }

    public void setStarWide(int wide){
        starWide=wide;

    }
    public void setHeaderWide(int wide){
        headerWide=wide;
    }
    public void setFooterWide(int wide){
        footerWide=wide;

    }

    public CutImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        paint=new Paint();
        ctx=context;
    }

    public void setStar(double star){
        this.star=star;
    }


    public CutImageView(Context context) {
        super(context);
        paint=new Paint();
        ctx=context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void intParams(Bitmap bmp){
        full_wide =bmp.getWidth();
        full_height=bmp.getHeight();
        if(starWide==0){
            starWide=(full_wide-footerWide-headerWide-4*speratorWide)/5;
        }else {
            speratorWide=(full_wide-footerWide-headerWide-5*speratorWide)/4;
        }
        cover_wide= (int) (headerWide+star*starWide);
        uncover_wide=full_wide-cover_wide;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(bd_on==null){
            bd_on=(BitmapDrawable)ctx.getResources().getDrawable(R.drawable.full);
        }
        if(bd_off==null){
            bd_off=(BitmapDrawable)ctx.getResources().getDrawable(R.drawable.empty);
        }
        Bitmap bmp=bd_on.getBitmap();
        intParams(bmp);


        double d1=(double)full_height/(double)full_wide;
        double d2=(double)getHeight()/(double)getWidth();
        int jz=0;
        int _wide=getWidth();
        if(d2-d1>0.01){
            jz= (int) (getHeight()-getWidth()*d1);
        }else if(d2-d1<-0.01){
            _wide= (int) ((double)full_wide/(double)full_height*getHeight());
        }else {
            _wide=getWidth();
        }
        switch (position){
            case Middle:
                jz=jz/2;
               break;
            case UP:
                jz=0;
                break;
            case bottom:
            break;

        }

        int target_height= (int) (_wide*d1);
        int target_coverWide= (int) ((double)cover_wide/(double)full_wide*(double)_wide);
        Rect src_rect1=new Rect(0,0,cover_wide,full_height);

        Rect target_rect1=new Rect(0,0+jz,target_coverWide,target_height+jz);
        canvas.drawBitmap(bmp,src_rect1,target_rect1,paint);
        Bitmap bmp2=bd_off.getBitmap();
        Rect src_rect2=new Rect(full_wide -uncover_wide,0, full_wide,full_height);
        Rect target_rect2=new Rect(target_coverWide,jz, _wide,target_height+jz);
        canvas.drawBitmap(bmp2, src_rect2, target_rect2, paint);

    }
}
