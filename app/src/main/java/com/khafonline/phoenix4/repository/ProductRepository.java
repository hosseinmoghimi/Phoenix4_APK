package com.khafonline.phoenix4.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.khafonline.phoenix4.core.Core;
import com.khafonline.phoenix4.model.Product;
import com.khafonline.phoenix4.volley.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {
    //region FieldsNames
    public static final String table_name = "Product";
    public static final String product_id = "id";
    public static final String title = "title";
    public static final String category_id = "category_id";
    public static final String thumbnail = "thumbnail";
    public static final String priority = "priority";
    //endregion
    Context context;
    SQLiteDatabase database;
    DatabaseHelper dataBaseHelper;

    public ProductRepository() {
        this.context = MyApplication.getAppContext();
        dataBaseHelper = new DatabaseHelper(context);
    }


    private void open() {
        database = dataBaseHelper.getWritableDatabase();
    }

    private void close() {
        database.close();
    }

    private Product cursor2product(Cursor cursor) {
        Product product = new Product();

        //region Cursors
        if (cursor.getColumnIndex(product_id) >= 0)
            product.setId(cursor.getInt(cursor.getColumnIndex(product_id)));

        if (cursor.getColumnIndex(title) >= 0)
            product.setTitle(cursor.getString(cursor.getColumnIndex(title)));

        if (cursor.getColumnIndex(category_id) >= 0)
            product.setCategory_id(cursor.getInt(cursor.getColumnIndex(category_id)));

        if (cursor.getColumnIndex(thumbnail) >= 0)
            product.setThumbnail(cursor.getString(cursor.getColumnIndex(thumbnail)));

        if (cursor.getColumnIndex(priority) >= 0)
            product.setPriority(cursor.getInt(cursor.getColumnIndex(priority)));


        //endregion


        return product;


    }


    private List<Product> cursor2CategoryList(Cursor cursor) {
        List<Product> products = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            products.add(cursor2product(cursor));
            cursor.moveToNext();
        }
        return products;
    }

    public int _insert(Product product) {
        int a = 0;



//region ContentValues

        ContentValues contentValues = new ContentValues();
        contentValues.put(product_id, product.getId());
        contentValues.put(title, product.getTitle());
        contentValues.put(category_id, product.getCategory_id());
        contentValues.put(thumbnail, product.getThumbnail());
        contentValues.put(priority, product.getPriority());


//endregion


//endregion
        int id1 = (int) database.insert(table_name, null, contentValues);

        return id1;
    }


    public List<Integer> _insertList(List<Product> categories) {
        List<Integer> listID = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++)
            listID.add(_insert(categories.get(i)));
        Core.addLog(String.valueOf(categories.size()) + " Category(s) added.");
        return listID;
    }

    public void _update(Product product) {

        int a = 0;

        //region ContentValues

        ContentValues contentValues = new ContentValues();
        contentValues.put(product_id, product.getId());
        contentValues.put(title, product.getTitle());
        contentValues.put(category_id, product.getCategory_id());
        contentValues.put(thumbnail, product.getThumbnail());
        contentValues.put(priority, product.getPriority());

        //endregion

        String where = "( " + product_id + " = ? )";

        database.update(table_name, contentValues, where, new String[]{String.valueOf(product.getId())});

    }


    public void _delete(int id) {
        String where = "( " + id + " = ? )";
        database.delete(table_name, where, new String[]{String.valueOf(id)});
    }


    public void _deleteAll() {

        database.delete(table_name, null, null);
    }

    public List<Product> _selectAll() {

        String order = "";
        order = " ORDER BY " + priority;
        Cursor cursor = database.rawQuery("SELECT * FROM " + table_name + order, null);
        List<Product> products = cursor2CategoryList(cursor);
        cursor.close();
        return products;
    }


    @Nullable
    private Product _select(int productID) {
        String Where = " WHERE ( " + product_id + " = " + String.valueOf(productID) + " )";
        Cursor cursor = database.rawQuery("SELECT * FROM " + table_name + Where, null);
        List<Product> products = cursor2CategoryList(cursor);
        cursor.close();
        if (products.size() == 1)
            return products.get(0);
        return null;
    }




    @Nullable
    private List<Product> _search(String searchText) {


        String order = "";
        order = " ORDER BY " + product_id;


        String Where = " WHERE ((" + product_id + "  >0)" +
                " OR (" + title + " LIKE '%" + String.valueOf(searchText) + "%')" +
                " OR (" + thumbnail + " LIKE '%" + String.valueOf(searchText) + "%')" +

                ")";


        String command = "SELECT * FROM " + table_name + Where + order;
        Cursor cursor = database.rawQuery(command, null);

        List<Product> products = cursor2CategoryList(cursor);
        cursor.close();
        return products;


    }

    @Nullable
    private List<Product> _selectByCategoryId(int category_idd) {


        String order = "";
        order = " ORDER BY " + priority;


        String Where = " WHERE (" + category_id + "  =" + String.valueOf(category_idd) + ")";


        String command = "SELECT * FROM " + table_name + Where + order;
        Cursor cursor = database.rawQuery(command, null);

        List<Product> products = cursor2CategoryList(cursor);
        cursor.close();
        return products;


    }


    public int _getMaxID() {
        int max = 0;
        try {
            Cursor cursor = database.rawQuery("SELECT MAX(" + product_id + ") FROM " + table_name, null);
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                max = cursor.getInt(0);
                cursor.moveToNext();
            }
            cursor.close();
        } catch (Exception ee) {

        }
        return max;
    }


    public void _refreshList(List<Product> products) {
        if (products == null) return;
        for (int i = 0; i < products.size(); i++) {
            _refresh(products.get(i));
        }

    }


    public void _refresh(Product product) {
        _delete(product.getId());


//region ContentValues

        ContentValues contentValues = new ContentValues();
        contentValues.put(product_id, product.getId());
        contentValues.put(title, product.getTitle());
        contentValues.put(category_id, product.getCategory_id());
        contentValues.put(thumbnail, product.getThumbnail());
        contentValues.put(priority, product.getPriority());


        //endregion
        database.insert(table_name, null, contentValues);
    }


    public static int insert(Product productCategory) {
        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        int id = productCategoryRepository._insert(productCategory);
        productCategoryRepository.close();
        return id;
    }


    public static List<Integer> insertList(List<Product> productCategory0s) {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        List<Integer> listID = productCategoryRepository._insertList(productCategory0s);
        productCategoryRepository.close();
        return listID;
    }


    public static void update(Product product) {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        productCategoryRepository._update(product);
        productCategoryRepository.close();
    }


    public static void delete(int id) {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        productCategoryRepository._delete(id);
        productCategoryRepository.close();
    }

    public static List<Product> selectAll() {


        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        List<Product> productCategorys = productCategoryRepository._selectAll();
        productCategoryRepository.close();
        return productCategorys;
    }

    public static void refreshList(List<Product> products) {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        productCategoryRepository._refreshList(products);
        productCategoryRepository.close();
        Core.addLog("CategoryRepository: " + String.valueOf(products.size()) + " item(s) refreshed.");
    }

    public static void refresh(Product product) {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        productCategoryRepository._refresh(product);
        productCategoryRepository.close();
        Core.addLog("CategoryRepository : 1 item (CategoryID=" + String.valueOf(product.getId()) + ") refreshed.");
    }


    public static List<Product> search(String searchText) {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        List<Product> productCategory0s = productCategoryRepository._search(searchText);
        productCategoryRepository.close();
        return productCategory0s;
    }


    public static void deleteAll() {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        productCategoryRepository._deleteAll();
        productCategoryRepository.close();

    }

    public static int getMaxID() {

        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        int max = productCategoryRepository._getMaxID();
        productCategoryRepository.close();
        return max;
    }

    @Nullable
    public static Product select(int productID) {
        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        Product product = productCategoryRepository._select(productID);
        productCategoryRepository.close();
        return product;
    }

    @Nullable
    public static List<Product> selectByCategoryId(int categoryId) {
        com.khafonline.phoenix4.repository.ProductRepository productCategoryRepository = new com.khafonline.phoenix4.repository.ProductRepository();
        productCategoryRepository.open();
        List<Product> products = productCategoryRepository._selectByCategoryId(categoryId);
        productCategoryRepository.close();
        return products;
    }
    //endregion




}
