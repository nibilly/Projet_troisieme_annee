package com.example.jean_michel.projet_troisieme_annee.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.jean_michel.projet_troisieme_annee.SQLiteHelper.UserHelper;
import com.example.jean_michel.projet_troisieme_annee.donnee.User;

import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private SQLiteDatabase database;
    private UserHelper dbHelper;
    private String[] allColumns = { UserHelper.COLUMN_ID,
            UserHelper.COLUMN_FIRST_NAME, UserHelper.COLUMN_LAST_NAME };

    public UserDAO(Context context) {
        dbHelper = new UserHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public User createUser(User user) {
        ContentValues values = new ContentValues();
        values.put(UserHelper.COLUMN_FIRST_NAME, user.getFirstName());
        values.put(UserHelper.COLUMN_LAST_NAME, user.getLastName());
        long insertId = database.insert(UserHelper.TABLE_USER, null,
                values);
        user.setId((int) insertId);
        return user;
    }

    public void deleteUser(User user) {
        long id = user.getId();
        Log.d(UserDAO.class.getName(), "User deleted with id: " + id);
        database.delete(UserHelper.TABLE_USER, UserHelper.COLUMN_ID
                + " = " + id, null);
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<User>();
        Cursor cursor = database.query(UserHelper.TABLE_USER,
                allColumns, null, null, null, null, null);
        if(cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                User user = CursorToUser(cursor);
                users.add(user);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return users;
    }

    public List<User> findUserById(int id){
        List<User> users = new ArrayList<>();
        Cursor cursor = database.query(UserHelper.TABLE_USER,
                allColumns, UserHelper.COLUMN_ID + " = " + id,
                null, null, null, null);
        cursor.moveToFirst();
        User user = CursorToUser(cursor);
        users.add(user);
        cursor.close();
        return users;
    }

    private User CursorToUser(Cursor cursor){
        return new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
    }
}
