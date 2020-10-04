package com.setsoft.ydskelimelik.adapter;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.setsoft.ydskelimelik.R;
import com.setsoft.ydskelimelik.adapter.listeners.IAllWordsAdapterListener;
import com.setsoft.ydskelimelik.model.Word;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AllWordsAdapter extends RecyclerView.Adapter<AllWordsAdapter.MyViewHolder> implements Filterable {
    private Context context;
    private List<Word> mData;
    private List<Word> mDataAll;
    private IAllWordsAdapterListener listener;
    private ObjectAnimator animator;
    private AnimatorSet mSetRightOut;
    private AnimatorSet mSetLeftIn;
    private boolean mIsBackVisible;

    public AllWordsAdapter(Context context, List<Word> mData, IAllWordsAdapterListener listener) {
        this.context = context;
        this.mData = mData;
        this.listener = listener;
        this.mDataAll = new ArrayList<>(mData);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.card_item_allwords, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tvWord.setText(mData.get(position).getEnglish());

        holder.rlvWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int distance = 8000;
                float scale = context.getResources().getDisplayMetrics().density * distance;
                holder.rlvWord.setCameraDistance(scale);
                mSetRightOut = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.out_animation);
                mSetLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(context, R.animator.in_animation);
                if (holder.tvWord.getText().toString().equals(mData.get(position).getEnglish())) {
                    mSetRightOut.setTarget(holder.rlvWord);
                    mSetLeftIn.setTarget(holder.rlvWord);
                    mSetRightOut.start();
                    mSetLeftIn.start();
                    holder.tvWord.setText(mData.get(position).getTurkish());
                } else {
                    mSetRightOut.setTarget(holder.rlvWord);
                    mSetLeftIn.setTarget(holder.rlvWord);
                    mSetRightOut.start();
                    mSetLeftIn.start();
                    mIsBackVisible = false;
                    holder.tvWord.setText(mData.get(position).getEnglish());
                }



             //   animator=ObjectAnimator.ofFloat(holder.rlvWord,"rotationY",0f,180f);
            //    animator.setDuration(1000);
          //      animator.start();

            }
        });



    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        //run on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Word> filteredList = new ArrayList<>();
            if (charSequence.toString().isEmpty()) {
                filteredList.addAll(mDataAll);
            } else {
                for (Word word : mDataAll) {
                    if (word.getEnglish().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                        filteredList.add(word);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //run on a ui thread
        @Override
        protected void publishResults(CharSequence constraint, FilterResults filterResults) {
            mData.clear();
            mData.addAll((Collection<? extends Word>) filterResults.values);
            notifyDataSetChanged();


        }
    };

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout rlvWord;
        TextView tvWord;
        ImageView btnVoice,btnAlltoLearn;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rlvWord = itemView.findViewById(R.id.rlvWord);
            tvWord = itemView.findViewById(R.id.tvWord);
            btnVoice=itemView.findViewById(R.id.btnVoice);
            btnAlltoLearn=itemView.findViewById(R.id.btnAddtoLearn);
            btnVoice.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   listener.onItemClick(getAdapterPosition());


                }
            });
            rlvWord.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    listener.onLongItemClick(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
