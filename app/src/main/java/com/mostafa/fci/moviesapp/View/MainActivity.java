package com.mostafa.fci.moviesapp.View;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mostafa.fci.moviesapp.Model.Movies.Movie;
import com.mostafa.fci.moviesapp.Model.Network.NetworkState;
import com.mostafa.fci.moviesapp.Presenter.Presenter;
import com.mostafa.fci.moviesapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IView{

    Presenter mPresenter;
    ArrayList<Movie> mMovies;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMovies = new ArrayList<>();

        recyclerView = findViewById(R.id.movies_recycler_view);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        adapter = new RecyclerViewAdapter(mMovies);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter.setOnClickedListener(new RecyclerViewAdapter.OnClickedListener() {
            @Override
            public void onClicked(int position) {
                Intent intent = new Intent(MainActivity.this,MovieDetailsActivity.class);
                intent.putExtra("movie",mMovies.get(position));
                intent.putExtra("index",position);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter = new Presenter(this);
    }



    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.destroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.items, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.refresh) {
            // Check the Internet Connection
            if (NetworkState.isOnLine(this)) {
                // Load Data From Server
                mPresenter.refreshData();
            }
            return true;
        }else if (id == R.id.favorite){
            startActivity(new Intent(MainActivity.this,FavoriteActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void updateListView(List<Movie> movies) {
        mMovies.clear();
        mMovies.addAll(movies);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showInternetError() {
        Toast toast = Toast.makeText(this, "Please Check Connection of Internet"
                , Toast.LENGTH_SHORT);
        View toastView = toast.getView();
        toastView.setBackgroundResource(R.drawable.toast_view_bg);
        TextView toastMessage = toastView.findViewById(android.R.id.message);
        toastMessage.setPadding(20,10,20,10);
        toastMessage.setTextColor(Color.WHITE);
        toastMessage.setGravity(Gravity.CENTER);
        toast.show();
    }
}
