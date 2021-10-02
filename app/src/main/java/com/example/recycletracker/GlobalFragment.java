package com.example.recycletracker;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.*;


/**
 * A simple {@link Fragment} subclass.
 */
public class GlobalFragment extends Fragment {

    private User[] users;

    public GlobalFragment() {
        // Required empty public constructor
        String[] names = {"Olivia", "Liam", "Emma", "Noah", "Amelia", "Oliver", "Ava", "Elijah",
                "Sophia", "Lucas", "Charlotte", "Mason", "Isabella", "Levi", "Mia", "Asher", "Luna",
                "James", "Harper", "Mateo"};
        int[] plastic = {17, 26, 15, 24, 18, 27, 22, 16, 24, 25, 19, 25, 22, 22, 18, 20, 17, 23, 20, 16};
        int[] metal = {17, 25, 15, 24, 18, 27, 22, 16, 25, 25, 19, 25, 22, 22, 18, 20, 17, 23, 20, 16};
        int[] paper = {17, 25, 15, 24, 18, 27, 22, 16, 25, 25, 19, 25, 22, 22, 18, 20, 17, 23, 20, 16};

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
        View view = inflater.inflate(R.layout.fragment_global, container, false);

        // setting IDs of top 5
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

        s1.setText(Integer.toString(users[0].getTotal()));
        s2.setText(Integer.toString(users[1].getTotal()));
        s3.setText(Integer.toString(users[2].getTotal()));
        s4.setText(Integer.toString(users[3].getTotal()));
        s5.setText(Integer.toString(users[4].getTotal()));

        // determining user rank/percentile
        TextView userrank = view.findViewById(R.id.userrank);
        TextView percentile = view.findViewById(R.id.percentile);

        userrank.setText("You are rank " + Integer.toString(16) + " of " + Integer.toString(20));
        percentile.setText("You've recycled more than " + Float.toString(15) + "% of people using this app");

        return view;
    }

    private class User implements Comparable<User>{

        // Represents one user, contains data on the user's name and stats
        private final String ID;
        private final int plastic;
        private final int paper;
        private final int metal;
        private final int total;

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

        public int compareTo(User other) {
            if (this.total > other.total) { return -1; }
            if (this.total < other.total) { return 1; }
            return 0;
        }
    }
}