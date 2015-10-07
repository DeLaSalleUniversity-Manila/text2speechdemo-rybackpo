# Text-to-Speech App (converts text into Speech)

text2speechdemo-rybackpo created by Classroom for GitHub

This assignment illustrates the conversion of text into speech in Android platform. It also introduces a ListView widget.

## Problem:

Design and implement an Android app that takes a text file of ESL Telephone Alphabet phrases as input, then, outputs the texts in a ListView in which when clicked outputs speech.  

text.txt
```text
A is for Apple
B is for Boy
C is for Cat
D is for Dog
E is for East
F is for Five
G is for Goat
H is for House
I is for Ice cream
J is for July
K is for King
L is for Lemon
M is for Money
N is for Number
O is for Open
P is for People
Q is for Queen
R is for Red
S is for Summer
T is for Time
U is for Uniform
V is for Visa
W is for Woman
X is for X-ray
Y is for Yellow
Z is for Zebra
```

## Sample Solution:

https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-rybackpo

## Keypoints:

Initialize the TextToSpeech Object:

```Java
private void initTextToSpeech(){
    txttospeech = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
          @Override
          public void onInit(int status) {
              textloaded = true;
          }
    });
}
```

Load the Texts from text file:

```Java
private void getTextFromFile(){
    Texts = new ArrayList<String>();
    Scanner sc = new Scanner(getResources().openRawResource(R.raw.text));
    while(sc.hasNextLine()){
        String words = sc.nextLine();
        Texts.add(words);
    }
}
```

Set-up the ListView:
```Java
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
```

Handle the clicks:
```Java
private void onClick(int index){
    String textline = Texts.get(index);
    if(textloaded){
        txttospeech.setSpeechRate(0.6f);
        txttospeech.speak(textline, TextToSpeech.QUEUE_FLUSH, null);
    }
}
```

## Screenshots:

![alt tag](https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-rybackpo/blob/master/device-2015-10-07-170437.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-rybackpo/blob/master/device-2015-10-07-170457.png)

![alt tag](https://github.com/DeLaSalleUniversity-Manila/text2speechdemo-rybackpo/blob/master/device-2015-10-07-170507.png)
