package com.example.girl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_a0;
    private ImageView iv_b0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_a0 = (ImageView) findViewById(R.id.iv_a0);
        iv_b0 = (ImageView) findViewById(R.id.iv_b0);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;
        Bitmap bitmapA0 = BitmapFactory.decodeResource(getResources(),R.drawable.a0, options);
        Bitmap bitmapB0 = BitmapFactory.decodeResource(getResources(), R.drawable.b0, options);
        iv_a0.setImageBitmap(bitmapA0);
        iv_b0.setImageBitmap(bitmapB0);

        final Bitmap bitmapAlter = Bitmap.createBitmap(bitmapA0.getWidth(), bitmapA0.getHeight(), bitmapA0.getConfig());
        Canvas canvas = new Canvas(bitmapAlter);
        canvas.drawBitmap(bitmapB0, new Matrix(), new Paint());

        iv_a0.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        System.out.println("绘画中");
                        int x = (int) event.getX();
                        int y = (int) event.getY();
                        for (int i = -28; i < 28; i++)
                            for (int j = -28; j < 28; j++) {
                                bitmapAlter.setPixel(x + i, y + j, Color.TRANSPARENT);
                            }
                        iv_b0.setImageBitmap(bitmapAlter);
                        break;
                }
                return true;
            }
        });
    }
}
