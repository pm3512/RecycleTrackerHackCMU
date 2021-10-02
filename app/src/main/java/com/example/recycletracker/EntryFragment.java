package com.example.recycletracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class EntryFragment extends Fragment {

    public EntryFragment() {

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_entry,container,false);
            Spinner recycleObjects = (Spinner) view.findViewById(R.id.typeSpinner);
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item,
                    getResources().getStringArray(R.array.objectTypes));
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            recycleObjects.setAdapter(myAdapter);
            return view;

    }
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.submitButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapToHome();
            }
        });

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapToHome();
            }
        });

        view.findViewById(R.id.searchButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapToSearch();
            }
        });
    }

    private void swapToHome() {
        HomeFragment newEntryFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, newEntryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void swapToSearch() {
        searchbarFragment newEntryFragment = new searchbarFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, newEntryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }




    }
