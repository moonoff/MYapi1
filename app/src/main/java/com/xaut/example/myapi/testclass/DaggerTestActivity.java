package com.xaut.example.myapi.testclass;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.xaut.example.myapi.R;

import javax.inject.Inject;

public class DaggerTestActivity extends AppCompatActivity {

    @Inject
    Cloth cloth;
    @Inject
    Shoe shoe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView dagger_test_textview = (TextView) findViewById(R.id.dagger_test_textview);


        DaggerTestComponent daggerTestComponent = DaggerDaggerTestComponent.builder().daggerTestModle(new DaggerTestModle()).build();
        daggerTestComponent.inject(this);
        dagger_test_textview.setText(cloth + "+++" + shoe);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
