package com.safia.topquiz.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.safia.topquiz.Model.User;
import com.safia.topquiz.R;

import java.util.List;

public class RankingAdapter extends RecyclerView.Adapter <RankingAdapter.MyViewHolder>  {

    private List<User> mUserList;
    private final int maxPlayerView = 5;

    public RankingAdapter(List<User> userList) {
        this.mUserList = userList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.activity_item_list, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.updateWithListUser(this.mUserList.get(position));

    }

    @Override
    public int getItemCount() {
        if (mUserList.size() > maxPlayerView){
            return maxPlayerView;
        }else{
            return this.mUserList.size();}
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mName;
        public TextView mScore;

        public MyViewHolder(View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.player_name);
            mScore = itemView.findViewById(R.id.player_score);
        }

        public void updateWithListUser(User user){
            this.mName.setText(user.getFirstName());
            this.mScore.setText(Integer.toString(user.getScore()));
        }
    }
}
