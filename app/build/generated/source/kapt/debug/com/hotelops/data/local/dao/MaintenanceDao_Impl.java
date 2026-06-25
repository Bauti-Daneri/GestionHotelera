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
import com.hotelops.data.local.entity.MaintenanceTicketEntity;
import com.hotelops.domain.model.TicketCategory;
import com.hotelops.domain.model.TicketStatus;
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
public final class MaintenanceDao_Impl implements MaintenanceDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<MaintenanceTicketEntity> __insertionAdapterOfMaintenanceTicketEntity;

  private final Converters __converters = new Converters();

  private final EntityDeletionOrUpdateAdapter<MaintenanceTicketEntity> __deletionAdapterOfMaintenanceTicketEntity;

  private final EntityDeletionOrUpdateAdapter<MaintenanceTicketEntity> __updateAdapterOfMaintenanceTicketEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTicketById;

  private final SharedSQLiteStatement __preparedStmtOfMarkAsSynced;

  private final SharedSQLiteStatement __preparedStmtOfUpdateTicketStatus;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTicketsByHotel;

  public MaintenanceDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMaintenanceTicketEntity = new EntityInsertionAdapter<MaintenanceTicketEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `maintenance_tickets` (`id`,`hotelId`,`roomId`,`roomNumber`,`title`,`description`,`category`,`status`,`priority`,`reportedBy`,`reportedByName`,`assignedTo`,`assignedToName`,`imageUrl`,`createdAt`,`updatedAt`,`completedAt`,`syncedAt`,`isDirty`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MaintenanceTicketEntity entity) {
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
        if (entity.getTitle() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDescription());
        }
        final String _tmp = __converters.fromTicketCategory(entity.getCategory());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = __converters.fromTicketStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        if (entity.getPriority() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPriority());
        }
        if (entity.getReportedBy() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getReportedBy());
        }
        if (entity.getReportedByName() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getReportedByName());
        }
        if (entity.getAssignedTo() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getAssignedTo());
        }
        if (entity.getAssignedToName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getAssignedToName());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getImageUrl());
        }
        statement.bindLong(15, entity.getCreatedAt());
        statement.bindLong(16, entity.getUpdatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getCompletedAt());
        }
        if (entity.getSyncedAt() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getSyncedAt());
        }
        final int _tmp_2 = entity.isDirty() ? 1 : 0;
        statement.bindLong(19, _tmp_2);
      }
    };
    this.__deletionAdapterOfMaintenanceTicketEntity = new EntityDeletionOrUpdateAdapter<MaintenanceTicketEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `maintenance_tickets` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MaintenanceTicketEntity entity) {
        if (entity.getId() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getId());
        }
      }
    };
    this.__updateAdapterOfMaintenanceTicketEntity = new EntityDeletionOrUpdateAdapter<MaintenanceTicketEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `maintenance_tickets` SET `id` = ?,`hotelId` = ?,`roomId` = ?,`roomNumber` = ?,`title` = ?,`description` = ?,`category` = ?,`status` = ?,`priority` = ?,`reportedBy` = ?,`reportedByName` = ?,`assignedTo` = ?,`assignedToName` = ?,`imageUrl` = ?,`createdAt` = ?,`updatedAt` = ?,`completedAt` = ?,`syncedAt` = ?,`isDirty` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final MaintenanceTicketEntity entity) {
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
        if (entity.getTitle() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getTitle());
        }
        if (entity.getDescription() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getDescription());
        }
        final String _tmp = __converters.fromTicketCategory(entity.getCategory());
        if (_tmp == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, _tmp);
        }
        final String _tmp_1 = __converters.fromTicketStatus(entity.getStatus());
        if (_tmp_1 == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, _tmp_1);
        }
        if (entity.getPriority() == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, entity.getPriority());
        }
        if (entity.getReportedBy() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getReportedBy());
        }
        if (entity.getReportedByName() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getReportedByName());
        }
        if (entity.getAssignedTo() == null) {
          statement.bindNull(12);
        } else {
          statement.bindString(12, entity.getAssignedTo());
        }
        if (entity.getAssignedToName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getAssignedToName());
        }
        if (entity.getImageUrl() == null) {
          statement.bindNull(14);
        } else {
          statement.bindString(14, entity.getImageUrl());
        }
        statement.bindLong(15, entity.getCreatedAt());
        statement.bindLong(16, entity.getUpdatedAt());
        if (entity.getCompletedAt() == null) {
          statement.bindNull(17);
        } else {
          statement.bindLong(17, entity.getCompletedAt());
        }
        if (entity.getSyncedAt() == null) {
          statement.bindNull(18);
        } else {
          statement.bindLong(18, entity.getSyncedAt());
        }
        final int _tmp_2 = entity.isDirty() ? 1 : 0;
        statement.bindLong(19, _tmp_2);
        if (entity.getId() == null) {
          statement.bindNull(20);
        } else {
          statement.bindString(20, entity.getId());
        }
      }
    };
    this.__preparedStmtOfDeleteTicketById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM maintenance_tickets WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAsSynced = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE maintenance_tickets SET syncedAt = ?, isDirty = 0 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdateTicketStatus = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE maintenance_tickets SET status = ?, updatedAt = ?, completedAt = ?, isDirty = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteTicketsByHotel = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM maintenance_tickets WHERE hotelId = ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertTicket(final MaintenanceTicketEntity ticket,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMaintenanceTicketEntity.insert(ticket);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertTickets(final List<MaintenanceTicketEntity> tickets,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfMaintenanceTicketEntity.insert(tickets);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTicket(final MaintenanceTicketEntity ticket,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMaintenanceTicketEntity.handle(ticket);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object updateTicket(final MaintenanceTicketEntity ticket,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMaintenanceTicketEntity.handle(ticket);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTicketById(final String ticketId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTicketById.acquire();
        int _argIndex = 1;
        if (ticketId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, ticketId);
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
          __preparedStmtOfDeleteTicketById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAsSynced(final String ticketId, final long syncedAt,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAsSynced.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, syncedAt);
        _argIndex = 2;
        if (ticketId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, ticketId);
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
  public Object updateTicketStatus(final String ticketId, final TicketStatus status,
      final long updatedAt, final Long completedAt, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateTicketStatus.acquire();
        int _argIndex = 1;
        final String _tmp = __converters.fromTicketStatus(status);
        if (_tmp == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, _tmp);
        }
        _argIndex = 2;
        _stmt.bindLong(_argIndex, updatedAt);
        _argIndex = 3;
        if (completedAt == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindLong(_argIndex, completedAt);
        }
        _argIndex = 4;
        if (ticketId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, ticketId);
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
          __preparedStmtOfUpdateTicketStatus.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteTicketsByHotel(final String hotelId,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTicketsByHotel.acquire();
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
          __preparedStmtOfDeleteTicketsByHotel.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<MaintenanceTicketEntity>> getTicketsByHotel(final String hotelId) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE hotelId = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_tickets"}, new Callable<List<MaintenanceTicketEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceTicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<MaintenanceTicketEntity> _result = new ArrayList<MaintenanceTicketEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceTicketEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp);
            final TicketStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_1);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _item = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<MaintenanceTicketEntity> getTicketById(final String ticketId) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (ticketId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, ticketId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_tickets"}, new Callable<MaintenanceTicketEntity>() {
      @Override
      @Nullable
      public MaintenanceTicketEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final MaintenanceTicketEntity _result;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp);
            final TicketStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_1);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _result = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<MaintenanceTicketEntity>> getTicketsByStatus(final String hotelId,
      final TicketStatus status) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE hotelId = ? AND status = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    final String _tmp = __converters.fromTicketStatus(status);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_tickets"}, new Callable<List<MaintenanceTicketEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceTicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<MaintenanceTicketEntity> _result = new ArrayList<MaintenanceTicketEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceTicketEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp_1);
            final TicketStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_2);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _item = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<MaintenanceTicketEntity>> getTicketsByCategory(final String hotelId,
      final TicketCategory category) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE hotelId = ? AND category = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    final String _tmp = __converters.fromTicketCategory(category);
    if (_tmp == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, _tmp);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_tickets"}, new Callable<List<MaintenanceTicketEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceTicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<MaintenanceTicketEntity> _result = new ArrayList<MaintenanceTicketEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceTicketEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp_1);
            final TicketStatus _tmpStatus;
            final String _tmp_2;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_2 = null;
            } else {
              _tmp_2 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_2);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _item = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<MaintenanceTicketEntity>> getTicketsByAssignedUser(final String hotelId,
      final String userId) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE hotelId = ? AND assignedTo = ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (hotelId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, hotelId);
    }
    _argIndex = 2;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_tickets"}, new Callable<List<MaintenanceTicketEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceTicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<MaintenanceTicketEntity> _result = new ArrayList<MaintenanceTicketEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceTicketEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp);
            final TicketStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_1);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _item = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Flow<List<MaintenanceTicketEntity>> getTicketsByRoom(final String hotelId,
      final String roomId) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE hotelId = ? AND roomId = ? ORDER BY createdAt DESC";
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
    return CoroutinesRoom.createFlow(__db, false, new String[] {"maintenance_tickets"}, new Callable<List<MaintenanceTicketEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceTicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<MaintenanceTicketEntity> _result = new ArrayList<MaintenanceTicketEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceTicketEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp);
            final TicketStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_1);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _item = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
  public Object getDirtyTickets(
      final Continuation<? super List<MaintenanceTicketEntity>> $completion) {
    final String _sql = "SELECT * FROM maintenance_tickets WHERE isDirty = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<MaintenanceTicketEntity>>() {
      @Override
      @NonNull
      public List<MaintenanceTicketEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfHotelId = CursorUtil.getColumnIndexOrThrow(_cursor, "hotelId");
          final int _cursorIndexOfRoomId = CursorUtil.getColumnIndexOrThrow(_cursor, "roomId");
          final int _cursorIndexOfRoomNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "roomNumber");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfDescription = CursorUtil.getColumnIndexOrThrow(_cursor, "description");
          final int _cursorIndexOfCategory = CursorUtil.getColumnIndexOrThrow(_cursor, "category");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final int _cursorIndexOfPriority = CursorUtil.getColumnIndexOrThrow(_cursor, "priority");
          final int _cursorIndexOfReportedBy = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedBy");
          final int _cursorIndexOfReportedByName = CursorUtil.getColumnIndexOrThrow(_cursor, "reportedByName");
          final int _cursorIndexOfAssignedTo = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedTo");
          final int _cursorIndexOfAssignedToName = CursorUtil.getColumnIndexOrThrow(_cursor, "assignedToName");
          final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "imageUrl");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final int _cursorIndexOfUpdatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "updatedAt");
          final int _cursorIndexOfCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "completedAt");
          final int _cursorIndexOfSyncedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "syncedAt");
          final int _cursorIndexOfIsDirty = CursorUtil.getColumnIndexOrThrow(_cursor, "isDirty");
          final List<MaintenanceTicketEntity> _result = new ArrayList<MaintenanceTicketEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final MaintenanceTicketEntity _item;
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
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpDescription;
            if (_cursor.isNull(_cursorIndexOfDescription)) {
              _tmpDescription = null;
            } else {
              _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
            }
            final TicketCategory _tmpCategory;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfCategory)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfCategory);
            }
            _tmpCategory = __converters.toTicketCategory(_tmp);
            final TicketStatus _tmpStatus;
            final String _tmp_1;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmp_1 = null;
            } else {
              _tmp_1 = _cursor.getString(_cursorIndexOfStatus);
            }
            _tmpStatus = __converters.toTicketStatus(_tmp_1);
            final String _tmpPriority;
            if (_cursor.isNull(_cursorIndexOfPriority)) {
              _tmpPriority = null;
            } else {
              _tmpPriority = _cursor.getString(_cursorIndexOfPriority);
            }
            final String _tmpReportedBy;
            if (_cursor.isNull(_cursorIndexOfReportedBy)) {
              _tmpReportedBy = null;
            } else {
              _tmpReportedBy = _cursor.getString(_cursorIndexOfReportedBy);
            }
            final String _tmpReportedByName;
            if (_cursor.isNull(_cursorIndexOfReportedByName)) {
              _tmpReportedByName = null;
            } else {
              _tmpReportedByName = _cursor.getString(_cursorIndexOfReportedByName);
            }
            final String _tmpAssignedTo;
            if (_cursor.isNull(_cursorIndexOfAssignedTo)) {
              _tmpAssignedTo = null;
            } else {
              _tmpAssignedTo = _cursor.getString(_cursorIndexOfAssignedTo);
            }
            final String _tmpAssignedToName;
            if (_cursor.isNull(_cursorIndexOfAssignedToName)) {
              _tmpAssignedToName = null;
            } else {
              _tmpAssignedToName = _cursor.getString(_cursorIndexOfAssignedToName);
            }
            final String _tmpImageUrl;
            if (_cursor.isNull(_cursorIndexOfImageUrl)) {
              _tmpImageUrl = null;
            } else {
              _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            final long _tmpUpdatedAt;
            _tmpUpdatedAt = _cursor.getLong(_cursorIndexOfUpdatedAt);
            final Long _tmpCompletedAt;
            if (_cursor.isNull(_cursorIndexOfCompletedAt)) {
              _tmpCompletedAt = null;
            } else {
              _tmpCompletedAt = _cursor.getLong(_cursorIndexOfCompletedAt);
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
            _item = new MaintenanceTicketEntity(_tmpId,_tmpHotelId,_tmpRoomId,_tmpRoomNumber,_tmpTitle,_tmpDescription,_tmpCategory,_tmpStatus,_tmpPriority,_tmpReportedBy,_tmpReportedByName,_tmpAssignedTo,_tmpAssignedToName,_tmpImageUrl,_tmpCreatedAt,_tmpUpdatedAt,_tmpCompletedAt,_tmpSyncedAt,_tmpIsDirty);
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
