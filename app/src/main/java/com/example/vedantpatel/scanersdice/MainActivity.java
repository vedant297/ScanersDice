package com.example.vedantpatel.scanersdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

public class MainActivity extends AppCompatActivity {

    public int user_overall_score=0;
    public int user_turn_score=0;
    public int computer_overall_score=0;
    public int computer_turn_score=0;
    public int temp_current_score_user=0;
    public int temp_current_score_computer=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll_button=(Button)findViewById(R.id.roll);
        roll_button.setOnClickListener(roll_event);

        Button hold_button=(Button)findViewById(R.id.hold);
        hold_button.setOnClickListener(hold_event);

        Button reset_button=(Button)findViewById(R.id.reset);
        reset_button.setOnClickListener(reset_event);
    }

    private View.OnClickListener reset_event=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            computer_overall_score=0;
            user_overall_score=0;
            temp_current_score_user=0;
            temp_current_score_computer=0;

            TextView fake1=(TextView)findViewById(R.id.dis_yourcurrentscore);
            fake1.setText(String.valueOf(0));

            TextView temp_cu_score=(TextView)findViewById(R.id.dis_comcurrentscore);
            temp_cu_score.setText(String.valueOf(0));

            TextView user_score=(TextView)findViewById(R.id.dis_yourscore);
            user_score.setText(String.valueOf(0));

            TextView com_score=(TextView)findViewById(R.id.dis_computerscore);
            com_score.setText(String.valueOf(0));
        }
    };
    private View.OnClickListener hold_event=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            user_overall_score=user_overall_score+temp_current_score_user;
            temp_current_score_user=0;
            temp_current_score_computer=0;

            TextView fake=(TextView)findViewById(R.id.dis_yourcurrentscore);
            fake.setText(String.valueOf(temp_current_score_user));

            fake=(TextView)findViewById(R.id.dis_yourscore);
            fake.setText(String.valueOf(user_overall_score));

            computer_event();

            fake=(TextView)findViewById(R.id.dis_yourcurrentscore);
            fake.setText(String.valueOf(0));

            fake =(TextView)findViewById(R.id.dis_comcurrentscore);
            fake.setText(String.valueOf(temp_current_score_computer));
        }
    };

    private View.OnClickListener roll_event = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(MainActivity.this,"User Turn",Toast.LENGTH_SHORT).show();
            userroll();
        }
    };

    public void userroll(){
        user_turn_score=0;
        computer_overall_score+=computer_turn_score;
        computer_turn_score =0;

        TextView temp= (TextView)findViewById(R.id.dis_comcurrentscore);
        temp.setText(String.valueOf(computer_turn_score));

        android.os.Handler handler=new android.os.Handler();
        //final Toast toast=Toast.makeText(MainActivity.this,"User Turn",Toast.LENGTH_SHORT);
       // toast.show();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //toast.cancel();
            }
        },3000);

        int dice_number=new Random().nextInt(6)+1;

        TextView comp_score=(TextView)findViewById(R.id.dis_yourscore);

        ImageView image=(ImageView)findViewById(R.id.imageView);
        user_turn_score=dice_number;
        //long mili=1500;
        if(user_overall_score<100){

            switch (dice_number){
                case 1:
                    image.setImageResource(R.drawable.dice1);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            computer_event();

                        }
                    },2000);
                    break;
                case 2:
                    image.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    image.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    image.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    image.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    image.setImageResource(R.drawable.dice6);
                    break;
            }
            comp_score.setText(String.valueOf(user_overall_score));
            temp_current_score_user=temp_current_score_user+dice_number;
            TextView turn_score=(TextView)findViewById(R.id.dis_yourcurrentscore);
            turn_score.setText(String.valueOf(temp_current_score_user));
        }
        else {
            Toast.makeText(MainActivity.this,"You have won the game",Toast.LENGTH_SHORT).show();
        }
    }

    public void computer_event(){
        computer_turn_score=0;
        user_turn_score=0;
        TextView temp = (TextView)findViewById(R.id.dis_yourcurrentscore);
        temp.setText(String.valueOf(0));

       // Toast.makeText(MainActivity.this,"Computer Turn",Toast.LENGTH_SHORT).show();
        int dice_number=new Random().nextInt(6)+1;


        TextView your_score=(TextView)findViewById(R.id.dis_computerscore);

        ImageView image=(ImageView)findViewById(R.id.imageView);
        image.setImageResource(R.drawable.dice1);
        computer_turn_score=dice_number;



        if(user_overall_score<100){

            switch (dice_number){
                case 1:
                    image.setImageResource(R.drawable.dice1);
                    userroll();
                    break;
                case 2:
                    image.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    image.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    image.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    image.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    image.setImageResource(R.drawable.dice6);
                    break;
            }
            temp_current_score_computer=temp_current_score_computer+dice_number;
            computer_overall_score=computer_turn_score+computer_overall_score;

            TextView fake1=(TextView)findViewById(R.id.dis_comcurrentscore);
            fake1.setText(String.valueOf(temp_current_score_computer));
            your_score.setText(String.valueOf(computer_overall_score));
        }
        else {
            Toast.makeText(MainActivity.this,"You have won the game",Toast.LENGTH_SHORT).show();
        }


    }
}
