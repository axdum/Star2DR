package mob.star2dr.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mob.star2dr.R;
import mob.star2dr.model.BusRoutes;

/**
 * Created by User_1 on 06/01/2018.
 */

public class LigneAdaptater extends ArrayAdapter<BusRoutes> {
    private final Context context;
    private final ArrayList<BusRoutes> data;
    private final int layoutResourceId;
    private final String c = "#000000";

    public LigneAdaptater(@NonNull Context context, @LayoutRes int layoutResourceId, @NonNull ArrayList<BusRoutes> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        final ViewHolder holder;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ViewHolder();
            holder.shortName = (TextView)row.findViewById(R.id.shortNameTxt);
            holder.Dir1 = (TextView)row.findViewById(R.id.firstDirTxt);
            holder.Dir2 = (TextView)row.findViewById(R.id.secondDirTxt);

            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)row.getTag();
        }

        BusRoutes ligne = data.get(position);

        holder.shortName.setText(ligne.getShort_name());
        String Dirs = ligne.getLong_name();
        String[] splitDirs = Dirs.split("<>");
        if(splitDirs.length == 2){
            holder.Dir1.setText(splitDirs[0]);
            holder.Dir2.setText(splitDirs[1].substring(1));
        }else if(splitDirs.length == 3){
            holder.Dir1.setText(splitDirs[0]);
            holder.Dir2.setText(splitDirs[2].substring(1));
        }
        holder.Dir1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   holder.Dir1.setTextColor(Color.parseColor("#2196F3"));
            }
        });
        holder.Dir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   holder.Dir2.setTextColor(Color.parseColor("#2196F3"));
            }
        });

        // color
        holder.shortName.setBackgroundColor(Color.parseColor("#" + ligne.getColor()));
        holder.shortName.setTextColor(Color.parseColor("#" + ligne.getText_color()));
        return row;
    }

    static class ViewHolder
    {
        TextView shortName;
        TextView Dir1;
        TextView Dir2;
    }
}

