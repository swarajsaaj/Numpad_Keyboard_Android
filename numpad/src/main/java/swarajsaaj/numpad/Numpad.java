package swarajsaaj.numpad;

import android.annotation.TargetApi;
import android.app.Instrumentation;
import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by swarajpal on 07-05-2016.
 */
public class Numpad extends LinearLayout implements View.OnClickListener {

    Instrumentation it = new Instrumentation();
    List<View> keys = new ArrayList<>();

    private NumpadHandler mNumpadHandler;

    public Numpad(Context context) {
        super(context);
        initLayout(context);
    }

    public Numpad(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public Numpad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Numpad(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initLayout(context);
    }

    public void initLayout(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.numpad, this, true);

        TextView key1 = (TextView) findViewById(R.id.key1);
        TextView key2 = (TextView) findViewById(R.id.key2);
        TextView key3 = (TextView) findViewById(R.id.key3);

        TextView key4 = (TextView) findViewById(R.id.key4);
        TextView key5 = (TextView) findViewById(R.id.key5);
        TextView key6 = (TextView) findViewById(R.id.key6);

        TextView key7 = (TextView) findViewById(R.id.key7);
        TextView key8 = (TextView) findViewById(R.id.key8);
        TextView key9 = (TextView) findViewById(R.id.key9);

        ImageView keyBackspace = (ImageView) findViewById(R.id.keyBackSpace);
        TextView key0 = (TextView) findViewById(R.id.key0);
        ImageView keyNext = (ImageView) findViewById(R.id.keyNext);

        keys.add(key1);
        keys.add(key2);
        keys.add(key3);
        keys.add(key4);
        keys.add(key5);
        keys.add(key6);
        keys.add(key7);
        keys.add(key8);
        keys.add(key9);
        keys.add(key0);
        keys.add(keyBackspace);
        keys.add(keyNext);



        for(View curKey : keys){
            curKey.setOnClickListener(this);
        }
    }

    /**
     * Bind the handler to the step after entering the pin
     * @param handler
     */
    public void bindNextStepHandler(NumpadHandler handler) {
        this.mNumpadHandler = handler;
    }

    @Override
    public void onClick(View view) {
        int pressedKeyId = view.getId();
        Log.d("Numpad","Key pressed : "+pressedKeyId);
        //Need to replace with if else ladder from switch due to R ids not final in ADT14.
        if (pressedKeyId == R.id.key1) {
            pressKey(KeyEvent.KEYCODE_1);

        } else if (pressedKeyId == R.id.key2) {
            pressKey(KeyEvent.KEYCODE_2);

        } else if (pressedKeyId == R.id.key3) {
            pressKey(KeyEvent.KEYCODE_3);

        } else if (pressedKeyId == R.id.key4) {
            pressKey(KeyEvent.KEYCODE_4);

        } else if (pressedKeyId == R.id.key5) {
            pressKey(KeyEvent.KEYCODE_5);

        } else if (pressedKeyId == R.id.key6) {
            pressKey(KeyEvent.KEYCODE_6);

        } else if (pressedKeyId == R.id.key7) {
            pressKey(KeyEvent.KEYCODE_7);

        } else if (pressedKeyId == R.id.key8) {
            pressKey(KeyEvent.KEYCODE_8);

        } else if (pressedKeyId == R.id.key9) {
            pressKey(KeyEvent.KEYCODE_9);

        } else if (pressedKeyId == R.id.keyBackSpace) {
            pressKey(KeyEvent.KEYCODE_DEL);

        } else if (pressedKeyId == R.id.key0) {
            pressKey(KeyEvent.KEYCODE_0);
        } else if (pressedKeyId == R.id.keyNext) {
            moveToNext();

        }
    }

    private void moveToNext() {
        if (mNumpadHandler != null) {
            mNumpadHandler.nextStep();
        }
    }

    public void pressKey(final int keycode) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                it.sendCharacterSync(keycode);
            }
        }).start();
    }

    /**
     * Sets the color of the text and icons in numpad.
     * @param colorId
     */
    public void setIconsColor(int colorId) {
        for(View v:keys){
            if(v instanceof TextView){
                ((TextView)v).setTextColor(colorId);
            }else{
                ((ImageView)v).setColorFilter(colorId, PorterDuff.Mode.MULTIPLY);
            }
        }
    }
}
