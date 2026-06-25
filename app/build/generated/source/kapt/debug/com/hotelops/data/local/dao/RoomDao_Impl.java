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
import com.hotelops.data.local.entity.RoomEntity;
import com.hotelops.domain.model.RoomStatus;
import com.hotelops.domain.model.RoomType;
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
public final class RoomDao_Impl implements RoomDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RoomEntity> __insertionAdapterOfRoomEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<RoomEntity> __deletionAdapterOfRoomEntity;

  private final EntityDeletionOrUpdateAdapter<RoomEntity> __updateAdapterOfRoomEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRoomById;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  private final SharedSQLiteStatement __preparedStmtOfUpdateRoomStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteRoomsByHotel;

  public RoomDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoomEntity = new EntityInsertionAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `rooms` (`id`,`hotelId`,`roomNumber`,`floor`,`type`,`status`,`notes`,`lastCleanedAt`,`lastCleanedBy`,`createdAt`,`syncedAt`,`isDirty`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
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
        if (entity.getRoomNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRoomNumber());
        }
        statement.bindLong(4, entity.getFloor());
        final String _tmp = __converters.fromRoomType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp);
        }
        final String _tmp_1 = __converters.fromRoomStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_1);
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        if (entity.getLastCleanedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getLastCleanedAt());
        }
        if (entity.getLastCleanedBy() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getLastCleanedBy());
        }
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getSyncedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getSyncedAt());
        }
        final int _tmp_2 = entity.isDirty() ? 1 : 0;
        statement.bindLong(12, _tmp_2);
      }
    };
    this.__deletionAdapterOfRoomEntity = new EntityDeletionOrUpdateAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `rooms` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfRoomEntity = new EntityDeletionOrUpdateAdapter<RoomEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `rooms` SET `id` = ?,`hotelId` = ?,`roomNumber` = ?,`floor` = ?,`type` = ?,`status` = ?,`notes` = ?,`lastCleanedAt` = ?,`lastCleanedBy` = ?,`createdAt` = ?,`syncedAt` = ?,`isDirty` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomEntity entity) {
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
        if (entity.getRoomNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRoomNumber());
        }
        statement.bindLong(4, entity.getFloor());
        final String _tmp = __converters.fromRoomType(entity.getType());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp);
        }
        final String _tmp_1 = __converters.fromRoomStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp_1);
        }
        if (entity.getNotes() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getNotes());
        }
        if (entity.getLastCleanedAt() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getLastCleanedAt());
        }
        if (entity.getLastCleanedBy() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getLastCleanedBy());
        }
        statement.bindLong(10, entity.getCreatedAt());
        if (entity.getSyncedAt() == null) {
          statement.bindNull(11);
        } else {
          statement.bindLong(11, entity.getSyncedAt());
        }
        final int _tmp_2 = entity.isDirty() ? 1 : 0;
        statement.bindLong(12, _tmp_2);
        if (entity.getId() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteRoomById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM rooms WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE rooms SET syncedAt = ?, isDirty = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateRoomStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE rooms SET status = ?, lastCleanedAt = ?, lastCleanedBy = ?, isDirty = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteRoomsByHotel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM rooms WHERE hotelId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomEntity.insert(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertRooms(final List<RoomEntity> rooms,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomEntity.insert(rooms);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRoomEntity.handle(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateRoom(final RoomEntity room, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRoomEntity.handle(room);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoomById(final String roomId, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRoomById.acquire();
        int _argIndex = 1;
        if (roomId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, roomId);
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
          __preparedStmtOfDeleteRoomById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final String roomId, final long syncedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, syncedAt);
        _argIndex = 2;
        if (roomId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, roomId);
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
    }, $completion);
  }

  @Override
  public Object updateRoomStatus(final String roomId, final RoomStatus status, final Long cleanedAt,
      final String cleanedBy, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateRoomStatus.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromRoomStatus(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        if (cleanedAt == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, cleanedAt);
        }
        _argIndex = 3;
        if (cleanedBy == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, cleanedBy);
        }
        _argIndex = 4;
        if (roomId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, roomId);
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
          __preparedStmtOfUpdateRoomStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteRoomsByHotel(final String hotelId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteRoomsByHotel.acquire();
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
          __preparedStmtOfDeleteRoomsByHotel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RoomEntity>> getRoomsByHotel(final String hotelId) {
    final String _sql = "SELECT * FROM rooms WHERE hotelId = ? ORDER BY floor ASC, roomNumber ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastCleanedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedAt");
          final int _cursorIndexOfLastCleanedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedBy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
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
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final int _tmpFloor;
            _tmpFloor = _cursor.getInt(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toRoomType(_tmp);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastCleanedAt;
            if (_cursor.isNull(_cursorIndexOfLastCleanedAt)) {
              _tmpLastCleanedAt = null;
            } else {
              _tmpLastCleanedAt = _cursor.getLong(_cursorIndexOfLastCleanedAt);
            }
            final String _tmpLastCleanedBy;
            if (_cursor.isNull(_cursorIndexOfLastCleanedBy)) {
              _tmpLastCleanedBy = null;
            } else {
              _tmpLastCleanedBy = _cursor.getString(_cursorIndexOfLastCleanedBy);
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
            _item = new RoomEntity(_tmpId,_tmpHotelId,_tmpRoomNumber,_tmpFloor,_tmpType,_tmpStatus,_tmpNotes,_tmpLastCleanedAt,_tmpLastCleanedBy,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<RoomEntity> getRoomById(final String roomId) {
    final String _sql = "SELECT * FROM rooms WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (roomId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, roomId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<RoomEntity>() {
      @Override
      @Nullable
      public RoomEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastCleanedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedAt");
          final int _cursorIndexOfLastCleanedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedBy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final RoomEntity _result;
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
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final int _tmpFloor;
            _tmpFloor = _cursor.getInt(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toRoomType(_tmp);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastCleanedAt;
            if (_cursor.isNull(_cursorIndexOfLastCleanedAt)) {
              _tmpLastCleanedAt = null;
            } else {
              _tmpLastCleanedAt = _cursor.getLong(_cursorIndexOfLastCleanedAt);
            }
            final String _tmpLastCleanedBy;
            if (_cursor.isNull(_cursorIndexOfLastCleanedBy)) {
              _tmpLastCleanedBy = null;
            } else {
              _tmpLastCleanedBy = _cursor.getString(_cursorIndexOfLastCleanedBy);
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
            _result = new RoomEntity(_tmpId,_tmpHotelId,_tmpRoomNumber,_tmpFloor,_tmpType,_tmpStatus,_tmpNotes,_tmpLastCleanedAt,_tmpLastCleanedBy,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<RoomEntity>> getRoomsByStatus(final String hotelId, final RoomStatus status) {
    final String _sql = "SELECT * FROM rooms WHERE hotelId = ? AND status = ? ORDER BY floor ASC, roomNumber ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    final String _tmp = __converters.fromRoomStatus(status);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastCleanedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedAt");
          final int _cursorIndexOfLastCleanedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedBy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
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
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final int _tmpFloor;
            _tmpFloor = _cursor.getInt(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toRoomType(_tmp_1);
            final RoomStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toRoomStatus(_tmp_2);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastCleanedAt;
            if (_cursor.isNull(_cursorIndexOfLastCleanedAt)) {
              _tmpLastCleanedAt = null;
            } else {
              _tmpLastCleanedAt = _cursor.getLong(_cursorIndexOfLastCleanedAt);
            }
            final String _tmpLastCleanedBy;
            if (_cursor.isNull(_cursorIndexOfLastCleanedBy)) {
              _tmpLastCleanedBy = null;
            } else {
              _tmpLastCleanedBy = _cursor.getString(_cursorIndexOfLastCleanedBy);
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
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsDirty);
            _tmpIsDirty = _tmp_3 != 0;
            _item = new RoomEntity(_tmpId,_tmpHotelId,_tmpRoomNumber,_tmpFloor,_tmpType,_tmpStatus,_tmpNotes,_tmpLastCleanedAt,_tmpLastCleanedBy,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<RoomEntity>> getRoomsByFloor(final String hotelId, final int floor) {
    final String _sql = "SELECT * FROM rooms WHERE hotelId = ? AND floor = ? ORDER BY roomNumber ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, floor);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"rooms"}, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastCleanedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedAt");
          final int _cursorIndexOfLastCleanedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedBy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
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
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final int _tmpFloor;
            _tmpFloor = _cursor.getInt(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toRoomType(_tmp);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastCleanedAt;
            if (_cursor.isNull(_cursorIndexOfLastCleanedAt)) {
              _tmpLastCleanedAt = null;
            } else {
              _tmpLastCleanedAt = _cursor.getLong(_cursorIndexOfLastCleanedAt);
            }
            final String _tmpLastCleanedBy;
            if (_cursor.isNull(_cursorIndexOfLastCleanedBy)) {
              _tmpLastCleanedBy = null;
            } else {
              _tmpLastCleanedBy = _cursor.getString(_cursorIndexOfLastCleanedBy);
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
            _item = new RoomEntity(_tmpId,_tmpHotelId,_tmpRoomNumber,_tmpFloor,_tmpType,_tmpStatus,_tmpNotes,_tmpLastCleanedAt,_tmpLastCleanedBy,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Object getRoomByNumber(final String roomNumber, final String hotelId,
      final Continuation<? super RoomEntity> $completion) {
    final String _sql = "SELECT * FROM rooms WHERE roomNumber = ? AND hotelId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (roomNumber == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, roomNumber);
    }
    _argIndex = 2;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<RoomEntity>() {
      @Override
      @Nullable
      public RoomEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastCleanedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedAt");
          final int _cursorIndexOfLastCleanedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedBy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final RoomEntity _result;
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
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final int _tmpFloor;
            _tmpFloor = _cursor.getInt(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toRoomType(_tmp);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastCleanedAt;
            if (_cursor.isNull(_cursorIndexOfLastCleanedAt)) {
              _tmpLastCleanedAt = null;
            } else {
              _tmpLastCleanedAt = _cursor.getLong(_cursorIndexOfLastCleanedAt);
            }
            final String _tmpLastCleanedBy;
            if (_cursor.isNull(_cursorIndexOfLastCleanedBy)) {
              _tmpLastCleanedBy = null;
            } else {
              _tmpLastCleanedBy = _cursor.getString(_cursorIndexOfLastCleanedBy);
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
            _result = new RoomEntity(_tmpId,_tmpHotelId,_tmpRoomNumber,_tmpFloor,_tmpType,_tmpStatus,_tmpNotes,_tmpLastCleanedAt,_tmpLastCleanedBy,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDirtyRooms(final Continuation<? super List<RoomEntity>> $completion) {
    final String _sql = "SELECT * FROM rooms WHERE isDirty = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RoomEntity>>() {
      @Override
      @NonNull
      public List<RoomEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfFloor = CursorUtil.getColumnIndexOrThrow(_cursor, "floor");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfNotes = CursorUtil.getColumnIndexOrThrow(_cursor, "notes");
          final int _cursorIndexOfLastCleanedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedAt");
          final int _cursorIndexOfLastCleanedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "lastCleanedBy");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomEntity> _result = new ArrayList<RoomEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomEntity _item;
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
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final int _tmpFloor;
            _tmpFloor = _cursor.getInt(_cursorIndexOfFloor);
            final RoomType _tmpType;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfType);
            }
            _tmpType = __converters.toRoomType(_tmp);
            final RoomStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toRoomStatus(_tmp_1);
            final String _tmpNotes;
            if (_cursor.isNull(_cursorIndexOfNotes)) {
              _tmpNotes = null;
            } else {
              _tmpNotes = _cursor.getString(_cursorIndexOfNotes);
            }
            final Long _tmpLastCleanedAt;
            if (_cursor.isNull(_cursorIndexOfLastCleanedAt)) {
              _tmpLastCleanedAt = null;
            } else {
              _tmpLastCleanedAt = _cursor.getLong(_cursorIndexOfLastCleanedAt);
            }
            final String _tmpLastCleanedBy;
            if (_cursor.isNull(_cursorIndexOfLastCleanedBy)) {
              _tmpLastCleanedBy = null;
            } else {
              _tmpLastCleanedBy = _cursor.getString(_cursorIndexOfLastCleanedBy);
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
            _item = new RoomEntity(_tmpId,_tmpHotelId,_tmpRoomNumber,_tmpFloor,_tmpType,_tmpStatus,_tmpNotes,_tmpLastCleanedAt,_tmpLastCleanedBy,_tmpCreatedAt,_tmpSyncedAt,_tmpIsDirty);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
