package com.vic.bidds.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vic.bidds.AboutusActivity2;
import com.vic.bidds.R;
import com.vic.bidds.ViewDetailsActivity;
import com.vic.bidds.ViewDetailsActivity2;


public class Fragment_Feed extends Fragment   {
    View view;
    CardView one, two, three, four, five, six, livebet;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String value;
    TextView okay_text, cancel_text;

    private Dialog dialog;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_feed, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("whatsapp");
        dialog = new Dialog(getContext());
        
        one = view.findViewById(R.id.card_one);
        livebet = view.findViewById(R.id.livebetCard);
        two = view.findViewById(R.id.card_two);
        three = view.findViewById(R.id.card_three);
        four = view.findViewById(R.id.card_four);

        five = view.findViewById(R.id.card_contact);


        onClickListeners();

        return view;
    }


    private void onClickListeners() {
        one.setOnClickListener(view -> {
            intentToActivity("Free", "surebet");
        });
        livebet.setOnClickListener(view -> {
            intentToActivity2("Free", "allsports", "Today");

        });

        two.setOnClickListener(view -> {
            intentToActivity("Free", "dailyodds");
        });

        three.setOnClickListener(view -> {
            Intent intent = new Intent(getContext(), AboutusActivity2.class);
            startActivity(intent);
        });

        four.setOnClickListener(view -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.vic.bidds"));
            startActivity(browserIntent);
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show the dialog to choose the country
                showCountryDialog();
            }
        });
    }

    private void showCountryDialog() {
            // Create a dialog to choose the country
            dialog.setContentView(R.layout.country_dialog);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            // Find the close icon ImageView
            ImageView closeIcon = dialog.findViewById(R.id.close_icon);
            closeIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close the dialog when the close icon is clicked
                    dialog.dismiss();
                }
            });

            // Find the RadioGroup in the layout
            RadioGroup countryRadioGroup = dialog.findViewById(R.id.country_radio_group);

            // Set a listener to handle country selection
            countryRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // Find which radio button is selected
                    RadioButton selectedRadioButton = group.findViewById(checkedId);
                    if (selectedRadioButton != null) {
                        String selectedCountry = selectedRadioButton.getText().toString();
                        // Handle the selected country
                        handleCountrySelection(selectedCountry);
                        // Dismiss the initial dialog
                        dialog.dismiss();
                    }
                }
            });

            dialog.show();
        }


    // Method to handle the selected country
        private void handleCountrySelection(String country) {
            // Perform actions based on the selected country
            switch (country) {
                case "Asian Countries":
                    showAsianCountriesPopup();
                    break;
                case "European Countries":
                    showAsianCountriesPopup();
                    break;
                case "United States Of America":
                    showAsianCountriesPopup();
                    break;
                case "North / South America":
                    showAsianCountriesPopup();
                    break;
                case "South Africa":
                    showAfricanCountriesPopup();
                    break;
                case "Nigeria":
                    showNigeriaCountriesPopup();
                    break;
                case "Ghana":
                    showGhanaCountriesPopup();
                    break;
                case "Kenya":
                    showkenyaCountriesPopup();
                    break;
                case "Uganda":
                    showugandaCountriesPopup();
                    break;
                case "Zambia":
                    showAsianCountriesPopup();
                    break;
                case "Tanzanian":
                    showAsianCountriesPopup();
                    break;
                case "Other African Countries":
                    showAsianCountriesPopup();
                    break;


            }
        }

    private void showugandaCountriesPopup() {
        // Create and show the Asian countries popup
        Dialog asianDialog = new Dialog(getContext());
        // Set the content view for the Asian countries popup
        asianDialog.setContentView(R.layout.uganda_countries_popup);
        asianDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        asianDialog.setCancelable(false);
        asianDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        // Show the Asian countries popup
        asianDialog.show();
    }
    private void showkenyaCountriesPopup() {
        // Create and show the Asian countries popup
        Dialog asianDialog = new Dialog(getContext());
        // Set the content view for the Asian countries popup
        asianDialog.setContentView(R.layout.kenya_countries_popup);
        asianDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        asianDialog.setCancelable(false);
        asianDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        // Show the Asian countries popup
        asianDialog.show();
    }
    private void showGhanaCountriesPopup() {
        // Create and show the Asian countries popup
        Dialog asianDialog = new Dialog(getContext());
        // Set the content view for the Asian countries popup
        asianDialog.setContentView(R.layout.ghana_countries_popup);
        asianDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        asianDialog.setCancelable(false);
        asianDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        // Show the Asian countries popup
        asianDialog.show();
    }
    private void showNigeriaCountriesPopup() {
        // Create and show the Asian countries popup
        Dialog asianDialog = new Dialog(getContext());
        // Set the content view for the Asian countries popup
        asianDialog.setContentView(R.layout.nigeria_countries_popup);
        asianDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        asianDialog.setCancelable(false);
        asianDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        // Show the Asian countries popup
        asianDialog.show();
    }


    private void showAsianCountriesPopup() {
        // Inside your fragment or activity
        Dialog dialog = new Dialog(getContext());
        five.setOnClickListener(v -> {
            dialog.setContentView(R.layout.asian_countries_popup);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            // Find the close icon ImageView
            ImageView closeIcon = dialog.findViewById(R.id.close_icon);
            closeIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Close the dialog when the close icon is clicked
                    dialog.dismiss();
                }
            });

            // Find checkboxes in the popup
            CheckBox cryptoCurrencyCheckBox = dialog.findViewById(R.id.crypto_currency_checkbox);
            CheckBox skrillCheckBox = dialog.findViewById(R.id.skrill_checkbox);
            CheckBox paypalCheckBox = dialog.findViewById(R.id.paypal_checkbox);
            CheckBox netellerCheckBox = dialog.findViewById(R.id.neteller_checkbox);
            CheckBox airtmCheckBox = dialog.findViewById(R.id.airtm_checkbox);
            CheckBox worldRemitCheckBox = dialog.findViewById(R.id.world_remit_checkbox);

            // Set click listeners for checkboxes
            cryptoCurrencyCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Handle checkbox selection
                    handleOptionSelection("Crypto Currency");
                }
            });

            skrillCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Handle checkbox selection
                    handleOptionSelection("Skrill");
                }
            });

            paypalCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Handle checkbox selection
                    handleOptionSelection("PayPal");
                }
            });

            netellerCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Handle checkbox selection
                    handleOptionSelection("Neteller");
                }
            });

            airtmCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Handle checkbox selection
                    handleOptionSelection("Airtm");
                }
            });

            worldRemitCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Handle checkbox selection
                    handleOptionSelection("World Remit");
                }
            });

            dialog.show();
        });
    }
