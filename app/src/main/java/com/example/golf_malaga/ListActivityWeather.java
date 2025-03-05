package com.example.golf_malaga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.os.AsyncTask;


public class ListActivityWeather extends AppCompatActivity
        {
            private ListView listView;
            private ArrayList<String> temperatureList;

            @Override
            protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_list_weather);

                listView = findViewById(R.id.list_weather);
                temperatureList = new ArrayList<>();
                new FetchWeatherData().execute("London");
            }

            private class FetchWeatherData extends AsyncTask<String, Void, String> {
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
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(ListActivityWeather.this, android.R.layout.simple_list_item_1, temperatureList);
                        listView.setAdapter(adapter);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }





/*{
    private static final String API_KEY = "f3f433d08ff3b744e9ad3b5f41ed52b9";
    //ArrayList<WeatherModel> weatherModel;
    //ListView listweather;
    //WeatherAdapter weatherAdapter;
    private ListView listView;
    private ArrayList<String> temperatureList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_weather);

        listView = findViewById(R.id.weather_list);
        temperatureList = new ArrayList<>();
        new FetchWeatherData().execute();
    }

    private class FetchWeatherData extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder result = new StringBuilder();
            try {
                URL url = new URL("https://api.openweathermap.org/data/2.5/forecast?q=Casares&appid=f3f433d08ff3b744e9ad3b5f41ed52b9&units=metric");
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
                JSONArray list = jsonObject.getJSONArray("list");
                for (int i = 0; i < list.length(); i++) {
                    JSONObject main = list.getJSONObject(i).getJSONObject("main");
                    String tempMin = main.getString("temp_min");
                    String tempMax = main.getString("temp_max");
                    temperatureList.add("Min: " + tempMin + "°C, Max: " + tempMax + "°C");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(ListActivityWeather.this, android.R.layout.simple_list_item_1, temperatureList);
                listView.setAdapter(adapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}*/




