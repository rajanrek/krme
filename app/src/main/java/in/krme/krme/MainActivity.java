package in.krme.krme;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Toolbar mToolbar;
    private FirebaseAuth mAuth;
    public FirebaseUser user;

    private ViewPager myViewPager;
    private TabLayout myTabLayout;
    private TabsPagerAdapter myTabsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if(user == null){
            startActivity(new Intent(this, StartPageActivity.class));
            finish();
        }
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        //Tab for main activites

        myViewPager = (ViewPager) findViewById(R.id.main_tab_pager);
        myTabsPagerAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        myViewPager.setAdapter(myTabsPagerAdapter);
        myTabLayout = (TabLayout) findViewById(R.id.main_tab);
        myTabLayout.setupWithViewPager(myViewPager);


        mToolbar = (Toolbar) findViewById(R.id.main_app_bar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("KRMe");

        mAuth.signOut();
    }


    @Override
    protected void onStart()
    {
        super.onStart();


        if(mAuth.getCurrentUser() == null)
        {
            finish();
            startActivity(new Intent(this, StartPageActivity.class));
        }

    }

    private void LogOutUser()
    {
       startActivity(new Intent(this,StartPageActivity.class));
        Intent startpageIntent = new Intent(MainActivity.this, StartPageActivity.class);
        startpageIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(startpageIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
         super.onOptionsItemSelected(item);
         if(item.getItemId() == R.id.main_logout_button)
         {
             mAuth.signOut();


         }

         return true;
    }
}
