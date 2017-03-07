package adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vukhachoi.weddingmanagement.DetailWeddingActivity;
import com.example.vukhachoi.weddingmanagement.R;

import java.util.List;

import hall.wedding.management.NameHall;

/**
 * Created by Vu Khac Hoi on 3/3/2017.
 */

public class RecyclerNameHallAdapter extends RecyclerView.Adapter<RecyclerNameHallAdapter.DataViewHolde> {

    public List<NameHall> hallName;
    public Context context;

    public RecyclerNameHallAdapter(List<NameHall> hallDetails, Context context) {
        this.hallName = hallDetails;
        this.context = context;
    }



    @Override
    public RecyclerNameHallAdapter.DataViewHolde onCreateViewHolder(ViewGroup parent, int viewType) {
        View Itemview;
        switch (viewType)
        {
            case 1:
                Itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_hall,parent,false);
                break;
            case 2:
                Itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_hall,parent,false);
                break;
            default:
                Itemview = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_name_hall,parent,false);
                break;
        }
        Itemview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return  new RecyclerNameHallAdapter.DataViewHolde(Itemview);
    }



    @Override
    public void onBindViewHolder(RecyclerNameHallAdapter.DataViewHolde holder, int position) {
        holder.txtbantoida.setText(hallName.get(position).getBanToiDa()+"");
        holder.txtgiatoithieu.setText(hallName.get(position).getGiaToiThieu()+"");
        holder.txtNamehall.setText(hallName.get(position).getNamehall()+"");
       holder.imgActive.setImageResource(hallName.get(position).getImgActive());
    }

    @Override
    public int getItemCount() {
        return hallName==null ? 0:hallName.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(hallName.get(position).isActive())
            return 1;
        else return 2;
    }

    public  class DataViewHolde extends RecyclerView.ViewHolder {
        TextView txtbantoida,txtgiatoithieu,txtNamehall;
ImageView imgActive;
        public DataViewHolde(View itemView) {
            super(itemView);
            txtbantoida= (TextView) itemView.findViewById(R.id.txtbantoida);
            txtgiatoithieu= (TextView) itemView.findViewById(R.id.txtgiatoithieu);
            txtNamehall= (TextView) itemView.findViewById(R.id.txtNameHall);
            imgActive= (ImageView) itemView.findViewById(R.id.imgActive);



            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int Position=getAdapterPosition();
                    if(Position!=RecyclerView.NO_POSITION) {

                        Intent intent=new Intent(context, DetailWeddingActivity.class);
intent.putExtra("Tensanh",txtNamehall.getText().toString());

                        v.getContext().startActivity(intent);
                    }
                }
            });
        }
    }
}
