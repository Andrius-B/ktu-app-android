package lt.welovedotnot.ktu_ais_app.views.activities.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import lt.welovedotnot.ktu_ais_app.R;

/**
 * Created by Mindaugas on 5/1/2017.
 */

public class GradesFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grades, container, false);
    }
}
