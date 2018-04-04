package com.example.aimanrahmat.uccservicerating;

import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PageMain extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public static final String EXTRA_MESSAGE = "com.example.aimanrahmat.MESSAGE";

    ListView list1;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToogle;
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainpage);

        mToolbar = (Toolbar)findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        list1 = (ListView)findViewById(R.id.listCounter);

        String[] counter = new String[]{"1. Information","2. Academics", "3. Student Welfare", "4. Enrollment Unit", "5. International Students",
                "6. Financial", "7. Payment"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,android.R.id.text1,counter);

        list1.setAdapter(adapter);

        //ListView Item Click Listener
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

                // ListView Clicked item index
                int itemPosition = position;

                // ListView Clicked item value
                String  itemValue    = (String) list1.getItemAtPosition(position);

                Intent intent = new Intent(PageMain.this, PageRating.class);
                intent.putExtra(EXTRA_MESSAGE, itemValue);
                startActivity(intent);
            }
        });

        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        mToogle = new ActionBarDrawerToggle(this, mDrawerLayout,R.string.open,R.string.close);

        mDrawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView nView = (NavigationView)findViewById(R.id.navigation_view);
        nView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        if (mToogle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id==R.id.nav_info){
            Intent intent = new Intent(PageMain.this,PageInfo.class);
            startActivity(intent);
        }
        if (id==R.id.nav_stats){
            Intent intent = new Intent(PageMain.this,PageStats.class);
            startActivity(intent);
        }
        if (id==R.id.nav_about){
            Intent intent = new Intent(PageMain.this,PageAbout.class);
            startActivity(intent);
        }
        return false;
    }
}
