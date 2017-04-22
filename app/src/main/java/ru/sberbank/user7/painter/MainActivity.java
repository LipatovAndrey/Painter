package ru.sberbank.user7.painter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    Button btnClear;
    PaintinfView paintinView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       paintinView =(ru.sberbank.user7.painter.PaintinfView) findViewById(R.id.paintinfView);
        btnClear = (Button) findViewById(R.id.button);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                paintinView.clear();
            }
        });

    }
}
