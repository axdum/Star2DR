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

    public LigneAdaptater(@NonNull Context context, @LayoutRes int layoutResourceId, @NonNull ArrayList<BusRoutes> data) {
        super(context, layoutResourceId, data);
        this.context = context;
        this.data = data;
        this.layoutResourceId = layoutResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder = null;

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
        holder.Dir1.setText("test dir 1");
        holder.Dir2.setText("test dir 2");

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

