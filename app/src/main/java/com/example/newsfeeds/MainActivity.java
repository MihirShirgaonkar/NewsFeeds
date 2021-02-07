package com.example.newsfeeds;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsfeeds.NewsClasses.Articles;
import com.example.newsfeeds.NewsClasses.Headline;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Spinner spinner;
    String countryName = "in";

    RecyclerView recyclerView;
    final String API_KEY = "917f03499c644394a5dff8579a9c8bdb";
    Adapters adapters;
    List<Articles> listData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        String country = getCountry();
        retrieveJson(country,API_KEY);

        spinner = findViewById(R.id.spinner);


        ArrayList<String> list = new ArrayList<>();
        list.add("Select Region");
        list.add("India");
        list.add("United States");
        list.add("United Kingdom");
        list.add("Turkey");
        list.add("Switzerland");
        list.add("South Africa");
        list.add("Singapore");
        list.add("Saudi Arabia");
        list.add("Australia");


        spinner.setAdapter(new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_spinner_dropdown_item,list));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position){
                    case 0:

                        break;
                    case 1:
                        countryName = "in";
                        String country = getCountry();
                        retrieveJson(country,API_KEY);

                        break;

                        case 2:
                        countryName = "us";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;

                            case 3:
                        countryName = "gb";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;

                            case 4:
                        countryName = "tr";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;

                        case 5:
                        countryName = "ch";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;

                        case 6:
                        countryName = "za";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;

                            case 7:
                        countryName = "sg";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;
 case 8:
                        countryName = "sa";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;
case 9:
                        countryName = "au";
                            country = getCountry();
                            retrieveJson(country,API_KEY);

                            break;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public String getCountry(){
//        Locale locale = Locale.getDefault();
//        String country = locale.getCountry();
        return countryName;
    }

    public void retrieveJson(String country,String apiKey){
        Call<Headline> call = ApiClient.getInstance().getApi().getHeadline(country,apiKey);
        call.enqueue(new Callback<Headline>() {
            @Override
            public void onResponse(Call<Headline> call, Response<Headline> response) {
                if (response.isSuccessful() && response.body().getArticles() != null){
                    listData.clear();

                    listData = response.body().getArticles();

                    adapters = new Adapters(MainActivity.this,listData);
                    recyclerView.setAdapter(adapters);

                }
            }

            @Override
            public void onFailure(Call<Headline> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}