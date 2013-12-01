package com.cocacola.climateambassador.module.internal.fragment;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.InjectView;
import butterknife.Views;
import com.cocacola.climateambassador.R;
import com.cocacola.climateambassador.core.fragment.CaFragment;
import com.cocacola.climateambassador.module.activity.AbsModuleActivity;
import com.cocacola.climateambassador.module.fragment.ModuleFragment;

/**
 * Created by realandylawton on 8/31/13.
 */
public class ModuleCasesFragment extends CaFragment {

    public static ModuleCasesFragment newInstance(Long moduleId) {

        ModuleCasesFragment fragment = new ModuleCasesFragment();
        fragment.setArguments(ModuleFragment.createBundleWithModuleId(moduleId));

        return fragment;

    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.module_internal_frag_cases, null);

        return view;

    }

    @Override public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Create a Module fragment
        ModuleFragment fragment = ModuleFragment.newInstance(getModuleId());

        // Set it as the content fragment
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.module_frag_case_container, fragment);

        transaction.commit();


    }

    @Override public void onDestroyView() {
        super.onDestroyView();

        ModuleFragment fragment =
            (ModuleFragment) getChildFragmentManager().findFragmentById(R.id.module_frag_case_container);

        if(fragment != null) {
            getChildFragmentManager().beginTransaction().remove(fragment).commit();
        }

    }

    private Long getModuleId() {
        return getArguments().getLong(AbsModuleActivity.EXTRA_MODULE_ID, 0);
    }

}
