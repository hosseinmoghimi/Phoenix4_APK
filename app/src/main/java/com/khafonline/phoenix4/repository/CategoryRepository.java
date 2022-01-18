package com.khafonline.phoenix4.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.khafonline.phoenix4.core.Core;
import com.khafonline.phoenix4.model.Category;
import com.khafonline.phoenix4.volley.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepository {
    //region FieldsNames
    public static final String table_name = "Category";
    public static final String category_id = "id";
    public static final String title = "title";
    public static final String parent_id = "Parent_id";
    public static final String thumbnail = "thumbnail";
    public static final String priority = "priority";
    //endregion
    Context context;
    SQLiteDatabase database;
    DatabaseHelper dataBaseHelper;

    public CategoryRepository() {
        this.context = MyApplication.getAppContext();
        dataBaseHelper = new DatabaseHelper(context);
    }


    private void open() {
        database = dataBaseHelper.getWritableDatabase();
    }

    private void close() {
        database.close();
    }

    private Category cursor2category(Cursor cursor) {
        Category category = new Category();

        //region Cursors
        if (cursor.getColumnIndex(category_id) >= 0)
            category.setId(cursor.getInt(cursor.getColumnIndex(category_id)));

        if (cursor.getColumnIndex(title) >= 0)
            category.setTitle(cursor.getString(cursor.getColumnIndex(title)));

        if (cursor.getColumnIndex(parent_id) >= 0)
            category.setParent_id(cursor.getInt(cursor.getColumnIndex(parent_id)));

        if (cursor.getColumnIndex(thumbnail) >= 0)
            category.setThumbnail(cursor.getString(cursor.getColumnIndex(thumbnail)));

        if (cursor.getColumnIndex(priority) >= 0)
            category.setPriority(cursor.getInt(cursor.getColumnIndex(priority)));


        //endregion


        return category;


    }


    private List<Category> cursor2CategoryList(Cursor cursor) {
        List<Category> categories = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(cursor2category(cursor));
            cursor.moveToNext();
        }
        return categories;
    }

    public int _insert(Category category) {

        this._delete(category.getId());
        int a = 0;
        try {
            if (category.getParent_id() > 0)
                a = category.getParent_id();
        } catch (Exception eee) {
            category.setParent_id(0);
        }


//region ContentValues

        ContentValues contentValues = new ContentValues();
        contentValues.put(category_id, category.getId());
        contentValues.put(title, category.getTitle());
        contentValues.put(parent_id, category.getParent_id());
        contentValues.put(thumbnail, category.getThumbnail());
        contentValues.put(priority, category.getPriority());


//endregion


//endregion
        int id1 = (int) database.insert(table_name, null, contentValues);

        return id1;
    }


    public List<Integer> _insertList(List<Category> categories) {
        List<Integer> listID = new ArrayList<>();
        for (int i = 0; i < categories.size(); i++)
            listID.add(_insert(categories.get(i)));
        Core.addLog(String.valueOf(categories.size()) + " Category(s) added.");
        return listID;
    }

    public void _update(Category category) {

        int a = 0;
        try {
            if (category.getParent_id() > 0)
                a = category.getParent_id();
        } catch (Exception eee) {
            category.setParent_id(0);
        }


        //region ContentValues

        ContentValues contentValues = new ContentValues();
        contentValues.put(category_id, category.getId());
        contentValues.put(title, category.getTitle());
        contentValues.put(parent_id, category.getParent_id());
        contentValues.put(thumbnail, category.getThumbnail());
        contentValues.put(priority, category.getPriority());

        //endregion

        String where = "( " + category_id + " = ? )";

        database.update(table_name, contentValues, where, new String[]{String.valueOf(category.getId())});

    }


    public void _delete(int id) {
        String where = "( " + id + " = ? )";
        database.delete(table_name, where, new String[]{String.valueOf(id)});
    }


    public void _deleteAll() {

        database.delete(table_name, null, null);
    }

    public List<Category> _selectAll() {

        String order = "";
        order = " ORDER BY " + priority;
        Cursor cursor = database.rawQuery("SELECT * FROM " + table_name + order, null);
        List<Category> categories = cursor2CategoryList(cursor);
        cursor.close();
        return categories;
    }


    @Nullable
    private Category _select(int categoryID) {
        String Where = " WHERE ( " + category_id + " = " + String.valueOf(categoryID) + " )";
        Cursor cursor = database.rawQuery("SELECT * FROM " + table_name + Where, null);
        List<Category> categories = cursor2CategoryList(cursor);
        cursor.close();
        if (categories.size() == 1)
            return categories.get(0);
        return null;
    }


    @Nullable
    private List<Category> _selectRoots() {
        String order = "";
        order = " ORDER BY " + category_id;


        String Where = " WHERE (" + parent_id + "  = 0)";


        String command = "SELECT * FROM " + table_name + Where + order;
        Cursor cursor = database.rawQuery(command, null);

        List<Category> categories = cursor2CategoryList(cursor);
        cursor.close();
        return categories;
    }

    @Nullable
    private List<Category> _search(String searchText) {


        String order = "";
        order = " ORDER BY " + category_id;


        String Where = " WHERE ((" + category_id + "  >0)" +
                " OR (" + title + " LIKE '%" + String.valueOf(searchText) + "%')" +
                " OR (" + thumbnail + " LIKE '%" + String.valueOf(searchText) + "%')" +

                ")";


        String command = "SELECT * FROM " + table_name + Where + order;
        Cursor cursor = database.rawQuery(command, null);

        List<Category> categories = cursor2CategoryList(cursor);
        cursor.close();
        return categories;


    }

    @Nullable
    private List<Category> _selectByParentID(int parentID) {


        String order = "";
        order = " ORDER BY " + priority;


        String Where = " WHERE (" + parent_id + "  =" + String.valueOf(parentID) + ")";


        String command = "SELECT * FROM " + table_name + Where + order;
        Cursor cursor = database.rawQuery(command, null);

        List<Category> categories = cursor2CategoryList(cursor);
        cursor.close();
        return categories;


    }


    public int _getMaxID() {
        int max = 0;
        try {
            Cursor cursor = database.rawQuery("SELECT MAX(" + category_id + ") FROM " + table_name, null);
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


    public void _refreshList(List<Category> categories) {
        if (categories == null) return;
        for (int i = 0; i < categories.size(); i++) {
            _refresh(categories.get(i));
        }

    }


    public void _refresh(Category category) {
        _delete(category.getId());


//region ContentValues

        ContentValues contentValues = new ContentValues();
        contentValues.put(category_id, category.getId());
        contentValues.put(title, category.getTitle());
        contentValues.put(parent_id, category.getParent_id());
        contentValues.put(thumbnail, category.getThumbnail());
        contentValues.put(priority, category.getPriority());


        //endregion
        database.insert(table_name, null, contentValues);
    }


    public static int insert(Category category) {
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        int id = categoryRepository._insert(category);
        categoryRepository.close();
        return id;
    }


    public static List<Integer> insertList(List<Category> category0s) {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        List<Integer> listID = categoryRepository._insertList(category0s);
        categoryRepository.close();
        return listID;
    }


    public static void update(Category category) {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        categoryRepository._update(category);
        categoryRepository.close();
    }


    public static void delete(int id) {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        categoryRepository._delete(id);
        categoryRepository.close();
    }

    public static List<Category> selectAll() {


        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        List<Category> categories = categoryRepository._selectAll();
        categoryRepository.close();
        return categories;
    }

    public static void refreshList(List<Category> categories) {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        categoryRepository._refreshList(categories);
        categoryRepository.close();
        Core.addLog("CategoryRepository: " + String.valueOf(categories.size()) + " item(s) refreshed.");
    }

    public static void refresh(Category category) {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        categoryRepository._refresh(category);
        categoryRepository.close();
        Core.addLog("CategoryRepository : 1 item (CategoryID=" + String.valueOf(category.getId()) + ") refreshed.");
    }


    public static List<Category> search(String searchText) {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        List<Category> category0s = categoryRepository._search(searchText);
        categoryRepository.close();
        return category0s;
    }


    public static void deleteAll() {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        categoryRepository._deleteAll();
        categoryRepository.close();

    }

    public static int getMaxID() {

        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        int max = categoryRepository._getMaxID();
        categoryRepository.close();
        return max;
    }

    @Nullable
    public static Category select(int categoryID) {
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        Category category = categoryRepository._select(categoryID);
        categoryRepository.close();
        return category;
    }

    @Nullable
    public static List<Category> selectByParentID(int parentID) {
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        List<Category> categories = categoryRepository._selectByParentID(parentID);
        categoryRepository.close();
        return categories;
    }
    //endregion

    public static List<Category> selectRoots() {
        CategoryRepository categoryRepository = new CategoryRepository();
        categoryRepository.open();
        List<Category> categories = categoryRepository._selectRoots();
        categoryRepository.close();
        return categories;
    }


}
