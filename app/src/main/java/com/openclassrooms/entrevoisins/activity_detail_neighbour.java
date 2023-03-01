package com.openclassrooms.entrevoisins;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;

public class activity_detail_neighbour extends AppCompatActivity {

    @BindView(R.id.fleche_retour)
    Button mFlecheRetour;
    @BindView(R.id.avatar_detail)
    ImageView mAvatar;
    @BindView(R.id.ajouter_favoris)
    FloatingActionButton mAjouterFavoris;
    @BindView(R.id.nom_detail)
    TextView mNom;
    @BindView(R.id.adresse_detail)
    TextView mAdresse;
    @BindView(R.id.telephone_detail)
    TextView mTelephone;
    @BindView(R.id.site_web_detail)
    TextView mSiteWeb;
    @BindView(R.id.a_propos_detail)
    TextView mAPropos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_neighbour);
    }
}