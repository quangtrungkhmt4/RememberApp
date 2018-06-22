package com.example.quang.remember;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.quang.remember.model.Color;
import com.example.quang.remember.views.MyCanvasView;


public class DrawActivity extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private ViewGroup viewGroup;
    private MyCanvasView myCanvasView;

    private ImageButton btnBackLine;
    private ImageButton btnNextLine;
    private ImageButton btnChangeColor;
    private ImageButton btnChangeStroke;
    private ImageButton btnDone;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.draw_activity);

        findID();
        initViews();

    }



    private void findID() {
        toolbar = findViewById(R.id.toolbarDraw);
        viewGroup = findViewById(R.id.lnGroup);
        btnBackLine = findViewById(R.id.btnBackDraw);
        btnNextLine = findViewById(R.id.btnNextDraw);
        btnChangeColor = findViewById(R.id.btnColorDraw);
        btnChangeStroke = findViewById(R.id.btnStrokeDraw);
        btnDone = findViewById(R.id.btnDoneDraw);
    }

    private void initViews() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        myCanvasView = new MyCanvasView(this);
        myCanvasView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                , ViewGroup.LayoutParams.MATCH_PARENT));
        viewGroup.addView(myCanvasView);

        btnDone.setOnClickListener(this);
        btnNextLine.setOnClickListener(this);
        btnBackLine.setOnClickListener(this);
        btnChangeStroke.setOnClickListener(this);
        btnChangeColor.setOnClickListener(this);




    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnBackDraw:
                myCanvasView.onClickUndo();
                break;
            case R.id.btnNextDraw:
                myCanvasView.onClickRedo();
                break;
            case R.id.btnColorDraw:
                myCanvasView.setLineColor();
                break;
            case R.id.btnStrokeDraw:
                myCanvasView.setSWidth();
                break;
            case R.id.btnDoneDraw:

                break;
        }
    }
}
