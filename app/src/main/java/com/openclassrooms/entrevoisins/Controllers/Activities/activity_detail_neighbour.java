package com.openclassrooms.entrevoisins.Controllers.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.service.NeighbourApiService;

import java.util.List;
import java.util.Objects;

public class activity_detail_neighbour extends AppCompatActivity implements View.OnClickListener {

    ImageView mAvatar;
    FloatingActionButton mAjouterFavoris;
    TextView mNom;
    TextView mAdresse;
    TextView mTelephone;
    TextView mSiteWeb;
    TextView mAPropos;
    Bundle extras;
    String dname;
    boolean Fav;
    private List<Neighbour> mNeighbours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
        mAvatar = (ImageView) findViewById(R.id.avatar_detail);
        mNom = (TextView) findViewById(R.id.nom_detail);
        mAdresse = (TextView) findViewById(R.id.adresse_detail);
        mTelephone = (TextView) findViewById(R.id.telephone_detail);
        mSiteWeb = (TextView) findViewById(R.id.site_web_detail);
        mAPropos = (TextView) findViewById(R.id.a_propos_detail);
        mAjouterFavoris = (FloatingActionButton) findViewById(R.id.ajouter_favoris);
        mAjouterFavoris.setOnClickListener(this);
        extras = getIntent().getExtras();
        dname = extras.getString("name");
        Fav = extras.getBoolean("fav");
        mNeighbours = DI.getNeighbourApiService().getNeighbours();
        changer_image_favoris();
        AfficheInfoUser();
    }


    public void AfficheInfoUser() {

        if (extras != null) {

            String dphone = extras.getString("phone");
            String daddress = extras.getString("address");
            String dabout = extras.getString("about");
            String davatar = extras.getString("avatar");

            mNom.setText(dname);
            mTelephone.setText(dphone);
            mAdresse.setText(daddress);
            mAPropos.setText(dabout);
            mSiteWeb.setText("www.facebook.fr/" + dname);
            Glide.with(mAvatar.getContext())
                    .load(davatar)
                    .into(mAvatar);

        }


    }

    @Override
    public void onClick(View view) {

        if (view == mAjouterFavoris) {

            if (Objects.equals(mAjouterFavoris.getDrawable().getConstantState(), getResources().getDrawable(R.drawable.ic_favoris_off).getConstantState())) {
                mAjouterFavoris.setImageDrawable(getResources().getDrawable(R.drawable.ic_favoris_on));
                Toast.makeText(getApplicationContext(), "" + dname + " ajouté en favoris", Toast.LENGTH_SHORT).show();

            } else {
                mAjouterFavoris.setImageDrawable(getResources().getDrawable(R.drawable.ic_favoris_off));
                Toast.makeText(getApplicationContext(), "" + dname + " supprimé des favoris", Toast.LENGTH_SHORT).show();
            }

            for (Neighbour neighbour : mNeighbours) {
                if (Objects.equals(neighbour.getName(), dname) && neighbour.isFav() == false) {
                    neighbour.setFav(true);
                } else if (Objects.equals(neighbour.getName(), dname) && neighbour.isFav() == true) {
                    neighbour.setFav(false);
                }

            }
        }
    }

    public void changer_image_favoris() {

        for (Neighbour neighbour : mNeighbours) {
            if (neighbour.isFav() == true && Objects.equals(neighbour.getName(), dname)) {
                mAjouterFavoris.setImageDrawable(getResources().getDrawable(R.drawable.ic_favoris_on));
            }
        }
    }
}