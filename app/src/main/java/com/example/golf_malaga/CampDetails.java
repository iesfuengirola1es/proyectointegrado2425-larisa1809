package com.example.golf_malaga;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.golf_malaga.Login.SigningActivity;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CampDetails extends AppCompatActivity {
    ShapeableImageView mainimage, attr1img, attr2img, hotel1img, hotel2img;
    TextView campo_name, description, weatherinfo, reserve,service,attr1name, attr1des, attr2name, attr2des, hotel1name, hotel1des, hotel2name, hotel2des,rest1btn,rest2btn,hotel1btn,hotel2btn;
    Button locationbtn;

    String location,locationrest1,locationrest2,locationhotel1,locationhotel2,reservesite;


    //tiempo
    private TextView  temperatureText,humidityText,windText,proxWeather;
    private ImageView weatherImage,chatbtn,volver;

    private EditText cityNameInput;
    private ListView listView;
    private ArrayList<String> temperatureList;
    private static final String API_KEY = "f3f433d08ff3b744e9ad3b5f41ed52b9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_details);

        mainimage = findViewById(R.id.campdetails_image);

        campo_name = findViewById(R.id.campdetails_name);
        description = findViewById(R.id.campdetails_description);
        reserve = findViewById(R.id.reserve);
        service = findViewById(R.id.service);


        weatherinfo = findViewById(R.id.campdetails_weather);
        //proxWeather=findViewById(R.id.proxDias);
        //listView = findViewById(R.id.list_weather);
        temperatureList = new ArrayList<>();

        attr1img = findViewById(R.id.campdetails_place1image);
        attr2img = findViewById(R.id.campdetails_place2image);
        attr1name = findViewById(R.id.campdetails_place1name);
        attr2name = findViewById(R.id.campdetails_place2name);
        attr1des = findViewById(R.id.campdetails_place1description);
        attr2des = findViewById(R.id.campdetails_place2description);

        hotel1img = findViewById(R.id.campdetails_food1image);
        hotel2img = findViewById(R.id.campdetails_food2image);

        hotel1name = findViewById(R.id.campdetails_food1name);
        hotel2name = findViewById(R.id.campdetails_food2name);

        hotel1des = findViewById(R.id.campdetails_food1description);
        hotel2des = findViewById(R.id.campdetails_food2description);

        rest1btn= findViewById(R.id.rest1btn);
        rest2btn= findViewById(R.id.rest2btn);
        hotel1btn= findViewById(R.id.hotel1btn);
        hotel2btn= findViewById(R.id.hotel2btn);

                locationbtn = findViewById(R.id.campdetails_getlocationbtn);
        chatbtn = findViewById(R.id.chat);
        volver=findViewById(R.id.imageExitCamp);

        //tiempo

        temperatureText = findViewById(R.id.temperatureText);
        //humidityText = findViewById(R.id.humidityText);
        windText= findViewById(R.id.windText);
        //descriptionText = findViewById(R.id.descriptionText);
        weatherImage = findViewById(R.id.weatherIcon);

        cityNameInput = findViewById(R.id.campNameInput);




        String city_str = getIntent().getStringExtra("putextra_city");
        String state_str = getIntent().getStringExtra("putextra_state");
        int image_int = getIntent().getIntExtra("putextra_image",0);


        if (city_str.equals("Casares Golf Club")) {
            mainimage.setImageResource(R.drawable.casares);
            campo_name.setText("Casares Golf Club, Casares");
            description.setText("Campo de golf de 9 hoyos y longitud de 2.289 metros, se compone de tres pares 3, cinco pares 4 y un par 5 conocido por su nivel de dificultad.");
            reserve.setText("por telefono +34 952 937 895");
            reservesite="https://www.casarescostagolf.com/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");



//restaurantes
           //restaurante 1
            attr1img.setImageResource(R.drawable.sunset);
            attr1name.setText("Sunset");
            attr1des.setText("Cocina mediterránea, desayunos, cenas");


           //restaurante 2
            attr2img.setImageResource(R.drawable.bahia);
            attr2name.setText("Bahia de Casares");
            attr2des.setText("Cocina mediterránea, española, desayunos");

    //hotels
            //hotel 1
            hotel1name.setText("Casa Bahia de Casares");
            hotel1des.setText("Situado menos de 1 km de la playa, WiFi y aparcamiento privado");
            hotel1img.setImageResource(R.drawable.bahiahotel);

            //hotel2
            hotel2name.setText("Hotel Rural Casares");
            hotel2des.setText("17 habitaciones dobles, baño privado y balcón al exterior");
            hotel2img.setImageResource(R.drawable.casareshotel);

            location = "https://www.google.com/maps/place/Casares+Costa+Golf/@36.3897895,-5.2257389,17z/data=!3m1!4b1!4m6!3m5!1s0xd0cd0a1113c1af3:0xdedd29f3f6e2d61b!8m2!3d36.3897895!4d-5.223164!16s%2Fg%2F1tzzws53?entry=ttu&g_ep=EgoyMDI1MDIwMi4wIKXMDSoASAFQAw%3D%3D";
            locationrest1="https://www.facebook.com/buagardenrestaurant/?locale=es_ES";
            locationrest2="https://www.vivamanilva.com/item/restaurante-bahia-de-casares/";
            locationhotel1="https://casa-baha-de-casares.es-andalucia.com/es/";
            locationhotel2="https://www.hotelcasares.es/";

            //tiempo
            FetchWeatherData("Casares");
            //new ShowWeather().execute("Casares");

        }
        if (city_str.equals("Valle Romano Golf")) {
            mainimage.setImageResource(R.drawable.estepona);
            campo_name.setText("Valle Romano Golf, Estepona");
            description.setText("Un campo de golf con 18 hoyos, par 71 y 6.213 metros de longitud, para golfistas aficionados y profesionales. Con un diseño meticuloso que considera cada detalle y un campo desafiante.");
            reserve.setText("por telefono +34952800600");
            reservesite="https://valleromanogolf.com/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.columena);
            attr1name.setText("Restaurante Columela");
            attr1des.setText("Cocina mediterránea, organic, vegan");


            //restaurante 2
            attr2img.setImageResource(R.drawable.piccolino);
            attr2name.setText("Pizzeria Piccolino");
            attr2des.setText("Cocina italiana, pizza");

            //hotels
            //hotel 1
            hotel1name.setText("Casa Bahia de Casares");
            hotel1des.setText("Situado menos de 1 km de la playa, WiFi y aparcamiento privado");
            hotel1img.setImageResource(R.drawable.bahiahotel);

            //hotel2
            hotel2name.setText("Ona Valle Romano");
            hotel2des.setText("5 min de la playa y 20 minutos de la zona de juegos");
            hotel2img.setImageResource(R.drawable.oneromano);

            location = "https://www.google.com/maps/place/Valle+Romano+Golf/@36.4312755,-5.1976174,17z/data=!3m1!4b1!4m6!3m5!1s0xd0cd736d1c85de1:0x6c65c4e367c03d3d!8m2!3d36.4312712!4d-5.1950425!16s%2Fg%2F1yg4ddhsx?entry=ttu&g_ep=EgoyMDI1MDIyMy4xIKXMDSoASAFQAw%3D%3D";
            locationrest1="https://columelavalleromanogolf.es/";
            locationrest2="https://gastroranking.es/r/pizzeria-piccolino-valle-romano_261082/";
            locationhotel1="https://casa-baha-de-casares.es-andalucia.com/es/";
            locationhotel2="https://www.onahotels.com/en/apart-hotel-valle-romano-estepona/?gad_source=1&gclid=CjwKCAiAiaC-BhBEEiwAjY99qJ6JciV0caSFXdWmpfQvWuIY7JSGAC_tks727Dai1oGidN8skIa7ERoCFHkQAvD_BwE&gclsrc=aw.ds";
            //tiempo
            FetchWeatherData("Estepona");

        }
        locationbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(location)));
            }
        });
        rest1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(locationrest1)));
            }
        });
        rest2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(locationrest2)));
            }
        });
        hotel1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(locationhotel1)));
            }
        });
        hotel2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(locationhotel2)));
            }
        });
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(reservesite)));
            }
        });




        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CampDetails.this, SigningActivity.class));
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CampDetails.this, MainActivity.class));
            }
        });

    }

    private void FetchWeatherData(String cityName) {
        String url = "https:api.openweathermap.org/data/2.5/weather?q="+cityName + "&appid="+ API_KEY + "&units=metric";
        //String url = "https:api.openweathermap.org/data/3.0/onecall/day_summary?q="+cityName + "&appid="+ API_KEY + "&units=metric";
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() ->
                {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    try {
                        Response response = client.newCall(request).execute();
                        String result = response.body().string();
                        runOnUiThread(() -> updateUI(result));
                    } catch (IOException e)
                    {
                        e.printStackTrace();;
                    }
                }
        );


}

    private void updateUI(String result)
    {
        if(result != null)
        {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONObject main =  jsonObject.getJSONObject("main");
                double temperature = main.getDouble("temp");
                //double humidity = main.getDouble("humidity");
                double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");

                String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                String iconCode = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
                String resourceName = "ic_" + iconCode;
                int resId = getResources().getIdentifier(resourceName, "drawable", getPackageName());
                weatherImage.setImageResource(resId);

                //cityNameText.setText(jsonObject.getString("name"));
                temperatureText.setText(String.format("%.0f°", temperature));
                //humidityText.setText(String.format("%.0f%%", humidity));
                windText.setText(String.format("%.0f km/h", windSpeed));
                //descriptionText.setText(description);
            } catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
    /*
    private class ShowWeather extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String cityName = params[0];
            String apiKey = "f3f433d08ff3b744e9ad3b5f41ed52b9";
            String urlString = "https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid=" + apiKey + "&units=metric";
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL(urlString);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray listArray = jsonObject.getJSONArray("list");
                for (int i = 0; i < listArray.length(); i++) {
                    JSONObject main = listArray.getJSONObject(i).getJSONObject("main");
                    String temp=main.getString("temp");
                    String humidity=main.getString("humidity");

                    //String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                    temperatureList.add("Temp: " + temp + "°C, humidity: " + humidity+"%");


                    //String tempMin = main.getString("temp_min");
                    //String tempMax = main.getString("temp_max");
                    //temperatureList.add("Min: " + tempMin + "°C, Max: " + tempMax + "°C");
                    i=i+8;
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(CampDetails.this, android.R.layout.simple_list_item_1, temperatureList);
                listView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
*/

}
