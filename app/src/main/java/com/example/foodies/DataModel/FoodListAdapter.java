package com.example.foodies.DataModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodies.R;

import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<FoodListAdapter.FoodViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context mContext;
    private List<Food> mFood;



    public FoodListAdapter(Context context)
    {
        layoutInflater = LayoutInflater.from(context);
        mContext=context;
    }

    @NonNull
    @Override
    public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = layoutInflater.inflate(R.layout.row,parent,false);
        FoodViewHolder viewHolder = new FoodViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodViewHolder holder, int position) {
    if (mFood != null){
        Food food = mFood.get(position);
        holder.setData(food.getMealName(),food.getTime(),food.getType(),food.getOccasion(), food.getPrice(), food.getImage(), position);
     }
        else {
        holder.foodItemViewMeal.setText(R.string.no);
        }
    }



    @Override
    public int getItemCount() {
        if (mFood != null)
        {
            return mFood.size();
        }
        else return 0;

    }
    public void setNotes (List<Food> foods)
    {
        mFood = foods;
        notifyDataSetChanged();
    }

    public class FoodViewHolder extends RecyclerView.ViewHolder {

        private TextView foodItemViewMeal ;
        private TextView foodItemViewTime;
        private TextView foodItemViewType;
        private TextView foodItemViewOcassion;
        private TextView foodItemViewPrice;
        private ImageView imageView;

        private int mPosition;
        public FoodViewHolder(@NonNull View itemView) {
            super(itemView);

            foodItemViewMeal=itemView.findViewById(R.id.meal);
            foodItemViewTime=itemView.findViewById(R.id.time);
            foodItemViewType=itemView.findViewById(R.id.type);
            foodItemViewOcassion=itemView.findViewById(R.id.ocassion);
            foodItemViewPrice=itemView.findViewById(R.id.money);
            imageView = itemView.findViewById(R.id.image);

        }

        public void setData(String meal,String time, String type,String ocassion,String price ,byte[] image, int position) {
            foodItemViewMeal.setText(meal);
            foodItemViewTime.setText(time);
            foodItemViewType.setText(type);
            foodItemViewOcassion.setText(ocassion);
            foodItemViewPrice.setText(price);
            imageView.setImageBitmap(DataConverter.convertByteArray2Bitmap(image));
            mPosition = position;
        }
    }
}
