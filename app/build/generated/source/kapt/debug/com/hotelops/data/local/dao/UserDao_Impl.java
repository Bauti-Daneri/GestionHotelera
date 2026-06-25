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
import com.hotelops.data.local.database.Converters;
import com.hotelops.data.local.entity.UserEntity;
import com.hotelops.domain.model.UserRole;
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
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<UserEntity> __insertionAdapterOfUserEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<UserEntity> __deletionAdapterOfUserEntity;

  private final EntityDeletionOrUpdateAdapter<UserEntity> __updateAdapterOfUserEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUserById;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  private final SharedSQLiteStatement __preparedStmtOfDeleteUsersByHotel;

  public UserDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUserEntity = new EntityInsertionAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `users` (`id`,`hotelId`,`name`,`email`,`passwordHash`,`role`,`department`,`phone`,`employeeId`,`createdAt`,`syncedAt`,`isDirty`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getHotelId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getHotelId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getPasswordHash() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPasswordHash());
        }
        final String _tmp = __converters.fromUserRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getDepartment() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDepartment());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPhone());
        }
        if (entity.getEmployeeId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getEmployeeId());
        }
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getSyncedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getSyncedAt());
        }
        final int _tmp_1 = entity.isDirty() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
      }
    };
    this.__deletionAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `users` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfUserEntity = new EntityDeletionOrUpdateAdapter<UserEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `users` SET `id` = ?,`hotelId` = ?,`name` = ?,`email` = ?,`passwordHash` = ?,`role` = ?,`department` = ?,`phone` = ?,`employeeId` = ?,`createdAt` = ?,`syncedAt` = ?,`isDirty` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final UserEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
        if (entity.getHotelId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getHotelId());
        }
        if (entity.getName() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getName());
        }
        if (entity.getEmail() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getEmail());
        }
        if (entity.getPasswordHash() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getPasswordHash());
        }
        final String _tmp = __converters.fromUserRole(entity.getRole());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        if (entity.getDepartment() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getDepartment());
        }
        if (entity.getPhone() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getPhone());
        }
        if (entity.getEmployeeId() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getEmployeeId());
        }
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getSyncedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getSyncedAt());
        }
        final int _tmp_1 = entity.isDirty() ? 1 : 0;
        statement.bindLong(12, _tmp_1);
        if (entity.getId() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteUserById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE users SET syncedAt = ?, isDirty = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteUsersByHotel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM users WHERE hotelId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertUser(final UserEntity user, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object insertUsers(final List<UserEntity> users, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfUserEntity.insert(users);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteUser(final UserEntity user, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object updateUser(final UserEntity user, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfUserEntity.handle(user);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, arg1);
  }

  @Override
  public Object deleteUserById(final String userId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUserById.acquire();
        int _argIndex = 1;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
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
          __preparedStmtOfDeleteUserById.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Object markAsSynced(final String userId, final long syncedAt,
      final Continuation<? super Unit> arg2) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, syncedAt);
        _argIndex = 2;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
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
  public Object deleteUsersByHotel(final String hotelId, final Continuation<? super Unit> arg1) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteUsersByHotel.acquire();
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
          __preparedStmtOfDeleteUsersByHotel.release(_stmt);
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<UserEntity>> getUsersByHotel(final String hotelId) {
    final String _sql = "SELECT * FROM users WHERE hotelId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpHotelId;
            if (_cursor.isNull(_cursorIndexOfHotelId)) {
              _tmpHotelId = null;
            } else {
              _tmpHotelId = _cursor.getString(_cursorIndexOfHotelId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPasswordHash;
            if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
              _tmpPasswordHash = null;
            } else {
              _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
            }
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toUserRole(_tmp);
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmployeeId;
            if (_cursor.isNull(_cursorIndexOfEmployeeId)) {
              _tmpEmployeeId = null;
            } else {
              _tmpEmployeeId = _cursor.getString(_cursorIndexOfEmployeeId);
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
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_1 != 0;
            _item = new UserEntity(_tmpId,_tmpHotelId,_tmpName,_tmpEmail,_tmpPasswordHash,_tmpRole,_tmpDepartment,_tmpPhone,_tmpEmployeeId,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<UserEntity> getUserById(final String userId) {
    final String _sql = "SELECT * FROM users WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpHotelId;
            if (_cursor.isNull(_cursorIndexOfHotelId)) {
              _tmpHotelId = null;
            } else {
              _tmpHotelId = _cursor.getString(_cursorIndexOfHotelId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPasswordHash;
            if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
              _tmpPasswordHash = null;
            } else {
              _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
            }
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toUserRole(_tmp);
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmployeeId;
            if (_cursor.isNull(_cursorIndexOfEmployeeId)) {
              _tmpEmployeeId = null;
            } else {
              _tmpEmployeeId = _cursor.getString(_cursorIndexOfEmployeeId);
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
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_1 != 0;
            _result = new UserEntity(_tmpId,_tmpHotelId,_tmpName,_tmpEmail,_tmpPasswordHash,_tmpRole,_tmpDepartment,_tmpPhone,_tmpEmployeeId,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Object getUserByEmail(final String email, final String hotelId,
      final Continuation<? super UserEntity> arg2) {
    final String _sql = "SELECT * FROM users WHERE email = ? AND hotelId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    _argIndex = 2;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpHotelId;
            if (_cursor.isNull(_cursorIndexOfHotelId)) {
              _tmpHotelId = null;
            } else {
              _tmpHotelId = _cursor.getString(_cursorIndexOfHotelId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPasswordHash;
            if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
              _tmpPasswordHash = null;
            } else {
              _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
            }
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toUserRole(_tmp);
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmployeeId;
            if (_cursor.isNull(_cursorIndexOfEmployeeId)) {
              _tmpEmployeeId = null;
            } else {
              _tmpEmployeeId = _cursor.getString(_cursorIndexOfEmployeeId);
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
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_1 != 0;
            _result = new UserEntity(_tmpId,_tmpHotelId,_tmpName,_tmpEmail,_tmpPasswordHash,_tmpRole,_tmpDepartment,_tmpPhone,_tmpEmployeeId,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg2);
  }

  @Override
  public Object getUserByEmailGlobal(final String email,
      final Continuation<? super UserEntity> arg1) {
    final String _sql = "SELECT * FROM users WHERE email = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (email == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, email);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<UserEntity>() {
      @Override
      @Nullable
      public UserEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final UserEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpHotelId;
            if (_cursor.isNull(_cursorIndexOfHotelId)) {
              _tmpHotelId = null;
            } else {
              _tmpHotelId = _cursor.getString(_cursorIndexOfHotelId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPasswordHash;
            if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
              _tmpPasswordHash = null;
            } else {
              _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
            }
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toUserRole(_tmp);
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmployeeId;
            if (_cursor.isNull(_cursorIndexOfEmployeeId)) {
              _tmpEmployeeId = null;
            } else {
              _tmpEmployeeId = _cursor.getString(_cursorIndexOfEmployeeId);
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
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_1 != 0;
            _result = new UserEntity(_tmpId,_tmpHotelId,_tmpName,_tmpEmail,_tmpPasswordHash,_tmpRole,_tmpDepartment,_tmpPhone,_tmpEmployeeId,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, arg1);
  }

  @Override
  public Flow<List<UserEntity>> getUsersByRole(final String hotelId, final UserRole role) {
    final String _sql = "SELECT * FROM users WHERE hotelId = ? AND role = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    final String _tmp = __converters.fromUserRole(role);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"users"}, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpHotelId;
            if (_cursor.isNull(_cursorIndexOfHotelId)) {
              _tmpHotelId = null;
            } else {
              _tmpHotelId = _cursor.getString(_cursorIndexOfHotelId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPasswordHash;
            if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
              _tmpPasswordHash = null;
            } else {
              _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
            }
            final UserRole _tmpRole;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toUserRole(_tmp_1);
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmployeeId;
            if (_cursor.isNull(_cursorIndexOfEmployeeId)) {
              _tmpEmployeeId = null;
            } else {
              _tmpEmployeeId = _cursor.getString(_cursorIndexOfEmployeeId);
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
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_2 != 0;
            _item = new UserEntity(_tmpId,_tmpHotelId,_tmpName,_tmpEmail,_tmpPasswordHash,_tmpRole,_tmpDepartment,_tmpPhone,_tmpEmployeeId,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Object getDirtyUsers(final Continuation<? super List<UserEntity>> arg0) {
    final String _sql = "SELECT * FROM users WHERE isDirty = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<UserEntity>>() {
      @Override
      @NonNull
      public List<UserEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfEmail = CursorUtil.getColumnIndexOrThrow(_cursor, "email");
          final int _cursorIndexOfPasswordHash = CursorUtil.getColumnIndexOrThrow(_cursor, "passwordHash");
          final int _cursorIndexOfRole = CursorUtil.getColumnIndexOrThrow(_cursor, "role");
          final int _cursorIndexOfDepartment = CursorUtil.getColumnIndexOrThrow(_cursor, "department");
          final int _cursorIndexOfPhone = CursorUtil.getColumnIndexOrThrow(_cursor, "phone");
          final int _cursorIndexOfEmployeeId = CursorUtil.getColumnIndexOrThrow(_cursor, "employeeId");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<UserEntity> _result = new ArrayList<UserEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final UserEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpHotelId;
            if (_cursor.isNull(_cursorIndexOfHotelId)) {
              _tmpHotelId = null;
            } else {
              _tmpHotelId = _cursor.getString(_cursorIndexOfHotelId);
            }
            final String _tmpName;
            if (_cursor.isNull(_cursorIndexOfName)) {
              _tmpName = null;
            } else {
              _tmpName = _cursor.getString(_cursorIndexOfName);
            }
            final String _tmpEmail;
            if (_cursor.isNull(_cursorIndexOfEmail)) {
              _tmpEmail = null;
            } else {
              _tmpEmail = _cursor.getString(_cursorIndexOfEmail);
            }
            final String _tmpPasswordHash;
            if (_cursor.isNull(_cursorIndexOfPasswordHash)) {
              _tmpPasswordHash = null;
            } else {
              _tmpPasswordHash = _cursor.getString(_cursorIndexOfPasswordHash);
            }
            final UserRole _tmpRole;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfRole)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfRole);
            }
            _tmpRole = __converters.toUserRole(_tmp);
            final String _tmpDepartment;
            if (_cursor.isNull(_cursorIndexOfDepartment)) {
              _tmpDepartment = null;
            } else {
              _tmpDepartment = _cursor.getString(_cursorIndexOfDepartment);
            }
            final String _tmpPhone;
            if (_cursor.isNull(_cursorIndexOfPhone)) {
              _tmpPhone = null;
            } else {
              _tmpPhone = _cursor.getString(_cursorIndexOfPhone);
            }
            final String _tmpEmployeeId;
            if (_cursor.isNull(_cursorIndexOfEmployeeId)) {
              _tmpEmployeeId = null;
            } else {
              _tmpEmployeeId = _cursor.getString(_cursorIndexOfEmployeeId);
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
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_1 != 0;
            _item = new UserEntity(_tmpId,_tmpHotelId,_tmpName,_tmpEmail,_tmpPasswordHash,_tmpRole,_tmpDepartment,_tmpPhone,_tmpEmployeeId,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
