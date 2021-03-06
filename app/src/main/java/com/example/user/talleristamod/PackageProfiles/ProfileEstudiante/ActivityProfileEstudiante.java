package com.example.user.talleristamod.PackageProfiles.ProfileEstudiante;

import android.content.Intent;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.user.talleristamod.PackageProfiles.ActivityActivitiesFreiya;
import com.example.user.talleristamod.PackageProfiles.Login.TalleristaPrincipalMenu;
import com.example.user.talleristamod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ActivityProfileEstudiante extends AppCompatActivity implements CardView.OnClickListener {

    CircleImageView imageViewProfilePhoto;
    TextView textViewNameProfile;
    CardView cardViewActivities, cardViewProfile;
    ConstraintLayout layoutAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_estudiante);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        Uri uriPhotoUser = user.getPhotoUrl();
        String nameUser = user.getDisplayName();

        imageViewProfilePhoto = (CircleImageView) findViewById(R.id.profileEstudianteimage);
        Picasso.with(this).load(uriPhotoUser).resize(50, 50).into(imageViewProfilePhoto);
        cardViewActivities = findViewById(R.id.cdActivities);
        cardViewProfile = findViewById(R.id.cdProfile);
        cardViewProfile.setOnClickListener(this);
        cardViewActivities.setOnClickListener(this);


        layoutAtras = findViewById(R.id.layoutAtras);
        textViewNameProfile = (TextView) findViewById(R.id.textViewNameEstudianteProfile);
        textViewNameProfile.setText(nameUser);
        layoutAtras.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cdActivities:
                Intent intent1 = new Intent(v.getContext(), ActivityActivitiesFreiya.class);
                v.getContext().startActivity(intent1);
                finish();
                break;

            case R.id.cdProfile:
                Intent intent2 = new Intent(v.getContext(), ActivityPrincipalProfile.class);
                v.getContext().startActivity(intent2);
                finish();
                break;
            case R.id.layoutAtras:
                finishActivity ();
                break;

        }
    }

    @Override
    public void onBackPressed (){
        finishActivity();
    }

    private void finishActivity (){
        Intent intent = new Intent(getApplicationContext(), TalleristaPrincipalMenu.class);
        startActivity(intent);
        finish();
    }



}