// Method to handle option selection
        private void handleOptionSelection(String option) {
            // Perform actions based on the selected option
            Toast.makeText(getContext(), "Selected Option: " + option, Toast.LENGTH_SHORT).show();
            // You can perform any other actions here, such as launching another activity, making API calls, etc.
        }



        private void showAfricanCountriesPopup() {
            // Create and show the African countries popup
            Dialog africanDialog = new Dialog(getContext());
            // Set the content view for the African countries popup
            africanDialog.setContentView(R.layout.african_countries_popup);
            africanDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            africanDialog.setCancelable(false);
            africanDialog.getWindow().getAttributes().windowAnimations = R.style.animation;
            // Show the African countries popup
            africanDialog.show();
        }

//        Dialog dialog = new Dialog(getContext());
//        five.setOnClickListener(v -> {
//                        dialog.setContentView(R.layout.country_dialog);
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialog.setCancelable(false);
//            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
//
//            dialog.setContentView(R.layout.custom_dialog);
//            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//            dialog.setCancelable(false);
//            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
//
//            okay_text = dialog.findViewById(R.id.okay_button);
//            cancel_text = dialog.findViewById(R.id.cancel_button);
//
//            okay_text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    Toast.makeText(getActivity(), "Agreed", Toast.LENGTH_SHORT).show();
//                    String url = "https://api.whatsapp.com/send?phone=" +value;
//                    Intent i = new Intent(Intent.ACTION_VIEW);
//                    i.setData(Uri.parse(url));
//                    startActivity(i);
//                }
//            });
//
//            cancel_text.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    dialog.dismiss();
//                    Toast.makeText(getActivity(), "Cancel clicked", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            dialog.show();
//
//        });
//        getdata();
//    }

    private void getdata() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                value = snapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(getActivity(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });}

    private void intentToActivity(String sub, String page) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        getActivity().startActivity(intent);


    }

    private void intentToActivity2(String sub, String page,String day) {
        Intent intent = new Intent(getActivity(), ViewDetailsActivity2.class);
        intent.putExtra("subject", sub);
        intent.putExtra("page", page);
        intent.putExtra("Old", day);
        getActivity().startActivity(intent);
    }



}
