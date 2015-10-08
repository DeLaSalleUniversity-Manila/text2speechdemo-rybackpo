package com.example.rybackpo.texttospeech;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> Texts;
    private TextToSpeech txttospeech;
    private boolean textloaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextToSpeech();
        getTextFromFile();
        setupListView();
    }

    private void initTextToSpeech(){
        txttospeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                textloaded = true;
            }
        });
    }

    // Get Text from the text file
    private void getTextFromFile(){
        Texts = new ArrayList<String>();
        Scanner sc = new Scanner(getResources().openRawResource(R.raw.text));
        while(sc.hasNextLine()){
            String words = sc.nextLine();
            Texts.add(words);
        }
    }

    // Setups the List of the ListView
    private void setupListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Texts);
        ListView textList = (ListView) findViewById(R.id.textListView);
        textList.setAdapter(adapter);
        textList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onClick(position);
            }
        });
    }

    private void onClick(int index){
        String textline = Texts.get(index);
        if(textloaded){
            txttospeech.setSpeechRate(0.6f);
            txttospeech.speak(textline, TextToSpeech.QUEUE_FLUSH, null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
