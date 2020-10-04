package com.setsoft.ydskelimelik.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.setsoft.ydskelimelik.R;
import com.setsoft.ydskelimelik.adapter.AllWordsAdapter;
import com.setsoft.ydskelimelik.adapter.listeners.IAllWordsAdapterListener;
import com.setsoft.ydskelimelik.base.IBaseView;
import com.setsoft.ydskelimelik.model.Word;
import com.setsoft.ydskelimelik.presenter.AllWordsPresenter;
import com.setsoft.ydskelimelik.presenter.IAllWordsPresenter;

import java.util.List;

public class AllWordsActivity extends AppCompatActivity implements IBaseView,IAllWordsView,IAllWordsAdapterListener{
    private Button btnToday, btnWeek, btnMonth, btnAllTime;
    private RecyclerView recyclerView;
    private AllWordsAdapter adapter;
    private List<Word> list;
    private IAllWordsPresenter presenter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_words);
        bindViews();
        presenter = new AllWordsPresenter(this, this);
        btnToday.setBackground(getResources().getDrawable(R.drawable.bg_allwords_btn_menu));
        presenter.onTodayClick();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return true;
            }
        });

    }
    @Override
    public void bindViews() {
        btnToday = findViewById(R.id.btnToday);
        btnWeek = findViewById(R.id.btnWeek);
        btnMonth = findViewById(R.id.btnMonth);
        btnAllTime = findViewById(R.id.btnAllTime);
        recyclerView = findViewById(R.id.recyclerView);
        searchView=findViewById(R.id.searchView);
    }

    public void setButtonsBackground() {
        btnToday.setBackgroundColor(Color.TRANSPARENT);
        btnWeek.setBackgroundColor(Color.TRANSPARENT);
        btnMonth.setBackgroundColor(Color.TRANSPARENT);
        btnAllTime.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    public void setAdapterData(List<Word>list) {
        this.list=list;
        adapter=new AllWordsAdapter(this,list,this);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setItemViewCacheSize(list.size());
        recyclerView.setAdapter(adapter);
    }

    public void onTodayClick(View view) {
        presenter.onTodayClick();
        setButtonsBackground();
        btnToday.setBackground(getResources().getDrawable(R.drawable.bg_allwords_btn_menu));
    }

    public void onWeeklyClick(View view) {
        presenter.onWeeklyClick();
        setButtonsBackground();
        btnWeek.setBackground(getResources().getDrawable(R.drawable.bg_allwords_btn_menu));
    }

    public void onMonthlyClick(View view) {
        presenter.onMonthlyClick();
        setButtonsBackground();
        btnMonth.setBackground(getResources().getDrawable(R.drawable.bg_allwords_btn_menu));
    }

    public void onAllWordsClick(View view) {
        presenter.onAllWordsClick();
        setButtonsBackground();
        btnAllTime.setBackground(getResources().getDrawable(R.drawable.bg_allwords_btn_menu));
    }


    @Override
    public void onItemClick(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
        Toast.makeText(this, "Single Clicked"+position, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onLongItemClick(int position) {
        Toast.makeText(this, "Long Clicked"+position, Toast.LENGTH_SHORT).show();
    }


}
