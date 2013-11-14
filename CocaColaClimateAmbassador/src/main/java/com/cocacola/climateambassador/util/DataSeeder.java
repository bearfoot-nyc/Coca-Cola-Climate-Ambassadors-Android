package com.cocacola.climateambassador.util;

import android.content.Context;
import com.cocacola.climateambassador.data.Module;
import javax.inject.Inject;

/**
 * Created by realandylawton on 11/14/13.
 */
public class DataSeeder {

    private Context mContext;

    @Inject
    public DataSeeder(Context context) {
        mContext = context;
    }

    public Module seedModule(String fileName) {
        return null;
    }
}
