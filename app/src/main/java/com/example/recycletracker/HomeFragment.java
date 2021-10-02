package com.example.recycletracker;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private Button recycleButton;
    private TextView aluminumWeight;
    private TextView plasticWeight;
    private TextView paperWeight;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        recycleButton = (Button) view.findViewById(R.id.recycleButton);
        aluminumWeight = (TextView) view.findViewById(R.id.textView6);
        plasticWeight = (TextView) view.findViewById(R.id.textView13);
        paperWeight = (TextView) view.findViewById(R.id.textView14);

        recycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                swapFragment();
            }
        });

        return view;
    }

    /*public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);

        view.findViewById(R.id.recycleButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_statsFragment);
            }
        });
    }*/

    private void swapFragment(){
        EntryFragment newEntryFragment = new EntryFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, newEntryFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}