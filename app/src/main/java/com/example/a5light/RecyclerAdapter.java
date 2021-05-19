package com.example.a5light;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.a5light.constants.Constants;
import com.example.a5light.data.Detect_Data;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    Context context;
    private List<Detect_Data> dataList;
    private Intent intent;


    public RecyclerAdapter(List<Detect_Data> dataList) {
        this.dataList = dataList;
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_list, parent, false);
        RecyclerAdapter.ViewHolder vh = new RecyclerAdapter.ViewHolder(view);
        return vh;
    }


    // onBindViewHolder : Adapter class
    // position에 해당하는 데이터를 표시. 실제화면에 데이터와 레이아웃을 연결
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Detect_Data item = dataList.get(position);

        Glide.with(holder.detect_thumbnail.getContext())
                .load(Constants.BASE_URL + item.getThumbnail())
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.detect_thumbnail);
        holder.detect_date.setText(item.getDateTime());
        holder.detect_name.setText(item.getObject());


    }


    //  전체 데이터의 개수를 리턴
    @Override
    public int getItemCount() {
        return dataList.size();
    }


    //  아이템 뷰를 저장하는 뷰홀더 클래스
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView detect_name;
        TextView detect_date;
        ImageView detect_thumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //뷰 객체에 대한 참조.
            detect_date = itemView.findViewById(R.id.detect_date);
            detect_name = itemView.findViewById(R.id.detect_name);
            detect_thumbnail = itemView.findViewById(R.id.detect_thumbnail);

            itemView.setClickable(true);
            itemView.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    intent = new Intent(view.getContext(), Detail.class);
                    intent.putExtra("number", position);
                    intent.putExtra("url", dataList.get(position).getVideo());
                    intent.putExtra("name", dataList.get(position).getObject());
                    intent.putExtra("date", dataList.get(position).getObject());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }


}
