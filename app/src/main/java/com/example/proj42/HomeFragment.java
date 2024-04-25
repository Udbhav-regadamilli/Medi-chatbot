package com.example.proj42;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.cardview.widget.CardView;

public class HomeFragment extends Fragment {

    private CardView botCard;
    private CardView nearbyCard;
    private CardView infoCard;
    private CardView feedbackCard;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_homefragment, container, false);

        botCard = view.findViewById(R.id.botCard);
        nearbyCard = view.findViewById(R.id.nearbyCard);
        infoCard = view.findViewById(R.id.infoCard);
        feedbackCard = view.findViewById(R.id.feedbackCard);

        botCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), bot.class);
                startActivity(intent);
            }
        });

        nearbyCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), nearby.class);
                startActivity(intent);
            }
        });

        infoCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add code for info card action
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new InfoFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        feedbackCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add code for feedback card action
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new feedbackFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
