package com.example.rkaramballi.fragmentlifecycleexample;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class FragmentLifeCycleActivity extends ActionBarActivity {

    private static final String TAG = "HostActivity";

    private Button addButton;
    private Button pushButton;
    private Button popButton;
    private Button removeButton;

    public FragmentLifeCycleActivity() {
        Log.i(TAG, "constructor");
    }

    @Override
    protected void onCreate(Bundle bundle) {
        Log.i(TAG, "onCreate enter");
        super.onCreate(bundle);
        Log.i(TAG, "super.onCreate done");
        setContentView(R.layout.activity_fragment_life_cycle);
        Log.i(TAG, "setContentView done");

        addButton = (Button) findViewById(R.id.addButton);
        removeButton = (Button) findViewById(R.id.removeButton);
        pushButton = (Button) findViewById(R.id.pushButton);
        popButton = (Button) findViewById(R.id.popButton);

        addButton.setOnClickListener(new AddClickListener());
        removeButton.setOnClickListener(new RemoveClickListener());
        pushButton.setOnClickListener(new PushClickListener());
        popButton.setOnClickListener(new PopClickListener());

        getSupportFragmentManager().addOnBackStackChangedListener(new BackStackListener());

        Log.i(TAG, "onCreate exit");
    }

    @Override
    protected void onDestroy() {
        Log.i(TAG, "onDestroy enter");
        super.onDestroy();
        Log.i(TAG, "onDestroy exit");
    }

    @Override
    protected void onPause() {
        Log.i(TAG, "onPause enter");
        super.onPause();
        Log.i(TAG, "onPause exit");
    }

    @Override
    protected void onResume() {
        Log.i(TAG, "onResume enter");
        super.onResume();
        Log.i(TAG, "onResume exit");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Log.i(TAG, "onSaveInstanceState enter");
        super.onSaveInstanceState(outState);
        Log.i(TAG, "onSaveInstanceState exit");
    }

    @Override
    protected void onStart() {
        Log.i(TAG, "onStart enter");
        super.onStart();
        Log.i(TAG, "onStart exit");
    }

    @Override
    protected void onStop() {
        Log.i(TAG, "onStop enter");
        super.onStop();
        Log.i(TAG, "onStop exit");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onRestoreInstanceState enter");
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, "onRestoreInstanceState exit");
    }

    private class AddClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AddedFragment fragment = new AddedFragment();

            FragmentTransaction ft = FragmentLifeCycleActivity.this.getSupportFragmentManager()
                    .beginTransaction();
            Log.i(TAG, "Adding fragment");
            ft.add(R.id.lowerContainer, fragment, "AddedFragment");
            Log.i(TAG, "Committing transaction");
            ft.commit();
            Log.i(TAG, "Committed transaction");

            FragmentLifeCycleActivity.this.addButton.setVisibility(View.GONE);
            FragmentLifeCycleActivity.this.pushButton.setVisibility(View.GONE);
            FragmentLifeCycleActivity.this.removeButton.setVisibility(View.VISIBLE);
            FragmentLifeCycleActivity.this.popButton.setVisibility(View.GONE);
        }
    }

    private class PushClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            AddedFragment fragment = new AddedFragment();

            FragmentTransaction ft = FragmentLifeCycleActivity.this.getSupportFragmentManager()
                    .beginTransaction();
            Log.i(TAG, "Adding fragment");
            ft.add(R.id.lowerContainer, fragment, "AddedFragment");
            Log.i(TAG, "Add to back stack");
            ft.addToBackStack("AddedFragmentToStack");
            Log.i(TAG, "Committing transaction");
            ft.commit();
            Log.i(TAG, "Committed transaction");

            FragmentLifeCycleActivity.this.addButton.setVisibility(View.GONE);
            FragmentLifeCycleActivity.this.pushButton.setVisibility(View.GONE);
            FragmentLifeCycleActivity.this.removeButton.setVisibility(View.GONE);
            FragmentLifeCycleActivity.this.popButton.setVisibility(View.VISIBLE);
        }
    }

    private class RemoveClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentManager fm = FragmentLifeCycleActivity.this.getSupportFragmentManager();
            AddedFragment fragment = (AddedFragment) fm.findFragmentByTag("AddedFragment");
            FragmentTransaction ft = fm.beginTransaction();
            Log.i(TAG, "Removing fragment");
            ft.remove(fragment);
            Log.i(TAG, "Committing transaction");
            ft.commit();
            Log.i(TAG, "Committed transaction");

            FragmentLifeCycleActivity.this.addButton.setVisibility(View.VISIBLE);
            FragmentLifeCycleActivity.this.pushButton.setVisibility(View.VISIBLE);
            FragmentLifeCycleActivity.this.removeButton.setVisibility(View.GONE);
            FragmentLifeCycleActivity.this.popButton.setVisibility(View.GONE);
        }
    }

    private class PopClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            FragmentManager fm = FragmentLifeCycleActivity.this.getSupportFragmentManager();
            Log.i(TAG, "Popping back stack");
            fm.popBackStack();
            Log.i(TAG, "Popped back stack");
        }
    }

    private class BackStackListener implements FragmentManager.OnBackStackChangedListener {
        @Override
        public void onBackStackChanged() {
            Log.i(TAG, "onBackStackChanged");

            FragmentManager fm = FragmentLifeCycleActivity.this.getSupportFragmentManager();
            if (fm.getBackStackEntryCount() == 0) {
                Log.i(TAG, "back stack empty");
                FragmentLifeCycleActivity.this.addButton.setVisibility(View.VISIBLE);
                FragmentLifeCycleActivity.this.pushButton.setVisibility(View.VISIBLE);
                FragmentLifeCycleActivity.this.removeButton.setVisibility(View.GONE);
                FragmentLifeCycleActivity.this.popButton.setVisibility(View.GONE);
            }
        }
    }
}
