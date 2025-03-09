package com.example.golf_malaga.AllCampsGolf;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.golf_malaga.CategoryAdapter;
import com.example.golf_malaga.CategoryModel;
import com.example.golf_malaga.MainActivity;
import com.example.golf_malaga.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CampsGolfList extends AppCompatActivity {
    RecyclerView recycler, categoryRecycler;
    LinearLayoutManager manager,managerCategory;
    static CampsGolfList_Adapter adapter;
    CategoryAdapter adapterCategory;

    ArrayList<CategoryModel> arrayCategory;
    static ArrayList<CampsGolfList_Model> array = new ArrayList<>();
    static ArrayList<CampsGolfList_Model> fullarray = new ArrayList<>();
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camp_golf_list);

        array.clear();

        //array = new ArrayList<>();
        array.add(new CampsGolfList_Model("Casares Golf Club", "Casares", R.drawable.casares,1));
        array.add(new CampsGolfList_Model("Valle Romano Golf", "Estepona", R.drawable.estepona,2));
        array.add(new CampsGolfList_Model("Golf Río Real", "Marbella", R.drawable.marbella,3));
        array.add(new CampsGolfList_Model("Miraflores Golf Club", "Mijas", R.drawable.mijas,4));
        array.add(new CampsGolfList_Model("La Quinta Golf", "Benahavís", R.drawable.laquinagolf,12));
        array.add(new CampsGolfList_Model("Lauro Golf", "Alhaurín de la Torre", R.drawable.laurogolf,9));
        array.add(new CampsGolfList_Model("Atalaya Golf", "Estepona", R.drawable.atalayagolf,2));
        array.add(new CampsGolfList_Model("Villa Padierna Golf", "Benahavís", R.drawable.villapadierna,12));
        array.add(new CampsGolfList_Model("Mijas Golf Club", "Mijas", R.drawable.mijasgolf,4));


        Collections.sort(array, new Comparator<CampsGolfList_Model>(){
            public int compare(CampsGolfList_Model obj1, CampsGolfList_Model obj2) {
                // ## Ascending order
                return obj1.getTowns().compareToIgnoreCase(obj2.getTowns()); // To compare string values

            }
        });
        fullarray.addAll(array);
        adapter = new CampsGolfList_Adapter(this, array);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recycler = findViewById(R.id.campgolf_recycler);
        recycler.setAdapter(adapter);
        recycler.setLayoutManager(manager);


        arrayCategory = new ArrayList<>();
        arrayCategory.add(new CategoryModel(9, "Alhaurín de la Torre"));
        arrayCategory.add(new CategoryModel(10, "Alhaurín El Grande"));
        arrayCategory.add(new CategoryModel(11, "Antequera"));
        arrayCategory.add(new CategoryModel(12, "Benahavís"));
        arrayCategory.add(new CategoryModel(13, "Benalmádena"));
        arrayCategory.add(new CategoryModel(1, "Casares"));
        arrayCategory.add(new CategoryModel(2, "Estepona"));
        arrayCategory.add(new CategoryModel(6, "Fuengirola"));
        arrayCategory.add(new CategoryModel(14, "Manilva"));
        arrayCategory.add(new CategoryModel(5, "Málaga"));
        arrayCategory.add(new CategoryModel(3, "Marbella"));
        arrayCategory.add(new CategoryModel(4, "Mijas"));
        arrayCategory.add(new CategoryModel(7, "Ojén"));
        arrayCategory.add(new CategoryModel(8, "Torremolinos"));
       arrayCategory.add(new CategoryModel(15, "Vélez Málaga"));

        adapterCategory = new CategoryAdapter(this, arrayCategory);

        managerCategory = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        categoryRecycler = findViewById(R.id.titleTownRecycler);
        categoryRecycler.setAdapter(adapterCategory);
        categoryRecycler.setLayoutManager(managerCategory);

        imageView=findViewById(R.id.imageExit);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //error
                fullarray.clear();
                startActivity(new Intent(CampsGolfList.this, MainActivity.class));
            }
        });

    }
   public static void showCampsByTown(int category) {

        array.clear();
        array.addAll(fullarray);

List<CampsGolfList_Model> filterCamp=new ArrayList<>();
for(CampsGolfList_Model c:array){

    if(c.getCategory()==category)

            filterCamp.add(c);
}

        array.clear();
        array.addAll(filterCamp);
        adapter.notifyDataSetChanged();


   }


}
