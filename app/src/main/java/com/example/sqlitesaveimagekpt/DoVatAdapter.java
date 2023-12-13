package com.example.sqlitesaveimagekpt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DoVatAdapter extends BaseAdapter {
    private Context context;
    public int layout;
    private List<DoVat> doVatList;

    public DoVatAdapter(Context context, int layout, List<DoVat> doVatList) {
        this.context = context;
        this.layout = layout;
        this.doVatList = doVatList;
    }

    @Override
    public int getCount() {
        return doVatList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    private class ViewHolder{
        TextView txtTen, txtMota;
        ImageView imgHinh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            // Anh xa
            holder.txtTen  = (TextView) view.findViewById(R.id.textViewTenCustom);
            holder.txtMota = (TextView) view.findViewById(R.id.textViewMotaCustom);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imageHinhCustom);
            view.setTag(holder);
        }else {
          holder = (ViewHolder) view.getTag();
        }

        DoVat doVat = doVatList.get(i);
        holder.txtTen.setText(doVat.getTen());
        holder.txtMota.setText(doVat.getMota());

        // chuyển byte[] -> bitmap
        byte[] hinhAnh = doVat.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imgHinh.setImageBitmap(bitmap);
        return view;
    }
}
