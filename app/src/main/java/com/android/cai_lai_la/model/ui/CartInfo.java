package com.android.cai_lai_la.model.ui;

import com.android.cai_lai_la.model.Product;

public class CartInfo extends Product {
    private int Num = 1;

    public int getNum(){
        return Num;
    }

    public void setNum(int num){
        this.Num = num;
    }
}

