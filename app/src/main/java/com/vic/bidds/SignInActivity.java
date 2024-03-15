package com.vic.bidds;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.applovin.mediation.MaxAd;
import com.applovin.mediation.MaxAdViewAdListener;
import com.applovin.mediation.MaxError;
import com.applovin.mediation.ads.MaxAdView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;


public class SignInActivity extends AppCompatActivity implements MaxAdViewAdListener {

    private TextView tv_sign_up;
    private MaxAdView adView;
    //Google Auth
    private CardView signInGoogle, signInPhone;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 1;
    private FirebaseFirestore db;
    private DocumentReference document_ref;
    private String userID;

    //Email Auth
    TextInputEditText email_field, password_field;
    TextInputLayout email_layout, password_layout;
    CardView login;
    LinearLayout llAllVIP;
    ProgressBar progressBar;
    TextView sign_up, forgot_password;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    String value;
    Button mDialogButton;
    TextView okay_text, cancel_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        // Create the ad view
        adView = new MaxAdView(getString(R.string.apl_banner), this);
        adView.setListener(this);
        adView.setGravity(Gravity.BOTTOM);
        ViewGroup rootView = findViewById(android.R.id.content);
        rootView.addView(adView);

        // Load the ad
        adView.loadAd();
        //
        Dialog dialog = new Dialog(SignInActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("whatsapp");
        llAllVIP=findViewById(R.id.llAllVIP);

        llAllVIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.setContentView(R.layout.custom_dialog);
                dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                dialog.setCancelable(false);
                dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

                okay_text = dialog.findViewById(R.id.okay_button);
                cancel_text = dialog.findViewById(R.id.cancel_button);

                okay_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Agreed", Toast.LENGTH_SHORT).show();
                        String url = "https://api.whatsapp.com/send?phone=" +value;
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });

                cancel_text.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                        Toast.makeText(SignInActivity.this, "Cancel clicked", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

            }
        });

        getdata();


//
//        //Email
//        email_layout = findViewById(R.id.email_layout);
//        password_layout = findViewById(R.id.password_layout);
//
//        email_field = findViewById(R.id.userEmail);
//        password_field = findViewById(R.id.userPassword);
//        login = findViewById(R.id.btn_login);
//        progressBar = findViewById(R.id.login_progressbar);
//
//
//
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
//
//        db = FirebaseFirestore.getInstance();
//        mAuth = FirebaseAuth.getInstance();
//
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestIdToken(getString(R.string.default_web_client_id))
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
//
//
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String email = email_field.getText().toString().trim();
//                String password = password_field.getText().toString().trim();
//
//                if (email.isEmpty()) {
//                    email_layout.setError("E-mail is required");
//                    return;
//                }
//
//                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//                    email_layout.setError("Please enter a valid email");
//                    return;
//                }
//
//                if (password.isEmpty()) {
//                    password_layout.setError("Password is required");
//                    return;
//                }
//
//                if (password.length() < 6) {
//                    password_layout.setError("Minimum length of password should be 6");
//                    return;
//                } else {
//                    login.setVisibility(View.GONE);
//                    progressBar.setVisibility(View.VISIBLE);
//                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                updateUI(mAuth.getCurrentUser());
//                            } else {
//                                login.setVisibility(View.VISIBLE);
//
//                                Toast.makeText(SignInActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });
//                }
//            }
//        });
//
//
//
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == RC_SIGN_IN) {
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            try {
//                GoogleSignInAccount account = task.getResult(ApiException.class);
//                firebaseAuthWithGoogle(account.getIdToken());
//            } catch (ApiException e) {
//                Log.w("TAG", "Google sign in failed", e);
//            }
//        }
//    }
//
//    private void firebaseAuthWithGoogle(String idToken) {
//        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
//
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            userID = mAuth.getUid();
//
//                            FirebaseUser currentUser = mAuth.getCurrentUser();
//
//                            FirebaseUser user = mAuth.getCurrentUser();
//                            updateUI(user);
//                        } else {
//                            Toast.makeText(SignInActivity.this, "Something went wrong!", Toast.LENGTH_SHORT).show();
//                            updateUI(null);
//                        }
//                    }
//                });
//    }
//
//
//    private void updateUI(FirebaseUser user) {
//        if (user != null) {
//            Intent intent = new Intent(SignInActivity.this, MainVIpActivity.class);
//            startActivity(intent);
//            finish();
//        }
//    }
//
////    @Override
////    public void onBackPressed() {
////        super.onBackPressed();
////        Intent mainActivity = new Intent(SignInActivity.this, MainActivity.class);
////        startActivity(mainActivity);
////        finish();
////    }
////
//    public void onStart() {
//        super.onStart();
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
    }
    @Override
    public void onAdExpanded(MaxAd maxAd) {

    }

    @Override
    public void onAdCollapsed(MaxAd maxAd) {

    }

    @Override
    public void onAdLoaded(MaxAd maxAd) {

    }

    @Override
    public void onAdDisplayed(MaxAd maxAd) {

    }

    @Override
    public void onAdHidden(MaxAd maxAd) {

    }

    @Override
    public void onAdClicked(MaxAd maxAd) {

    }

    @Override
    public void onAdLoadFailed(String s, MaxError maxError) {

    }

    @Override
    public void onAdDisplayFailed(MaxAd maxAd, MaxError maxError) {

    }

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
                Toast.makeText(getApplicationContext(), "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
}}