package com.example.recycletracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalFragment extends Fragment {

    private User[] users;

    private Spinner SelectType; // spinner that selects what type of recyclable to display stats for
    private String type;        // current type

    private View view;

    public GlobalFragment() {
        // Required empty public constructor
        String[] names = {"Olivia", "Liam", "Emma", "Noah", "Amelia", "Oliver", "Ava", "Elijah",
                "Sophia", "Lucas", "Charlotte", "Mason", "Isabella", "Levi", "Mia", "Asher", "Luna",
                "James", "Harper", "Mateo"};
        int[] plastic = {17, 26, 15, 24, 18, 27, 22, 16, 24, 25, 19, 25, 22, 22, 18, 20, 17, 23, 20, 16};
        int[] metal = {27, 32, 39, 30, 31, 19, 31, 27, 28, 21, 46, 34, 40, 26, 23, 37, 26, 23, 24, 35};
        int[] paper = {22, 15, 5, 13, 10, 11, 27, 10, 18, 17, 5, 8, 12, 7, 13, 10, 14, 19, 16, 9};

        users = new User[20];
        for (int i = 0; i < 20; i++) {
            User u = new User(names[i], plastic[i], metal[i], paper[i]);
            users[i] = u;
        }
        Arrays.sort(users);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_global, container, false);

        SelectType = view.findViewById(R.id.SelectType);
        populateSelectType();

        SelectType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                type = SelectType.getSelectedItem().toString();
                populateStats();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) { }
        });

        return view;
    }

    private void populateSelectType() {
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.recyclables));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SelectType.setAdapter(typeAdapter);
    }

    private void populateStats() {
        for (int i = 0; i < 20; i++)
        {
            users[i].setType(type);
        }
        Arrays.sort(users);

        TextView i1 = view.findViewById(R.id.id1);
        TextView i2 = view.findViewById(R.id.id2);
        TextView i3 = view.findViewById(R.id.id3);
        TextView i4 = view.findViewById(R.id.id4);
        TextView i5 = view.findViewById(R.id.id5);

        i1.setText(users[0].getID());
        i2.setText(users[1].getID());
        i3.setText(users[2].getID());
        i4.setText(users[3].getID());
        i5.setText(users[4].getID());

        // setting scores of top 5
        TextView s1 = view.findViewById(R.id.score1);
        TextView s2 = view.findViewById(R.id.score2);
        TextView s3 = view.findViewById(R.id.score3);
        TextView s4 = view.findViewById(R.id.score4);
        TextView s5 = view.findViewById(R.id.score5);

        s1.setText(Integer.toString(users[0].getItem(type)));
        s2.setText(Integer.toString(users[1].getItem(type)));
        s3.setText(Integer.toString(users[2].getItem(type)));
        s4.setText(Integer.toString(users[3].getItem(type)));
        s5.setText(Integer.toString(users[4].getItem(type)));

        // determining user rank/percentile
        TextView userrank = view.findViewById(R.id.userrank);
        TextView percentile = view.findViewById(R.id.percentile);

        int rank = 1;
        for (int i = 0; i < 20; i++) {
            if (users[i].getID().equals("Mia")) {
                break;
            }
            rank++;
        }
        userrank.setText("You are rank " + Integer.toString(rank) + " of " + Integer.toString(20));
        if (type.equals("All")) {
            percentile.setText("You've recycled more than " + Double.toString((20-rank) / 0.2) + "% of people using this app");
        }
        else {
            percentile.setText("You've recycled more " + type.toLowerCase() + " than " + Double.toString((20-rank) / 0.2) + "% of people using this app");
        }
    }

    private class User implements Comparable<User>{

        // Represents one user, contains data on the user's name and stats
        private final String ID;
        private final int plastic;
        private final int paper;
        private final int metal;
        private final int total;
        private String type = "All";

        public User(String ID, int plastic, int paper, int metal) {
            this.ID = ID;
            this.plastic = plastic;
            this.paper = paper;
            this.metal = metal;
            this.total = plastic + paper + metal;
        }

        public String getID() { return ID; }
        public int getPlastic() { return plastic; }
        public int getMetal() { return metal; }
        public int getPaper() { return paper; }
        public int getTotal() { return total; }
        public void setType(String type) { this.type = type; }
        public int getItem(String type) {
            if (type.equals("All")) return total;
            if (type.equals("Plastic")) return plastic;
            if (type.equals("Metal")) return metal;
            else return paper;

        }

        public int compareTo(User other) {
            if (type.equals("All")) {
                if (this.total > other.total) { return -1; }
                if (this.total < other.total) { return 1; }
            }
            else if (type.equals("Plastic")) {
                if (this.plastic > other.plastic) { return -1; }
                if (this.plastic < other.plastic) { return 1; }
            }
            else if (type.equals("Paper")) {
                if (this.paper > other.paper) { return -1; }
                if (this.paper < other.paper) { return 1; }
            }
            else if (type.equals("Metal")) {
                if (this.metal > other.metal) { return -1; }
                if (this.metal < other.metal) { return 1; }
            }
            return 0;
        }
    }
}