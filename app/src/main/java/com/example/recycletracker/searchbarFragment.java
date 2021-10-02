package com.example.recycletracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;



import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.fragment.NavHostFragment;

public class searchbarFragment extends Fragment {
    ListView listView;
    String[] objects = {"Plastic Bottle 1 litre", "Plastic Bottle 500 ml", "Aluminum Can", "Paper",
            "Cardboard Box", "Glass Bottle 125 mL", "Glass Bottle 500 mL", "Glass Bottle 1000mL"};
    ArrayAdapter<String> arrayAdapter;

    public searchbarFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_searchbar, container, false);
        listView = (ListView) view.findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, objects);
        listView.setAdapter(arrayAdapter);
        return view;
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type the item you are recycling");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                swapToHome();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                arrayAdapter.getFilter().filter(newText);
                return false;
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
}
