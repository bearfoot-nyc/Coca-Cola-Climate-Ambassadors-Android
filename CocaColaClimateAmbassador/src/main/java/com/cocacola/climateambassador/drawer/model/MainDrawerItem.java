package com.cocacola.climateambassador.drawer.model;

import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.data.Section;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by realandylawton on 11/23/13.
 */
public class MainDrawerItem extends DrawerItem {

    private static Map<String, Integer> sIcons = new LinkedHashMap<String, Integer>();
    static {
        sIcons.put("Internal Training Materials", R.drawable.ic_drawer_folder);
        sIcons.put("For Suppliers", R.drawable.ic_drawer_wrench);
    }

    private Section mSection;

    public MainDrawerItem(Section section) {
        mSection = section;
    }

    public Section getSection() {
        return mSection;
    }

    public void setSection(Section section) {
        mSection = section;
    }

    public int getIconId() {
       return sIcons.get(getTitle());
    }

    @Override public String getTitle() {
        return mSection.getName();
    }

}
