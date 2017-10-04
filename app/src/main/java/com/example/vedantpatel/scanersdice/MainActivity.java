package com.example.vedantpatel.scanersdice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int user_overall_score=0;
    public int user_turn_score=0;
    public int computer_overall_score=0;
    public int computer_turn_score=0;
    public int fexit=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button roll = (Button)findViewById(R.id.roll);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                userroll();
            }
        });


        Button Hold = (Button)findViewById(R.id.hold);
        Hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                user_overall_score = user_overall_score+user_turn_score;


                TextView textView = (TextView)findViewById(R.id.dis_yourscore);
                textView.setText(String.valueOf(user_overall_score));

                user_turn_score=0;
                textView=(TextView)findViewById(R.id.dis_yourcurrentscore);
                textView.setText(String.valueOf(user_turn_score));


                computer_event();

            }
        });

        Button Roll = (Button) findViewById(R.id.roll);
        Roll.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MainActivity.this,"computer score "+computer_turn_score,Toast.LENGTH_SHORT).show();
                computer_overall_score = computer_overall_score+computer_turn_score;
                computer_turn_score=0;

                TextView textView = (TextView)findViewById(R.id.dis_comcurrentscore);
                textView.setText(String.valueOf(computer_turn_score));

                textView= (TextView)findViewById(R.id.dis_computerscore);
                textView.setText(String.valueOf(computer_overall_score));

                userroll();

            }
        });

        Button Reset = (Button)findViewById(R.id.reset);
        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                user_turn_score=0;
                user_overall_score=0;
                computer_turn_score=0;
                computer_overall_score=0;

                TextView textView = (TextView) findViewById(R.id.dis_yourcurrentscore);
                textView.setText(String.valueOf(user_turn_score));

                textView = (TextView) findViewById(R.id.dis_yourscore);
                textView.setText(String.valueOf(user_overall_score));

                textView = (TextView) findViewById(R.id.dis_comcurrentscore);
                textView.setText(String.valueOf(computer_turn_score));

                textView = (TextView) findViewById(R.id.dis_computerscore);
                textView.setText(String.valueOf(computer_overall_score));
            }
        });
    }



    public void userroll(){


        android.os.Handler handler=new android.os.Handler();

        TextView textView = (TextView)findViewById(R.id.dis_yourcurrentscore);
        textView.setText(String.valueOf(user_turn_score));

        computer_turn_score=0;


        int dice_number=new Random().nextInt(6)+1;

        ImageView image=(ImageView)findViewById(R.id.imageView);
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
                    },800);
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

            user_turn_score = user_turn_score+dice_number;

            textView = (TextView)findViewById(R.id.dis_yourcurrentscore);
            textView.setText(String.valueOf(user_turn_score));

        }
        else {
            Toast.makeText(MainActivity.this,"You have won the game",Toast.LENGTH_SHORT).show();
        }
    }

    public void computer_event(){


            int dice_number=new Random().nextInt(6)+1;


            ImageView image=(ImageView)findViewById(R.id.imageView);
            image.setImageResource(R.drawable.dice1);

            user_turn_score=0;
            TextView temp = (TextView)findViewById(R.id.dis_yourcurrentscore);
            temp.setText(String.valueOf(user_turn_score));



            if(computer_overall_score<100)
            {

                switch (dice_number){
                    case 1:
                        image.setImageResource(R.drawable.dice1);
                        computer_turn_score=0;
                        temp = (TextView)findViewById(R.id.dis_comcurrentscore);
                        temp.setText(String.valueOf(0));

                        Toast.makeText(MainActivity.this,"Roll over"+computer_turn_score,Toast.LENGTH_SHORT).show();
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

                computer_turn_score = computer_turn_score+dice_number;

                temp = (TextView)findViewById(R.id.dis_comcurrentscore);
                temp.setText(String.valueOf(computer_turn_score));


            }
            else {
                Toast.makeText(MainActivity.this,"You have won the game",Toast.LENGTH_SHORT).show();
            }
        }




}
