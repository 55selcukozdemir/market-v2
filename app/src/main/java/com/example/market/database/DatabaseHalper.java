package com.example.market.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHalper extends SQLiteOpenHelper
{
    private static final String TAG = "DatabaseHalper";

    private static final String DATABASE_NAME = "my_products.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "product";

    public static final String COLUMN_ID = "product_id";
    public static final String COLUMN_BARCODE = "product_barcode";
    public static final String COLUMN_PRODUCT = "product";
    public static final String COLUMN_CATEGORY = "catecory";
    public static final String COLUMN_PRICE = "product_price";

    private Context context;

    private static final String TABLE_PRODUCT_CREATE =
            "CREATE TABLE " +
            TABLE_NAME + " (" +
            COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_BARCODE + " TEXT, " +
            COLUMN_PRODUCT + " TEXT, " +
            COLUMN_CATEGORY + " TEXT, " +
            COLUMN_PRICE + " FLOAT" + ")";


    public DatabaseHalper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLE_PRODUCT_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void addProduct(String product, String barcode, String category, Float price){
        final SQLiteDatabase db = this.getWritableDatabase();
        final ContentValues cv = new ContentValues();
        cv.put(COLUMN_PRODUCT, product.trim());
        cv.put(COLUMN_BARCODE, barcode.trim());
        cv.put(COLUMN_CATEGORY, category.trim());
        cv.put(COLUMN_PRICE, price);
        Log.d(TAG, "addProduct:" + product +" " + barcode + " " + price);
        Log.d(TAG, "addProduct: "+ cv.toString());
        db.insert(TABLE_NAME, null, cv);

    }

    public void deleteProduct(String barcode){
        final SQLiteDatabase database = this.getWritableDatabase();

        String whereClause = COLUMN_BARCODE + " =?";
        String[] whereArgs = new String[]{barcode};
        database.delete(TABLE_NAME, whereClause, whereArgs);
        database.close();

        Toast.makeText(context, "işlem başarılı", Toast.LENGTH_LONG).show();
    }

    public List<MProduct> searchForProduct(String searchTerm){
        final SQLiteDatabase db = this.getWritableDatabase();
        final Cursor cursor = db.query(
                TABLE_NAME,
                new String[]{COLUMN_BARCODE, COLUMN_CATEGORY, COLUMN_PRODUCT, COLUMN_PRICE},
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

        try {
            if (!cursor.moveToFirst()){
                return new ArrayList<>();
            }

            final List<MProduct> mProduct = new ArrayList<>();

            do {
                final int id = cursor.getInt(idIndex);
                final String barcode = cursor.getString(barocdeIndex);
                final String productName = cursor.getString(productIndex);
                final String catecory = cursor.getString(categorytIndex);
                final float  price = cursor.getFloat(priceIndex);
                mProduct.add(new MProduct(id, barcode, productName, catecory, price));
            } while (cursor.moveToNext());

            return mProduct;
        }finally {
            cursor.close();
            db.close();
        }
    }

    public List<MProduct> allList(){
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

        try {
            if (!cursor.moveToFirst()){
                return new ArrayList<>();
            }

            final List<MProduct> aProduct = new ArrayList<>();

            do {
                final int id = cursor.getInt(idIndex);
                final String barcode = cursor.getString(barocdeIndex);
                final String productName = cursor.getString(productIndex);
                final String catecory = cursor.getString(categorytIndex);
                final float  price = cursor.getFloat(priceIndex);
                aProduct.add(new MProduct(id, barcode, productName, catecory, price));
            } while (cursor.moveToNext());

            return aProduct;
        }finally {
            cursor.close();
        }


    }
}
