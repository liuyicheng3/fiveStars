package com.lyc.fivestars.starts;

import android.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.LinearLayout;


public class MainActivity extends ActionBarActivity {
    CutImageView fiveStars;
    double[] ds={0.3,0.5,2,4.5,5};
    LinearLayout ll_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ll_content = (LinearLayout) findViewById(R.id.ll_content);
        for(int j=0;j<ds.length;j++){
            fiveStars=new CutImageView(this);
            fiveStars.setStar(ds[j]);
            ll_content.addView(fiveStars);
            ViewGroup.LayoutParams lp=fiveStars.getLayoutParams();
            lp.width= android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT;
            lp.height=200;
            fiveStars.setStarWide(80);
            fiveStars.setLayoutParams(lp);
            fiveStars.setPosition(CutImageView.Position.bottom);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
