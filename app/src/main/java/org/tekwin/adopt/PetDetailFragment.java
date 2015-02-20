package org.tekwin.adopt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.tekwin.adopt.dummy.CompanyContent;

import java.util.ArrayList;


public class PetDetailFragment extends Fragment implements View.OnClickListener {
    public static final int VOICE_RECOGNITION_REQUEST_CODE = 1;
    public static final String ARG_ITEM_ID = "item_id";


    EditText textinput;
    TextView chat;
    ListView lv;



    private CompanyContent.CompanyListItem mItem;
    public PetDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {

            mItem = CompanyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pet_detail, container, false);

        if (mItem != null) {

            //TextView chatBox = (TextView) rootView.findViewById(R.id.chatBox);
            ImageButton chatButton =  (ImageButton)rootView.findViewById(R.id.chatButton);
            ImageButton voiceButton =  (ImageButton)rootView.findViewById(R.id.voiceButton);
            textinput = (EditText) rootView.findViewById(R.id.txtinput);
            chat = (TextView)rootView.findViewById(R.id.chat);
            lv = (ListView)rootView.findViewById(R.id.chatview);
            voiceButton.setOnClickListener(this);
           chatButton.setOnClickListener(this);

        }

        return rootView;
    }


    @Override
    public void onClick(View v) {

       int id = v.getId();

        switch (id){

            case R.id.voiceButton:

            Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
             //determine what language is being spoken
            speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT,"Hello, How can i help you");
            startActivityForResult(speechIntent, VOICE_RECOGNITION_REQUEST_CODE);
            break;
            case R.id.chatButton:
                String input = textinput.getText().toString();
                chat.setText(input);
            break;
        }



    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            lv.setAdapter(new ArrayAdapter<>(
                    getActivity(),
                    android.R.layout.simple_list_item_1,
                    results));

            //Result code for various error.
        } else if (resultCode == RecognizerIntent.RESULT_AUDIO_ERROR) {
            showToastMessage("Audio Error");
        } else if (resultCode == RecognizerIntent.RESULT_CLIENT_ERROR) {
            showToastMessage("Client Error");
        } else if (resultCode == RecognizerIntent.RESULT_NETWORK_ERROR) {
            showToastMessage("Network Error");
        } else if (resultCode == RecognizerIntent.RESULT_SERVER_ERROR) {
            showToastMessage("Server Error");
        }
    }


    void showToastMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }

}

