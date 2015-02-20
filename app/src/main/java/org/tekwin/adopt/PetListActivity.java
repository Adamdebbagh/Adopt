package org.tekwin.adopt;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.FragmentActivity;

import java.util.Locale;


public class PetListActivity extends FragmentActivity
        implements PetListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */ public static final String ARG_ITEM_ID = "item_id";
    private boolean mTwoPane;



    public String welcomeText = String.format("Hello. How can i help you today ? ");
    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        if (findViewById(R.id.pet_detail_container) != null) {

            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((PetListFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.pet_list))
                    .setActivateOnItemClick(true);
        }

        tts = new TextToSpeech(this,new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {

                if (status != TextToSpeech.ERROR){
                    tts.setLanguage(Locale.US);
                }
            }
        });
    }


    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(PetDetailFragment.ARG_ITEM_ID, id);
            PetDetailFragment fragment = new PetDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.pet_detail_container, fragment)
                    .commit();


        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, PetDetailActivity.class);
            detailIntent.putExtra(PetDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);

            tts.speak(welcomeText, TextToSpeech.QUEUE_FLUSH,null);


        }
    }
}
