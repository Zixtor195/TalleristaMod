package com.example.user.talleristamod.PackageProfiles.ProfileEstudiante;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.talleristamod.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ActivityPrincipalProfile extends AppCompatActivity implements View.OnClickListener {
    public Integer count1, count2, count3, count4, count5, count6, count7;
    public TextView tv1,tv2, tv3, tv4, tv5, tv6, tv7, tvNameStudent, tvPoints;
    public ImageView imageViewPlayerlvl;
    public String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
    public String userName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__principal_profile);
        initialConfig();
        showBadges();
        showLevelnPoints();

    }

    public void initialConfig (){
        count1 = 0;
        count2 = 0;
        count3 = 0;
        count4 = 0;
        count5 = 0;
        count6 = 0;
        count7 = 0;

        imageViewPlayerlvl = findViewById(R.id.imageViewPlayerLvl);
        tvPoints = findViewById(R.id.textViewPoints);
        tvNameStudent = findViewById(R.id.textViewNameStudentBadges);
        tv1 = findViewById(R.id.textViewCountBadge1);
        tv2 = findViewById(R.id.textViewCountBadge2);
        tv3 = findViewById(R.id.textViewCountBadge3);
        tv4 = findViewById(R.id.textViewCountBadge4);
        tv5 = findViewById(R.id.textViewCountBadge5);
        tv6 = findViewById(R.id.textViewCountBadge6);
        tv7 = findViewById(R.id.textViewCountBadge7);
    }

    @Override
    public void onClick(View view)
    {
        switch (view.getId())
        {

        }

    }


    public void showLevelnPoints(){
        final DatabaseReference databaseEstudianteLevel = FirebaseDatabase.getInstance().getReference("Estudiante/"+userId+"/Puntaje");
        databaseEstudianteLevel.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String PointsStr = dataSnapshot.getValue().toString();
                Integer PointsInt = Integer.parseInt(PointsStr);

                tvPoints.setText(PointsStr);
                if (PointsInt>=1000){
                    imageViewPlayerlvl.setImageResource(R.drawable.gatodos);
                } else if (PointsInt >=2000){

                }else if (PointsInt>=3000){

                }else {
                    imageViewPlayerlvl.setImageResource(R.drawable.gatouno);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void showBadges(){

        tvNameStudent.setText(userName);

        final DatabaseReference databaseEstudianteRegister = FirebaseDatabase.getInstance().getReference("Estudiante/"+userId+"/Badges");

        databaseEstudianteRegister.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objectQuestionsSnapShot : dataSnapshot.getChildren())
                {
                    String MedalCode = (String) objectQuestionsSnapShot.getValue();

                   switch (MedalCode){
                       case "001":
                           count1++;
                           break;
                       case "002":
                           count2++;
                           break;
                       case "003":
                           count3++;
                           break;
                       case "004":
                           count4++;
                           break;
                       case "005":
                           count5++;
                           break;
                       case "006":
                           count6++;
                           break;
                       case "007":
                           count7++;
                           break;
                   }

                }

                tv1.setText("x"+count1.toString());
                tv2.setText("x"+count2.toString());
                tv3.setText("x"+count3.toString());
                tv4.setText("x"+count4.toString());
                tv5.setText("x"+count5.toString());
                tv6.setText("x"+count6.toString());
                tv7.setText("x"+count7.toString());

                databaseEstudianteRegister.removeEventListener(this);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}