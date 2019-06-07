package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Handler mHandler = new Handler();
    private TextView countText;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button=findViewById(R.id.button3);//ボタンの設定(Timer開始ボタンにも)
        TextView textView2=findViewById(R.id.textView);
        final SharedPreferences[] datastore = new SharedPreferences[1];

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               EditText editText=(EditText)findViewById(R.id.editText); //入力値
               SharedPreferences preferences = getSharedPreferences("storedata",MODE_PRIVATE); //データの保存


                int a = preferences.getInt("count",0);;
                int b = Integer.parseInt(editText.getText().toString());
                int c = a + b;

                Timer timer = new Timer();
                TimerTask timertask = new TimerTask() {
                    @Override
                    //public class TimerTask extends Teimer{
                    public void run() {
                        mHandler.post(new Runnable() {
                            public void run() {
                                count += 1;
                                countText.setText(String.valueOf(count));
                            }
                        });
                    }

                };
                //};


                //ストック数保存
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("count",c);
                editor.commit();



                TextView textView1 = (TextView)findViewById(R.id.textView);
                textView1.setText(String.valueOf(c));



            }


        });
    }
}
