package com.hotelops.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.hotelops.data.local.entity.HotelEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class HotelDao_Impl implements HotelDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<HotelEntity> __insertionAdapterOfHotelEntity;

  private final EntityDeletionOrUpdateAdapter<HotelEntity> __deletionAdapterOfHotelEntity;

  private final EntityDeletionOrUpdateAdapter<HotelEntity> __updateAdapterOfHotelEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteHotelById;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  public HotelDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfHotelEntity = new EntityInsertionAdapter<HotelEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `hotels` (`id`,`name`,`address`,`city`,`country`,`phone`,`email`,`createdAt`,`syncedAt`,`isDirty`) VALUES (?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HotelEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCity());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCountry());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPhone());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getEmail());
        }
        statement.bindLong(8, entity.getCreatedAt());
        if (entity.getSyncedAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getSyncedAt());
        }
        final int _tmp = entity.isDirty() ? 1 : 0;
        statement.bindLong(10, _tmp);
      }
    };
    this.__deletionAdapterOfHotelEntity = new EntityDeletionOrUpdateAdapter<HotelEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `hotels` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HotelEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfHotelEntity = new EntityDeletionOrUpdateAdapter<HotelEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `hotels` SET `id` = ?,`name` = ?,`address` = ?,`city` = ?,`country` = ?,`phone` = ?,`email` = ?,`createdAt` = ?,`syncedAt` = ?,`isDirty` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final HotelEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getName());
        }
        if (entity.getAddress() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getAddress());
        }
        if (entity.getCity() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getCity());
        }
        if (entity.getCountry() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getCountry());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getPhone());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getEmail());
        }
        statement.bindLong(8, entity.getCreatedAt());
        if (entity.getSyncedAt() == null) {
          statement.bindNull(9);
        } else {
          statement.bindLong(9, entity.getSyncedAt());
        }
        final int _tmp = entity.isDirty() ? 1 : 0;
        statement.bindLong(10, _tmp);
        if (entity.getId() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteHotelById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM hotels WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE hotels SET syncedAt = ?, isDirty = 0 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertHotel(final HotelEntity hotel, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfHotelEntity.insert(hotel);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteHotel(final HotelEntity hotel, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfHotelEntity.handle(hotel);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateHotel(final HotelEntity hotel, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfHotelEntity.handle(hotel);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteHotelById(final String hotelId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteHotelById.acquire();
        int _argIndex = 1;
        if (hotelId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, hotelId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteHotelById.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object markAsSynced(final String hotelId, final long syncedAt,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, syncedAt);
        _argIndex = 2;
        if (hotelId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, hotelId);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkAsSynced.release(_stmt);
        }
      }
    }, arg2);
  }

  @Override
  public Flow<HotelEntity> getHotelById(final String hotelId) {
    final String _sql = "SELECT * FROM hotels WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"hotels"}, new Callable<HotelEntity>() {
      @Override
      @Nullable
      public HotelEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final HotelEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpSyncedAt;
            if (_cursor.isNull(_cursorIndexOfSyncedAt)) {
              _tmpSyncedAt = null;
            } else {
              _tmpSyncedAt = _cursor.getLong(_cursorIndexOfSyncedAt);
            }
            final boolean _tmpIsDirty;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp != 0;
            _result = new HotelEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpPhone,_tmpEmail,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<HotelEntity>> getAllHotels() {
    final String _sql = "SELECT * FROM hotels";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"hotels"}, new Callable<List<HotelEntity>>() {
      @Override
      @NonNull
      public List<HotelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<HotelEntity> _result = new ArrayList<HotelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HotelEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpSyncedAt;
            if (_cursor.isNull(_cursorIndexOfSyncedAt)) {
              _tmpSyncedAt = null;
            } else {
              _tmpSyncedAt = _cursor.getLong(_cursorIndexOfSyncedAt);
            }
            final boolean _tmpIsDirty;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp != 0;
            _item = new HotelEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpPhone,_tmpEmail,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getDirtyHotels(final Continuation<? super List<HotelEntity>> arg0) {
    final String _sql = "SELECT * FROM hotels WHERE isDirty = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<HotelEntity>>() {
      @Override
      @NonNull
      public List<HotelEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfAddress = CursorUtil.getColumnIndexOrThrow(_cursor, "address");
          final int _cursorIndexOfCity = CursorUtil.getColumnIndexOrThrow(_cursor, "city");
          final int _cursorIndexOfCountry = CursorUtil.getColumnIndexOrThrow(_cursor, "country");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<HotelEntity> _result = new ArrayList<HotelEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final HotelEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpAddress;
            if (_cursor.isNull(_cursorIndexOfAddress)) {
              _tmpAddress = null;
            } else {
              _tmpAddress = _cursor.getString(_cursorIndexOfAddress);
            }
            final String _tmpCity;
            if (_cursor.isNull(_cursorIndexOfCity)) {
              _tmpCity = null;
            } else {
              _tmpCity = _cursor.getString(_cursorIndexOfCity);
            }
            final String _tmpCountry;
            if (_cursor.isNull(_cursorIndexOfCountry)) {
              _tmpCountry = null;
            } else {
              _tmpCountry = _cursor.getString(_cursorIndexOfCountry);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final Long _tmpSyncedAt;
            if (_cursor.isNull(_cursorIndexOfSyncedAt)) {
              _tmpSyncedAt = null;
            } else {
              _tmpSyncedAt = _cursor.getLong(_cursorIndexOfSyncedAt);
            }
            final boolean _tmpIsDirty;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp != 0;
            _item = new HotelEntity(_tmpId,_tmpName,_tmpAddress,_tmpCity,_tmpCountry,_tmpPhone,_tmpEmail,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg0);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
