package com.cocacola.climateambassador.core.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.util.DataChecker;
import com.cocacola.climateambassador.core.util.DataSeeder;
import com.cocacola.climateambassador.core.util.Toaster;
import java.io.IOException;
import javax.inject.Inject;

/**
 * Created by realandylawton on 12/1/13.
 */
public class LandingActivity extends CaActivity {

    @Inject protected DataChecker mDataChecker;
    @Inject protected DataSeeder mDataSeeder;

    private ProgressDialog mProgressDialog;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.landing_activity);

        if(!mDataChecker.isDataSeeded()) {
            showLoading();
            new DataSeedTask().execute(mDataSeeder);
        }
        else {
            onSeeded();
        }

    }

    public void onSeeded() {
        startActivity(new Intent(LandingActivity.this, MainActivity.class));
        finish();
    }

    public void onSeedFailed(DataSeeder.SeedFailedException e) {
        Toaster.toast(this, "Failed to load content: " + e.getMessage());
    }

    private void showLoading() {
    }

    private class DataSeedTask extends AsyncTask<DataSeeder, Void, DataSeeder.SeedFailedException> {

        @Override protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(LandingActivity.this);
            mProgressDialog.setTitle("Initializing");
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setIndeterminate(true);
            mProgressDialog.show();
        }

        @Override protected DataSeeder.SeedFailedException doInBackground(DataSeeder... params) {
            
            DataSeeder dataSeeder = params[0];

            try {
                dataSeeder.seed();
            } catch (IOException e) {
                return new DataSeeder.SeedFailedException("IO Exception: " + e.getMessage());
            } catch (DataSeeder.SeedFailedException e) {
                return e;
            }

            return null;
        }

        @Override protected void onPostExecute(DataSeeder.SeedFailedException e) {
            super.onPostExecute(e);

            if(mProgressDialog != null) {
                mProgressDialog.dismiss();
            }

            if(e == null) {
                onSeeded();
            }
            else {
                onSeedFailed(e);
            }

        }
    }

}
