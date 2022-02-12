package com.example.market.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SaleDatabaseHalper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHalper";

    private static final String DATABASE_NAME = "sale_product.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "sale";

    public static final String COLUMN_ID = "sale_id";
    public static final String COLUMN_BARCODE = "sale_barcode";
    public static final String COLUMN_PRODUCT = "sale_name";
    public static final String COLUMN_CATEGORY = "sale_catecory";
    public static final String COLUMN_PRICE = "sale_price";
    public static final String COLUMN_NUMBER = "sale_number";

    private Context context;

    private static final String TABLE_SALE_CREATE =
            "CREATE TABLE " +
                    TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_BARCODE + " TEXT, " +
                    COLUMN_PRODUCT + " TEXT, " +
                    COLUMN_CATEGORY + " TEXT, " +
                    COLUMN_PRICE + " FLOAT," +
                    COLUMN_NUMBER + " INTEGER" + ")";


    public SaleDatabaseHalper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_SALE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }



    public void addSale(String product, String barcode, String category, Float price, int number){
        final SQLiteDatabase db = this.getWritableDatabase();
        final ContentValues cv = new ContentValues();

        cv.put(COLUMN_PRODUCT, product.trim());
        cv.put(COLUMN_BARCODE, barcode.trim());
        cv.put(COLUMN_CATEGORY, category.trim());
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_NUMBER, number);

        db.insert(TABLE_NAME, null, cv);

    }

    public void deleteSale(String barcode){
        final SQLiteDatabase database = this.getWritableDatabase();

        String whereClause = COLUMN_BARCODE + " =?";
        String[] whereArgs = new String[]{barcode};
        database.delete(TABLE_NAME, whereClause, whereArgs);
        database.close();
    }


    public List<MSale> searchForSale(String searchTerm){
        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor cursor = db.query(
                TABLE_NAME,
                //new String[]{COLUMN_BARCODE, COLUMN_CATEGORY, COLUMN_PRODUCT, COLUMN_PRICE, COLUMN_NUMBER},
                null,
                COLUMN_BARCODE + " LIKE ?",
                new String[]{"%" + searchTerm + "%"},
                null,
                null,
                null
        );
        final int idIndex = cursor.getColumnIndex(COLUMN_ID);
        final int barocdeIndex = cursor.getColumnIndex(COLUMN_BARCODE);
        final int productIndex = cursor.getColumnIndex(COLUMN_PRODUCT);
        final int categorytIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        final int priceIndex = cursor.getColumnIndex(COLUMN_PRICE);
        final int numberIndex = cursor.getColumnIndex(COLUMN_NUMBER);

        try {
            if (!cursor.moveToFirst()){
                return new ArrayList<>();
            }

            final List<MSale> mProduct = new ArrayList<>();

            do {
                final int id = cursor.getInt(idIndex);
                final String barcode = cursor.getString(barocdeIndex);
                final String productName = cursor.getString(productIndex);
                final String catecory = cursor.getString(categorytIndex);
                final float  price = cursor.getFloat(priceIndex);
                final int number = cursor.getInt(numberIndex);
                mProduct.add(new MSale(id, barcode, productName, catecory, price,number));
            } while (cursor.moveToNext());

            return mProduct;
        }finally {
            cursor.close();
            db.close();
        }
    }

    public List<MSale> allList(){
        final SQLiteDatabase db = this.getReadableDatabase();
        final Cursor cursor = db.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        final int idIndex = cursor.getColumnIndex(COLUMN_ID);
        final int barocdeIndex = cursor.getColumnIndex(COLUMN_BARCODE);
        final int productIndex = cursor.getColumnIndex(COLUMN_PRODUCT);
        final int categorytIndex = cursor.getColumnIndex(COLUMN_CATEGORY);
        final int priceIndex = cursor.getColumnIndex(COLUMN_PRICE);
        final int numberIndex = cursor.getColumnIndex(COLUMN_NUMBER);

        try {
            if (!cursor.moveToFirst()){
                return new ArrayList<>();
            }

            final List<MSale> aProduct = new ArrayList<>();

            do {
                final int id = cursor.getInt(idIndex);
                final String barcode = cursor.getString(barocdeIndex);
                final String productName = cursor.getString(productIndex);
                final String catecory = cursor.getString(categorytIndex);
                final float  price = cursor.getFloat(priceIndex);
                final int number = cursor.getInt(numberIndex);
                aProduct.add(new MSale(id, barcode, productName, catecory, price,number));
            } while (cursor.moveToNext());

            return aProduct;
        }finally {
            cursor.close();
        }


    }

    public void saleUpdate(String product, String barcode, String category, float price, int number){
        final SQLiteDatabase database = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT, product.trim());
        cv.put(COLUMN_BARCODE, barcode.trim());
        cv.put(COLUMN_CATEGORY, category.trim());
        cv.put(COLUMN_PRICE, price);
        cv.put(COLUMN_NUMBER, number);

        database.update(TABLE_NAME,cv,COLUMN_BARCODE + " =?", new String[]{barcode});
        database.close();
    }


}
