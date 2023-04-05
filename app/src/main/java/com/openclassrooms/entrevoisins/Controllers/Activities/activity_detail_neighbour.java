package com.openclassrooms.entrevoisins.Controllers.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.Objects;

public class activity_detail_neighbour extends AppCompatActivity implements View.OnClickListener {

    ImageView mAvatar;
    FloatingActionButton mAjouterFavoris;
    TextView mNomAvatar, mNom, mAdresse, mTelephone, mSiteWeb, mAPropos;
    ;
    Neighbour dNeighbour;
    private NeighbourApiService mApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        mNomAvatar = findViewById(R.id.nom_avatar);
        mAvatar = findViewById(R.id.avatar_detail);
        mNom = findViewById(R.id.nom_detail);
        mAdresse = findViewById(R.id.adresse_detail);
        mTelephone = findViewById(R.id.telephone_detail);
        mSiteWeb = findViewById(R.id.site_web_detail);
        mAPropos = findViewById(R.id.a_propos_detail);
        mAjouterFavoris = findViewById(R.id.ajouter_favoris);
        mAjouterFavoris.setOnClickListener(this);
        mApiService = DI.getNeighbourApiService();
        dNeighbour = (Neighbour) getIntent().getSerializableExtra("neighbour");
        this.AfficheInfoUser();
        this.changer_image_favoris();
        this.configureToolbar();
    }


    public void AfficheInfoUser() {

        if (dNeighbour != null) {

            mNomAvatar.setText(dNeighbour.getName());
            mNom.setText(dNeighbour.getName());
            mTelephone.setText(dNeighbour.getPhoneNumber());
            mAdresse.setText(dNeighbour.getAddress());
            mAPropos.setText(dNeighbour.getAboutMe());
            mSiteWeb.setText("www.facebook.fr/" + dNeighbour.getName());
            Glide.with(mAvatar.getContext())
                    .load(dNeighbour.getAvatarUrl())
                    .into(mAvatar);

        }


    }

    @Override
    public void onClick(View view) {

        if (view == mAjouterFavoris) {

            if (Objects.equals(mAjouterFavoris.getDrawable().getConstantState(), getResources().getDrawable(R.drawable.ic_favoris_off).getConstantState())) {
                mAjouterFavoris.setImageDrawable(getResources().getDrawable(R.drawable.ic_favoris_on));
                mApiService.createFavNeighbour(dNeighbour);
                Toast.makeText(getApplicationContext(), "" + dNeighbour.getName() + " ajouté en favoris", Toast.LENGTH_SHORT).show();


            } else {
                mAjouterFavoris.setImageDrawable(getResources().getDrawable(R.drawable.ic_favoris_off));
                mApiService.deleteFavNeighbour(dNeighbour);
                Toast.makeText(getApplicationContext(), "" + dNeighbour.getName() + " supprimé des favoris", Toast.LENGTH_SHORT).show();

            }

        }
    }

    public void changer_image_favoris() {

        if (dNeighbour.isFav() == true) {
            mAjouterFavoris.setImageDrawable(getResources().getDrawable(R.drawable.ic_favoris_on));
        }

    }


    private void configureToolbar() {
        Toolbar toolbar = findViewById(R.id.menu_detail);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
    }
}