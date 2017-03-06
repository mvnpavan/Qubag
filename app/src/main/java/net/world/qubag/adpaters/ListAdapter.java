package net.world.qubag.adpaters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.world.qubag.R;
import net.world.qubag.callbacks.CallbackUtil;
import net.world.qubag.models.Item;

import java.util.List;

/**
 * Created by mvnpavan on 05/03/17.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListItemViewHolder> {


    private List<Item> items;
    private CallbackUtil callbackUtil;
    private Context context;

    public ListAdapter(Context context,List<Item> items,CallbackUtil callbackUtil) {
        this.context = context;
        this.items = items;
        this.callbackUtil = callbackUtil;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_list_item, null);
        ListItemViewHolder rcv = new ListItemViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
        holder.itemName.setText(items.get(position).getName());
        String price = items.get(position).getPrice();
        holder.itemPrice.setText(Html.fromHtml("Price: <b>"+context.getResources().getString(R.string.Rs)+price+"</b>"));
    }

    @Override
    public int getItemCount() {
        return items!=null?items.size():0;
    }

    public final class ListItemViewHolder extends RecyclerView.ViewHolder {

        TextView itemName;
        TextView itemPrice;
        ImageView itemChooser;

        public ListItemViewHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.item_name);
            itemPrice = (TextView) itemView.findViewById(R.id.item_prise);
            itemChooser = (ImageView) itemView.findViewById(R.id.item_add_cart);
            itemChooser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callbackUtil.addItemToCart(items.get(getAdapterPosition()));
                }
            });
        }
    }

    public void resetData(List<Item> data){
        items = data;
        notifyDataSetChanged();
    }
}
