package com.cocacola.climateambassador;

import android.app.Application;
import android.content.Context;

import com.cocacola.climateambassador.modules.CaProdModule;

import dagger.ObjectGraph;

/**
 * Created by andrewlawton on 8/21/13.
 */
public class CaApplication extends Application {

    private static Context sAppContext;
    private static ObjectGraph sObjectGraph;

    public static ObjectGraph getObjectGraph(Context context) {
        if (sObjectGraph == null){
            sAppContext = context;
            sObjectGraph = ObjectGraph.create(new CaProdModule(context));
        }
        return sObjectGraph;
    }

    public static Context getContext() {
        return sAppContext;
    }

    @Override
    public void onCreate() {
        sObjectGraph = null;
        getObjectGraph(this).inject(this);
    }

    @Override
    public void onTerminate() {
        sObjectGraph = null;
        sAppContext = null;
    }

}
