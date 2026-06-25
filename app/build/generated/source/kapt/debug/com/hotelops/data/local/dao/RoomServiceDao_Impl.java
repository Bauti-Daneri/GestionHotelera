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
import com.hotelops.data.local.entity.RoomServiceOrderEntity;
import com.hotelops.domain.model.OrderItem;
import com.hotelops.domain.model.OrderStatus;
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
public final class RoomServiceDao_Impl implements RoomServiceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<RoomServiceOrderEntity> __insertionAdapterOfRoomServiceOrderEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<RoomServiceOrderEntity> __deletionAdapterOfRoomServiceOrderEntity;

  private final EntityDeletionOrUpdateAdapter<RoomServiceOrderEntity> __updateAdapterOfRoomServiceOrderEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOrderById;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  private final SharedSQLiteStatement __preparedStmtOfUpdateOrderStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteOrdersByHotel;

  public RoomServiceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfRoomServiceOrderEntity = new EntityInsertionAdapter<RoomServiceOrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `room_service_orders` (`id`,`hotelId`,`roomId`,`roomNumber`,`guestName`,`items`,`status`,`totalAmount`,`specialInstructions`,`createdAt`,`updatedAt`,`deliveredAt`,`syncedAt`,`isDirty`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomServiceOrderEntity entity) {
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
        if (entity.getRoomId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRoomId());
        }
        if (entity.getRoomNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRoomNumber());
        }
        if (entity.getGuestName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getGuestName());
        }
        final String _tmp = __converters.fromOrderItemList(entity.getItems());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        final String _tmp_1 = __converters.fromOrderStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindDouble(8, entity.getTotalAmount());
        if (entity.getSpecialInstructions() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSpecialInstructions());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getUpdatedAt());
        if (entity.getDeliveredAt() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getDeliveredAt());
        }
        if (entity.getSyncedAt() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getSyncedAt());
        }
        final int _tmp_2 = entity.isDirty() ? 1 : 0;
        statement.bindLong(14, _tmp_2);
      }
    };
    this.__deletionAdapterOfRoomServiceOrderEntity = new EntityDeletionOrUpdateAdapter<RoomServiceOrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `room_service_orders` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomServiceOrderEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfRoomServiceOrderEntity = new EntityDeletionOrUpdateAdapter<RoomServiceOrderEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `room_service_orders` SET `id` = ?,`hotelId` = ?,`roomId` = ?,`roomNumber` = ?,`guestName` = ?,`items` = ?,`status` = ?,`totalAmount` = ?,`specialInstructions` = ?,`createdAt` = ?,`updatedAt` = ?,`deliveredAt` = ?,`syncedAt` = ?,`isDirty` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final RoomServiceOrderEntity entity) {
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
        if (entity.getRoomId() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRoomId());
        }
        if (entity.getRoomNumber() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getRoomNumber());
        }
        if (entity.getGuestName() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getGuestName());
        }
        final String _tmp = __converters.fromOrderItemList(entity.getItems());
        if (_tmp == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, _tmp);
        }
        final String _tmp_1 = __converters.fromOrderStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp_1);
        }
        statement.bindDouble(8, entity.getTotalAmount());
        if (entity.getSpecialInstructions() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getSpecialInstructions());
        }
        statement.bindLong(10, entity.getCreatedAt());
        statement.bindLong(11, entity.getUpdatedAt());
        if (entity.getDeliveredAt() == null) {
          statement.bindNull(12);
        } else {
          statement.bindLong(12, entity.getDeliveredAt());
        }
        if (entity.getSyncedAt() == null) {
          statement.bindNull(13);
        } else {
          statement.bindLong(13, entity.getSyncedAt());
        }
        final int _tmp_2 = entity.isDirty() ? 1 : 0;
        statement.bindLong(14, _tmp_2);
        if (entity.getId() == null) {
          statement.bindNull(15);
        } else {
          statement.bindString(15, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteOrderById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM room_service_orders WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE room_service_orders SET syncedAt = ?, isDirty = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateOrderStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE room_service_orders SET status = ?, updatedAt = ?, deliveredAt = ?, isDirty = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteOrdersByHotel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM room_service_orders WHERE hotelId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertOrder(final RoomServiceOrderEntity order,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomServiceOrderEntity.insert(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertOrders(final List<RoomServiceOrderEntity> orders,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfRoomServiceOrderEntity.insert(orders);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOrder(final RoomServiceOrderEntity order,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfRoomServiceOrderEntity.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateOrder(final RoomServiceOrderEntity order,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfRoomServiceOrderEntity.handle(order);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOrderById(final String orderId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOrderById.acquire();
        int _argIndex = 1;
        if (orderId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, orderId);
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
          __preparedStmtOfDeleteOrderById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final String orderId, final long syncedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, syncedAt);
        _argIndex = 2;
        if (orderId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, orderId);
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
  public Object updateOrderStatus(final String orderId, final OrderStatus status,
      final long updatedAt, final Long deliveredAt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateOrderStatus.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromOrderStatus(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        if (deliveredAt == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, deliveredAt);
        }
        _argIndex = 4;
        if (orderId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, orderId);
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
          __preparedStmtOfUpdateOrderStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOrdersByHotel(final String hotelId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOrdersByHotel.acquire();
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
          __preparedStmtOfDeleteOrdersByHotel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<RoomServiceOrderEntity>> getOrdersByHotel(final String hotelId) {
    final String _sql = "SELECT * FROM room_service_orders WHERE hotelId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"room_service_orders"}, new Callable<List<RoomServiceOrderEntity>>() {
      @Override
      @NonNull
      public List<RoomServiceOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfGuestName = CursorUtil.getColumnIndexOrThrow(_cursor, "guestName");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfSpecialInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "specialInstructions");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeliveredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomServiceOrderEntity> _result = new ArrayList<RoomServiceOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomServiceOrderEntity _item;
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
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpGuestName;
            if (_cursor.isNull(_cursorIndexOfGuestName)) {
              _tmpGuestName = null;
            } else {
              _tmpGuestName = _cursor.getString(_cursorIndexOfGuestName);
            }
            final List<OrderItem> _tmpItems;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItems);
            }
            _tmpItems = __converters.toOrderItemList(_tmp);
            final OrderStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toOrderStatus(_tmp_1);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpSpecialInstructions;
            if (_cursor.isNull(_cursorIndexOfSpecialInstructions)) {
              _tmpSpecialInstructions = null;
            } else {
              _tmpSpecialInstructions = _cursor.getString(_cursorIndexOfSpecialInstructions);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpDeliveredAt;
            if (_cursor.isNull(_cursorIndexOfDeliveredAt)) {
              _tmpDeliveredAt = null;
            } else {
              _tmpDeliveredAt = _cursor.getLong(_cursorIndexOfDeliveredAt);
            }
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
            _item = new RoomServiceOrderEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpGuestName,_tmpItems,_tmpStatus,_tmpTotalAmount,_tmpSpecialInstructions,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeliveredAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<RoomServiceOrderEntity> getOrderById(final String orderId) {
    final String _sql = "SELECT * FROM room_service_orders WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (orderId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, orderId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"room_service_orders"}, new Callable<RoomServiceOrderEntity>() {
      @Override
      @Nullable
      public RoomServiceOrderEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfGuestName = CursorUtil.getColumnIndexOrThrow(_cursor, "guestName");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfSpecialInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "specialInstructions");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeliveredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final RoomServiceOrderEntity _result;
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
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpGuestName;
            if (_cursor.isNull(_cursorIndexOfGuestName)) {
              _tmpGuestName = null;
            } else {
              _tmpGuestName = _cursor.getString(_cursorIndexOfGuestName);
            }
            final List<OrderItem> _tmpItems;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItems);
            }
            _tmpItems = __converters.toOrderItemList(_tmp);
            final OrderStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toOrderStatus(_tmp_1);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpSpecialInstructions;
            if (_cursor.isNull(_cursorIndexOfSpecialInstructions)) {
              _tmpSpecialInstructions = null;
            } else {
              _tmpSpecialInstructions = _cursor.getString(_cursorIndexOfSpecialInstructions);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpDeliveredAt;
            if (_cursor.isNull(_cursorIndexOfDeliveredAt)) {
              _tmpDeliveredAt = null;
            } else {
              _tmpDeliveredAt = _cursor.getLong(_cursorIndexOfDeliveredAt);
            }
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
            _result = new RoomServiceOrderEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpGuestName,_tmpItems,_tmpStatus,_tmpTotalAmount,_tmpSpecialInstructions,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeliveredAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<RoomServiceOrderEntity>> getOrdersByStatus(final String hotelId,
      final OrderStatus status) {
    final String _sql = "SELECT * FROM room_service_orders WHERE hotelId = ? AND status = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    final String _tmp = __converters.fromOrderStatus(status);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"room_service_orders"}, new Callable<List<RoomServiceOrderEntity>>() {
      @Override
      @NonNull
      public List<RoomServiceOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfGuestName = CursorUtil.getColumnIndexOrThrow(_cursor, "guestName");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfSpecialInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "specialInstructions");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeliveredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomServiceOrderEntity> _result = new ArrayList<RoomServiceOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomServiceOrderEntity _item;
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
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpGuestName;
            if (_cursor.isNull(_cursorIndexOfGuestName)) {
              _tmpGuestName = null;
            } else {
              _tmpGuestName = _cursor.getString(_cursorIndexOfGuestName);
            }
            final List<OrderItem> _tmpItems;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfItems);
            }
            _tmpItems = __converters.toOrderItemList(_tmp_1);
            final OrderStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toOrderStatus(_tmp_2);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpSpecialInstructions;
            if (_cursor.isNull(_cursorIndexOfSpecialInstructions)) {
              _tmpSpecialInstructions = null;
            } else {
              _tmpSpecialInstructions = _cursor.getString(_cursorIndexOfSpecialInstructions);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpDeliveredAt;
            if (_cursor.isNull(_cursorIndexOfDeliveredAt)) {
              _tmpDeliveredAt = null;
            } else {
              _tmpDeliveredAt = _cursor.getLong(_cursorIndexOfDeliveredAt);
            }
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
            _item = new RoomServiceOrderEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpGuestName,_tmpItems,_tmpStatus,_tmpTotalAmount,_tmpSpecialInstructions,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeliveredAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<RoomServiceOrderEntity>> getOrdersByRoom(final String hotelId,
      final String roomId) {
    final String _sql = "SELECT * FROM room_service_orders WHERE hotelId = ? AND roomId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    if (roomId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, roomId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"room_service_orders"}, new Callable<List<RoomServiceOrderEntity>>() {
      @Override
      @NonNull
      public List<RoomServiceOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfGuestName = CursorUtil.getColumnIndexOrThrow(_cursor, "guestName");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfSpecialInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "specialInstructions");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeliveredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomServiceOrderEntity> _result = new ArrayList<RoomServiceOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomServiceOrderEntity _item;
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
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpGuestName;
            if (_cursor.isNull(_cursorIndexOfGuestName)) {
              _tmpGuestName = null;
            } else {
              _tmpGuestName = _cursor.getString(_cursorIndexOfGuestName);
            }
            final List<OrderItem> _tmpItems;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItems);
            }
            _tmpItems = __converters.toOrderItemList(_tmp);
            final OrderStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toOrderStatus(_tmp_1);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpSpecialInstructions;
            if (_cursor.isNull(_cursorIndexOfSpecialInstructions)) {
              _tmpSpecialInstructions = null;
            } else {
              _tmpSpecialInstructions = _cursor.getString(_cursorIndexOfSpecialInstructions);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpDeliveredAt;
            if (_cursor.isNull(_cursorIndexOfDeliveredAt)) {
              _tmpDeliveredAt = null;
            } else {
              _tmpDeliveredAt = _cursor.getLong(_cursorIndexOfDeliveredAt);
            }
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
            _item = new RoomServiceOrderEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpGuestName,_tmpItems,_tmpStatus,_tmpTotalAmount,_tmpSpecialInstructions,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeliveredAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<RoomServiceOrderEntity>> getActiveOrders(final String hotelId) {
    final String _sql = "SELECT * FROM room_service_orders WHERE hotelId = ? AND status IN ('PENDING', 'PREPARING') ORDER BY createdAt ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"room_service_orders"}, new Callable<List<RoomServiceOrderEntity>>() {
      @Override
      @NonNull
      public List<RoomServiceOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfGuestName = CursorUtil.getColumnIndexOrThrow(_cursor, "guestName");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfSpecialInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "specialInstructions");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeliveredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomServiceOrderEntity> _result = new ArrayList<RoomServiceOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomServiceOrderEntity _item;
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
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpGuestName;
            if (_cursor.isNull(_cursorIndexOfGuestName)) {
              _tmpGuestName = null;
            } else {
              _tmpGuestName = _cursor.getString(_cursorIndexOfGuestName);
            }
            final List<OrderItem> _tmpItems;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItems);
            }
            _tmpItems = __converters.toOrderItemList(_tmp);
            final OrderStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toOrderStatus(_tmp_1);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpSpecialInstructions;
            if (_cursor.isNull(_cursorIndexOfSpecialInstructions)) {
              _tmpSpecialInstructions = null;
            } else {
              _tmpSpecialInstructions = _cursor.getString(_cursorIndexOfSpecialInstructions);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpDeliveredAt;
            if (_cursor.isNull(_cursorIndexOfDeliveredAt)) {
              _tmpDeliveredAt = null;
            } else {
              _tmpDeliveredAt = _cursor.getLong(_cursorIndexOfDeliveredAt);
            }
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
            _item = new RoomServiceOrderEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpGuestName,_tmpItems,_tmpStatus,_tmpTotalAmount,_tmpSpecialInstructions,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeliveredAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Object getDirtyOrders(
      final Continuation<? super List<RoomServiceOrderEntity>> $completion) {
    final String _sql = "SELECT * FROM room_service_orders WHERE isDirty = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<RoomServiceOrderEntity>>() {
      @Override
      @NonNull
      public List<RoomServiceOrderEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfGuestName = CursorUtil.getColumnIndexOrThrow(_cursor, "guestName");
          final int _cursorIndexOfItems = CursorUtil.getColumnIndexOrThrow(_cursor, "items");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfTotalAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAmount");
          final int _cursorIndexOfSpecialInstructions = CursorUtil.getColumnIndexOrThrow(_cursor, "specialInstructions");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfDeliveredAt = CursorUtil.getColumnIndexOrThrow(_cursor, "deliveredAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<RoomServiceOrderEntity> _result = new ArrayList<RoomServiceOrderEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final RoomServiceOrderEntity _item;
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
            final String _tmpRoomId;
            if (_cursor.isNull(_cursorIndexOfRoomId)) {
              _tmpRoomId = null;
            } else {
              _tmpRoomId = _cursor.getString(_cursorIndexOfRoomId);
            }
            final String _tmpRoomNumber;
            if (_cursor.isNull(_cursorIndexOfRoomNumber)) {
              _tmpRoomNumber = null;
            } else {
              _tmpRoomNumber = _cursor.getString(_cursorIndexOfRoomNumber);
            }
            final String _tmpGuestName;
            if (_cursor.isNull(_cursorIndexOfGuestName)) {
              _tmpGuestName = null;
            } else {
              _tmpGuestName = _cursor.getString(_cursorIndexOfGuestName);
            }
            final List<OrderItem> _tmpItems;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfItems)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfItems);
            }
            _tmpItems = __converters.toOrderItemList(_tmp);
            final OrderStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toOrderStatus(_tmp_1);
            final double _tmpTotalAmount;
            _tmpTotalAmount = _cursor.getDouble(_cursorIndexOfTotalAmount);
            final String _tmpSpecialInstructions;
            if (_cursor.isNull(_cursorIndexOfSpecialInstructions)) {
              _tmpSpecialInstructions = null;
            } else {
              _tmpSpecialInstructions = _cursor.getString(_cursorIndexOfSpecialInstructions);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpDeliveredAt;
            if (_cursor.isNull(_cursorIndexOfDeliveredAt)) {
              _tmpDeliveredAt = null;
            } else {
              _tmpDeliveredAt = _cursor.getLong(_cursorIndexOfDeliveredAt);
            }
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
            _item = new RoomServiceOrderEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpGuestName,_tmpItems,_tmpStatus,_tmpTotalAmount,_tmpSpecialInstructions,_tmpCreatedAt,_tmpUpdatedAt,_tmpDeliveredAt,_tmpSyncedAt,_tmpIsDirty);
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
