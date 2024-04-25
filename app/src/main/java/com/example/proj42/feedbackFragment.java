package com.example.proj42;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class feedbackFragment extends Fragment {

    private RatingBar ratingBar1, ratingBar2, ratingBar3, ratingBar4, ratingBar5;
    private TextView ratingText1, ratingText2, ratingText3, ratingText4, ratingText5;
    private Button submitButton;

    private Boolean rb1, rb2, rb3, rb4, rb5;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        // Initialize your views
        ratingBar1 = view.findViewById(R.id.ratingBar1);
        ratingBar2 = view.findViewById(R.id.ratingBar2);
        ratingBar3 = view.findViewById(R.id.ratingBar3);
        ratingBar4 = view.findViewById(R.id.ratingBar4);
        ratingBar5 = view.findViewById(R.id.ratingBar5);

        ratingText1 = view.findViewById(R.id.comment1);
        ratingText2 = view.findViewById(R.id.comment2);
        ratingText3 = view.findViewById(R.id.comment3);
        ratingText4 = view.findViewById(R.id.comment4);
        ratingText5 = view.findViewById(R.id.comment5);

        submitButton = view.findViewById(R.id.submitButton);

        rb1 = false;
        rb2 = false;
        rb3 = false;
        rb4 = false;
        rb5 = false;

        // Set up a listener for the RatingBars
        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText1.setText(getFeedbackMessage(rating));
                rb1 = true;
            }
        });

        ratingBar2.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText2.setText(getFeedbackMessage(rating));
                rb2 = true;
            }
        });

        ratingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText3.setText(getFeedbackMessage(rating));
                rb3 = true;
            }
        });

        ratingBar4.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText4.setText(getFeedbackMessage(rating));
                rb4 = true;
            }
        });

        ratingBar5.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingText5.setText(getFeedbackMessage(rating));
                rb5 = true;
            }
        });

        // Set up a click listener for the submit button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Handle the submission logic here
                // You can retrieve the ratings using ratingBar1.getRating() and ratingBar2.getRating()
                // Then, you can proceed with further actions or send the ratings to a server.

                // After handling submission, navigate to the ThankYouActivity
                if(rb1 && rb2 && rb3 && rb4 && rb5){
                    Toast.makeText(getActivity(), "Thanks for your feedback", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getActivity(), "Your feedback is valuable, please fill the form", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private String getFeedbackMessage(float rating) {
        if (rating == 5) {
            return "😍 Thank you for the awesome feedback!";
        } else if (rating >= 3) {
            return "😊 We appreciate your feedback!";
        } else if (rating >= 1) {
            return "😐 We'll work on improving!";
        } else {
            return "😡 Your feedback is valuable. Let us know how we can do better.";
        }
    }
}
