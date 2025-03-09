package com.example.golf_malaga;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.golf_malaga.Login.LoginClass;
import com.google.android.material.imageview.ShapeableImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
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
        if (city_str.equals("Golf Río Real")) {
            mainimage.setImageResource(R.drawable.marbella);
            campo_name.setText("Golf Río Real, Marbella");
            description.setText("Río Real Golf se extiende a lo largo de más de 6.000 metros, ofreciendo 18 hoyos y un par 72 que desafía a jugadores de todos los niveles.");
            reserve.setText("por telefono +34 952 765 732");
            reservesite="https://rioreal.com/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.com/maps/place/R%C3%ADo+Real+Golf+%26+Hotel+-+Marbella/@36.5127761,-4.8490844,17z/data=!3m1!4b1!4m9!3m8!1s0xd72d8825f6bf80d:0x95f748c7b2f5e1!5m2!4m1!1i2!8m2!3d36.5127762!4d-4.8442135!16s%2Fg%2F11btv67nvd?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoASAFQAw%3D%3D";



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.trocadero);
            attr1name.setText("Trocadero Arena");
            attr1des.setText("Cocina mediterránea, europea");
            locationrest1="https://grupotrocadero.com/trocadero-arena/";

            //restaurante 2
            attr2img.setImageResource(R.drawable.loggia);
            attr2name.setText("La Loggia");
            attr2des.setText("Cocina italiana,mediterránea");
            locationrest2="https://www.anantara.com/es/villa-padierna-marbella/restaurants/la-loggia";
            //hotels
            //hotel 1
            hotel1name.setText("Rio Real Golf Hotel");
            hotel1des.setText("Hotel de 30 habitaciones. Cada suite ofrece TV, minibar, WIFI gratis.");
            hotel1img.setImageResource(R.drawable.riohotel);
            locationhotel1="https://rioreal.com/";
            //hotel2
            hotel2name.setText("Amare Beach Hotel");
            hotel2des.setText("Situado en pleno paseo marítimo y 5 minutos a pie del casco antiguo de Marbella. ");
            hotel2img.setImageResource(R.drawable.amarebeach);
            locationhotel2="https://www.amarehotels.com/amare-marbella/?utm_source=tripadvisor&utm_medium=perfil-plus&utm_content=amare-marbella";


            //tiempo
            FetchWeatherData("Marbella");


        }
        if (city_str.equals("Miraflores Golf Club")) {
            mainimage.setImageResource(R.drawable.mijas);
            campo_name.setText("Miraflores Golf Club, Mijas");
            description.setText("Un campo par 71 que se extiende por 5.148 metros. Ofrece 18 hoyos distintivos que presentan un desafío variado, desde calles anchas hasta greens elevados.");
            reserve.setText("por telefono +34 952 931 960");
            reservesite="https://www.mirafloresgolf.es/?lang=es";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.es/maps/place/Miraflores+Golf/@36.50194,-4.704454,17z/data=!3m1!4b1!4m6!3m5!1s0xd731fc3cac95395:0x81767df2ad1734a6!8m2!3d36.50194!4d-4.704454!16s%2Fg%2F11f124_0pd?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoASAFQAw%3D%3D";



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.roccagrill);
            attr1name.setText("Rocca Grill Restaurant");
            attr1des.setText("Restaurante de carne, barbacoa");
            locationrest1="https://roccagrill.es/en/home/";

            //restaurante 2
            attr2img.setImageResource(R.drawable.piscineclub);
            attr2name.setText("La Piscine Club");
            attr2des.setText("Confitería, cocina italiana");
            locationrest2="https://www.facebook.com/lapiscineclub/";

            //hotels
            //hotel 1
            hotel1name.setText("Marriott's Beach Resort");
            hotel1des.setText("Offers with a balcony, TV and CD player. The apartments have direct access to the beach.");
            hotel1img.setImageResource(R.drawable.mariotthotel);
            locationhotel1="https://www.marriott.com/es/hotels/agpmb-marriotts-marbella-beach-resort/overview/";
            //hotel2
            hotel2name.setText("Terrace Costa Hotel");
            hotel2des.setText("Está situado en el centro de Fuengirola, a 350 metros de la playa, y ofrece solárium, vistas al jardín y WiFi. ");
            hotel2img.setImageResource(R.drawable.terrace);
            locationhotel2="https://www.terracecosta.com/";


            //tiempo
            FetchWeatherData("Mijas");


        }
        if (city_str.equals("La Quinta Golf")) {
            mainimage.setImageResource(R.drawable.laquinagolf);
            campo_name.setText("La Quinta Golf, Benahavís");
            description.setText("El campo de golf cuenta con 27 hoyos, lo que lo convierte en uno de los campos más exclusivos y desafiantes de la región. Los tres recorridos componen La Quinta Golf and Country Club");
            reserve.setText("on line");
            reservesite="https://open.imaster.golf/en/quinta/disponibilidad";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.es/maps/place/La+Quinta+Golf+%26+Country+Club/@36.512381,-4.997348,17z/data=!3m1!4b1!4m6!3m5!1s0xd72d5ebbd632f19:0x920a840b72c36e72!8m2!3d36.512381!4d-4.9947731!16s%2Fg%2F1z3t2c7kl?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoASAFQAw%3D%3D";



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.gamapola);
            attr1name.setText("El Gamonal");
            attr1des.setText("Comida mediterranea, europea");
            locationrest1="https://elgamonal.es/";

            //restaurante 2
            attr2img.setImageResource(R.drawable.pradohalcones);
            attr2name.setText("Prado Halcones");
            attr2des.setText("Comida mediterranea, europea");
            locationrest2="https://www.visitacostadelsol.com/malaga-costa-del-sol/gastronomia/donde-comer/prado-halcones-p101925";

            //hotels
            //hotel 1
            hotel1name.setText("Hotel Amanhavis");
            hotel1des.setText("Se encuentra a tan solo unos 5 kilómetros de las playas principales de la Costa del Sol.");
            hotel1img.setImageResource(R.drawable.amanavis);
            locationhotel1="https://www.amanhavis.com/es/";
            //hotel2
            hotel2name.setText("Terrace Costa Hotel");
            hotel2des.setText("Está situado en el centro de Fuengirola, a 350 metros de la playa, y ofrece solárium, vistas al jardín y WiFi. ");
            hotel2img.setImageResource(R.drawable.terrace);
            locationhotel2="https://www.terracecosta.com/";


            //tiempo
            FetchWeatherData("Benahavís");


        }
        if (city_str.equals("Lauro Golf")) {
            mainimage.setImageResource(R.drawable.laurogolf);
            campo_name.setText("Lauro Golf, Alhaurín de la Torre");
            description.setText("Tiene 27 hoyos que se dividen en tres recorridos diferentes, cada uno con sus propias características y desafíos. Los hoyos están en perfecto estado y ofrecen una gran variedad de posibilidades para los jugadores.");
            reserve.setText("tel. +34 952 412 767");
            reservesite="https://www.laurogolf.com/golf/campo-de-golf/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.com/maps/place/Lauro+Golf+Resort/@36.631716,-4.6627402,12.75z/data=!4m10!1m2!2m1!1sLauro+Golf!3m6!1s0xd72e5cbaf7ce167:0xa2f0be33690919a1!8m2!3d36.651329!4d-4.6307769!15sCgpMYXVybyBHb2xmWgwiCmxhdXJvIGdvbGaSAQtnb2xmX2NvdXJzZeABAA!16s%2Fg%2F1tgq7__h?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoASAFQAw%3D%3D";



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.barrisa);
            attr1name.setText("Bar la Risa");
            attr1des.setText("Bar, comida europea");
            locationrest1="https://www.tripadvisor.es/Restaurant_Review-g1028572-d7689191-Reviews-Bar_la_Risa-Alhaurin_de_la_Torre_Province_of_Malaga_Andalucia.html";

            //restaurante 2
            attr2img.setImageResource(R.drawable.venta);
            attr2name.setText("Venta La Porrita");
            attr2des.setText("Comida mediterranea, española");
            locationrest2="https://www.tripadvisor.es/Restaurant_Review-g1028572-d5444785-Reviews-Venta_La_Porrita-Alhaurin_de_la_Torre_Province_of_Malaga_Andalucia.html";

            //hotels
            //hotel 1
            hotel1name.setText("Cortijo Chico Málaga");
            hotel1des.setText("Está situado en Alhaurín de la Torre, a 10 minutos en coche del aeropuerto de Málaga, ofrece aparcamiento gratuito y conexión WiFi gratuita.");
            hotel1img.setImageResource(R.drawable.cortijo);
            locationhotel1="https://hotelcortijochico.com/";
            //hotel2
            hotel2name.setText("Terrace Costa Hotel");
            hotel2des.setText("Está situado en el centro, ofrece solárium, vistas al jardín y WiFi. ");
            hotel2img.setImageResource(R.drawable.terrace);
            locationhotel2="https://www.terracecosta.com/";


            //tiempo
            FetchWeatherData("Alhaurín de la Torre");


        }
        if (city_str.equals("Villa Padierna Golf")) {
            mainimage.setImageResource(R.drawable.villapadierna);
            campo_name.setText("Villa Padierna Golf, Benahavís");
            description.setText("Este club cuenta con tres campos de golf distintivos, cada uno con 18 hoyos: Flamingos Golf, Tramores Golf, Alferini Golf.");
            reserve.setText("tel. +34 952 412 767");
            reservesite="https://www.villapadierna.es/es/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.com/maps/place/Villa+Padierna+Golf+Club/@36.4755383,-5.0568276,17z/data=!3m2!4b1!5s0xd732ac94ac3a797:0xfdaf5607cddd074c!4m6!3m5!1s0xd732bba1e84d969:0xcfc6b383d8799f62!8m2!3d36.4755383!4d-5.0542527!16s%2Fg%2F11gpncj0lf?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D";



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.barrisa);
            attr1name.setText("La Veranda");
            attr1des.setText("Comida española, europea");
            locationrest1="https://www.anantara.com/en/villa-padierna-marbella/restaurants/la-veranda";

            //restaurante 2
            attr2img.setImageResource(R.drawable.loggia);
            attr2name.setText("La Loggia");
            attr2des.setText("Comida mediterranea, española");
            locationrest2="https://www.anantara.com/en/villa-padierna-marbella/restaurants/la-loggia";

            //hotels
            //hotel 1
            hotel1name.setText("Hotel Amanhavis");
            hotel1des.setText("Está situado cerca de la costa, un elegante y tranquilo refugio a pocos pasos del club de golf.");
            hotel1img.setImageResource(R.drawable.cortijo);
            locationhotel1="https://www.guestreservations.com/amanhavis-hotel-benahavis/booking?utm_source=google&utm_medium=cpc&utm_campaign=991006006&gad_source=1&gclid=EAIaIQobChMI2f3q3uf8iwMVPToGAB1-sTrVEAAYASAAEgLUBfD_BwE";
            //hotel2
            hotel2name.setText("Terrace Costa Hotel");
            hotel2des.setText("Está situado en el centro, ofrece solárium, vistas al jardín y WiFi. ");
            hotel2img.setImageResource(R.drawable.terrace);
            locationhotel2="https://www.terracecosta.com/";


            //tiempo
            FetchWeatherData("Benahavís");


        }
        if (city_str.equals("Atalaya Golf")) {
            mainimage.setImageResource(R.drawable.atalayagolf);
            campo_name.setText("Atalaya Golf, Estepona");
            description.setText("Un campo de 18 hoyos, situado cerca del centro de Estepona y en el lado interior de la carretera principal de la Costa,  integrado  en el paisaje natural de la majestuosa Sierra Bermeja.");
            reserve.setText("tel. +34 952 412 767");
            reservesite="https://www.atalaya-golf.com/es/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.com/maps/place/Atalaya+Golf+%26+Country+Club/@36.4812417,-5.0201656,17z/data=!3m2!4b1!5s0xd732a5fe8ba9bb9:0xe6c15bd925519631!4m6!3m5!1s0xd732ac3a943e8ed:0x528270e6e1964448!8m2!3d36.4812417!4d-5.0175907!16s%2Fg%2F1hc16nrl1?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D";



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


            locationrest1="https://columelavalleromanogolf.es/";
            locationrest2="https://gastroranking.es/r/pizzeria-piccolino-valle-romano_261082/";
            locationhotel1="https://casa-baha-de-casares.es-andalucia.com/es/";
            locationhotel2="https://www.onahotels.com/en/apart-hotel-valle-romano-estepona/?gad_source=1&gclid=CjwKCAiAiaC-BhBEEiwAjY99qJ6JciV0caSFXdWmpfQvWuIY7JSGAC_tks727Dai1oGidN8skIa7ERoCFHkQAvD_BwE&gclsrc=aw.ds";
            //tiempo
            FetchWeatherData("Estepona");

        }
        if (city_str.equals("Mijas Golf Club")) {
            mainimage.setImageResource(R.drawable.mijasgolf);
            campo_name.setText("Mijas Golf Club, Mijas");
            description.setText("Dos campos de golf de renombre, Los Lagos y Los Olivos, Mijas Golf Club.Los Lagos tiene 18 hoyos y 9 lagos que atraviesan el campo. Los Olivos también tiene 18 hoyos y recorrido de 5.840 metros.");
            reserve.setText("tel. +34 952 412 767");
            reservesite="https://mijasgolf.org/";
            service.setText("academia de golf, tienda, alquiler de palos, sala de fitness");
            location = "https://www.google.com/maps/place/Mijas+Golf+International+SAU+-+MIJAS+GOLF+CLUB/@36.5492897,-4.6717261,17z/data=!3m1!4b1!4m6!3m5!1s0xd72e1bce75e4717:0x94c6e4b70974f168!8m2!3d36.5492897!4d-4.6691512!16s%2Fg%2F1ptzrlplx?entry=ttu&g_ep=EgoyMDI1MDMwNC4wIKXMDSoJLDEwMjExNDU1SAFQAw%3D%3D";



//restaurantes
            //restaurante 1
            attr1img.setImageResource(R.drawable.roccagrill);
            attr1name.setText("Rocca Grill Restaurant");
            attr1des.setText("Restaurante de carne, barbacoa");
            locationrest1="https://roccagrill.es/en/home/";

            //restaurante 2
            attr2img.setImageResource(R.drawable.piscineclub);
            attr2name.setText("La Piscine Club");
            attr2des.setText("Confitería, cocina italiana");
            locationrest2="https://www.facebook.com/lapiscineclub/";

            //hotels
            //hotel 1
            hotel1name.setText("Marriott's Beach Resort");
            hotel1des.setText("Offers with a balcony, TV and CD player. The apartments have direct access to the beach.");
            hotel1img.setImageResource(R.drawable.mariotthotel);
            locationhotel1="https://www.marriott.com/es/hotels/agpmb-marriotts-marbella-beach-resort/overview/";
            //hotel2
            hotel2name.setText("Terrace Costa Hotel");
            hotel2des.setText("Está situado en el centro de Fuengirola, a 350 metros de la playa, y ofrece solárium, vistas al jardín y WiFi. ");
            hotel2img.setImageResource(R.drawable.terrace);
            locationhotel2="https://www.terracecosta.com/";


            //tiempo
            FetchWeatherData("Mijas");



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
                startActivity(new Intent(CampDetails.this, LoginClass.class));
            }
        });
        /*
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CampDetails.this, MainActivity.class));
            }
        });*/

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
