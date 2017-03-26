package nyc.c4q.dereksantos.spotifyfellowship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private TextView personIdTv;
    private TextView personNameTv;
    private TextView personFavoriteCityTv;
    private Button createPersonButton;
    private EditText personNameEt;
    private EditText personFavCityEt;
    private EditText personIdEt;
    private Button getRequesToPeopleButton;
    private Button postRequesToPeopleButton;
    private Button getRequestPreviousButton;
    private Button putRequestButton;
    private Button getOnePersonButton;
    private Button getRequesToPeopleButton2;
    private Button deletePersonButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // GET
        personIdTv = (TextView) findViewById(R.id.person_id);
        personNameTv = (TextView) findViewById(R.id.person_name);
        personFavoriteCityTv = (TextView) findViewById(R.id.person_favoritecity);

        getRequesToPeopleButton = (Button) findViewById(R.id.getpeople);
        getRequesToPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPeople();
            }
        });


        postRequesToPeopleButton = (Button) findViewById(R.id.posttopeople);
        postRequesToPeopleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = "Sean";
                String favCity = "New York";

                postPerson(name, favCity);
            }
        });


        getRequestPreviousButton = (Button) findViewById(R.id.getpreviousperson);
        getRequestPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(1);
                getPerson(id);
            }
        });

        putRequestButton = (Button) findViewById(R.id.puttopeople);
        putRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putPerson();
            }
        });

        getOnePersonButton = (Button) findViewById(R.id.getspecificperson);
        getOnePersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = String.valueOf(1);
                getPerson(id);
            }
        });

        deletePersonButton = (Button) findViewById(R.id.deleteperson);
        deletePersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });

        getRequesToPeopleButton2 = (Button) findViewById(R.id.getpeople2);
        getRequesToPeopleButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getPeople();
            }
        });
    }

    private void getPerson(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4567/") // "http://10.0.2.2:8080/" or try "http://127.0.0.1:8080/" or "http://192.168.1.136:8080/" or "http://164.92.124.42:8080/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyApi service = retrofit.create(SpotifyApi.class);
        Call<Person> call = service.getPerson(id);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                personNameTv.setText(person.getName());
                personFavoriteCityTv.setText(person.getFavoriteCity());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void getPeople() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4567/") // "http://10.0.2.2:8080/" or try "http://127.0.0.1:8080/" or "http://192.168.1.136:8080/" or "http://164.92.124.42:8080/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyApi service = retrofit.create(SpotifyApi.class);
        Call<List<Person>> call = service.getPeople();

        call.enqueue(new Callback<List<Person>>() {
            @Override
            public void onResponse(Call<List<Person>> call, Response<List<Person>> response) {
                List<Person> personList = response.body();

                if (personList.size() > 0 ) {
                    Person person = personList.get(0);
                    personIdTv.setText(person.getId());
                    personNameTv.setText(person.getName());
                    personFavoriteCityTv.setText(person.getFavoriteCity());
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Person>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void putPerson() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4567/") // "http://10.0.2.2:8080/" or try "http://127.0.0.1:8080/" or "http://192.168.1.136:8080/" or "http://164.92.124.42:8080/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyApi service = retrofit.create(SpotifyApi.class);

        Map<String, String> fieldMap = new HashMap<>();
        fieldMap.put("name", "Sean");
        fieldMap.put("favoriteCity", "Brooklyn");
        Call<Person> call = service.putPerson(fieldMap);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Person person = response.body();
                personNameTv.setText(person.getName());
                personFavoriteCityTv.setText(person.getFavoriteCity());
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
            }
        });
    }

    private void postPerson(String name, String favCity) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:4567/") // "http://10.0.2.2:8080/" or try "http://127.0.0.1:8080/" or "http://192.168.1.136:8080/" or "http://164.92.124.42:8080/"
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SpotifyApi service = retrofit.create(SpotifyApi.class);
        Call<Person> call = service.createPerson(name, favCity);

        call.enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                Toast.makeText(MainActivity.this, "This Worked! User ID:" + response.body().getId(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t);
                Toast.makeText(MainActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
