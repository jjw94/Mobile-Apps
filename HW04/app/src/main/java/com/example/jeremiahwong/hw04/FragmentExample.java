package com.example.jeremiahwong.hw04;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FragmentExample extends Activity {
    private MyListFragment myListFragment;
    private FragmentTwo fragmentTwo;
    private Button listButton;
    private Button fragmentTwoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_example);

        listButton = (Button)findViewById(R.id.listButton);
        listButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loadFragment("List");
            }
        });
        fragmentTwoButton = (Button)findViewById(R.id.fragmentTwoButton);
        fragmentTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment("Two");
            }
        });

        //would need to keep track of which fragment is being displayed for
        //rotation if desired in the appropriate methods
        loadFragment("List");
    }

    private void loadFragment(String which) {

        if (which.equals("List")) {
            //get the MyListFragment
            myListFragment = new MyListFragment();
            //set the change listener
            myListFragment.setItemChangedListener(itemChangedListener);
            //note: no transition or backstack - but clear backstack
            getFragmentManager().popBackStack(null,
                    getFragmentManager().POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.the_info,myListFragment);
            ft.commit();
        } else if (which.equals("Two")) {
            //get the Fragment Two Fragment
            fragmentTwo = new FragmentTwo();

            //note: no transition or backstack - but clear backstack
            getFragmentManager().popBackStack(null,
                    getFragmentManager().POP_BACK_STACK_INCLUSIVE);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.the_info,fragmentTwo);
            ft.commit();
        }
    }

    //listener for list fragment
    private MyListFragment.ItemChangedListener itemChangedListener = new MyListFragment.ItemChangedListener() {
        @Override
        public void onSelectedItemChanged(String itemNameString) {
            //create and show the fragment
            DetailFragment details = DetailFragment.newInstance(itemNameString);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.animator.fragment_animation_fade_in, R.animator.fragment_animation_fade_out);
            ft.replace(R.id.the_info, details);
            ft.addToBackStack(null);
            ft.commit();
        }
    };
}
